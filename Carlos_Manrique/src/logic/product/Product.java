package logic.product;

import java.io.Serializable;

import logic.ApplicationException;

public class Product implements Serializable {
	
	private static final long serialVersionUID = 5755369772837724242L;
	
	public static final String DRINK = "Drink";
	public static final String FOOD = "Food";
	public static final String DECORATION = "Decoration";
	public static final String PLACE = "Place";
	public static final String OTHERS = "Otros";

	private String code;
	private String category;
	private String name;
	private String description;
	private float unitPrice;
	private float groupPrice;

	
	public Product(String code, String category, String name, String description, float unitPrice, float groupPrice) throws ApplicationException {
		setCode(code);
		setCategory(category);
		setName(name);
		setDescription(description);
		setUnitPrice(unitPrice);
		setGroupPrice(groupPrice);
	}
	
	

	@Override
	public String toString() {
		return getCode()+
				"@"+getCategory()+
				"@"+getName()+
				"@"+getDescription()+
				"@"+getUnitPrice()+
				"@"+getGroupPrice();
	}



	public String getCode() {
		return code;
	}

	private void setCode(String code) throws ApplicationException {
		if(code == null || code.length() == 0)
			throw new ApplicationException("The code of the product must be filled!");
		this.code = code;
	}

	public String getCategory() {
		return category;
	}

	private void setCategory(String category) throws ApplicationException {
		if(!category.equals(DRINK) &&
			!category.equals(FOOD) &&
			!category.equals(DECORATION) &&
			!category.equals(OTHERS) &&
			!category.equals(PLACE))
			throw new ApplicationException("The category of the product is not valid");
		this.category = category;
	}

	public String getName() {
		return name;
	}

	private void setName(String name) throws ApplicationException {
		if(name == null || name.length() == 0)
			throw new ApplicationException("The name of the product must be filled!");
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	private void setDescription(String description) throws ApplicationException {
		if(description == null || description.length() == 0)
			throw new ApplicationException("The description of the product must be filled!");
		this.description = description;
	}

	public float getUnitPrice() {
		return unitPrice;
	}

	private void setUnitPrice(float unitPrice) throws ApplicationException {
		if(unitPrice<0)
			throw new ApplicationException("The unit price of the product can not be negative");
		this.unitPrice = unitPrice;
	}

	public float getGroupPrice() {
		return groupPrice;
	}

	private void setGroupPrice(float groupPrice) throws ApplicationException {
		if(groupPrice<0)
			throw new ApplicationException("The group price of the product can not be negative");
		this.groupPrice = groupPrice;
	}

	public String getImageFileName() {
		return getCode() + ".jpg";
	}



	public String serialize() {
		return toString();
	}
	
	/**
	 * Returns the total if is product has unit Price, otherwise -1 and must be calculated from the bill
	 * @return
	 * @throws ApplicationException 
	 */
	public float getTotal(int quantity, int people) throws ApplicationException {
		if(quantity <= 0) 
			throw new ApplicationException("The quantity must be greater than 0");
		if(people <= 0) 
			throw new ApplicationException("The people that attend to the party must be greater than 0");
		
		if(getGroupPrice()==0) {
			return getUnitPrice()*quantity;
		}
		else {
			int groupsOfTen = people/10;
			if(people%10!=0) {
				groupsOfTen++;
			}
			return getGroupPrice()*groupsOfTen;
		}
		
	}



}
