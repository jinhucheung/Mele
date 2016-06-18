package com.mele.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ele.me.api.Api;
import com.mele.control.DatabaseManager;
import com.mele.dao.entity.StoreInfoNType;
import com.mele.dao.impl.StoreInfoNTypeDao;

@WebServlet("/StoreInfoNTypeServlet")
public class StoreInfoNTypeServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public StoreInfoNTypeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// response.setHeader("Access-Control-Allow-Origin", "*");
		// response.setCharacterEncoding("utf-8");
		// PrintWriter pw = response.getWriter();
		// String jsondata = "";
		// String func = request.getParameter("func");
		// if (func == null) {
		// jsondata = Api.Factory().response(400, "参数错误,请加上fun参数", false);
		// pw.write(jsondata);
		// pw.flush();
		// pw.close();
		// return;
		// }
		super.doGet(request, response);
		// else
		if (func.equals("addStoreInfoNType")) {
			jsondata = this.addStoreInfoNType(request);
		} else if (func.equals("deleteStoreInfoNType")) {
			jsondata = this.deleteStoreInfoNType(request);
		} else if (func.equals("deleteAllStoreInfoNType")) {
			jsondata = this.deleteAllStoreInfoNType(request);
		} else if (func.equals("fetchAllStoreInfoNTypes")) {
			jsondata = this.fetchAllStoreInfoNTypes(request);
		} else if (func.equals("fetchAllStoreInfoNTypesByStoreId")) {
			jsondata = this.fetchAllStoreInfoNTypesByStoreId(request);
		} else if (func.equals("fetchAllStoreInfoNTypesByTypeId")) {
			jsondata = this.fetchAllStoreInfoNTypesByTypeId(request);
		} else if (func.equals("addStoreInfoNType")) {
			jsondata = this.addStoreInfoNType(request);
		} else {
			jsondata = Api.Factory().response(400, "参数错误", false);
		}
		pw.write(jsondata);
		pw.flush();
		pw.close();
		pw=null;
		func="";
	}

	private String addStoreInfoNType(HttpServletRequest request) {
		String storeId = request.getParameter("storeId");
		String stypeId = request.getParameter("stypeId");
		long storeid;
		int stypeid;
		try {
			storeid = Long.parseLong(storeId);
			stypeid = Integer.parseInt(stypeId);
		} catch (NumberFormatException e) {
			return Api.Factory().response(400, "参数错误", false);
		}

		Connection mCon = DatabaseManager.getInstance().open(mCurUserName);
		StoreInfoNTypeDao typeDao = new StoreInfoNTypeDao(mCon);
		StoreInfoNType infoNType = new StoreInfoNType(storeid, stypeid);
		try {
			if (typeDao.addStoreInfoNType(infoNType)) {
				return Api.Factory().response(200, "添加成功", true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Api.Factory().response(401, "添加失败", false);
	}

	private String deleteStoreInfoNType(HttpServletRequest request) {
		String storeId = request.getParameter("storeId");
		String stypeId = request.getParameter("stypeId");
		long storeid;
		int stypeid;
		try {
			storeid = Long.parseLong(storeId);
			stypeid = Integer.parseInt(stypeId);
		} catch (NumberFormatException e) {
			return Api.Factory().response(400, "参数错误", false);
		}
		Connection mCon = DatabaseManager.getInstance().open(mCurUserName);
		StoreInfoNTypeDao typeDao = new StoreInfoNTypeDao(mCon);
		StoreInfoNType infoNType = new StoreInfoNType(storeid, stypeid);
		try {
			if (typeDao.deleteStoreInfoNType(infoNType)) {
				return Api.Factory().response(200, "添加成功", true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Api.Factory().response(401, "添加失败", false);
	}

	private String deleteAllStoreInfoNType(HttpServletRequest request) {
		Connection mCon = DatabaseManager.getInstance().open(mCurUserName);
		StoreInfoNTypeDao typeDao = new StoreInfoNTypeDao(mCon);
		if (typeDao.deleteAllStoreInfoNType()) {
			return Api.Factory().response(200, "删除成功", true);
		} else {
			return Api.Factory().response(401, "删除成功", false);
		}
	}

	private String fetchAllStoreInfoNTypesByTypeId(HttpServletRequest request) {

		String stypeId = request.getParameter("storeId");

		int storeId;
		try {

			storeId = Integer.parseInt(stypeId);
		} catch (NumberFormatException e) {
			return Api.Factory().response(400, "参数错误", false);
		}
		Connection mCon = DatabaseManager.getInstance().open(mCurUserName);
		StoreInfoNTypeDao typeDao = new StoreInfoNTypeDao(mCon);
		List<StoreInfoNType> StoreInfoNTypes = null;
		try {
			StoreInfoNTypes = typeDao.fetchAllStoreInfoNTypesByStoreId(storeId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (StoreInfoNTypes != null) {
			return Api.Factory().response(200, "获取成功", StoreInfoNTypes);
		}
		return Api.Factory().response(401, "获取失败", false);
	}

	private String fetchAllStoreInfoNTypesByStoreId(HttpServletRequest request) {
		String stypeId = request.getParameter("storeTypeId");

		int storeTypeId;
		try {

			storeTypeId = Integer.parseInt(stypeId);
		} catch (NumberFormatException e) {
			return Api.Factory().response(400, "参数错误", false);
		}
		Connection mCon = DatabaseManager.getInstance().open(mCurUserName);
		StoreInfoNTypeDao typeDao = new StoreInfoNTypeDao(mCon);
		List<StoreInfoNType> StoreInfoNTypes = null;
		try {
			StoreInfoNTypes = typeDao
					.fetchAllStoreInfoNTypesByTypeId(storeTypeId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (StoreInfoNTypes != null) {
			return Api.Factory().response(200, "获取成功", StoreInfoNTypes);
		}
		return Api.Factory().response(401, "获取失败", false);
	}

	private String fetchAllStoreInfoNTypes(HttpServletRequest request) {
		Connection mCon = DatabaseManager.getInstance().open(mCurUserName);
		StoreInfoNTypeDao typeDao = new StoreInfoNTypeDao(mCon);
		List<StoreInfoNType> StoreInfoNTypes = typeDao
				.fetchAllStoreInfoNTypes();
		if (StoreInfoNTypes != null) {
			return Api.Factory().response(200, "获取成功", StoreInfoNTypes);
		}
		return Api.Factory().response(401, "获取失败", false);
	}

}
