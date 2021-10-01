package operations.domains.user.services;

import operations.domains.user.entity.User;
import operations.domains.user.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
public class UserService {

    private static final String ENTITY_NAME = "User";
    /////TODO Qui c era un final: controllare
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User save(User user) {
        return this.userRepository.save(user);
    }

    public void delete(User user) {
        this.userRepository.delete(user);

    }

//    public User findActiveBySubject(String subject) {
//        return this.userRepository.findActiveBySubject(subject).orElse(null);
//    }

    public User findBySubject(String subject) {
        return this.userRepository.findBySubject(subject).orElseThrow(RuntimeException::new);
    }

    public List<User> findAll() {
        return StreamSupport.stream(this.userRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }


    public User findById(Long id) {
        return this.userRepository.findById(id).orElseThrow(RuntimeException::new);
    }
}
