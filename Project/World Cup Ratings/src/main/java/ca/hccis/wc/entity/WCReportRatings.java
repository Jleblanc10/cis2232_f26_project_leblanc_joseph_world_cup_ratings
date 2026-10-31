/**
 * Ratings Report
 * @author LeBlanc Joseph
 * @since 2026-10-25
 */
package ca.hccis.wc.entity;

import java.util.ArrayList;

/**
 * Holds the information required for the World Cup
 * Player Effectiveness Ratings report.
 */
public class WCReportRatings {

    private int limit = 10;

    private ArrayList<Player> players = new ArrayList<>();

    public WCReportRatings() {
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }
}
