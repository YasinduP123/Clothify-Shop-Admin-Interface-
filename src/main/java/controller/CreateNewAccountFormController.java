package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import dto.User;
import entity.UserEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import org.hibernate.Session;
import service.ServiceFactory;
import service.user.UserService;
import service.user.impl.UserServiceImpl;
import util.CrudUtil;
import util.HibernateUtil;
import util.RoleTypes;
import util.ServiceType;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class CreateNewAccountFormController implements Initializable {

	@FXML
	private JFXComboBox<String> comboNewRole;

	@FXML
	private JFXTextField txtNewEmail;

	@FXML
	private JFXTextField txtNewPassword;

	UserService userService = ServiceFactory.getInstance().getServiceType(ServiceType.USER);

	@FXML
	void btnBackOnAction(ActionEvent event) {

	}

	@FXML
	void btnCreateNewOnAction(ActionEvent event) {

		User user = new User(txtNewEmail.getText(), txtNewPassword.getText(), comboNewRole.getValue());

		System.out.println(user);

		try {
			String passwordPattern = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=!]).{8,}$";
			boolean matches = Pattern.compile(passwordPattern).matcher(txtNewPassword.getText()).matches();
			if (matches) {
				if (userService.addUser(user)) {
					new Alert(Alert.AlertType.CONFIRMATION, "User account create success...:)").show();
				}
			}else {
				new Alert(Alert.AlertType.ERROR, "password should have 8 characters or more, including numbers, letters, and symbols ").show();
			}
		}catch (RuntimeException e){
			new Alert(Alert.AlertType.ERROR,"User account didn't create :(").show();
			throw new RuntimeException(e);
		}

	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {

		ObservableList<String> roleList = FXCollections.observableArrayList();

		roleList.add("Cashier");
		roleList.add("Admin");

		comboNewRole.setItems(roleList);

	}
}