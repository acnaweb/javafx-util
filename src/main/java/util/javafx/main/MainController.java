package util.javafx.main;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import util.javafx.ScreenManager;

public class MainController implements Initializable {

	@FXML
	private BorderPane root;

	@FXML
	private MenuItem mnuCustomer;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initListeners();
	}

	public void initListeners() {
		mnuCustomer.setOnAction(evt -> {
			ScreenManager.showCustomer();
		});
	}

	public void setMainContent(Node node) {
		root.setCenter(node);
	}
}
