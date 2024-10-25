package controller;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import dto.User;
import entity.UserEntity;
import jakarta.persistence.NoResultException;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;
import service.ServiceFactory;
import service.SuperService;
import service.user.UserService;
import service.user.impl.UserServiceImpl;
import util.CrudUtil;
import util.ServiceType;
import util.TempUserLoginPasswordStore;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginFormController implements Initializable {

	@FXML
	public JFXCheckBox showPassword;
	@FXML
	public JFXTextField textPassword;
	@FXML
	private JFXTextField txtEmail;
	@FXML
	private JFXPasswordField txtPassword;

	@FXML
	void btnCreateNewAccountOnAction(ActionEvent event) {

		Stage stage = new Stage();
		try {
			stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/create-new-account-form-view.fxml"))));
			stage.show();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	@FXML
	void btnForgotPasswordOnAction(ActionEvent event) {

		Stage stage = new Stage();
		try {
			stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/forgotten-password-view.fxml"))));
			stage.show();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	@FXML
	void btnLoginOnAction(ActionEvent event) {

		try {
			List<User> connection = TempUserLoginPasswordStore.getInstance().getConnection();
			UserService userService = ServiceFactory.getInstance().getServiceType(ServiceType.USER);
			User user = userService.searchByEmail(txtEmail.getText());
			connection.clear();
			System.out.println("userId " + user.getUserId());
			connection.add(0, user);

			if (Objects.equals(user.getEmail(), txtEmail.getText()) && Objects.equals(user.getPassword(), txtPassword.getText()) || Objects.equals(user.getPassword(), textPassword.getText())){
				Stage stage = new Stage();
				stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/dashborad-form-view.fxml"))));
				stage.show();
			}else {
				new Alert(Alert.AlertType.ERROR, "Wrong email or password.Try again...!").show();
			}

		} catch (IOException | NoResultException e) {
			new Alert(Alert.AlertType.ERROR, "Wrong email or password.Try again...!").show();
		}

	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {

		textPassword.textProperty().bindBidirectional(txtPassword.textProperty());

		textPassword.setManaged(false);
		textPassword.setVisible(false);

		showPassword.selectedProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue) {
				textPassword.setManaged(true);
				textPassword.setVisible(true);
				txtPassword.setManaged(false);
				txtPassword.setVisible(false);
			} else {
				textPassword.setManaged(false);
				textPassword.setVisible(false);
				txtPassword.setManaged(true);
				txtPassword.setVisible(true);
			}
		});
	}
}