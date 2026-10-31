/**
 * RatingsDAO
 * @author LeBlanc Joseph
 * @since 2026-10-25
 */
package ca.hccis.wc.dao;

import ca.hccis.wc.entity.Player;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WCRatingsDAO {

    private static final Logger logger =
            LoggerFactory.getLogger(WCRatingsDAO.class);

    private Connection conn = null;

    private static final double GOALS_WEIGHT = 1.0;
    private static final double ASSISTS_WEIGHT = 0.75;
    private static final double DEFENSE_WEIGHT = 0.5;
    private static final double YELLOW_CARDS_WEIGHT = 0.5;
    private static final double RED_CARDS_WEIGHT = 2.0;

    private static final double MATCH_MINUTES = 90.0;

    /**
     * Constructor.
     * Establishes a connection to the database.
     */
    public WCRatingsDAO() {

        try {

            ResourceBundle rb = ResourceBundle.getBundle("application");

            String url = rb.getString("spring.datasource.url");
            String username = rb.getString("spring.datasource.username");
            String password = rb.getString("spring.datasource.password");

            conn = DriverManager.getConnection(url, username, password);

        } catch (Exception e) {

            logger.error("Error connecting to database", e);

        }
    }

    /**
     * Selects all player ratings.
     *
     * @return ArrayList of players
     */
    public ArrayList<Player> selectAll() {

        ArrayList<Player> players = new ArrayList<>();

        if (conn == null) {
            logger.error("Database connection is not available.");
            return players;
        }

        String sql = "SELECT * FROM PlayerEffectivenessRatings";

        try {

            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            players = loadList(rs);

            rs.close();
            statement.close();

        } catch (SQLException e) {

            logger.error("Error selecting all player ratings", e);

        }

        return players;
    }

    /**
     * Selects player ratings by player name.
     *
     * @param playerName name of the player
     * @return ArrayList of matching players
     */
    public ArrayList<Player> selectAllByPlayerName(String playerName) {

        ArrayList<Player> players = new ArrayList<>();

        if (conn == null) {
            logger.error("Database connection is not available.");
            return players;
        }

        String sql = "SELECT * FROM PlayerEffectivenessRatings "
                + "WHERE playerName = ?";

        try {

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, playerName);

            ResultSet rs = ps.executeQuery();

            players = loadList(rs);

            rs.close();
            ps.close();

        } catch (SQLException e) {

            logger.error("Error selecting player ratings by name", e);

        }

        return players;
    }

    /**
     * Selects the highest-rated players.
     * Returns ArrayList of top players.
     */
    public ArrayList<Player> selectTopPlayers(int limit) {

        ArrayList<Player> players = selectAll();

        if (limit < 1) {
            limit = 1;
        } else if (limit > 50) {
            limit = 50;
        }

        players.sort(
                Comparator.comparingDouble(
                        Player::getPlayerEffectiveness
                ).reversed()
        );

        if (players.size() > limit) {
            return new ArrayList<>(players.subList(0, limit));
        }

        return players;
    }

    /**
     * Loads player records from the ResultSet.
     * Calculates offensive contribution, defensive contribution,
     * penalties, and overall player effectiveness.
     *
     */
    private ArrayList<Player> loadList(ResultSet rs) throws SQLException {

        ArrayList<Player> players = new ArrayList<>();

        while (rs.next()) {

            Player player = new Player();

            player.setId(rs.getInt("id"));
            player.setAssessmentDate(rs.getString("assessmentDate"));
            player.setCreatedDateTime(rs.getString("createdDateTime"));

            player.setPlayerName(rs.getString("playerName"));
            player.setCountry(rs.getString("country"));
            player.setPosition(rs.getString("position"));

            int minutesPlayed = rs.getInt("minutesPlayed");

            int goals = rs.getInt("goals");
            int assists = rs.getInt("assists");
            int defensiveActions = rs.getInt("defensiveActions");
            int yellowCards = rs.getInt("yellowCards");
            int redCards = rs.getInt("redCards");

            player.setMinutesPlayed(minutesPlayed);
            player.setGoals(goals);
            player.setAssists(assists);
            player.setDefensiveActions(defensiveActions);
            player.setYellowCards(yellowCards);
            player.setRedCards(redCards);

            double offensiveContribution = 0;
            double defensiveContribution = 0;
            double penalties = 0;
            double playerEffectiveness = 0;

            if (minutesPlayed > 0) {

                offensiveContribution =
                        ((goals * GOALS_WEIGHT)
                                + (assists * ASSISTS_WEIGHT))
                                / minutesPlayed * MATCH_MINUTES;

                defensiveContribution =
                        (defensiveActions * DEFENSE_WEIGHT)
                                / minutesPlayed * MATCH_MINUTES;

                penalties =
                        ((yellowCards * YELLOW_CARDS_WEIGHT)
                                + (redCards * RED_CARDS_WEIGHT))
                                / minutesPlayed * MATCH_MINUTES;

                playerEffectiveness =
                        offensiveContribution
                                + defensiveContribution
                                - penalties;
            }

            player.setOffensiveContribution(offensiveContribution);
            player.setDefensiveContribution(defensiveContribution);
            player.setPenalties(penalties);
            player.setPlayerEffectiveness(playerEffectiveness);

            players.add(player);
        }

        return players;
    }

    /**
     * Closes the database connection.
     */
    public void closeConnection() {

        try {

            if (conn != null && !conn.isClosed()) {
                conn.close();
            }

        } catch (SQLException e) {

            logger.error("Error closing database connection", e);

        }
    }
}
