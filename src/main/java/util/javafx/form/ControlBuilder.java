package util.javafx.form;

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
import javafx.scene.layout.GridPane;

public class ControlBuilder {

	@SuppressWarnings("rawtypes")
	public static <V, E extends Enum<E>> void build(GridPane gridControls, Map<String, Node> controls, String header,
			String attribute, int percentWidth, ControlType controlType, Class<E> enumData) {

		Label label = new Label(header);
		label.getStyleClass().add("lbl");
		label.getStyleClass().add("lbl-default");

		controls.put(attribute + "_0", label);

		Node input = null;

		switch (controlType) {
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

		final int gridRow = controls.size();
		gridControls.add(label, 0, gridRow);
		gridControls.add(input, 1, gridRow);

		controls.put(attribute, input);

	}

}
