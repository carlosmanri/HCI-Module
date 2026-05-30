package logic.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;

/**
 * A utility class to read/write text lines 
 * from/to a compressed text file (.txt.gz) 
 */
public class ZipFileUtil extends AbstractFileUtil {


	@Override
	protected BufferedReader createReader(String inFileName) throws FileNotFoundException {
		try{
			return new BufferedReader(
				new InputStreamReader(
						new GZIPInputStream(
								new FileInputStream(inFileName))));
		}catch(FileNotFoundException o){
			throw o;
		}
		catch(IOException e){
			throw new RuntimeException(e);
		}
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
