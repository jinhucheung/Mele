package com.mele.test;

import java.sql.Connection;
import java.util.List;

import com.mele.control.DatabaseManager;
import com.mele.dao.IStoreSchema;
import com.mele.dao.entity.Store;
import com.mele.dao.impl.StoreDao;

/**
 * 测试StoreDao
 * 
 * @author Jinhu
 * @date 2016/6/11
 */
public class TestStoreDao {
	public static void main(String[] args) throws Exception {
		Connection mCon = DatabaseManager.getInstance().open();
		Thread.sleep(500);

		// 显示表级结构
		System.out
				.println("\n--------------------显示表级结构--------------------------");
		System.out.println(IStoreSchema.STOREINFO_TABLE_CREATE);
		System.out
				.println("-----------------------------------------------------\n");

		StoreDao mStoreDao = new StoreDao(mCon);
		int storeNums;
		// 获取所有商家信息(包含商家类型名)
		System.out
				.println("\n--------------------获取所有商家信息--------------------------");
		List<Store> mStores = mStoreDao.fetchAllStores();
		storeNums = mStores.size();
		for (Store store : mStores) {
			System.out.println(store);
		}
		System.out
				.println("-----------------------------------------------------\n");

		// 获取某城市所有商家
		System.out
				.println("\n--------------------获取某城市所有商家--------------------------");
		mStores = mStoreDao.fetchAllStoresByCityCode("0753");
		for (Store store : mStores) {
			System.out.println(store);
		}
		System.out
				.println("-----------------------------------------------------\n");

		// 根据起送价获取所有商家
		System.out
				.println("\n--------------------根据起送价获取所有商家--------------------------");
		mStores = mStoreDao.fetchAllStoresByStartPrice(8, 12);
		for (Store store : mStores) {
			System.out.println(store);
		}
		System.out
				.println("-----------------------------------------------------\n");

		// 根据商家类型Id获取所有商家
		System.out
				.println("\n--------------------根据商家类型Id获取所有商家--------------------------");
		mStores = mStoreDao.fetchAllStoresByTypeId(36);
		for (Store store : mStores) {
			System.out.println(store);
		}
		System.out
				.println("-----------------------------------------------------\n");

		// 根据商家Id获取商家
		Store mStore;
		System.out
				.println("\n--------------------根据商家Id获取商家--------------------------");
		System.out.println(mStore = mStoreDao.fetchStoreByStoreId(1));
		System.out
				.println("-----------------------------------------------------\n");

		// 添加商家信息
		System.out
				.println("\n--------------------添加商家信息--------------------------");
		mStore.setStoreId(storeNums + 1);
		mStore.setStoreName("豆加一233");
		mStore.setNotice("Hello");
		mStoreDao.addStore(mStore);
		System.out
				.println("-----------------------------------------------------\n");

		// 更新商家信息
		System.out
				.println("\n--------------------更新商家信息--------------------------");
		mStoreDao.updateStoreName(1, "豆加一23");
		System.out.println(mStoreDao.fetchStoreByStoreId(1));
		mStoreDao.updateStoreName(1, "豆加一");
		System.out
				.println("-----------------------------------------------------\n");

		// 删除商家信息
		System.out
				.println("\n--------------------删除商家信息--------------------------");
		mStoreDao.deleteStore(storeNums + 1);
		System.out
				.println("-----------------------------------------------------\n");

		// mStoreDao.deleteAllStores();

		Thread.sleep(500);
		DatabaseManager.getInstance().close();
		System.exit(0);

	}
}
