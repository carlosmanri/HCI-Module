package logic.billTest;

import java.util.Date;
import java.util.HashMap;

import org.junit.Test;

import logic.bill.Bill;
import logic.product.Product;

public class BillConstructorTest extends BaseBillTest {

	@Test
	public void validConstructorTest() {
		new Bill(true, "username", "Jhon", "Doe", "11223344A", new Date(),
				100, "The comment", new HashMap<Product, Integer>());
		
	}

}
