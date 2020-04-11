package util.javafx.customer;

import java.util.Map;

import javafx.scene.Node;
import util.javafx.form.FormUtils;
import util.javafx.form.function.OnControlToModelListener;

public class CustomerControlToModel implements OnControlToModelListener<Customer> {

	@Override
	public void bind(Map<String, Node> controls, Customer data) {
		data.setId((int) FormUtils.getValueFromControl(controls, "id"));
		data.setName((String) FormUtils.getValueFromControl(controls, "name"));
	}

}
