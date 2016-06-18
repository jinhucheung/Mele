package com.mele.dao;

/**
 * 描述'uaddress'表结构
 * 
 * @author Jinhu
 * @date 2016/6/12
 */
public interface IUserAddressSchema {

	String USER_ADDRESS_TABLE = "uaddress";

	String COLUMN_ID = "uaddressid";
	String COLUMN_USER_ACCOUNT = "uaccount";
	String COLUMN_CONTACT = "ucontact";
	String COLUMN_SEX = "usex";
	String COLUMN_TEL = "utel";
	String COLUMN_ADDRESS = "uaddress";
	String COLUMN_TAG = "utag";

	String[] USER_ADDRESS_COLUMNS = { COLUMN_ID, COLUMN_USER_ACCOUNT,
			COLUMN_CONTACT, COLUMN_SEX, COLUMN_TEL, COLUMN_ADDRESS, COLUMN_TAG };

	String USER_ADDRESS_TABLE_CREATE = "CREATE TABLE IF NO EXISTS "//
			+ COLUMN_ID//
			+ " int(10) unsigned NOT NULL,"//
			+ COLUMN_USER_ACCOUNT//
			+ " char(16) NOT NULL,"//
			+ COLUMN_CONTACT//
			+ " varchar(12) DEFAULT NULL,"//
			+ COLUMN_SEX//
			+ " int(11) DEFAULT NULL,"//
			+ COLUMN_TEL//
			+ " char(11) DEFAULT NULL,"//
			+ COLUMN_ADDRESS//
			+ " varchar(20) DEFAULT NULL,"//
			+ COLUMN_TAG//
			+ " varchar(8) DEFAULT NULL,"//
			+ "  PRIMARY KEY ("//
			+ COLUMN_ID//
			+ ","//
			+ COLUMN_USER_ACCOUNT//
			+ "),"//
			+ " KEY ("//
			+ COLUMN_USER_ACCOUNT//
			+ "),"//
			+ "  CONSTRAINT  FOREIGN KEY ("//
			+ COLUMN_USER_ACCOUNT//
			+ ") REFERENCES "//
			+ IUserSchema.USER_TABLE//
			+ " ("//
			+ IUserSchema.COLUMN_ACCOUNT//
			+ ") ON DELETE CASCADE ON UPDATE CASCADE"//
			+ " ) ENGINE=InnoDB DEFAULT CHARSET=utf8";
}
