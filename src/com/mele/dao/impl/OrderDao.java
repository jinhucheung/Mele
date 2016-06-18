package com.mele.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mele.control.DbContentProvider;
import com.mele.dao.IOrderDao;
import com.mele.dao.IOrderSchema;
import com.mele.dao.entity.Food;
import com.mele.dao.entity.Order;
import com.mele.dao.entity.Store;
import com.mele.dao.util.ContentValues;
import com.mele.util.LogUtil;
import com.mele.util.TextUtils;

public class OrderDao extends DbContentProvider implements IOrderDao,
		IOrderSchema {
	private static final String TAG = OrderDao.class.getSimpleName();

	private ContentValues insertValues;

	/** 日期转换格式 。 */
	private final SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	public OrderDao(Connection conn) {
		super(conn);
	}

	@Override
	protected Order ResultSetToEntity(ResultSet resultSet) {
		try {
			if (null != resultSet) {
				String account = resultSet.getString(COLUMN_ACCOUNT);
				String orderCode = resultSet.getString(COLUMN_ORDER_CODE);
				long storeId = resultSet.getLong(COLUMN_STORE_ID);
				int userAddressId = resultSet.getInt(COLUMN_ADDRESS_ID);
				String orderFoodIds = resultSet.getString(COLUMN_ORDER_FOOD_ID);
				String orderFoodPriceXNum = resultSet
						.getString(COLUMN_ORDER_FOOD_PRICE_X_NUM);
				String note = resultSet.getString(COLUMN_NOTE);
				Timestamp orderTime = resultSet.getTimestamp(COLUMN_ORDER_DATE);
				// Date orderDate = new Date(orderTime.getTime());
				String comment = resultSet.getString(COLUMN_COMMENT);
				Order order = new Order(account, orderCode, storeId,
						userAddressId, orderFoodIds, orderFoodPriceXNum, note,
						orderTime, comment);
				return order;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 添加订单
	 * 
	 * @param order
	 * @return
	 */
	@Override
	public boolean addOrder(Order order) throws Exception {
		if (null == order || order.isNullValue())
			throw new NullPointerException();
		setContentValues(order);
		try {
			return super.insert(ORDER_TABLE, insertValues);
		} catch (SQLException e) {
			e.printStackTrace();
			LogUtil.warning(TAG, "addOrder(order)... 插入数据异常");
		}
		return false;
	}

	/**
	 * 根据订单编码删除订单
	 * 
	 * @param orderCode
	 * @return
	 */
	@Override
	public boolean deleteOrder(String orderCode) throws Exception {
		if (TextUtils.isEmpty(orderCode))
			throw new NullPointerException();

		final String selection = COLUMN_ORDER_CODE + " = ? ";
		final String selectionArgs[] = { orderCode };
		try {
			boolean isDeleted = super.delete(ORDER_TABLE, selection,
					selectionArgs);
			if (isDeleted) {
				LogUtil.info(TAG, "删除'" + ORDER_TABLE + "'表数据:"
						+ COLUMN_ORDER_CODE + "= " + orderCode);
			}
			return isDeleted;
		} catch (SQLException e) {
			e.printStackTrace();
			LogUtil.warning(TAG, "deleteOrder(String)...删除数据异常");
		}
		return false;
	}

	/**
	 * 删除用户账户所有订单
	 * 
	 * @param account
	 * @return
	 */
	@Override
	public boolean deleteAllOrdersInAccount(String account) throws Exception {
		if (TextUtils.isEmpty(account))
			throw new NullPointerException();

		final String selection = COLUMN_ACCOUNT + " = ? ";
		final String selectionArgs[] = { account };
		try {
			boolean isDeleted = super.delete(ORDER_TABLE, selection,
					selectionArgs);
			if (isDeleted) {
				LogUtil.info(TAG, "删除'" + ORDER_TABLE + "'表数据:"
						+ COLUMN_ACCOUNT + "= " + account);
			}
			return isDeleted;
		} catch (SQLException e) {
			e.printStackTrace();
			LogUtil.warning(TAG, "deleteAllOrdersInAccount(String)...删除数据异常");
		}
		return false;
	}

	/**
	 * 删除商家所有订单
	 * 
	 * @param storeId
	 * @return
	 */
	@Override
	public boolean deleteAllOrdersInStore(long storeId) throws Exception {
		if (storeId <= 0)
			throw new IllegalArgumentException();

		final String selection = COLUMN_STORE_ID + " = ? ";
		final String selectionArgs[] = { storeId + "" };
		try {
			boolean isDeleted = super.delete(ORDER_TABLE, selection,
					selectionArgs);
			if (isDeleted) {
				LogUtil.info(TAG, "删除'" + ORDER_TABLE + "'表数据:"
						+ COLUMN_STORE_ID + "= " + storeId);
			}
			return isDeleted;
		} catch (SQLException e) {
			e.printStackTrace();
			LogUtil.warning(TAG, "deleteAllOrdersInStore(long)...删除数据异常");
		}
		return false;
	}

	/**
	 * 修改订单评论
	 * 
	 * @param foodIds
	 * @return
	 */
	@Override
	public int updateOrderComment(String orderCode, String comment)
			throws Exception {
		if (TextUtils.isEmpty(orderCode) || TextUtils.isEmpty(comment))
			throw new NullPointerException();

		final ContentValues values = new ContentValues();
		values.put(COLUMN_COMMENT, comment);
		return updateOrderValues(orderCode, values);
	}

	private int updateOrderValues(String orderCode, ContentValues values) {
		final String selection = COLUMN_ORDER_CODE + "= ?";
		final String selectionArgs[] = { orderCode };
		try {
			int updatedRows = super.update(ORDER_TABLE, values, selection,
					selectionArgs);
			if (updatedRows > 0)
				LogUtil.info(TAG, "updateOrderValues()...  updated row : "
						+ updatedRows);
			return updatedRows;
		} catch (SQLException e) {
			e.printStackTrace();
			LogUtil.warning(TAG, "updateOrderValues()... 更新订单信息 异常");
		}
		return 0;
	}

	/**
	 * 获取指定订单编号的订单信息
	 * 
	 * @param orderCode
	 * @return
	 */
	@Override
	public Order fetchOrder(String orderCode) {
		final String selection = COLUMN_ORDER_CODE + "= ?";
		final String selectionArgs[] = { orderCode };
		// 获取订单信息
		Order mOrder = null;
		try {
			ResultSet mResultSet = super.query(ORDER_TABLE, ORDER_COLUMNS,
					selection, selectionArgs, COLUMN_ORDER_CODE);
			if (null != mResultSet && mResultSet.next()) {
				mOrder = ResultSetToEntity(mResultSet);
				mResultSet.close();
			}
			// 获取订单信息
			fetchFoods(mOrder);
			// 获取商家信息
			fetchStoreInfo(mOrder);
			return mOrder;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取指定用户的所有订单信息
	 * 
	 * @param account
	 * @return
	 */
	@Override
	public List<Order> fetchAllOrderInAccount(String account) throws Exception {
		if (TextUtils.isEmpty(account))
			throw new NullPointerException();

		final String selection = COLUMN_ACCOUNT + " = ? ";
		final String[] selectionArgs = { account };
		return fetchOrderList(selection, selectionArgs);
	}

	/**
	 * 获取指定商家的所有订单信息
	 * 
	 * @param storeId
	 * @return
	 */
	@Override
	public List<Order> fetchAllOrderInStore(long storeId) {
		if (storeId <= 0)
			throw new IllegalArgumentException();

		final String selection = COLUMN_STORE_ID + " = ? ";
		final String[] selectionArgs = { storeId + "" };

		List<Order> orders = new ArrayList<Order>();
		try {
			// 获取订单信息
			ResultSet mResultSet = super.query(ORDER_TABLE, ORDER_COLUMNS,
					selection, selectionArgs, COLUMN_ORDER_CODE);
			if (null != mResultSet) {
				while (mResultSet.next()) {
					Order order = ResultSetToEntity(mResultSet);
					if (null != order && order.getStoreId() > 0) {
						orders.add(order);
					}
				}
				mResultSet.close();
				return orders;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取所有订单信息
	 * 
	 * @return
	 */
	@Override
	public List<Order> fetchAllOrder() {
		return fetchOrderList(null, null);
	}

	/**
	 * 
	 * @param selection
	 * @param selectionArgs
	 * @return
	 */
	protected List<Order> fetchOrderList(String selection,
			String[] selectionArgs) {
		List<Order> orders = new ArrayList<Order>();
		try {
			// 获取订单信息
			ResultSet mResultSet = super.query(ORDER_TABLE, ORDER_COLUMNS,
					selection, selectionArgs, COLUMN_ORDER_CODE);
			if (null != mResultSet) {
				while (mResultSet.next()) {
					Order order = ResultSetToEntity(mResultSet);
					if (null != order && order.getStoreId() > 0) {
						fetchFoods(order);
						fetchStoreInfo(order);
						orders.add(order);
					}
				}
				mResultSet.close();
				return orders;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// -----------------------------------辅助方法------------------------------
	/**
	 * 获取订单菜式信息
	 * 
	 * @param mOrder
	 * @throws Exception
	 */
	public void fetchFoods(Order mOrder) {
		if (null != mOrder && !mOrder.isNullValue()) {
			String foodIdsStr = mOrder.getOrderFoodIds();
			if (!TextUtils.isEmpty(foodIdsStr)) {
				String[] foodIds = foodIdsStr.split(";");
				int[] foodId = stringArrayToIntArray(foodIds);
				if (null == foodId) {
					LogUtil.info(
							TAG,
							"fetchFoods(Order).....订单编号: "
									+ mOrder.getOrderCode()
									+ "   菜式编号格式错误!! 此菜式编号:"
									+ mOrder.getOrderFoodIds());
					return;
				}
				StoreMenuDao mStoreMenuDao = new StoreMenuDao(mConn);
				List<Food> foods = null;
				try {
					foods = mStoreMenuDao.fetchFoods(mOrder.getStoreId(),
							foodId);
				} catch (Exception e) {
					e.printStackTrace();
					LogUtil.warning(TAG, "fetchFoods(Order)....获取订单菜式信息失败!!!");
				}
				if (null != foods) {
					mOrder.setFoods(foods);
				}
			}
		}
	}

	/**
	 * 获取商家信息
	 * 
	 * @param mOrder
	 */
	private void fetchStoreInfo(Order mOrder) {
		if (null != mOrder && !mOrder.isNullValue()) {
			long storeId = mOrder.getStoreId();
			if (0 != storeId) {
				StoreDao mStoreDao = new StoreDao(mConn);
				Store mStore = null;
				try {
					mStore = mStoreDao.fetchStoreByStoreId(storeId);
				} catch (Exception e) {
					e.printStackTrace();
					LogUtil.warning(TAG, "fetchFoods(Order)....获取商家信息失败!!!");
				}
				if (null != mStore) {
					mOrder.setStore(mStore);
				}
			}
		}
	}

	private int[] stringArrayToIntArray(String[] strArray) {
		if (null == strArray || strArray.length == 0)
			return null;
		int[] intArray = new int[strArray.length];
		for (int i = 0; i < strArray.length; i++) {
			intArray[i] = Integer.parseInt(strArray[i]);
		}
		return intArray;
	}

	private void setContentValues(Order order) throws Exception {
		if (null == order || order.isNullValue())
			throw new NullPointerException(
					"Order is null OR OrderCode is null!");

		insertValues = new ContentValues();

		insertValues.put(COLUMN_ACCOUNT, order.getUserAccount());
		insertValues.put(COLUMN_ADDRESS_ID, order.getUserAddressId());
		insertValues.put(COLUMN_COMMENT, order.getComment());
		insertValues.put(COLUMN_NOTE, order.getUserNote());
		insertValues.put(COLUMN_ORDER_CODE, order.getOrderCode());
		insertValues.put(COLUMN_ORDER_DATE,
				dateFormat.format(order.getOrderTime()));
		insertValues.put(COLUMN_ORDER_FOOD_ID, order.getOrderFoodIds());
		insertValues.put(COLUMN_ORDER_FOOD_PRICE_X_NUM,
				order.getOrderFoodNumNPrice());
		insertValues.put(COLUMN_STORE_ID, order.getStoreId());
	}

	public ContentValues getContentValues() {
		return insertValues;
	}

}
