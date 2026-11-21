
package ca.hccis.wc.entity;

import java.util.ArrayList;
import java.util.List;

public class WCReportRatings {

    private int limit = 10;
    private List<Player> players = new ArrayList<>();

    public WCReportRatings() {
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
