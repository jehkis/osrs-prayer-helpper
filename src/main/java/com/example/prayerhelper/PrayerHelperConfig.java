package com.example.prayerhelper;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.Range;

@ConfigGroup("prayerhelper")
public interface PrayerHelperConfig extends Config {

    @ConfigItem(
        keyName = "enabled",
        name = "Enable plugin",
        description = "Enable low prayer reminder"
    )
    default boolean enabled() {
        return true;
    }

    @Range(
        min = 1,
        max = 99
    )
    @ConfigItem(
        keyName = "prayerThreshold",
        name = "Prayer threshold",
        description = "Show warning when current prayer is at or below this value"
    )
    default int prayerThreshold() {
        return 10;
    }

    @ConfigItem(
        keyName = "showOverlay",
        name = "Show overlay",
        description = "Show on-screen warning overlay"
    )
    default boolean showOverlay() {
        return true;
    }
}
