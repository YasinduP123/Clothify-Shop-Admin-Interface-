package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardFormController {

	@FXML
	void btnOrderManagementOnAction(ActionEvent event) {
		Stage stage = new Stage();
		try {
			stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/order-management-view-form.fxml"))));
			stage.show();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@FXML
	void btnReportsOnAction(ActionEvent event) {
		Stage stage = new Stage();
		try {
			stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/report-view-form.fxml"))));
			stage.show();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@FXML
	void btnStockManagementOnAction(ActionEvent event) {
		Stage stage = new Stage();
		try {
			stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/stock-management-form-view.fxml"))));
			stage.show();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@FXML
	void btnSupplierManagementOnAction(ActionEvent event	) {
		Stage stage = new Stage();
		try {
			stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/supplier-management-view-form.fxml"))));
			stage.show();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@FXML
	public void btnEmployeeManagementOnAction(ActionEvent actionEvent) {
		Stage stage = new Stage();
		try {
			stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/employee-management-form-view.fxml"))));
			stage.show();
		} catch (IOException e) {

			throw new RuntimeException(e);
		}
	}
}
