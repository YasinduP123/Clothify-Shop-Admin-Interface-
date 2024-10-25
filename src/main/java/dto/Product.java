package dto;


import jakarta.persistence.ElementCollection;
import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	private Long productId;
	private String description;
	private String size;
	private String stock;
	private String price;
	private String category;
	private Long userId;

}
