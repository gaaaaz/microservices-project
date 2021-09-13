package academy.digitallab.store.serviceproduct.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

import academy.digitallab.store.serviceproduct.entity.Category;
import academy.digitallab.store.serviceproduct.entity.ErrorMessage;
import academy.digitallab.store.serviceproduct.entity.Product;
import academy.digitallab.store.serviceproduct.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping
	public ResponseEntity<List<Product>> listProduct(){ 
		
		/* ResponseEntity es más util para devolver los response y http status */
		
		List<Product> products = productService.listAllProduct();
		
		if(products.isEmpty()) {
			return ResponseEntity.noContent().build(); //204
		}
		
		return ResponseEntity.ok(products);
	}
	
	@GetMapping("/category/{categoryId}")
	public ResponseEntity<List<Product>> listProductByCategory(@PathVariable(name= "categoryId", required = false) Long categoryId){
		
		List<Product> products = new ArrayList<>();
		
		if(null == categoryId) {
			products = productService.listAllProduct();
			
			if(products.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
			
		} else {
			products = productService.findByCategory(Category.builder().id(categoryId).build());
		
			if(products.isEmpty()) {
				return ResponseEntity.notFound().build(); //204
			}
		}
		
		return ResponseEntity.ok(products);
	}
	
	@GetMapping("/{idProduct}")
	public ResponseEntity<Product> getProduct (@PathVariable("idProduct") Long idProduct){
		
		Product product = productService.getProduct(idProduct);
		
		if(null == product) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(product);
		
	}
	
	@PostMapping
	public ResponseEntity<Product> createProduct (@RequestBody Product product, BindingResult result){
		
		/* Binding result para capturar los errores de acuerdo a la validación con las anotaciones en Product */
		if(result.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
		}
		
		Product productCreate = productService.createProduct(product);
		return ResponseEntity.status(HttpStatus.CREATED).body(productCreate);
		
	}
	
	@PutMapping("/{idProduct}")
	public ResponseEntity<Product> updateProduct (@PathVariable("idProduct") Long idProduct, @RequestBody Product product){
		
		product.setId(idProduct);
		Product productDB = productService.updateProduct(product);
		
		if(productDB == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(productDB);
		
	}
	
	@DeleteMapping("/{idProduct}")
	public ResponseEntity<Product> deleteProduct (@PathVariable("idProduct") Long idProduct){
		
		Product productDelete = productService.deleteProduct(idProduct);
		
		if(productDelete == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(productDelete);
		
	}
	
	@GetMapping("/{idProduct}/stock") /* RequestParam añade la variable ?quantity=value en la ruta */
	public ResponseEntity<Product> updateStock (@PathVariable("idProduct") Long idProduct, 
			@RequestParam(required = true) Double quantity){
		
		Product stockProduct = productService.updateStock(idProduct, quantity);
	
		if(stockProduct == null) {
			return ResponseEntity.notFound().build();
		}
	
		return ResponseEntity.ok(stockProduct);
	}
	
	/* Funcion para formatear los mensajes de error según la validación en Product (anotaciones) */
	private String formatMessage(BindingResult result) {
		
		List<Map<String, String>> errors = result.getFieldErrors().stream()
				.map(err -> { //Java streams, está recorriendo la lista y guardando cada elemento en un Map<key,value>
					Map<String, String> error = new HashMap<>();
					error.put(err.getField(), err.getDefaultMessage());
					return error;
				}).collect(Collectors.toList()); // y lo convierte a una lista
		
		ErrorMessage errorMessage = ErrorMessage.builder()
				.code("01")
				.messages(errors)
				.build();
		
		/* Jackson para retornar los errores como json */
		
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
