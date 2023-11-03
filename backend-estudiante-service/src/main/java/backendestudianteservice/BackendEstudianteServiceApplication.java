package backendestudianteservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BackendEstudianteServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendEstudianteServiceApplication.class, args);
	}

}
