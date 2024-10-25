package service.orderDetail;

import dto.OrderDetails;
import dto.OrderHistory;
import javafx.collections.ObservableList;
import service.SuperService;

import java.util.List;

public interface OrderDetailService extends SuperService {
	boolean addOrderdetails(List<OrderDetails> orderDetails);

	ObservableList<OrderDetails> getOrderDetails(Long newVal);
}
