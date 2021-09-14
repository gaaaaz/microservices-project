package academy.digitallab.store.serviceshopping.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Positive;

import academy.digitallab.store.serviceshopping.model.Product;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_invoice_items")
public class InvoiceItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Positive(message = "El stock debe ser mayor que cero")
	private Double quantity;
	
	private Double price;

	@Column(name = "product_id")
	private Long productId;

	@Transient /* Valor que se muestra en el json de la response pero no se almacena en bd */
	private Double subTotal;
	
	/* Elemento que viene de otro microservicio por feign (product), y al igual que subtotal lleva @Transient porque
	 * esta en la response pero no se guarda en bd. */
	@Transient
	private Product product;

	public Double getSubTotal() { /* Método get de subTotal, no usando lombok */
		if (this.price > 0 && this.quantity > 0) {
			return this.quantity * this.price;
		} else {
			return (double) 0;
		}
	}

	public InvoiceItem() {
		this.quantity = (double) 0;
		this.price = (double) 0;

	}

}
