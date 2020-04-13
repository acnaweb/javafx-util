package util.javafx;

import java.util.Map;

import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextInputControl;

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

}
