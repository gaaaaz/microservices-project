package academy.digitallab.store.serviceproduct.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_categories") // Tabla a crear en H2
@Data
@NoArgsConstructor @AllArgsConstructor @Builder
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Para generar las pk AI
	private Long id;
	private String name;
	
}
