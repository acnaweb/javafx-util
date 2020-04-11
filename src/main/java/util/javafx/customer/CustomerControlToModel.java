package util.javafx.customer;

import java.util.Map;

import javafx.scene.Node;
import util.javafx.form.ControlToValue;
import util.javafx.listener.OnControlToModelListener;

public class CustomerControlToModel implements OnControlToModelListener<Customer> {

	@Override
	public void bind(Map<String, Node> controls, Customer data) {
		data.setId((int) ControlToValue.getValueFromControl(controls, "id"));
		data.setName((String) ControlToValue.getValueFromControl(controls, "name"));
	}

}
