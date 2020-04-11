package util.javafx.customer;

import java.util.Map;

import javafx.scene.Node;
import util.javafx.form.FormUtils;
import util.javafx.form.function.OnModelToControlListener;

public class CustomerModelToControl implements OnModelToControlListener<Customer> {

	@Override
	public void bind(Map<String, Node> controls, Customer data) {
		FormUtils.setValueToControl(controls, "id", data.getId());
		FormUtils.setValueToControl(controls, "name", data.getName());
	}

}
