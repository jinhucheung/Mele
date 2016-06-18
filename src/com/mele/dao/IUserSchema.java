package com.mele.dao;

/**
 * 描述'user'表结构
 * 
 * @author Jinhu
 * @date 2016/6/12
 */
public interface IUserSchema {

	String USER_TABLE = "user";

	String COLUMN_ACCOUNT = "account";
	String COLUMN_PASSWORD = "password";
	String COLUMN_TEL = "tel";
	String COLUMN_EMAIL = "email";
	String COLUMN_PAYPASSWORD = "paypassword";
	String COLUMN_HEAD_IC = "headic";
	String COLUMN_LEVEL = "level";

	String USER_COLUMNS[] = { COLUMN_ACCOUNT, COLUMN_PASSWORD, COLUMN_TEL,
			COLUMN_EMAIL, COLUMN_PAYPASSWORD, COLUMN_HEAD_IC, COLUMN_LEVEL };

	String USER_TABLE_CREATE = "CREATE TABLE IF NO EXISTS "//
			+ USER_TABLE//
			+ "("//
			+ COLUMN_ACCOUNT//
			+ " char(16) NOT NULL,"//
			+ COLUMN_PASSWORD//
			+ " varchar(20) DEFAULT NULL,"//
			+ COLUMN_TEL//
			+ " char(11) DEFAULT NULL,"//
			+ COLUMN_EMAIL//
			+ " varchar(30) DEFAULT NULL,"//
			+ COLUMN_PAYPASSWORD//
			+ " varchar(20) DEFAULT NULL,"//
			+ COLUMN_HEAD_IC//
			+ " varchar(50) DEFAULT NULL,"//
			+ COLUMN_LEVEL//
			+ " int(10) unsigned DEFAULT NULL,"//
			+ " PRIMARY KEY ("//
			+ COLUMN_ACCOUNT//
			+ "),"//
			+ " UNIQUE KEY("//
			+ COLUMN_ACCOUNT//
			+ ")"//
			+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8";
}
