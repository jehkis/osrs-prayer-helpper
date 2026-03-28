package com.example.prayerhelper;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;
import org.junit.Assert;
import org.junit.Test;

public class PrayerHelperPluginTest {

    public static void main(String[] args) throws Exception {
        ExternalPluginManager.loadBuiltin(PrayerHelperPlugin.class);
        RuneLite.main(args);
    }

    @Test
    public void isPrayerLowReturnsTrueAtThreshold() {
        Assert.assertTrue(PrayerHelperPlugin.isPrayerLow(10, 10));
    }

    @Test
    public void isPrayerLowReturnsFalseAboveThreshold() {
        Assert.assertFalse(PrayerHelperPlugin.isPrayerLow(11, 10));
    }
}
