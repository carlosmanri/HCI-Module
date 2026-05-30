package logic.parser;

import java.util.ArrayList;
import java.util.List;

import logic.ApplicationException;
import logic.product.Product;

public class ProductParser {

	private static final int PRODUCT_PARTS = 6;
	private static final String LINE_SEPARATOR = "@";


	public List<Product> parse(List<String> lines) {
		List<Product> res = new ArrayList<>();

		for (String line : lines) {
			if (line.length() == 0)
				continue; // If blank line ignore it
			try {
				Product v = parseLine(line);
				res.add(v);
			} catch (InvalidLineException e) {
				System.err.println("ParserError :: Invalid line: " + e.getMessage());
				//AppLog.logger.log("ParserError: " + e.getMessage());
			} catch (ApplicationException e) {
				System.err.println("ParserError :: Invalid product: " + e.getMessage());
			}
		}
		return res;
	}

	private Product parseLine(String line) throws InvalidLineException, ApplicationException {
		String[] parts = line.split(LINE_SEPARATOR);

		return createProduct(parts);

	}

	private Product createProduct(String[] parts) throws InvalidLineException, ApplicationException {
		checkFieldsNumber(PRODUCT_PARTS, parts);

		String code = parts[0];
		String category = parts[1];
		String name = parts[2];
		String description = parts[3];
		float unitPrice = toFloat(parts[4]);
		float groupPrice = toFloat(parts[5]);

		return new Product(code, category, name, description, unitPrice, groupPrice);
	}

	private float toFloat(String s) throws InvalidLineException {
		try {
			return Float.parseFloat(s);
		} catch (NumberFormatException e) {
			throw new InvalidLineException("Wrong integer value. ", e);
		}

	}

	private void checkFieldsNumber(int size, String[] parts) throws InvalidLineException {
		if (parts.length == size)
			return;
		throw new InvalidLineException("Wrong number of fields. ");
	}

}
