package util.javafx.table;

import java.io.IOException;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import util.javafx.form.function.OnRefreshListener;
import util.javafx.form.function.OnSelectListener;

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

	public void setOnSelectListener(OnSelectListener selectListener) {
		table.setOnMouseClicked(evt -> {
			T item = table.getSelectionModel().getSelectedItem();
			selectListener.notify(item);
		});
	}

	public void setOnRefreshListener(OnRefreshListener refreshListener) {
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
