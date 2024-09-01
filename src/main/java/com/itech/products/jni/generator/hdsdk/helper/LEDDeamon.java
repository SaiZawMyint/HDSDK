package com.itech.products.jni.generator.hdsdk.helper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.itech.products.jni.generator.hdsdk.HDSDKiTech;

public class LEDDeamon {

	private final String ip; 
	private final LEDScreenManager screenManager;
	
	public LEDDeamon(String ip) {
		super();
		this.ip = ip;
		screenManager = new LEDScreenManager();
	}

	public void viewIn(String carNumber) {
		String font = "Arial";
		int green = HDSDKiTech.INSTANCE.Hd_GetColor(127,255,0);
		screenManager.addText(carNumber, font, green, 0, 8, 64, 20, 12);
		screenManager.addText(formatDate(), font, green, 0, 38, 64, 20, 10);
		screenManager.sendScreen(ip);
	}
	
	public void viewOut(String carNumber, String duration, String amount) {
		String font = "Arial";
		int red = HDSDKiTech.INSTANCE.Hd_GetColor(139, 0, 0);
		int green = HDSDKiTech.INSTANCE.Hd_GetColor(127,255,0);
		screenManager.addText(carNumber, font, green, 0, 7, 64, 20, 12);
		screenManager.addText(duration, font, green, 0, 25, 64, 20, 9);
		screenManager.addText(amount, font, red, 0, 45, 64, 15, 10);
		screenManager.sendScreen(ip);
	}
	
	public static String formatDate() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
		LocalDateTime now = LocalDateTime.now();
		return now.format(formatter);
	}
	
}
