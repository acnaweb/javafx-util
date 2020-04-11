package util.javafx.customer;

import java.util.function.Supplier;

public class CustomerSupplier implements Supplier<Customer> {

	@Override
	public Customer get() {
		return new Customer();
	}

}
