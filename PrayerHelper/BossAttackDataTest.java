package com.example.prayerhelper;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BossAttackDataTest {
    @Test
    public void testOlmLeftHandMage() {
        // Oletetaan että animaatio 7357 on MAGE left_hand phase
        assertEquals(BossAttackData.AttackType.MAGE,
            BossAttackData.getAttackType("great olm", 7357));
    }

    @Test
    public void testOlmRightHandRange() {
        // Oletetaan että animaatio 7358 on RANGE right_hand phase
        assertEquals(BossAttackData.AttackType.RANGE,
            BossAttackData.getAttackType("great olm", 7358));
    }

    @Test
    public void testOlmHeadMelee() {
        // Oletetaan että animaatio 7359 on MELEE head phase
        assertEquals(BossAttackData.AttackType.MELEE,
            BossAttackData.getAttackType("great olm", 7359));
    }

    @Test
    public void testUnknownBossReturnsNull() {
        assertNull(BossAttackData.getAttackType("tuntematon bossi", 12345));
    }

    @Test
    public void testUnknownAnimReturnsNull() {
        assertNull(BossAttackData.getAttackType("great olm", 9999));
    }
}
