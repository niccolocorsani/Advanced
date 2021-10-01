package operations.backend.operation.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import operations.backend.operation.facades.OperationServiceFacade;
import operations.domains.operation.entity.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/operation")
public class OperationController {


    private final Logger log = LoggerFactory.getLogger(OperationController.class);
    private final OperationServiceFacade operationServiceFacade;

    public OperationController(OperationServiceFacade operationServiceFacade) {
        this.operationServiceFacade = operationServiceFacade;
    }



    /////Crea Operazione
    @ApiOperation(value = "Create Operation", notes = "TODO" /*,response = UserListItemResponse.class*/)
    @ApiResponses({
            @ApiResponse(code = 200, message = "TODO" /*,response = UserListItemResponse.class*/),
    })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Operation saveOperation(@RequestBody Operation operation) {
        log.info("createOperation method start" + operation.getDescription());
        this.operationServiceFacade.saveOperation(operation);
        log.info("createOperation method end" + operation.getDescription());
        return operation;
    }

    ////Read by ID
    @ApiOperation(value = "Retrieve operation", notes = "Retrieve operation", response = Operation.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "operation retrieved", response = Operation.class),
    })
    @GetMapping(value = "/{idOperation}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Operation getOperation(@PathVariable("idOperation") Long idOperation) {
        log.info("getCallCenter method start with id: {}", idOperation);

        Operation operation = this.operationServiceFacade.findById(idOperation);

        log.info("getCallCenter method end with id: {}", idOperation);

        return operation;
    }


    /////Update operazione già esistente
    @ApiOperation(value = "TODO", notes = "TODO" /*j ,response = UserListItemResponse.class*/)
    @ApiResponses({
            @ApiResponse(code = 200, message = "TODO" /*,response = UserListItemResponse.class*/),
    })
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/updateOperation")
    public Operation updateOperation(@RequestBody Operation operation) {
        log.info("insertOperation method start");
        this.operationServiceFacade.updateOperation(operation.getId(), operation.getCode(), operation.getDescription());
        log.info("insertOperation method end");
        return operation;
    }


    /////Delete operazioni
    @ApiOperation(value = "Delete operation", notes = "Delete operation")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = Long.class),
    })
    @DeleteMapping("/{idOperation}")
    public ResponseEntity<Void> deleteCallCenter(@PathVariable Long idOperation) {
        log.info("deleteOperation method start");
        this.operationServiceFacade.deleteOperation(idOperation);
        log.info("deleteOperation method end");
        return ResponseEntity.noContent().build();
    }


    /////Ritorna lista
    @ApiOperation(value = "Retrieve Operation List", notes = "Retrieve Operation List", response = Operation.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "User List", response = Operation.class),
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Operation> getOperationList() {
        log.info("getOperationList method start");
        List<Operation> operationList = this.operationServiceFacade.getOperationList();
        log.info("getOperationList method end");
        return operationList;
    }

}
