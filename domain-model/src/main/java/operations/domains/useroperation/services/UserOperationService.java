package operations.domains.useroperation.services;

import operations.domains.useroperation.entity.UserOperation;
import operations.domains.useroperation.repositories.UserOperationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
public class UserOperationService {

    private static final String ENTITY_NAME = "UserOperationPermission";

    private final UserOperationRepository userOperationRepository;

    public UserOperationService(UserOperationRepository userOperationRepository) {
        this.userOperationRepository = userOperationRepository;
    }

    public UserOperation save(UserOperation userOperation) {
        return this.userOperationRepository.save(userOperation);
    }


    public void delete(UserOperation userOperation) {
        this.userOperationRepository.delete(userOperation);
    }


    public List<UserOperation> findAll() {
        return StreamSupport.stream(this.userOperationRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public UserOperation findById(Long id) {
        return this.userOperationRepository.findById(id).orElseThrow(RuntimeException::new);
    }



}
