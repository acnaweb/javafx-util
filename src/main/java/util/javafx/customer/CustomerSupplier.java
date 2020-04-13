package util.javafx.customer;

import java.util.function.Supplier;

import util.javafx.model.Customer;

public class CustomerSupplier implements Supplier<Customer> {

	@Override
	public Customer get() {
		return new Customer();
	}

}
