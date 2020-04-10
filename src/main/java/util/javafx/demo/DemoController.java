package util.javafx.demo;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import util.javafx.DialogUtils;
import util.javafx.demo.controller.Controller;

public class DemoController implements Initializable, Controller {

	@FXML
	private MenuItem mnuClose;

	@FXML
	private MenuItem mnuDelete;

	@FXML
	private MenuItem mnuAbout;

	@FXML
	private VBox mainContent;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initListeners();
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

}
