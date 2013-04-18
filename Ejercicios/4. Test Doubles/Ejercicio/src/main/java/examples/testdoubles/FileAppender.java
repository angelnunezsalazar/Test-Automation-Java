package examples.testdoubles;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class FileAppender {

	public void write(String message) throws Exception {
		FileWriter filestream = new FileWriter("log.txt", true);
		BufferedWriter out = new BufferedWriter(filestream);
		out.write(message);
		out.close();
	}
}
