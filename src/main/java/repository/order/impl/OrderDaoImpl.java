package repository.order.impl;

import db.DBConnection;
import dto.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import repository.order.OrderDao;
import util.CrudUtil;
import util.TempGenaratedKeyStore;

import java.sql.*;
import java.util.List;

public class OrderDaoImpl implements OrderDao{

	@Override
	public boolean save(Order entity) {
		String SQL = "INSERT INTO orders (order_id, user_id) VALUES (?, ?)";
		try {
			System.out.println("Order Save " + entity);
			Connection connection = DBConnection.getInstance().getConnection();

			PreparedStatement psTm = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);

			psTm.setObject(1, entity.getOrderId());
			psTm.setObject(2, entity.getUserId());

			boolean isInserted = psTm.executeUpdate() > 0;
			ResultSet generatedKeys = psTm.getGeneratedKeys();
			if (generatedKeys.next()) {
				Long l = TempGenaratedKeyStore.getInstance().setId(Long.valueOf(generatedKeys.getString(1)));
				System.out.println("Generated Key: " + l);
			}

			return isInserted;
		} catch (SQLException e) {
			throw new RuntimeException("Failed to save Order", e);
		}
	}


	@Override
	public boolean update(Order entity) {
		return false;
	}

	@Override
	public boolean delete(Long entity) {
		return false;
	}

	@Override
	public List<Order> findAll() {

		ObservableList<Long> idList = FXCollections.observableArrayList();
		try {
			ResultSet rst = CrudUtil.execute("SELECT * FROM orders");
			while (rst.next()){
				idList.add(Long.parseLong(rst.getString(1)));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return null;
	}

	@Override
	public ObservableList<Long> getAllIds(){

		ObservableList<Long> idList = FXCollections.observableArrayList();
		try {
			ResultSet rst = CrudUtil.execute("SELECT order_id FROM orders");
			while (rst.next()){
				idList.add(Long.parseLong(rst.getString(1)));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return idList;

	}
}
