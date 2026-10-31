package ca.hccis.wc.repositories;

import ca.hccis.wc.jpa.entity.CodeValue;
import ca.hccis.wc.jpa.entity.CodeValueId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeValueRepository extends CrudRepository<CodeValue, CodeValueId> {
}