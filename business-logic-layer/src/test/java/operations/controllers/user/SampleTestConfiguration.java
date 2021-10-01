package operations.controllers.user;

import operations.backend.OperationBackEnd;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class SampleTestConfiguration {

///// questa classe risulta necessaria dal momento che nel UserControllerTest verrà cercata una classe configurazione, se viene specificata attraverso contextConfiguration() caricherà tutti i bean e non sarà più test di unita

}
