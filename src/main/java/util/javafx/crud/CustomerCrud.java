package util.javafx.crud;

import java.util.Map;

import javafx.scene.Node;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import util.javafx.customer.Customer;
import util.javafx.form.ControlBuilder;
import util.javafx.form.ControlType;
import util.javafx.function.FormBuilder;
import util.javafx.function.OnControlToModelListener;
import util.javafx.function.OnModelToControlListener;
import util.javafx.function.TableBuilder;
import util.javafx.function.TableColumnBuilder;
import util.javafx.util.FormUtils;

public final class CustomerCrud {

	private static FormBuilder formBuilder = new FormBuilder() {
		@Override
		public void build(GridPane gridControls, Map<String, Node> controls) {
			ControlBuilder.build(gridControls, controls, "ID", "id", 75, ControlType.INTEGER, null);
			ControlBuilder.build(gridControls, controls, "Nome", "name", 200, ControlType.TEXTFIELD, null);
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

	public static FormBuilder getFormBuilder() {
		return formBuilder;
	}

	public static OnControlToModelListener<Customer> getOnControlToModelListener() {
		return onControlToModelListener;
	}

	public static OnModelToControlListener<Customer> getOnModelToControlListener() {
		return onModelToControlListener;
	}

	public static TableBuilder<Customer> getTableBuilder() {
		return tableBuilder;
	}

}
