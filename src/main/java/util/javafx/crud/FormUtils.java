package util.javafx.crud;

import java.util.List;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.GridPane;

public class FormUtils {

	@SuppressWarnings("unchecked")
	public static Object getValueFromControl(Map<String, Node> controls, String attribute) {
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
	public static void setValueToControl(Map<String, Node> controls, String attribute, Object value) {
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
	public static void clearControls(Map<String, Node> controls) {
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

	public static void disableControls(Map<String, Node> controls, boolean status) {
		controls.entrySet().stream().forEach(entry -> {
			if (entry.getValue() instanceof Node) {
				((Node) entry.getValue()).setDisable(status);
			}
		});
	}

	public static void build(List<Column> columns, GridPane gridControls, Map<String, Node> controls) {
		columns.stream().filter(c -> Column.Scope.BOTH.equals(c.getScope()) || Column.Scope.FORM.equals(c.getScope()))
				.forEach(column -> buildColumn(gridControls, controls, column));
	}

	private static <V> void buildColumn(GridPane gridControls, Map<String, Node> controls, Column column) {

		Label label = new Label(column.getHeader());
		label.getStyleClass().add("lbl");
		label.getStyleClass().add("lbl-default");

		controls.put(column.getAttribute() + "_0", label);

		Node input = null;

		switch (column.getControlType()) {
		case ENUM:
			ObservableList<String> items = FXCollections.observableArrayList();

//			for (Enum item : enumData.getEnumConstants()) {
//				items.add(item.name());
//			}
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

		final int gridRow = controls.size();
		gridControls.add(label, 0, gridRow);
		gridControls.add(input, 1, gridRow);

		controls.put(column.getAttribute(), input);

	}
}
