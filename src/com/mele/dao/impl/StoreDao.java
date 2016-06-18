package com.mele.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.mele.Constants;
import com.mele.control.DbContentProvider;
import com.mele.dao.IStoreDao;
import com.mele.dao.IStoreSchema;
import com.mele.dao.entity.Order;
import com.mele.dao.entity.Store;
import com.mele.dao.entity.StoreInfoNType;
import com.mele.dao.entity.StoreType;
import com.mele.dao.util.ContentValues;
import com.mele.util.LogUtil;
import com.mele.util.TextUtils;

/**
 * 'StoreInfo'数据访问接口
 * 
 * @author Jinhu
 * @date 2016/6/10
 */
public class StoreDao extends DbContentProvider implements IStoreDao,
		IStoreSchema {
	private static final String TAG = StoreDao.class.getSimpleName();

	/** 插入数据集 。 */
	private ContentValues insertValues;

	public StoreDao(Connection conn) {
		super(conn);
	}

	/**
	 * 添加商家
	 * 
	 * @param store
	 * @return
	 */
	@Override
	public boolean addStore(Store store) throws Exception {
		if (null == store || store.getStoreId() <= 0)
			throw new NullPointerException();

		setContentValues(store);
		try {
			return super.insert(STORE_TABLE, insertValues);
		} catch (SQLException e) {
			e.printStackTrace();
			LogUtil.warning(TAG, "addStore(store)... 插入数据异常");
		}
		return false;
	}

	/**
	 * 添加商家列表
	 * 
	 * @param stores
	 * @return
	 */
	@Override
	public boolean addStores(List<Store> stores) throws Exception {
		if (null == stores || 0 == stores.size())
			throw new NullPointerException("stores 数据不能为空！");

		try {
			super.setAutoCommit(false); // 启动事务提交
			for (Store store : stores) {
				setContentValues(store);
				super.insert(STORE_TABLE, insertValues);
			}
			super.commit();
			super.setAutoCommit(true);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			super.rollback();
			LogUtil.warning(TAG, "addStores(List<Store>)...增加商家列表数据异常,已回退操作!!!");
		}
		super.setAutoCommit(true);
		return false;
	}

	/**
	 * 根据商家Id删除
	 * 
	 * @param storeId
	 * @return
	 */
	@Override
	public boolean deleteStore(long storeId) throws Exception {
		if (storeId <= 0)
			throw new IllegalArgumentException("storeId must > 0");
		final String selection = COLUMN_ID + " = ? ";
		final String selectionArgs[] = { storeId + "" };
		try {
			boolean isDeleted = super.delete(STORE_TABLE, selection,
					selectionArgs);
			if (isDeleted) {
				LogUtil.info(TAG, "删除'" + STORE_TABLE + "'表数据:" + COLUMN_ID
						+ "= " + storeId);
			}
			return isDeleted;
		} catch (SQLException e) {
			e.printStackTrace();
			LogUtil.warning(TAG, "deleteStore(int)...删除数据异常");
		}
		return false;
	}

	/**
	 * 删除某城市所有商家
	 * 
	 * @param cityCode
	 * @return
	 */
	@Override
	public boolean deleteStoreByCityCode(String cityCode) throws Exception {
		if (TextUtils.isEmpty(cityCode))
			throw new NullPointerException();

		final String selection = COLUMN_CITY_CODE + " = ? ";
		final String selectionArgs[] = { cityCode };
		try {
			boolean isDeleted = super.delete(STORE_TABLE, selection,
					selectionArgs);
			if (isDeleted) {
				LogUtil.info(TAG, "删除'" + STORE_TABLE + "'表所有数据:"
						+ COLUMN_CITY_CODE + "= " + cityCode);
			}
			return isDeleted;
		} catch (SQLException e) {
			e.printStackTrace();
			LogUtil.warning(TAG, "deleteStoreByCityCode(String)...删除数据异常");
		}
		return false;
	}

	/**
	 * 删除所有商家
	 * 
	 * @return
	 */
	@Override
	public boolean deleteAllStores() {
		try {
			boolean isDeleted = super.delete(STORE_TABLE, null, null);
			if (isDeleted)
				LogUtil.info(TAG, "删除'" + STORE_TABLE + "'所有数据");
			return isDeleted;
		} catch (SQLException e) {
			e.printStackTrace();
			LogUtil.warning(TAG, "deleteAllStores()...删除数据异常");
		}
		return false;
	}

	/**
	 * 修改商家名
	 * 
	 * @param storeId
	 * @param storeName
	 * @return
	 */
	@Override
	public int updateStoreName(long storeId, String storeName) throws Exception {
		if (TextUtils.isEmpty(storeName))
			throw new NullPointerException();
		ContentValues values = new ContentValues();
		values.put(COLUMN_STORE_NAME, storeName);
		return updateStoreValues(storeId, values);
	}

	/**
	 * 修改商家Logo
	 * 
	 * @param storeId
	 * @param storeLogo
	 * @return
	 */
	@Override
	public int updateStoreLogo(long storeId, String storeLogo) throws Exception {
		if (TextUtils.isEmpty(storeLogo))
			throw new NullPointerException();
		ContentValues values = new ContentValues();
		values.put(COLUMN_STORE_LOGO, storeLogo);
		return updateStoreValues(storeId, values);
	}

	/**
	 * 修改商家营业时间
	 * 
	 * @param storeId
	 * @param shopHours
	 * @return
	 */
	@Override
	public int updateStoreShopHours(long storeId, String shopHours)
			throws Exception {
		if (TextUtils.isEmpty(shopHours))
			throw new NullPointerException();
		ContentValues values = new ContentValues();
		values.put(COLUMN_SHOP_HOURS, shopHours);
		return updateStoreValues(storeId, values);
	}

	/**
	 * 修改商家起送价或配送费
	 * 
	 * @param storeId
	 * @param startPrice
	 * @param transportpricePrice
	 * @return
	 */
	@Override
	public int updateStorePrice(long storeId, float startPrice,
			float transportpricePrice) {
		ContentValues values = null;
		if (startPrice > 0) {
			if (null == values)
				values = new ContentValues();
			values.put(COLUMN_START_PRICE, startPrice);
		}

		if (transportpricePrice > 0) {
			if (null == values)
				values = new ContentValues();
			values.put(COLUMN_TRANSPORT_PRICE, transportpricePrice);
		}

		if (null != values)
			return updateStoreValues(storeId, values);
		return 0;
	}

	/**
	 * 修改商家公告
	 * 
	 * @param storeId
	 * @param notice
	 * @param cheapenNotice
	 * @param newUserNotice
	 * @return
	 */
	@Override
	public int updateStoreNotice(long storeId, String notice,
			String cheapenNotice, String newUserNotice) {
		ContentValues values = null;
		if (!TextUtils.isEmpty(notice)) {
			if (null == values)
				values = new ContentValues();
			values.put(COLUMN_NOTICE, notice);
		}

		if (!TextUtils.isEmpty(cheapenNotice)) {
			if (null == values)
				values = new ContentValues();
			values.put(COLUMN_CHEAPEN_NOTICE, cheapenNotice);
		}

		if (!TextUtils.isEmpty(newUserNotice)) {
			if (null == values)
				values = new ContentValues();
			values.put(COLUMN_NEWUSER_NOTICE, newUserNotice);
		}

		if (null != values)
			return updateStoreValues(storeId, values);
		return 0;
	}

	/**
	 * 修改商家电话
	 * 
	 * @param storeId
	 * @param tel
	 * @return
	 */
	@Override
	public int updateStoreTel(long storeId, String tel) throws Exception {
		if (TextUtils.isEmpty(tel))
			throw new NullPointerException();
		final ContentValues values = new ContentValues();
		values.put(COLUMN_TEL, tel);

		return updateStoreValues(storeId, values);
	}

	/**
	 * 修改商家信息
	 * 
	 * @param storeId
	 * @param values
	 * @return
	 */
	@Override
	public int updateStoreValues(long storeId, ContentValues values) {
		final String selection = COLUMN_ID + "= ?";
		final String selectionArgs[] = { storeId + "" };
		try {
			int updatedRows = super.update(STORE_TABLE, values, selection,
					selectionArgs);
			if (updatedRows > 0)
				LogUtil.info(TAG, "updateStoreValues()...  updated row : "
						+ updatedRows);
			return updatedRows;
		} catch (SQLException e) {
			e.printStackTrace();
			LogUtil.warning(TAG, "updateStoreValues()... 更新商家信息 异常");
		}
		return 0;

	}

	/**
	 * 获取所有商家信息
	 */
	@Override
	public List<Store> fetchAllStores() {
		List<Store> stores = new ArrayList<Store>();
		try {
			// 获取商家基本信息
			ResultSet mResultSet = super.query(STORE_TABLE, STORE_COLUMNS,
					null, null, COLUMN_ID);
			if (null != mResultSet) {
				while (mResultSet.next()) {
					Store store = ResultSetToEntity(mResultSet);
					if (null != store && store.getStoreId() > 0)
						stores.add(store);
				}
				mResultSet.close();
			}

			if (null == stores || stores.size() == 0) {
				LogUtil.warning(TAG, "fetchAllStores().....没有查找商家信息!!");
				return null;
			}
			try {
				// 获取商家类型信息
				fetchStoreType(stores);
				// 获取商家订单信息
				fetchStoreOrderInfo(stores);
			} catch (Exception e) {
				e.printStackTrace();
				LogUtil.warning(TAG, "获取商家类型或订单信息失败!!");
			}
			return stores;
		} catch (SQLException e) {
			e.printStackTrace();
			LogUtil.warning(TAG, "fetchAllStores()...查询异常");
		}
		return null;
	}

	/**
	 * 获取某城市所有商家
	 * 
	 * @param cityCode
	 * @return
	 */
	@Override
	public List<Store> fetchAllStoresByCityCode(String cityCode)
			throws Exception {
		if (TextUtils.isEmpty(cityCode))
			throw new NullPointerException();

		final String selection = COLUMN_CITY_CODE + "= ? ";
		final String selectionArgs[] = { cityCode };

		return fetchStoreList(selection, selectionArgs);
	}

	/**
	 * 根据起送价获取所有商家
	 * 
	 * @param minPrice
	 * @param maxPrice
	 * @return
	 */
	@Override
	public List<Store> fetchAllStoresByStartPrice(float minPrice, float maxPrice)
			throws Exception {
		if (minPrice > maxPrice)
			throw new IllegalArgumentException();

		final String selection = COLUMN_START_PRICE + ">= ?" + " And "
				+ COLUMN_START_PRICE + "<= ?";
		final String selectionArgs[] = { minPrice + "", maxPrice + "" };

		return fetchStoreList(selection, selectionArgs);
	}

	/**
	 * 根据商家类型Id获取所有商家
	 * 
	 * @param storeTypeId
	 * @return
	 */
	@Override
	public List<Store> fetchAllStoresByTypeId(int storeTypeId) throws Exception {
		if (storeTypeId < 0)
			throw new IllegalArgumentException();

		// storeTypeId类型判断
		if (storeTypeId == 0) {
			return fetchAllStores();
		}
		// 判断是否为父级类型Id
		List<Store> mStores = fetchAllStoreBySuperTypeId(null, storeTypeId);
		if (null != mStores)
			return mStores;

		// 不是父级类型Id，获取其属的子类型Id商家信息
		// 获取商家基本信息
		StoreInfoNTypeDao mStoreInfoNTypeDao = new StoreInfoNTypeDao(mConn);

		List<StoreInfoNType> storeInfoNType = mStoreInfoNTypeDao
				.fetchAllStoreInfoNTypesByTypeId(storeTypeId);

		if (null != storeInfoNType && storeInfoNType.size() > 0) {

			String selection = "";
			String selectionArgs[] = new String[storeInfoNType.size()];

			for (int i = 0; i < storeInfoNType.size(); i++) {
				if (0 == i) {
					selection = COLUMN_ID + " in ( ";
				}
				if (storeInfoNType.size() - 1 != i) {
					selection += " ? , ";
				} else {
					selection += " ? ) ";
				}

				selectionArgs[i] = storeInfoNType.get(i).getStoreId() + "";
			}

			mStores = fetchStoreList(selection, selectionArgs);
		}

		if (null == mStores || mStores.size() == 0) {
			LogUtil.warning(TAG, "fetchAllStoresByTypeId().....没有查找商家信息!!");
			return null;
		}
		// 获取商家类型信息
		fetchStoreType(mStores);
		// 获取商家订单信息
		fetchStoreOrderInfo(mStores);

		return mStores;
	}

	/**
	 * 根据商家Id获取商家
	 */
	@Override
	public Store fetchStoreByStoreId(long storeId) {
		final String selection = COLUMN_ID + "= ?";
		final String selectionArgs[] = { storeId + "" };

		// 获取商家基本信息
		Store mStore = new Store();
		try {
			ResultSet mResultSet = super.query(STORE_TABLE, STORE_COLUMNS,
					selection, selectionArgs, COLUMN_ID);
			if (null != mResultSet && mResultSet.next()) {
				mStore = ResultSetToEntity(mResultSet);
				mResultSet.close();
				try {
					// 获取商家类型信息
					fetchStoreType(mStore);
					// 获取商家订单信息
					fetchStoreOrderInfo(mStore);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return mStore;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 根据商家类型Id获取指定城市所有商家
	 * 
	 * @param storeTypeId
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Store> fetchAllStoresByCityCodeNTypeId(String cityCode,
			int storeTypeId) throws Exception {
		if (storeTypeId < 0 || TextUtils.isEmpty(cityCode))
			throw new IllegalArgumentException();

		// storeTypeId类型判断
		if (storeTypeId == 0) {
			return fetchAllStoresByCityCode(cityCode);
		}
		// 判断是否为父级类型Id
		List<Store> mStores = fetchAllStoreBySuperTypeId(cityCode, storeTypeId);
		if (null != mStores)
			return mStores;

		// 不是父级类型Id，获取其属的子类型Id商家信息
		// 获取商家基本信息
		StoreInfoNTypeDao mStoreInfoNTypeDao = new StoreInfoNTypeDao(mConn);

		List<StoreInfoNType> storeInfoNType = mStoreInfoNTypeDao
				.fetchAllStoreInfoNTypesByTypeId(storeTypeId);

		if (null != storeInfoNType && storeInfoNType.size() > 0) {

			String selection = "";
			String selectionArgs[] = new String[storeInfoNType.size() + 1];

			for (int i = 0; i < storeInfoNType.size(); i++) {
				if (0 == i) {
					selection = COLUMN_ID + " in ( ";
				}
				if (storeInfoNType.size() - 1 != i) {
					selection += " ? , ";
				} else {
					selection += " ? ) ";
				}

				selectionArgs[i] = storeInfoNType.get(i).getStoreId() + "";
			}
			selection += " And " + COLUMN_CITY_CODE + " = ? ";
			selectionArgs[selectionArgs.length - 1] = cityCode;
			mStores = fetchStoreList(selection, selectionArgs);
		}

		if (null == mStores || mStores.size() == 0) {
			LogUtil.warning(TAG, "fetchAllStoresByTypeId().....没有查找商家信息!!");
			return null;
		}
		// 获取商家类型信息
		fetchStoreType(mStores);
		// 获取商家订单信息
		fetchStoreOrderInfo(mStores);

		return mStores;
	}

	/**
	 * 根据起送价获取指定城市所有商家
	 * 
	 * @param minPrice
	 * @param maxPrice
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Store> fetchAllStoresByCityCodeNStartPrice(String cityCode,
			float minPrice, float maxPrice) throws Exception {
		if (minPrice > maxPrice || TextUtils.isEmpty(cityCode))
			throw new IllegalArgumentException();

		final String selection = COLUMN_START_PRICE + ">= ?" + " And "
				+ COLUMN_START_PRICE + "<= ?" + " And " + COLUMN_CITY_CODE
				+ " = ? ";
		final String selectionArgs[] = { minPrice + "", maxPrice + "", cityCode };

		return fetchStoreList(selection, selectionArgs);
	}

	// --------------------------------辅助方法----------------------------------------
	/**
	 * 获取商家类型信息
	 * 
	 * @param stores
	 */
	protected void fetchStoreType(List<Store> stores) throws Exception {
		if (null == stores || 0 == stores.size()) {
			LogUtil.warning(TAG,
					"fetchStoreType(List<Store>).....没有商家基础信息，不能查找商家类型信息!!");
			return;
		}

		StoreInfoNTypeDao mInfoNTypeDao = new StoreInfoNTypeDao(mConn);
		StoreTypeDao mTypeDao = new StoreTypeDao(mConn);

		for (Store store : stores) {
			String typeStr = "";
			List<StoreInfoNType> storeInfoNTypes = mInfoNTypeDao
					.fetchAllStoreInfoNTypesByStoreId(store.getStoreId());
			for (StoreInfoNType infoNType : storeInfoNTypes) {
				StoreType storeType = mTypeDao.fetchStoreTypeById(infoNType
						.getStypeId());
				if (null != storeType) {
					typeStr = typeStr + storeType.getTypeName() + " ";
				}
			}
			if (!TextUtils.isEmpty(typeStr.trim())) {
				store.setStoreType(typeStr);
			}
		}
	}

	/**
	 * 获取商家订单信息
	 * 
	 * @param stores
	 */
	protected void fetchStoreOrderInfo(List<Store> stores) throws Exception {
		if (null == stores || 0 == stores.size()) {
			LogUtil.warning(TAG,
					"fetchStoreType(List<Store>).....没有商家基础信息，不能查找商家订单信息!!");
			return;

		}
		OrderDao mOrderDao = new OrderDao(mConn);
		for (Store store : stores) {
			List<Order> mOrders = mOrderDao.fetchAllOrderInStore(store
					.getStoreId());
			if (null != mOrders) {
				store.setOrderNums(mOrders.size());
			}
		}
	}

	protected void fetchStoreOrderInfo(Store store) {
		if (null == store) {
			LogUtil.warning(TAG,
					"fetchStoreType(List<Store>).....没有商家基础信息，不能查找商家订单信息!!");
			return;

		}
		OrderDao mOrderDao = new OrderDao(mConn);
		List<Order> mOrders = mOrderDao
				.fetchAllOrderInStore(store.getStoreId());
		if (null != mOrders) {
			store.setOrderNums(mOrders.size());
		}
	}

	protected void fetchStoreType(Store store) throws Exception {
		if (null == store) {
			LogUtil.warning(TAG,
					"fetchStoreType(Store).....没有商家基础信息，不能查找商家类型信息!!");
			return;
		}

		StoreInfoNTypeDao mInfoNTypeDao = new StoreInfoNTypeDao(mConn);
		StoreTypeDao mTypeDao = new StoreTypeDao(mConn);

		String typeStr = "";
		List<StoreInfoNType> storeInfoNTypes = mInfoNTypeDao
				.fetchAllStoreInfoNTypesByStoreId(store.getStoreId());
		for (StoreInfoNType infoNType : storeInfoNTypes) {
			StoreType storeType = mTypeDao.fetchStoreTypeById(infoNType
					.getStypeId());
			if (null != storeType) {
				typeStr = typeStr + storeType.getTypeName() + " ";
			}
		}
		if (!TextUtils.isEmpty(typeStr.trim())) {
			store.setStoreType(typeStr);
		}
	}

	protected List<Store> fetchStoreList(String selection,
			String[] selectionArgs) {
		List<Store> stores = new ArrayList<Store>();
		try {
			// 获取商家基本信息
			ResultSet mResultSet = super.query(STORE_TABLE, STORE_COLUMNS,
					selection, selectionArgs, COLUMN_ID);
			if (null != mResultSet) {
				while (mResultSet.next()) {
					Store store = ResultSetToEntity(mResultSet);
					if (null != store && store.getStoreId() > 0)
						stores.add(store);
				}
				mResultSet.close();
			}

			if (null == stores || stores.size() == 0) {
				LogUtil.warning(TAG, "fetchStoreList().....没有查找商家信息!!");
				return null;
			}

			try {
				// 获取商家类型信息
				fetchStoreType(stores);
				// 获取商家订单信息
				fetchStoreOrderInfo(stores);
			} catch (Exception e) {
				e.printStackTrace();
				LogUtil.warning(TAG, "获取商家类型或订单信息失败!");
			}

			return stores;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据商家父级类型Id获取所有商家
	 * 
	 * @param storeTypeId
	 * @return
	 * @throws Exception
	 */
	protected List<Store> fetchAllStoreBySuperTypeId(String cityCode,
			int superStoreTypeId) throws Exception {
		// 判断是否为父级类型Id
		boolean isSuperId = false;
		for (int superId : Constants.STORE_SUPER_TYPE_ID) {
			if (superStoreTypeId == superId) {
				isSuperId = true;
				break;
			}
		}
		if (!isSuperId)
			return null;
		List<Store> mStores = null;

		// 获取相应的子类型Id
		StoreTypeDao mStoreTypeDao = new StoreTypeDao(mConn);
		List<StoreType> storeTypes = mStoreTypeDao
				.fetchStoreTypeBySuperId(superStoreTypeId);
		// 获取所有商家信息与类型联系
		StoreInfoNTypeDao mStoreInfoNTypeDao = new StoreInfoNTypeDao(mConn);
		List<StoreInfoNType> storeInfoNTypes = mStoreInfoNTypeDao
				.fetchAllStoreInfoNTypes();

		// 相应的商家Id
		Set<Long> storeIdSet = new HashSet<Long>();
		for (StoreType mStoreType : storeTypes) {
			for (StoreInfoNType mStoreInfoNType : storeInfoNTypes) {
				if (mStoreType.getStypeId() == mStoreInfoNType.getStypeId()) {
					storeIdSet.add(mStoreInfoNType.getStoreId());
				}
			}
		}

		// 获取商家基本信息
		if (null != storeIdSet && storeIdSet.size() > 0) {
			String selection = "";
			String selectionArgs[] = null;
			if (TextUtils.isEmpty(cityCode)) {
				selectionArgs = new String[storeIdSet.size()];
			} else {
				selectionArgs = new String[storeIdSet.size() + 1];
			}
			Iterator<Long> mIdIterator = storeIdSet.iterator();
			int i = 0;
			while (mIdIterator.hasNext()) {
				if (0 == i) {
					selection = COLUMN_ID + " in ( ";
				}
				if (storeIdSet.size() - 1 != i) {
					selection += " ? , ";
				} else {
					selection += " ? ) ";
				}
				selectionArgs[i] = mIdIterator.next() + "";
				i++;
			}
			if (!TextUtils.isEmpty(cityCode)) {
				selection += " And " + COLUMN_CITY_CODE + " = ? ";
				selectionArgs[selectionArgs.length - 1] = cityCode;
			}
			mStores = fetchStoreList(selection, selectionArgs);
		}

		if (null == mStores || mStores.size() == 0) {
			LogUtil.warning(TAG, "fetchAllStoresByTypeId().....没有查找商家信息!!");
			return null;
		}
		// 获取商家类型信息
		fetchStoreType(mStores);
		// 获取商家订单信息
		fetchStoreOrderInfo(mStores);

		return mStores;
	}

	/**
	 * 将当前ResultSet行数据转成Store对象
	 */
	@Override
	protected Store ResultSetToEntity(ResultSet resultSet) {
		Store mStore = new Store();
		try {
			if (null != resultSet) {
				String cheapenNotice = resultSet
						.getString(COLUMN_CHEAPEN_NOTICE);
				String cityCode = resultSet.getString(COLUMN_CITY_CODE);
				String latlng = resultSet.getString(COLUMN_LATLNG);
				String newuserNotice = resultSet
						.getString(COLUMN_NEWUSER_NOTICE);
				String notice = resultSet.getString(COLUMN_NOTICE);
				String shopHours = resultSet.getString(COLUMN_SHOP_HOURS);
				float startPrice = resultSet.getFloat(COLUMN_START_PRICE);
				float transportPrice = resultSet
						.getFloat(COLUMN_TRANSPORT_PRICE);
				long storeId = resultSet.getLong(COLUMN_ID);
				String storeLogo = resultSet.getString(COLUMN_STORE_LOGO);
				String storeName = resultSet.getString(COLUMN_STORE_NAME);
				String tel = resultSet.getString(COLUMN_TEL);

				String address = resultSet.getString(COLUMN_ADDRESS);
				String account = resultSet.getString(COLUMN_ACCOUNT);

				mStore.setCheapenNotice(cheapenNotice);
				mStore.setCityCode(cityCode);
				mStore.setLatlng(latlng);
				mStore.setNewuserNotice(newuserNotice);
				mStore.setNotice(notice);
				mStore.setShopHours(shopHours);
				mStore.setStartPrice(startPrice);
				mStore.setStoreId(storeId);
				mStore.setStoreLogo(storeLogo);
				mStore.setStoreName(storeName);
				mStore.setTel(tel);
				mStore.setAddress(address);
				mStore.setAccount(account);
				mStore.setTransportPrice(transportPrice);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			LogUtil.warning(TAG, "ResultSetToEntity()... 异常");
		}
		return mStore;
	}

	private void setContentValues(Store store) {
		insertValues = new ContentValues();

		insertValues.put(COLUMN_ACCOUNT, store.getAccount());
		insertValues.put(COLUMN_ADDRESS, store.getAddress());
		insertValues.put(COLUMN_CHEAPEN_NOTICE, store.getCheapenNotice());
		insertValues.put(COLUMN_CITY_CODE, store.getCityCode());
		insertValues.put(COLUMN_ID, store.getStoreId());
		insertValues.put(COLUMN_LATLNG, store.getLatlng());
		insertValues.put(COLUMN_NEWUSER_NOTICE, store.getNewuserNotice());
		insertValues.put(COLUMN_NOTICE, store.getNotice());
		insertValues.put(COLUMN_SHOP_HOURS, store.getShopHours());
		insertValues.put(COLUMN_START_PRICE, store.getStartPrice());
		insertValues.put(COLUMN_STORE_LOGO, store.getStoreLogo());
		insertValues.put(COLUMN_STORE_NAME, store.getStoreName());
		insertValues.put(COLUMN_TEL, store.getTel());
		insertValues.put(COLUMN_TRANSPORT_PRICE, store.getTransportPrice());
	}

	public ContentValues getContentValues() {
		return insertValues;
	}

}
