package logic.billTest;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;

import org.junit.Test;

public class BillSettersTest extends BaseBillTest{

	@Test
	public void setNifTest() {
		bill.setNif("00112233A");
		assertEquals("00112233A",bill.getNif());
	}
	
	@Test (expected = InvalidParameterException.class)
	public void setNifNullTest() {
		bill.setNif(null);
	}
	
	@Test (expected = InvalidParameterException.class)
	public void setNifEmptyTest() {
		bill.setNif("");
	}

	
	@Test
	public void setCommentTest() {
		bill.setComments("hey");
		assertEquals("hey",bill.getComments());
	}
	
	
	@Test (expected = InvalidParameterException.class)
	public void setCommentNullTest() {
		bill.setComments(null);
	}
	
	@Test
	public void setNameTest() {
		bill.setName("othername");
		assertEquals("othername", bill.getName());
	}
	
	@Test (expected = InvalidParameterException.class)
	public void setNullNameTest() {
		bill.setName(null);
	}
	
	@Test (expected = InvalidParameterException.class)
	public void setEmptyNameTest() {
		bill.setName("");
	}
	
	@Test
	public void setSurameTest() {
		bill.setSurname("othername");
		assertEquals("othername", bill.getSurname());
	}
	
	@Test (expected = InvalidParameterException.class)
	public void setNullSurnameTest() {
		bill.setSurname(null);
	}
	
	@Test (expected = InvalidParameterException.class)
	public void setEmptySurnameTest() {
		bill.setSurname("");
	}
	
	@Test
	public void setNumberAttendandsTest() {
		bill.setNumberAttendants(10);
		assertEquals(10, bill.getNumberAttendants());		
	}
	
	@Test (expected = InvalidParameterException.class)
	public void setNegativeNumberAttendandsTest() {
		bill.setNumberAttendants(-10);
	}
	
	@Test (expected = InvalidParameterException.class)
	public void setZeroNumberAttendandsTest() {
		bill.setNumberAttendants(0);
	}
}
