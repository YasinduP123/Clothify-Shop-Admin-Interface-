package dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartTm {
	private Long orderId;
	private Long productId;
	private Long userId;
	private String description;
	private Integer qty;
	private Double unitPrice;
	private String size;
	private Double total;
	public CartTm(Long productId, Long userId, String description, Integer qty, Double unitPrice, String size, Double total) {
		this.productId = productId;
		this.userId = userId;
		this.description = description;
		this.qty = qty;
		this.unitPrice = unitPrice;
		this.size = size;
		this.total = total;
	}

}
