package academy.digitallab.store.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/* Este proyecto es para el funcionamiento de EUREKA SERVER.
 * Sirve para qué cuando arranquen los microservicios se registren en un servicio centralizado, 
 * para que después cuando los clientes deseen acceder a ellos puedan hacerlo a través
 * este proyecto. No olvidar añadir la anotación @EnableEurekaServer. */

@EnableEurekaServer
@SpringBootApplication
public class ServiceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceRegistryApplication.class, args);
	}

}
