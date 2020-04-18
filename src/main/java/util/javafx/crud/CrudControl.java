package util.javafx.crud;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.function.Supplier;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;
import util.javafx.function.OnControlToModelListener;
import util.javafx.function.OnLoadListener;
import util.javafx.function.OnModelToControlListener;
import util.javafx.function.OnPersistListener;
import util.javafx.function.OnRefreshListener;
import util.javafx.function.OnSelectListener;

public class CrudControl<T> extends VBox implements OnSelectListener<T>, OnLoadListener<T> {
	private static final String FXML = "CrudControl.fxml";

	@FXML
	private Button btnRefresh;

	@FXML
	private Button btnNew;

	@FXML
	private Button btnSave;

	@FXML
	private Button btnRemove;

	@FXML
	private Button btnCancel;

	@FXML
	private Button btnForm;

	@FXML
	private ScrollPane itemContent;

	@FXML
	private ToolBar toolbarAction;

	private ObservableList<Button> customButtons = FXCollections.<Button>observableArrayList();

	// @Autowired
	private FormItemControl formItemControl = new FormItemControl();

	@FXML
	private Map<String, Node> controls = new HashMap<String, Node>();

	@FXML
	private TableView<T> table;
	private T selectedItem;

	private Supplier<T> supplier;
	private Predicate<T> validator;

	private final TableBuilder<T> tableBuilder = new TableBuilder<T>();

	/*********************************************************/

	private OnControlToModelListener<T> onControlToModelListener;

	private OnModelToControlListener<T> onModelToControlListener;

	private OnRefreshListener onRefreshListener;

	private OnPersistListener<T> onPersistListener;

	private List<OnSelectListener<T>> onSelectListeners;

	/*********************************************************/
	public CrudControl(Supplier<T> supplier, Predicate<T> validator) {

		this.supplier = supplier;
		this.validator = validator;

		Objects.requireNonNull(supplier, "supplier is null");
		Objects.requireNonNull(validator, "validator is null");

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXML));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try {
			fxmlLoader.load();

		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

	public void init(final Map<String, Object> params) {
		updateControls(FormState.SETTING_UP);
		initListeners();
		validate();
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
				onPersistListener.delete(selectedItem);
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

		/**************************************************************************************/
		btnForm.setOnAction(evt -> {
			setContent(formItemControl);
		});
	}

	public void createTable(List<Column> columns) {
		tableBuilder.build(columns, table);
	}

	public void createForm(List<Column> columns) {
		btnForm.setVisible(false);
		FormUtils.build(columns, formItemControl, controls);
		setContent(formItemControl);
	}

	private void validate() {
		Objects.requireNonNull(onControlToModelListener, "onControlToModelListener is null");
		Objects.requireNonNull(onModelToControlListener, "onModelToControlListener is null");
		Objects.requireNonNull(onPersistListener, "onPersistListener null");
		Objects.requireNonNull(onRefreshListener, "onRefreshListener null");

		if (table.getColumns().size() == 0) {
			throw new IllegalArgumentException("tabela não inicializada");
		}

		if (controls.size() == 0) {
			throw new IllegalArgumentException("forma não inicializado");
		}

	}

	/**********************************************************************************************************/
	public void setOnControlToModelListener(OnControlToModelListener<T> onControlToModelListener) {
		this.onControlToModelListener = onControlToModelListener;
	}

	/**********************************************************************************************************/
	public void setOnModelToControlListener(OnModelToControlListener<T> onModelToControlListener) {
		this.onModelToControlListener = onModelToControlListener;
	}

	/**********************************************************************************************************/
	public void setOnRefreshListener(OnRefreshListener onRefreshListener) {
		this.onRefreshListener = onRefreshListener;
	}

	/**********************************************************************************************************/
	public void setOnPersistListener(OnPersistListener<T> onPersistListener) {
		this.onPersistListener = onPersistListener;

	}

	/**********************************************************************************************************/
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
		case PERSISTING:
			break;
		default:
			break;

		}

		btnForm.setDisable(btnSave.isDisable());
		customButtons.forEach(btn -> btn.setDisable(btnSave.isDisable()));

	}

	/**********************************************************************************************************/
	public T getSelectedItem() {
		return selectedItem;
	}

	public void setContent(Node content) {
		this.itemContent.setContent(content);

		if (!(content instanceof FormItemControl)) {
			btnNew.setDisable(true);
			btnSave.setDisable(true);
			btnRemove.setDisable(true);
			btnCancel.setDisable(true);
		}

	}

	public void addButton(Button button) {
		button.getStyleClass().add("btn");
		button.getStyleClass().add("btn-default");
		button.getStyleClass().add("btn-sm");
		toolbarAction.getItems().add(button);
		customButtons.add(button);

		btnForm.setVisible(true);

	}

	/**********************************************************************************************************/
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

		if (onSelectListeners != null)
			onSelectListeners.forEach(listener -> listener.notify(data));

	}

	public void registerOnSelectListener(OnSelectListener<T> listener) {
		if (onSelectListeners == null)
			onSelectListeners = new ArrayList<OnSelectListener<T>>();
		onSelectListeners.add(listener);
	}

}
