package logic.productTest;

import static org.junit.Assert.*;

import org.junit.Test;

import logic.ApplicationException;
import logic.product.Product;

public class ProductTest {

	@Test
	public void validProductTest() throws ApplicationException {
		Product p = new Product("code", "Drink", "name", "description", 1, 2);
		assertEquals("code", p.getCode());
		assertEquals("Drink", p.getCategory());
		assertEquals("name", p.getName());
		assertEquals("description", p.getDescription());
		assertEquals(1.0, p.getUnitPrice(), 0.0);
		assertEquals(2.0, p.getGroupPrice(), 0.0);
		assertEquals("code.jpg", p.getImageFileName());
	}
	
	@Test
	public void UnitPriceTest() throws ApplicationException {
		Product p = new Product("BE001","Drink","Beer","Dutch Beer in a 5 lt. barrel",17,0);
		assertEquals(1700, p.getTotal(100, 75),0.0);
	}
	
	@Test
	public void GroupPriceTest() throws ApplicationException {
		Product p = new Product("OT001","Otros","Disk-jockey","Yoi can specify the type of music you want in the booking",0,200);
		assertEquals(1600, p.getTotal(8, 75),0.0);
	}
	
	@Test (expected = ApplicationException.class)
	public void UnitPriceNegativeQuantityTest() throws ApplicationException {
		Product p = new Product("BE001","Drink","Beer","Dutch Beer in a 5 lt. barrel",17,0);
		p.getTotal(-1, 10);	
	}
	
	@Test (expected = ApplicationException.class)
	public void UnitPriceZeroQuantityTest() throws ApplicationException {
		Product p = new Product("BE001","Drink","Beer","Dutch Beer in a 5 lt. barrel",17,0);
		p.getTotal(0, 10);	
	}
	
	@Test (expected = ApplicationException.class)
	public void UnitPriceNegativePeopleTest() throws ApplicationException {
		Product p = new Product("BE001","Drink","Beer","Dutch Beer in a 5 lt. barrel",17,0);
		p.getTotal(1, -12);	
	}
	
	@Test (expected = ApplicationException.class)
	public void UnitPriceZeroPeopleTest() throws ApplicationException {
		Product p = new Product("BE001","Drink","Beer","Dutch Beer in a 5 lt. barrel",17,0);
		p.getTotal(1, 0);	
	}
	
	
	@Test(expected = ApplicationException.class)
	public void GroupPriceNegativeQuantityTest() throws ApplicationException {
		Product p = new Product("OT001","Otros","Disk-jockey","Yoi can specify the type of music you want in the booking",0,200);
		p.getTotal(-1, 10);
	}
	
	@Test(expected = ApplicationException.class)
	public void GroupPriceZeroQuantityTest() throws ApplicationException {
		Product p = new Product("OT001","Otros","Disk-jockey","Yoi can specify the type of music you want in the booking",0,200);
		p.getTotal(0, 10);
	}
	
	@Test(expected = ApplicationException.class)
	public void GroupPriceNegativePeopleTest() throws ApplicationException {
		Product p = new Product("OT001","Otros","Disk-jockey","Yoi can specify the type of music you want in the booking",0,200);
		p.getTotal(1, -10);
	}
	
	@Test(expected = ApplicationException.class)
	public void GroupPriceZeroPeopleTest() throws ApplicationException {
		Product p = new Product("OT001","Otros","Disk-jockey","Yoi can specify the type of music you want in the booking",0,200);
		p.getTotal(1, 0);
	}

	@Test
	public void serializeTest()throws ApplicationException  {
		Product p = new Product("code", "Drink", "name", "description", 1, 2);
		assertEquals("code@Drink@name@description@1.0@2.0", p.serialize());
	}
	
	@Test (expected = ApplicationException.class)
	public void invalidUnitPriceTest() throws ApplicationException {
		new Product("code", "Drink", "name", "description", -1, 2);
	}
	
	@Test (expected = ApplicationException.class)
	public void invalidGroupPriceTest()throws ApplicationException  {
		new Product("code", "Drink", "name", "description", 1, -2);
	}
	
	@Test (expected = ApplicationException.class)
	public void emptyNameTest()throws ApplicationException  {
		new Product("code", "Drink", "", "description", 1, 2);
	}
	
	@Test (expected = ApplicationException.class)
	public void nullNameTest()throws ApplicationException  {
		new Product("code", "Drink", null, "description", 1, 2);
	}
	
	@Test (expected = ApplicationException.class)
	public void emptyDescriptionTest() throws ApplicationException {
		new Product("code", "Drink", "name", "", 1, 2);
	}
		
	@Test (expected = ApplicationException.class)
	public void nullDescriptionTest() throws ApplicationException {
		new Product("code", "Drink", "name",null, 1, 2);
	}
	
	@Test (expected = ApplicationException.class)
	public void emptyCodeTest() throws ApplicationException {
		new Product("", "Drink", "name", "description", 1, 2);
	}
	
	@Test (expected = ApplicationException.class)
	public void nullCodeTest() throws ApplicationException {
		new Product(null, "Drink", "name", "description", 1, 2);
	}
	
	@Test (expected = ApplicationException.class)
	public void invalidCategoryTest() throws ApplicationException {
		new Product("code", "nonExistentCategory", "name", "description", 1, 2);
	}

}
