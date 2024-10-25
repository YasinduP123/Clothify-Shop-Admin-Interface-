package service.employee.impl;

import dto.Employee;
import entity.EmployeeEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.employee.EmployeeDao;
import service.employee.EmployeeService;
import util.DaoType;

public class EmployeeServiceImpl implements EmployeeService {

	ModelMapper mapper = new ModelMapper();
	EmployeeDao employeeDao = DaoFactory.getInstance().getDaoType(DaoType.EMPLOYEE);
	@Override
	public boolean addEmployee(Employee employee) {
		return employeeDao.save(mapper.map(employee, EmployeeEntity.class));
	}

	@Override
	public ObservableList<Employee> getEmployeeList() {
		ObservableList<Employee> employeeList = FXCollections.observableArrayList();
		employeeDao.findAll().forEach(employeeEntity -> employeeList.add(mapper.map(employeeEntity, Employee.class)));
		return employeeList;
	}

	@Override
	public ObservableList<Long> getEmpIds() {
		return employeeDao.getEmpIds();
	}

	@Override
	public Employee getEmpById(Long id) {
		return mapper.map(employeeDao.findById(id),Employee.class);
	}

	@Override
	public ObservableList<Employee> searchByEmail(String email) {
		ObservableList<Employee> employees = FXCollections.observableArrayList();
		employeeDao.findByEmail(email).forEach(employeeEntity -> employees.add(mapper.map(employeeEntity,Employee.class)));
		return employees;
	}

	@Override
	public boolean deleteById(Long id) {
		return employeeDao.delete(id);
	}

	@Override
	public boolean updateEmployee(Employee employee) {
		return employeeDao.update(mapper.map(employee,EmployeeEntity.class));
	}

}
