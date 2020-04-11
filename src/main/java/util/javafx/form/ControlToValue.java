package util.javafx.form;

import java.util.Map;

import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextInputControl;

public class ControlToValue {

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

}
