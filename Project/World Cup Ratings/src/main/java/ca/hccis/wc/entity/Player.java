
package ca.hccis.wc.entity;

/**
 * Player Entity
 * @author LeBlanc Joseph
 * @since 2026-10-24
 */
public class Player {

    private int id;
    private String assessmentDate;
    private String createdDateTime;

    private String playerName;
    private String country;
    private String position;

    private int minutesPlayed;
    private int goals;
    private int assists;
    private int defensiveActions;
    private int yellowCards;
    private int redCards;

    private double offensiveContribution;
    private double defensiveContribution;
    private double penalties;
    private double playerEffectiveness;

    /**
     * Default constructor.
     */
    public Player() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAssessmentDate() {
        return assessmentDate;
    }

    public void setAssessmentDate(String assessmentDate) {
        this.assessmentDate = assessmentDate;
    }

    public String getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(String createdDateTime) {
        this.createdDateTime = createdDateTime;
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

    public double getOffensiveContribution() {
        return offensiveContribution;
    }

    public void setOffensiveContribution(double offensiveContribution) {
        this.offensiveContribution = offensiveContribution;
    }

    public double getDefensiveContribution() {
        return defensiveContribution;
    }

    public void setDefensiveContribution(double defensiveContribution) {
        this.defensiveContribution = defensiveContribution;
    }

    public double getPenalties() {
        return penalties;
    }

    public void setPenalties(double penalties) {
        this.penalties = penalties;
    }

    public double getPlayerEffectiveness() {
        return playerEffectiveness;
    }

    public void setPlayerEffectiveness(double playerEffectiveness) {
        this.playerEffectiveness = playerEffectiveness;
    }
}
