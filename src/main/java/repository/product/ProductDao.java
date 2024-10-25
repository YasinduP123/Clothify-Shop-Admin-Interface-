package repository.product;

import dto.OrderDetails;
import entity.ProductEntity;
import javafx.collections.ObservableList;
import repository.CrudRepository;

public interface ProductDao extends CrudRepository<ProductEntity> {
	ProductEntity findById(Long id);
	ObservableList<ProductEntity> findByCategory(String category);

	ObservableList<Long> findAllIds();

	boolean updateById(OrderDetails productId);

}
