package academy.digitallab.store.serviceshopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import academy.digitallab.store.serviceshopping.entity.InvoiceItem;

public interface InvoiceItemsRepository extends JpaRepository<InvoiceItem, Long> {
}
