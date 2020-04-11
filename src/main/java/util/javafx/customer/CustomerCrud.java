package util.javafx.customer;

import java.util.Map;

import javafx.scene.Node;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import util.javafx.form.ControlType;
import util.javafx.form.FormControlBuilder;
import util.javafx.form.FormUtils;
import util.javafx.form.function.FormBuilder;
import util.javafx.form.function.OnControlToModelListener;
import util.javafx.form.function.OnModelToControlListener;
import util.javafx.table.TableBuilder;
import util.javafx.table.TableColumnBuilder;

public class CustomerCrud {
	private static FormBuilder formBuilder = new FormBuilder() {
		@Override
		public void build(GridPane gridControls, Map<String, Node> controls) {
			FormControlBuilder.build(gridControls, controls, "ID", "id", 75, ControlType.INTEGER, null);
			FormControlBuilder.build(gridControls, controls, "Nome", "name", 200, ControlType.TEXTFIELD, null);
		}
	};

	private static OnControlToModelListener<Customer> onControlToModelListener = new OnControlToModelListener<Customer>() {
		@Override
		public void bind(Map<String, Node> controls, Customer data) {
			data.setId((int) FormUtils.getValueFromControl(controls, "id"));
			data.setName((String) FormUtils.getValueFromControl(controls, "name"));
		}
	};

	private static OnModelToControlListener<Customer> onModelToControlListener = new OnModelToControlListener<Customer>() {
		@Override
		public void bind(Map<String, Node> controls, Customer data) {
			FormUtils.setValueToControl(controls, "id", data.getId());
			FormUtils.setValueToControl(controls, "name", data.getName());
		}

	};

	private static TableBuilder<Customer> tableBuilder = new TableBuilder<Customer>() {
		@Override
		public void build(TableView<Customer> table) {
			table.setEditable(false);
			TableColumnBuilder.<Customer, Integer>build(table, "ID", "id", 75, ControlType.INTEGER);
			TableColumnBuilder.<Customer, Integer>build(table, "Nome", "name", 200, ControlType.TEXTFIELD);
		}
	};

}
