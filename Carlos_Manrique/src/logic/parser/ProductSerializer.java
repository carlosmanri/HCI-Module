package logic.parser;

import java.util.LinkedList;
import java.util.List;

import logic.ApplicationException;
import logic.product.Product;


public class ProductSerializer {
	
	public List<String> serialize(List<Product> products) throws ApplicationException {
		List<String> res = new LinkedList<>();
		for(Product p: products) {
			if(p==null) {
				throw new ApplicationException("Error: Tried to serialize a null object. ");
			}
			res.add( serialize( p )  );
		}
		return res;
	}

	private String serialize(Product p) {
		return p.serialize();
	}
}
