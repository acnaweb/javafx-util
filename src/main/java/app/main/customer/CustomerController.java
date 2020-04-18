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

	private CrudControl<Customer> crud;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		showCrud();
	}

	private void showCrud() {
		crud = CustomerCrudFactory.getCrudControlCustomer(this, this);
		crud.init(null);

		setMainContent(crud);
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
		crud.load(CustomerRepository.list());
	}
}
