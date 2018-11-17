package com.nvelickovic10.coduner.util;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public final class CSVUtils {

	public static final char SEPARATOR = ',';
	private PrintWriter out;

	public CSVUtils(String fileName) {
		try {
			//nodeLoadTestResults.csv
			out = new PrintWriter("src/test/resources/" + fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public void writeLine(String line) {
		out.println(line);
		out.flush();
	}

	public void saveFile() {
		out.close();
	}
}
