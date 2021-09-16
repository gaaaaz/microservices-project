package academy.digitallab.store.serviceshopping.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import academy.digitallab.store.serviceshopping.model.Customer;

@Component
public class CustomerHystrixFallbackFactory implements CustomerClient{

	/* Clase que envia un Customer con datos vacíos si la llamada de shopping a customer falla. */
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
