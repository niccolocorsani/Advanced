package operations.controllers.user;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import operations.backend.user.controllers.UserController;
import operations.backend.user.facades.UserServiceFacade;
import operations.domains.user.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;


@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {


    @Autowired
    private MockMvc mvc;
    @MockBean
    private UserServiceFacade myService;


    @Test
    public void testAllEmployeesNotEmpty() throws Exception {
        User user1 = new User();
        user1.setUsername("user1");
        user1.setName("name1");
        user1.setEmail("email1");
        User user2 = new User();
        user1.setUsername("user2");
        user1.setName("name2");
        user1.setEmail("email2");
        when(myService.findAll()).
                thenReturn(asList(
                        user1, user2
                ));
        this.mvc.perform(get("/api/user/")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$[0].username", is("user1")))
                .andExpect((ResultMatcher) jsonPath("$[0].name", is("name1")))
                .andExpect((ResultMatcher) jsonPath("$[0].email", is("email1")));

    }
}
