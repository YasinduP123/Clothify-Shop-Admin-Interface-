package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.ServiceFactory;
import service.product.ProductService;
import util.ServiceType;
import util.TempUserLoginPasswordStore;

import java.net.URL;
import java.util.ResourceBundle;


public class StockManagementFormController implements Initializable {

	@FXML
	public JFXComboBox<Long> comboSearchId;

	@FXML
	public JFXTextField txtDescription;

	@FXML
	public JFXComboBox<String> comboCategory;

	@FXML
	public TableColumn<? , ?> colCategory;

	@FXML
	public Label lblSelectedId;

	@FXML
	public JFXComboBox<String> comboSortByCategory;

	@FXML
	public TableColumn<? , ?> colUserID;

	@FXML
	private TableColumn<?, ?> colDescription;

	@FXML
	private TableColumn<?, ?> colPrice;

	@FXML
	private TableColumn<?, ?> colProductId;

	@FXML
	private TableColumn<?, ?> colSize;

	@FXML
	private TableColumn<?, ?> colStock;

	@FXML
	private JFXComboBox<String> comboSize;

	@FXML
	private TableView<Product> tblStock;

	@FXML
	private JFXTextField txtPrice;

	@FXML
	private JFXTextField txtStock;

	ProductService productService = ServiceFactory.getInstance().getServiceType(ServiceType.PRODUCT);
	Long userId = TempUserLoginPasswordStore.getInstance().getConnection().get(0).getUserId();
	@FXML
	void btnAddOnAction(ActionEvent event) {

			try {
				System.out.println("User ID: " + userId);

				boolean isAddItem = productService.addProduct(
						new Product(
								null,
								txtDescription.getText(),
								comboSize.getValue(),
								txtStock.getText(),
								txtPrice.getText(),
								comboCategory.getValue(),
								userId
						)
				);

				if (isAddItem) {
					new Alert(Alert.AlertType.CONFIRMATION, "Item added successfully!").show();
				} else {
					new Alert(Alert.AlertType.ERROR, "Failed to add the item.").show();
				}

				loadTable();

			} catch (Exception e) {
				e.printStackTrace();
				new Alert(Alert.AlertType.ERROR, "An error occurred: " + e.getMessage()).show();
			}

	}

	@FXML
	void btnBackOnAction(ActionEvent event) {}
	@FXML
	void btnDeleteOnAction(ActionEvent event) {
		boolean isDeleted = productService.deleteProduct(Long.parseLong(lblSelectedId.getText()));

		if (isDeleted){
			new Alert(Alert.AlertType.CONFIRMATION,"Product delete success... :)").show();
		}else{
			new Alert(Alert.AlertType.ERROR,"Product delete NOT success... :(").show();
		}

		loadTable();
	}

	@FXML
	void btnPrintStockOnAction(ActionEvent event) {

	}

	@FXML
	void btnSearchOnAction(ActionEvent event) {}

	@FXML
	void btnUpdateOnAction(ActionEvent event) {
		boolean isUpdateItem = productService.updateProduct(
				new Product(
						Long.parseLong(lblSelectedId.getText()),
						txtDescription.getText(),
						comboSize.getValue(),
						txtStock.getText(),
						txtPrice.getText(),
						comboCategory.getValue(),
						userId
				)
		);

		if (isUpdateItem){
			new Alert(Alert.AlertType.CONFIRMATION,"Item Update success :)").show();
		}else {
			new Alert(Alert.AlertType.ERROR,"Item didn't Update :(").show();
		}

		loadTable();
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		ObservableList<String> categories = FXCollections.observableArrayList();
		categories.add("Ladies");
		categories.add("Gents");
		categories.add("Kids");
		comboCategory.setItems(categories);
		comboSortByCategory.setItems(categories);

		ObservableList<String> sizeList = FXCollections.observableArrayList();

		sizeList.add("S");
		sizeList.add("M");
		sizeList.add("L");
		sizeList.add("XL");
		sizeList.add("XXL");

		comboSize.setItems(sizeList);
		tblStock.getSelectionModel().selectedItemProperty().addListener((observableValue, oldVal, newVal) -> {
			if (newVal != null) {
				getSelectedItemFromTable(newVal);
			}
		});

		comboSearchId.getSelectionModel().selectedItemProperty().addListener((observableValue, oldVal, newVal) -> {
			if (newVal != null) {
				getSearchedItemById(newVal);
			}
		});

		comboSortByCategory.getSelectionModel().selectedItemProperty().addListener((observableValue, oldVal, newVal) -> {
			if (newVal != null) {
				sortByCategory(newVal);
			}
		});

		loadTable();

	}

	private void sortByCategory(String category) {

		colProductId.setCellValueFactory(new PropertyValueFactory<>("productId"));
		colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
		colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
		colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
		colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
		colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
		colUserID.setCellValueFactory(new PropertyValueFactory<>("userId"));

		tblStock.setItems(productService.getProductByCategory(category));

	}

	private void getSearchedItemById(Long id) {
		Product product = productService.getItemsById(id);

		txtDescription.setText(product.getDescription());
		txtPrice.setText(product.getPrice());
		txtStock.setText(product.getStock());
		lblSelectedId.setText(""+product.getProductId());
		comboCategory.setValue(product.getCategory());
		comboSize.setValue(product.getSize());

	}

	private void getSelectedItemFromTable(Product selectedProduct) {
		txtDescription.setText(selectedProduct.getDescription());
		txtPrice.setText(selectedProduct.getPrice());
		txtStock.setText(selectedProduct.getStock());
		lblSelectedId.setText(selectedProduct.getProductId().toString());
		comboCategory.setValue(selectedProduct.getCategory());
		comboSize.setValue(selectedProduct.getSize());

		System.out.println("selectedProduct "+selectedProduct);

	}


	private void loadTable(){
		ObservableList<Product> productList = productService.getProduct();
		System.out.println(productList);

		colProductId.setCellValueFactory(new PropertyValueFactory<>("productId"));
		colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
		colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
		colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
		colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
		colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
		colUserID.setCellValueFactory(new PropertyValueFactory<>("userId"));

		ObservableList<Long> productIds = FXCollections.observableArrayList();
		productList.forEach(product -> productIds.add(product.getProductId()));
		comboSearchId.setItems(productIds);

		tblStock.setItems(productList);

	}

	@FXML
	public void btnClearOnAction(ActionEvent actionEvent) {
		clear();
	}

	@FXML
	public void btnReloadOnAction(ActionEvent actionEvent) {
		clear();
		loadTable();
	}

	private void clear(){
		txtDescription.setText("");
		txtPrice.setText("");
		txtStock.setText("");
		comboCategory.setValue(null);
		comboSize.setValue(null);
		lblSelectedId.setText("");
		comboSortByCategory.setValue(null);
	}
}
