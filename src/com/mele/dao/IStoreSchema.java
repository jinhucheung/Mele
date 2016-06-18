package com.mele.dao;

/**
 * 描述'storeinfo'表结构
 * 
 * @author Jinhu
 * @date 2016/6/10
 */
public interface IStoreSchema {

	String STORE_TABLE = "storeinfo";

	String COLUMN_ID = "storeId";
	String COLUMN_STORE_NAME = "storeName";
	String COLUMN_ADDRESS = "address";
	String COLUMN_CITY_CODE = "citycode";
	String COLUMN_SHOP_HOURS = "shophours";
	String COLUMN_LATLNG = "latlng";
	String COLUMN_START_PRICE = "startprice";
	String COLUMN_TRANSPORT_PRICE = "transportprice";
	String COLUMN_STORE_LOGO = "storelogo";
	String COLUMN_NOTICE = "notice";
	String COLUMN_CHEAPEN_NOTICE = "cheapennotice";
	String COLUMN_NEWUSER_NOTICE = "newusernotice";
	String COLUMN_TEL = "tel";
	String COLUMN_ACCOUNT = "uaccount";

	String[] STORE_COLUMNS = new String[] {
			//
			COLUMN_ID, COLUMN_STORE_NAME, COLUMN_ADDRESS, COLUMN_CITY_CODE,
			COLUMN_SHOP_HOURS, COLUMN_LATLNG, COLUMN_START_PRICE,
			COLUMN_TRANSPORT_PRICE, COLUMN_STORE_LOGO, COLUMN_NOTICE,
			COLUMN_CHEAPEN_NOTICE, COLUMN_NEWUSER_NOTICE, COLUMN_TEL,
			COLUMN_ACCOUNT //
	};

	String STOREINFO_TABLE_CREATE = "CREATE TABLE IF NO EXISTS "//
			+ STORE_TABLE//
			+ "("//
			+ COLUMN_ID//
			+ " bigint(20) unsigned NOT NULL AUTO_INCREMENT,"//
			+ COLUMN_STORE_NAME//
			+ " varchar(28) DEFAULT NULL,"//
			+ COLUMN_ADDRESS//
			+ " varchar(50) DEFAULT NULL,"//
			+ COLUMN_CITY_CODE//
			+ " char(7) DEFAULT NULL,"//
			+ COLUMN_SHOP_HOURS//
			+ " char(24) DEFAULT NULL,"//
			+ COLUMN_LATLNG//
			+ " varchar(30) DEFAULT NULL,"//
			+ COLUMN_START_PRICE//
			+ " float unsigned DEFAULT NULL,"//
			+ COLUMN_TRANSPORT_PRICE//
			+ " float unsigned DEFAULT NULL,"//
			+ COLUMN_STORE_LOGO//
			+ " varchar(50) DEFAULT NULL,"//
			+ COLUMN_NOTICE//
			+ " varchar(50) DEFAULT NULL,"//
			+ COLUMN_CHEAPEN_NOTICE//
			+ " varchar(50) DEFAULT NULL,"//
			+ COLUMN_NEWUSER_NOTICE//
			+ " varchar(50) DEFAULT NULL,"//
			+ COLUMN_TRANSPORT_PRICE//
			+ " char(11) DEFAULT NULL,"//
			+ COLUMN_TEL//
			+ " char(11) DEFAULT NULL,"//
			+ COLUMN_ACCOUNT//
			+ " char(16) NOT NULL,,"//
			+ " PRIMARY KEY ("//
			+ COLUMN_ID//
			+ " ),"//
			+ " KEY ("//
			+ COLUMN_CITY_CODE//
			+ "),"//
			+ " CONSTRAINT FOREIGN KEY ("//
			+ COLUMN_CITY_CODE//
			+ ")"//
			+ " REFERENCES "//
			+ ICitySchema.CITY_TABLE//
			+ " ("//
			+ ICitySchema.COLUMN_CITY_CODE//
			+ ") ON DELETE CASCADE ON UPDATE CASCADE, "//
			+ " CONSTRAINT FOREIGN KEY ("//
			+ COLUMN_ACCOUNT//
			+ ")"//
			+ " REFERENCES "//
			+ IUserSchema.USER_TABLE//
			+ " ("//
			+ IUserSchema.COLUMN_ACCOUNT//
			+ ") ON DELETE CASCADE ON UPDATE CASCADE "//
			+ ")  ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8";
}
