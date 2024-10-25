package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Product")
public class ProductEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productId;

	@Column(nullable = false)
	private String description;

	private String size;
	private String stock;
	private String price;
	private String category;
	private String userId;
}
