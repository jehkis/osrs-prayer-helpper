package com.example.prayerhelper;

import com.google.inject.Provides;
import java.util.Locale;
import javax.inject.Inject;
import net.runelite.api.Actor;
import net.runelite.api.Client;
import net.runelite.api.NPC;
import net.runelite.api.Prayer;
import net.runelite.api.Skill;
import net.runelite.api.events.AnimationChanged;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.NpcDespawned;
import net.runelite.api.events.NpcSpawned;
import net.runelite.api.events.StatChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

@PluginDescriptor(
    name = "Prayer Helper",
    description = "Antaa visuaalisia vihjeita prayerin vaihtoon ja korostaa oikeaa suojaprayeria.",
    tags = {"prayer", "helper", "overlay", "boss", "raid"}
)
public class PrayerHelperPlugin extends Plugin {

    @Inject
    private OverlayManager overlayManager;

    @Inject
    private PrayerHelperOverlay overlay;

    @Inject
    private Client client;

    @Inject
    private PrayerHelperConfig config;

    private NPC trackedBoss;
    private BossAttackData.AttackType lastAttackType;
    private boolean lowHpWarningActive;
    private boolean lowPrayerWarningActive;

    @Provides
    PrayerHelperConfig provideConfig(ConfigManager configManager) {
        return configManager.getConfig(PrayerHelperConfig.class);
    }

    @Override
    protected void startUp() {
        overlayManager.add(overlay);
        overlay.clearHint();
    }

    @Override
    protected void shutDown() {
        overlayManager.remove(overlay);
        overlay.clearHint();
        overlay.clearHighlightPrayer();
        trackedBoss = null;
        lastAttackType = null;
        lowHpWarningActive = false;
        lowPrayerWarningActive = false;
    }

    @Subscribe
    public void onNpcSpawned(NpcSpawned event) {
        NPC npc = event.getNpc();

        if (config.autoDetection()) {
            if (BossAttackData.ZULRAH_NPC_IDS.contains(npc.getId())) {
                trackedBoss = npc;
                overlay.setHint("Aloita Zulrah!", null);
            }
            return;
        }

        String manualBossName = config.manualBoss();
        if (!manualBossName.isEmpty() && npc.getName() != null
            && npc.getName().toLowerCase(Locale.ROOT).contains(manualBossName.toLowerCase(Locale.ROOT))) {
            trackedBoss = npc;
            overlay.setHint("Aloita " + manualBossName + "!", null);
        }
    }

    @Subscribe
    public void onNpcDespawned(NpcDespawned event) {
        if (event.getNpc() == trackedBoss) {
            trackedBoss = null;
            lastAttackType = null;
            overlay.clearHint();
            overlay.clearHighlightPrayer();
        }
    }

    @Subscribe
    public void onAnimationChanged(AnimationChanged event) {
        Actor actor = event.getActor();
        if (!(actor instanceof NPC) || actor != trackedBoss) {
            return;
        }

        NPC npc = (NPC) actor;
        int animationId = npc.getAnimation();

        String bossName = config.autoDetection() && BossAttackData.ZULRAH_NPC_IDS.contains(npc.getId())
            ? "zulrah"
            : (!config.manualBoss().isEmpty() ? config.manualBoss() : npc.getName());

        BossAttackData.AttackType attackType = BossAttackData.getAttackType(bossName, animationId);
        lastAttackType = attackType;

        if ("zulrah".equalsIgnoreCase(bossName) && animationId == 5806) {
            overlay.setHint("Zulrah JAD-vaihe! Vaihda prayer joka hyokkayksella!", null);
        }

        if (config.debugMode()) {
            String debugMsg = String.format(
                "[PrayerHelper DEBUG] Boss: %s, NPC ID: %d, Animation: %d, AttackType: %s",
                bossName,
                npc.getId(),
                animationId,
                attackType
            );
            System.out.println(debugMsg);
            overlay.setDebugInfo(debugMsg);
        } else {
            overlay.setDebugInfo(null);
        }

        if (attackType == null) {
            return;
        }

        String text = null;
        switch (attackType) {
            case MAGE:
                text = "Vaihda Mage!";
                break;
            case RANGE:
                text = "Vaihda Range!";
                break;
            case MELEE:
                text = "Vaihda Melee!";
                break;
            default:
                break;
        }

        if (config.showOverlay() && (config.showIcon() || config.showText())) {
            overlay.setHint(config.showText() ? text : "", null);
        } else {
            overlay.clearHint();
        }
    }

    @Subscribe
    public void onGameTick(GameTick tick) {
        if (lastAttackType == null || trackedBoss == null) {
            overlay.clearHighlightPrayer();
            return;
        }

        Prayer expectedPrayer;
        switch (lastAttackType) {
            case MAGE:
                expectedPrayer = Prayer.PROTECT_FROM_MAGIC;
                break;
            case RANGE:
                expectedPrayer = Prayer.PROTECT_FROM_MISSILES;
                break;
            case MELEE:
                expectedPrayer = Prayer.PROTECT_FROM_MELEE;
                break;
            default:
                overlay.clearHighlightPrayer();
                return;
        }

        if (!client.isPrayerActive(expectedPrayer)) {
            overlay.setHint("VAROITUS: Prayer ei ole paalla!", null);
            overlay.setHighlightPrayer(expectedPrayer);
        } else {
            overlay.clearHighlightPrayer();
        }
    }

    @Subscribe
    public void onPrayerStatChanged(StatChanged event) {
        if (event.getSkill() != Skill.PRAYER) {
            return;
        }

        int currentPrayer = event.getBoostedLevel();
        int maxPrayer = event.getLevel();
        if (maxPrayer > 0 && currentPrayer <= (maxPrayer * 0.2)) {
            if (!lowPrayerWarningActive) {
                overlay.setHint("VAROITUS: Prayer-pisteet vahissa!", null);
                lowPrayerWarningActive = true;
            }
        } else if (lowPrayerWarningActive) {
            overlay.clearHint();
            lowPrayerWarningActive = false;
        }
    }

    @Subscribe
    public void onStatChanged(StatChanged event) {
        if (event.getSkill() != Skill.HITPOINTS) {
            return;
        }

        int currentHp = event.getBoostedLevel();
        int maxHp = event.getLevel();
        if (maxHp > 0 && currentHp <= (maxHp * 0.3)) {
            if (!lowHpWarningActive) {
                overlay.setHint("VAROITUS: Matala HP!", null);
                lowHpWarningActive = true;
            }
        } else if (lowHpWarningActive) {
            overlay.clearHint();
            lowHpWarningActive = false;
        }
    }
}
