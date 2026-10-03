package ca.hccis.wc.entity;

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
                + "\nRed Cards: " + redCards;
    }
}