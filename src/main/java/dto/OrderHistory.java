package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderHistory {
	private Long orderId;
	private Long productId;
	private String description;
	private Integer qty;
	private Long userId;

}
