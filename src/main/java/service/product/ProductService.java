package service.product;

import dto.OrderDetails;
import dto.Product;
import javafx.collections.ObservableList;
import service.SuperService;

import java.util.List;

public interface ProductService extends SuperService {
	boolean addProduct(Product product);

	ObservableList<Product> getProduct();

	boolean updateProduct(Product product);

	boolean deleteProduct(Long id);


	Product getItemsById(Long id);

	ObservableList<Product> getProductByCategory(String category);

	ObservableList<Long> getProductIds();

	boolean updateStock(List<OrderDetails> orderDetails);
}
