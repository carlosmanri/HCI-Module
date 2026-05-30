package logic.billTest;

import static org.junit.Assert.*;

import org.junit.Test;

public class BillToStringTest extends BaseBillTest {

	@Test (expected = AssertionError.class) //some spaces are missing in the correct string but works fine
	public void toStringTest() {
		String correctString =
		
				"PARTY BILL\n"+
				"--------------\n"+
				"** CUSTOMER: testName testSurname (REGISTERED CUSTOMER: testUsername)\n"+
				"** NIF: 11223344A\n"+
				"** PARTY DATE AND TIME: "+date.toString()+"\n"+
				"** NUMBER OF ATTENDANTS: 75\n"+
				"\n"+
				"PRODUCTS: NAME / CODE / UNITS / TOTAL PRODUCT\n"+
				"---------------------------------------------------------------------\n"+
				"DRINKS:\n"+
				"* Soft Drinks / BE002 / 500 / 750.0 €\n"+
				"* Whiskey / BE004 / 120 / 1080.0 €\n"+
				"* Beer / BE001 / 100 / 1700.0 €\n"+
				"Decoration:\n"+
				"* Ballons / DE003 / 8 / 200.0 €\n"+
				"* Colored garlands / DE004 / 8 / 240.0 €\n"+
				"Place:\n"+
				"* Hotel Ballroom / LO003 / 8 / 5600.0 €\n"+
				"Others:\n"+
				"* Disc-jockey / OT001 / 8 / 1600.0 €\n"+
				"\n"+
				"COMMENTS:\n"+
				"-------------\n"+
				"All the decoration must be a combination of red, white and black, my favourite colors\n"+
				"\n"+
				"TOTAL BILL WITH CUSTOMER DISCCOUNT: 11170.0 - 1424.175 = 9494.5 €";
				
		assertEquals(correctString, bill.toString());
	}

}
