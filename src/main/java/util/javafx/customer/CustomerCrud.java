package util.javafx.customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javafx.scene.Node;
import javafx.scene.control.TableView;
import util.javafx.FormUtils;
import util.javafx.crud.Column;
import util.javafx.crud.Column.Scope;
import util.javafx.crud.ControlType;
import util.javafx.function.OnControlToModelListener;
import util.javafx.function.OnModelToControlListener;
import util.javafx.function.TableBuilder;
import util.javafx.function.TableColumnBuilder;
import util.javafx.model.Customer;

public final class CustomerCrud {
	public static List<Column> columns = new ArrayList<Column>();
	static {
		columns.add(new Column("ID", "id", 75, ControlType.INTEGER, Scope.BOTH, null));
		columns.add(new Column("Nome", "name", 200, ControlType.TEXTFIELD, Scope.BOTH, null));
	}

	public static OnControlToModelListener<Customer> onControlToModelListener = new OnControlToModelListener<Customer>() {
		@Override
		public void bind(Map<String, Node> controls, Customer data) {
			data.setId((int) FormUtils.getValueFromControl(controls, "id"));
			data.setName((String) FormUtils.getValueFromControl(controls, "name"));
		}
	};

	public static OnModelToControlListener<Customer> onModelToControlListener = new OnModelToControlListener<Customer>() {
		@Override
		public void bind(Map<String, Node> controls, Customer data) {
			FormUtils.setValueToControl(controls, "id", data.getId());
			FormUtils.setValueToControl(controls, "name", data.getName());
		}

	};

	public static TableBuilder<Customer> tableBuilder = new TableBuilder<Customer>() {
		@Override
		public void build(List<Column> columns, TableView<Customer> table) {
			table.setEditable(false);

			columns.stream()
					.filter(c -> Column.Scope.BOTH.equals(c.getScope()) || Column.Scope.TABLE.equals(c.getScope()))
					.forEach(c -> TableColumnBuilder.build(table, c.getHeader(), c.getAttribute(), c.getPercentWidth(),
							c.getControlType()));
		}
	};

}
