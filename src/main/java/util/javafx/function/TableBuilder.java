package util.javafx.function;

import javafx.scene.control.TableView;

public interface TableBuilder<T> {
	void build(TableView<T> table);
}
