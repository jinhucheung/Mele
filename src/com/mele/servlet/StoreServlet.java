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
import com.mele.dao.entity.Store;
import com.mele.dao.impl.StoreDao;
import com.mele.dao.util.ContentValues;
import com.mele.util.TextUtils;

@WebServlet("/StoreServlet")
public class StoreServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public StoreServlet() {
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
		if (func.equals("GetStoresByCityCode")) {
			jsondata = this.GetStoresByCityCode(request);
		} else if (func.equals("addStore")) {
			jsondata = this.addStore(request);
		} else if (func.equals("deleteStore")) {
			jsondata = this.deleteStore(request);
		}

		else if (func.equals("deleteStoreByCityCode")) {
			jsondata = this.deleteStoreByCityCode(request);
		}

		else if (func.equals("deleteAllStores")) {
			jsondata = this.deleteAllStores(request);
		} else if (func.equals("updateStoreName")) {
			jsondata = this.updateStoreName(request);
		} else if (func.equals("updateStoreLogo")) {
			jsondata = this.updateStoreLogo(request);
		} else if (func.equals("updateStoreShopHours")) {
			jsondata = this.updateStoreShopHours(request);
		} else if (func.equals("updateStorePrice")) {
			jsondata = this.updateStorePrice(request);
		} else if (func.equals("updateStoreNotice")) {
			jsondata = this.updateStoreNotice(request);
		} else if (func.equals("updateStoreTel")) {
			jsondata = this.updateStoreTel(request);
		} else if (func.equals("updateStoreValues")) {
			jsondata = this.updateStoreValues(request);
		} else if (func.equals("updateStoreTel")) {
			jsondata = this.updateStoreTel(request);
		} else if (func.equals("updateStoreTel")) {
			jsondata = this.updateStoreTel(request);
		} else if (func.equals("fetchAllStores")) {
			jsondata = this.fetchAllStores();
		} else if (func.equals("fetchAllStoresByCityCode")) {
			jsondata = this.fetchAllStoresByCityCode(request);
		} else if (func.equals("fetchAllStoresByStartPrice")) {
			jsondata = this.fetchAllStoresByStartPrice(request);

		} else if (func.equals("fetchAllStoresByTypeId")) {
			jsondata = this.fetchAllStoresByTypeId(request);

		} else if (func.equals("fetchStoreByStoreId")) {
			jsondata = this.fetchStoreByStoreId(request);

		} else if (func.equals("fetchAllStoresByCityCodeNTypeId")) {
			jsondata = this.fetchAllStoresByCityCodeNTypeId(request);
		} else if (func.equals("fetchAllStoresByCityCodeNStartPrice")) {
			jsondata = this.fetchAllStoresByCityCodeNStartPrice(request);
		} else {
			jsondata = Api.Factory().response(400, "参数错误", false);
		}

		pw.write(jsondata);
		pw.flush();
		pw.close();
		pw = null;
		func = "";
	}

	private String GetStoresByCityCode(HttpServletRequest request) {
		String citycode = request.getParameter("citycode");
		if (citycode == null) {
			citycode = "0753";
		}
		Connection mCon = DatabaseManager.getInstance().open(mCurUserName);
		StoreDao storeDao = new StoreDao(mCon);
		List<Store> stores = null;
		try {
			stores = storeDao.fetchAllStoresByCityCode(citycode);
		} catch (Exception e) {
			return Api.Factory().response(401, "获取失败", "");

		}
		return Api.Factory().response(200, "请求成功", stores);
	}

	private String addStore(HttpServletRequest request) {
		String parms[] = { "storeId", "storeName", "storeLogo", "tel",
				"cityCode", "shopHours", "latlng", "startPrice",
				"transportPrice", "notice", "cheapenNotice", "newuserNotice",
				"storeType", "uAccount", "address" };
		String[] values = new String[15];
		for (int i = 0; i < parms.length; i++) {
			values[i] = request.getParameter(parms[i]);
			if (values[i] == null) {

				return Api.Factory().response(400, "参数错误哦", false);
			}

		}
		long values1 = 0;
		float value8 = 0;
		float value9 = 0;
		try {
			values1 = Long.parseLong(values[0]);
			value8 = Float.parseFloat(values[7]);
			value9 = Float.parseFloat(values[8]);
		} catch (Exception e) {
			return Api.Factory().response(400, "参数错误", false);
		}
		Connection conn = DatabaseManager.getInstance().open(mCurUserName);
		StoreDao storeDao = new StoreDao(conn);
		Store store = new Store(values1, values[1], values[2], values[3],
				values[4], values[5], values[6], value8, value9, values[9],
				values[10], values[11], values[12], values[13], values[14]);
		System.out.print("@" + store.toString());
		try {
			if (storeDao.addStore(store)) {
				return Api.Factory().response(200, "添加成功", true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Api.Factory().response(401, "添加失败", false);
	}

	private String deleteStore(HttpServletRequest request) {
		String storeId = request.getParameter("storeId");
		long storeid = 0;
		try {
			storeid = Long.parseLong(storeId);
		} catch (Exception e) {
			return Api.Factory().response(400, "参数错误", false);
		}
		Connection conn = DatabaseManager.getInstance().open(mCurUserName);
		StoreDao storeDao = new StoreDao(conn);
		try {
			if (storeDao.deleteStore(storeid)) {
				return Api.Factory().response(200, "删除成功", true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Api.Factory().response(401, "删除失败", false);
	}

	private String deleteStoreByCityCode(HttpServletRequest request) {
		String cityCode = request.getParameter("cityCode");
		if (cityCode == null) {
			return Api.Factory().response(400, "参数错误", false);
		}
		Connection conn = DatabaseManager.getInstance().open(mCurUserName);
		StoreDao storeDao = new StoreDao(conn);
		try {
			if (storeDao.deleteStoreByCityCode(cityCode)) {
				return Api.Factory().response(200, "删除成功", true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Api.Factory().response(401, "删除失败", false);
	}

	private String deleteAllStores(HttpServletRequest request) {
		Connection conn = DatabaseManager.getInstance().open(mCurUserName);
		StoreDao storeDao = new StoreDao(conn);
		boolean isDel = storeDao.deleteAllStores();
		if (isDel) {
			return Api.Factory().response(200, "删除成功", true);
		} else {
			return Api.Factory().response(401, "删除失败", false);
		}

	}

	private String updateStoreName(HttpServletRequest request) {
		String storeName = request.getParameter("storeName");
		String storeId = request.getParameter("storeId");
		long storeid = 0;
		try {
			storeid = Long.parseLong(storeId);
		} catch (Exception e) {
			return Api.Factory().response(400, "参数错误", true);
		}
		Connection conn = DatabaseManager.getInstance().open(mCurUserName);
		StoreDao storeDao = new StoreDao(conn);
		int isUpdate = 0;
		try {
			isUpdate = storeDao.updateStoreName(storeid, storeName);
		} catch (Exception e) {
			return Api.Factory().response(400, "参数错误", false);
		}
		if (isUpdate > 0) {
			return Api.Factory().response(200, "更新成功", true);
		} else {
			return Api.Factory().response(401, "更新失败", false);
		}
	}

	private String updateStoreLogo(HttpServletRequest request) {
		String storeId = request.getParameter("storeId");
		String storeLogo = request.getParameter("storeLogo");
		long storeid = 0;
		try {
			storeid = Long.parseLong(storeId);
		} catch (Exception e) {
			return Api.Factory().response(400, "参数错误", true);
		}
		Connection conn = DatabaseManager.getInstance().open(mCurUserName);
		StoreDao storeDao = new StoreDao(conn);
		int isUpdate = 0;
		try {
			isUpdate = storeDao.updateStoreLogo(storeid, storeLogo);
		} catch (Exception e) {
			return Api.Factory().response(400, "参数错误", false);
		}
		if (isUpdate > 0) {
			return Api.Factory().response(200, "更新成功", true);
		} else {
			return Api.Factory().response(401, "更新失败", false);
		}
	}

	private String updateStoreShopHours(HttpServletRequest request) {
		String storeId = request.getParameter("storeId");
		String shopHours = request.getParameter("shopHours");
		long storeid = 0;
		try {
			storeid = Long.parseLong(storeId);
		} catch (Exception e) {
			return Api.Factory().response(400, "参数错误", false);
		}
		Connection conn = DatabaseManager.getInstance().open(mCurUserName);
		StoreDao storeDao = new StoreDao(conn);

		int isUpdate = 0;
		try {
			isUpdate = storeDao.updateStoreShopHours(storeid, shopHours);
		} catch (Exception e) {
			return Api.Factory().response(400, "参数错误", true);
		}
		if (isUpdate > 0) {
			return Api.Factory().response(200, "更新成功", true);
		} else {
			return Api.Factory().response(401, "更新失败", false);
		}

	}

	private String updateStorePrice(HttpServletRequest request) {
		String storeId = request.getParameter("storeId");
		String startPrice = request.getParameter("startPrice");
		String transportpricePrice = request
				.getParameter("transportpricePrice");
		float startprice = 0;
		float transportpriceprice = 0;
		long storeid = 0;
		try {
			storeid = Long.parseLong(storeId);
			startprice = Float.parseFloat(startPrice);
			transportpriceprice = Float.parseFloat(transportpricePrice);
		} catch (Exception e) {
			return Api.Factory().response(400, "参数错误", false);
		}
		Connection conn = DatabaseManager.getInstance().open(mCurUserName);
		StoreDao storeDao = new StoreDao(conn);
		int isUpdate = 0;
		try {
			isUpdate = storeDao.updateStorePrice(storeid, startprice,
					transportpriceprice);
		} catch (Exception e) {
			return Api.Factory().response(400, "参数错误", false);
		}

		if (isUpdate > 0) {
			return Api.Factory().response(200, "更新成功", true);
		} else {
			return Api.Factory().response(401, "更新失败", false);
		}
	}

	private String updateStoreNotice(HttpServletRequest request) {
		String parms[] = { "storeId", "notice", "cheapenNotice",
				"newUserNotice" };
		String val[] = new String[4];
		for (int i = 0; i < parms.length; i++) {
			val[i] = request.getParameter(parms[i]);
			if (val[i] == null) {
				return Api.Factory().response(400, "参数错JJ误", false);
			}
		}
		long val0 = 0;
		try {
			val0 = Long.parseLong(val[0]);
		} catch (Exception e) {
			return Api.Factory().response(400, "参数错误", false);
		}

		Connection conn = DatabaseManager.getInstance().open(mCurUserName);
		StoreDao storeDao = new StoreDao(conn);
		int isup = 0;
		try {
			isup = storeDao.updateStoreNotice(val0, val[1], val[2], val[3]);
		} catch (Exception e) {
			return Api.Factory().response(400, "参数错误", false);
		}
		if (isup > 0) {
			return Api.Factory().response(200, "更新成功", true);
		} else {
			return Api.Factory().response(401, "更新失败", false);
		}
	}

	private String updateStoreTel(HttpServletRequest request) {
		String storeId = request.getParameter("storeId");
		String tel = request.getParameter("tel");
		long storeid = 0;
		try {
			storeid = Long.parseLong(storeId);
		} catch (Exception e) {
			return Api.Factory().response(400, "参数错误", false);
		}
		if (TextUtils.isEmpty(storeId)) {
			return Api.Factory().response(400, "参数错误", false);
		}
		Connection conn = DatabaseManager.getInstance().open(mCurUserName);
		StoreDao storeDao = new StoreDao(conn);
		try {
			if (storeDao.updateStoreTel(storeid, tel) > 0) {
				return Api.Factory().response(200, "更新成功", true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Api.Factory().response(401, "更新失败", false);
	}

	private String updateStoreValues(HttpServletRequest request) {
		String storeId = request.getParameter("storeId");
		long storeid = 0;
		try {
			storeid = Long.parseLong(storeId);
		} catch (Exception e) {
			return Api.Factory().response(400, "参数错误", false);
		}
		if (TextUtils.isEmpty(storeId) || TextUtils.isEmpty(storeId)) {
			return Api.Factory().response(400, "参数错误", false);
		}

		Connection conn = DatabaseManager.getInstance().open(mCurUserName);
		StoreDao storeDao = new StoreDao(conn);
		ContentValues values = new ContentValues();
		/**
		 * *******
		 */
		storeDao.updateStoreValues(storeid, values);
		return null;

	}

	private String fetchAllStores() {
		Connection conn = DatabaseManager.getInstance().open(mCurUserName);
		StoreDao storeDao = new StoreDao(conn);
		List<Store> stores = storeDao.fetchAllStores();
		if (null == stores) {
			return Api.Factory().response(400, "获取失败", false);
		}
		return Api.Factory().response(200, "获取成功", stores);
	}

	private String fetchAllStoresByCityCode(HttpServletRequest request) {
		String cityCode = request.getParameter("cityCode");
		if (cityCode == null) {
			return Api.Factory().response(400, "参数错误", false);
		}
		Connection conn = DatabaseManager.getInstance().open(mCurUserName);
		StoreDao storeDao = new StoreDao(conn);
		List<Store> stores;
		try {
			stores = storeDao.fetchAllStoresByCityCode(cityCode);
		} catch (Exception e) {
			return Api.Factory().response(400, "参数错误", false);
		}

		if (null == stores) {
			return Api.Factory().response(401, "获取失败", false);
		}
		return Api.Factory().response(200, "获取成功", stores);
	}

	private String fetchAllStoresByStartPrice(HttpServletRequest request) {
		String minPrice = request.getParameter("minPrice");
		String maxPrice = request.getParameter("maxPrice");
		if (minPrice == null || maxPrice == null) {
			return Api.Factory().response(400, "参数错误ya", false);
		}
		float minprice = 0;
		float maxprice = 0;
		try {
			maxprice = Float.parseFloat(maxPrice);
			minprice = Float.parseFloat(minPrice);
		} catch (Exception e) {
			return Api.Factory().response(400, "参数错误", false);
		}
		Connection conn = DatabaseManager.getInstance().open(mCurUserName);
		StoreDao storeDao = new StoreDao(conn);
		List<Store> stores;
		try {
			stores = storeDao.fetchAllStoresByStartPrice(minprice, maxprice);
		} catch (Exception e) {
			return Api.Factory().response(400, "参数错误", false);
		}

		if (null == stores) {
			return Api.Factory().response(401, "获取失败", false);
		} else
			return Api.Factory().response(200, "获取成功", stores);
	}

	private String fetchAllStoresByTypeId(HttpServletRequest request) {
		String storeTypeId = request.getParameter("storeTypeId");

		if (storeTypeId == null) {
			return Api.Factory().response(400, "参数错误", false);
		}
		int storeTypeid = 0;
		try {
			storeTypeid = Integer.parseInt(storeTypeId);
		} catch (Exception e) {
			return Api.Factory().response(400, "参数错误", false);
		}
		Connection conn = DatabaseManager.getInstance().open(mCurUserName);
		StoreDao storeDao = new StoreDao(conn);

		// System.out.println(storeTypeid);
		List<Store> stores;
		try {
			stores = storeDao.fetchAllStoresByTypeId(storeTypeid);
		} catch (Exception e) {
			return Api.Factory().response(400, "参数错误", false);
		}

		if (null == stores) {
			return Api.Factory().response(401, "获取失败", false);
		} else
			return Api.Factory().response(200, "获取成功", stores);
	}

	private String fetchStoreByStoreId(HttpServletRequest request) {
		String storeId = request.getParameter("storeId");
		long storeid = 0;
		try {
			storeid = Long.parseLong(storeId);
		} catch (Exception e) {
			return Api.Factory().response(400, "参数错误", false);
		}
		if (TextUtils.isEmpty(storeId)) {
			return Api.Factory().response(400, "参数错误", false);
		}
		Connection conn = DatabaseManager.getInstance().open(mCurUserName);
		StoreDao storeDao = new StoreDao(conn);
		Store store;
		try {
			store = storeDao.fetchStoreByStoreId(storeid);
		} catch (Exception e) {
			return Api.Factory().response(400, "参数错误", false);
		}
		if (null == store) {
			return Api.Factory().response(401, "获取失败", false);
		} else
			return Api.Factory().response(200, "获取成功", store);
	}

	private String fetchAllStoresByCityCodeNTypeId(HttpServletRequest request) {
		String storeTypeId = request.getParameter("storeTypeId");
		String cityCode = request.getParameter("cityCode");
		if (storeTypeId == null || null == cityCode
				|| "".equals(cityCode.trim())) {
			return Api.Factory().response(400, "参数错误", false);
		}
		int storeTypeid = 0;
		try {
			storeTypeid = Integer.parseInt(storeTypeId);
		} catch (Exception e) {
			return Api.Factory().response(400, "参数错误", false);
		}
		Connection conn = DatabaseManager.getInstance().open(mCurUserName);
		StoreDao storeDao = new StoreDao(conn);

		List<Store> stores;
		try {
			stores = storeDao.fetchAllStoresByCityCodeNTypeId(cityCode,
					storeTypeid);
		} catch (Exception e) {
			return Api.Factory().response(400, "参数错误", false);
		}

		if (null == stores) {
			return Api.Factory().response(401, "获取失败", false);
		} else
			return Api.Factory().response(200, "获取成功", stores);
	}

	private String fetchAllStoresByCityCodeNStartPrice(
			HttpServletRequest request) {
		String minPrice = request.getParameter("minPrice");
		String maxPrice = request.getParameter("maxPrice");
		String cityCode = request.getParameter("cityCode");
		if (minPrice == null || maxPrice == null || null == cityCode
				|| "".equals(cityCode.trim())) {
			return Api.Factory().response(400, "参数错误ya", false);
		}
		float minprice = 0;
		float maxprice = 0;
		try {
			maxprice = Float.parseFloat(maxPrice);
			minprice = Float.parseFloat(minPrice);
		} catch (Exception e) {
			return Api.Factory().response(400, "参数错误", false);
		}
		Connection conn = DatabaseManager.getInstance().open(mCurUserName);
		StoreDao storeDao = new StoreDao(conn);
		List<Store> stores;
		try {
			stores = storeDao.fetchAllStoresByCityCodeNStartPrice(cityCode,
					minprice, maxprice);
		} catch (Exception e) {
			return Api.Factory().response(400, "参数错误", false);
		}

		if (null == stores) {
			return Api.Factory().response(401, "获取失败", false);
		} else
			return Api.Factory().response(200, "获取成功", stores);
	}

}
