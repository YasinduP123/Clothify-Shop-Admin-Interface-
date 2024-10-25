package repository.orderDetail.impl;

import dto.OrderDetails;
import dto.OrderHistory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import repository.DaoFactory;
import repository.orderDetail.OrderDetailDao;
import util.CrudUtil;
import util.DaoType;
import util.TempGenaratedKeyStore;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderDetailsDaoImpl implements OrderDetailDao {
	@Override
	public boolean save(OrderDetails entity) {
		System.out.println("ID FORM SAVE :"+TempGenaratedKeyStore.getInstance().getId());
		try {
			boolean execute = CrudUtil.execute("INSERT INTO OrderDetail VALUES (?,?,?,?)",
					TempGenaratedKeyStore.getInstance().getId(),
					entity.getProductId(),
					entity.getDescription(),
					entity.getQty()
			);

			System.out.println("GenKey "+TempGenaratedKeyStore.getInstance().getId());
			System.out.println("GenKey "+execute);
			System.out.println("DETaiLs "+entity);

			return execute;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public boolean update(OrderDetails entity) {
		return false;
	}

	@Override
	public boolean delete(Long entity) {
		return false;
	}

	@Override
	public List<OrderDetails> findAll() {
		return null;
	}

	@Override
	public ObservableList<OrderDetails> findById(Long newVal) {

		ObservableList<OrderDetails> orderDetails = FXCollections.observableArrayList();
		try {
			ResultSet execute = CrudUtil.execute("SELECT * FROM orderDetail WHERE order_id = ?", newVal);
			while (execute.next()){
				orderDetails.add(
					new OrderDetails(
						Long.parseLong(execute.getString(1)),
						Long.parseLong(execute.getString(2)),
						execute.getString(3),
						Integer.parseInt(execute.getString(4))
					)
				);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return orderDetails;
	}
}
