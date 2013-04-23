package diapositivas.designfortesteability;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
@SuppressWarnings("unused")
public class NoTrabajoConstructor {

	public class HtmlDocument {
		
		private String html = "";
		
		public HtmlDocument(String url) throws Exception {
			URL urlObject = new URL(url);
			InputStream input = urlObject.openStream();
			InputStreamReader stream = new InputStreamReader(input);
			BufferedReader reader = new BufferedReader(stream);
			String inputLine;
			while ((inputLine = reader.readLine()) != null)
				html+=inputLine;
			reader.close();
		}
	}
}
