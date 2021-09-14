package academy.digitallab.store.serviceshopping.controller;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import academy.digitallab.store.serviceshopping.entity.ErrorMessage;
import academy.digitallab.store.serviceshopping.entity.Invoice;
import academy.digitallab.store.serviceshopping.service.InvoiceService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/invoices")
public class InvoiceController {

	@Autowired
	private InvoiceService invoiceService;

	@GetMapping
	public ResponseEntity<List<Invoice>> listAllInvoices() {
		
		List<Invoice> invoices = invoiceService.findInvoiceAll();
		
		if (invoices.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(invoices);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Invoice> getInvoice(@PathVariable("id") long id) {
		log.info("Fetching Invoice with id {}", id);
		Invoice invoice = invoiceService.getInvoice(id);
		if (null == invoice) {
			log.error("Invoice with id {} not found.", id);
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(invoice);
	}

	@PostMapping
	public ResponseEntity<Invoice> createInvoice(@Valid @RequestBody Invoice invoice, BindingResult result) {
		
		log.info("Creando Factura : {}", invoice);
		
		if (result.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
		}
		
		Invoice invoiceDB = invoiceService.createInvoice(invoice);

		return ResponseEntity.status(HttpStatus.CREATED).body(invoiceDB);
	}

	
	@PutMapping("/{idInvoice}")
	public ResponseEntity<?> updateInvoice(@PathVariable("idInvoice") Long idInvoice, @RequestBody Invoice invoice) {
		log.info("Actualizando factura con id {}", idInvoice);

		invoice.setId(idInvoice);
		Invoice currentInvoice = invoiceService.updateInvoice(invoice);

		if (currentInvoice == null) {
			log.error("No se puede actualizar. Factura con id {} no encontrada.", idInvoice);
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(currentInvoice);
	}

	@DeleteMapping("/{idInvoice}")
	public ResponseEntity<Invoice> deleteInvoice(@PathVariable("idInvoice") long idInvoice) {
		log.info("Buscando y borrando la Factura con el id {}", idInvoice);

		Invoice invoice = invoiceService.getInvoice(idInvoice);
		if (invoice == null) {
			log.error("No se puede eliminar. Factura con el id {} no encontrada.", idInvoice);
			return ResponseEntity.notFound().build();
		}
		invoice = invoiceService.deleteInvoice(invoice);
		return ResponseEntity.ok(invoice);
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
