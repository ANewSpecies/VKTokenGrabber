package com.github.dedinc.vktokengrabber.utils;

public class Helper {

	public static VK getVK() {
		return new VK();
	}

	public static Downloader getDownloader() {
		return new Downloader();
	}

	public static Request getRequest() {
		return new Request();
	}

	public static Version getVersion() {
		return new Version();
	}
	
	public static Binaries getBinaries() {
		return new Binaries();
	}

	public static WebDriver getWebDriver() {
		return new WebDriver();
	}

	public static Zipper getZipper() {
		return new Zipper();
	}
	
	public static UserAgents getUserAgents() {
		return new UserAgents();
	}
	
	public static Handler getHander() {
		return new Handler();
	}
}
