package util.javafx.form;

import java.util.Map;

import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextInputControl;

public class ValueToControl {
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
}
