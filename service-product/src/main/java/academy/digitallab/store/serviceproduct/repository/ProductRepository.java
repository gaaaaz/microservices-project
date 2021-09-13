package academy.digitallab.store.serviceproduct.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import academy.digitallab.store.serviceproduct.entity.Category;
import academy.digitallab.store.serviceproduct.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	/* JpaRepository tiene permite el uso de funciones básicas para CRUD y búsqueda (todos, por id, etc.)
	 * sin necesidad de colocar querys sql. 
	 * Cumple el mismo papel que "mapper", cuando se usa un ORM como MyBatis, solo que no utiliza archivos .xml extra.
	 * Si se quieren realizar operaciones complejas como join u otras, usar la anotación @Query.*/
	
	public List<Product> findByCategory(Category category);
	
	//Si quieres hacer un findBy por cierto argumento o entidad, solo hay que colocar findByEntidad(entidad ó argumento)
	
	//H2 base de datos en memoria
	//Autowired inyección de dependencias - Builder construcción de objeto - entidad
	
}
