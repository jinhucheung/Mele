package com.mele.test;

import java.sql.Connection;
import java.util.List;

import com.mele.control.DatabaseManager;
import com.mele.dao.IStoreMenuSchema;
import com.mele.dao.entity.Food;
import com.mele.dao.impl.StoreMenuDao;

/**
 * 测试StoreMenuDao
 * 
 * @author Jinhu
 * @date 2016/6/11
 */
public class TestStoreMenuDao {
	public static void main(String[] args) throws Exception {
		Connection mCon = DatabaseManager.getInstance().open();
		Thread.sleep(500);

		// 显示表级结构
		System.out
				.println("\n--------------------显示表级结构--------------------------");
		System.out.println(IStoreMenuSchema.STORE_MENU_CREATE);
		System.out
				.println("-----------------------------------------------------\n");

		StoreMenuDao mStoreMenuDao = new StoreMenuDao(mCon);
		int foodNums;
		// 获取所有商家菜式信息
		System.out
				.println("\n--------------------获取所有商家菜式信息--------------------------");
		List<Food> mFoods = mStoreMenuDao.fetchAllFoods();
		foodNums = mFoods.size();
		for (Food food : mFoods) {
			System.out.println(food);
		}
		System.out
				.println("-----------------------------------------------------\n");

		// 获取指定商家价格区间内菜式信息
		System.out
				.println("\n--------------------获取指定商家价格区间内菜式信息--------------------------");
		mFoods = mStoreMenuDao.fetchAllFoodsByFoodPrices(1, 4, 6);
		for (Food food : mFoods) {
			System.out.println(food);
		}
		System.out
				.println("-----------------------------------------------------\n");

		// 获取指定商家菜式类型的菜式信息
		System.out
				.println("\n--------------------获取指定商家菜式类型的菜式信息--------------------------");
		mFoods = mStoreMenuDao.fetchAllFoodsByFoodType(1, "美味潮汕粽");
		for (Food food : mFoods) {
			System.out.println(food);
		}
		System.out
				.println("-----------------------------------------------------\n");

		// 根据商家Id及一组菜式Id获取菜式信息
		System.out
				.println("\n--------------------根据商家Id及一组菜式Id获取菜式信息--------------------------");
		mFoods = mStoreMenuDao.fetchFoods(1, new int[] { 1, 4, 3, 6 });
		for (Food food : mFoods) {
			System.out.println(food);
		}
		System.out
				.println("-----------------------------------------------------\n");

		System.out
				.println("\n--------------------获取指定商家及菜式编号的信息-------------------------");
		System.out.println(mStoreMenuDao.fetchFood(1, 1));
		System.out
				.println("\n--------------------获取指定商家编号的信息-------------------------");
		mFoods = mStoreMenuDao.fetchAllFoodsByStoreId(1);
		foodNums = mFoods.size();
		for (Food food : mFoods) {
			System.out.println(food);
		}
		System.out
				.println("-----------------------------------------------------\n");

		// 添加菜式信息
		System.out
				.println("\n-------------------- 添加菜式信息-------------------------");
		Food food = new Food();
		food.setStoreId(1);
		food.setFoodId(foodNums + 1);
		food.setFoodName("珍珠奶茶");
		food.setFoodPrices(5);
		food.setFoodType("夏日新饮品");
		mStoreMenuDao.addFood(food);
		System.out.println(mStoreMenuDao.fetchFood(1, foodNums + 1));
		System.out
				.println("-----------------------------------------------------\n");

		// 获取指定商家的菜式类型
		System.out
				.println("\n-------------------- 获取指定商家的菜式类型-------------------------");
		System.out.println(mStoreMenuDao.fetchAllFoodTypeByStoreId(1));
		System.out
				.println("-----------------------------------------------------\n");

		mStoreMenuDao.deleteFood(1, foodNums + 1);

		// mStoreMenuDao.deleteAllFoods();

		Thread.sleep(500);
		DatabaseManager.getInstance().close();
		System.exit(0);
	}
}
