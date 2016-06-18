package com.mele.dao.entity;

import com.mele.util.TextUtils;

/**
 * 城市信息
 * 
 * @author Jinhu
 * @date 2016/6/10
 */
public class City {

	/** 城市编码 。 */
	private String cityCode;

	/** 城市名 。 */
	private String cityName;

	public City() {
		this("", "");
	}

	public City(String cityCode, String cityName) {
		this.cityCode = cityCode;
		this.cityName = cityName;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@Override
	public String toString() {
		return "CityCode: " + cityCode + " , " + "CityName: " + cityName;
	}

	@Override
	public boolean equals(Object obj) {
		if (null == obj)
			return false;
		if (this == obj)
			return true;
		if (obj instanceof City) {
			City another = (City) obj;
			return cityCode.equals(another.cityCode);
		}
		return false;
	}

	@Override
	public int hashCode() {
		if (TextUtils.isEmpty(cityCode)) {
			return super.hashCode();
		}
		return Integer.parseInt(cityCode);
	}

	public boolean isNullValue() {
		return TextUtils.isEmpty(cityCode);
	}
}
