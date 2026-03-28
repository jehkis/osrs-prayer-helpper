package com.example.prayerhelper;

import com.google.inject.Provides;
import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.Skill;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.StatChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

@PluginDescriptor(
    name = "Prayer Helper",
    description = "Warns when prayer points fall below a configurable threshold",
    tags = {"prayer", "warning", "overlay"}
)
public class PrayerHelperPlugin extends Plugin {

    @Inject
    private Client client;

    @Inject
    private OverlayManager overlayManager;

    @Inject
    private PrayerHelperOverlay overlay;

    @Inject
    private PrayerHelperConfig config;

    private boolean lowPrayerActive;

    @Provides
    PrayerHelperConfig provideConfig(ConfigManager configManager) {
        return configManager.getConfig(PrayerHelperConfig.class);
    }

    @Override
    protected void startUp() {
        overlayManager.add(overlay);
        lowPrayerActive = false;
    }

    @Override
    protected void shutDown() {
        overlayManager.remove(overlay);
        lowPrayerActive = false;
    }

    @Subscribe
    public void onGameStateChanged(GameStateChanged event) {
        if (event.getGameState() == GameState.LOGIN_SCREEN || event.getGameState() == GameState.HOPPING) {
            lowPrayerActive = false;
        }
    }

    @Subscribe
    public void onStatChanged(StatChanged event) {
        if (!config.enabled() || event.getSkill() != Skill.PRAYER) {
            return;
        }

        lowPrayerActive = isPrayerLow(event.getBoostedLevel(), config.prayerThreshold());
    }

    boolean isLowPrayerActive() {
        return lowPrayerActive;
    }

    static boolean isPrayerLow(int currentPrayer, int threshold) {
        return currentPrayer <= threshold;
    }
}
