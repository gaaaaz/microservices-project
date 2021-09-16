package academy.digitallab.store.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/* Lo único a realizar para habilitar Gateway como puerta única para nuestros microservicios es colocar en la clase main
 * la anotación @EnableEurekaClient y colocar la configuración correspondiente en service-gateway.yml en la carpeta
 * config-data, así como la configuración de boostrap.yml.
 * No olvidar pushear las configuraciones y reiniciar service-config y service-registry.*/
@EnableEurekaClient
@SpringBootApplication
public class ServiceGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceGatewayApplication.class, args);
	}

}
