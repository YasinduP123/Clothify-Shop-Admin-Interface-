package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

import java.net.URL;
import java.util.ResourceBundle;

public class ReportFormController implements Initializable {

	@FXML
	public PieChart salesChart;

	@FXML
	void btnBackOnAction(ActionEvent event) {

	}

	@FXML
	void btnAnnuallyOnAction(ActionEvent event) {

		salesChart.getData().clear();
		PieChart.Data slice1 = new PieChart.Data("Moose", 30);
		PieChart.Data slice2 = new PieChart.Data("Adidas", 25);
		PieChart.Data slice3 = new PieChart.Data("Lacoste", 25);
		PieChart.Data slice4 = new PieChart.Data("BOSS", 20);

		double moose = slice1.getPieValue();
		double adidas = slice2.getPieValue();
		double lacoste = slice3.getPieValue();
		double boss = slice4.getPieValue();

		double totalSales = moose + adidas + lacoste + boss;

		slice1.setName(slice1.getName()+" \n"+moose+" \n"+ moose * (totalSales/100)+" %");
		slice2.setName(slice2.getName()+" \n"+adidas+" \n"+ adidas * (totalSales/100)+" %");
		slice3.setName(slice3.getName()+" \n"+lacoste+" \n"+ lacoste * (totalSales/100)+" %");
		slice4.setName(slice4.getName()+" \n"+boss+" \n"+ boss * (totalSales/100)+" %");

		salesChart.getData().addAll(slice1, slice2, slice3, slice4);
		salesChart.setTitle("Annual Sales Report");
	}

	@FXML
	void btnDailyOnAction(ActionEvent event) {

		salesChart.getData().clear();
		PieChart.Data slice1 = new PieChart.Data("Moose", 32);
		PieChart.Data slice2 = new PieChart.Data("Adidas", 43);
		PieChart.Data slice3 = new PieChart.Data("Lacoste", 50);
		PieChart.Data slice4 = new PieChart.Data("BOSS", 52);

		double moose = slice1.getPieValue();
		double adidas = slice2.getPieValue();
		double lacoste = slice3.getPieValue();
		double boss = slice4.getPieValue();

		double totalSales = moose + adidas + lacoste + boss;

		slice1.setName(slice1.getName()+" \n"+moose+" \n"+ moose * (totalSales/100)+" %");
		slice2.setName(slice2.getName()+" \n"+adidas+" \n"+ adidas * (totalSales/100)+" %");
		slice3.setName(slice3.getName()+" \n"+lacoste+" \n"+ lacoste * (totalSales/100)+" %");
		slice4.setName(slice4.getName()+" \n"+boss+" \n"+ boss * (totalSales/100)+" %");

		salesChart.getData().addAll(slice1, slice2, slice3, slice4);
		salesChart.setTitle("Daily Sales Report");
	}

	@FXML
	void btnMonthlyOnAction(ActionEvent event) {

		salesChart.getData().clear();
		PieChart.Data slice1 = new PieChart.Data("Moose", 50);
		PieChart.Data slice2 = new PieChart.Data("Adidas", 15);
		PieChart.Data slice3 = new PieChart.Data("Lacoste", 25);
		PieChart.Data slice4 = new PieChart.Data("BOSS", 10);

		double moose = slice1.getPieValue();
		double adidas = slice2.getPieValue();
		double lacoste = slice3.getPieValue();
		double boss = slice4.getPieValue();

		double totalSales = moose + adidas + lacoste + boss;

		slice1.setName(slice1.getName()+" \n"+moose+" \n"+ moose * (totalSales/100)+" %");
		slice2.setName(slice2.getName()+" \n"+adidas+" \n"+ adidas * (totalSales/100)+" %");
		slice3.setName(slice3.getName()+" \n"+lacoste+" \n"+ lacoste * (totalSales/100)+" %");
		slice4.setName(slice4.getName()+" \n"+boss+" \n"+ boss * (totalSales/100)+" %");

		salesChart.getData().addAll(slice1, slice2, slice3, slice4);
		salesChart.setTitle("Monthly Sales Report");
	}

	@FXML
	void btnPrintReportOnAction(ActionEvent event) {

	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {


	}
}
