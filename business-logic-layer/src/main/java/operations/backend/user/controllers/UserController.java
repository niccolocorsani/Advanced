package operations.backend.user.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import operations.backend.user.facades.UserServiceFacade;
import operations.backend.user.dto.responses.UserListItemResponse;
import operations.backend.user.dto.responses.UserResponse;
import operations.domains.operation.entity.Operation;
import operations.domains.user.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/user")
public class UserController {

    private final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserServiceFacade userServiceFacade;

    public UserController(UserServiceFacade userServiceFacade) {
        this.userServiceFacade = userServiceFacade;
    }


///CREATE in autorizathion filter

    ////READ
    ////READ by ID
    @CrossOrigin(origins = {"http://localhost:4200"})
    @ApiOperation(value = "Retrieve user", notes = "Retrieve user", response = User.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "example of User class", response = User.class),
    })
    @GetMapping(value = "/{idUser}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserResponse getUser(@PathVariable("idUser") Long idUser) {
        log.info(" method start with id: {}", idUser);
        UserResponse user = this.userServiceFacade.getUserById(idUser);
        log.info("getCallCenter method end with id: {}", idUser);
        return user;
    }

    /// UPDATE in autorizathion filter   TODO come argomento mi aspetto uno user o i suoi attributi?
    @ApiOperation(value = "update User", notes = "update User" /*,response = UserListItemResponse.class*/)
    @ApiResponses({
            @ApiResponse(code = 200, message = "TODO" /*,response = UserListItemResponse.class*/),
    })
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/putUser")
    public User putUser(@RequestBody User user) {
        log.info("insertUser method start");
        this.userServiceFacade.putUser(user);
        log.info("insertUser method end");
        return user;
    }


    /// UPDATE in autorizathion filter   TODO come argomento mi aspetto uno user o i suoi attributi?
    @ApiOperation(value = "update User", notes = "update User" /*,response = UserListItemResponse.class*/)
    @ApiResponses({
            @ApiResponse(code = 200, message = "TODO" /*,response = UserListItemResponse.class*/),
    })
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/updateUser")
    public User updateUser(@RequestBody User user) {
        log.info("insertUser method start");
        this.userServiceFacade.updateUser(user);
        log.info("insertUser method end");
        return user;
    }

    /////Delete operazioni
    @ApiOperation(value = "Delete user", notes = "Delete user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = Long.class),
    })
    @DeleteMapping("/{idUser}")
    public ResponseEntity<Void> deleteCallCenter(@PathVariable Long idUser) {
        log.info("deleteUser method start");
        this.userServiceFacade.deleteUserById(idUser);
        log.info("deleteUser method end");
        return ResponseEntity.noContent().build();
    }

    /////RETRIVE LIST
    @ApiOperation(value = "Retrieve User List", notes = "Retrieve User List", response = UserListItemResponse.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "User List", response = UserListItemResponse.class),
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserListItemResponse>> getUserList() {
        log.info("getUserList method start");
        List<UserListItemResponse> userList = this.userServiceFacade.getUserList();
        log.info("getUserList method end");
        return ResponseEntity.ok().body(userList);
    }

    /////Crea User
    @ApiOperation(value = "Create User", notes = "TODO" /*,response = UserListItemResponse.class*/)
    @ApiResponses({
            @ApiResponse(code = 200, message = "TODO" /*,response = UserListItemResponse.class*/),
    })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public User saveUser(@RequestBody User user) {
        log.info("PostUser method start");
        this.userServiceFacade.putUser(user);
        log.info("PostUser method end");
        return user;
    }
}
