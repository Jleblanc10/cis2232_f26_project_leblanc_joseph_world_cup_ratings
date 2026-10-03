package ca.hccis.wc.bo;

import ca.hccis.wc.entity.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EffectivenessBOTest {

    /**
     * Test 1 created by Joseph LeBlanc
     *
     * @author Joseph LeBlanc
     * @since 20261002
     */

    //Simple assert equals test

    @Test
    void testDetermineEffectiveness1() {
        Player player = new Player(
                1,
                "Bob",
                "Canada",
                "forward",
                900,
                10,
                5,
                20,
                2,
                0
        );

        EffectivenessBO effectivenessBO = new EffectivenessBO();

        double actual = effectivenessBO.getPlayerEffectiveness(
                player.getMinutesPlayed(),
                player.getGoals(),
                player.getAssists(),
                player.getDefensiveActions(),
                player.getYellowCards(),
                player.getRedCards()
        );

        Assertions.assertEquals(2.275, actual, 0.001);
    }

    // Simple assert greater than test.

    @Test
    void testDetermineEffectiveness2() {
        Player player = new Player(
                1,
                "Hanna",
                "Canada",
                "Defense",
                90,
                5,
                3,
                10,
                1,
                0
        );

        EffectivenessBO effectivenessBO = new EffectivenessBO();

        double actual = effectivenessBO.getPlayerEffectiveness(
                player.getMinutesPlayed(),
                player.getGoals(),
                player.getAssists(),
                player.getDefensiveActions(),
                player.getYellowCards(),
                player.getRedCards()
        );

        Assertions.assertEquals(11.75, actual, 0.001);
        Assertions.assertTrue(actual > 0);
    }

    // simple negative assert equals test.

    @Test
    void testDetermineEffectiveness3() {
        Player player = new Player(
                1,
                "Felix",
                "Canada",
                "Defense",
                90,
                0,
                0,
                0,
                2,
                1
        );

        EffectivenessBO effectivenessBO = new EffectivenessBO();

        double actual = effectivenessBO.getPlayerEffectiveness(
                player.getMinutesPlayed(),
                player.getGoals(),
                player.getAssists(),
                player.getDefensiveActions(),
                player.getYellowCards(),
                player.getRedCards()
        );

        Assertions.assertEquals(-3.0, actual, 0.001);
    }

    //  10 additional tests, AI generated, via google search AI.

    // Test 3 (Completed from your snippet): Extreme penalties, zero contributions
    @Test
    void testDetermineEffectiveness4() {
        Player player = new Player(1, "Felix", "Canada", "Defense", 90, 0, 0, 0, 2, 1);
        EffectivenessBO effectivenessBO = new EffectivenessBO();

        double actual = effectivenessBO.getPlayerEffectiveness(
                player.getMinutesPlayed(), player.getGoals(), player.getAssists(),
                player.getDefensiveActions(), player.getYellowCards(), player.getRedCards()
        );

        // Math: 0 + 0 - (((2 * 0.5) + (1 * 2.0)) / 90 * 90) = -3.0
        Assertions.assertEquals(-3.0, actual, 0.001);
    }

    // Test 4: Heavy penalties with minimal playtime (Amplified negative score)
    @Test
    void testDetermineEffectiveness5_SevereNegativeScore() {
        Player player = new Player(4, "Trouble", "Canada", "forward", 10, 0, 0, 0, 2, 1);
        EffectivenessBO effectivenessBO = new EffectivenessBO();

        double actual = effectivenessBO.getPlayerEffectiveness(
                player.getMinutesPlayed(), player.getGoals(), player.getAssists(),
                player.getDefensiveActions(), player.getYellowCards(), player.getRedCards()
        );

        // Math: 0 + 0 - ((1.0 + 2.0) / 10 * 90) = -27.0
        Assertions.assertEquals(-27.0, actual, 0.001);
    }

    // Test 5: Standard forward player with average mixed stats
    @Test
    void testDetermineEffectiveness6_AverageForward() {
        Player player = new Player(5, "Striker", "Canada", "forward", 450, 4, 2, 5, 1, 0);
        EffectivenessBO effectivenessBO = new EffectivenessBO();

        double actual = effectivenessBO.getPlayerEffectiveness(
                player.getMinutesPlayed(), player.getGoals(), player.getAssists(),
                player.getDefensiveActions(), player.getYellowCards(), player.getRedCards()
        );

        // Offense: ((4*1) + (2*0.75)) / 450 * 90 = 1.1
        // Defense: (5*0.5) / 450 * 90 = 0.5
        // Penalty: (1*0.5) / 450 * 90 = 0.1
        // Total: 1.1 + 0.5 - 0.1 = 1.5
        Assertions.assertEquals(1.5, actual, 0.001);
    }

    // Test 6: Zero active game metrics boundary
    @Test
    void testDetermineEffectiveness7_ZeroStats() {
        Player player = new Player(6, "Ghost", "Canada", "midfielder", 90, 0, 0, 0, 0, 0);
        EffectivenessBO effectivenessBO = new EffectivenessBO();

        double actual = effectivenessBO.getPlayerEffectiveness(
                player.getMinutesPlayed(), player.getGoals(), player.getAssists(),
                player.getDefensiveActions(), player.getYellowCards(), player.getRedCards()
        );

        Assertions.assertEquals(0.0, actual, 0.001);
    }

    // Test 7: Division by zero boundary logic fallback (0 minutes played)
    @Test
    void testDetermineEffectiveness8_ZeroMinutes() {
        Player player = new Player(7, "Bench", "Canada", "forward", 0, 10, 5, 20, 1, 1);
        EffectivenessBO effectivenessBO = new EffectivenessBO();

        double actual = effectivenessBO.getPlayerEffectiveness(
                player.getMinutesPlayed(), player.getGoals(), player.getAssists(),
                player.getDefensiveActions(), player.getYellowCards(), player.getRedCards()
        );

        // Checks if minutesPlayed == 0 correctly returns 0 early before performing division
        Assertions.assertEquals(0.0, actual, 0.001);
    }

    // Test 8: Super sub player with extreme efficiency in very low minutes
    @Test
    void testDetermineEffectiveness9_SuperSub() {
        Player player = new Player(8, "Sub", "Canada", "forward", 15, 2, 1, 1, 0, 0);
        EffectivenessBO effectivenessBO = new EffectivenessBO();

        double actual = effectivenessBO.getPlayerEffectiveness(
                player.getMinutesPlayed(), player.getGoals(), player.getAssists(),
                player.getDefensiveActions(), player.getYellowCards(), player.getRedCards()
        );

        // Offense: ((2*1) + (1*0.75)) / 15 * 90 = 16.5
        // Defense: (1*0.5) / 15 * 90 = 3.0
        // Penalty: 0
        // Total: 16.5 + 3.0 = 19.5
        Assertions.assertEquals(19.5, actual, 0.001);
    }

    // Test 9: Clean sheet defensive wall with zero offensive metrics
    @Test
    void testDetermineEffectiveness10_DefensiveWall() {
        Player player = new Player(9, "Wall", "Canada", "Defense", 180, 0, 0, 35, 0, 0);
        EffectivenessBO effectivenessBO = new EffectivenessBO();

        double actual = effectivenessBO.getPlayerEffectiveness(
                player.getMinutesPlayed(), player.getGoals(), player.getAssists(),
                player.getDefensiveActions(), player.getYellowCards(), player.getRedCards()
        );

        // Defense: (35 * 0.5) / 180 * 90 = 8.75
        Assertions.assertEquals(8.75, actual, 0.001);
    }

    // Test 10: Multi-game high endurance scaling
    @Test
    void testDetermineEffectiveness11_LargeMinutes() {
        Player player = new Player(10, "IronMan", "Canada", "midfielder", 3000, 15, 12, 80, 4, 0);
        EffectivenessBO effectivenessBO = new EffectivenessBO();

        double actual = effectivenessBO.getPlayerEffectiveness(
                player.getMinutesPlayed(), player.getGoals(), player.getAssists(),
                player.getDefensiveActions(), player.getYellowCards(), player.getRedCards()
        );

        // Offense: (15 + 9) / 3000 * 90 = 0.72
        // Defense: 40 / 3000 * 90 = 1.2
        // Penalty: 2 / 3000 * 90 = 0.06
        // Total: 0.72 + 1.2 - 0.06 = 1.86
        Assertions.assertEquals(1.86, actual, 0.001);
    }

    // Test 11: High penalty metric with high scoring offsetting values
    @Test
    void testDetermineEffectiveness12_AggressiveScorer() {
        Player player = new Player(11, "WildCard", "Canada", "forward", 90, 3, 0, 2, 2, 1);
        EffectivenessBO effectivenessBO = new EffectivenessBO();

        double actual = effectivenessBO.getPlayerEffectiveness(
                player.getMinutesPlayed(), player.getGoals(), player.getAssists(),
                player.getDefensiveActions(), player.getYellowCards(), player.getRedCards()
        );

        // Offense: 3 / 90 * 90 = 3.0
        // Defense: 1 / 90 * 90 = 1.0
        // Penalty: (1 + 2) / 90 * 90 = 3.0
        // Total: 3.0 + 1.0 - 3.0 = 1.0
        Assertions.assertEquals(1.0, actual, 0.001);
    }

    // Test 12: Playmaker emphasis (High assist ratios vs zero scoring)
    @Test
    void testDetermineEffectiveness13_PurePlaymaker() {
        Player player = new Player(12, "Maestro", "Canada", "midfielder", 540, 0, 12, 10, 0, 0);
        EffectivenessBO effectivenessBO = new EffectivenessBO();

        double actual = effectivenessBO.getPlayerEffectiveness(
                player.getMinutesPlayed(), player.getGoals(), player.getAssists(),
                player.getDefensiveActions(), player.getYellowCards(), player.getRedCards()
        );

        // Offense: (12 * 0.75) / 540 * 90 = 1.5
        // Defense: (10 * 0.5) / 540 * 90 = 0.8333...
        // Total: 1.5 + 0.8333333333333333 = 2.3333...
        Assertions.assertEquals(2.333, actual, 0.001);
    }
}