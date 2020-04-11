package util.javafx.form;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import util.javafx.form.function.Form;
import util.javafx.form.function.FormBuilder;
import util.javafx.form.function.OnControlToModelListener;
import util.javafx.form.function.OnModelToControlListener;
import util.javafx.form.function.OnPersistListener;
import util.javafx.form.function.OnRefreshListener;
import util.javafx.form.function.OnSelectListener;

public class FormControl<T> extends VBox implements Form<T> {
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

	private OnRefreshListener onRefreshListener;

	private OnControlToModelListener<T> onControlToModelListener;

	private OnModelToControlListener<T> onModelToControlListener;

	private OnSelectListener<T> onSelectListener;

	private OnPersistListener<T> onPersistListener;

	public FormControl() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXML));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

//	/**************************************************************************************/
//	@SuppressWarnings("unchecked")
//	@Override
//	public void notify(Object data) {
//		if (data != null)
//			selectedItem = (T) data;
//
//		// modelToControlListener.bind(controls, selectedItem);
//	}

	/**************************************************************************************/
	public void updateControls(FormState formState) {
		FormUtils.clearControls(controls);

		switch (formState) {
		case IDLE:
			btnNew.setDisable(false);
			btnSave.setDisable(true);
			btnRemove.setDisable(true);
			btnCancel.setDisable(true);
			FormUtils.disableControls(controls, true);
			break;
		case LOADING:
			btnNew.setDisable(true);
			btnSave.setDisable(true);
			btnRemove.setDisable(true);
			btnCancel.setDisable(true);
			FormUtils.disableControls(controls, true);
			break;
		case EDITING:
			btnNew.setDisable(false);
			btnSave.setDisable(false);
			btnRemove.setDisable(false);
			btnCancel.setDisable(false);
			FormUtils.disableControls(controls, false);
			break;
		case SETTING_UP:
			btnNew.setDisable(false);
			btnSave.setDisable(false);
			btnRemove.setDisable(false);
			btnCancel.setDisable(false);
			FormUtils.disableControls(controls, true);
			break;
		default:
			break;
		}
	}

	@Override
	public T getSelectedItem() {
		return this.selectedItem;
	}

	@Override
	public void setSelectedItem(T selectedItem) {
		this.selectedItem = selectedItem;
	}

	@Override
	public void createForm(FormBuilder builder) {
		builder.build(gridControls, controls);
	}

	@Override
	public void setOnRefreshListener(OnRefreshListener onRefreshListener) {
		this.onRefreshListener = onRefreshListener;
	}

	@Override
	public void setOnControlToModelListener(OnControlToModelListener<T> onControlToModelListener) {
		this.onControlToModelListener = onControlToModelListener;
	}

	@Override
	public void setOnModelToControlListener(OnModelToControlListener<T> onModelToControlListener) {
		this.onModelToControlListener = onModelToControlListener;
	}

	@Override
	public void setOnSelectListener(OnSelectListener<T> onSelectListener) {
		this.onSelectListener = onSelectListener;

	}

	@Override
	public void setOnPersistListener(OnPersistListener<T> onPersistListener) {
		this.onPersistListener = onPersistListener;

	}

	@Override
	public void initListeners() {
		btnSave.setOnAction(evt -> {
			updateControls(FormState.PERSISTING);

			onControlToModelListener.bind(controls, selectedItem);

			selectedItem = onPersistListener.persist(selectedItem);
			updateControls(FormState.IDLE);
			onRefreshListener.refresh();
		});

		/**************************************************************************************/
		btnCancel.setOnAction(evt -> {
			selectedItem = null;
			updateControls(FormState.IDLE);
		});

	}

	@Override
	public boolean validateListeners() {
		return (onRefreshListener != null && onControlToModelListener != null && onModelToControlListener != null
				&& onSelectListener != null && onPersistListener != null);
	}

}
