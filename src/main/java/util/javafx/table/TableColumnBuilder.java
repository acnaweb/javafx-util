package util.javafx.table;

import java.util.Objects;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import util.javafx.form.ControlType;

public class TableColumnBuilder {

	public static <T, V> void build(TableView<T> table, String header, String attribute, int percentWidth,
			ControlType controlType) {
		Objects.requireNonNull(controlType);

		// Create column
		TableColumn<T, V> col = new TableColumn<T, V>(header);
		col.setMaxWidth(1f * percentWidth);
		col.setPrefWidth(1f * percentWidth);

		col.setCellValueFactory(new PropertyValueFactory<T, V>(attribute));

		if (controlType != null) {
			switch (controlType) {
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

	}

}
