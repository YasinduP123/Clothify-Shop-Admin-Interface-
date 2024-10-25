package repository;


import repository.User.impl.UserDaoImpl;
import repository.employee.EmployeeDao;
import repository.employee.impl.EmployeeDaoImpl;
import repository.order.impl.OrderDaoImpl;
import repository.orderDetail.impl.OrderDetailsDaoImpl;
import repository.product.impl.ProductDaoImpl;
import repository.supplier.impl.SupplierDaoImpl;
import service.product.impl.ProductServiceImpl;
import util.DaoType;

import static util.ServiceType.USER;

public class DaoFactory {
	private static DaoFactory instance;

	private DaoFactory(){}

	public static DaoFactory getInstance(){
		return instance == null ? instance = new DaoFactory() : instance;
	}

	public <T extends SuperDao>T getDaoType(DaoType type){
		switch (type){
			case USER: return (T) new UserDaoImpl();
			case PRODUCT:return (T) new ProductDaoImpl();
			case SUPPLIER:return (T) new SupplierDaoImpl();
			case EMPLOYEE:return (T) new EmployeeDaoImpl();
			case ORDER:return (T) new OrderDaoImpl();
			case ORDER_DETAIL:return (T) new OrderDetailsDaoImpl();
		}
		return null;
	}
}
