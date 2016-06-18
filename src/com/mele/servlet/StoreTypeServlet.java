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
import com.mele.dao.entity.StoreType;
import com.mele.dao.impl.StoreTypeDao;

@WebServlet("/StoreTypeServlet")
public class StoreTypeServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public StoreTypeServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
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
		// else
		if (func.equals("addStoreType")) {
			jsondata = this.addStoreType(request);
		} else if (func.equals("deleteStoreType")) {
			jsondata = this.deleteStoreType(request);
		} else if (func.equals("deleteAllStoreTypes")) {
			jsondata = this.deleteAllStoreTypes(request);
		} else if (func.equals("updateStoreTypeName")) {
			jsondata = this.updateStoreTypeName(request);
		} else if (func.equals("fetchAllStoreTypes")) {
			jsondata = this.fetchAllStoreTypes(request);
		} else if (func.equals("fetchStoreTypeById")) {
			jsondata = this.fetchStoreTypeById(request);
		} else if (func.equals("fetchStoreTypeBySuperId")) {
			jsondata = this.fetchStoreTypeBySuperId(request);
		} else {
			jsondata = Api.Factory().response(400, "参数错误", false);
		}
		pw.write(jsondata);
		pw.flush();
		pw.close();
		pw=null;
		func="";
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	private String addStoreType(HttpServletRequest request) {
		String superTypeId = request.getParameter("superTypeId");
		String typeName = request.getParameter("typeName");
		int superTypeid = 0;
		boolean isAdd = false;
		try {
			superTypeid = Integer.parseInt(superTypeId);
			Connection conn = DatabaseManager.getInstance().open(mCurUserName);
			StoreTypeDao std = new StoreTypeDao(conn);
			StoreType storeType = new StoreType(typeName, superTypeid);
			isAdd = std.addStoreType(storeType);

		} catch (Exception e) {
			return Api.Factory().response(400, "参数错误", false);
		}
		if (isAdd) {
			return Api.Factory().response(200, "添加成功", true);
		} else {
			return Api.Factory().response(401, "添加失败", false);
		}
	}

	private String deleteStoreType(HttpServletRequest request) {
		String storeTypeId = request.getParameter("storeTypeId");
		int storeTypeid = 0;
		boolean isDel = false;
		try {
			storeTypeid = Integer.parseInt(storeTypeId);
			Connection conn = DatabaseManager.getInstance().open(mCurUserName);
			StoreTypeDao std = new StoreTypeDao(conn);
			isDel = std.deleteStoreType(storeTypeid);

		} catch (Exception e) {
			return Api.Factory().response(400, "参数错误", false);
		}
		if (isDel) {
			return Api.Factory().response(200, "删除成功", true);
		} else {
			return Api.Factory().response(401, "删除失败", false);
		}
	}

	private String deleteAllStoreTypes(HttpServletRequest request) {
		boolean isDel = false;
		try {
			Connection conn = DatabaseManager.getInstance().open(mCurUserName);
			StoreTypeDao std = new StoreTypeDao(conn);
			isDel = std.deleteAllStoreTypes();

		} catch (Exception e) {
			return Api.Factory().response(400, "参数错误", false);
		}
		if (isDel) {
			return Api.Factory().response(200, "删除成功", true);
		} else {
			return Api.Factory().response(401, "删除失败", false);
		}
	}

	private String updateStoreTypeName(HttpServletRequest request) {
		return null;
	}

	private String fetchAllStoreTypes(HttpServletRequest request) {
		Connection conn = DatabaseManager.getInstance().open(mCurUserName);
		StoreTypeDao std = new StoreTypeDao(conn);
		List<StoreType> storeTypes;
		try {
			storeTypes = std.fetchAllStoreTypes();
		} catch (Exception e) {
			return Api.Factory().response(400, "参数错误", false);
		}
		if (storeTypes != null) {
			return Api.Factory().response(200, "获取成功", storeTypes);
		}
		return Api.Factory().response(200, "获取失败", storeTypes);
	}

	private String fetchStoreTypeById(HttpServletRequest request) {
		Connection conn = DatabaseManager.getInstance().open(mCurUserName);
		StoreTypeDao std = new StoreTypeDao(conn);
		String id = request.getParameter("id");
		int Id = 0;
		StoreType storeType;
		try {
			Id = Integer.parseInt(id);
			storeType = std.fetchStoreTypeById(Id);
		} catch (Exception e) {
			return Api.Factory().response(400, "参数错误", false);
		}
		if (storeType != null) {
			return Api.Factory().response(200, "获取成功", storeType);
		} else {
			return Api.Factory().response(401, "获取失败", false);
		}
	}

	private String fetchStoreTypeBySuperId(HttpServletRequest request) {
		Connection conn = DatabaseManager.getInstance().open(mCurUserName);
		StoreTypeDao std = new StoreTypeDao(conn);
		String superId = request.getParameter("superId");
		int superid = 0;
		List<StoreType> storeTypes;
		try {
			superid = Integer.parseInt(superId);
			storeTypes = std.fetchStoreTypeBySuperId(superid);
		} catch (Exception e) {
			return Api.Factory().response(400, "参数错误", false);
		}
		if (storeTypes != null) {
			return Api.Factory().response(200, "获取成功", storeTypes);
		} else {
			return Api.Factory().response(401, "获取失败", false);
		}
	}

}
