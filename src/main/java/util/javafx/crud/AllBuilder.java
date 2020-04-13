package util.javafx.crud;

import java.util.List;
import java.util.Map;

import javafx.scene.Node;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import util.javafx.function.FormBuilder;
import util.javafx.function.TableBuilder;
import util.javafx.function.TableColumnBuilder;
import util.javafx.model.Customer;

public class AllBuilder {

	public static FormBuilder formBuilder = new FormBuilder() {
		@Override
		public void build(List<Column> columns, GridPane gridControls, Map<String, Node> controls) {
			columns.stream()
					.filter(c -> Column.Scope.BOTH.equals(c.getScope()) || Column.Scope.FORM.equals(c.getScope()))
					.forEach(c -> ControlBuilder.build(gridControls, controls, c.getHeader(), c.getAttribute(),
							c.getPercentWidth(), c.getControlType(), c.getEnumData()));
		}

	};

}
