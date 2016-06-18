package com.mele.dao;

import java.util.List;

import com.mele.dao.entity.Food;
import com.mele.dao.util.ContentValues;

/**
 * 定义'storeMenu'数据访问接口模型
 * 
 * @author Jinhu
 * @date 2016/6/11
 */
public interface IStoreMenuDao {

	/**
	 * 添加商家菜式信息
	 * 
	 * @param food
	 * @return
	 * @throws Exception 
	 */
	public boolean addFood(Food food) throws Exception;

	/**
	 * 添加商家菜式列表
	 * 
	 * @param foods
	 * @return
	 * @throws Exception 
	 */
	public boolean addFoods(List<Food> foods) throws Exception;

	/**
	 * 删除指定商家菜式
	 * 
	 * @return
	 * @throws Exception 
	 */
	public boolean deleteFood(long storeId, int foodId) throws Exception;

	/**
	 * 删除所有商家菜式列表
	 * 
	 * @return
	 */
	public boolean deleteAllFoods();

	/**
	 * 修改商家菜式名
	 * 
	 * @param storeId
	 * @param foodId
	 * @param foodName
	 * @return
	 * @throws Exception 
	 */
	public int updateFoodName(long storeId, int foodId, String foodName) throws Exception;

	/**
	 * 修改商家菜式价格
	 * 
	 * @param storeId
	 * @param foodId
	 * @param foodPrices
	 * @return
	 * @throws Exception 
	 */
	public int updateFoodPrices(long storeId, int foodId, float foodPrices) throws Exception;

	/**
	 * 修改商家菜式类型
	 * 
	 * @param storeId
	 * @param foodId
	 * @param foodType
	 * @return
	 * @throws Exception 
	 */
	public int updateFoodType(long storeId, int foodId, String foodType) throws Exception;

	/**
	 * 修改商家菜式图片
	 * 
	 * @param storeId
	 * @param foodId
	 * @param foodIc
	 * @return
	 * @throws Exception 
	 */
	public int updateFoodIc(long storeId, int foodId, String foodIc) throws Exception;

	/**
	 * 修改商家菜式信息
	 * 
	 * @param storeId
	 * @param foodId
	 * @param values
	 * @return
	 */
	public int updateFoodValues(long storeId, int foodId, ContentValues values);

	/**
	 * 获取所有菜式信息
	 * 
	 * @return
	 */
	public List<Food> fetchAllFoods();

	/**
	 * 根据商家Id获取所有菜式信息
	 * 
	 * @param storeId
	 * @return
	 * @throws Exception 
	 */
	public List<Food> fetchAllFoodsByStoreId(long storeId) throws Exception;

	/**
	 * 获取指定价格区间的所有菜式信息
	 * 
	 * @param minPrices
	 * @param maxPrices
	 * @return
	 * @throws Exception 
	 */
	public List<Food> fetchAllFoodsByFoodPrices(long storeId, float minPrices,
			float maxPrices) throws Exception;

	/**
	 * 根据菜式类型名获取菜式信息
	 * 
	 * @param foodType
	 * @return
	 * @throws Exception 
	 */
	public List<Food> fetchAllFoodsByFoodType(long storeId, String foodType) throws Exception;

	/**
	 * 根据商家Id及菜式Id获取菜式信息
	 * 
	 * @param foodId
	 * @return
	 * @throws Exception 
	 */
	public Food fetchFood(long storeId, int foodId) throws Exception;

	/**
	 * 根据商家Id及一组菜式Id获取菜式信息
	 * 
	 * @param storeId
	 * @param foodId
	 * @return
	 * @throws Exception 
	 */
	public List<Food> fetchFoods(long storeId, int[] foodId) throws Exception;
	
	/**
	 * 获取指定商家的所有菜式类型
	 * @param storeId
	 * @return
	 * @throws Exception
	 */
	public List<String> fetchAllFoodTypeByStoreId(long storeId) throws Exception;
}
