        @ConfigItem(
            keyName = "debugMode",
            name = "Debug-tila",
            description = "Näytä kehittäjälle animaatio-ID:t ja muut tiedot overlayssa ja konsolissa"
        )
        default boolean debugMode() { return false; }
    @ConfigItem(
        keyName = "overlayPosX",
        name = "Overlayn X-sijainti",
        description = "Overlayn keskikohdan X-koordinaatti (esim. 260)"
    )
    default int overlayPosX() { return 260; }

    @ConfigItem(
        keyName = "overlayPosY",
        name = "Overlayn Y-sijainti",
        description = "Overlayn keskikohdan Y-koordinaatti (esim. 140)"
    )
    default int overlayPosY() { return 140; }
package com.example.prayerhelper;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("prayerhelper")
public interface PrayerHelperConfig extends Config {
    @ConfigItem(
        keyName = "showOverlay",
        name = "Näytä overlay",
        description = "Näytä visuaalinen vihje prayerin vaihdosta"
    )
    default boolean showOverlay() { return true; }

    @ConfigItem(
        keyName = "showIcon",
        name = "Näytä prayer-ikoni",
        description = "Näytä overlayssa prayerin ikoni"
    )
    default boolean showIcon() { return true; }

    @ConfigItem(
        keyName = "showText",
        name = "Näytä overlay-teksti",
        description = "Näytä overlayssa teksti (esim. 'Vaihda Mage!')"
    )
    default boolean showText() { return true; }

    @ConfigItem(
        keyName = "bigIcons",
        name = "Isommat prayer-ikonit",
        description = "Tee prayer-valikon ikoneista isompia"
    )
    default boolean bigIcons() { return false; }

    @ConfigItem(
        keyName = "autoDetection",
        name = "Automaattinen bossin tunnistus",
        description = "Tunnista bossi automaattisesti NPC ID:n perusteella"
    )
    default boolean autoDetection() { return true; }

    @ConfigItem(
        keyName = "manualBoss",
        name = "Manuaalinen bossin valinta",
        description = "Valitse bossi manuaalisesti, jos automaattinen tunnistus ei ole käytössä"
    )
    default String manualBoss() { return ""; }

    @ConfigItem(
        keyName = "overlayColor",
        name = "Overlayn väri",
        description = "Overlayn tekstin ja ikonin väri (heksana, esim. #FF0000)"
    )
    default String overlayColor() { return "#FF0000"; }

    @ConfigItem(
        keyName = "overlaySize",
        name = "Overlayn koko",
        description = "Overlayn tekstin ja ikonin koko (esim. 32)"
    )
    default int overlaySize() { return 32; }
}
