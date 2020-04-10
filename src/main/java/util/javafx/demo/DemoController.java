package util.javafx.demo;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import util.javafx.DialogUtils;
import util.javafx.demo.controller.Controller;
import util.javafx.table.TableControl;

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
		TableControl control = new  TableControl();
		mainContent.getChildren().add(control);
		
	}

}
