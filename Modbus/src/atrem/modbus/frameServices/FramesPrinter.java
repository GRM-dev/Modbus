package atrem.modbus.frameServices;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FramesPrinter {

	private PrintWriter noResponselogWriter;
	private PrintWriter pairedLogWriter;
	public static final String NEW_LINE = System.lineSeparator();
	private static final String NO_RESPONSE_FRAMES_FILE = "NoResponseFrames ";
	private static final String PAIRED_FRAMES_FILE = "PairedFrames ";

	public FramesPrinter() {

		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String date = dateFormat.format(calendar.getTime());
		String path = System.getProperty("user.home") + "/Desktop/";

		noResponselogWriter = createPrintWriter(date, path,
				NO_RESPONSE_FRAMES_FILE);
		pairedLogWriter = createPrintWriter(date, path, PAIRED_FRAMES_FILE);

	}

	private PrintWriter createPrintWriter(String date, String path,
			String fileName) {
		File log = new File(path + fileName + date + ".txt");
		PrintWriter printWriter = null;
		try {
			printWriter = new PrintWriter(new FileWriter(log, true));
		} catch (IOException e) {
			e.printStackTrace();
		}
		printWriter.println("" + new Date());
		return printWriter;
	}

	private void writeToLog(PrintWriter printWriter, String arg) {
		noResponselogWriter.println(arg);
		noResponselogWriter.flush();
	}

	public void saveNoResponseFrame(String frame) {
		writeToLog(noResponselogWriter, frame);

	}

	public void savePairedFrame(String frame) {
		writeToLog(pairedLogWriter, frame);

	}
}
