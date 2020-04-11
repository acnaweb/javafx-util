package util.javafx.main;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import util.javafx.DialogUtils;
import util.javafx.customer.Customer;
import util.javafx.customer.CustomerControlToModel;
import util.javafx.customer.CustomerForm;
import util.javafx.customer.CustomerRepository;
import util.javafx.customer.CustomerTable;
import util.javafx.customer.CustomerModelToControl;
import util.javafx.demo.controller.Controller;
import util.javafx.form.FormControl;
import util.javafx.form.function.OnRefreshListener;
import util.javafx.table.TableControl;

public class MainController implements Initializable, Controller, OnRefreshListener {

	@FXML
	private MenuItem mnuClose;

	@FXML
	private MenuItem mnuDelete;

	@FXML
	private MenuItem mnuAbout;

	@FXML
	private VBox mainContent;

	public TableControl<Customer> tableControl = new TableControl<Customer>();
	public FormControl<Customer> formControl = new FormControl<Customer>();

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

//		tableControl.setOnSelectListener(formControl);
//		tableControl.setOnRefreshListener(this);
//		tableControl.createTable(new CustomerTable());
//
//		formControl.setOnModelToControlListener(new CustomerModelToControl());
//		formControl.setOnControlToModelListener(new CustomerControlToModel());
//		formControl.createForm(new CustomerForm());
//
//		mainContent.getChildren().add(tableControl);
//		mainContent.getChildren().add(formControl);

		load();
	}

	@Override
	public void load() {
//		System.out.println("load");
//		tableControl.populate(CustomerRepository.list());
//		formControl.clearControls();
	}

}
