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
import com.mele.dao.entity.Food;
import com.mele.dao.impl.StoreMenuDao;
import com.mele.dao.util.ContentValues;

@WebServlet("/StoreMenuServlet")
public class StoreMenuServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public StoreMenuServlet() {
		super();

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

		if (func.equals("addFood")) {
			jsondata = this.addFood(request);
		} else if (func.equals("deleteFood")) {
			jsondata = this.deleteFood(request);
		} else if (func.equals("deleteAllFoods")) {
			jsondata = this.deleteAllFoods(request);
		} else if (func.equals("updateFoodName")) {
			jsondata = this.updateFoodName(request);
		} else if (func.equals("updateFoodPrices")) {
			jsondata = this.updateFoodPrices(request);
		} else if (func.equals("updateFoodType")) {
			jsondata = this.updateFoodType(request);
		} else if (func.equals("updateFoodIc")) {
			jsondata = this.updateFoodIc(request);
		} else if (func.equals("updateFoodValues")) {
			jsondata = this.updateFoodValues(request);
		} else if (func.equals("fetchAllFoods")) {
			jsondata = this.fetchAllFoods(request);
		} else if (func.equals("fetchAllFoodsByStoreId")) {
			jsondata = this.fetchAllFoodsByStoreId(request);
		} else if (func.equals("fetchAllFoodsByFoodPrices")) {
			jsondata = this.fetchAllFoodsByFoodPrices(request);
		} else if (func.equals("fetchAllFoodsByFoodType")) {
			jsondata = this.fetchAllFoodsByFoodType(request);
		} else if (func.equals("fetchFood")) {
			jsondata = this.fetchFood(request);
		} else if (func.equals("fetchAllFoodTypeByStoreId")) {
			jsondata = this.fetchAllFoodTypeByStoreId(request);
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

	/*
	 * private String getMenuByStoreId(HttpServletRequest request) { String
	 * strStoreId=request.getParameter("storeId"); if(strStoreId==null){ return
	 * Api.Factory().response(400, "参数错误", false); } long storeId = 0; try {
	 * storeId = Long.parseLong(strStoreId); } catch (NumberFormatException e) {
	 * 
	 * } Connection mCon = DatabaseManager.getInstance().open(); StoreMenuDao
	 * storeMenuDao = new StoreMenuDao(mCon); List<Food> foods ; try { foods =
	 * storeMenuDao.fetchAllFoodsByStoreId(storeId);
	 * 
	 * } catch (Exception e) { return Api.Factory().response(400, "参数错误",
	 * false); }
	 * 
	 * return Api.Factory().response(200, "获取成功", foods); }
	 */

	private String addFood(HttpServletRequest request) {
		String storeId, foodId, foodName, foodPrices, foodType, foodIc;
		storeId = request.getParameter("storeId");
		foodId = request.getParameter("foodId");
		foodName = request.getParameter("foodName");
		foodPrices = request.getParameter("foodPrices");
		foodType = request.getParameter("foodType");
		foodIc = request.getParameter("foodIc");
		System.out.println("SEEE" + storeId + ":" + foodId + ":" + foodName
				+ ":" + foodPrices + ":" + foodType);
		long storeid = 0;
		int foodid = 0;
		float foodprices = 0;
		try {
			storeid = Long.parseLong(storeId);
			foodid = Integer.parseInt(foodId);
			foodprices = Float.parseFloat(foodPrices);
		} catch (Exception e) {
			return Api.Factory().response(400, "参数错误", false);
		}

		Connection conn = DatabaseManager.getInstance().open(mCurUserName);
		StoreMenuDao menuDao = new StoreMenuDao(conn);
		Food food = new Food();

		food.setStoreId(storeid);
		food.setFoodId(foodid);
		food.setFoodName(foodName);
		food.setFoodPrices(foodprices);
		food.setFoodType(foodType);
		food.setFoodIc(foodIc);
		try {
			if (menuDao.addFood(food)) {
				return Api.Factory().response(200, "添加成功", true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Api.Factory().response(401, "添加失败", false);
	}

	private String deleteFood(HttpServletRequest request) {
		String storeId, foodId;
		storeId = request.getParameter("storeId");
		foodId = request.getParameter("foodId");
		long storeid = 0;
		int foodid = 0;
		try {
			storeid = Long.parseLong(storeId);
			foodid = Integer.parseInt(foodId);

		} catch (Exception e) {
			return Api.Factory().response(400, "参数错误", false);
		}
		Connection conn = DatabaseManager.getInstance().open(mCurUserName);
		StoreMenuDao menuDao = new StoreMenuDao(conn);
		try {
			if (menuDao.deleteFood(storeid, foodid)) {
				return Api.Factory().response(200, "删除成功", true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Api.Factory().response(401, "删除", false);
	}

	private String deleteAllFoods(HttpServletRequest request) {
		Connection conn = DatabaseManager.getInstance().open(mCurUserName);
		StoreMenuDao menuDao = new StoreMenuDao(conn);
		if (menuDao.deleteAllFoods()) {
			return Api.Factory().response(200, "删除成功", true);
		}
		return Api.Factory().response(401, "删除失败", false);
	}

	private String updateFoodName(HttpServletRequest request) {
		String storeId, foodId, foodName;
		storeId = request.getParameter("storeId");
		foodId = request.getParameter("foodId");
		foodName = request.getParameter("foodName");
		long storeid = 0;
		int foodid = 0;
		try {
			storeid = Long.parseLong(storeId);
			foodid = Integer.parseInt(foodId);

		} catch (Exception e) {
			return Api.Factory().response(400, "参数错误", false);
		}
		Connection conn = DatabaseManager.getInstance().open(mCurUserName);
		StoreMenuDao menuDao = new StoreMenuDao(conn);
		try {
			if (menuDao.updateFoodName(storeid, foodid, foodName) > 0) {
				return Api.Factory().response(200, "更新成功", true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Api.Factory().response(401, "更新失败", false);
	}

	private String updateFoodPrices(HttpServletRequest request) {
		String storeId, foodId, foodPrices;
		storeId = request.getParameter("storeId");
		foodId = request.getParameter("foodId");
		foodPrices = request.getParameter("foodPrices");
		long storeid = 0;
		int foodid = 0;
		float foodprices = 0;
		try {
			storeid = Long.parseLong(storeId);
			foodid = Integer.parseInt(foodId);
			foodprices = Float.parseFloat(foodPrices);
		} catch (Exception e) {
			return Api.Factory().response(400, "参数错误", false);
		}
		Connection conn = DatabaseManager.getInstance().open(mCurUserName);
		StoreMenuDao menuDao = new StoreMenuDao(conn);

		try {
			if (menuDao.updateFoodPrices(storeid, foodid, foodprices) > 0) {
				return Api.Factory().response(200, "更新成功", true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Api.Factory().response(401, "更新失败", false);
	}

	private String updateFoodType(HttpServletRequest request) {
		String storeId, foodId, foodType;
		storeId = request.getParameter("storeId");
		foodId = request.getParameter("foodId");
		foodType = request.getParameter("foodType");
		long storeid = 0;
		int foodid = 0;

		try {
			storeid = Long.parseLong(storeId);
			foodid = Integer.parseInt(foodId);
		} catch (Exception e) {
			return Api.Factory().response(400, "参数错误", false);
		}
		Connection conn = DatabaseManager.getInstance().open(mCurUserName);
		StoreMenuDao menuDao = new StoreMenuDao(conn);
		try {
			if (menuDao.updateFoodType(storeid, foodid, foodType) > 0) {
				return Api.Factory().response(200, "更新成功", true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Api.Factory().response(401, "更新失败", false);
	}

	private String updateFoodIc(HttpServletRequest request) {
		String storeId, foodId, foodIc;
		storeId = request.getParameter("storeId");
		foodId = request.getParameter("foodId");
		foodIc = request.getParameter("foodIc");
		long storeid = 0;
		int foodid = 0;

		try {
			storeid = Long.parseLong(storeId);
			foodid = Integer.parseInt(foodId);
		} catch (Exception e) {
			return Api.Factory().response(400, "参数错误", false);
		}
		Connection conn = DatabaseManager.getInstance().open(mCurUserName);
		StoreMenuDao menuDao = new StoreMenuDao(conn);
		try {
			if (menuDao.updateFoodIc(storeid, foodid, foodIc) > 0) {
				return Api.Factory().response(200, "更新成功", true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Api.Factory().response(401, "更新失败", false);
	}

	// /////////////////////
	private String updateFoodValues(HttpServletRequest request) {
		String storeId, foodId;
		storeId = request.getParameter("storeId");
		foodId = request.getParameter("foodId");
		long storeid = 0;
		int foodid = 0;

		try {
			storeid = Long.parseLong(storeId);
			foodid = Integer.parseInt(foodId);
		} catch (Exception e) {
			return Api.Factory().response(400, "参数错误", false);
		}
		Connection conn = DatabaseManager.getInstance().open(mCurUserName);
		StoreMenuDao menuDao = new StoreMenuDao(conn);
		ContentValues contentValues = new ContentValues();

		if (menuDao.updateFoodValues(storeid, foodid, contentValues) > 0) {
			return Api.Factory().response(200, "更新成功", true);
		}
		return Api.Factory().response(401, "更新失败", false);
	}

	private String fetchAllFoods(HttpServletRequest request) {
		Connection conn = DatabaseManager.getInstance().open(mCurUserName);
		StoreMenuDao menuDao = new StoreMenuDao(conn);
		List<Food> foods = menuDao.fetchAllFoods();
		if (foods != null) {
			return Api.Factory().response(200, "获取成功", foods);
		}
		return Api.Factory().response(401, "获取失败", false);
	}

	private String fetchAllFoodsByStoreId(HttpServletRequest request) {
		String storeId;
		storeId = request.getParameter("storeId");
		long storeid = 0;
		try {
			storeid = Long.parseLong(storeId);

		} catch (Exception e) {
			return Api.Factory().response(400, "参数错误", false);
		}
		Connection conn = DatabaseManager.getInstance().open(mCurUserName);
		StoreMenuDao menuDao = new StoreMenuDao(conn);
		List<Food> foods = null;
		try {
			foods = menuDao.fetchAllFoodsByStoreId(storeid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (foods != null) {
			return Api.Factory().response(200, "获取成功", foods);
		}
		return Api.Factory().response(401, "获取失败", false);
	}

	private String fetchAllFoodsByFoodPrices(HttpServletRequest request) {
		String storeId;
		storeId = request.getParameter("storeId");
		String minPrices;
		minPrices = request.getParameter("minPrices");
		String maxPrices;
		maxPrices = request.getParameter("maxPrices");
		long storeid = 0;
		float minprices = 0;
		float maxprices = 0;
		try {
			storeid = Long.parseLong(storeId);
			minprices = Float.parseFloat(minPrices);
			maxprices = Float.parseFloat(maxPrices);

		} catch (Exception e) {
			return Api.Factory().response(400, "参数错误", false);
		}
		Connection conn = DatabaseManager.getInstance().open(mCurUserName);
		StoreMenuDao menuDao = new StoreMenuDao(conn);
		List<Food> foods = null;
		try {
			foods = menuDao.fetchAllFoodsByFoodPrices(storeid, minprices,
					maxprices);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (foods != null) {
			return Api.Factory().response(200, "获取成功", foods);
		}
		return Api.Factory().response(401, "获取失败", false);
	}

	private String fetchAllFoodsByFoodType(HttpServletRequest request) {
		String foodType = request.getParameter("foodType");
		String storeId = request.getParameter("storeId");
		long storeid = 0;
		try {
			storeid = Long.parseLong(storeId);

		} catch (Exception e) {
			return Api.Factory().response(400, "参数错误", false);
		}
		Connection conn = DatabaseManager.getInstance().open(mCurUserName);
		StoreMenuDao menuDao = new StoreMenuDao(conn);
		List<Food> foods = null;
		try {
			foods = menuDao.fetchAllFoodsByFoodType(storeid, foodType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (foods != null) {
			return Api.Factory().response(200, "获取成功", foods);
		}
		return Api.Factory().response(401, "获取失败", false);
	}

	private String fetchFood(HttpServletRequest request) {
		String storeId, foodId;
		storeId = request.getParameter("storeId");
		foodId = request.getParameter("foodId");
		long storeid = 0;
		int foodid = 0;
		try {
			storeid = Long.parseLong(storeId);
			foodid = Integer.parseInt(foodId);

		} catch (Exception e) {
			return Api.Factory().response(400, "参数错误", false);
		}
		Connection conn = DatabaseManager.getInstance().open(mCurUserName);
		StoreMenuDao menuDao = new StoreMenuDao(conn);
		Food food = null;
		try {
			food = menuDao.fetchFood(storeid, foodid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (food != null) {
			return Api.Factory().response(200, "获取成功", food);
		}
		return Api.Factory().response(401, "获取失败", false);
	}

	private String fetchAllFoodTypeByStoreId(HttpServletRequest request) {
		String storeId;
		storeId = request.getParameter("storeId");
		long storeid = 0;
		try {
			storeid = Long.parseLong(storeId);
		} catch (Exception e) {
			return Api.Factory().response(400, "参数错误", false);
		}
		Connection conn = DatabaseManager.getInstance().open(mCurUserName);
		StoreMenuDao menuDao = new StoreMenuDao(conn);
		List<String> mFoodTypes = null;
		try {
			mFoodTypes = menuDao.fetchAllFoodTypeByStoreId(storeid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (mFoodTypes != null) {
			return Api.Factory().response(200, "获取成功", mFoodTypes);
		}
		return Api.Factory().response(401, "获取失败", false);
	}
}
