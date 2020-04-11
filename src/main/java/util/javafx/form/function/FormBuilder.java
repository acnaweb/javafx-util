package util.javafx.form.function;

import java.util.Map;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public interface FormBuilder {
	void build(GridPane gridControls, Map<String, Node> controls);
}
