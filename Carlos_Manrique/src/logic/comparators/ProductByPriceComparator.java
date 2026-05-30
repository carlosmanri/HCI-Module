package logic.comparators;
import java.util.Comparator;

import logic.product.Product;

public class ProductByPriceComparator implements Comparator<Product>{

	@Override
	public int compare(Product s1, Product s2) {
		float res = s1.getUnitPrice() - s2.getUnitPrice();
		if (res==0) {
			return (int)(s1.getGroupPrice() - s2.getGroupPrice());
		}
		return (int)res;
	}

}
