package util.javafx.table;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

public class TableControl<T> extends VBox {
	private static final String FXML = "TableControl.fxml";

	@FXML
	private TableView<?> table;

	private OnDataSelected<T> onDataSelected;

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

	public void init(OnDataSelected<T> onDataSelected) {
		this.onDataSelected = onDataSelected;
	}

}
