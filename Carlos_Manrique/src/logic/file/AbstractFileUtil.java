package logic.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.LinkedList;
import java.util.List;



public abstract class AbstractFileUtil {

	public AbstractFileUtil() {
		super();
	}

	protected abstract BufferedWriter createWriter(String outFileName);

	protected abstract BufferedReader createReader(String inFileName) throws FileNotFoundException;

	public List<String> loadLines(String inFileName) throws FileNotFoundException {
		List<String> res = new LinkedList<>();

		BufferedReader in = createReader(inFileName);
		String line;
		try {
			try {
				while ((line = in.readLine()) != null) {
					res.add(line);
				}
			} finally {
				in.close();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return res;
	}

	public void saveToFile(String outFileName, List<String> lines) {

		BufferedWriter out = createWriter(outFileName);
		try {
			try {
				for (String line : lines) {
					out.write(line);
					out.newLine();
				}
			} finally {
				out.close();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void saveToFile(String outFileName, String line) {
	    try {
			Files.write(Paths.get(outFileName), line.getBytes(), StandardOpenOption.APPEND);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	    

	}
}