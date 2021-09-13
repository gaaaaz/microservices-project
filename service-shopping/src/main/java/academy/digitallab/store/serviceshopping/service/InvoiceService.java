package academy.digitallab.store.serviceshopping.service;

import java.util.List;

import academy.digitallab.store.serviceshopping.entity.Invoice;

public interface InvoiceService {

	public List<Invoice> findInvoiceAll();

    public Invoice createInvoice(Invoice invoice);
    public Invoice updateInvoice(Invoice invoice);
    public Invoice deleteInvoice(Invoice invoice);

    public Invoice getInvoice(Long id);
	
}
