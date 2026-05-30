package logic.billTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import logic.ApplicationException;
import logic.bill.Bill;
import logic.product.Product;

public class BaseBillTest {

	Bill bill;
	Date date;
	Map<Product, Integer> cart;

	Product p1;
	Product p2;
	Product p3;
	Product p4;
	Product p5;
	Product p6;
	Product p7;


	@Before
	public void setup() throws ApplicationException {

		p1 = new Product("BE001", "Drink", "Beer", "description", 17, 0);
		p2 = new Product("BE002", "Drink", "Soft Drinks", "description", 1.5f, 0);
		p3 = new Product("BE004", "Drink", "Whiskey", "description", 9, 0);
		p4 = new Product("DE003", "Decoration", "Ballons", "description", 0, 25);
		p5 = new Product("DE004", "Decoration", "Colored garlands", "description", 0, 30);
		p6 = new Product("LO003", "Place", "Hotel Ballroom", "description", 0, 700);
		p7 = new Product("OT001", "Otros", "Disc-jockey", "description", 0, 200);
		
		cart = new HashMap<Product, Integer>();
		cart.put(p1, 100);
		cart.put(p2, 500);
		cart.put(p3, 120);
		cart.put(p4, 8);
		cart.put(p5, 8);
		cart.put(p6, 8);
		cart.put(p7, 8);

		date = new Date();

		bill = new Bill(true, "testUsername", "testName", "testSurname", "11223344A", date, 75,
				"All the decoration must be a combination of red, white and black, my favourite colors", cart);
	}

}
