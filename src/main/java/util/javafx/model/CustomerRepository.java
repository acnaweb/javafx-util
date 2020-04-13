package util.javafx.model;

import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {
	public static List<Customer> list() {
		List<Customer> result = new ArrayList<Customer>();
		Customer customer = new Customer ();
		customer.setId(1);
		customer.setName("Pelé");
		result.add(customer);
		
		customer = new Customer ();
		customer.setId(2);
		customer.setName("Maradona");
		result.add(customer);
		
		return result;
	}

}
