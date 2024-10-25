package repository.order;

import dto.Order;
import javafx.collections.ObservableList;
import repository.CrudRepository;

public interface OrderDao extends CrudRepository<Order> {
	ObservableList<Long> getAllIds();
}
