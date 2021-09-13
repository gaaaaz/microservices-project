package academy.digitallab.store.servicecustomer.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import academy.digitallab.store.servicecustomer.entity.Customer;
import academy.digitallab.store.servicecustomer.entity.ErrorMessage;
import academy.digitallab.store.servicecustomer.entity.Region;
import academy.digitallab.store.servicecustomer.service.CustomerService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/customers")
@Slf4j
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@GetMapping //?regionId=
	public ResponseEntity<List<Customer>> listAllCustomers(
			@RequestParam(name = "regionId", required = false) Long regionId) {
		
		List<Customer> customers = new ArrayList<>();
		
		if (null == regionId) {
			customers = customerService.findCustomerAll();
			if (customers.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
		} else {
			Region Region = new Region();
			Region.setId(regionId);
			customers = customerService.findCustomersByRegion(Region);
			if (null == customers) {
				log.error("No se encontraron clientes con el Region id {}.", regionId);
				return ResponseEntity.notFound().build();
			}
		}

		return ResponseEntity.ok(customers);
	}

	
	@GetMapping("/{idCustomer}")
	public ResponseEntity<Customer> getCustomer(@PathVariable("idCustomer") Long idCustomer) {
		
		log.info("Buscando cliente con el id {}", idCustomer);
		Customer customer = customerService.getCustomer(idCustomer);
		
		if (null == customer) {
			log.error("Cliente con el id {} no encontrado.", idCustomer);
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(customer);
	}

	
	@PostMapping
	public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer, BindingResult result) {
		
		log.info("Creando cliente : {}", customer);
		if (result.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
		}

		Customer customerDB = customerService.createCustomer(customer);

		return ResponseEntity.status(HttpStatus.CREATED).body(customerDB);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateCustomer(@PathVariable("id") long id, @RequestBody Customer customer) {
		
		log.info("Actualizando cliente con el id {}", id);

		Customer currentCustomer = customerService.getCustomer(id);

		if (null == currentCustomer) {
			log.error("No se puede actualizar. Cliente con el id {} no encontrado.", id);
			return ResponseEntity.notFound().build();
		}
		
		customer.setId(id);
		currentCustomer = customerService.updateCustomer(customer);
		
		return ResponseEntity.ok(currentCustomer);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Customer> deleteCustomer(@PathVariable("id") Long id) {
		
		log.info("Encontrando y borrando el cliente con el id {}", id);

		Customer customer = customerService.getCustomer(id);
		if (null == customer) {
			log.error("No se puede eliminar. Cliente con el id {} no encontrado.", id);
			return ResponseEntity.notFound().build();
		}
		
		customer = customerService.deleteCustomer(customer);
		
		return ResponseEntity.ok(customer);
	}

	private String formatMessage(BindingResult result) {
		
		List<Map<String, String>> errors = result.getFieldErrors().stream().map(err -> {
			Map<String, String> error = new HashMap<>();
			error.put(err.getField(), err.getDefaultMessage());
			return error;

		}).collect(Collectors.toList());
		
		ErrorMessage errorMessage = ErrorMessage.builder().code("01").messages(errors).build();
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = "";
		try {
			jsonString = mapper.writeValueAsString(errorMessage);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonString;
	}

}
