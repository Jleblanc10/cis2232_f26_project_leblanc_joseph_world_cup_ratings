package ca.hccis.wc.repositories;

import ca.hccis.wc.entity.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// Handles database operations through Spring Data JPA

@Repository
public interface WCRatingsRepository extends CrudRepository<Player, Integer> {
}