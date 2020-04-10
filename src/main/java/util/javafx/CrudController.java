package util.javafx;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import org.springframework.stereotype.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import net.footstats.javafx.table.AbstractTableManager;
import net.footstats.javafx.table.TableViewBuilder.CellType;
import net.footstats.spring.CustomJpaRepository;

@Controller
public abstract class CrudController<T> implements Initializable, Observer {
	private int gridRow = 0;

	private Button btnNew;
	private Button btnRefresh;
	private Button btnSave;
	private Button btnCancel;
	private Button btnRemove;

	private Label status;

	private GridPane gridControls;
	private Map<String, Node> controls = new HashMap<String, Node>();

	protected Stage stage;
	protected Scene scene;
	protected Node contentCrud;

	protected AbstractTableManager<T> tableManager;
	protected T selectedItem;

	protected CustomJpaRepository<T, ?> service;

	protected abstract T newInstanceItem();

	protected abstract void createTableManager();

	protected abstract void initListeners();

	protected abstract void createControls();

	protected abstract void bindModelToControl();

	protected abstract void bindControlToModel();

	@SuppressWarnings("rawtypes")
	protected <V, E extends Enum<E>> void addControl(String header, String attribute, int percentWidth,
			CellType cellType, boolean showInTable, boolean showInForm, Class<E> enumData) {

		Label label = new Label(header);
		label.getStyleClass().add("lbl");
		label.getStyleClass().add("lbl-default");

		controls.put(attribute + "_0", label);

		Node input = null;

		switch (cellType) {
		case ENUM:
			ObservableList<String> items = FXCollections.observableArrayList();
			for (Enum item : enumData.getEnumConstants()) {
				items.add(item.name());
			}
			input = new ComboBox<>(items);
			break;
		case CHECKBOX:
			input = new CheckBox();
			break;
		case INTEGER:
			input = new Spinner<Integer>(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE));

			break;
		case CHOICEBOX:

		case IMAGE:
		case PROGRESSBAR:
		case TEXTFIELD:
			input = new TextField();
			break;
		}
		if (showInForm == true) {
			gridControls.add(label, 0, gridRow);
			gridControls.add(input, 1, gridRow);
		}

		if (showInTable) {
			tableManager.<V>createColumn(header, attribute, percentWidth, cellType);
		}

		controls.put(attribute, input);

		gridRow++;

	}

	@SuppressWarnings("unchecked")
	protected Object getValueFromControl(String attribute) {
		if (controls.containsKey(attribute) && controls.get(attribute) != null) {
			if (controls.get(attribute) instanceof TextInputControl) {
				return ((TextInputControl) controls.get(attribute)).getText();
			} else if (controls.get(attribute) instanceof CheckBox) {
				return ((CheckBox) controls.get(attribute)).isSelected();
			} else if (controls.get(attribute) instanceof Spinner) {
				return ((Spinner<Integer>) controls.get(attribute)).getValue();
			} else if (controls.get(attribute) instanceof ComboBox) {
				return ((ComboBox<String>) controls.get(attribute)).getValue();
			}
		}
		return null;

	}

	@SuppressWarnings("unchecked")
	protected void setValueToControl(String attribute, Object value) {
		try {
			if (controls.containsKey(attribute) && value != null && controls.get(attribute) != null) {
				if (controls.get(attribute) instanceof TextInputControl) {
					((TextInputControl) controls.get(attribute)).setText(value.toString());
				} else if (controls.get(attribute) instanceof CheckBox) {
					((CheckBox) controls.get(attribute)).setSelected((boolean) value);
				} else if (controls.get(attribute) instanceof Spinner) {
					((Spinner<Integer>) controls.get(attribute)).getValueFactory().setValue((Integer) value);
				} else if (controls.get(attribute) instanceof ComboBox) {
					((ComboBox<String>) controls.get(attribute)).setValue(value.toString());
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage() + " " + attribute + "=" + value);
		}

	}

	@SuppressWarnings("unchecked")
	protected final void clearControls() {
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

	protected final void disableControls(boolean status) {
		controls.entrySet().stream().forEach(entry -> {
			if (entry.getValue() instanceof Node) {
				((Node) entry.getValue()).setDisable(status);
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public void update(Observable o, Object data) {
		updateControls(FormState.EDITING);
		selectedItem = (T) data;
		bindModelToControl();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		defaultListeners();
		initListeners();
		createTableManager();
		createControls();
		updateControls(FormState.IDLE);

	}

	private void defaultListeners() {

	}

	protected void showAlert(String msg) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Titulo");
		alert.setHeaderText("Cabeçalho");
		alert.setContentText(msg);
		alert.showAndWait();
	}

	protected void showAlertError(String msg) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Titulo");
		alert.setHeaderText("Cabeçalho");
		alert.setContentText(msg);
		alert.showAndWait().ifPresent(rs -> {
			if (rs == ButtonType.OK) {
				System.out.println("Pressed OK.");
			}
		});
	}

	protected void updateControls(FormState formState) {
		clearControls();

		switch (formState) {
		case IDLE:
			btnRefresh.setDisable(false);
			btnNew.setDisable(false);
			btnSave.setDisable(true);
			btnRemove.setDisable(true);
			btnCancel.setDisable(true);
			disableControls(true);
			break;
		case LOADING:
			btnRefresh.setDisable(true);
			btnNew.setDisable(true);
			btnSave.setDisable(true);
			btnRemove.setDisable(true);
			btnCancel.setDisable(true);
			disableControls(true);
			break;
		case EDITING:
			btnRefresh.setDisable(false);
			btnNew.setDisable(false);
			btnSave.setDisable(false);
			btnRemove.setDisable(false);
			btnCancel.setDisable(false);
			disableControls(false);
			break;
		}

	}

	public Node getContentCrud() {
		return contentCrud;
	}

	protected void setContentCrud(Node contentCrud) {
		this.contentCrud = contentCrud;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	public void setService(CustomJpaRepository<T, ?> service) {
		this.service = service;
	}

	public void setGridControls(GridPane gridControls) {
		this.gridControls = gridControls;
	}

	public void setStatus(Label label) {
		this.status = label;
	}

	protected void setNewAction(Button btn) {
		btnNew = btn;

		btnNew.setOnAction(evt -> {
			selectedItem = newInstanceItem();
			updateControls(FormState.EDITING);
		});
	}

	protected void setRemoveAction(Button btn) {
		btnRemove = btn;

		btnRemove.setOnAction(evt -> {
			service.delete(selectedItem);
			selectedItem = null;
			updateControls(FormState.IDLE);
			btnRefresh.fire();
		});
	}

	protected void setRefreshAction(Button btn) {
		btnRefresh = btn;
		btnRefresh.setOnAction(evt -> {
			Service<Void> serviceWork = new Service<Void>() {

				@Override
				protected Task<Void> createTask() {
					return new Task<Void>() {

						@Override
						protected Void call() throws Exception {
							updateControls(FormState.LOADING);

							selectedItem = null;
							List<T> data;
							tableManager.removeAllItems();
							try {
								data = (List<T>) service.findByIsDeletedFalse();

								tableManager.populate(data);
							} catch (Exception e) {
								e.printStackTrace();
								showAlertError("getItems" + e.getMessage());
							}

							updateControls(FormState.IDLE);
							return null;
						}
					};
				}
			};

			if (stage != null)
				stage.titleProperty().bind(serviceWork.titleProperty());

			if (status != null)
				status.textProperty().bind(serviceWork.messageProperty());

			serviceWork.setOnSucceeded(par1 -> {
				// System.out.println(Calendar.getInstance().toString());
			});

			serviceWork.setOnFailed(par1 -> {
				showAlertError(par1.getEventType().getName());

			});
			serviceWork.start();

		});
	}

	protected void setSaveAction(Button btn) {
		btnSave = btn;
		btnSave.setOnAction(evt -> {
			bindControlToModel();
			selectedItem = service.save(selectedItem);
			// tableManager.table.getSelectionModel().select(selectedItem);
			updateControls(FormState.IDLE);
			btnRefresh.fire();
		});
	}

	protected void setCancelAction(Button btn) {
		btnCancel = btn;
		btnCancel.setOnAction(evt -> {
			selectedItem = null;
			updateControls(FormState.IDLE);
		});
	}

	public Stage getStage() {
		return stage;
	}

	public Scene getScene() {
		return scene;
	}

	public enum FormState {
		IDLE, EDITING, LOADING
	}

}
