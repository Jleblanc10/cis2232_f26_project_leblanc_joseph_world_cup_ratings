
package ca.hccis.wc.bo;

import ca.hccis.wc.dao.WCRatingsDAO;
import ca.hccis.wc.entity.Player;

import java.util.ArrayList;

/**
 * Business Object for World Cup Player Effectiveness Ratings.
 */
public class WCRatingsBO {

    /**
     * Processes the request for the highest-rated players.
     *
     * @param limit maximum number of players to return
     * @return ArrayList containing the highest-rated players
     */
    public ArrayList<Player> processSelectTopPlayers(int limit) {

        WCRatingsDAO wcRatingsDAO = new WCRatingsDAO();

        try {

            return wcRatingsDAO.selectTopPlayers(limit);

        } finally {

            wcRatingsDAO.closeConnection();

        }
    }

}
