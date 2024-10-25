package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.Supplier;
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
import service.supplier.SupplierService;
import util.ServiceType;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SupplierManagementFormController implements Initializable {

	@FXML
	public JFXComboBox<Long> comboSelectId;
	@FXML
	public Label lblSelectedId;
	@FXML
	private TableColumn<?, ?> colCompany;

	@FXML
	private TableColumn<?, ?> colEmail;

	@FXML
	private TableColumn<?, ?> colItem;

	@FXML
	private TableColumn<?, ?> colSupplierId;

	@FXML
	private TableColumn<?, ?> colUserId;

	@FXML
	private TableView<Supplier> tblSupplier;

	@FXML
	private JFXTextField txtCompany;

	@FXML
	private JFXTextField txtItem;

	@FXML
	private JFXTextField txtSupplierEmail;

	@FXML
	private JFXTextField txtSupplierName;

	SupplierService supplierService = ServiceFactory.getInstance().getServiceType(ServiceType.SUPPLIER);

	@FXML
	void btnAddSupplierOnAction(ActionEvent event) {

		Supplier supplier = new Supplier(
				txtSupplierName.getText(),
				txtCompany.getText(),
				txtItem.getText(),
				txtSupplierEmail.getText()
		);

		boolean isSaveSupplier = supplierService.isSaveSupplier(supplier);
		if (isSaveSupplier){
			new Alert(Alert.AlertType.CONFIRMATION,"Supplier Saved Successfully... : )").show();
		}else {
			new Alert(Alert.AlertType.CONFIRMATION,"Supplier NOT Saved... : (").show();
		}

		load();

	}


	@FXML
	void btnDeleteSupplierOnAction(ActionEvent event) {
		Long id = Long.parseLong(lblSelectedId.getText());
		if (lblSelectedId.getText() != null){
			boolean isDeleted = supplierService.deleteById(id);
			if (isDeleted){
				new Alert(Alert.AlertType.CONFIRMATION,"Supplier Deleted : ) ").show();
			}else {
				new Alert(Alert.AlertType.ERROR, "Supplier NOT Deleted...! ").show();
			}
		}else {
				new Alert(Alert.AlertType.ERROR, "Supplier NOT Deleted...! ").show();
		}
		load();
	}

	@FXML
	void btnPrintSupplierOnAction(ActionEvent event) {

	}

	@FXML
	void btnSearchSupplierOnAction(ActionEvent event) {
		List<Supplier> byEmail = supplierService.findByEmail(txtSupplierEmail.getText());
		if (byEmail != null){
			colSupplierId.setCellValueFactory(new PropertyValueFactory<>("id"));
			colUserId.setCellValueFactory(new PropertyValueFactory<>("name"));
			colCompany.setCellValueFactory(new PropertyValueFactory<>("company"));
			colItem.setCellValueFactory(new PropertyValueFactory<>("item"));
			colEmail.setCellValueFactory(new PropertyValueFactory<>("emailAddress"));

			ObservableList<Supplier> suppliers = FXCollections.observableArrayList();
			suppliers.addAll(byEmail);
			tblSupplier.setItems(suppliers);
		}
	}

	@FXML
	void btnUpdateSupplierOnAction(ActionEvent event) {
		Supplier supplier = new Supplier(
				Long.parseLong(lblSelectedId.getText()),
				txtSupplierName.getText(),
				txtCompany.getText(),
				txtItem.getText(),
				txtSupplierEmail.getText()
		);

		boolean isUpdateSupplier = supplierService.isUpdateSupplier(supplier);
		if (isUpdateSupplier){
			new Alert(Alert.AlertType.CONFIRMATION,"Supplier Updated Successfully... : )").show();
		}else {
			new Alert(Alert.AlertType.CONFIRMATION,"Supplier NOT Update... : (").show();
		}

		load();
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {

		tblSupplier.getSelectionModel().selectedItemProperty().addListener((observableValue, oldVal, newVal) -> {
				if(newVal != null){
					selectedTableRow(newVal);
				}
		});

		ObservableList<Long> idList = FXCollections.observableArrayList();
		List<Long> allIds = supplierService.getAllIds();

		comboSelectId.getSelectionModel().selectedItemProperty().addListener((observableValue, oldVal, newVal) -> {
			if(newVal != null){
				findById(newVal);
			}
		} );

		if (allIds != null){
			idList.addAll(allIds);
			comboSelectId.setItems(idList);
		}

		load();
	}

	private void selectedTableRow(Supplier newVal) {
		lblSelectedId.setText(newVal.getId().toString());
		txtItem.setText(newVal.getItem());
		txtSupplierEmail.setText(newVal.getEmailAddress());
		txtCompany.setText(newVal.getCompany());
		txtSupplierName.setText(newVal.getName());
	}

	private void findById(Long selectedId) {

		Supplier supplier = supplierService.findById(selectedId);
		txtSupplierName.setText(supplier.getName());
		txtCompany.setText(supplier.getCompany());
		txtItem.setText(supplier.getItem());
		txtSupplierEmail.setText(supplier.getEmailAddress());
		lblSelectedId.setText(selectedId.toString());
	}

	public void load(){

		ObservableList<Supplier> allSuppliers = supplierService.getAllSuppliers();
		System.out.println("allSuppliers "+allSuppliers);

		colSupplierId.setCellValueFactory(new PropertyValueFactory<>("id"));
		colUserId.setCellValueFactory(new PropertyValueFactory<>("name"));
		colCompany.setCellValueFactory(new PropertyValueFactory<>("company"));
		colItem.setCellValueFactory(new PropertyValueFactory<>("item"));
		colEmail.setCellValueFactory(new PropertyValueFactory<>("emailAddress"));

		tblSupplier.setItems(allSuppliers);
	}

	@FXML
	public void btnReloadOnAction(ActionEvent actionEvent) {
		load();
	}

	@FXML
	public void btnClearOnAction(ActionEvent actionEvent) {
		txtCompany.setText("");
		txtSupplierName.setText("");
		txtSupplierEmail.setText("");
		txtItem.setText("");
		lblSelectedId.setText("");
		comboSelectId.setValue(null);
	}
}
