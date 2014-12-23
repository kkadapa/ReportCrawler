package com.crawlradiologyimages;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Test {

	// public static void main(String[] args) throws IOException {

	// Document doc = Jsoup.connect("http://en.wikipedia.org/").get();
	// Elements newsHeadlines = doc.select("#mp-itn b a");
	// for (Element e : newsHeadlines) {
	// System.out.println(e.text());
	// }
	// }

	public static final String webSiteURL = "http://radiology.casereports.net/index.php/rcr/article/view/955/1227";
	static String crawlStorageFolder = "/Users/karthik/Documents/workspace/imageData/Musculoskeletal/955/1227";

	public static void main(String[] args) {

		Document doc;
		try {
			doc = Jsoup.connect(webSiteURL).get();

			// Get all elements with img tag,

			Elements img = doc.getElementsByTag("img");

			for (Element element : img) {

				// For each element get the srs url
				String src = element.absUrl("src");

				System.out.println("Image Found!");
				System.out.println("src attribute is :" + src);
				getImages(src);

			}
		} catch (IOException e) {
			System.err.println("There was an error");
			e.printStackTrace();
			Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	private static void getImages(String src) throws IOException {

		// Extract the name of the image from the src attribute
		int indexname = src.lastIndexOf("/");

		if (indexname == src.length()) {
			src = src.substring(1, indexname);
		}

		indexname = src.lastIndexOf("/");
		String name = src.substring(indexname, src.length());
		System.out.println(name);

		// Open a URL Stream
		URL url = new URL(src);
		InputStream in = url.openStream();

		OutputStream out = new BufferedOutputStream(new FileOutputStream(
				crawlStorageFolder + name));

		for (int i; (i = in.read()) != -1;) {
			out.write(i);
		}
		out.close();
		in.close();

	}
}