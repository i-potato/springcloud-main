package org.yyf.springcloud.commons.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.UUID;

public class IdUtil {
	/**
	 * 按时间串生成ID
	 * @return
	 */
	public static String createId(){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
		LocalDateTime dateTime = LocalDateTime.now();
		String dateString = dateTime.format(formatter);
		return dateString+String.format("%0"+6+"d", new Random().nextInt(999999));
	}
	
	/**
	 * UUID
	 * @return
	 */
	public static String createUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	
}
