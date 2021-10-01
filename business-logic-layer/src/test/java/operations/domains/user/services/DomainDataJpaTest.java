package operations.domains.user.services;

import operations.domains.operation.entity.Operation;
import operations.domains.user.entity.User;
import operations.domains.useroperation.services.UserOperationService;
import operations.domains.useroperation.entity.UserOperation;
import org.h2.tools.Server;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

////Integration Test
@RunWith(SpringRunner.class)
@ComponentScan(basePackages = {"operations"})
@EnableAutoConfiguration(exclude = LiquibaseAutoConfiguration.class)
@DataJpaTest
public class DomainDataJpaTest {

    @Configuration
    static class ContextConfiguration {
        @Bean(initMethod = "start", destroyMethod = "stop")
        public Server h2Server() throws SQLException {
            return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092");
        }
    }

    public DomainDataJpaTest() {
    }
    @Autowired
    UserService userService;
    @Autowired
    UserOperationService userOperationService;

    @Before
    public void setUp() throws SQLException {
        for (int i = 3; i < 6; i++) {
            long l = i;
            try {
                User user = new User();
                user.setSubject(String.valueOf(Math.random()));
                user.setUsername("username" + i);
                user.setName("name" + i);
                UserOperation userOperation = new UserOperation();
                List<UserOperation> userOperationList = new ArrayList<>();
                userOperationList.add(userOperation);
                userOperationService.save(userOperation);
                user.setUserOperation(userOperationList);Operation operation = new Operation();
                userService.save(user);
                System.err.println("Caricato user" + i);
                Thread.sleep(100);
            } catch (Exception e) {
                System.err.println("Error");
                e.printStackTrace();
            }
        }

        System.err.println("Fine caricamento dati");
    }

    @Test
    public void testQuery() throws InterruptedException {
        List<User> users = userService.findAll();
        assertEquals(users.get(0).getName(),"name3");
        assertEquals(users.get(1).getName(),"name4");
        assertEquals(users.get(2).getName(),"name5");

    }
}
