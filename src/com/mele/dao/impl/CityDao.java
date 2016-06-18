package com.mele.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mele.control.DbContentProvider;
import com.mele.dao.ICityDao;
import com.mele.dao.ICitySchema;
import com.mele.dao.entity.City;
import com.mele.dao.util.ContentValues;
import com.mele.util.LogUtil;
import com.mele.util.TextUtils;

/**
 * 'Cities'数据访问接口
 * 
 * @author Jinhu
 * @date 2016/6/1
 */
public class CityDao extends DbContentProvider implements ICitySchema, ICityDao {
	private static final String TAG = CityDao.class.getSimpleName();

	/** 插入数据集 。 */
	private ContentValues insertValues;

	public CityDao(Connection conn) {
		super(conn);
	}

	/**
	 * 将当前ResultSet行数据转成City对象
	 */
	@Override
	protected City ResultSetToEntity(ResultSet resultSet) {
		City mCity = new City();
		try {
			if (null != resultSet) {
				String cityCode = resultSet.getString(COLUMN_CITY_CODE);
				String cityName = resultSet.getString(COLUMN_CITY_NAME);

				mCity.setCityCode(cityCode);
				mCity.setCityName(cityName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			LogUtil.warning(TAG, "ResultSetToEntity()... 异常");
		}
		return mCity;
	}

	/**
	 * 添加城市信息
	 */
	@Override
	public boolean addCity(City city) throws Exception{
		if (null == city)
			throw new NullPointerException();

		setContentValues(city);
		try {
			return super.insert(CITY_TABLE, insertValues);
		} catch (SQLException e) {
			e.printStackTrace();
			LogUtil.warning(TAG, "addCity(City)... 插入数据异常");
		}
		return false;
	}

	/**
	 * 添加城市列表信息
	 */
	@Override
	public boolean addCities(List<City> cities) throws Exception{
		if (null == cities || 0 == cities.size())
			throw new NullPointerException("cities 数据不能为空！");
		try {
			super.setAutoCommit(false); // 启动事务提交
			for (City city : cities) {
				setContentValues(city);
				super.insert(CITY_TABLE, insertValues);
			}
			super.commit();
			super.setAutoCommit(true);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			super.rollback();
			LogUtil.warning(TAG, "addCities(List<City>)...增加城市列表数据异常,已回退操作!!!");
		}
		super.setAutoCommit(true);
		return false;
	}

	/**
	 * 根据CityCode删除表中数据
	 */
	@Override
	public boolean deleteCity(String cityCode) throws Exception{
		if (TextUtils.isEmpty(cityCode))
			throw new NullPointerException("cityCode 不能为空或空子串");

		final String selection = COLUMN_CITY_CODE + " = ? ";
		final String selectionArgs[] = { cityCode };
		try {
			boolean isDeleted = super.delete(CITY_TABLE, selection,
					selectionArgs);
			if (isDeleted)
				LogUtil.info(TAG, "删除'" + CITY_TABLE + "'表数据:"
						+ COLUMN_CITY_CODE + "= " + cityCode);
			return isDeleted;
		} catch (SQLException e) {
			e.printStackTrace();
			LogUtil.warning(TAG, "deleteCity(String)...删除数据异常");
		}
		return false;
	}

	/**
	 * 删除所有城市数据
	 * 
	 * @return
	 */
	@Override
	public boolean deleteAllCities() {
		try {
			boolean isDeleted = super.delete(CITY_TABLE, null, null);
			if (isDeleted)
				LogUtil.info(TAG, "删除'" + CITY_TABLE + "'所有数据");
			return isDeleted;
		} catch (SQLException e) {
			e.printStackTrace();
			LogUtil.warning(TAG, "deleteAllCities()...删除数据异常");
		}
		return false;
	}

	/**
	 * 根据城市编码更新成名名
	 * 
	 * @param cityCode
	 * @param cityName
	 * @return
	 */
	@Override
	public int updateCityName(String cityCode, String cityName) throws Exception{
		if (TextUtils.isEmpty(cityCode))
			throw new NullPointerException("cityCode 不能为空或空子串");

		final ContentValues updateValue = new ContentValues();
		updateValue.put(COLUMN_CITY_NAME, cityName);

		final String selection = COLUMN_CITY_CODE + "= ?";
		final String selectionArgs[] = { cityCode };

		try {
			int updatedRows = super.update(CITY_TABLE, updateValue, selection,
					selectionArgs);
			if (updatedRows > 0)
				LogUtil.info(TAG, "updateCityName()...  updated row : "
						+ updatedRows);
			return updatedRows;
		} catch (SQLException e) {
			e.printStackTrace();
			LogUtil.warning(TAG, "updateCityName()...更新城市名异常");
		}
		return 0;
	}

	/**
	 * 获取所有城市信息
	 * 
	 * @return
	 */
	@Override
	public List<City> fetchAllCities() {
		List<City> cities = new ArrayList<City>();
		try {
			ResultSet mResultSet = super.query(CITY_TABLE, CITY_COLUMNS, null,
					null, COLUMN_CITY_CODE);
			if (null != mResultSet) {
				while (mResultSet.next()) {
					City city = ResultSetToEntity(mResultSet);
					if (null != city && !TextUtils.isEmpty(city.getCityCode()))
						cities.add(city);
				}
				mResultSet.close();
			}
			return cities;
		} catch (SQLException e) {
			e.printStackTrace();
			LogUtil.warning(TAG, "fetchAllCities()...查询异常");
		}
		return null;
	}

	/**
	 * 根据城市获取城市信息
	 * 
	 * @param cityName
	 * @return
	 */
	@Override
	public City fetchCityByCityName(String cityName) throws Exception{
		if (TextUtils.isEmpty(cityName))
			throw new NullPointerException("cityName 不能为空或空子串");

		final String selection = COLUMN_CITY_NAME + "= ? ";
		final String selectionArgs[] = { cityName };

		City mCity = new City();
		try {
			ResultSet mResultSet = super.query(CITY_TABLE, CITY_COLUMNS,
					selection, selectionArgs, COLUMN_CITY_CODE);
			if (null != mResultSet && mResultSet.next()) {
				mCity = ResultSetToEntity(mResultSet);
				mResultSet.close();
			}
			return mCity;
		} catch (SQLException e) {
			e.printStackTrace();
			LogUtil.warning(TAG, "fetchCityByCityName()...");
		}
		return null;
	}

	/**
	 * 根据城市编码获取城市信息
	 * 
	 * @param cityCode
	 * @return
	 */
	@Override
	public City fetchCityByCityCode(String cityCode) throws Exception{
		if (TextUtils.isEmpty(cityCode))
			throw new NullPointerException("cityCode 不能为空或空子串");

		final String selection = COLUMN_CITY_CODE + "= ? ";
		final String selectionArgs[] = { cityCode };

		City mCity = new City();
		try {
			ResultSet mResultSet = super.query(CITY_TABLE, CITY_COLUMNS,
					selection, selectionArgs, COLUMN_CITY_CODE);
			if (null != mResultSet && mResultSet.next()) {
				mCity = ResultSetToEntity(mResultSet);
				mResultSet.close();
			}

			return mCity;
		} catch (SQLException e) {
			e.printStackTrace();
			LogUtil.warning(TAG, "fetchCityByCityCode()...");
		}
		return null;
	}

	private void setContentValues(City city) {
		insertValues = new ContentValues();
		insertValues.put(COLUMN_CITY_CODE, city.getCityCode());
		insertValues.put(COLUMN_CITY_NAME, city.getCityName());
	}

	public ContentValues getContentValues() {
		return insertValues;
	}

}
