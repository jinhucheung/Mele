package com.mele.dao;

/**
 * 描述'storetype'表结构
 * 
 * @author Jinhu
 * @date 2016/6/11
 */
public interface IStoreTypeSchema {

	String STORE_TYPE_TABLE = "storetype";

	String COLUMN_ID = "stypeid";
	String COLUMN_TYPE_NAME = "typename";
	String COLUMN_SUPER_ID = "supertypeid";

	String[] STORE_TYPE_COLUMNS = new String[] { COLUMN_ID, COLUMN_TYPE_NAME,
			COLUMN_SUPER_ID };

	String STORE_TYPE_TABLE_CREATE = "CREATE TABLE IF NO EXISTS "//
			+ STORE_TYPE_TABLE//
			+ "("//
			+ COLUMN_ID//
			+ " int(10) unsigned NOT NULL AUTO_INCREMENT,"//
			+ COLUMN_TYPE_NAME//
			+ " varchar(10) DEFAULT NULL,"//
			+ COLUMN_SUPER_ID//
			+ " int(10) unsigned DEFAULT NULL,"//
			+ " PRIMARY KEY ("//
			+ COLUMN_ID//
			+ "),"//
			+ " KEY `SuperTypeFK_idx` ("//
			+ COLUMN_SUPER_ID//
			+ "),"//
			+ "CONSTRAINT FOREIGN KEY ("//
			+ COLUMN_SUPER_ID//
			+ ")"//
			+ " REFERENCES "//
			+ STORE_TYPE_TABLE//
			+ "("//
			+ COLUMN_ID//
			+ ") ON DELETE CASCADE ON UPDATE CASCADE"//
			+ ") ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8";
}
