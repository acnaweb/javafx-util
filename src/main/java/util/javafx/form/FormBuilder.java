package util.javafx.form;

import java.util.Map;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public interface FormBuilder {
	void build(GridPane gridControls, Map<String, Node> controls);
}
