package academy.digitallab.store.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

/* Actuator y Admin Server son útiles para el monitoreo y métricas de nuestros microservicios y aplicación en general.
 * Para utilizarlo, es necesario activar actuator en cada uno de los microservicios, en sus respectivos archivos de
 * configuración en la carpeta config-data, así como las dependencias en sus archivos pom.xml respectivos.
 * Una vez realizado esto, podremos acceder a Actuator a través de localhost:8091/actuator/ (:8092 y :8093 también).
 * 
 * Para Admin Server, crearemos un proyecto (service-admin) con la dependencia Spring Boot Admin (Server),
 * habilitaremos en la clase main con @EnableAdminServer y colocaremos las configuraciones en el application.properties.
 * También añadiremos la dependencia "spring-boot-admin-starter-client" en cada uno de los microservicios y la respectiva
 * config en cada archivo bootstrap.yml de cada microservicio.
 * Recuerda que Admin Server y los Admin Client en los microservicios deben tener la misma versión en el pom.xml*/

@EnableAdminServer
@SpringBootApplication
public class ServiceAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceAdminApplication.class, args);
	}

}
