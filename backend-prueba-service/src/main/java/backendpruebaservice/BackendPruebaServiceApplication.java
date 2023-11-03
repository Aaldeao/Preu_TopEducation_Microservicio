package backendpruebaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BackendPruebaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendPruebaServiceApplication.class, args);
	}

}
