package app.main.customer;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import util.javafx.crud.CrudControl;
import util.javafx.function.OnPersistListener;
import util.javafx.function.OnRefreshListener;

public class CustomerController implements Initializable, OnRefreshListener, OnPersistListener<Customer> {

	@FXML
	private VBox mainContent;

	private CrudControl<Customer> control;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		showCrud();
	}

	private void showCrud() {
		control = CustomerCrudFactory.getCrud(this, this);
		control.init(null);

		setMainContent(control);
		refresh();
	}

	private void setMainContent(Node node) {
		mainContent.getChildren().clear();
		mainContent.getChildren().add(node);
	}

	@Override
	public Customer persist(Customer data) {
		System.out.println("persisting");
		return data;
	}

	@Override
	public void delete(Customer data) {
		System.out.println("deleting");
	}

	@Override
	public void refresh() {
		control.load(CustomerRepository.list());
	}
}
