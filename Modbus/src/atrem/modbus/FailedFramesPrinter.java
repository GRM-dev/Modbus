package atrem.modbus;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FailedFramesPrinter {

	private PrintWriter logWriter;

	public FailedFramesPrinter() {

		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String date = dateFormat.format(calendar.getTime());
		String path = System.getProperty("user.home") + "/Desktop/";

		File log = new File(path + date + ".txt");

		try {
			logWriter = new PrintWriter(new FileWriter(log, true));
		} catch (IOException e) {
			e.printStackTrace();
		}
		logWriter.println("" + new Date());

	}

	public void writeToLog(String arg) {
		logWriter.println(arg);
	}
}
