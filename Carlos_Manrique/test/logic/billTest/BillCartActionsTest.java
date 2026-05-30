package logic.billTest;

import static org.junit.Assert.*;

import org.junit.Test;

import logic.ApplicationException;
import logic.product.Product;

public class BillCartActionsTest extends BaseBillTest {

	@Test
	public void changeQuantityProductTest() throws ApplicationException {

		bill.changeItemQuantity(p1, 10);

		assertEquals(10, bill.getQuantityOfProduct(p1).intValue());
	}

	@Test(expected = ApplicationException.class)
	public void changeQuantityZeroProductTest() throws ApplicationException {
		bill.changeItemQuantity(p1, 0);
	}

	@Test(expected = ApplicationException.class)
	public void changeQuantityNegativeProductTest() throws ApplicationException {
		bill.changeItemQuantity(p1, -10);
	}
	
	@Test(expected = ApplicationException.class)
	public void changeQuantityNullProductTest() throws ApplicationException {
		bill.changeItemQuantity(null, 10);
	}
	@Test(expected = ApplicationException.class)
	public void changeQuantityNonExistingProductTest() throws ApplicationException {
		bill.changeItemQuantity(new Product("asdf", "Drink", "name", "desc", 12, 12), 10);
	}
	
	@Test
	public void addNonExistingProductTest() throws ApplicationException {
		Product p = new Product("code", "Drink", "name", "description", 1, 2);
		cart.put(p, 10);
		bill.addProduct(p, 10);

		assertEquals(cart, bill.getCart());
	}

	@Test
	public void addExistingProductTest() throws ApplicationException {
		cart.replace(p1, 110); // now there are 10 more

		bill.addProduct(p1, 10);
		assertEquals(cart, bill.getCart());

	}

	@Test(expected = ApplicationException.class)
	public void addNullProductTest() throws ApplicationException {
		bill.addProduct(null, 120);
	}

	@Test(expected = ApplicationException.class)
	public void addProductNegativeQuantityTest() throws ApplicationException {
		bill.addProduct(new Product("BE001", "Drink", "Beer", "description", 17, 0), -100);
	}

	@Test(expected = ApplicationException.class)
	public void addProductZeroQuantityTest() throws ApplicationException {
		bill.addProduct(new Product("BE001", "Drink", "Beer", "description", 17, 0), 0);
	}

	@Test
	public void removeProductTest() throws ApplicationException {
		bill.removeProduct(p1);
		assertEquals(cart, bill.getCart());
	}
	
	@Test (expected = ApplicationException.class)
	public void removeNonExistentProductTest() throws ApplicationException {
		bill.removeProduct(new Product("code", "Drink", "name", "description", 1, 2));
	}
	
	@Test (expected = ApplicationException.class)
	public void removeNullProductTest() throws ApplicationException {
		bill.removeProduct(null);
	}
	
	@Test
	public void getCartTest() {
		assertEquals(cart, bill.getCart());
	}

	@Test
	public void removeAllProductsTest() {
		bill.removeAllProducts();
		assertTrue(bill.getCart().isEmpty());
	}

}
