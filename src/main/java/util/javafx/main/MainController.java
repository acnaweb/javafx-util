package util.javafx.main;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import util.javafx.DialogUtils;
import util.javafx.crud.CrudControl;
import util.javafx.crud.CustomerCrud;
import util.javafx.customer.Customer;
import util.javafx.customer.CustomerRepository;
import util.javafx.demo.controller.Controller;
import util.javafx.function.OnPersistListener;
import util.javafx.function.OnRefreshListener;

public class MainController implements Initializable, Controller, OnRefreshListener, OnPersistListener<Customer> {

	@FXML
	private MenuItem mnuClose;

	@FXML
	private MenuItem mnuDelete;

	@FXML
	private MenuItem mnuAbout;

	@FXML
	private VBox mainContent;

	public CrudControl<Customer> customerControl = new CrudControl<Customer>();

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
		customerControl.createTable(CustomerCrud.getTableBuilder());
		customerControl.createForm(CustomerCrud.getFormBuilder());
		customerControl.setOnModelToControlListener(CustomerCrud.getOnModelToControlListener());
		customerControl.setOnRefreshListener(this);
		customerControl.setOnPersistListener(this);
		customerControl.setOnControlToModelListener(CustomerCrud.getOnControlToModelListener());

		mainContent.getChildren().add(customerControl);

		refresh();
	}

	@Override
	public void refresh() {
		System.out.println("load");
		customerControl.populate(CustomerRepository.list());
	}

	@Override
	public Customer persist(Customer data) {
		System.out.println("persist");
		return data;
	}

}
