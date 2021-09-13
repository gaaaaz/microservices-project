package academy.digitallab.store.serviceshopping.entity;

import java.util.List;
import java.util.Map;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorMessage {

	private String code;
	private List<Map<String, String>> messages;

}
