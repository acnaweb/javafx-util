package util.javafx.customer;

import java.util.Map;

import javafx.scene.Node;
import util.javafx.form.ValueToControl;
import util.javafx.listener.OnValueToControlListener;

public class CustomerModelToControl implements OnValueToControlListener<Customer> {

	@Override
	public void bind(Map<String, Node> controls, Customer data) {
		ValueToControl.setValueToControl(controls, "id", data.getId());
		ValueToControl.setValueToControl(controls, "name", data.getName());
	}

}
