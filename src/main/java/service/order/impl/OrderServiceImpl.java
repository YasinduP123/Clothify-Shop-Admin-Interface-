package service.order.impl;

import db.DBConnection;
import dto.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import repository.DaoFactory;
import repository.order.OrderDao;
import service.ServiceFactory;
import service.order.OrderService;
import service.orderDetail.OrderDetailService;
import service.product.ProductService;
import util.DaoType;
import util.ServiceType;
import util.TempGenaratedKeyStore;

import java.sql.Connection;
import java.sql.SQLException;

public class OrderServiceImpl implements OrderService {

	OrderDao orderDao = DaoFactory.getInstance().getDaoType(DaoType.ORDER);
	OrderDetailService orderDetailService = ServiceFactory.getInstance().getServiceType(ServiceType.ORDER_DETAILS);
	ProductService productService = ServiceFactory.getInstance().getServiceType(ServiceType.PRODUCT);
	@Override
	public boolean placeOrder(Order order) throws SQLException {
		Connection connection = DBConnection.getInstance().getConnection();
		try {
			connection.setAutoCommit(false);
			boolean isOrderAdd = orderDao.save(order);
			System.out.println("isOrderAdd "+isOrderAdd);
			System.out.println("GenKeyStroe "+TempGenaratedKeyStore.getInstance().getId());

			if (isOrderAdd) {
				boolean isOrderDetailsAdd = orderDetailService.addOrderdetails(order.getOrderDetails());
				System.out.println("isOrderDetailAdd "+isOrderDetailsAdd);

				if (isOrderDetailsAdd) {
					boolean isUpdateStock = productService.updateStock(order.getOrderDetails());
					System.out.println("isUpdateStock "+isUpdateStock);

					if (isUpdateStock) {
						System.out.println(isUpdateStock);
						connection.commit();
						return true;
					}
				}
			}
		}finally {
			connection.rollback();
			connection.setAutoCommit(true);


		}
		return false;
	}

	@Override
	public ObservableList<Long> getAllOrderIds() {
		return orderDao.getAllIds();
	}

	@Override
	public ObservableList<Order> getOrders(Long newVal) {
		ObservableList<Order> orders = FXCollections.observableArrayList();
		orders.addAll(orderDao.findAll());
		return orders;
	}
}
