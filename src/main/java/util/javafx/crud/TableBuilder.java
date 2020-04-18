package util.javafx.crud;

import java.util.List;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableBuilder<T> {

	private <V> void buildColumn(TableView<T> table, Column column) {

		// Create column
		TableColumn<T, V> col = new TableColumn<T, V>(column.getHeader());
		col.setMaxWidth(1f * column.getPercentWidth());
		col.setPrefWidth(1f * column.getPercentWidth());

		col.setCellValueFactory(new PropertyValueFactory<T, V>(column.getAttribute()));

		if (column.getControlType() != null) {
			switch (column.getControlType()) {
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

	public void build(List<Column> columns, TableView<T> table) {
		table.setEditable(false);

		columns.stream().filter(c -> Column.Scope.BOTH.equals(c.getScope()) || Column.Scope.TABLE.equals(c.getScope()))
				.forEach(column -> buildColumn(table, column));
	}
}
