package com.mele.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ele.me.api.Api;
import com.mele.Constants;
import com.mele.util.TextUtils;

/**
 * Servlet基类
 * 
 * @date 2016/6/14
 */
public class BaseServlet extends HttpServlet {

	/**   序列号 。*/
	private static final long serialVersionUID = 1L;

	/** 保存当前用户名 。 */
	protected String mCurUserName = Constants.DEFAULT_USER;

	/** 输出流。 */
	protected PrintWriter pw;

	/** Json数据。 */
	protected String jsondata;

	/** 调用函数判别 。 */
	protected String func;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setCharacterEncoding("utf-8");
		pw = response.getWriter();
		jsondata = "";
		func = request.getParameter("func");
		if (func == null) {
			jsondata = Api.Factory().response(400, "参数错误,请加上fun参数", false);
			pw.write(jsondata);
			pw.flush();
			pw.close();
			return;
		}

		// 判断登录用户名
		String mUserName = LoginMes.isLogin(request);
		if (TextUtils.isEmpty(mUserName)) {
			mCurUserName = Constants.DEFAULT_USER;
		} else {
			mCurUserName = mUserName;
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
