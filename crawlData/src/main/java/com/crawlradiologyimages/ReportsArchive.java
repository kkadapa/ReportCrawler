package com.crawlradiologyimages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ReportsArchive {

	public static void getReportsArchiveDataFromURL(String webSiteURL) {

		// List allDataList = new ArrayList();
		// List volumeList = new ArrayList();

		try {
			Document doc = Jsoup.connect(webSiteURL).get();

			Elements titles = doc.select("a");

			// print all titles in main page
			for (Element e : titles) {
				if (e.text().contains("Vol")) {
					System.out.println("Volume text: " + e.text());
				}
				// System.out.println("html: " + e.html());
			}

			Elements yearOfArchive = doc.select("h3");
			for (Element e : yearOfArchive) {
				System.out.println("yearOfArchive: " + e.text());
				// System.out.println("html: " + e.html());
			}

			// print all available links on page
			Elements getH4s = doc.select("h4");

			for (Element l : getH4s) {
				Elements links = l.select("a[href]");
				for (Element el : links) {
					System.out.println("link: " + el.attr("abs:href"));
				}
			}

		} catch (IOException e) {
			System.err.println("There was an error");
			e.printStackTrace();
			Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, e);
		}
		// return allDataList;
	}
}
