package logic.parserTest;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import logic.ApplicationException;
import logic.parser.ProductParser;
import logic.product.Product;

public class ProductParserTest {

	List<Product> products;

	@Before
	public void Setup() throws ApplicationException {
		products = new ArrayList<Product>();
		
		products.add( new Product("code1", "Drink", "name1", "description1", 1, 1));
		products.add( new Product("code2", "Drink", "name2", "description2", 2, 2));
		products.add( new Product("code3", "Drink", "name3", "description3", 3, 3));	
	}

	@Test
	public void ValidTest() throws ApplicationException {
		List<String> lines;
		try {
			lines = new logic.file.FileUtil().loadLines("testFiles/party.txt");
			List<Product> p = new ProductParser().parse(lines);

		} catch (FileNotFoundException e) {
			throw new ApplicationException("The file does not exists", e);
		}
		
		
		
		
	}

	@Test 
	public void InvalidTest() throws ApplicationException {
		products = new ArrayList<Product>();
		products.add(new Product("code3", "Drink", "name3", "description3", 3, 3));	
		
		List<String> lines;
		try {
			lines = new logic.file.FileUtil().loadLines("testFiles/partyWrong.txt");
			List<Product> p = new ProductParser().parse(lines);
		} catch (FileNotFoundException e) {
			throw new ApplicationException("The file does not exists", e);
		}

	}

}
