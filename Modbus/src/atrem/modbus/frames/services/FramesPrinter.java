package atrem.modbus.frames.services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class FramesPrinter {

	public static final String NEW_LINE = System.lineSeparator();
	private PrintWriter printWriter;

	public FramesPrinter(File file) {
		printWriter = createPrintWriter(file);
	}

	private PrintWriter createPrintWriter(File file) {
		File log = file;
		PrintWriter printWriter = null;
		try {
			printWriter = new PrintWriter(new FileWriter(log, true));
		} catch (IOException e) {
			e.printStackTrace();
		}
		printWriter.println("" + new Date());
		printWriter.flush();
		return printWriter;
	}

	public void writeToLog(String arg) {
		printWriter.println(arg);
		printWriter.flush();
	}

}
