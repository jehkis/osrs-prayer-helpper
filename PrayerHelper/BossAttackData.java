package com.example.prayerhelper;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class BossAttackData {
    public enum AttackType {
        MAGE,
        RANGE,
        MELEE
    }

    public static final Set<Integer> ZULRAH_NPC_IDS = Set.of(2042, 2043, 2044);
    public static final Set<Integer> VORKATH_NPC_IDS = Set.of(8059);
    public static final Set<Integer> SARACHNIS_NPC_IDS = Set.of(8340);

    private static final Map<String, Map<Integer, AttackType>> BOSS_ATTACK_MAP = new HashMap<>();

    static {
        Map<Integer, AttackType> jadAttacks = new HashMap<>();
        jadAttacks.put(2656, AttackType.MAGE);
        jadAttacks.put(2652, AttackType.RANGE);
        jadAttacks.put(2654, AttackType.MELEE);
        BOSS_ATTACK_MAP.put("jad", jadAttacks);

        Map<Integer, AttackType> olmAttacks = new HashMap<>();
        olmAttacks.put(7357, AttackType.MAGE);
        olmAttacks.put(7358, AttackType.RANGE);
        olmAttacks.put(7359, AttackType.MELEE);
        BOSS_ATTACK_MAP.put("great olm", olmAttacks);

        Map<Integer, AttackType> zulrahAttacks = new HashMap<>();
        zulrahAttacks.put(5806, AttackType.MAGE);
        zulrahAttacks.put(5807, AttackType.RANGE);
        BOSS_ATTACK_MAP.put("zulrah", zulrahAttacks);

        Map<Integer, AttackType> vorkathAttacks = new HashMap<>();
        vorkathAttacks.put(7952, AttackType.MAGE);
        vorkathAttacks.put(7954, AttackType.RANGE);
        BOSS_ATTACK_MAP.put("vorkath", vorkathAttacks);

        Map<Integer, AttackType> sarachnisAttacks = new HashMap<>();
        sarachnisAttacks.put(8078, AttackType.MELEE);
        sarachnisAttacks.put(8079, AttackType.RANGE);
        BOSS_ATTACK_MAP.put("sarachnis", sarachnisAttacks);

        Map<Integer, AttackType> rexAttacks = new HashMap<>();
        rexAttacks.put(2855, AttackType.MELEE);
        BOSS_ATTACK_MAP.put("dagannoth rex", rexAttacks);

        Map<Integer, AttackType> primeAttacks = new HashMap<>();
        primeAttacks.put(2854, AttackType.MAGE);
        BOSS_ATTACK_MAP.put("dagannoth prime", primeAttacks);

        Map<Integer, AttackType> supremeAttacks = new HashMap<>();
        supremeAttacks.put(2856, AttackType.RANGE);
        BOSS_ATTACK_MAP.put("dagannoth supreme", supremeAttacks);

        Map<Integer, AttackType> graardorAttacks = new HashMap<>();
        graardorAttacks.put(7018, AttackType.MELEE);
        graardorAttacks.put(7021, AttackType.RANGE);
        BOSS_ATTACK_MAP.put("general graardor", graardorAttacks);

        Map<Integer, AttackType> zilyanaAttacks = new HashMap<>();
        zilyanaAttacks.put(6967, AttackType.MELEE);
        zilyanaAttacks.put(6970, AttackType.MAGE);
        BOSS_ATTACK_MAP.put("commander zilyana", zilyanaAttacks);

        Map<Integer, AttackType> krilAttacks = new HashMap<>();
        krilAttacks.put(6945, AttackType.MELEE);
        krilAttacks.put(6947, AttackType.MAGE);
        BOSS_ATTACK_MAP.put("k'ril tsutsaroth", krilAttacks);

        Map<Integer, AttackType> kreeArraAttacks = new HashMap<>();
        kreeArraAttacks.put(6972, AttackType.RANGE);
        kreeArraAttacks.put(6973, AttackType.MAGE);
        BOSS_ATTACK_MAP.put("kree'arra", kreeArraAttacks);
    }

    private BossAttackData() {
    }

    public static AttackType getAttackType(String bossName, int animationId) {
        if (bossName == null) {
            return null;
        }

        Map<Integer, AttackType> attacks = BOSS_ATTACK_MAP.get(bossName.toLowerCase());
        if (attacks == null) {
            return null;
        }

        return attacks.get(animationId);
    }
}
