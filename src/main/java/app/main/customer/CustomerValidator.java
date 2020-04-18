package app.main.customer;

import java.util.function.Predicate;

public class CustomerValidator implements Predicate<Customer> {

	@Override
	public boolean test(Customer t) {
		return t.getName() != null;
	}

}
