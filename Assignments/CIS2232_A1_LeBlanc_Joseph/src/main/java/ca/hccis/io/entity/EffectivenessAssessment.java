package ca.hccis.io.entity;

public class EffectivenessAssessment {

    public class Player {

        private int id;
        private String playerName;
        private String country;
        private String position;
        private int minutesPlayed;
        private int goals;
        private int assists;
        private int defensiveActions;
        private int yellowCards;
        private int redCards;
        private double playerEffectiveness;

        private static final double GOALS_WEIGHT = 1.0;
        private static final double ASSISTS_WEIGHT = 0.75;
        private static final double DEFENSE_WEIGHT = 0.5;
        private static final double YELLOW_CARDS_WEIGHT = 0.5;
        private static final double RED_CARDS_WEIGHT = 2.0;

        public double getOffensiveContribution() {
            if (minutesPlayed == 0) {
                return 0;
            }

            return ((goals * GOALS_WEIGHT)
                    + (assists * ASSISTS_WEIGHT))
                    / minutesPlayed * 90;
        }

        public double getDefensiveContribution() {
            if (minutesPlayed == 0) {
                return 0;
            }

            return (defensiveActions * DEFENSE_WEIGHT)
                    / minutesPlayed * 90;
        }

        public double getPenalties() {
            if (minutesPlayed == 0) {
                return 0;
            }

            return ((yellowCards * YELLOW_CARDS_WEIGHT)
                    + (redCards * RED_CARDS_WEIGHT))
                    / minutesPlayed * 90;
        }

        public double getPlayerEffectiveness() {
            double offense = getOffensiveContribution();
            double defense = getDefensiveContribution();
            double penalty = getPenalties();

            return offense + defense - penalty;
        }
    }
}