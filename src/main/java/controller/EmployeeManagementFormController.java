package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.Employee;
import entity.EmployeeEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import repository.employee.EmployeeDao;
import service.ServiceFactory;
import service.SuperService;
import service.employee.EmployeeService;
import util.ServiceType;

import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeManagementFormController implements Initializable {

	@FXML
	public TableColumn<?, ?> colEmail;

	@FXML
	private TableColumn<?, ?> colCompany;

	@FXML
	private TableColumn<?, ?> colId;

	@FXML
	private TableColumn<?, ?> colName;

	@FXML
	private TableColumn<?, ?> colSalary;

	@FXML
	private JFXComboBox<Long> comboSelectdId;

	@FXML
	private Label lblSelectedId;

	@FXML
	private TableView<Employee> tblEmployee;

	@FXML
	private JFXTextField txtCompany;

	@FXML
	private JFXTextField txtEmail;

	@FXML
	private JFXTextField txtName;


	EmployeeService employeeService = ServiceFactory.getInstance().getServiceType(ServiceType.EMPLOYEE);
	@FXML
	void btnAddOnAction(ActionEvent event) {

		if (txtName.getText() != null && txtCompany.getText() != null && txtEmail.getText() != null) {
			boolean isAddEmployee = employeeService.addEmployee(
					new Employee(
							null,
							txtName.getText(),
							txtCompany.getText(),
							txtEmail.getText()
					)
			);

			if (isAddEmployee) {
				new Alert(Alert.AlertType.CONFIRMATION,"Employee Add Success").show();
			}else{
				new Alert(Alert.AlertType.ERROR,"Employee NOT Add :(").show();
			}
		}else {
			new Alert(Alert.AlertType.ERROR,"Employee cannot add null values...!").show();
		}

		load();
	}

	@FXML
	void btnDeleteOnAction(ActionEvent event) {
		employeeService.deleteById(Long.parseLong(lblSelectedId.getText()));
		load();
	}

	@FXML
	void btnReloadOnAction(ActionEvent event) {
		load();
	}

	@FXML
	void btnSearchOnAction(ActionEvent event) {

		colId.setCellValueFactory(new PropertyValueFactory<>("id"));
		colName.setCellValueFactory(new PropertyValueFactory<>("name"));
		colCompany.setCellValueFactory(new PropertyValueFactory<>("company"));
		colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

		tblEmployee.setItems(employeeService.searchByEmail(txtEmail.getText()));
	}

	@FXML
	void btnUpdateOnAction(ActionEvent event) {

		if (txtName.getText() != null && txtCompany.getText() != null && txtEmail.getText() != null) {
			boolean isAddEmployee = employeeService.updateEmployee(
					new Employee(
							Long.parseLong(lblSelectedId.getText()),
							txtName.getText(),
							txtCompany.getText(),
							txtEmail.getText()
					)
			);

			if (isAddEmployee) {
				new Alert(Alert.AlertType.CONFIRMATION,"Employee Update Success").show();
			}else{
				new Alert(Alert.AlertType.ERROR,"Employee NOT Update :(").show();
			}
		}else {
			new Alert(Alert.AlertType.ERROR,"Employee cannot Update null values...!").show();
		}

		load();
	}

	public void load(){
		colId.setCellValueFactory(new PropertyValueFactory<>("id"));
		colName.setCellValueFactory(new PropertyValueFactory<>("name"));
		colCompany.setCellValueFactory(new PropertyValueFactory<>("company"));
		colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

		tblEmployee.setItems(employeeService.getEmployeeList());
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		comboSelectdId.setItems(employeeService.getEmpIds());

		comboSelectdId.getSelectionModel().selectedItemProperty().addListener((observableValue, oldVal, newVal) -> {
			if (newVal != null) getEmp(newVal);
		});

		tblEmployee.getSelectionModel().selectedItemProperty().addListener((observableValue, oldVal, newVal) -> {
			if (newVal != null) getSelectedItem(newVal);
		} );

		load();
	}

	private void getSelectedItem(Employee employee) {

		lblSelectedId.setText(employee.getId().toString());
		txtName.setText(employee.getName());
		txtCompany.setText(employee.getCompany());
		txtEmail.setText(employee.getEmail());
	}

	private void getEmp(Long id) {

		Employee empById = employeeService.getEmpById(id);
		lblSelectedId.setText(empById.getId()+"");
		txtEmail.setText(empById.getEmail());
		txtName.setText(empById.getName());
		txtCompany.setText(empById.getCompany());

	}
}
