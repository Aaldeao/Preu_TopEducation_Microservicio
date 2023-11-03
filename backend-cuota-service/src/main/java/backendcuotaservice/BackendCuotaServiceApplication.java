package backendcuotaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BackendCuotaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendCuotaServiceApplication.class, args);
	}

}
