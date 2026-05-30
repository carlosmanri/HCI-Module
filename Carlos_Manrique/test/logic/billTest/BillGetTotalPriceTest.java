package logic.billTest;

import static org.junit.Assert.*;

import org.junit.Test;

import logic.bill.Bill;

public class BillGetTotalPriceTest extends BaseBillTest {

	@Test
	public void getTotalPriceLoggedTest() {
		assertEquals(9494.5, bill.getTotalPrice(), 0.0);
	}

	@Test
	public void getTotalPriceNotLoggedTest() {
		bill.setLoged(false);
		assertEquals(11170 , bill.getTotalPrice(), 0.0);
	}
	
	@Test
	public void getTotalPriceEmptyTest() {
		bill = new Bill();
		assertEquals(0, bill.getTotalPrice(), 0.0);
	}
	
}
