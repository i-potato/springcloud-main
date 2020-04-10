package org.yyf.springcloud.commons.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 参数工具类 主要处理String和八大基本类型 
 * 不包含commons.lang3中的已有的方法
 * @author yyf
 *
 */
public class ParameterUtil {
	/**
	 * string 取小数点后两位
	 * @param s
	 * @return
	 */
	public static String stringSplit(String s) {
		return String.format("%.2f", Double.valueOf(s));
	}
	
	/**
	 * double 取小数点后两位 四舍五入
	 * @param d
	 * @return
	 */
	public static Double doubleSplit(Double d) {
		return (double) Math.round(d * 100) / 100;
	}
	
	/**
	 * 返回str中匹配 正则表达式 的第一个匹配值
	 * @param str
	 * @param regex
	 * @return
	 */
	public static String getStrWithRegex(String str,String regex) {
		
		str = str.replaceAll("\\s", "");
		
		Pattern pattern=Pattern.compile(regex); 
		
		Matcher matcher=pattern.matcher(str); 
		
		while(matcher.find()) {
			str = matcher.group();
		}
		
		return str;
	}
	

	
	/**
	 * 驼峰转下划线
	 * @param str
	 * @return
	 */
	public static  String camelToUnderline(String str) {
	      Matcher matcher = Pattern.compile("[A-Z]").matcher(str);
	     StringBuffer sb = new StringBuffer();
	     while (matcher.find()) {
	          matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
	      }
	       matcher.appendTail(sb);
	     return sb.toString();
	 }
	
	/**
	 * 下划线转驼峰
	 * @param str
	 * @return
	 */
	public static  String underlineToCamel(String str){ 
        if (str==null||"".equals(str.trim())){  
            return "";  
        }  
        int len=str.length();  
        StringBuilder sb=new StringBuilder(len);  
        for (int i = 0; i < len; i++) {  
            char c = Character.toLowerCase(str.charAt(i));  
            if (c == '_'){  
               if (++i<len){  
                   sb.append(Character.toUpperCase(str.charAt(i)));  
               }  
            }else{  
                sb.append(c);  
            }  
        }  
        return sb.toString();  
    } 
	
}
