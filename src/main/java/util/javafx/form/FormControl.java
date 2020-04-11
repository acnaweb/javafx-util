package util.javafx.form;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import util.javafx.listener.OnControlToModelListener;
import util.javafx.listener.OnSelectListener;
import util.javafx.listener.OnValueToControlListener;

public class FormControl<T> extends VBox implements OnSelectListener {
	private static final String FXML = "FormControl.fxml";

	@FXML
	private GridPane gridControls;

	private Map<String, Node> controls = new HashMap<String, Node>();

	@FXML
	private Button btnSave;

	@FXML
	private Button btnRemove;

	@FXML
	private Button btnCancel;

	@FXML
	private Button btnNew;

	private T selectedItem;

	private OnValueToControlListener<T> modelToControlListener;

	private OnControlToModelListener<T> controlToModelListener;

	public FormControl() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXML));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try {
			fxmlLoader.load();
			initListeners();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

	private void initListeners() {
		btnSave.setOnAction(evt -> {
			controlToModelListener.bind(controls, selectedItem);
			System.out.println(selectedItem);

			// selectedItem = service.save(selectedItem);
			// tableManager.table.getSelectionModel().select(selectedItem);
			// updateControls(FormState.IDLE);
			// btnRefresh.fire();
		});

	}

	/**************************************************************************************/
	public void setOnModelToControlListener(OnValueToControlListener<T> modelToControlListener) {
		this.modelToControlListener = modelToControlListener;
	}

	/**************************************************************************************/
	public void createForm(FormBuilder formBuilder) {
		formBuilder.build(gridControls, controls);
	}

	/**************************************************************************************/
	@SuppressWarnings("unchecked")
	@Override
	public void notify(Object data) {
		if (data != null)
			selectedItem = (T) data;

		modelToControlListener.bind(controls, selectedItem);
	}

	/**************************************************************************************/

	/**************************************************************************************/
	@SuppressWarnings("unchecked")
	public final void clearControls() {
		controls.entrySet().stream().forEach(entry -> {
			if (entry.getValue() instanceof TextInputControl) {
				((TextInputControl) entry.getValue()).clear();
			} else if (entry.getValue() instanceof CheckBox) {
				((CheckBox) entry.getValue()).setSelected(false);
			} else if (entry.getValue() instanceof Spinner) {
				try {
					((Spinner<Integer>) entry.getValue()).getValueFactory().setValue(0);
				} catch (Exception e) {
				}
			} else if (entry.getValue() instanceof ComboBox) {
				((ComboBox<String>) entry.getValue()).setValue(null);
			}
		});
	}

	/**************************************************************************************/
	private final void disableControls(boolean status) {
		controls.entrySet().stream().forEach(entry -> {
			if (entry.getValue() instanceof Node) {
				((Node) entry.getValue()).setDisable(status);
			}
		});
	}

	/**************************************************************************************/
	private void setCancelAction(Button btn) {
		btnCancel = btn;
		btnCancel.setOnAction(evt -> {
			selectedItem = null;
			updateControls(FormState.IDLE);
		});
	}

	/**************************************************************************************/
	private void updateControls(FormState formState) {
		clearControls();

		switch (formState) {
		case IDLE:
			btnNew.setDisable(false);
			btnSave.setDisable(true);
			btnRemove.setDisable(true);
			btnCancel.setDisable(true);
			disableControls(true);
			break;
		case LOADING:
			btnNew.setDisable(true);
			btnSave.setDisable(true);
			btnRemove.setDisable(true);
			btnCancel.setDisable(true);
			disableControls(true);
			break;
		case EDITING:
			btnNew.setDisable(false);
			btnSave.setDisable(false);
			btnRemove.setDisable(false);
			btnCancel.setDisable(false);
			disableControls(false);
			break;
		}

	}

	/**************************************************************************************/
	public static enum FormState {
		IDLE, EDITING, LOADING
	}

	public void setOnControlToModelListener(OnControlToModelListener<T> controlToModelListener) {
		this.controlToModelListener = controlToModelListener;
	}

}
