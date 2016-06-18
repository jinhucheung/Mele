package com.mele.dao;

/**
 * 描述商家信息与类型联系<br/>
 * 'storeinfo_type'表结构
 * 
 * @author Jinhu
 * @date 2016/6/11
 */
public interface IStoreInfoNTypeSchema {

	String STOREINFO_TYPE_TABLE = "storeinfo_type";

	String COLUMN_STORE_ID = "storeid";
	String COLUMN_STORE_TYPE_ID = "stypeid";

	String[] STOREINFO_TYPE_COLUMNS = new String[] { COLUMN_STORE_ID,
			COLUMN_STORE_TYPE_ID };

	String STOREINFO_TYPE_TABLE_CREATE = "CREATE TABLE IF NO EXISTS "//
			+ STOREINFO_TYPE_TABLE//
			+ "("//
			+ COLUMN_STORE_ID//
			+ " bigint(20) unsigned NOT NULL,"//
			+ COLUMN_STORE_TYPE_ID//
			+ " int(10) unsigned NOT NULL,"//
			+ " PRIMARY KEY ("//
			+ COLUMN_STORE_ID//
			+ ","//
			+ COLUMN_STORE_TYPE_ID//
			+ " KEY `typefk_idx` ("//
			+ COLUMN_STORE_ID//
			+ "),"//
			+ "CONSTRAINT FOREIGN KEY ("//
			+ COLUMN_STORE_ID//
			+ ")  REFERENCES "//
			+ IStoreSchema.STORE_TABLE//
			+ " ("//
			+ IStoreSchema.COLUMN_ID//
			+ ") ON DELETE CASCADE ON UPDATE CASCADE,"//
			+ "CONSTRAINT FOREIGN KEY ("//
			+ COLUMN_STORE_TYPE_ID//
			+ ")  REFERENCES "//
			+ IStoreTypeSchema.STORE_TYPE_TABLE//
			+ " ("//
			+ IStoreTypeSchema.COLUMN_ID//
			+ ") ON DELETE CASCADE ON UPDATE CASCADE"//
			+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8";
}
