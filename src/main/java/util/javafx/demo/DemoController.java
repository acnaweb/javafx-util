package util.javafx.demo;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import util.javafx.DialogUtils;
import util.javafx.demo.controller.Controller;
import util.javafx.model.Customer;
import util.javafx.model.CustomerTable;
import util.javafx.model.Database;
import util.javafx.table.RefreshListener;
import util.javafx.table.SelectListener;
import util.javafx.table.TableControl;

public class DemoController implements Initializable, Controller, SelectListener, RefreshListener {

	@FXML
	private MenuItem mnuClose;

	@FXML
	private MenuItem mnuDelete;

	@FXML
	private MenuItem mnuAbout;

	@FXML
	private VBox mainContent;

	public TableControl<Customer> control;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initListeners();
		createControls();
	}

	@Override
	public void initListeners() {
		mnuClose.setOnAction(evt -> {
			DialogUtils.showAlert("Close");
		});

		mnuDelete.setOnAction(evt -> {
			DialogUtils.showAlert("Delete");
		});
	}

	@Override
	public void createControls() {
		control = new TableControl<Customer>();
		control.init(this, this);
		control.createTable(new CustomerTable());
		mainContent.getChildren().add(control);
		refresh();
	}

	@Override
	public void notify(Object data) {
		if (data != null) {
			Customer customer = (Customer) data;
			System.out.println(customer.getName());
		}
	}

	@Override
	public void refresh() {
		control.populate(Database.list());
	}

}
