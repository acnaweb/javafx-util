package util.javafx.customer;

import java.util.Map;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import util.javafx.form.ControlType;
import util.javafx.form.FormControlBuilder;
import util.javafx.form.function.FormBuilder;

public class CustomerForm implements FormBuilder {
	@Override
	public void build(GridPane gridControls, Map<String, Node> controls) {
		FormControlBuilder.build(gridControls, controls, "ID", "id", 75, ControlType.INTEGER, null);
		FormControlBuilder.build(gridControls, controls, "Nome", "name", 200, ControlType.TEXTFIELD, null);
	}
}
