package util.javafx.model;

import javafx.scene.control.TableView;
import util.javafx.table.TableBuilder;
import util.javafx.table.TableColumnBuilder;

public class CustomerTable implements TableBuilder<Customer> {

	@Override
	public void build(TableView<Customer> table) {
		table.setEditable(false);
		TableColumnBuilder.<Customer, Integer>build(table, "ID", "id", 75, TableColumnBuilder.CellType.INTEGER);
		TableColumnBuilder.<Customer, Integer>build(table, "Nome", "name", 200, TableColumnBuilder.CellType.TEXTFIELD);
	}

}
