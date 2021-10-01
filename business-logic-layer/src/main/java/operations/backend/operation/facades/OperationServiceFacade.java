package operations.backend.operation.facades;

import operations.domains.operation.entity.Operation;
import org.springframework.stereotype.Component;
import operations.domains.operation.services.OperationService;

import java.util.List;

@Component
public class OperationServiceFacade {


    private final OperationService operationService;

    public OperationServiceFacade(OperationService operationService) {
        this.operationService = operationService;
    }

    public Operation getOperationById(Long id) {
        Operation operation = this.operationService.findById(id);
        return operation;
    }

    public Operation saveOperation(Operation operation) {
        this.operationService.save(operation);
        return operation;
    }

    public void updateOperation(Long id, String code, String description) {
        Operation operation = this.operationService.findById(id);
        operation.setCode(code);
        operation.setDescription(description);
        this.operationService.save(operation);
    }


    public Operation findById(Long idOperation) {
        Operation operation = this.operationService.findById(idOperation);
        return operation;
    }

    public void deleteOperation(Long idOperation) {
        Operation operation = this.operationService.findById(idOperation);
        this.operationService.delete(operation);
    }

    public List<Operation> getOperationList() {
        return this.operationService.findAll();
    }
}
