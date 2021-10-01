package operations.domains.operation.repositories;

import operations.domains.operation.entity.Operation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRepository extends CrudRepository<Operation,Long> {

}
