package academy.digitallab.store.serviceshopping.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import academy.digitallab.store.serviceshopping.model.Customer;

/* No olvidar añadir la anotación @FeignClient, darle un nombre (usualmente el nombre del microservicio) y copiar
 * los métodos que quieres del Controller de los otros microservicios, junto al mismo @RequestMapping de estos. */

/* Hystrix: fallback es la clase creada para la respuesta en caso falle la llamada de shopping al microservicio customer,
 * no olvidar añadir en la anotacion de FeignClient. */
@FeignClient(name = "service-customer", fallback = CustomerHystrixFallbackFactory.class)
public interface CustomerClient {

	@GetMapping("/customers/{idCustomer}")
	public ResponseEntity<Customer> getCustomer(@PathVariable("idCustomer") Long idCustomer);
	
}

/* Esta clase imitan los métodos del controller del microservicio customer-client, así como hay otra clase con los de
 * product-client. De esta manera en este microservicio (service-shopping) podemos utilizar los métodos de los otros
 * microservicios gracias a Feign. Es necesario crear el package "client" y las clases que necesites.
 * Después de colocar la anotación en la clase main de shopping-service y crear los package y clases, ya podrás usar
 * los métodos de otros microservicios en este. */

 