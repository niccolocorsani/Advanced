package operations.domains.user.services;

import operations.domains.user.entity.User;
import operations.domains.user.repositories.UserRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static java.util.Arrays.asList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

//@RunWith(MockitoJUnitRunner.class) // JUnit 4
@ExtendWith(MockitoExtension.class) //for JUnit 5
class UserServiceTest {

    private User user1;
    private User user2;
    private User user3;

    /*@DataJpaTest can be used if you want to test JPA applications.
     By default it will configure an in-memory embedded database, scan for @Entity classes and configure Spring Data JPA repositories
     . Regular @Component beans will not be loaded into the ApplicationContext.*/

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        this.user1 = new User();
        this.user1.setId(1L);
        this.user1.setUsername("user1");
        this.user1.setName("name1");
        this.user1.setSubject("subject1");
        this.user2 = new User();
        this.user2.setId(2L);
        this.user2.setUsername("user2");
        this.user2.setName("name2");
        this.user2.setEmail("email2");
        this.user2.setSubject("subject2");
        this.user3 = new User();
        this.user3.setId(3L);
        this.user3.setUsername("user3");
        this.user3.setName("name3");
        this.user3.setEmail("email3");
        this.user3.setSubject("subject3");

    }

    @Test
    void save() {
        when(this.userRepository.save(any(User.class)))
                .thenReturn(this.user1);
        User result = this.userService.save(this.user1);
        assertThat(result).isSameAs(this.user1);
    }

    @Test
    void findBySubject() {
        when(this.userRepository.findBySubject("subject1")).
                thenReturn(Optional.of(this.user1));
        assertThat(this.userService.findBySubject("subject1"))
                .isSameAs(this.user1);
    }

    @Test
    void findAllTest() {
        when(this.userRepository.findAll()).
                thenReturn(asList(this.user1, this.user2));
        //// Qui è necessario AsserThat e non Assert altrimenti va in errore
        assertThat(this.userService.findAll()).
                contains(this.user1, this.user2);
        assertThat(this.userService.findAll()).doesNotContain(this.user3);
    }

    @Test
    void findById() {
        when(this.userRepository.findById(1L)).
                thenReturn(Optional.of(this.user1));
        assertThat(this.userService.findById(1L))
                .isSameAs(this.user1);
    }
}
