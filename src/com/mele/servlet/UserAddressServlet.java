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
import com.mele.dao.entity.UserAddress;
import com.mele.dao.impl.UserAddressDao;

@WebServlet("/UserAddressServlet")
public class UserAddressServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public UserAddressServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
//		response.setHeader("Access-Control-Allow-Origin", "*");
//		response.setCharacterEncoding("utf-8");
//		PrintWriter pw = response.getWriter();
//		String jsondata = "";
//		String func = request.getParameter("func");
//		if (func == null) {
//			jsondata = Api.Factory().response(400, "参数错误,请加上fun参数", false);
//			pw.write(jsondata);
//			pw.flush();
//			pw.close();
//			return;
//		}
		super.doGet(request, response);
		if (func.equals("getUserAddress")) {
			jsondata = this.getUserAddress(request);
		} else if (func.equals("addUserAddress")) {
			jsondata = this.addUserAddress(request);
		} else if (func.equals("getUserAddressByAccount")) {
			jsondata = this.getUserAddressByAccount(request);
		} else if (func.equals("deleteUserAddress")) {
			jsondata = this.deleteUserAddress(request);

		} else if (func.equals("deleteUserAddress")) {
			jsondata = this.deleteUserAddress(request);

		} else if (func.equals("updateUserAddressInAddress")) {
			jsondata = this.updateUserAddressInAddress(request);

		} else if (func.equals("updateUserAddressInCnullntact")) {
			jsondata = this.updateUserAddressInCnullntact(request);

		} else if (func.equals("updateUserAddressInSex")) {
			jsondata = this.updateUserAddressInSex(request);

		} else if (func.equals("updateUserAddressInTag")) {
			jsondata = this.updateUserAddressInTag(request);

		} else if (func.equals("updateUserAddressInTel")) {
			jsondata = this.updateUserAddressInTel(request);

		} else if (func.equals("updateUserAddressValues")) {
			jsondata = this.updateUserAddressValues(request);

		} else if (func.equals("deleteAllUserAddress")) {
			jsondata = this.deleteAllUserAddress(request);

		} else if (func.equals("fetchAllUserAddressByAccount")) {
			jsondata = this.fetchAllUserAddressByAccount(request);

		} else if (func.equals("fetchAllUserAddressByAccountNTag")) {
			jsondata = this.fetchAllUserAddressByAccountNTag(request);

		} else if (func.equals("fetchUserAddress")) {
			jsondata = this.fetchUserAddress(request);

		} else if (func.equals("fetchAllUserAddress")) {
			jsondata = this.fetchAllUserAddress(request);

		} else {
			jsondata = Api.Factory().response(400, "参数错误", false);
		}
		pw.write(jsondata);
		pw.flush();
		pw.close();
		pw=null;
		func="";
	}

	private String getUserAddressByAccount(HttpServletRequest request) {
		String acount = request.getParameter("account");
		if (acount == null) {
			return Api.Factory().response(400, "你的参数不对", false);
		}
		Connection mCon = DatabaseManager.getInstance().open(mCurUserName);
		UserAddressDao mUserAddressDao = new UserAddressDao(mCon);
		List<UserAddress> userAddressList;
		try {
			userAddressList = mUserAddressDao
					.fetchAllUserAddressByAccount(acount);
		} catch (Exception e) {
			return Api.Factory().response(400, "参数不对呀", false);
		}
		return Api.Factory().response(200, "获取成功", userAddressList);

	}

	private String addUserAddress(HttpServletRequest request) {
		String userAccount = null, userContact = null, userTel = null, userTag = null, userAddress = null;
		int addressId = 0, userSex = 0;
		Connection mCon = DatabaseManager.getInstance().open(mCurUserName);
		UserAddressDao addressDao = new UserAddressDao(mCon);
		UserAddress address = new UserAddress(addressId, userAccount,
				userContact, userSex, userTel, userTag, userAddress);

		boolean isAdd = false;
		try {
			isAdd = addressDao.addUserAddress(address);
		} catch (Exception e) {
			return Api.Factory().response(400, "参数不对", false);
		}
		if (isAdd) {
			return Api.Factory().response(200, "添加成功", true);
		} else {
			return Api.Factory().response(401, "添加失败", true);
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	private String getUserAddress(HttpServletRequest request) {
		String account = request.getParameter("account");
		String strAddressId = request.getParameter("addressId");
		if (account == null || strAddressId == null) {
			return Api.Factory().response(400, "参数错误,账号及地址Id,为空", false);
		}
		int addressId;
		try {

			addressId = Integer.parseInt(strAddressId);
		} catch (Exception e) {
			return Api.Factory().response(400, "参数错误,AddressId应为数字", false);
		}

		Connection mCon = DatabaseManager.getInstance().open(mCurUserName);
		UserAddressDao addressDao = new UserAddressDao(mCon);
		UserAddress user = null;
		try {
			user = addressDao.fetchUserAddress(account, addressId);
		} catch (Exception e) {
			return Api.Factory().response(400, "参数错误,账号或地址Id,为空", false);
		}
		return Api.Factory().response(200, "获取成功", user);

	}

	private String deleteUserAddress(HttpServletRequest request) {
		String account = request.getParameter("account");
		String addressId = request.getParameter("addressId");
		if (account == null || addressId == null) {
			return Api.Factory().response(400, "参数错误,AddressId应为数字", false);
		}
		int addressid = -1000;
		try {
			addressid = Integer.parseInt(addressId);
		} catch (Exception e) {
			return Api.Factory().response(400, "参数错误,AddressId应为数字", false);
		}
		Connection mCon = DatabaseManager.getInstance().open(mCurUserName);
		UserAddressDao addressDao = new UserAddressDao(mCon);
		try {
			if (addressDao.deleteUserAddress(account, addressid)) {
				return Api.Factory().response(200, "删除成功", true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Api.Factory().response(401, "删除失败", false);
	}

	private String deleteAllUserAddress(HttpServletRequest request) {
		Connection mCon = DatabaseManager.getInstance().open(mCurUserName);
		UserAddressDao addressDao = new UserAddressDao(mCon);
		if (addressDao.deleteAllUserAddress()) {
			return Api.Factory().response(200, "删除成功", true);
		}
		return Api.Factory().response(401, "删除失败", false);
	}

	private String updateUserAddressValues(HttpServletRequest request) {
		// Connection mCon = DatabaseManager.getInstance().open();
		// UserAddressDao addressDao = new UserAddressDao(mCon);
		// addressDao.updateUserAddressValues(account, addressId, values);
		return null;
	}

	private String updateUserAddressInCnullntact(HttpServletRequest request) {
		String account, addressId, contact;
		account = request.getParameter("account");
		addressId = request.getParameter("addressId");
		contact = request.getParameter("contact");
		int addressid;
		try {
			addressid = Integer.parseInt(addressId);
		} catch (NumberFormatException e) {
			return Api.Factory().response(400, "参数错误", false);
		}
		Connection mCon = DatabaseManager.getInstance().open(mCurUserName);
		UserAddressDao addressDao = new UserAddressDao(mCon);
		try {
			if (addressDao.updateUserAddressInContact(account, addressid,
					contact) > 0) {
				return Api.Factory().response(200, "更新成功", true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Api.Factory().response(401, "更新失败", false);
	}

	private String updateUserAddressInSex(HttpServletRequest request) {
		String account, addressId, sex;
		account = request.getParameter("account");
		addressId = request.getParameter("addressId");
		sex = request.getParameter("sex");
		int addressid;
		int Sex;
		try {
			addressid = Integer.parseInt(addressId);
			Sex = Integer.parseInt(sex);
		} catch (NumberFormatException e) {
			return Api.Factory().response(400, "参数错误", false);
		}
		Connection mCon = DatabaseManager.getInstance().open(mCurUserName);
		UserAddressDao addressDao = new UserAddressDao(mCon);
		try {
			if (addressDao.updateUserAddressInSex(account, addressid, Sex) > 0) {
				return Api.Factory().response(200, "更新成功", true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Api.Factory().response(401, "更新失败", false);
	}

	private String updateUserAddressInTel(HttpServletRequest request) {
		String account, addressId, tel;
		account = request.getParameter("account");
		addressId = request.getParameter("addressId");
		tel = request.getParameter("tel");
		int addressid;
		try {
			addressid = Integer.parseInt(addressId);
		} catch (NumberFormatException e) {
			return Api.Factory().response(400, "参数错误", false);
		}
		Connection mCon = DatabaseManager.getInstance().open(mCurUserName);
		UserAddressDao addressDao = new UserAddressDao(mCon);
		try {
			if (addressDao.updateUserAddressInTel(account, addressid, tel) > 0) {
				return Api.Factory().response(200, "更新成功", true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Api.Factory().response(401, "更新失败", false);
	}

	private String updateUserAddressInTag(HttpServletRequest request) {
		String account, addressId, tag;
		account = request.getParameter("account");
		addressId = request.getParameter("addressId");
		tag = request.getParameter("tag");
		int addressid;
		try {
			addressid = Integer.parseInt(addressId);
		} catch (NumberFormatException e) {
			return Api.Factory().response(400, "参数错误", false);
		}
		Connection mCon = DatabaseManager.getInstance().open(mCurUserName);
		UserAddressDao addressDao = new UserAddressDao(mCon);
		try {
			if (addressDao.updateUserAddressInTag(account, addressid, tag) > 0) {
				return Api.Factory().response(200, "更新成功", true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Api.Factory().response(401, "更新失败", false);
	}

	private String updateUserAddressInAddress(HttpServletRequest request) {
		String account, addressId, address;
		account = request.getParameter("account");
		addressId = request.getParameter("addressId");
		address = request.getParameter("address");
		int addressid;
		try {
			addressid = Integer.parseInt(addressId);
		} catch (NumberFormatException e) {
			return Api.Factory().response(400, "参数错误", false);
		}
		Connection mCon = DatabaseManager.getInstance().open(mCurUserName);
		UserAddressDao addressDao = new UserAddressDao(mCon);
		try {
			if (addressDao.updateUserAddressInAddress(account, addressid,
					address) > 0) {
				return Api.Factory().response(200, "更新成功", true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Api.Factory().response(401, "更新失败", false);
	}

	private String fetchAllUserAddress(HttpServletRequest request) {
		Connection mCon = DatabaseManager.getInstance().open(mCurUserName);
		UserAddressDao addressDao = new UserAddressDao(mCon);
		List<UserAddress> userAddress = addressDao.fetchAllUserAddress();
		return Api.Factory().response(200, "获取成功", userAddress);
	}

	private String fetchAllUserAddressByAccount(HttpServletRequest request) {
		String account = request.getParameter("account");
		Connection mCon = DatabaseManager.getInstance().open(mCurUserName);
		UserAddressDao addressDao = new UserAddressDao(mCon);
		List<UserAddress> UserAddresss;
		try {
			UserAddresss = addressDao.fetchAllUserAddressByAccount(account);
			return Api.Factory().response(200, "获取成功", UserAddresss);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Api.Factory().response(401, "获取失败", false);

	}

	private String fetchAllUserAddressByAccountNTag(HttpServletRequest request) {
		String account = request.getParameter("account");
		String tag = request.getParameter("tag");
		Connection mCon = DatabaseManager.getInstance().open(mCurUserName);
		UserAddressDao addressDao = new UserAddressDao(mCon);
		List<UserAddress> UserAddresss;
		try {
			UserAddresss = addressDao.fetchAllUserAddressByAccountNTag(account,
					tag);
			return Api.Factory().response(200, "获取成功", UserAddresss);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Api.Factory().response(401, "获取失败", false);
	}

	private String fetchUserAddress(HttpServletRequest request) {
		String account = request.getParameter("account");
		String addressId = request.getParameter("addressId");
		int addressid = Integer.parseInt(addressId);
		Connection mCon = DatabaseManager.getInstance().open(mCurUserName);
		UserAddressDao addressDao = new UserAddressDao(mCon);
		UserAddress userAddress;
		try {
			userAddress = addressDao.fetchUserAddress(account, addressid);
			return Api.Factory().response(200, "获取成功", userAddress);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Api.Factory().response(401, "获取失败", false);
	}

}
