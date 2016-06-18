package com.mele.test;

import java.sql.Connection;
import java.util.List;

import com.mele.control.DatabaseManager;
import com.mele.dao.IStoreInfoNTypeSchema;
import com.mele.dao.entity.StoreInfoNType;
import com.mele.dao.impl.StoreInfoNTypeDao;

/**
 * 测试StoreInfoNTypeDao
 * 
 * @author Jinhu
 * @date 2016/6/11
 */
public class TestStoreInfoNTypeDao {
	public static void main(String[] args) throws Exception {
		Connection mCon = DatabaseManager.getInstance().open();
		Thread.sleep(500);

		// 显示表级结构
		System.out
				.println("\n--------------------显示表级结构--------------------------");
		System.out.println(IStoreInfoNTypeSchema.STOREINFO_TYPE_TABLE_CREATE);
		System.out
				.println("-----------------------------------------------------\n");

		StoreInfoNTypeDao mStoreInfoNTypeDao = new StoreInfoNTypeDao(mCon);

		// 查询所有城市
		System.out
				.println("\n--------------------查询所有商家编号与类型联系--------------------------");
		List<StoreInfoNType> mStoreInfoNType = mStoreInfoNTypeDao
				.fetchAllStoreInfoNTypes();
		System.out.println(mStoreInfoNType);
		System.out
				.println("-----------------------------------------------------\n");

		// 根据商家编号查询所有商家类型联系
		System.out
				.println("\n--------------------根据商家编号查询所有商家类型联系--------------------------");
		mStoreInfoNType = mStoreInfoNTypeDao
				.fetchAllStoreInfoNTypesByStoreId(3);
		System.out.println(mStoreInfoNType);
		System.out
				.println("-----------------------------------------------------\n");

		// 根据商家类型编号查询所有商家类型联系
		System.out
				.println("\n--------------------根据商家类型编号查询所有商家类型联系--------------------------");
		mStoreInfoNType = mStoreInfoNTypeDao.fetchAllStoreInfoNTypesByTypeId(8);
		System.out.println(mStoreInfoNType);
		System.out
				.println("-----------------------------------------------------\n");

		// 添加商家信息与类型联系
		System.out
				.println("\n--------------------添加商家信息与类型联系--------------------------");
		System.out.println("请不要插入商家或商家类型表中不存在的编号!!");
		StoreInfoNType infoNType = new StoreInfoNType(10, 12);
		mStoreInfoNTypeDao.addStoreInfoNType(infoNType);
		System.out.println(mStoreInfoNTypeDao
				.fetchAllStoreInfoNTypesByStoreId(10));
		System.out
				.println("-----------------------------------------------------\n");

		// 删除指定商家信息与类型联系
		System.out
				.println("\n--------------------删除指定商家信息与类型联系--------------------------");
		mStoreInfoNTypeDao.deleteStoreInfoNType(new StoreInfoNType(10, 12));
		System.out
				.println("-----------------------------------------------------\n");

		// mStoreInfoNTypeDao.deleteAllStoreInfoNType();

		Thread.sleep(500);
		DatabaseManager.getInstance().close();
		System.exit(0);
	}
}
