package logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import logic.bill.Bill;
import logic.parser.ProductParser;
import logic.parser.UserAccountParser;
import logic.product.Product;
import logic.user.UserAccount;

public class PartyManager {

	public static final String DEFAULT_PRODUCT_FILENAME = "files/party.txt";
	public static final String DEFAULT_USER_FILENAME = "files/clientes.dat";

	
	List<Product> products;
	List<UserAccount> users;
	Bill bill;

	public PartyManager(){
		products = new ArrayList<Product>();
		users = new ArrayList<UserAccount>();
		bill = new Bill();
	}
	
	public void restart() throws ApplicationException {
		//loadProductsFile(DEFAULT_PRODUCT_FILENAME);
		//loadUsersFile(DEFAULT_USER_FILENAME);
		bill = new Bill();
	}
	
	public void init() throws ApplicationException {
		loadProductsFile(DEFAULT_PRODUCT_FILENAME);
		loadUsersFile(DEFAULT_USER_FILENAME);
		bill = new Bill();

		
	}
	
	public void newParty() {
		bill.setCart(new HashMap<Product, Integer>());
		bill.setComments("");
		bill.setNumberAttendants(1);
		bill.setTotalPrice(0);
		
	}
	
	
	public boolean validateUser(UserAccount user) {
		return users.stream().anyMatch(x-> x.equals(user));
	}

	public List<Product> getProducts() {
		return new ArrayList<Product>(products);
	}

	public List<UserAccount> getUsers() {
		return new ArrayList<UserAccount>(users);
	}

	public Bill getBill() {
		return bill;
	}
	
	private void loadProductsFile(String inFileName) throws ApplicationException {
		List<String> lines;
		try {
			lines = new logic.file.FileUtil().loadLines(inFileName);
		} catch (FileNotFoundException e) {
			throw new ApplicationException("The file does not exists", e);
		}
		List<Product> products = new ProductParser().parse(lines);
		this.products.addAll(products);
	}


	private void loadUsersFile(String inFileName) throws ApplicationException {
		List<String> lines;
		try {
			lines = new logic.file.FileUtil().loadLines(inFileName);
		} catch (FileNotFoundException e) {
			throw new ApplicationException("The file does not exists", e);
		}
		List<UserAccount> users = new UserAccountParser().parse(lines);
		this.users.addAll(users);
	}

	public boolean registerNewUser(UserAccount user) throws ApplicationException {
		if(users.stream().anyMatch(x -> x.getUsername().equals(user.getUsername()))) 
			throw new ApplicationException("The username already exists");
		
		users.add(user);
		bill.setLoged(true);
		bill.setUsername(user.getUsername());
		new logic.file.FileUtil().saveToFile(DEFAULT_USER_FILENAME, user.toString()+"\n");
		
		return true;
	}

	public void printReceipt() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			String dateString = sdf.format(this.getBill().getDate());
			File file = new File(bill.getName()+"_"+bill.getSurname()+"_"+dateString+".txt");
			Files.write(Paths.get(file.getPath()), bill.toString().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		String[] lines = bill.toString().split("\n");
//		
//		
//		List<String> strings = new ArrayList<String>();
//
//		for(String s : lines) {
//			strings.add(s);
//		}
//		
//		
//		new logic.file.FileUtil().saveToFile(bill.getName()+"_"+bill.getSurname()+bill.getDate(), strings);

	}

	
}
