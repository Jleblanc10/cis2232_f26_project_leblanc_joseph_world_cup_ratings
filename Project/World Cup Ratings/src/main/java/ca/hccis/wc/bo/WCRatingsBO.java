package ca.hccis.wc.bo;

import ca.hccis.wc.entity.Player;
import ca.hccis.wc.repositories.WCRatingsRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class WCRatingsBO {

    private final WCRatingsRepository repository;

    private static final double GOALS_WEIGHT = 1.0;
    private static final double ASSISTS_WEIGHT = 0.75;
    private static final double DEFENSE_WEIGHT = 0.5;
    private static final double YELLOW_CARDS_WEIGHT = 0.5;
    private static final double RED_CARDS_WEIGHT = 2.0;
    private static final double MATCH_MINUTES = 90.0;

    public WCRatingsBO(WCRatingsRepository repository) {
        this.repository = repository;
    }

    /**
     * Returns all player ratings.
     */
    public List<Player> processSelectAll() {
        List<Player> players = new ArrayList<>();
        repository.findAll().forEach(players::add);
        return players;
    }

    /**
     * Returns the highest-rated players.
     */
    public List<Player> processSelectTopPlayers(int limit) {

        List<Player> players = processSelectAll();

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
     * Calculates player effectiveness.
     */
    public void calculatePlayerEffectiveness(Player player) {

        int minutesPlayed = player.getMinutesPlayed();

        double offensiveContribution = 0;
        double defensiveContribution = 0;
        double penalties = 0;
        double playerEffectiveness = 0;

        if (minutesPlayed > 0) {

            offensiveContribution =
                    ((player.getGoals() * GOALS_WEIGHT)
                            + (player.getAssists() * ASSISTS_WEIGHT))
                            / minutesPlayed * MATCH_MINUTES;

            defensiveContribution =
                    (player.getDefensiveActions() * DEFENSE_WEIGHT)
                            / minutesPlayed * MATCH_MINUTES;

            penalties =
                    ((player.getYellowCards() * YELLOW_CARDS_WEIGHT)
                            + (player.getRedCards() * RED_CARDS_WEIGHT))
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
    }

    /**
     * Saves a player rating.
     */
    public Player processSave(Player player) {

        calculatePlayerEffectiveness(player);

        return repository.save(player);
    }

    /**
     * Finds a player rating by ID.
     */
    public Player processFindById(int id) {
        return repository.findById(id).orElse(null);
    }

    /**
     * Deletes a player rating.
     */
    public void processDelete(int id) {
        repository.deleteById(id);
    }
}