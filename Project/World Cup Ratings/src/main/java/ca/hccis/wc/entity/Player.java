
package ca.hccis.wc.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

/**
 * Player Entity
 *
 * Represents a player's World Cup effectiveness rating.
 *
 * @author LeBlanc Joseph
 * @since 2026-11-20
 * Updated for sprint 3
 */
@Entity
@Table(name = "PlayerEffectivenessRatings")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Assessment date is required")
    private String assessmentDate;

    private String createdDateTime;

    @NotBlank(message = "Player name is required")
    private String playerName;

    @NotBlank(message = "Country is required")
    private String country;

    @NotBlank(message = "Position is required")
    private String position;

    @Min(value = 1, message = "Minutes played must be greater than zero")
    private int minutesPlayed;

    @Min(value = 0, message = "Goals cannot be negative")
    private int goals;

    @Min(value = 0, message = "Assists cannot be negative")
    private int assists;

    @Min(value = 0, message = "Defensive actions cannot be negative")
    private int defensiveActions;

    @Min(value = 0, message = "Yellow cards cannot be negative")
    private int yellowCards;

    @Min(value = 0, message = "Red cards cannot be negative")
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
