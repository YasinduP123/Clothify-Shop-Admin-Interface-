package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {
	private Long orderId;
	private Long userId;
	private List<OrderDetails> orderDetails;
}
