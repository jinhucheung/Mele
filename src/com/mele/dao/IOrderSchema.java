package com.mele.dao;

/**
 * 描述'uoder'表级结构
 * 
 * @author Jinhu
 * @date 2016/6/12
 */
public interface IOrderSchema {
	String ORDER_TABLE = "uorder";

	String COLUMN_ACCOUNT = "uaccount";
	String COLUMN_ORDER_CODE = "ordercode";
	String COLUMN_STORE_ID = "storeId";
	String COLUMN_ADDRESS_ID = "uaddressid";
	String COLUMN_ORDER_FOOD_ID = "orderfoodid";
	String COLUMN_ORDER_FOOD_PRICE_X_NUM = "orderfoodpricenum";
	String COLUMN_NOTE = "uNote";
	String COLUMN_ORDER_DATE = "orderdate";
	String COLUMN_COMMENT = "comment";

	String[] ORDER_COLUMNS = { COLUMN_ACCOUNT, COLUMN_ORDER_CODE,
			COLUMN_STORE_ID, COLUMN_ADDRESS_ID, COLUMN_ORDER_FOOD_ID,
			COLUMN_ORDER_FOOD_PRICE_X_NUM, COLUMN_NOTE, COLUMN_ORDER_DATE,
			COLUMN_COMMENT };

	String ORDER_TABLE_CREATE = "CREATE TABLE IF NO EXISTS "//
			+ ORDER_TABLE//
			+ "("//
			+ COLUMN_ACCOUNT//
			+ " char(16) NOT NULL,"//
			+ COLUMN_ORDER_CODE//
			+ " char(16) NOT NULL,"//
			+ COLUMN_STORE_ID//
			+ " bigint(20) unsigned DEFAULT NULL,"//
			+ COLUMN_ADDRESS_ID//
			+ " int(10) unsigned DEFAULT NULL,"//
			+ COLUMN_ORDER_FOOD_ID//
			+ " varchar(25) DEFAULT NULL,"//
			+ COLUMN_ORDER_FOOD_PRICE_X_NUM//
			+ " varchar(50) DEFAULT NULL,"//
			+ COLUMN_NOTE//
			+ " char(50) DEFAULT NULL,"//
			+ COLUMN_ORDER_DATE//
			+ " datetime DEFAULT NULL,"//
			+ COLUMN_COMMENT//
			+ " varchar(50) DEFAULT NULL,"//
			+ "  PRIMARY KEY ("//
			+ COLUMN_ACCOUNT//
			+ ","//
			+ COLUMN_ORDER_CODE//
			+ "),"//
			+ " UNIQUE KEY ("//
			+ COLUMN_ORDER_CODE//
			+ "),"//
			+ " KEY ("//
			+ COLUMN_STORE_ID//
			+ "),"//
			+ " KEY ("//
			+ COLUMN_ADDRESS_ID//
			+ "),"//
			+ " CONSTRAINT FOREIGN KEY ("//
			+ COLUMN_STORE_ID//
			+ ") REFERENCES "//
			+ IStoreSchema.STORE_TABLE//
			+ "("//
			+ IStoreSchema.COLUMN_ID//
			+ ")  ON DELETE CASCADE ON UPDATE CASCADE,"//
			+ " CONSTRAINT FOREIGN KEY ("//
			+ COLUMN_ACCOUNT//
			+ ") REFERENCES "//
			+ IUserSchema.USER_TABLE//
			+ "("//
			+ IUserSchema.COLUMN_ACCOUNT//
			+ ") ON DELETE CASCADE ON UPDATE CASCADE,"//
			+ " CONSTRAINT FOREIGN KEY ("//
			+ COLUMN_ADDRESS_ID//
			+ ") REFERENCES "//
			+ IUserAddressSchema.USER_ADDRESS_TABLE//
			+ "("//
			+ IUserAddressSchema.COLUMN_ID//
			+ ") ON DELETE CASCADE ON UPDATE CASCADE"//
			+ " ) ENGINE=InnoDB DEFAULT CHARSET=utf8";
}
