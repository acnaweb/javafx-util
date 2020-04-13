package util.javafx.customer;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import util.javafx.crud.CrudControl;
import util.javafx.function.OnPersistListener;
import util.javafx.function.OnRefreshListener;
import util.javafx.model.Customer;
import util.javafx.model.CustomerRepository;

public class CustomerController implements Initializable, OnRefreshListener, OnPersistListener<Customer> {

	@FXML
	private VBox mainContent;

	private CrudControl<Customer> control = new CrudControl<Customer>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		createControl();
	}

	private void createControl() {
		mainContent.getChildren().clear();
		mainContent.getChildren().add(control);

		control.createTable(CustomerCrud.tableBuilder);
		control.createForm(CustomerCrud.formBuilder);
		control.setOnModelToControlListener(CustomerCrud.onModelToControlListener);
		control.setOnRefreshListener(this);
		control.setOnPersistListener(this);
		control.setOnControlToModelListener(CustomerCrud.onControlToModelListener);
		control.setSupplier(new CustomerSupplier());
		control.setValidator(new CustomerValidator());
		refresh();
	}

	@Override
	public void refresh() {
		System.out.println("load");
		control.populate(CustomerRepository.list());
	}

	@Override
	public Customer persist(Customer data) {
		System.out.println("persist");
		return data;
	}
}
