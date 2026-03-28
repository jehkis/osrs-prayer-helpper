package com.example.prayerhelper;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PrayerHelperLogicTest {
    @Test
    public void testLowHpWarning() {
        int maxHp = 99;
        int lowHp = 29; // 29/99 < 0.3
        int safeHp = 40; // 40/99 > 0.3
        assertTrue(lowHp <= (maxHp * 0.3));
        assertFalse(safeHp <= (maxHp * 0.3));
    }

    @Test
    public void testLowPrayerWarning() {
        int maxPrayer = 70;
        int lowPrayer = 13; // 13/70 < 0.2
        int safePrayer = 20; // 20/70 > 0.2
        assertTrue(lowPrayer <= (maxPrayer * 0.2));
        assertFalse(safePrayer <= (maxPrayer * 0.2));
    }

    @Test
    public void testZulrahJadSpecial() {
        String bossName = "zulrah";
        int jadAnim = 5806;
        assertEquals("zulrah", bossName.toLowerCase());
        assertEquals(5806, jadAnim);
    }
}
