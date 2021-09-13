package academy.digitallab.store.serviceproduct;

import java.util.Date;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import academy.digitallab.store.serviceproduct.entity.Category;
import academy.digitallab.store.serviceproduct.entity.Product;
import academy.digitallab.store.serviceproduct.repository.ProductRepository;
import academy.digitallab.store.serviceproduct.service.ProductService;
import academy.digitallab.store.serviceproduct.service.ProductServiceImpl;

@SpringBootTest
public class ProductServiceMockTest {

	/* Mockito permite realizar pruebas sobre ciertas funcionalidades sin necesidad de ejecutar todo. Es decir,
	 * se encarga de "fingir" un entorno para poder realizar las pruebas de las funciones específicas que se
	 * necesiten. */
	
	@Mock
	private ProductRepository productRepository;
	
	private ProductService productService;
	
	@BeforeEach /* Se ejecuta la función antes de realizar los Test */
	public void setup() {
		
		MockitoAnnotations.openMocks(this);
		productService = new ProductServiceImpl(productRepository);
		
		// Producto inicial para las pruebas
		Product computer = Product.builder()
				.id(1L)
				.name("Computer")
				.category(Category.builder().id(1L).build())
				.price(Double.parseDouble("12.5"))
				.stock(Double.parseDouble("5"))
				.build(); 
		
		// Mockear las funciones - Retornará las entidades encontradas ó actualizadas, etc...
		Mockito.when(productRepository.findById(1L))
			.thenReturn(Optional.of(computer));
		
		Mockito.when(productRepository.save(computer))
			.thenReturn(computer);
		

	}
	
	@Test
	public void whenValidGetID_ThenReturnProduct() {
		Product found = productService.getProduct(1L);
		Assertions.assertThat(found.getName()).isEqualTo("Computer"); 
		/* Test correcto si encuentras el producto con el id 1 y el nombre es igual a "Computer" */
	}
	
	/* Si añadimos un nuevo valor al stock, este debe actualizarse */
	public void whenValidUpdateStock_ThenReturnNewStock() {
		
		Product newStock = productService.updateStock(1L, Double.parseDouble("8")); // Producto para test (inicial) tiene 5
		Assertions.assertThat(newStock.getStock()).isEqualTo(13); // Más los 8...
		
	}
	
	
	
}
