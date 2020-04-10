package org.yyf.springcloud.commons.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 日期处理工具 主要使用LocalDateTime 和 LocalDate
 * 依赖JDK8
 * @author yyf
 * @date 2019年8月29日
 */
public class DateUtil {
	
	
	/**
	 * 获取当天时间字符串  
	 * @param format  字符串格式 例：yyyyMMdd HHmmss
	 * @return
	 */
	public static String getNowTime(String format) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		LocalDateTime dateTime = LocalDateTime.now();
		String dateString = dateTime.format(formatter);
		return dateString;
	}
	
	/**
	 * 获取昨天的日期字符串
	 * @param format 字符串格式 例：yyyyMMdd HHmmss
	 * @return
	 */
	public static String getYesterday(String format) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		LocalDateTime dateTime = LocalDateTime.now().minusDays(1);
		String dateString = dateTime.format(formatter);
		return dateString;
	}
	
	/**
	 * 获取七天前的日期字符串 
	 * @param format 字符串格式 例：yyyyMMdd HHmmss
	 * @return
	 */
	public static String getPreWeekDay(String format) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		LocalDateTime dateTime = LocalDateTime.now().minusDays(7);
		String dateString = dateTime.format(formatter);
		return dateString;
	}
	
	
}
