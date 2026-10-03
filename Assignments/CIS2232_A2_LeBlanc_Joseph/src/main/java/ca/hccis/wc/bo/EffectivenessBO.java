package ca.hccis.wc.bo;

public class EffectivenessBO {

    private static final double GOALS_WEIGHT = 1.0;
    private static final double ASSISTS_WEIGHT = 0.75;
    private static final double DEFENSE_WEIGHT = 0.5;
    private static final double YELLOW_CARDS_WEIGHT = 0.5;
    private static final double RED_CARDS_WEIGHT = 2.0;

    public double getOffensiveContribution(int minutesPlayed, int goals, int assists) {

        if (minutesPlayed == 0) {
            return 0;
        }

        return ((goals * GOALS_WEIGHT)
                + (assists * ASSISTS_WEIGHT))
                / minutesPlayed * 90;
    }

    public double getDefensiveContribution(int minutesPlayed, int defensiveActions) {

        if (minutesPlayed == 0) {
            return 0;
        }

        return (defensiveActions * DEFENSE_WEIGHT)
                / minutesPlayed * 90;
    }

    public double getPenalties(int minutesPlayed, int yellowCards, int redCards) {

        if (minutesPlayed == 0) {
            return 0;
        }

        return ((yellowCards * YELLOW_CARDS_WEIGHT)
                + (redCards * RED_CARDS_WEIGHT))
                / minutesPlayed * 90;
    }

    public double getPlayerEffectiveness(int minutesPlayed, int goals, int assists,
                                         int defensiveActions, int yellowCards, int redCards) {

        double offense = getOffensiveContribution(minutesPlayed, goals, assists);
        double defense = getDefensiveContribution(minutesPlayed, defensiveActions);
        double penalty = getPenalties(minutesPlayed, yellowCards, redCards);

        return offense + defense - penalty;
    }


}

