package ca.hccis.io.entity;

import com.google.gson.Gson;

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

    public Player(int id, String playerName, String country, String position,
                  int minutesPlayed, int goals, int assists,
                  int defensiveActions, int yellowCards, int redCards) {

        this.id = id;
        this.playerName = playerName;
        this.country = country;
        this.position = position;
        this.minutesPlayed = minutesPlayed;
        this.goals = goals;
        this.assists = assists;
        this.defensiveActions = defensiveActions;
        this.yellowCards = yellowCards;
        this.redCards = redCards;

        this.playerEffectiveness = getPlayerEffectiveness();
    }

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

    // Getters and Setters.

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getMinutesPlayed() {
        return minutesPlayed;
    }

    public void setMinutesPlayed(int minutesPlayed) {
        this.minutesPlayed = minutesPlayed;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getDefensiveActions() {
        return defensiveActions;
    }

    public void setDefensiveActions(int defensiveActions) {
        this.defensiveActions = defensiveActions;
    }

    public int getYellowCards() {
        return yellowCards;
    }

    public void setYellowCards(int yellowCards) {
        this.yellowCards = yellowCards;
    }

    public int getRedCards() {
        return redCards;
    }

    public void setRedCards(int redCards) {
        this.redCards = redCards;
    }

    public double getStoredPlayerEffectiveness() {
        return playerEffectiveness;
    }

    public void setPlayerEffectiveness(double playerEffectiveness) {
        this.playerEffectiveness = playerEffectiveness;
    }


    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    // Player input toString.

    @Override
    public String toString() {

        return "ID: " + id
                + "\nPlayer Name: " + playerName
                + "\nCountry: " + country
                + "\nPosition: " + position
                + "\nMinutes Played: " + minutesPlayed
                + "\nGoals: " + goals
                + "\nAssists: " + assists
                + "\nDefensive Actions: " + defensiveActions
                + "\nYellow Cards: " + yellowCards
                + "\nRed Cards: " + redCards
                + "\nPlayer Effectiveness: " + playerEffectiveness;
    }
}