package academy.digitallab.store.serviceshopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import academy.digitallab.store.serviceshopping.entity.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

	public List<Invoice> findByCustomerId(Long customerId);
	public Invoice findByNumberInvoice(String numberInvoice);

}