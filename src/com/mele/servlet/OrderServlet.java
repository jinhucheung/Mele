package com.mele.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ele.me.api.Api;
import com.mele.Constants;
import com.mele.control.DatabaseManager;
import com.mele.dao.entity.Order;
import com.mele.dao.impl.OrderDao;

@WebServlet("/OrderServlet")
public class OrderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/** 缓存订单(未正式提交数据库) 。 */
	private static final Order mCacheOrder = new Order();

	/** 生成订单编号前14位 . */
	final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

	public OrderServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// response.setHeader("Access-Control-Allow-Origin", "*");
		// response.setCharacterEncoding("utf-8");
		// PrintWriter pw = response.getWriter();
		// String jsondata = "";
		// String func = request.getParameter("func");
		/*
		 * if(!LoginMes.isLogin(request)){ jsondata =Api.Factory().response(403,
		 * "请先登陆", false); pw.write(jsondata); pw.flush(); pw.close(); return; }
		 */
		// if (func == null) {
		// jsondata = Api.Factory().response(400, "参数错误,请加上fun参数", false);
		// pw.write(jsondata);
		// pw.flush();
		// pw.close();
		// return;
		// }

		super.doGet(request, response);

		if (Constants.DEFAULT_USER.equals(mCurUserName)) {
			jsondata = Api.Factory().response(403, "请先登陆", false);
			pw.write(jsondata);
			pw.flush();
			pw.close();
			pw = null;
			func = "";
			return;
		}

		if (func.equals("addOrder")) {
			jsondata = this.addOrder(request);
		} else if (func.equals("deleteOrder")) {
			jsondata = this.deleteOrder(request);
		} else if (func.equals("deleteAllOrdersInAccount")) {
			jsondata = this.deleteAllOrdersInAccount(request);
		} else if (func.equals("deleteAllOrdersInStore")) {
			jsondata = this.deleteAllOrdersInStore(request);
		} else if (func.equals("updateOrderComment")) {
			jsondata = this.updateOrderComment(request);
		} else if (func.equals("fetchOrder")) {
			jsondata = this.fetchOrder(request);
		} else if (func.equals("fetchAllOrder")) {
			jsondata = this.fetchAllOrder(request);
		} else if (func.equals("fetchAllOrderInAccount")) {
			jsondata = this.fetchAllOrderInAccount(request);
		} else if (func.equals("fetchAllOrderInStore")) {
			jsondata = this.fetchAllOrderInStore(request);
		} else if (func.equals("cacheOrder")) {
			jsondata = this.cacheOrder(request);
		} else if (func.equals("readCacheOrder")) {
			jsondata = this.readCacheOrder();
		} else if(func.equals("submitCacheOrder")){
			jsondata=this.submitCacheOrder();
		} else {
			jsondata = Api.Factory().response(400, "参数错误", false);
		}
		pw.write(jsondata);
		pw.flush();
		pw.close();
		pw = null;
		func = "";
	}

	/**
	 * 添加订单 OrderServlet?func=addOrder&cityCode=0753
	 * 
	 * @param request
	 * @return
	 * 
	 *         http://localhost:8080/eleme/apis/OrderServlet?func=addOrder&
	 *         userAccount
	 *         =1&orderCode=1&storeId=1&userAddressId=1&orderFoodId=1&
	 *         orderFoodPriceXNum=1&userNote=1&orderDate=1&comment=1
	 */
	private String addOrder(HttpServletRequest request) {
		String userAccount, orderCode, storeId, userAddressId, orderFoodId, orderFoodPriceXNum, userNote, orderTime, comment;
		userAccount = request.getParameter("userAccount");
		orderCode = request.getParameter("orderCode");
		storeId = request.getParameter("storeId");
		userAddressId = request.getParameter("userAddressId");
		orderFoodId = request.getParameter("orderFoodId");
		orderFoodPriceXNum = request.getParameter("orderFoodPriceXNum");
		userNote = request.getParameter("userNote");
		orderTime = request.getParameter("orderDate");
		comment = request.getParameter("comment");

		if (userAccount == null || orderCode == null || storeId == null
				|| userAddressId == null || orderFoodId == null
				|| orderFoodPriceXNum == null || userNote == null
				|| orderTime == null) {
			System.out.println("1GG");
			return Api.Factory().response(400, "参数错误", false);
		}

		long StoreId;
		int UserAddressId;
		long OrderTime;
		Timestamp ordertime;
		try {
			StoreId = Long.parseLong(storeId);
			UserAddressId = Integer.parseInt(userAddressId);
			OrderTime = Long.parseLong(orderTime);
			ordertime = new Timestamp(OrderTime);
		} catch (Exception e) {
			return Api.Factory().response(400, "参数错误", false);
		}

		Connection mCon = DatabaseManager.getInstance().open(mCurUserName);
		OrderDao orderDao = new OrderDao(mCon);

		Order order = new Order(userAccount, orderCode, StoreId, UserAddressId,
				orderFoodId, orderFoodPriceXNum, userNote, ordertime, comment);
		boolean isAdd;
		try {
			isAdd = orderDao.addOrder(order);
		} catch (Exception e) {
			return Api.Factory().response(400, "参数错误", false);
		}

		if (isAdd) {
			return Api.Factory().response(200, "添加成功", true);
		} else {
			return Api.Factory().response(401, "添加失败", false);
		}

	}

	/**
	 * 根据订单号删除订单 OrderServlet?func=delOrder&orderCode=2016060812112111
	 * 
	 * @param request
	 * @return
	 */
	private String deleteOrder(HttpServletRequest request) {

		Connection mCon = DatabaseManager.getInstance().open(mCurUserName);
		OrderDao orderDao = new OrderDao(mCon);
		String orderCode = request.getParameter("orderCode");
		boolean isDel = false;
		try {
			isDel = orderDao.deleteOrder(orderCode);
		} catch (Exception e) {
			return Api.Factory().response(400, "参数错误", true);
		}
		if (isDel) {
			return Api.Factory().response(200, "删除成功", true);
		} else {
			return Api.Factory().response(401, "删除失败", true);
		}
	}

	/**
	 * 删除指定账户的所有订单 OrderServlet?func=deleteAllOrdersInAccount&account=
	 * 
	 * @param request
	 * @return
	 */
	private String deleteAllOrdersInAccount(HttpServletRequest request) {

		Connection mCon = DatabaseManager.getInstance().open(mCurUserName);
		OrderDao orderDao = new OrderDao(mCon);
		String account = request.getParameter("account");
		boolean isDel = false;
		try {
			isDel = orderDao.deleteAllOrdersInAccount(account);
		} catch (Exception e) {
			return Api.Factory().response(400, "参数错误", true);
		}
		if (isDel) {
			return Api.Factory().response(200, "删除成功", true);
		} else {
			return Api.Factory().response(401, "删除失败", true);
		}
	}

	/**
	 * 根据商家Id删除所有订单 OrderServlet?func=deleteAllOrdersInStore&storeId=1
	 * 
	 * @param request
	 * @return
	 */
	private String deleteAllOrdersInStore(HttpServletRequest request) {

		String storeId = request.getParameter("storeId");
		long storeid;
		try {
			storeid = Long.parseLong(storeId);
		} catch (NumberFormatException e) {
			return Api.Factory().response(400, "参数错误", false);
		}
		Connection mCon = DatabaseManager.getInstance().open(mCurUserName);
		OrderDao orderDao = new OrderDao(mCon);
		boolean isDel = false;
		try {
			isDel = orderDao.deleteAllOrdersInStore(storeid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (isDel) {
			return Api.Factory().response(200, "删除成功", true);
		} else {
			return Api.Factory().response(401, "删除失败", true);
		}

	}

	private String updateOrderComment(HttpServletRequest request) {
		String orderCode = request.getParameter("orderCode");
		String comment = request.getParameter("comment");
		Connection mCon = DatabaseManager.getInstance().open(mCurUserName);
		OrderDao orderDao = new OrderDao(mCon);
		boolean isUpdate = false;
		try {
			if (orderDao.updateOrderComment(orderCode, comment) > 0) {
				isUpdate = true;
			}
		} catch (Exception e) {
			return Api.Factory().response(400, "参数错误", false);
		}

		if (isUpdate) {
			return Api.Factory().response(200, "修改成功", true);
		} else {
			return Api.Factory().response(401, "修改失败", true);
		}

	}

	private String fetchOrder(HttpServletRequest request) {
		String orderCode = request.getParameter("orderCode");
		if (orderCode == null) {
			return Api.Factory().response(400, "参数错误", false);
		}
		Connection mCon = DatabaseManager.getInstance().open(mCurUserName);
		OrderDao orderDao = new OrderDao(mCon);
		Order order = orderDao.fetchOrder(orderCode);
		if (order == null) {
			return Api.Factory().response(401, "获取失败", false);
		} else {
			return Api.Factory().response(200, "获取成功", order);
		}

	}

	private String fetchAllOrderInAccount(HttpServletRequest request) {

		String account = request.getParameter("account");
		Connection mCon = DatabaseManager.getInstance().open(mCurUserName);
		OrderDao orderDao = new OrderDao(mCon);
		List<Order> orders;
		try {
			orders = orderDao.fetchAllOrderInAccount(account);
		} catch (Exception e) {
			return Api.Factory().response(400, "参数错误", false);
		}

		return Api.Factory().response(200, "获取成功", orders);

	}

	private String fetchAllOrderInStore(HttpServletRequest request) {
		String storeId = request.getParameter("storeId");
		Connection mCon = DatabaseManager.getInstance().open(mCurUserName);
		OrderDao orderDao = new OrderDao(mCon);
		long storeid;
		try {
			storeid = Long.parseLong(storeId);
		} catch (NumberFormatException e) {
			return Api.Factory().response(400, "参数错误", false);
		}
		List<Order> orders;
		try {
			orders = orderDao.fetchAllOrderInStore(storeid);
		} catch (Exception e) {
			return Api.Factory().response(400, "参数错误", false);
		}
		return Api.Factory().response(200, "获取成功", orders);
	}

	private String fetchAllOrder(HttpServletRequest request) {
		Connection mCon = DatabaseManager.getInstance().open(mCurUserName);
		OrderDao orderDao = new OrderDao(mCon);
		List<Order> orders = orderDao.fetchAllOrder();
		if (orders == null) {
			return Api.Factory().response(401, "获取失败", false);
		} else {
			return Api.Factory().response(200, "获取成功", orders);
		}
	}

	/**
	 * 缓存订单数据
	 * 
	 * @param request
	 * @return
	 */
	private String cacheOrder(HttpServletRequest request) {
		String storeId = request.getParameter("storeId");
		String orderFoodIds = request.getParameter("orderFoodId");
		String orderFoodPriceXNum = request.getParameter("orderFoodPriceXNum");
		String userAccount = mCurUserName;

		if (storeId == null || orderFoodIds == null
				|| orderFoodPriceXNum == null) {
			// System.out.println("1GG");
			return Api.Factory().response(400, "参数错误", false);
		}
		long StoreId = Long.parseLong(storeId);
		Timestamp ordertime = new Timestamp(System.currentTimeMillis());

		String orderCode = dateFormat.format(ordertime);
		for (int i = 0; i < 2; i++) {
			int ramNum = (int) (Math.random() * 10);
			orderCode += ramNum;
		}
		mCacheOrder.setOrderCode(orderCode);
		mCacheOrder.setOrderFoodIds(orderFoodIds);
		mCacheOrder.setOrderFoodNumNPrice(orderFoodPriceXNum);
		mCacheOrder.setStoreId(StoreId);
		mCacheOrder.setUserAccount(userAccount);
		mCacheOrder.setOrderTime(ordertime);
		mCacheOrder.setUserAddressId(1);
		mCacheOrder.setComment("");
		mCacheOrder.setUserNote("");
		//System.out.println(mCacheOrder);
		return Api.Factory().response(200, "缓存成功", true);
	}

	/**
	 * 读取缓存订单数据
	 * 
	 * @return
	 */
	private String readCacheOrder() {
		if (mCacheOrder.isNullValue()) {
			return Api.Factory().response(400, "不存在缓存订单", false);
		}

		// 读取订单中菜式信息
		Connection mCon = DatabaseManager.getInstance().open(mCurUserName);
		OrderDao orderDao = new OrderDao(mCon);
		orderDao.fetchFoods(mCacheOrder);

		return Api.Factory().response(200, "读取缓存订单成功", mCacheOrder);
	}

	private String submitCacheOrder() {
		if (mCacheOrder.isNullValue()) {
			return Api.Factory().response(400, "不存在缓存订单", false);
		}

		// 读取订单中菜式信息
		Connection mCon = DatabaseManager.getInstance().open(mCurUserName);
		OrderDao orderDao = new OrderDao(mCon);
		
		try {
			orderDao.addOrder(mCacheOrder);
		} catch (Exception e) {
			e.printStackTrace();
			return Api.Factory().response(401, "提交订单失败！！", false);
		}
		mCacheOrder.setOrderCode("");
		return Api.Factory().response(200, "提交订单成功", true);
	}
}
