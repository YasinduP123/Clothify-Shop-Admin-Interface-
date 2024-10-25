package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetails {
	private Long orderId;
	private Long productId;
	private String description;
	private Integer qty;
}
