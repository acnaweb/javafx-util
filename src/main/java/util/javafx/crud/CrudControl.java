package util.javafx.crud;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.function.Supplier;

import javafx.collections.FXCollections;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import util.javafx.EntityBase;
import util.javafx.form.FormState;
import util.javafx.function.FormBuilder;
import util.javafx.function.OnControlToModelListener;
import util.javafx.function.OnLoadListener;
import util.javafx.function.OnModelToControlListener;
import util.javafx.function.OnPersistListener;
import util.javafx.function.OnRefreshListener;
import util.javafx.function.OnSelectListener;
import util.javafx.function.TableBuilder;
import util.javafx.util.DialogUtils;
import util.javafx.util.FormUtils;

public class CrudControl<T extends EntityBase> extends VBox implements OnLoadListener<T>, OnSelectListener<T> {
	private static final String FXML = "CrudControl.fxml";

	@FXML
	private Button btnRefresh;

	@FXML
	private TableView<T> table;
	private T selectedItem;
	private Supplier<T> supplier;
	private Predicate<T> validator;

	@FXML
	private Button btnNew;

	@FXML
	private Button btnSave;

	@FXML
	private Button btnRemove;

	@FXML
	private Button btnCancel;

	@FXML
	private GridPane gridControls;

	private OnControlToModelListener<T> onControlToModelListener;

	private OnModelToControlListener<T> onModelToControlListener;

	private OnRefreshListener onRefreshListener;

	private OnPersistListener<T> onPersistListener;

	private Map<String, Node> controls = new HashMap<String, Node>();

	public CrudControl() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXML));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try {
			fxmlLoader.load();
			init();

		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

	private void init() {
		updateControls(FormState.SETTING_UP);
		initListeners();
		updateControls(FormState.IDLE);
	}

	private void initListeners() {
		/**********************************************************/
		table.setOnMouseClicked(evt -> {
			notify(table.getSelectionModel().getSelectedItem());
		});

		/**********************************************************/
		btnNew.setOnAction(evt -> {
			selectedItem = supplier.get();
			updateControls(FormState.NEW);
		});

		/**********************************************************/
		btnCancel.setOnAction(evt -> {
			selectedItem = null;
			updateControls(FormState.IDLE);
		});

		/**********************************************************/
		btnRemove.setOnAction(evt -> {
			updateControls(FormState.PERSISTING);
			if (selectedItem != null) {
				selectedItem.deleteEntity();
				onPersistListener.persist(selectedItem);
				selectedItem = null;
				btnRefresh.fire();
			} else {
				DialogUtils.showAlertError("Item não selecionado");
			}
		});

		/**********************************************************/
		btnRefresh.setOnAction(evt -> {
			Service<Void> serviceWork = new Service<Void>() {

				@Override
				protected Task<Void> createTask() {
					return new Task<Void>() {

						@Override
						protected Void call() throws Exception {
							updateControls(FormState.LOADING);
							onRefreshListener.refresh();
							updateControls(FormState.IDLE);
							return null;
						}
					};
				}
			};

			serviceWork.setOnSucceeded(par1 -> {
			});

			serviceWork.setOnFailed(par1 -> {
				System.out.println(par1.getEventType().getName());

			});

			serviceWork.start();
		});

		/**********************************************************/
		btnSave.setOnAction(evt -> {
			updateControls(FormState.PERSISTING);
			if (selectedItem != null && validator.test(selectedItem)) {
				onControlToModelListener.bind(controls, selectedItem);
				selectedItem = onPersistListener.persist(selectedItem);
				btnRefresh.fire();
			} else {
				DialogUtils.showAlertError("Item não selecionado ou incompleto");
			}
		});

		/**************************************************************************************/
		btnCancel.setOnAction(evt -> {
			selectedItem = null;
			updateControls(FormState.IDLE);
		});
	}

	public void createTable(TableBuilder<T> builder) {
		builder.build(table);
	}

	public void createForm(FormBuilder builder) {
		builder.build(gridControls, controls);
	}

	public void populate(List<T> data) {
		load(data);
	}

	@Override
	public void load(List<T> data) {
		updateControls(FormState.LOADING);
		table.getItems().clear();
		table.getItems().addAll(FXCollections.observableArrayList(data));
		updateControls(FormState.IDLE);
	}

	@Override
	public void notify(T data) {
		if (data != null) {
			selectedItem = data;
			System.out.println(data);
			updateControls(FormState.EDITING);
			onModelToControlListener.bind(controls, selectedItem);
			FormUtils.disableControls(controls, false);
		} else {
			selectedItem = null;
		}
	}

	public void addItem(T item) {
		table.getItems().add(item);
	}

	public void removeAll() {
		table.getItems().clear();
	}

	public void setOnRefreshListener(OnRefreshListener onRefreshListener) {
		this.onRefreshListener = onRefreshListener;
	}

	public void setOnControlToModelListener(OnControlToModelListener<T> onControlToModelListener) {
		this.onControlToModelListener = onControlToModelListener;
	}

	public void setOnModelToControlListener(OnModelToControlListener<T> onModelToControlListener) {
		this.onModelToControlListener = onModelToControlListener;
	}

	public void setOnPersistListener(OnPersistListener<T> onPersistListener) {
		this.onPersistListener = onPersistListener;

	}

	public void setValidator(Predicate<T> validator) {
		this.validator = validator;
	}

	public void setSupplier(Supplier<T> supplier) {
		this.supplier = supplier;
	}

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
			btnNew.setDisable(true);
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

		case NEW:
			btnNew.setDisable(true);
			btnSave.setDisable(false);
			btnRemove.setDisable(true);
			btnCancel.setDisable(false);
			FormUtils.disableControls(controls, false);
			break;

		}

		btnNew.setVisible(!btnNew.isDisabled());
		btnSave.setVisible(!btnSave.isDisabled());
		btnRemove.setVisible(!btnRemove.isDisabled());
		btnCancel.setVisible(!btnCancel.isDisabled());

	}

	public boolean validateListeners() {
		Objects.requireNonNull(onRefreshListener, "onRefreshListener null");
		Objects.requireNonNull(onControlToModelListener, "onControlToModelListener null");
		Objects.requireNonNull(onModelToControlListener, "onModelToControlListener null");
		Objects.requireNonNull(onPersistListener, "onPersistListener null");

		return true;
	}

}
