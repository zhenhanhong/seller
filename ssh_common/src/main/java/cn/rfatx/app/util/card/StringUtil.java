package cn.rfatx.app.util.card;

import java.util.regex.Pattern;

/**
 * 通用方法
 */
public class StringUtil {

	public static final org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(StringUtil.class);

	/*
	 * 判断是否为整数
	 * 
	 * @param str 传入的字符串
	 * 
	 * @return 是整数返回true,否则返回false
	 */
	public static boolean isInteger(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
		return pattern.matcher(str).matches();
	}

	/*
	 * 判断是否为浮点数，包括double和float
	 * 
	 * @param str 传入的字符串
	 * 
	 * @return 是浮点数返回true,否则返回false
	 */
	public static boolean isDouble(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
		return pattern.matcher(str).matches();
	}

	/**
	 * 左补0
	 * 
	 * @param s
	 * @param i
	 * @return
	 */
	public static String leftPad(String s, int i) {
		String s1 = "0000000000000000000000000000000";
		if (i > s.length()) {
			return s1.substring(0, i - s.length()) + s;
		}
		return (s);
	}

	/**
	 * 右补0
	 * 
	 * @param s
	 * @param i
	 * @return
	 */
	public static String rightPad0(String s, int i) {
		String s1 = "0000000000000000000000000000000";
		if (i > s.length()) {
			return s + s1.substring(0, i - s.length());
		}
		return (s);
	}

	/**
	 * 右补F
	 * 
	 * @param s
	 * @param i
	 * @return
	 */
	public static String rightPadF(String s, int i) {
		String s1 = "FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF";
		if (i > s.length()) {
			return s + s1.substring(0, i - s.length());
		}
		return (s);
	}

	/**
	 * 奇偶判断
	 * 
	 * @param i
	 * @return
	 */
	public static boolean isOdd(int i) {
		if ((i % 2) == 1)
			return (true);
		else
			return false;
	}

	/**
	 * @Description: (判断是否是字符)
	 * @Title: ifChar
	 * @param @param c
	 * @param @return
	 * @return Boolean 返回类型
	 */
	public static Boolean isChar(char c) {
		if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z'))
			return true;
		else
			return false;
	}

}
