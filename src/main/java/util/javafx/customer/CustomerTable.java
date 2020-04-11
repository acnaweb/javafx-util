package util.javafx.customer;

import javafx.scene.control.TableView;
import util.javafx.form.ControlType;
import util.javafx.table.TableBuilder;
import util.javafx.table.TableColumnBuilder;

public class CustomerTable implements TableBuilder<Customer> {

	@Override
	public void build(TableView<Customer> table) {
		table.setEditable(false);
		TableColumnBuilder.<Customer, Integer>build(table, "ID", "id", 75, ControlType.INTEGER);
		TableColumnBuilder.<Customer, Integer>build(table, "Nome", "name", 200, ControlType.TEXTFIELD);
	}

}
