package academy.digitallab.store.serviceshopping.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import academy.digitallab.store.serviceshopping.client.CustomerClient;
import academy.digitallab.store.serviceshopping.client.ProductClient;
import academy.digitallab.store.serviceshopping.entity.Invoice;
import academy.digitallab.store.serviceshopping.entity.InvoiceItem;
import academy.digitallab.store.serviceshopping.model.Customer;
import academy.digitallab.store.serviceshopping.model.Product;
import academy.digitallab.store.serviceshopping.repository.InvoiceItemsRepository;
import academy.digitallab.store.serviceshopping.repository.InvoiceRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class InvoiceServiceImpl implements InvoiceService {

	@Autowired
	private InvoiceRepository invoiceRepository;
	
	@Autowired
	CustomerClient customerClient;
	
	@Autowired
	ProductClient productClient;
	
	@Autowired
	private InvoiceItemsRepository invoiceItemsRepository;
	
	@Override
	public List<Invoice> findInvoiceAll() {
		return invoiceRepository.findAll();
	}

	@Override
	public Invoice createInvoice(Invoice invoice) {
		
		Invoice invoiceDB = invoiceRepository.findByNumberInvoice(invoice.getNumberInvoice());
		
		if(invoiceDB != null) {
			return invoiceDB;
		}
		
		invoice.setState("CREATED");
		invoiceDB = invoiceRepository.save(invoice);
		
		/* Actualizando el stock de los items procesados en la factura (vendidos). Utiliza los métodos de
		 * ProductClient, es decir los métodos de otro microservicio gracias a Feign. */
		invoiceDB.getItems().forEach(invoiceItem -> {
			productClient.updateStock(invoiceItem.getProductId(), invoiceItem.getQuantity() * -1);
		});
		
		return invoiceDB;
	}

	@Override
	public Invoice updateInvoice(Invoice invoice) {
		
		Invoice invoiceDB = getInvoice(invoice.getId());
		
		if(invoiceDB == null) {
			return null;
		}
		
		invoiceDB.setCustomerId(invoice.getCustomerId());
        invoiceDB.setDescription(invoice.getDescription());
        invoiceDB.setNumberInvoice(invoice.getNumberInvoice());
        invoiceDB.getItems().clear();
        invoiceDB.setItems(invoice.getItems());
		
		return invoiceRepository.save(invoiceDB);
	}

	@Override
	public Invoice deleteInvoice(Invoice invoice) {
		
		Invoice invoiceDB = getInvoice(invoice.getId());
		
		if(invoiceDB == null) {
			return null;
		}
		
		invoiceDB.setState("DELETED");
		return invoiceRepository.save(invoiceDB);
	}

	@Override
	public Invoice getInvoice(Long id) {
		
		Invoice invoiceDB = invoiceRepository.findById(id).orElse(null);
		
		/* Utiliza los métodos de ProductClient y CustomerClient que hacen referencia a métodos
		 * de otros microservicios, gracias a Feign. */
		if(null != invoiceDB) {
			Customer customer = customerClient.getCustomer(invoiceDB.getCustomerId()).getBody();
			invoiceDB.setCustomer(customer);
		
			List<InvoiceItem> listItem=invoiceDB.getItems().stream().map(invoiceItem -> {
				Product product = productClient.getProduct(invoiceItem.getProductId()).getBody();
	            invoiceItem.setProduct(product);
	            return invoiceItem;
			}).collect(Collectors.toList());
			
			invoiceDB.setItems(listItem);
		}
		
		return invoiceDB;
	}

	
	
	
	
}
