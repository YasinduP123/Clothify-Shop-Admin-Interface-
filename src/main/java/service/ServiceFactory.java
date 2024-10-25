package service;

import repository.DaoFactory;
import repository.SuperDao;
import repository.User.impl.UserDaoImpl;
import repository.orderDetail.impl.OrderDetailsDaoImpl;
import service.employee.impl.EmployeeServiceImpl;
import service.order.impl.OrderServiceImpl;
import service.orderDetail.OrderDetailService;
import service.orderDetail.impl.OrderDetailServiceImpl;
import service.product.impl.ProductServiceImpl;
import service.supplier.impl.SupplierServiceImpl;
import service.user.UserService;
import service.user.impl.UserServiceImpl;
import util.DaoType;
import util.ServiceType;

public class ServiceFactory {
	private static ServiceFactory instance;

	private ServiceFactory(){}

	public static ServiceFactory getInstance(){
		return instance == null ? instance = new ServiceFactory() : instance;
	}

	public <T extends SuperService>T getServiceType(ServiceType type){
		switch (type){
			case USER: return (T) new UserServiceImpl();
			case PRODUCT:return (T) new ProductServiceImpl();
			case SUPPLIER:return (T) new SupplierServiceImpl();
			case EMPLOYEE:return (T) new EmployeeServiceImpl();
			case ORDER_DETAILS:return (T) new OrderDetailServiceImpl();
			case ORDER:return (T) new OrderServiceImpl();
		}
		return null;
	}
}