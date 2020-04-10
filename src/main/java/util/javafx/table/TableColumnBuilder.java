package util.javafx.table;

import java.util.Objects;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableColumnBuilder {

	public static <T, V> TableColumn<T, V> build(TableView<T> table, String header, String attribute, int percentWidth,
			CellType cellType) {
		Objects.requireNonNull(cellType);

		// Create column
		TableColumn<T, V> col = new TableColumn<T, V>(header);
		col.setMaxWidth(1f * percentWidth);
		col.setPrefWidth(1f * percentWidth);

		col.setCellValueFactory(new PropertyValueFactory<T, V>(attribute));

		if (cellType != null) {
			switch (cellType) {
			case CHECKBOX:
				col.setCellFactory(tc -> new CheckBoxTableCell<>());
				break;
			case CHOICEBOX:
				// col.setCellFactory(tc -> new ChoiceBoxTableCell<>());
			case ENUM:
				// col.setCellFactory(tc -> new ComboBoxTableCell<>());
			case PROGRESSBAR:
				// col.setCellFactory(tc -> new ProgressBarTableCell<Integer>());
			case TEXTFIELD:
				// col.setCellFactory(tc -> new TextFieldTableCell<>());
				break;
			default:
				// col.editableProperty().set(false);
				break;

			}
		}

		table.getColumns().add(col);

		return col;
	}

	public static enum CellType {
		TEXTFIELD, CHECKBOX, CHOICEBOX, ENUM, PROGRESSBAR, IMAGE, INTEGER
	}
}
