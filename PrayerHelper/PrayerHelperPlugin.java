import net.runelite.api.events.StatChanged;
import net.runelite.api.Skill;
    private boolean lowHpWarningActive = false;
    private boolean lowPrayerWarningActive = false;
        @Subscribe
        public void onPrayerStatChanged(StatChanged event) {
            if (event.getSkill() == Skill.PRAYER) {
                int currentPrayer = event.getValue();
                int maxPrayer = event.getMaximum();
                if (maxPrayer > 0 && currentPrayer <= (maxPrayer * 0.2)) {
                    if (!lowPrayerWarningActive) {
                        overlay.setHint("VAROITUS: Prayer-pisteet vähissä!", null);
                        lowPrayerWarningActive = true;
                    }
                } else {
                    if (lowPrayerWarningActive) {
                        overlay.clearHint();
                        lowPrayerWarningActive = false;
                    }
                }
            }
        }
    @Subscribe
    public void onStatChanged(StatChanged event) {
        if (event.getSkill() == Skill.HITPOINTS) {
            int currentHp = event.getValue();
            int maxHp = event.getMaximum();
            if (maxHp > 0 && currentHp <= (maxHp * 0.3)) {
                if (!lowHpWarningActive) {
                    overlay.setHint("VAROITUS: Matala HP!", null);
                    lowHpWarningActive = true;
                }
            } else {
                if (lowHpWarningActive) {
                    overlay.clearHint();
                    lowHpWarningActive = false;
                }
            }
        }
    }
package com.example.prayerhelper;

import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;
import javax.inject.Inject;
import net.runelite.api.Client;
import com.example.prayerhelper.PrayerHelperConfig;
import net.runelite.api.SpriteID;
import net.runelite.api.events.AnimationChanged;
import net.runelite.api.NPC;
import net.runelite.api.events.NpcSpawned;
import net.runelite.api.events.NpcDespawned;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.PrayerChanged;
import net.runelite.api.Prayer;
import net.runelite.api.Actor;
import net.runelite.client.eventbus.Subscribe;
import java.awt.image.BufferedImage;
import com.example.prayerhelper.BossAttackData;
import java.util.Locale;

@PluginDescriptor(
    name = "Prayer Helper",
    description = "Antaa visuaalisia vihjeitä prayerin vaihtoon ja muokkaa prayer-valikkoa.",
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


    // Esimerkkimuuttuja: seurataan yhtä boss-NPC:tä (voit laajentaa useampaan)
    private NPC trackedBoss = null;
    // Käytetään asetuksia

    @Override
    protected void startUp() throws Exception {
        overlayManager.add(overlay);
        overlay.clearHint();
    }

    @Subscribe
    public void onNpcSpawned(NpcSpawned event) {
        NPC npc = event.getNpc();
        if (config.autoDetection()) {
            if (BossAttackData.ZULRAH_NPC_IDS.contains(npc.getId())) {
                trackedBoss = npc;
                overlay.setHint("Aloita Zulrah!", client.getSprite(SpriteID.PRAYER_PROTECT_FROM_MAGIC, 0));
                return;
            }
            // Lisää muita bosseja tähän samalla mallilla
        } else {
            String manualBossName = config.manualBoss();
            if (!manualBossName.isEmpty() && npc.getName() != null &&
                npc.getName().toLowerCase(Locale.ROOT).contains(manualBossName.toLowerCase(Locale.ROOT))) {
                trackedBoss = npc;
                overlay.setHint("Aloita " + manualBossName + "!", client.getSprite(SpriteID.PRAYER_PROTECT_FROM_MAGIC, 0));
            }
        }
    }

    @Subscribe
    public void onNpcDespawned(NpcDespawned event) {
        if (event.getNpc() == trackedBoss) {
            trackedBoss = null;
            overlay.clearHint();
        }
    }

    private BossAttackData.AttackType lastAttackType = null;

    @Subscribe
    public void onAnimationChanged(AnimationChanged event) {
        Actor actor = event.getActor();
        if (actor instanceof NPC && actor == trackedBoss) {
            NPC npc = (NPC) actor;
            int anim = npc.getAnimation();
            String bossName = config.autoDetection() && BossAttackData.ZULRAH_NPC_IDS.contains(npc.getId()) ? "zulrah" : (!config.manualBoss().isEmpty() ? config.manualBoss() : npc.getName());
            BossAttackData.AttackType type = BossAttackData.getAttackType(bossName, anim);
            lastAttackType = type;
            // Erikoishyökkäys: Zulrah Jad phase (animaatio 5806)
            if ("zulrah".equalsIgnoreCase(bossName) && anim == 5806) {
                overlay.setHint("Zulrah JAD-vaihe! Vaihda prayer joka hyökkäyksellä!", null);
            }
            if (config.debugMode()) {
                String debugMsg = String.format("[PrayerHelper DEBUG] Boss: %s, NPC ID: %d, Animation: %d, AttackType: %s", bossName, npc.getId(), anim, type);
                System.out.println(debugMsg);
                overlay.setDebugInfo(debugMsg);
            } else {
                overlay.setDebugInfo(null);
            }
            if (type != null) {
                String text = null;
                BufferedImage icon = null;
                switch (type) {
                    case MAGE:
                        text = "Vaihda Mage!";
                        icon = client.getSprite(SpriteID.PRAYER_PROTECT_FROM_MAGIC, 0);
                        break;
                    case RANGE:
                        text = "Vaihda Range!";
                        icon = client.getSprite(SpriteID.PRAYER_PROTECT_FROM_MISSILES, 0);
                        break;
                    case MELEE:
                        text = "Vaihda Melee!";
                        icon = client.getSprite(SpriteID.PRAYER_PROTECT_FROM_MELEE, 0);
                        break;
                }
                if (config.showOverlay() && (config.showIcon() || config.showText())) {
                    overlay.setHint(config.showText() ? text : "", config.showIcon() ? icon : null);
                } else {
                    overlay.clearHint();
                }
            }
        }
    }

    @Subscribe
    public void onGameTick(GameTick tick) {
        // Prayerin tilan tarkistus: jos oikea prayer ei ole päällä, näytä varoitus ja korosta painike
        if (lastAttackType != null && trackedBoss != null) {
            boolean prayerActive = false;
            Prayer highlight = null;
            switch (lastAttackType) {
                case MAGE:
                    prayerActive = client.isPrayerActive(Prayer.PROTECT_FROM_MAGIC);
                    highlight = Prayer.PROTECT_FROM_MAGIC;
                    break;
                case RANGE:
                    prayerActive = client.isPrayerActive(Prayer.PROTECT_FROM_MISSILES);
                    highlight = Prayer.PROTECT_FROM_MISSILES;
                    break;
                case MELEE:
                    prayerActive = client.isPrayerActive(Prayer.PROTECT_FROM_MELEE);
                    highlight = Prayer.PROTECT_FROM_MELEE;
                    break;
            }
            if (!prayerActive) {
                overlay.setHint("VAROITUS: Prayer ei ole päällä!", null);
                overlay.setHighlightPrayer(highlight);
            } else {
                overlay.clearHighlightPrayer();
            }
        } else {
            overlay.clearHighlightPrayer();
        }
    }

    @Override
    protected void shutDown() throws Exception {
        overlayManager.remove(overlay);
        overlay.clearHint();
        trackedBoss = null;
    }
}
