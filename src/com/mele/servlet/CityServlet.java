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
import com.mele.dao.ICitySchema;
import com.mele.dao.entity.City;
import com.mele.dao.impl.CityDao;
import com.mele.file.cache.FileCache;

import static com.mele.Constants.*;

/**
 * 
 * @author Administrator
 *
 */
@WebServlet("/CityServlet")
public class CityServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public CityServlet() {
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
		// else
		if (func.equals("getCityByCityCode")) {
			jsondata = this.getCityByCityCode(request);
		} else if (func.equals("getAllCity")) {
			jsondata = this.getAllCity(request);
		} else if (func.equals("addcity")) {
			jsondata = this.addcity(request);
		} else if (func.equals("deleteCity")) {
			jsondata = this.deleteCity(request);
		} else if (func.equals("deleteAllCities")) {
			jsondata = this.deleteAllCities();
		} else if (func.equals("updateCityName")) {
			jsondata = this.updateCityName(request);
		} else if (func.equals("fetchAllCities")) {
			jsondata = this.fetchAllCities();
		} else if (func.equals("fetchCityByCityName")) {
			jsondata = this.fetchCityByCityName(request);
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
	 * 根据城市编码获取城市名称 CityServlet?func=getCityByCityCode&cityCode=0753
	 * 
	 * @param request
	 * @return
	 */
	private String getCityByCityCode(HttpServletRequest request) {
		String cityCode = request.getParameter("cityCode");
		if (cityCode == null) {
			return Api.Factory().response(400, "参数错误", false);
		}
		Connection mCon = DatabaseManager.getInstance().open(mCurUserName);
		CityDao mUserDao = new CityDao(mCon);
		City city;
		try {
			city = mUserDao.fetchCityByCityCode(cityCode);
		} catch (Exception e) {
			return Api.Factory().response(400, "参数错误", false);
		}

		if (city != null)
			return Api.Factory().response(200, "获取成功", city);
		else {
			return Api.Factory().response(401, "获取失败", city);
		}
	}

	/**
	 * 获取所有城市 CityServlet?func=getAllCity
	 * 
	 * @param request
	 * @return
	 */
	private String getAllCity(HttpServletRequest request) {

		Connection mCon = DatabaseManager.getInstance().open(mCurUserName);
		CityDao mUserDao = new CityDao(mCon);
		List<City> cities = mUserDao.fetchAllCities();
		if (cities != null)
			return Api.Factory().response(200, "获取成功", cities);
		else {
			return Api.Factory().response(401, "获取失败", false);
		}
	}

	/**
	 * 添加城市 CityServlet?func=addcity&cityCode=9999&cityName=New
	 * 
	 * @param request
	 * @return
	 */
	private String addcity(HttpServletRequest request) {
		String cityCode = request.getParameter("cityCode");
		String cityName = request.getParameter("cityName");
		System.out.println("城市名" + cityName);
		if (cityCode == null || cityName == null) {
			return Api.Factory().response(400, "参数错误", false);
		}
		Connection mCon = DatabaseManager.getInstance().open(mCurUserName);
		CityDao cityDao = new CityDao(mCon);
		City city = new City(cityCode, cityName);
		boolean isAdd = false;
		try {
			isAdd = cityDao.addCity(city);
		} catch (Exception e) {
			return Api.Factory().response(400, "参数错误", false);
		}
		if (isAdd) {
			return Api.Factory().response(200, "添加成功", true);
		} else {
			return Api.Factory().response(401, "添加失败", false);
		}

	}

	private String deleteCity(HttpServletRequest request) {
		String cityCode = request.getParameter("cityCode");
		Connection mCon = DatabaseManager.getInstance().open(mCurUserName);
		CityDao cityDao = new CityDao(mCon);
		boolean isDel = false;
		try {
			isDel = cityDao.deleteCity(cityCode);
		} catch (Exception e) {

			return Api.Factory().response(400, "参数错误", false);
		}
		if (isDel) {
			return Api.Factory().response(200, "删除成功", true);
		}
		return Api.Factory().response(401, "删除失败", false);
	}

	private String deleteAllCities() {
		Connection mCon = DatabaseManager.getInstance().open(mCurUserName);
		CityDao cityDao = new CityDao(mCon);
		boolean isDel = false;
		try {
			isDel = cityDao.deleteAllCities();
		} catch (Exception e) {
			return Api.Factory().response(400, "参数错误", false);
		}
		if (isDel) {
			return Api.Factory().response(200, "删除成功", true);
		} else
			return Api.Factory().response(401, "删除失败", false);
	}

	private String updateCityName(HttpServletRequest request) {
		String cityCode = request.getParameter("cityCode");
		String cityName = request.getParameter("cityName");
		System.out.println("城市名" + cityName);
		if (cityCode == null || cityName == null) {
			return Api.Factory().response(400, "参数错误", false);
		}
		Connection mCon = DatabaseManager.getInstance().open(mCurUserName);
		CityDao cityDao = new CityDao(mCon);
		int isUp = -200;
		try {
			isUp = cityDao.updateCityName(cityCode, cityName);
		} catch (Exception e) {
			return Api.Factory().response(400, "参数错误", false);
		}
		if (isUp > 0) {
			return Api.Factory().response(200, "更新成功", true);
		} else {
			return Api.Factory().response(401, "更新失败", false);
		}
	}

	private String fetchAllCities() {
		Connection mCon = DatabaseManager.getInstance().open(mCurUserName);
		CityDao cityDao = new CityDao(mCon);
		List<City> cities = cityDao.fetchAllCities();
		if (cities != null) {
			return Api.Factory().response(200, "获取成功", cities);
		} else {
			return Api.Factory().response(401, "获取失败", false);
		}
	}

	private String fetchCityByCityName(HttpServletRequest request) {
		Connection mCon = DatabaseManager.getInstance().open(mCurUserName);
		CityDao cityDao = new CityDao(mCon);
		String cityName = request.getParameter("cityName");
		City city;
		try {
			city = cityDao.fetchCityByCityName(cityName);
		} catch (Exception e) {
			return Api.Factory().response(400, "参数错误", false);
		}
		if (city != null) {
			return Api.Factory().response(200, "获取成功", city);
		} else {
			return Api.Factory().response(401, "获取失败", false);
		}

	}

}
