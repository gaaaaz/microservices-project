package academy.digitallab.store.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/* Gateway sirve para crear una puerta de entrada única a nuestros microservicios. Es decir, acceder a ellos a través
 * de una ruta única. Para ello, en el proyecto creado, en la clase main colocaremos la anotación de Eureka, así como
 * el archivo de config "service-gateway.yml" en config-data y el bootstrap.yml correspondiente en src/main/resources.
 * El service-gateway.yml se encargará de listar todos las rutas de los microservicios que tenemos disponibles
 * para luego poder invocarlos desde una unica ruta.
 * En este caso después de implementar Gateway, podremos acceder a todos nuestros microservicios a través de
 * localhost:8080. Cabe aclarar que antes de Gateway cada microservicio se accede a través de distintas rutas,
 * como :8091, :8092, 8093... */

@EnableEurekaClient
@SpringBootApplication
public class ServiceGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceGatewayApplication.class, args);
	}

}
