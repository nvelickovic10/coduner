package com.nvelickovic10.coduner.util;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public final class CSVUtils {

	public static final char SEPARATOR = ',';
	private static CSVUtils instance;
	private PrintWriter out;

	private CSVUtils() {
		try {
			out = new PrintWriter("src/test/resources/nodeLoadTestResults.csv");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public static final CSVUtils getInstance() {
		if (instance == null) {
			instance = new CSVUtils();
		}
		return instance;
	}

	public void writeLine(String line) {
		out.println(line);
		out.flush();
	}

	public void saveFile() {
		out.close();
	}
	
	public static void main(String[] args) {
		CSVUtils.getInstance().writeLine("waaa");
		CSVUtils.getInstance().saveFile();
	}
}
