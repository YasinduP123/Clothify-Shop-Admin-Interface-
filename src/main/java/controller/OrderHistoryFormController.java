package controller;

import com.jfoenix.controls.JFXComboBox;
import dto.OrderDetails;
import dto.OrderHistory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.ServiceFactory;
import service.order.OrderService;
import service.orderDetail.OrderDetailService;
import util.ServiceType;

import java.net.URL;
import java.util.ResourceBundle;

public class OrderHistoryFormController implements Initializable {

	@FXML
	public Label lblNetTotal;
	@FXML
	public TableColumn<?, ?> colQty;
	@FXML
	public TableColumn<?, ?> colUserId;
	@FXML
	private TableColumn<?, ?> colDescription;

	@FXML
	private TableColumn<?, ?> colProductId;

	@FXML
	private JFXComboBox<Long> comboOrderId;

	@FXML
	private TableView<OrderDetails> tblOrderDetails;

	OrderDetailService orderDetailService = ServiceFactory.getInstance().getServiceType(ServiceType.ORDER_DETAILS);
	OrderService orderService = ServiceFactory.getInstance().getServiceType(ServiceType.ORDER);
	@FXML
	void btnBackOnAction(ActionEvent event) {

	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {

		comboOrderId.setItems(orderService.getAllOrderIds());

		comboOrderId.getSelectionModel().selectedItemProperty().addListener((observableValue, oldVal, newVal) -> {
			getOrderDetailsById(newVal);
		});
	}


	private void getOrderDetailsById(Long newVal) {

		colProductId.setCellValueFactory(new PropertyValueFactory<>("productId"));
		colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
		colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));

		tblOrderDetails.setItems(orderDetailService.getOrderDetails(newVal));

	}


}
