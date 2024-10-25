package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import service.ServiceFactory;
import service.user.UserService;
import util.ServiceType;
import util.TempUserEmailStore;
import util.TempOtpStore;

import java.util.List;
import java.util.regex.Pattern;

public class ResetPasswordFormController {

	@FXML
	private JFXTextField txtNewPassword;

	@FXML
	private JFXTextField txtOtp;

	@FXML
	private JFXTextField txtReTypeNewPassword;

	@FXML
	void btnBackOnAction(ActionEvent event) {


	}

	@FXML
	void btnConformOnAction(ActionEvent event) {

		List<String> otpList = TempOtpStore.getInstance().getConnection();
		List<String> email = TempUserEmailStore.getInstance().getConnection();

		String otp = otpList.get(0);

			System.out.println("after otp "+otp);
			System.out.println("TXT otp "+otp);

			String passwordPattern = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=!]).{8,}$";
			boolean matches = Pattern.compile(passwordPattern).matcher(txtNewPassword.getText()).matches();

			if(matches) {
				if (txtOtp.getText().equals(otp) && txtNewPassword.getText().equals(txtReTypeNewPassword.getText())) {
					UserService userService = ServiceFactory.getInstance().getServiceType(ServiceType.USER);
					boolean isPasswordRest = userService.updatePassword(txtNewPassword.getText(), email.get(0));
					if (isPasswordRest) {
						TempOtpStore.getInstance().getConnection().clear();
						new Alert(Alert.AlertType.CONFIRMATION, "Password reset success... :)").show();
					}

				} else {
					new Alert(Alert.AlertType.ERROR, "Password NOT reset").show();
				}
			}else {
				new Alert(Alert.AlertType.ERROR, "password should have 8 characters or more, including numbers, letters, and symbols ").show();
			}

			System.out.println("Clear "+otpList);


	}
}
