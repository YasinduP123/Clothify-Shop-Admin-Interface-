package service.orderDetail.impl;

import dto.OrderDetails;
import dto.OrderHistory;
import javafx.collections.ObservableList;
import repository.DaoFactory;
import repository.orderDetail.OrderDetailDao;
import service.orderDetail.OrderDetailService;
import util.DaoType;

import java.util.List;

public class OrderDetailServiceImpl implements OrderDetailService {

	OrderDetailDao orderDetailDao = DaoFactory.getInstance().getDaoType(DaoType.ORDER_DETAIL);

	@Override
	public boolean addOrderdetails(List<OrderDetails> orderDetails) {

		for (OrderDetails orderDetails1 : orderDetails){
			boolean isSave = orderDetailDao.save(orderDetails1);
			if (!isSave){
				return false;
			}
		}
		return true;
	}

	@Override
	public ObservableList<OrderDetails> getOrderDetails(Long newVal) {
	   return orderDetailDao.findById(newVal);
	}

}
