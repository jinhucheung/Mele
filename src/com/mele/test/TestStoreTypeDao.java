package com.mele.test;

import java.sql.Connection;
import java.util.List;

import com.mele.control.DatabaseManager;
import com.mele.dao.IStoreTypeSchema;
import com.mele.dao.entity.StoreType;
import com.mele.dao.impl.StoreTypeDao;

public class TestStoreTypeDao {
	public static void main(String[] args) throws Exception {
		Connection mCon = DatabaseManager.getInstance().open();
		Thread.sleep(500);

		// 显示表级结构
		System.out
				.println("\n--------------------显示表级结构--------------------------");
		System.out.println(IStoreTypeSchema.STORE_TYPE_TABLE_CREATE);
		System.out
				.println("-----------------------------------------------------\n");

		StoreTypeDao mStoreTypeDao = new StoreTypeDao(mCon);

		// 查询所有商家类型
		System.out
				.println("\n--------------------查询所有商家类型--------------------------");
		List<StoreType> mStoreTypes = mStoreTypeDao.fetchAllStoreTypes();
		System.out.println(mStoreTypes);
		System.out
				.println("-----------------------------------------------------\n");

		// 根据城市编码或城市名查询
		System.out
				.println("\n--------------------根据商家类型查询--------------------------");
		StoreType storeType = mStoreTypeDao.fetchStoreTypeById(3);
		System.out.println(storeType.isNullValue() ? "没有查询到相关数据" : storeType);
		System.out
				.println("-----------------------------------------------------\n");

		// 增加并修改城市
		System.out
				.println("\n--------------------根据商家父级类型查询--------------------------");
		List<StoreType> storeTypes = mStoreTypeDao.fetchStoreTypeBySuperId(1);
		System.out
				.println((null != storeTypes && storeTypes.size() > 0) ? storeTypes
						: "没有查询到相关数据");
		System.out
				.println("-----------------------------------------------------\n");

		// 修改商家类型名
		System.out
				.println("\n--------------------修改商家类型名--------------------------");
		mStoreTypeDao.updateStoreTypeName(1, "修改后");
		System.out.println(mStoreTypeDao.fetchStoreTypeById(1));
		System.out
				.println("-----------------------------------------------------\n");

		// 加入新的商家类型
		System.out
				.println("\n--------------------加入新的商家类型--------------------------");
		storeType = new StoreType(mStoreTypes.size() + 1, "新加入类型", 1);
		mStoreTypeDao.addStoreType(storeType);
		System.out
				.println(mStoreTypeDao.fetchStoreTypeById(mStoreTypes.size() + 1));
		System.out
				.println("-----------------------------------------------------\n");

		// 删除商家类型信息
		System.out
				.println("\n--------------------删除商家类型信息--------------------------");
		mStoreTypeDao.deleteStoreType(mStoreTypes.size() + 1);
		System.out.println(mStoreTypeDao.fetchStoreTypeById(
				mStoreTypes.size() + 1).isNullValue() ? "没有查找到相关数据" : "查找到数据");
		System.out
				.println("-----------------------------------------------------\n");

		// mStoreTypeDao.deleteAllStoreTypes();

		Thread.sleep(500);
		DatabaseManager.getInstance().close();
		System.exit(0);
	}
}
