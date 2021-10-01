package operations.backend;

import operations.domains.operation.entity.Operation;
import operations.domains.user.repositories.UserRepository;
import operations.domains.user.entity.User;
import operations.domains.useroperation.entity.UserOperation;
import operations.domains.useroperation.repositories.UserOperationRepository;
import org.h2.tools.Server;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
@EntityScan(basePackages = {"operations"})
@ComponentScan(basePackages = {"operations"})//// senza specificare component scan qui, non trova i Bean Automatizzati: @Service
@EnableJpaRepositories(basePackages = {"operations"})
public class OperationBackEnd {


    

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(OperationBackEnd.class, args);
    }


    @Bean
    public CommandLineRunner testApp(UserRepository userRepository, UserOperationRepository userOperationRepository) {
        return args -> {
            this.loadUseres(userRepository);

        };
    }


    public void loadUseres(UserRepository userRepository) {
        for (int i = 3; i < 7; i++) {
            long l = i;
            try {
                User user = new User();
                user.setSubject(String.valueOf(Math.random()));
                System.err.println(user.getSubject());
                user.setUsername("username" + i);
                user.setName("name" + i);
                UserOperation userOperation = new UserOperation();
                List<UserOperation> userOperationList = new ArrayList<>();
                userOperationList.add(userOperation);
                Operation operation = new Operation();
                userOperation.setOperation(operation);
                userRepository.save(user);
                System.err.println("Caricato user" + i);
            } catch (Exception e) {
                System.err.println("Error");
                e.printStackTrace();
            }
        }


    }
//per connettersi al DB H2 esternamente è necesseario questo Bean
 /*   @Bean(initMethod = "start", destroyMethod = "stop")
    public Server h2Server() throws SQLException {
        return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092");
    }*/
}

