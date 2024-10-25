package service.employee;

import dto.Employee;
import javafx.collections.ObservableList;
import service.SuperService;

public interface EmployeeService extends SuperService {
	boolean addEmployee(Employee employee);

	ObservableList<Employee> getEmployeeList();

	ObservableList<Long> getEmpIds();

	Employee getEmpById(Long id);

	ObservableList<Employee> searchByEmail(String text);

	boolean deleteById(Long id);

	boolean updateEmployee(Employee employee);
}
