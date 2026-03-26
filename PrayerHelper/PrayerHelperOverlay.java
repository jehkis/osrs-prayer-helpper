package com.example.prayerhelper;

import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayPriority;
import net.runelite.api.Client;
import net.runelite.api.coords.Point;
import net.runelite.api.SpriteID;
import net.runelite.api.Sprite;
import java.awt.image.BufferedImage;
import javax.inject.Inject;
import java.awt.*;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetInfo;
import net.runelite.api.Prayer;
import com.example.prayerhelper.PrayerHelperConfig;

public class PrayerHelperOverlay extends Overlay {
    private final Client client;
    private String currentHint = null;
    private BufferedImage prayerIcon = null;
    private PrayerHelperConfig config;
    private String debugInfo = null;

    public void setDebugInfo(String info) {
        this.debugInfo = info;
    }

    @Inject
    public PrayerHelperOverlay(Client client, PrayerHelperConfig config) {
        setPosition(OverlayPosition.DYNAMIC);
        setLayer(OverlayLayer.ABOVE_WIDGETS);
        setPriority(OverlayPriority.HIGH);
        this.client = client;
        this.config = config;
    }

    public void setHint(String hint, BufferedImage icon) {
        this.currentHint = hint;
        this.prayerIcon = icon;
    }

    public void clearHint() {
        this.currentHint = null;
        this.prayerIcon = null;
    }

    // Prayer highlight -tuki
    private Prayer highlightPrayer = null;
    public void setHighlightPrayer(Prayer prayer) {
        this.highlightPrayer = prayer;
    }
    public void clearHighlightPrayer() {
        this.highlightPrayer = null;
    }

    @Override
    public Dimension render(Graphics2D graphics) {
        if ((currentHint == null || currentHint.isEmpty()) && prayerIcon == null && highlightPrayer == null && debugInfo == null) {
            return null;
        }
        int centerX = config != null ? config.overlayPosX() : 260;
        int centerY = config != null ? config.overlayPosY() : 140;
        int size = config != null ? config.overlaySize() : 32;
        Color color = Color.RED;
        try {
            if (config != null) {
                color = Color.decode(config.overlayColor());
            }
        } catch (Exception ignored) {}

        // Korosta prayer-painike, jos highlightPrayer asetettu
        if (highlightPrayer != null) {
            Widget widget = null;
            switch (highlightPrayer) {
                case PROTECT_FROM_MAGIC:
                    widget = client.getWidget(WidgetInfo.PRAYER_PROTECT_FROM_MAGIC);
                    break;
                case PROTECT_FROM_MISSILES:
                    widget = client.getWidget(WidgetInfo.PRAYER_PROTECT_FROM_MISSILES);
                    break;
                case PROTECT_FROM_MELEE:
                    widget = client.getWidget(WidgetInfo.PRAYER_PROTECT_FROM_MELEE);
                    break;
            }
            if (widget != null && !widget.isHidden()) {
                Rectangle bounds = widget.getBounds();
                if (bounds != null) {
                    graphics.setColor(new Color(255, 0, 0, 180));
                    graphics.setStroke(new BasicStroke(4));
                    graphics.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);
                }
            }
        }

        // Piirrä ikoni
        if (prayerIcon != null && (config == null || config.showIcon())) {
            int iconWidth = size;
            int iconHeight = size;
            graphics.drawImage(prayerIcon, centerX - iconWidth / 2, centerY - iconHeight / 2, iconWidth, iconHeight, null);
        }
        // Piirrä teksti
        if (currentHint != null && !currentHint.isEmpty() && (config == null || config.showText())) {
            graphics.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 200));
            graphics.setFont(new Font("Arial", Font.BOLD, size / 2));
            int textY = centerY + size / 2 + 24;
            int textWidth = graphics.getFontMetrics().stringWidth(currentHint);
            graphics.drawString(currentHint, centerX - textWidth / 2, textY);
        }
        // Piirrä debug-info overlayn alapuolelle, jos debug-tila päällä
        if (debugInfo != null && !debugInfo.isEmpty() && config != null && config.debugMode()) {
            graphics.setColor(new Color(0, 0, 0, 180));
            graphics.setFont(new Font("Consolas", Font.PLAIN, 14));
            int debugY = centerY + size / 2 + 44;
            int debugWidth = graphics.getFontMetrics().stringWidth(debugInfo);
            graphics.drawString(debugInfo, centerX - debugWidth / 2, debugY);
        }
        return null;
    }
}
