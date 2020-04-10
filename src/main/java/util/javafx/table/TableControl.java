package util.javafx.table;

import java.io.IOException;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

public class TableControl<T> extends VBox {
	private static final String FXML = "TableControl.fxml";

	@FXML
	private Button btnRefresh;

	@FXML
	private TableView<T> table;

	public TableControl() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXML));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

	public void createTable(TableBuilder<T> builder) {
		builder.build(table);
	}

	public void populate(List<T> data) {
		removeAll();
		table.getItems().addAll(FXCollections.observableArrayList(data));
	}

	public void init(SelectListener selectListener, RefreshListener refreshListener) {
		table.setOnMouseClicked(evt -> {
			T item = table.getSelectionModel().getSelectedItem();
			selectListener.notify(item);
		});

		btnRefresh.setOnAction(evt -> {
			refreshListener.refresh();
		});
	}

	public void addItem(T item) {
		table.getItems().add(item);
	}

	public void removeAll() {
		table.getItems().clear();

	}

}
