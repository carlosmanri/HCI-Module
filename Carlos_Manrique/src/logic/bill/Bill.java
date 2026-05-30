package logic.bill;

import java.security.InvalidParameterException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import logic.ApplicationException;
import logic.product.Product;

/**
 * This class models a bill, it stores the customer data and the ordermi amo
 * @author Carlos Manrique
 *
 */
public class Bill {

	private static final float DISCOUNT = 15;

	private boolean isLoged;
	private String username;
	private String name;
	private String surname;
	private String nif;
	private Date date;
	private int numberAttendants;
	private String comments;
	private float totalPrice;
	private String billFormated;

	private Map<Product, Integer> cart;
	
	
	public Bill(boolean isLoged, String username, String name, String surname, String nif, Date date,
			int numberAttendants, String comments, Map<Product, Integer> cart) {
		super();
		this.isLoged = isLoged;
		this.username = username;
		this.name = name;
		this.surname = surname;
		this.nif = nif;
		this.date = date;
		this.numberAttendants = numberAttendants;
		this.comments = comments;
		this.cart = cart;
	}
	
	public Bill() {
		this.isLoged = false;
		this.username = "";
		this.name = "";
		this.surname = "";
		this.nif = "";
		this.date = null;
		this.numberAttendants = 1;
		this.comments = "";
		this.cart = new HashMap<Product, Integer>();
		this.totalPrice = 0;
	}

	/**
	 * Prints the receipt in the desired format
	 */
	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = sdf.format(date);
		
		
		billFormated = "";
		billFormated =  "PARTY BILL\n"+
				"--------------\n"+
				"** CUSTOMER: "+getName() + " " +getSurname();
		if(isLoged) {
			billFormated += " "+ "(REGISTERED CUSTOMER: " + getUsername()+")";
		}
		billFormated += "\n** NIF: " + getNif() + "\n"+
				"** PARTY DATE AND TIME: " + dateStr+"\n"+
				"** NUMBER OF ATTENDANTS: " + getNumberAttendants()+"\n\n"+
				"PRODUCTS: NAME / CODE / UNITS / TOTAL PRODUCT\n"+
				"---------------------------------------------------------------------\n";
		
		if(cart.keySet().stream().anyMatch(x -> x.getCategory().equals(Product.DRINK.toString())))
			billFormated += "Drinks:\n";
		
		cart.forEach((p, q)-> {
			if(p.getCategory().equals(Product.DRINK.toString()))
				try {
					billFormated += "* " + p.getName() + " / "+ p.getCode() + " / "+ q +" / "+ p.getTotal(q, numberAttendants)+ " €\n";
				} catch (ApplicationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		});

		if(cart.keySet().stream().anyMatch(x -> x.getCategory().equals(Product.FOOD.toString())))
			billFormated += "Food:\n";
		
		cart.forEach((p, q)-> {
			if(p.getCategory().equals(Product.FOOD.toString()))
				try {
					billFormated += "* " + p.getName() + " / "+ p.getCode() + " / "+ q +" / "+ p.getTotal(q, numberAttendants)+ " €\n";
				} catch (ApplicationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		});
		if(cart.keySet().stream().anyMatch(x -> x.getCategory().equals(Product.DECORATION.toString())))
			billFormated += "Decoration:\n";
		
		cart.forEach((p, q)-> {
			if(p.getCategory().equals(Product.DECORATION.toString()))
				try {
					billFormated += "* " + p.getName() + " / "+ p.getCode() + " / "+ q +" / "+ p.getTotal(q, numberAttendants)+ " €\n";
				} catch (ApplicationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		});

		if(cart.keySet().stream().anyMatch(x -> x.getCategory().equals(Product.PLACE.toString())))
			billFormated += "Places:\n";
		
		cart.forEach((p, q)-> {
			if(p.getCategory().equals(Product.PLACE.toString()))
				try {
					billFormated += "* " + p.getName() + " / "+ p.getCode() + " / "+ q +" / "+ p.getTotal(q, numberAttendants)+ " €\n";
				} catch (ApplicationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		});

		if(cart.keySet().stream().anyMatch(x -> x.getCategory().equals(Product.OTHERS.toString())))
			billFormated += "Others:\n";
		
		cart.forEach((p, q)-> {
			if(p.getCategory().equals(Product.OTHERS.toString()))
				try {
					billFormated += "* " + p.getName() + " / "+ p.getCode() + " / "+ q +" / "+ p.getTotal(q, numberAttendants)+ " €\n";
				} catch (ApplicationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		});
		billFormated +="\n";
		billFormated += "COMMENTS:\n";
		billFormated+="-------------\n";
		billFormated += getComments()+"\n";
		billFormated += "\n";
		
		if(isLoged) {
			billFormated += "TOTAL BILL WITH CUSTOMER DISCCOUNT: "+ getTotalPriceWithoutDiscount()+" - "+getDiscount()+" = "+getTotalPrice()+" €\n" ;

		}
		else {
			billFormated += "TOTAL BILL: "+ getTotalPrice()+" €" ;
		}
		return billFormated;
				
	}




	public boolean isLoged() {
		return isLoged;
	}

	public void setLoged(boolean isLoged) {
		this.isLoged = isLoged;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if(name == null || name.length() == 0)
			throw new InvalidParameterException("Name must be filled");
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		if(surname == null || surname.length() == 0)
			throw new InvalidParameterException("Surname must be filled");
		this.surname = surname;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		if(nif == null || nif.length() == 0)
			throw new InvalidParameterException("NIF must be filled");
		this.nif = nif;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getNumberAttendants() {
		return numberAttendants;
	}

	public void setNumberAttendants(int numberAttendants) {
		if(numberAttendants <= 0)
			throw new InvalidParameterException("There must be more than 0 attendants");
		this.numberAttendants = numberAttendants;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		if(comments == null)
			throw new InvalidParameterException("The comment can not be null");
		this.comments = comments;
	}

	public Map<Product, Integer> getCart() {
		return cart;
	}

	public void setCart(Map<Product, Integer> cart) {
		this.cart = cart;
	}

	
	public float getTotalPrice()  {
		totalPrice = 0;
		cart.forEach((product,quantity) -> 	{
			try {
				totalPrice+=product.getTotal(quantity, numberAttendants);
			} catch (ApplicationException e) {
				e.printStackTrace();
			}
		});

		if(isLoged)
			return (float) (totalPrice * (100-DISCOUNT)/100.0);
		
		return totalPrice;
	}

	

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	
	private float getTotalPriceWithoutDiscount() {
		totalPrice = 0;
		cart.forEach((product,quantity) -> 	{
			try {
				totalPrice+=product.getTotal(quantity, numberAttendants);
			} catch (ApplicationException e) {
				e.printStackTrace();
			}
		});

		return totalPrice;
	}
	

	private float getDiscount() {
		return (float) (getTotalPrice() * (DISCOUNT)/100.0);
	}

	
	public void addProduct(Product product, int quantity) throws ApplicationException{
		if(quantity <= 0)
			throw new ApplicationException("Tried to add a product with a non valid quantity");
		if(product == null)
			throw new ApplicationException("Tried to add a null product");
		
		if(cart.containsKey(product)){
			cart.replace(product, cart.get(product)+quantity); //sum actual quantity and requested one to existing product
			return;
		}
		
		cart.put(product, quantity);
		
	}

	
	public int getNumberOfItems() {
		return cart.values().stream().mapToInt(Integer::intValue).sum();
	}

	public void removeAllProducts() {
		this.cart = new HashMap<Product, Integer>();

	}

	public void removeProduct(Product product) throws ApplicationException {		
		if(cart.remove(product) == null) 
			throw new ApplicationException("Tried to remove a product that does not exists in the cart");
		}
		


	public void changeItemQuantity(Product product, Integer quantity) throws ApplicationException {
		if(quantity <= 0)
			throw new ApplicationException("Quantity must be greater than zero");
		if(!cart.containsKey(product))
			throw new ApplicationException("Tried to modify quantity of a product that is not in the cart");
			
		cart.replace(product, quantity);

	}

	public Integer getQuantityOfProduct(Product product) throws ApplicationException {
		if(product == null)
			throw new ApplicationException("The product provided is null");
		if(!cart.containsKey(product))
			throw new ApplicationException("The product is not in the cart");
		
		return cart.get(product);
	}

}
