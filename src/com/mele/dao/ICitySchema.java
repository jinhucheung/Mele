package com.mele.dao;

/**
 * 描述'cities'表结构
 * 
 * @author Jinhu
 * @date 2016/6/10
 */
public interface ICitySchema {

	String CITY_TABLE = "cities";
	String COLUMN_CITY_CODE = "citycode";
	String COLUMN_CITY_NAME = "cityname";

	String[] CITY_COLUMNS = new String[] { COLUMN_CITY_CODE, COLUMN_CITY_NAME };

	String CITY_TABLE_CREATE = "CREATE TABLE IF NO EXISTS "//
			+ CITY_TABLE//
			+ "("//
			+ COLUMN_CITY_CODE//
			+ " CHAR(7) NOT NULL,"//
			+ COLUMN_CITY_NAME//
			+ " VARCHAR(26) DEFAULT NULL,"//
			+ " PRIMARY KEY ("//
			+ COLUMN_CITY_CODE//
			+ "),"//
			+ " UNIQUE KEY ("//
			+ COLUMN_CITY_CODE//
			+ ")"//
			+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8";
}
