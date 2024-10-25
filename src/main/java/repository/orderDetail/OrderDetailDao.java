package repository.orderDetail;

import dto.OrderDetails;
import dto.OrderHistory;
import javafx.collections.ObservableList;
import repository.CrudRepository;

public interface OrderDetailDao extends CrudRepository<OrderDetails> {
	ObservableList<OrderDetails> findById(Long newVal);
}
