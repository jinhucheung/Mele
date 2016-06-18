package com.mele.test;

import java.sql.Connection;
import java.util.List;

import com.mele.control.DatabaseManager;
import com.mele.dao.ICitySchema;
import com.mele.dao.entity.City;
import com.mele.dao.impl.CityDao;

/**
 * 测试CityDao
 * 
 * @author Jinhu
 * @date 2016/6/11
 */
public class TestCityDao {
	public static void main(String[] args) throws Exception {
		Connection mCon = DatabaseManager.getInstance().open();
		Thread.sleep(500);

		// 显示表级结构
		System.out
				.println("\n--------------------显示表级结构--------------------------");
		System.out.println(ICitySchema.CITY_TABLE_CREATE);
		System.out
				.println("-----------------------------------------------------\n");

		CityDao mCityDao = new CityDao(mCon);

		// 查询所有城市
		System.out
				.println("\n--------------------查询所有城市--------------------------");
		List<City> mCities = mCityDao.fetchAllCities();
		System.out.println(mCities);
		System.out
				.println("-----------------------------------------------------\n");

		// 根据城市编码或城市名查询
		System.out
				.println("\n--------------------根据城市编码或城市名查询所有城市--------------------------");
		System.out.println(mCityDao.fetchCityByCityCode("0752"));
		System.out.println(mCityDao.fetchCityByCityName("北京市"));
		System.out
				.println("-----------------------------------------------------\n");

		// 增加并修改城市
		System.out
				.println("\n--------------------增加城市--------------------------");
		mCityDao.addCity(new City("0683", "小城市"));
		mCityDao.updateCityName("0683", "小城市2333");
		System.out.println(mCityDao.fetchCityByCityCode("0683"));
		System.out
				.println("-----------------------------------------------------\n");
		// 删除城市
		System.out
				.println("\n--------------------删除城市--------------------------");
		mCityDao.deleteCity("0683");
		System.out
				.println("-----------------------------------------------------\n");

		// mCityDao.deleteAllCities();

		Thread.sleep(500);
		DatabaseManager.getInstance().close(mCon);
		System.exit(0);
	}
}
