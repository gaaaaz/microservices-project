package academy.digitallab.store.serviceshopping.model;

import lombok.Data;

@Data
public class Category {

	private Long id;
	private String name;
	
}

/* Estás clases imitan a las entidades de los microservicios service-product y service-customer, qué se necesitan
 * en este microservicio (service-shopping) para realizar las operaciones. Es necesario crear el package "model" y 
 * las clases necesarias de los otros microservicios, para realizar la comunicación con Feign. */
