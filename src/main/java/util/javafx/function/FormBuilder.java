package util.javafx.function;

import java.util.List;
import java.util.Map;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import util.javafx.crud.Column;

public interface FormBuilder {
	void build(List<Column> columns, GridPane gridControls, Map<String, Node> controls);
}
