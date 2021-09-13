package academy.digitallab.store.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer /* */
@SpringBootApplication
public class ServiceConfigApplication {

	/* Proyecto para Configuración de Spring Cloud con Microservicios */
	
	public static void main(String[] args) {
		SpringApplication.run(ServiceConfigApplication.class, args);
	}

}
