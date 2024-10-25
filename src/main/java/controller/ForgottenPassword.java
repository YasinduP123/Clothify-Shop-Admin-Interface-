package controller;

import com.jfoenix.controls.JFXTextField;
import jakarta.mail.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import service.ServiceFactory;
import service.user.UserService;
import util.ServiceType;
import util.TempUserEmailStore;
import java.io.IOException;

public class ForgottenPassword {
	@FXML
	private JFXTextField txtOtpEmail;
	@FXML
	void btnBackOnAction(ActionEvent event) {

	}
	@FXML
	void btnSendOtpOnAction(ActionEvent event) throws MessagingException {
		UserService serviceType = ServiceFactory.getInstance().getServiceType(ServiceType.USER);
		String email = txtOtpEmail.getText();
		TempUserEmailStore.getInstance().getConnection().add(0,email);
		try {
			if (serviceType.sendEmail(email)){
					new Alert(Alert.AlertType.CONFIRMATION, "Otp Send Success :)... Check your mail box").show();
					Stage stage = new Stage();
					stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/reset-password-view.fxml"))));
					stage.show();

			}else{
				new Alert(Alert.AlertType.ERROR, "Try again...!").show();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}
}
