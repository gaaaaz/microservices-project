package academy.digitallab.store.serviceproduct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/* Añadir la anotación @EnableEurekaCliente para la conexión del microservicio - proyecto a EurekaServer,
 * después de añadir las lineas respectivas en el archivo de configuración (service-customer.yml en config-data).
 * Las líneas a añadir estan en el archivo bootstrap.yml del presente microservicio - proyecto. */

@EnableEurekaClient
@SpringBootApplication
public class ServiceProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceProductApplication.class, args);
	}

}
