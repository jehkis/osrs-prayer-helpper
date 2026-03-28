package com.example.prayerhelper;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import javax.inject.Inject;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayPriority;
import net.runelite.client.ui.overlay.components.LineComponent;
import net.runelite.client.ui.overlay.components.PanelComponent;

public class PrayerHelperOverlay extends Overlay {
    private static final Color WARNING_COLOR = new Color(211, 47, 47);

    private final PrayerHelperPlugin plugin;
    private final PrayerHelperConfig config;
    private final PanelComponent panelComponent = new PanelComponent();

    @Inject
    public PrayerHelperOverlay(PrayerHelperPlugin plugin, PrayerHelperConfig config) {
        this.plugin = plugin;
        this.config = config;

        setPosition(OverlayPosition.TOP_LEFT);
        setLayer(OverlayLayer.ABOVE_SCENE);
        setPriority(OverlayPriority.HIGH);
    }

    @Override
    public Dimension render(Graphics2D graphics) {
        if (!config.enabled() || !config.showOverlay() || !plugin.isLowPrayerActive()) {
            return null;
        }

        panelComponent.getChildren().clear();
        panelComponent.getChildren().add(LineComponent.builder()
            .left("Prayer Helper")
            .right("Warning")
            .leftColor(WARNING_COLOR)
            .rightColor(WARNING_COLOR)
            .build());
        panelComponent.getChildren().add(LineComponent.builder()
            .left("Prayer at or below")
            .right(String.valueOf(config.prayerThreshold()))
            .build());

        return panelComponent.render(graphics);
    }
}
