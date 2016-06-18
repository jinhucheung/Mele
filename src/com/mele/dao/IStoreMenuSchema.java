package com.mele.dao;

/**
 * 描述商家菜谱<br/>
 * 'storemenu'表结构
 * 
 * @author Jinhu
 * @date 2016/6/11
 */
public interface IStoreMenuSchema {
	String STORE_MENU_TABLE = "storemenu";

	String COLUMN_STORE_ID = "storeid";
	String COLUMN_FOOD_ID = "foodid";
	String COLUMN_FOOD_NAME = "foodname";
	String COLUMN_FOOD_PRICES = "foodprices";
	String COLUMN_FOOD_TYPE = "foodtype";
	String COLUMN_FOOD_IC = "foodic";

	String STORE_MENU_COLUMNS[] = { COLUMN_STORE_ID, COLUMN_FOOD_ID,
			COLUMN_FOOD_NAME, COLUMN_FOOD_PRICES, COLUMN_FOOD_TYPE,
			COLUMN_FOOD_IC };

	String STORE_MENU_CREATE = "CREATE TABLE IF NO EXISTS "//
			+ STORE_MENU_TABLE//
			+ "("//
			+ COLUMN_STORE_ID//
			+ " bigint(20) unsigned NOT NULL,"//
			+ COLUMN_FOOD_ID//
			+ " int(10) unsigned NOT NULL,"//
			+ COLUMN_FOOD_NAME//
			+ " varchar(20) DEFAULT NULL,"//
			+ COLUMN_FOOD_PRICES//
			+ " float DEFAULT NULL,"//
			+ COLUMN_FOOD_TYPE//
			+ " varchar(20) DEFAULT NULL,"//
			+ COLUMN_FOOD_IC//
			+ " varchar(50) DEFAULT NULL,"//
			+ " PRIMARY KEY ("//
			+ COLUMN_STORE_ID//
			+ ","//
			+ COLUMN_FOOD_ID//
			+ "), CONSTRAINT FOREINGN KEY ("//
			+ COLUMN_STORE_ID//
			+ ") REFERENCES "//
			+ IStoreSchema.STORE_TABLE//
			+ "("//
			+ IStoreSchema.COLUMN_ID//
			+ ") ON DELETE CASCADE ON UPDATE CASCADE"//
			+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8";
}
