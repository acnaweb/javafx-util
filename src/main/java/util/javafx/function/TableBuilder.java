package util.javafx.function;

import java.util.List;

import javafx.scene.control.TableView;
import util.javafx.crud.Column;

public interface TableBuilder<T> {

	void build(List<Column> columns, TableView<T> table);
}
