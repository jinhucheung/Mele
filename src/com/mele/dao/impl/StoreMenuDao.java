package com.mele.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mele.control.DbContentProvider;
import com.mele.dao.IStoreMenuDao;
import com.mele.dao.IStoreMenuSchema;
import com.mele.dao.entity.Food;
import com.mele.dao.util.ContentValues;
import com.mele.util.LogUtil;
import com.mele.util.TextUtils;

/**
 * 'storemenu'数据访问接口
 * 
 * @author Jinhu
 * @date 2016/6/11
 */
public class StoreMenuDao extends DbContentProvider implements
		IStoreMenuSchema, IStoreMenuDao {
	private static final String TAG = StoreMenuDao.class.getSimpleName();

	private ContentValues insertValues;

	public StoreMenuDao(Connection conn) {
		super(conn);
	}

	/**
	 * 添加商家菜式信息
	 * 
	 * @param food
	 * @return
	 */
	@Override
	public boolean addFood(Food food) throws Exception {
		if (null == food || food.isNullValue())
			throw new NullPointerException();

		setContentValues(food);
		try {
			return super.insert(STORE_MENU_TABLE, insertValues);
		} catch (SQLException e) {
			e.printStackTrace();
			LogUtil.warning(TAG, "addFood(Food)... 插入数据异常");
		}
		return false;
	}

	/**
	 * 添加商家菜式列表
	 * 
	 * @param foods
	 * @return
	 */
	@Override
	public boolean addFoods(List<Food> foods) throws Exception {
		if (null == foods || 0 == foods.size())
			throw new NullPointerException();
		try {
			super.setAutoCommit(false); // 启动事务提交
			for (Food food : foods) {
				setContentValues(food);
				super.insert(STORE_MENU_TABLE, insertValues);
			}
			super.commit();
			super.setAutoCommit(true);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			super.rollback();
			LogUtil.warning(TAG, "addFoods(List<Food>)...增加商家菜式列表数据异常,已回退操作!!!");
		}
		super.setAutoCommit(true);
		return false;
	}

	/**
	 * 删除指定商家菜式
	 * 
	 * @return
	 */
	@Override
	public boolean deleteFood(long storeId, int foodId) throws Exception {
		if (storeId <= 0 || foodId <= 0)
			throw new IllegalArgumentException();

		final String selection = COLUMN_STORE_ID + " = ? " + " AND "
				+ COLUMN_FOOD_ID + " = ? ";
		final String[] selectionArgs = { storeId + "", foodId + "" };
		try {
			boolean isDeleted = super.delete(STORE_MENU_TABLE, selection,
					selectionArgs);
			if (isDeleted) {
				LogUtil.info(TAG, "删除'" + STORE_MENU_TABLE + "'表数据:"
						+ COLUMN_STORE_ID + "= " + storeId + " "
						+ COLUMN_FOOD_ID + "= " + foodId);
			}
			return isDeleted;
		} catch (SQLException e) {
			e.printStackTrace();
			LogUtil.warning(TAG, "deleteFood(long,int)...删除数据异常");
		}
		return false;
	}

	/**
	 * 删除所有商家菜式列表
	 * 
	 * @return
	 */
	@Override
	public boolean deleteAllFoods() {
		try {
			boolean isDeleted = super.delete(STORE_MENU_TABLE, null, null);
			if (isDeleted)
				LogUtil.info(TAG, "删除'" + STORE_MENU_TABLE + "'所有数据");
			return isDeleted;
		} catch (SQLException e) {
			e.printStackTrace();
			LogUtil.warning(TAG, "deleteAllFoods()...删除数据异常");
		}
		return false;
	}

	/**
	 * 修改商家菜式名
	 * 
	 * @param storeId
	 * @param foodId
	 * @param foodName
	 * @return
	 */
	@Override
	public int updateFoodName(long storeId, int foodId, String foodName)
			throws Exception {
		if (storeId <= 0 || foodId <= 0 || TextUtils.isEmpty(foodName))
			throw new IllegalArgumentException();

		final ContentValues values = new ContentValues();
		values.put(COLUMN_FOOD_NAME, foodName);
		return this.updateFoodValues(storeId, foodId, values);
	}

	/**
	 * 修改商家菜式价格
	 * 
	 * @param storeId
	 * @param foodId
	 * @param foodPrices
	 * @return
	 */
	@Override
	public int updateFoodPrices(long storeId, int foodId, float foodPrices)
			throws Exception {
		if (storeId <= 0 || foodId <= 0 || foodPrices < 0)
			throw new IllegalArgumentException();

		final ContentValues values = new ContentValues();
		values.put(COLUMN_FOOD_PRICES, foodPrices);
		return this.updateFoodValues(storeId, foodId, values);
	}

	/**
	 * 修改商家菜式类型
	 * 
	 * @param storeId
	 * @param foodId
	 * @param foodType
	 * @return
	 */
	@Override
	public int updateFoodType(long storeId, int foodId, String foodType)
			throws Exception {
		if (storeId <= 0 || foodId <= 0 || TextUtils.isEmpty(foodType))
			throw new IllegalArgumentException();

		final ContentValues values = new ContentValues();
		values.put(COLUMN_FOOD_TYPE, foodType);
		return this.updateFoodValues(storeId, foodId, values);
	}

	/**
	 * 修改商家菜式图片
	 * 
	 * @param storeId
	 * @param foodId
	 * @param foodIc
	 * @return
	 */
	@Override
	public int updateFoodIc(long storeId, int foodId, String foodIc)
			throws Exception {
		if (storeId <= 0 || foodId <= 0 || TextUtils.isEmpty(foodIc))
			throw new IllegalArgumentException();

		final ContentValues values = new ContentValues();
		values.put(COLUMN_FOOD_IC, foodIc);
		return this.updateFoodValues(storeId, foodId, values);
	}

	/**
	 * 修改商家菜式信息
	 * 
	 * @param storeId
	 * @param foodId
	 * @param values
	 * @return
	 */
	@Override
	public int updateFoodValues(long storeId, int foodId, ContentValues values) {
		final String selection = COLUMN_STORE_ID + "= ? AND " + COLUMN_FOOD_ID
				+ "= ? ";
		final String[] selectionArgs = { storeId + "", foodId + "" };

		try {
			int updatedRows = super.update(STORE_MENU_TABLE, values, selection,
					selectionArgs);
			if (updatedRows > 0) {
				LogUtil.info(TAG, "updateFoodValues()...  updated row : "
						+ updatedRows);
			}
			return updatedRows;
		} catch (SQLException e) {
			e.printStackTrace();
			LogUtil.warning(TAG, "updateFoodValues()... 更新菜式信息 异常");
		}
		return 0;
	}

	/**
	 * 获取所有菜式信息
	 * 
	 * @return
	 */
	@Override
	public List<Food> fetchAllFoods() {
		List<Food> foods = new ArrayList<Food>();
		try {
			ResultSet mResultSet = super.query(STORE_MENU_TABLE,
					STORE_MENU_COLUMNS, null, null, COLUMN_STORE_ID);
			if (null != mResultSet) {
				while (mResultSet.next()) {
					Food food = ResultSetToEntity(mResultSet);
					if (null != food && !food.isNullValue()) {
						foods.add(food);
					}
				}
			}
			mResultSet.close();
			return foods;
		} catch (SQLException e) {
			e.printStackTrace();
			LogUtil.warning(TAG, "fetchAllFoods()...查询异常");
		}

		return null;
	}

	/**
	 * 根据商家Id获取所有菜式信息
	 * 
	 * @param storeId
	 * @return
	 */
	@Override
	public List<Food> fetchAllFoodsByStoreId(long storeId) throws Exception {
		if (storeId <= 0)
			throw new IllegalArgumentException();

		List<Food> foods = new ArrayList<Food>();
		final String selection = COLUMN_STORE_ID + " = ?";
		final String[] selectionArgs = { storeId + "" };

		try {
			ResultSet mResultSet = super.query(STORE_MENU_TABLE,
					STORE_MENU_COLUMNS, selection, selectionArgs,
					COLUMN_STORE_ID);
			if (null != mResultSet) {
				while (mResultSet.next()) {
					Food food = ResultSetToEntity(mResultSet);
					if (null != food && !food.isNullValue()) {
						foods.add(food);
					}
				}
				mResultSet.close();
				return foods;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取指定价格区间的所有菜式信息
	 * 
	 * @param minPrices
	 * @param maxPrices
	 * @return
	 */
	@Override
	public List<Food> fetchAllFoodsByFoodPrices(long storeId, float minPrices,
			float maxPrices) throws Exception {
		if (storeId <= 0 || minPrices > maxPrices)
			throw new IllegalArgumentException();
		List<Food> foods = new ArrayList<Food>();
		final String selection = COLUMN_STORE_ID + " = ?" + " AND " + //
				COLUMN_FOOD_PRICES + " >= ? " + " AND " + //
				COLUMN_FOOD_PRICES + " <= ?";
		final String[] selectionArgs = { storeId + "", minPrices + "",
				maxPrices + "" };
		try {
			ResultSet mResultSet = super.query(STORE_MENU_TABLE,
					STORE_MENU_COLUMNS, selection, selectionArgs,
					COLUMN_STORE_ID);
			if (null != mResultSet) {
				while (mResultSet.next()) {
					Food food = ResultSetToEntity(mResultSet);
					if (null != food && !food.isNullValue()) {
						foods.add(food);
					}
				}
				mResultSet.close();
				return foods;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据菜式类型名获取菜式信息
	 * 
	 * @param foodType
	 * @return
	 */
	@Override
	public List<Food> fetchAllFoodsByFoodType(long storeId, String foodType)
			throws Exception {
		if (storeId <= 0 || TextUtils.isEmpty(foodType))
			throw new IllegalArgumentException();
		List<Food> foods = new ArrayList<Food>();
		final String selection = COLUMN_STORE_ID + " = ?" + " AND "
				+ COLUMN_FOOD_TYPE + " = ?";
		final String[] selectionArgs = { storeId + "", foodType };
		try {
			ResultSet mResultSet = super.query(STORE_MENU_TABLE,
					STORE_MENU_COLUMNS, selection, selectionArgs,
					COLUMN_STORE_ID);
			if (null != mResultSet) {
				while (mResultSet.next()) {
					Food food = ResultSetToEntity(mResultSet);
					if (null != food && !food.isNullValue()) {
						foods.add(food);
					}
				}
				mResultSet.close();
				return foods;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据商家Id及菜式Id获取菜式信息
	 * 
	 * @param foodId
	 * @return
	 */
	@Override
	public Food fetchFood(long storeId, int foodId) throws Exception {
		if (storeId <= 0 || foodId <= 0)
			throw new IllegalArgumentException();

		final String selection = COLUMN_STORE_ID + " = ?" + " AND "
				+ COLUMN_FOOD_ID + " = ?";
		final String[] selectionArgs = { storeId + "", foodId + "" };
		try {
			ResultSet mResultSet = super.query(STORE_MENU_TABLE,
					STORE_MENU_COLUMNS, selection, selectionArgs,
					COLUMN_STORE_ID);
			if (null != mResultSet && mResultSet.next()) {
				Food food = ResultSetToEntity(mResultSet);
				mResultSet.close();
				return food;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据商家Id及一组菜式Id获取菜式信息
	 * 
	 * @param storeId
	 * @param foodId
	 * @return
	 */
	@Override
	public List<Food> fetchFoods(long storeId, int[] foodId) throws Exception {
		if (storeId <= 0 || null == foodId || foodId.length == 0)
			throw new IllegalArgumentException();

		String selection = COLUMN_STORE_ID + " = ?" + " AND " + COLUMN_FOOD_ID
				+ " IN ";
		String[] selectionArgs = new String[foodId.length + 1];
		for (int i = 0; i < foodId.length; i++) {
			if (0 == i) {
				selection += "(";
				selectionArgs[i] = storeId + "";
			}

			if (i != foodId.length - 1) {
				selection += " ? , ";
			} else {
				selection += " ? ) ";
			}
			selectionArgs[i + 1] = foodId[i] + "";
		}

		List<Food> foods = new ArrayList<Food>();
		try {
			ResultSet mResultSet = super.query(STORE_MENU_TABLE,
					STORE_MENU_COLUMNS, selection, selectionArgs,
					COLUMN_FOOD_ID);
			if (null != mResultSet) {
				while (mResultSet.next()) {
					Food food = ResultSetToEntity(mResultSet);
					if (null != food && !food.isNullValue()) {
						foods.add(food);
					}
				}
				mResultSet.close();
				return foods;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取指定商家的所有菜式类型
	 * 
	 * @param storeId
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<String> fetchAllFoodTypeByStoreId(long storeId)
			throws Exception {
		if (storeId <= 0)
			throw new IllegalArgumentException();

		List<String> foodTypes = new ArrayList<String>();
		final String selection = COLUMN_STORE_ID + " = ?";
		final String[] selectionArgs = { storeId + "" };

		try {
			ResultSet mResultSet = super.query(true, STORE_MENU_TABLE,
					new String[] { COLUMN_FOOD_TYPE }, selection,
					selectionArgs, COLUMN_STORE_ID);
			if (null != mResultSet) {
				while (mResultSet.next()) {
					String mfoodType = mResultSet.getString(COLUMN_FOOD_TYPE);
					if (!TextUtils.isEmpty(mfoodType)) {
						foodTypes.add(mfoodType);
					}
				}
				mResultSet.close();

				if (foodTypes.size() == 0)
					return null;

				return foodTypes;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将当前ResultSet行数据转成Food对象
	 */
	@Override
	protected Food ResultSetToEntity(ResultSet resultSet) {
		Food mFood = new Food();
		try {
			if (null != resultSet) {
				long storeId = resultSet.getLong(COLUMN_STORE_ID);
				String foodType = resultSet.getString(COLUMN_FOOD_TYPE);
				float foodPrices = resultSet.getFloat(COLUMN_FOOD_PRICES);
				String foodName = resultSet.getString(COLUMN_FOOD_NAME);
				int foodId = resultSet.getInt(COLUMN_FOOD_ID);
				String foodIc = resultSet.getString(COLUMN_FOOD_IC);

				mFood.setStoreId(storeId);
				mFood.setFoodType(foodType);
				mFood.setFoodPrices(foodPrices);
				mFood.setFoodName(foodName);
				mFood.setFoodId(foodId);
				mFood.setFoodIc(foodIc);

				return mFood;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void setContentValues(Food food) throws Exception {
		if (null == food || food.isNullValue())
			throw new NullPointerException();

		insertValues = new ContentValues();
		insertValues.put(COLUMN_FOOD_ID, food.getFoodId());
		insertValues.put(COLUMN_FOOD_IC, food.getFoodIc());
		insertValues.put(COLUMN_FOOD_NAME, food.getFoodName());
		insertValues.put(COLUMN_FOOD_PRICES, food.getFoodPrices());
		insertValues.put(COLUMN_FOOD_TYPE, food.getFoodType());
		insertValues.put(COLUMN_STORE_ID, food.getStoreId());
	}

	public ContentValues getContentValues() {
		return insertValues;
	}

}
