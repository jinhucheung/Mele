package com.mele.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ele.me.api.Api;
import com.mele.Constants;
import com.mele.control.DatabaseManager;
import com.mele.dao.entity.User;
import com.mele.dao.impl.UserDao;
import com.mele.util.MD5Util;

@WebServlet("/UserSelvlet")
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public UserServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// response.setHeader("Access-Control-Allow-Origin", "*");
		// response.setCharacterEncoding("utf-8");
		// PrintWriter pw = response.getWriter();
		// String jsondata="";
		// String func=request.getParameter("func");
		// if(func==null){
		// jsondata= Api.Factory().response(400, "参数错误,请加上fun参数", false);
		// pw.write(jsondata);
		// pw.flush();
		// pw.close();
		// return;
		// }
		super.doGet(request, response);
		if (func.equals("login")) {
			jsondata = this.login(request, response);
		} else if (func.equals("isLogining")) {
			jsondata = this.isLogining(request);
		} else if (func.equals("addUser")) {
			jsondata = this.addUser(request, response);
		} else if (func.equals("deleteUser")) {
			jsondata = this.deleteUser(request);
		} else if (func.equals("deleteAllUser")) {
			jsondata = this.deleteAllUser();
		} else if (func.equals("updateUserTel")) {
			jsondata = this.updateUserTel(request);
		} else if (func.equals("updateUserHeadIc")) {
			jsondata = this.updateUserHeadIc(request);
		} else if (func.equals("updateUserLevel")) {
			jsondata = this.updateUserLevel(request);
		} else if (func.equals("updateUserValues")) {
			jsondata = this.updateUserValues(request);
		} else if (func.equals("fetchUser")) {
			jsondata = this.fetchUser(request);
		} else if (func.equals("fetchAllUser")) {
			jsondata = this.fetchAllUser();
		} else if (func.equals("fetchAllUserByLevel")) {
			jsondata = this.fetchAllUserByLevel(request);
		} else if (func.equals("fetchUserByStoreId")) {
			jsondata = this.fetchUserByStoreId(request);
		} else if (func.equals("getCurAccount")) {
			jsondata = this.getCurAccount(request);
		} else if(func.equals("logout")){
			jsondata=this.logout(request, response);
		}
		else {
			jsondata = Api.Factory().response(400, "参数错误", false);
		}
		pw.write(jsondata);
		pw.flush();
		pw.close();
		pw = null;
		func = "";
	}

	/**
	 * 是否已经登陆 UserServlet?func=isLogining
	 * 
	 * @param request
	 * @return
	 */
	private String isLogining(HttpServletRequest request) {
		if (isLogin(request)) {
			return Api.Factory().response(200, "已经登陆了", mCurUserName);
		}
		return Api.Factory().response(400, "尚未登陆", false);
	}

	private String login(HttpServletRequest request,
			HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if (username == null || password == null) {
			return Api.Factory().response(400, "参数错误,用户名,密码为空", false);
		}
		Connection mCon = DatabaseManager.getInstance().open(mCurUserName);
		UserDao mUserDao = new UserDao(mCon);
		User user = mUserDao.fetchUser(username);
		if (user == null) {
			return Api.Factory().response(400, "参数错误,没有该用户", false);
		}

		if (user.getPassword().equals(MD5Util.MD5(password))) {
			Cookie cookie = new Cookie(Constants.COOKIE_KEY_USERNAME, username);
			cookie.setMaxAge(24 * 60 * 60);
			response.addCookie(cookie);
			return Api.Factory().response(200, "登陆成功", true);

		} else {
			return Api.Factory().response(401, "登陆失败,请检查用户名与密码", false);
		}

	}

	/**
	 * 判断是否已经登陆
	 * 
	 * @param request
	 * @return
	 */
	private boolean isLogin(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies == null)
			return false;
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(Constants.COOKIE_KEY_USERNAME)) {
				return true;
			}
		}
		return false;
	}

	private String addUser(HttpServletRequest request,
			HttpServletResponse response) {
		String parm[] = { "account", "password", "tel", "email", "paypassword",
				"headic", "level" };
		String val[] = new String[7];
		for (int i = 0; i < parm.length; i++) {
			val[i] = request.getParameter(parm[i]);
			if (val[i] == null) {
				return Api.Factory().response(400, "参数错误leye", false);
			}
		}
		int val6 = -1000;
		try {
			val6 = Integer.parseInt(val[6]);
		} catch (NumberFormatException e) {
			return Api.Factory().response(400, "参数错误", false);
		}
		Connection mCon = DatabaseManager.getInstance().open(mCurUserName);
		UserDao mUserDao = new UserDao(mCon);
		User user = new User(val[0], MD5Util.MD5(val[1]), val[2], val[3],
				val[4], val[5], val6);
		boolean isAdd = false;
		try {
			isAdd = mUserDao.addUser(user);
		} catch (Exception e) {
			return Api.Factory().response(400, "参数错误", false);
		}
		if (isAdd) {
			// 登录当前注册的用户
			Cookie cookie = new Cookie(Constants.COOKIE_KEY_USERNAME, val[0]);
			cookie.setMaxAge(24 * 60 * 60);
			response.addCookie(cookie);
			mCurUserName = val[0];
			return Api.Factory().response(200, "添加成功", true);
		}
		return Api.Factory().response(401, "添加失败", false);
	}

	private String deleteUser(HttpServletRequest request) {
		String account = request.getParameter("account");
		Connection mCon = DatabaseManager.getInstance().open(mCurUserName);
		UserDao mUserDao = new UserDao(mCon);
		boolean isDel = false;
		try {
			isDel = mUserDao.deleteUser(account);
		} catch (Exception e) {
			return Api.Factory().response(400, "参数错误", false);
		}
		if (isDel) {
			return Api.Factory().response(200, "删除成功", true);
		}
		return Api.Factory().response(401, "删除失败", false);
	}

	private String deleteAllUser() {
		Connection mCon = DatabaseManager.getInstance().open(mCurUserName);
		UserDao mUserDao = new UserDao(mCon);
		boolean isDel = mUserDao.deleteAllUser();
		if (isDel) {
			return Api.Factory().response(200, "删除成功", true);
		}
		return Api.Factory().response(401, "删除失败", false);
	}

	private String updateUserTel(HttpServletRequest request) {
		String account = request.getParameter("account");
		String tel = request.getParameter("tel");
		Connection mCon = DatabaseManager.getInstance().open(mCurUserName);
		UserDao mUserDao = new UserDao(mCon);
		int isUp = -2000;
		try {
			isUp = mUserDao.updateUserTel(account, tel);
		} catch (Exception e) {
			return Api.Factory().response(400, "参数错误", false);
		}
		if (isUp > 0) {
			return Api.Factory().response(200, "更新成功", true);
		}
		return Api.Factory().response(401, "更新失败", false);
	}

	private String updateUserHeadIc(HttpServletRequest request) {
		String account = request.getParameter("account");
		String headIc = request.getParameter("headIc");
		Connection mCon = DatabaseManager.getInstance().open(mCurUserName);
		UserDao mUserDao = new UserDao(mCon);
		int isUp = -2000;
		try {
			isUp = mUserDao.updateUserHeadIc(account, headIc);
		} catch (Exception e) {
			return Api.Factory().response(400, "参数错误", false);
		}
		if (isUp > 0) {
			return Api.Factory().response(200, "更新成功", true);
		}
		return Api.Factory().response(401, "更新失败", false);

	}

	private String updateUserLevel(HttpServletRequest request) {
		String account = request.getParameter("account");
		String Level = request.getParameter("level");
		Connection mCon = DatabaseManager.getInstance().open(mCurUserName);
		UserDao mUserDao = new UserDao(mCon);
		int isUp = -2000;
		try {
			int level = Integer.parseInt(Level);
			isUp = mUserDao.updateUserLevel(account, level);
		} catch (Exception e) {
			return Api.Factory().response(400, "参数错误", false);
		}
		if (isUp > 0) {
			return Api.Factory().response(200, "更新成功", true);
		}
		return Api.Factory().response(401, "更新失败", false);

	}

	private String updateUserValues(HttpServletRequest request) {
		return null;
	}

	private String fetchAllUser() {
		Connection mCon = DatabaseManager.getInstance().open(mCurUserName);
		UserDao mUserDao = new UserDao(mCon);
		List<User> allUser = mUserDao.fetchAllUser();
		if (allUser != null) {
			return Api.Factory().response(200, "获取成功", allUser);
		}
		return Api.Factory().response(401, "获取失败", false);
	}

	private String fetchUser(HttpServletRequest request) {
		String account = request.getParameter("account");
		Connection mCon = DatabaseManager.getInstance().open(mCurUserName);
		UserDao mUserDao = new UserDao(mCon);
		User user;
		try {
			user = mUserDao.fetchUser(account);
		} catch (Exception e) {
			return Api.Factory().response(400, "参数错误", false);
		}

		if (user != null) {
			return Api.Factory().response(200, "获取成功", user);
		}
		return Api.Factory().response(401, "获取失败", false);
	}

	private String fetchAllUserByLevel(HttpServletRequest request) {
		Connection mCon = DatabaseManager.getInstance().open(mCurUserName);
		UserDao mUserDao = new UserDao(mCon);
		String Level = request.getParameter("Level");
		int level = -200;
		List<User> users;
		try {
			level = Integer.parseInt(Level);
			users = mUserDao.fetchAullUserByLevel(level);
		} catch (Exception e) {
			return Api.Factory().response(400, "参数错误", false);
		}
		if (users != null) {
			return Api.Factory().response(200, "获取成功", users);
		}

		return Api.Factory().response(401, "获取失败", false);
	}

	private String fetchUserByStoreId(HttpServletRequest request) {
		Connection mCon = DatabaseManager.getInstance().open(mCurUserName);
		UserDao mUserDao = new UserDao(mCon);
		String storeId = request.getParameter("storeId");
		long storeid = -200;
		User users;
		try {
			storeid = Long.parseLong(storeId);
			users = mUserDao.fetchUserByStoreId(storeid);
		} catch (Exception e) {
			return Api.Factory().response(400, "参数错误", false);
		}
		if (users != null) {
			return Api.Factory().response(200, "获取成功", users);
		}
		return Api.Factory().response(401, "获取失败", false);
	}

	private String getCurAccount(HttpServletRequest request) {
		String mCurAccount = LoginMes.isLogin(request);
		if (null == mCurAccount || "".equals(mCurAccount.trim())) {
			return Api.Factory().response(401, "获取失败", false);
		}

		Connection mCon = DatabaseManager.getInstance().open(mCurUserName);
		UserDao mUserDao = new UserDao(mCon);
		User user;
		try {
			user = mUserDao.fetchUser(mCurAccount);
		} catch (Exception e) {
			return Api.Factory().response(400, "参数错误", false);
		}
		if (user != null) {
			return Api.Factory().response(200, "获取成功", user);
		}
		return Api.Factory().response(401, "获取失败", false);
	}

	private String logout(HttpServletRequest request,
			HttpServletResponse response) {
		boolean isLogout = LoginMes.logout(request, response);
		if (isLogout) {
			return Api.Factory().response(200, "当前没登录用户或已经成功注销", true);
		} else {
			return Api.Factory().response(401, "注销失败", false);
		}
	}
}
