package academy.digitallab.store.serviceshopping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/* Añadir la anotación @EnableEurekaCliente para la conexión del microservicio - proyecto a EurekaServer,
 * después de añadir las lineas respectivas en el archivo de configuración (service-customer.yml en config-data).
 * Las líneas a añadir estan en el archivo bootstrap.yml del presente microservicio - proyecto. */

@EnableEurekaClient
@SpringBootApplication

/* Colocar esta anotación para habilitar Feign y permitir que este microservicio se comunique con los microservicios
 * customer y product. Recordar que es necesario añadir los package "model" y "client" en cada uno creando las entidades
 * y interfaces correspondientes para hacer uso de los métodos de los otros microservicios.
 * Una vez realizas todo lo necesario, recuerda reiniciar el service-config (Spring Cloud) y service-registry 
 * (Eureka Server), además de los microservicios (proyectos). */

@EnableFeignClients
public class ServiceShoppingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceShoppingApplication.class, args);
	}

}
