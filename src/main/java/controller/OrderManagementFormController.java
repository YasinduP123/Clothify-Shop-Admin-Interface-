package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.CartTm;
import dto.Order;
import dto.OrderDetails;
import dto.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service.ServiceFactory;
import service.order.OrderService;
import service.product.ProductService;
import util.ServiceType;
import util.TempUserLoginPasswordStore;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OrderManagementFormController implements Initializable {

	@FXML
	public JFXComboBox<Long> comboProductID;
	@FXML
	public JFXComboBox<String> comboSize;
	@FXML
	public TableColumn<?, ?> colSize;
	@FXML
	private TableColumn<?, ?> colDescription;

	@FXML
	private TableColumn<?, ?> colProductId;

	@FXML
	private TableColumn<?, ?> colQty;

	@FXML
	private TableColumn<?, ?> colTotal;

	@FXML
	private TableColumn<?, ?> colUnitPrice;

	@FXML
	private TableColumn<?, ?> colUserId;

	@FXML
	private Label lblNetTotal;

	@FXML
	private TableView<CartTm> tblItemCart;

	@FXML
	private JFXTextField txtDescription;

	@FXML
	private JFXTextField txtPrice;

	@FXML
	private JFXTextField txtProductId;

	@FXML
	private JFXTextField txtQty;


	@FXML
	private JFXTextField txtStock;

	Long userId = TempUserLoginPasswordStore.getInstance().getConnection().get(0).getUserId();

	ProductService productService = ServiceFactory.getInstance().getServiceType(ServiceType.PRODUCT);
	ObservableList<CartTm> cartTms = FXCollections.observableArrayList();
	Double netTotal = 0.0;

	OrderService orderService = ServiceFactory.getInstance().getServiceType(ServiceType.ORDER);
	@FXML
	void btnAddToCartOnAction(ActionEvent event){
		double total = Double.parseDouble(txtPrice.getText()) * Double.parseDouble(txtQty.getText());

		cartTms.add(
				new CartTm(
						comboProductID.getValue(),
						userId,
						txtDescription.getText(),
						Integer.parseInt(txtQty.getText()),
						Double.parseDouble(txtPrice.getText()),
						comboSize.getValue(),
						total
				)
		);

		netTotal(total);

		colProductId.setCellValueFactory(new PropertyValueFactory<>("productId"));
		colUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));
		colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
		colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
		colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
		colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
		colSize.setCellValueFactory(new PropertyValueFactory<>("size"));

		tblItemCart.setItems(cartTms);

		System.out.println(cartTms);

	}

	@FXML
	void btnBackOnAction(ActionEvent event) {

	}

	@FXML
	void btnPlaceOrderOnAction(ActionEvent event) {
		ArrayList<OrderDetails> orderDetails = new ArrayList<>();

		cartTms.forEach(cartTm -> orderDetails.add(
				new OrderDetails(
						cartTm.getOrderId(),
						cartTm.getProductId(),
						cartTm.getDescription(),
						cartTm.getQty()
				)
		));

		Order order = new Order(null, userId, orderDetails);

		try {
			boolean isPlacedOrder = orderService.placeOrder(order);

			if (isPlacedOrder){
				new Alert(Alert.AlertType.CONFIRMATION,"Order Successfully Placed...!").show();
			}else {
				new Alert(Alert.AlertType.ERROR,"Order NOT Placed :( ").show();
			}

		} catch (SQLException e) {
			new Alert(Alert.AlertType.ERROR,"Order NOT Placed :( ").show();
			throw new RuntimeException(e);
		}

	}

	@FXML
	public void btnOrderHistoryOnAction(ActionEvent actionEvent) {
		Stage stage = new Stage();
		try {
			stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/order-history-form-controller.fxml"))));
			stage.show();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		comboProductID.setItems(productService.getProductIds());

		ObservableList<String> sizes = FXCollections.observableArrayList();
		sizes.add("S");
		sizes.add("M");
		sizes.add("L");
		sizes.add("XL");
		sizes.add("XXL");
		comboSize.setItems(sizes);

		comboProductID.getSelectionModel().selectedItemProperty().addListener((observableValue, oldVal, newVal) -> {
			getProductById(newVal);
		});

	}

	private void getProductById(Long newVal) {
		Product product = productService.getItemsById(newVal);
		System.out.println("newVal "+newVal);
		txtStock.setText(product.getStock());
		txtPrice.setText(product.getPrice());
		txtDescription.setText(product.getDescription());
		comboSize.setValue(product.getSize());

	}

	private void netTotal(Double netTotal) {
		this.netTotal += netTotal;
		lblNetTotal.setText(this.netTotal+"");
	}

	@FXML
	public void btnRemoveRowOnAction(ActionEvent actionEvent) {
		CartTm selectedItem = tblItemCart.getSelectionModel().getSelectedItem();
		if (selectedItem != null) {
			netTotal = netTotal-selectedItem.getTotal();
			lblNetTotal.setText(""+(netTotal));
			tblItemCart.getItems().remove(selectedItem);
		}
	}

	@FXML
	public void btnClearCartOnAction(ActionEvent actionEvent) {
		tblItemCart.setItems(null);
		lblNetTotal.setText("");
	}
}
