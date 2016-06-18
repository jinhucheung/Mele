package com.mele.servlet;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mele.Constants;

/**
 * 核对是否已经登陆
 * 
 * @author Administrator
 *
 */
public class LoginMes {
	public static String isLogin(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies == null)
			return null;
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(Constants.COOKIE_KEY_USERNAME)) {
				return cookie.getValue();
			}
		}
		return null;
	}

	public static boolean logout(HttpServletRequest request,
			HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		if (cookies == null)
			return true;
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(Constants.COOKIE_KEY_USERNAME)) {
				cookie.setMaxAge(0);
				response.addCookie(cookie);
				return true;
			}
		}
		return false;
	}

}
