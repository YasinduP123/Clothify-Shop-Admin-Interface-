package service.order;

import dto.Order;
import dto.OrderDetails;
import javafx.collections.ObservableList;
import service.SuperService;

import java.sql.SQLException;

public interface OrderService extends SuperService {
	boolean placeOrder(Order order) throws SQLException;

	ObservableList<Long> getAllOrderIds();

	ObservableList<Order> getOrders(Long newVal);
}
