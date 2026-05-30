package logic.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * A utility class to read/write text lines from/to a text file
 */
public class FileUtil extends AbstractFileUtil {

	@Override
	protected BufferedReader createReader(String inFileName) throws FileNotFoundException {
		return new BufferedReader(new FileReader(inFileName));
	}

	@Override
	protected BufferedWriter createWriter(String outFileName) {
		try {
			return new BufferedWriter(new FileWriter(outFileName));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
