package util.javafx.form;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.stereotype.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

@Controller
public class FormController implements Initializable {

	@FXML
	private GridPane gridControls;

	@FXML
	private Button btnSave;

	@FXML
	private Button btnRemove;

	@FXML
	private Button btnCancel;

	@FXML
	private Button btnNew;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public GridPane getGridControls() {
		return gridControls;
	}

	public Button getBtnSave() {
		return btnSave;
	}

	public Button getBtnRemove() {
		return btnRemove;
	}

	public Button getBtnCancel() {
		return btnCancel;
	}

	public Button getBtnNew() {
		return btnNew;
	}

}
