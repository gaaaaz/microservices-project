package academy.digitallab.store.serviceshopping.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import academy.digitallab.store.serviceshopping.model.Customer;

@Component
public class CustomerHystrixFallbackFactory implements CustomerClient{

	/* No olvidar crear esta clase que implementa su respectivo Client y añadir las propiedades necesarias en el .yml
	 * Reiniciar service-config (Spring Cloud) y service-registry (Eureka), así como todos los microservicios.
	 * Si el servicio de Customer se cayera y se ejecuta el de shopping, hystrix con el fallback nos permitira continuar
	 * con el servicio, devolviendo un Customer con datos vacíos y el resto de resultados correctos. Con esto se evita
	 * tumbarse todo el microservicio si una de sus dependencias falla. 
	 * Dashboard: localhost:8093/hystrix
	 * Ruta para revision: localhost:8093/actuator/hystrix.stream */
	@Override
	public ResponseEntity<Customer> getCustomer(Long idCustomer) {
		
		Customer customer = Customer.builder()
				.firstName("none")
				.lastName("none")
				.email("none")
				.photoUrl("none").build();
		
		return ResponseEntity.ok(customer);
	}

	
	
}
