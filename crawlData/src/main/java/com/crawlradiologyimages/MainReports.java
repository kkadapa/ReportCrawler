package com.crawlradiologyimages;

public class MainReports {

	public static void main(String[] args) {

		String pageOneURL = "http://radiology.casereports.net/index.php/rcr/issue/archive?issuesPage=1#issues";
		String pageTwoURL = "http://radiology.casereports.net/index.php/rcr/issue/archive?issuesPage=2#issues";

		ReportsArchive.getReportsArchiveDataFromURL(pageOneURL);
		ReportsArchive.getReportsArchiveDataFromURL(pageTwoURL);

	}
}
