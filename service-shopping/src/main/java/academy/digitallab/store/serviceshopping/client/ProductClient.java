package academy.digitallab.store.serviceshopping.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import academy.digitallab.store.serviceshopping.model.Product;

@FeignClient(name = "service-product/service-product")
@RequestMapping("/products")
public interface ProductClient {

	@GetMapping("/{idProduct}")
	public ResponseEntity<Product> getProduct (@PathVariable("idProduct") Long idProduct);
	
	@GetMapping("/{idProduct}/stock") /* RequestParam añade la variable ?quantity=value en la ruta */
	public ResponseEntity<Product> updateStock (@PathVariable("idProduct") Long idProduct, 
			@RequestParam(required = true) Double quantity);
	
	
}
