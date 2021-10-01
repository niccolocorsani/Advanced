package operations.domains.operation.services;

import operations.domains.operation.entity.Operation;
import operations.domains.operation.repositories.OperationRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
public class OperationService {

    private static final String ENTITY_NAME = "Operation";

    private final OperationRepository operationRepository;

    @PersistenceContext // or even @Autowired //TODO implementare entityManager
    private EntityManager entityManager;

    public OperationService(OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }

    public Operation save(Operation operation) {
        return this.operationRepository.save(operation);
    }

    public void delete(Operation operation) {
        this.operationRepository.delete(operation);
    }

    public List<Operation> findAll() {
        return StreamSupport.stream(this.operationRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Operation findById(Long id) {
        return this.operationRepository.findById(id).orElseThrow(RuntimeException::new);
    }
}
