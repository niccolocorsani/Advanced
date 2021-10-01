package operations.backend.user.facades;

import operations.backend.user.dto.responses.UserListItemResponse;
import operations.backend.user.dto.responses.UserResponse;
import operations.domains.user.entity.User;
import operations.domains.user.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class UserServiceFacade {

    private final UserService userService;
    private final ModelMapper dtoConverter;

    public UserServiceFacade(UserService userService, ModelMapper dtoConverter) {
        this.userService = userService;
        this.dtoConverter = dtoConverter;
    }

    public List<UserListItemResponse> getUserList() {
        List<User> userList = this.userService.findAll();

        return userList.stream()
                .map(user -> dtoConverter.map(user, UserListItemResponse.class))
                .collect(Collectors.toList());
    }

    public UserResponse getUserById(Long id) {
        User user = this.userService.findById(id);
        return dtoConverter.map(user, UserResponse.class);
    }

    public void deleteUserById(Long id) {
        User user = this.userService.findById(id);
        this.userService.delete(user);
    }


    public void updateUser(User user) {

    }

    public void putUser(User user) {
        this.userService.save(user);

    }

    public List<User> findAll() {
        return null;
    }
}
