
    import java.util.Set;
        // Zulrah NPC ID:t automaattista tunnistusta varten
        // Zulrah NPC ID:t automaattista tunnistusta varten
        public static final Set<Integer> ZULRAH_NPC_IDS = Set.of(2042, 2043, 2044); // Green, Blue, Red Zulrah
        // Vorkath NPC ID:t
        public static final Set<Integer> VORKATH_NPC_IDS = Set.of(8059);
        // Sarachnis NPC ID:t
        public static final Set<Integer> SARACHNIS_NPC_IDS = Set.of(8340);
        // Dagannoth Kings NPC ID:t
        public static final Set<Integer> DKS_REX_NPC_IDS = Set.of(2883);
        public static final Set<Integer> DKS_PRIME_NPC_IDS = Set.of(2882);
        public static final Set<Integer> DKS_SUPREME_NPC_IDS = Set.of(2881);
        // God Wars Dungeon bossit (esimerkit)
        public static final Set<Integer> GWD_GRAARDOR_NPC_IDS = Set.of(2215);
        public static final Set<Integer> GWD_ZILYANA_NPC_IDS = Set.of(2205);
        public static final Set<Integer> GWD_KRIL_NPC_IDS = Set.of(3129);
        public static final Set<Integer> GWD_KREEARRA_NPC_IDS = Set.of(3162);
    package com.example.prayerhelper;

    import java.util.HashMap;
    import java.util.Map;

    /**
     * BossAttackData sisältää bossin nimen ja hyökkäysanimaatioiden mappingin prayer-tyyppeihin.
     * Lisää tänne uusia bosseja ja niiden animaatiot helposti.
     */
    public class BossAttackData {
        public enum AttackType {
            MAGE, RANGE, MELEE
        }

        /**
         * PhaseAttackData sisältää yhden vaiheen hyökkäysanimaatiot ja niiden prayer-tyypit.
         */
        public static class PhaseAttackData {
            public final String phaseName;
            public final Map<Integer, AttackType> animationToType;

            public PhaseAttackData(String phaseName, Map<Integer, AttackType> animationToType) {
                this.phaseName = phaseName;
                this.animationToType = animationToType;
            }
        }

        // Map: bossin nimi (lowercase) -> (vaihe -> PhaseAttackData)
        private static final Map<String, Map<String, PhaseAttackData>> bossPhaseMap = new HashMap<>();

        static {
                // Chambers of Xeric - Great Olm (esimerkkivaiheet ja animaatiot, tarkista oikeat ID:t)
                Map<String, PhaseAttackData> olmPhases = new HashMap<>();
                // Left hand phase
                Map<Integer, AttackType> olmLeft = new HashMap<>();
                olmLeft.put(7357, AttackType.MAGE); // Esimerkkianimaatio
                olmPhases.put("left_hand", new PhaseAttackData("left_hand", olmLeft));
                // Right hand phase
                Map<Integer, AttackType> olmRight = new HashMap<>();
                olmRight.put(7358, AttackType.RANGE);
                olmPhases.put("right_hand", new PhaseAttackData("right_hand", olmRight));
                // Head phase
                Map<Integer, AttackType> olmHead = new HashMap<>();
                olmHead.put(7359, AttackType.MELEE);
                olmPhases.put("head", new PhaseAttackData("head", olmHead));
                bossPhaseMap.put("great olm", olmPhases);

                // Desert Treasure II - The Leviathan (esimerkkivaiheet)
                Map<String, PhaseAttackData> leviathanPhases = new HashMap<>();
                Map<Integer, AttackType> leviathanP1 = new HashMap<>();
                leviathanP1.put(12353, AttackType.MAGE);
                leviathanPhases.put("phase1", new PhaseAttackData("phase1", leviathanP1));
                Map<Integer, AttackType> leviathanP2 = new HashMap<>();
                leviathanP2.put(12354, AttackType.RANGE);
                leviathanPhases.put("phase2", new PhaseAttackData("phase2", leviathanP2));
                bossPhaseMap.put("the leviathan", leviathanPhases);

                // Desert Treasure II - The Duke
                Map<String, PhaseAttackData> dukePhases = new HashMap<>();
                Map<Integer, AttackType> dukeP1 = new HashMap<>();
                dukeP1.put(12356, AttackType.MAGE);
                dukePhases.put("phase1", new PhaseAttackData("phase1", dukeP1));
                Map<Integer, AttackType> dukeP2 = new HashMap<>();
                dukeP2.put(12357, AttackType.RANGE);
                dukePhases.put("phase2", new PhaseAttackData("phase2", dukeP2));
                bossPhaseMap.put("the duke", dukePhases);

                // Desert Treasure II - The Whisperer
                Map<String, PhaseAttackData> whispererPhases = new HashMap<>();
                Map<Integer, AttackType> whispererP1 = new HashMap<>();
                whispererP1.put(12359, AttackType.MAGE);
                whispererPhases.put("phase1", new PhaseAttackData("phase1", whispererP1));
                Map<Integer, AttackType> whispererP2 = new HashMap<>();
                whispererP2.put(12360, AttackType.RANGE);
                whispererPhases.put("phase2", new PhaseAttackData("phase2", whispererP2));
                bossPhaseMap.put("the whisperer", whispererPhases);

                // Desert Treasure II - Vardorvis
                Map<String, PhaseAttackData> vardorvisPhases = new HashMap<>();
                Map<Integer, AttackType> vardorvisP1 = new HashMap<>();
                vardorvisP1.put(12362, AttackType.MAGE);
                vardorvisPhases.put("phase1", new PhaseAttackData("phase1", vardorvisP1));
                Map<Integer, AttackType> vardorvisP2 = new HashMap<>();
                vardorvisP2.put(12363, AttackType.RANGE);
                vardorvisPhases.put("phase2", new PhaseAttackData("phase2", vardorvisP2));
                bossPhaseMap.put("vardorvis", vardorvisPhases);
            // Esimerkki: Theatre of Blood - Verzik Vitur
            Map<String, PhaseAttackData> verzikPhases = new HashMap<>();

            // Zulrah
            Map<Integer, AttackType> zulrahAttacks = new HashMap<>();
            zulrahAttacks.put(5806, AttackType.MAGE);   // Magic attack
            zulrahAttacks.put(5807, AttackType.RANGE);  // Ranged attack
            bossAttackMap.put("zulrah", zulrahAttacks);

            // Vorkath
            Map<Integer, AttackType> vorkathAttacks = new HashMap<>();
            vorkathAttacks.put(7952, AttackType.MAGE);   // Dragonfire (Magic)
            vorkathAttacks.put(7954, AttackType.RANGE);  // Ranged attack
            bossAttackMap.put("vorkath", vorkathAttacks);

            // Sarachnis
            Map<Integer, AttackType> sarachnisAttacks = new HashMap<>();
            sarachnisAttacks.put(8078, AttackType.MELEE); // Melee
            sarachnisAttacks.put(8079, AttackType.RANGE); // Ranged
            bossAttackMap.put("sarachnis", sarachnisAttacks);

            // Dagannoth Rex
            Map<Integer, AttackType> rexAttacks = new HashMap<>();
            rexAttacks.put(2855, AttackType.MELEE); // Melee attack
            bossAttackMap.put("dagannoth rex", rexAttacks);

            // Dagannoth Prime
            Map<Integer, AttackType> primeAttacks = new HashMap<>();
            primeAttacks.put(2854, AttackType.MAGE); // Magic attack
            bossAttackMap.put("dagannoth prime", primeAttacks);

            // Dagannoth Supreme
            Map<Integer, AttackType> supremeAttacks = new HashMap<>();
            supremeAttacks.put(2856, AttackType.RANGE); // Ranged attack
            bossAttackMap.put("dagannoth supreme", supremeAttacks);

            // General Graardor
            Map<Integer, AttackType> graardorAttacks = new HashMap<>();
            graardorAttacks.put(7018, AttackType.MELEE); // Melee
            graardorAttacks.put(7021, AttackType.RANGE); // Ranged
            bossAttackMap.put("general graardor", graardorAttacks);

            // Commander Zilyana
            Map<Integer, AttackType> zilyanaAttacks = new HashMap<>();
            zilyanaAttacks.put(6967, AttackType.MELEE); // Melee
            zilyanaAttacks.put(6970, AttackType.MAGE); // Magic
            bossAttackMap.put("commander zilyana", zilyanaAttacks);

            // K'ril Tsutsaroth
            Map<Integer, AttackType> krilAttacks = new HashMap<>();
            krilAttacks.put(6945, AttackType.MELEE); // Melee
            krilAttacks.put(6947, AttackType.MAGE); // Magic
            bossAttackMap.put("k'ril tsutsaroth", krilAttacks);

            // Kree'arra
            Map<Integer, AttackType> kreeArraAttacks = new HashMap<>();
            kreeArraAttacks.put(6972, AttackType.RANGE); // Ranged
            kreeArraAttacks.put(6973, AttackType.MAGE); // Magic
            bossAttackMap.put("kree'arra", kreeArraAttacks);
            vorkathAttacks.put(7954, AttackType.RANGE);  // Ranged attack
            bossAttackMap.put("vorkath", vorkathAttacks);

            // Phantom Muspah (esimerkkianimaatiot, tarkista oikeat ID:t)
            Map<Integer, AttackType> muspahAttacks = new HashMap<>();
            muspahAttacks.put(12348, AttackType.MAGE);   // Mage attack
            muspahAttacks.put(12349, AttackType.RANGE);  // Range attack
            bossAttackMap.put("phantom muspah", muspahAttacks);

            // Yama (Desert Treasure II, esimerkkianimaatiot)
            Map<Integer, AttackType> yamaAttacks = new HashMap<>();
            yamaAttacks.put(12350, AttackType.MAGE);
            yamaAttacks.put(12351, AttackType.RANGE);
            yamaAttacks.put(12352, AttackType.MELEE);
            bossAttackMap.put("yama", yamaAttacks);

            // Desert Treasure II bossit (esimerkkianimaatiot)
            Map<Integer, AttackType> leviathanAttacks = new HashMap<>();
            leviathanAttacks.put(12353, AttackType.MAGE);
            leviathanAttacks.put(12354, AttackType.RANGE);
            leviathanAttacks.put(12355, AttackType.MELEE);
            bossAttackMap.put("the leviathan", leviathanAttacks);

            Map<Integer, AttackType> dukeAttacks = new HashMap<>();
            dukeAttacks.put(12356, AttackType.MAGE);
            dukeAttacks.put(12357, AttackType.RANGE);
            dukeAttacks.put(12358, AttackType.MELEE);
            bossAttackMap.put("the duke", dukeAttacks);

            Map<Integer, AttackType> whispererAttacks = new HashMap<>();
            whispererAttacks.put(12359, AttackType.MAGE);
            whispererAttacks.put(12360, AttackType.RANGE);
            whispererAttacks.put(12361, AttackType.MELEE);
            bossAttackMap.put("the whisperer", whispererAttacks);

            Map<Integer, AttackType> vardorvisAttacks = new HashMap<>();
            vardorvisAttacks.put(12362, AttackType.MAGE);
            vardorvisAttacks.put(12363, AttackType.RANGE);
            vardorvisAttacks.put(12364, AttackType.MELEE);
            bossAttackMap.put("vardorvis", vardorvisAttacks);

        }

        public static AttackType getAttackType(String bossName, int animationId) {
            if (bossName == null) return null;
            Map<Integer, AttackType> attacks = bossAttackMap.get(bossName.toLowerCase());
            if (attacks == null) return null;
            return attacks.get(animationId);
        }
    }
    // Doom of Mokhaiotl (esimerkkianimaatiot, tarkista oikeat ID:t)
    Map<Integer, AttackType> doomAttacks = new HashMap<>();
    doomAttacks.put(12345, AttackType.MAGE);   // Mage attack
    doomAttacks.put(12346, AttackType.RANGE);  // Range attack
    doomAttacks.put(12347, AttackType.MELEE);  // Melee attack
    bossAttackMap.put("doom of mokhaiotl", doomAttacks);

    // Zulrah
    Map<Integer, AttackType> zulrahAttacks = new HashMap<>();
    zulrahAttacks.put(5806, AttackType.MAGE);   // Mage phase
    zulrahAttacks.put(5807, AttackType.RANGE);  // Range phase
    bossAttackMap.put("zulrah", zulrahAttacks);

    // Vorkath
    Map<Integer, AttackType> vorkathAttacks = new HashMap<>();
    vorkathAttacks.put(7952, AttackType.MAGE);   // Dragonfire
    vorkathAttacks.put(7954, AttackType.RANGE);  // Ranged attack
    bossAttackMap.put("vorkath", vorkathAttacks);

    // Phantom Muspah (esimerkkianimaatiot, tarkista oikeat ID:t)
    Map<Integer, AttackType> muspahAttacks = new HashMap<>();
    muspahAttacks.put(12348, AttackType.MAGE);   // Mage attack
    muspahAttacks.put(12349, AttackType.RANGE);  // Range attack
    bossAttackMap.put("phantom muspah", muspahAttacks);

    // Yama (Desert Treasure II, esimerkkianimaatiot)
    Map<Integer, AttackType> yamaAttacks = new HashMap<>();
    yamaAttacks.put(12350, AttackType.MAGE);
    yamaAttacks.put(12351, AttackType.RANGE);
    yamaAttacks.put(12352, AttackType.MELEE);
    bossAttackMap.put("yama", yamaAttacks);

    // Desert Treasure II bossit (esimerkkianimaatiot)
    Map<Integer, AttackType> leviathanAttacks = new HashMap<>();
    leviathanAttacks.put(12353, AttackType.MAGE);
    leviathanAttacks.put(12354, AttackType.RANGE);
    leviathanAttacks.put(12355, AttackType.MELEE);
    bossAttackMap.put("the leviathan", leviathanAttacks);

    Map<Integer, AttackType> dukeAttacks = new HashMap<>();
    dukeAttacks.put(12356, AttackType.MAGE);
    dukeAttacks.put(12357, AttackType.RANGE);
    dukeAttacks.put(12358, AttackType.MELEE);
    bossAttackMap.put("the duke", dukeAttacks);

    Map<Integer, AttackType> whispererAttacks = new HashMap<>();
    whispererAttacks.put(12359, AttackType.MAGE);
    whispererAttacks.put(12360, AttackType.RANGE);
    whispererAttacks.put(12361, AttackType.MELEE);
    bossAttackMap.put("the whisperer", whispererAttacks);

    Map<Integer, AttackType> vardorvisAttacks = new HashMap<>();
    vardorvisAttacks.put(12362, AttackType.MAGE);
    vardorvisAttacks.put(12363, AttackType.RANGE);
    vardorvisAttacks.put(12364, AttackType.MELEE);
    bossAttackMap.put("vardorvis", vardorvisAttacks);
package com.example.prayerhelper;

import java.util.HashMap;
import java.util.Map;

/**
 * BossAttackData sisältää bossin nimen ja hyökkäysanimaatioiden mappingin prayer-tyyppeihin.
 * Lisää tänne uusia bosseja ja niiden animaatiot helposti.
 */
public class BossAttackData {
    public enum AttackType {
        MAGE, RANGE, MELEE
    }

    // Map: bossin nimi (lowercase) -> (animaatioID -> AttackType)
    private static final Map<String, Map<Integer, AttackType>> bossAttackMap = new HashMap<>();

    static {
        // Jad
        Map<Integer, AttackType> jadAttacks = new HashMap<>();
        jadAttacks.put(2656, AttackType.MAGE);   // Mage attack
        jadAttacks.put(2652, AttackType.RANGE);  // Range attack
        jadAttacks.put(2654, AttackType.MELEE);  // Melee attack
        bossAttackMap.put("jad", jadAttacks);

        // Dagannoth Rex
        Map<Integer, AttackType> rexAttacks = new HashMap<>();
        rexAttacks.put(2855, AttackType.MELEE); // Melee attack
        bossAttackMap.put("dagannoth rex", rexAttacks);

        // Dagannoth Prime
        Map<Integer, AttackType> primeAttacks = new HashMap<>();
        primeAttacks.put(2854, AttackType.MAGE); // Mage attack
        bossAttackMap.put("dagannoth prime", primeAttacks);

        // Dagannoth Supreme
        Map<Integer, AttackType> supremeAttacks = new HashMap<>();
        supremeAttacks.put(2856, AttackType.RANGE); // Range attack
        bossAttackMap.put("dagannoth supreme", supremeAttacks);

        // Sarachnis
        Map<Integer, AttackType> sarachnisAttacks = new HashMap<>();
        sarachnisAttacks.put(8078, AttackType.MELEE); // Melee
        sarachnisAttacks.put(8079, AttackType.RANGE); // Range
        bossAttackMap.put("sarachnis", sarachnisAttacks);

        // Callisto
        Map<Integer, AttackType> callistoAttacks = new HashMap<>();
        callistoAttacks.put(4925, AttackType.MELEE); // Melee
        bossAttackMap.put("callisto", callistoAttacks);

        // Venenatis
        Map<Integer, AttackType> venenatisAttacks = new HashMap<>();
        venenatisAttacks.put(5327, AttackType.MELEE); // Melee
        venenatisAttacks.put(5328, AttackType.RANGE); // Range
        bossAttackMap.put("venenatis", venenatisAttacks);

        // Vet'ion
        Map<Integer, AttackType> vetionAttacks = new HashMap<>();
        vetionAttacks.put(5499, AttackType.MELEE); // Melee
        bossAttackMap.put("vet'ion", vetionAttacks);

        // Chaos Elemental
        Map<Integer, AttackType> chaosElementalAttacks = new HashMap<>();
        chaosElementalAttacks.put(3146, AttackType.MAGE); // Mage
        chaosElementalAttacks.put(3147, AttackType.RANGE); // Range
        bossAttackMap.put("chaos elemental", chaosElementalAttacks);

        // Scorpia
        Map<Integer, AttackType> scorpiaAttacks = new HashMap<>();
        scorpiaAttacks.put(6254, AttackType.MELEE); // Melee
        bossAttackMap.put("scorpia", scorpiaAttacks);

        // Crazy Archaeologist
        Map<Integer, AttackType> crazyArchaeologistAttacks = new HashMap<>();
        crazyArchaeologistAttacks.put(5491, AttackType.RANGE); // Range
        bossAttackMap.put("crazy archaeologist", crazyArchaeologistAttacks);

        // Chaos Fanatic
        Map<Integer, AttackType> chaosFanaticAttacks = new HashMap<>();
        chaosFanaticAttacks.put(6619, AttackType.MAGE); // Mage
        bossAttackMap.put("chaos fanatic", chaosFanaticAttacks);

        // God Wars Dungeon bosses
        // General Graardor
        Map<Integer, AttackType> graardorAttacks = new HashMap<>();
        graardorAttacks.put(7018, AttackType.MELEE); // Melee
        graardorAttacks.put(7021, AttackType.RANGE); // Range
        bossAttackMap.put("general graardor", graardorAttacks);

        // Commander Zilyana
        Map<Integer, AttackType> zilyanaAttacks = new HashMap<>();
        zilyanaAttacks.put(6967, AttackType.MELEE); // Melee
        zilyanaAttacks.put(6970, AttackType.MAGE); // Mage
        bossAttackMap.put("commander zilyana", zilyanaAttacks);

        // K'ril Tsutsaroth
        Map<Integer, AttackType> krilAttacks = new HashMap<>();
        krilAttacks.put(6945, AttackType.MELEE); // Melee
        krilAttacks.put(6947, AttackType.MAGE); // Mage
        bossAttackMap.put("k'ril tsutsaroth", krilAttacks);

        // Kree'arra
        Map<Integer, AttackType> kreeArraAttacks = new HashMap<>();
        kreeArraAttacks.put(6972, AttackType.RANGE); // Range
        kreeArraAttacks.put(6973, AttackType.MAGE); // Mage
        bossAttackMap.put("kree'arra", kreeArraAttacks);

        // The Nightmare (Phosani's Nightmare, Moon phase = Nightmare)
        Map<Integer, AttackType> nightmareAttacks = new HashMap<>();
        nightmareAttacks.put(8594, AttackType.MAGE); // Mage
        nightmareAttacks.put(8596, AttackType.RANGE); // Range
        nightmareAttacks.put(8598, AttackType.MELEE); // Melee
        bossAttackMap.put("the nightmare", nightmareAttacks);
        bossAttackMap.put("phosani's nightmare", nightmareAttacks);
    }

    public static AttackType getAttackType(String bossName, int animationId) {
        if (bossName == null) return null;
        Map<Integer, AttackType> attacks = bossAttackMap.get(bossName.toLowerCase());
        if (attacks == null) return null;
        return attacks.get(animationId);
    }
}
