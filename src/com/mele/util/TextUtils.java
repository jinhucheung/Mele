package com.mele.util;

public class TextUtils {
	private static final String TAG = "TextUtils";

	/**
	 * 判断空字符串
	 */
	public static boolean isEmpty(CharSequence str) {
		if (str == null || str.length() == 0)
			return true;
		else
			return false;
	}
}
