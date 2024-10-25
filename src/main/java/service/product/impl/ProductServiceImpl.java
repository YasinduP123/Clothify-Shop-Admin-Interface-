package service.product.impl;

import dto.OrderDetails;
import dto.Product;
import entity.ProductEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.product.ProductDao;
import service.product.ProductService;
import util.DaoType;

import java.util.List;


public class ProductServiceImpl implements ProductService {

	ModelMapper mapper = new ModelMapper();
	ProductDao productDao = DaoFactory.getInstance().getDaoType(DaoType.PRODUCT);

	@Override
	public boolean addProduct(Product product) {
		System.out.println(product);
		return productDao.save(mapper.map(product, ProductEntity.class));
	}

	@Override
	public ObservableList<Product> getProduct() {
		ObservableList<Product> products = FXCollections.observableArrayList();
		productDao.findAll().forEach(productEntity -> products.add(mapper.map(productEntity,Product.class)));
		return products;
	}

	@Override
	public boolean updateProduct(Product product) {
		return productDao.update(mapper.map(product, ProductEntity.class));
	}

	@Override
	public boolean deleteProduct(Long id) {
		return productDao.delete(id);
	}

	@Override
	public Product getItemsById(Long id) {
		return mapper.map(productDao.findById(id),Product.class);
	}

	@Override
	public ObservableList<Product> getProductByCategory(String category) {

		ObservableList<Product> products = FXCollections.observableArrayList();
		productDao.findByCategory(category).forEach(productEntity -> products.add(mapper.map(productEntity, Product.class)));
		return products;
	}

	@Override
	public ObservableList<Long> getProductIds() {
		return productDao.findAllIds();
	}

	@Override
	public boolean updateStock(List<OrderDetails> orderDetails) {

		for (OrderDetails orderDetails1 : orderDetails){
			boolean isAdd = productDao.updateById(orderDetails1);
			System.out.println("isAdd "+isAdd);
			if (!isAdd) {
				return false;
			}
		}
		return true;
	}


}
