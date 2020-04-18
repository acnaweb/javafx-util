package app.main.customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javafx.scene.Node;
import javafx.scene.control.Button;
import util.javafx.crud.Column;
import util.javafx.crud.Column.Scope;
import util.javafx.crud.ControlType;
import util.javafx.crud.CrudControl;
import util.javafx.crud.DialogUtils;
import util.javafx.crud.FormUtils;
import util.javafx.function.OnControlToModelListener;
import util.javafx.function.OnModelToControlListener;
import util.javafx.function.OnPersistListener;
import util.javafx.function.OnRefreshListener;

public class CustomerCrudFactory {

	private static CrudControl<Customer> crud;

	/****************************************************************************/
	public static CrudControl<Customer> getCrudControlCustomer(OnRefreshListener onRefreshListener,
			OnPersistListener<Customer> onPersistListener) {
		if (crud == null) {
			crud = new CrudControl<Customer>(new CustomerSupplier(), new CustomerValidator());

			final List<Column> columns = new ArrayList<Column>();
			columns.add(new Column("ID", "id", 75, ControlType.INTEGER, Scope.BOTH, null));
			columns.add(new Column("Nome", "name", 200, ControlType.TEXTFIELD, Scope.BOTH, null));

			crud.createTable(columns);
			crud.setOnControlToModelListener(onControlToModelListener);
			crud.setOnModelToControlListener(onModelToControlListener);
			crud.setOnRefreshListener(onRefreshListener);
			crud.setOnPersistListener(onPersistListener);
			crud.createForm(columns);

			Button btn1 = new Button("Ação 1");
			btn1.setOnAction(evt -> {
				crud.setContent(null);
				DialogUtils.showAlert("Ação 1");
			});
			crud.addButton(btn1);

			Button btn2 = new Button("Ação 2");
			btn2.setOnAction(evt -> {
				crud.setContent(null);
				DialogUtils.showAlert("Ação 2");
			});
			crud.addButton(btn2);

		}
		return crud;
	}

	/****************************************************************************/
	private static OnControlToModelListener<Customer> onControlToModelListener = new OnControlToModelListener<Customer>() {
		@Override
		public void bind(Map<String, Node> controls, Customer data) {
			data.setId((int) FormUtils.getValueFromControl(controls, "id"));
			data.setName((String) FormUtils.getValueFromControl(controls, "name"));
		}
	};

	/****************************************************************************/
	private static OnModelToControlListener<Customer> onModelToControlListener = new OnModelToControlListener<Customer>() {
		@Override
		public void bind(Map<String, Node> controls, Customer data) {
			FormUtils.setValueToControl(controls, "id", data.getId());
			FormUtils.setValueToControl(controls, "name", data.getName());
		}

	};

}
