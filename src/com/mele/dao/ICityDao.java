package com.mele.dao;

import java.util.List;

import com.mele.dao.entity.City;

/**
 * 定义'cities'数据访问接口模型
 * 
 * @author Jinhu
 * @date 2016/6/10
 */
public interface ICityDao {

	/**
	 * 添加City
	 * 
	 * @param city
	 * @return
	 * @throws Exception 
	 */
	public boolean addCity(City city) throws Exception;

	/**
	 * 添加City列表
	 * 
	 * @param cities
	 * @return
	 * @throws Exception 
	 */
	public boolean addCities(List<City> cities) throws Exception;

	/**
	 * 删除城市
	 * 
	 * @param cityCode
	 * @return
	 * @throws Exception 
	 */
	public boolean deleteCity(String cityCode) throws Exception;

	/**
	 * 删除所有城市
	 * 
	 * @return
	 */
	public boolean deleteAllCities();

	/**
	 * 修改城市名
	 * 
	 * @param cityCode
	 * @param cityName
	 * @return
	 * @throws Exception 
	 */
	public int updateCityName(String cityCode, String cityName) throws Exception;

	/**
	 * 查询所有城市
	 * 
	 * @return
	 */
	public List<City> fetchAllCities();

	/**
	 * 根据城市名查询
	 * 
	 * @param cityName
	 * @return
	 * @throws Exception 
	 */
	public City fetchCityByCityName(String cityName) throws Exception;

	/**
	 * 根据城市编码查询
	 * 
	 * @param cityCode
	 * @return
	 * @throws Exception 
	 */
	public City fetchCityByCityCode(String cityCode) throws Exception;

}
