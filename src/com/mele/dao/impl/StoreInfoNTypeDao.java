package com.mele.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mele.control.DbContentProvider;
import com.mele.dao.IStoreInfoNTypeDao;
import com.mele.dao.IStoreInfoNTypeSchema;
import com.mele.dao.entity.StoreInfoNType;
import com.mele.dao.util.ContentValues;
import com.mele.util.LogUtil;

/**
 * 'StoreInfo_Type'数据访问接口
 * 
 * @author Jinhu
 * @date 2016/6/11
 */
public class StoreInfoNTypeDao extends DbContentProvider implements
		IStoreInfoNTypeDao, IStoreInfoNTypeSchema {
	private static final String TAG = StoreInfoNTypeDao.class.getSimpleName();

	/** 插入数据集 。 */
	private ContentValues insertValues;

	public StoreInfoNTypeDao(Connection conn) {
		super(conn);
	}

	/**
	 * 将当前ResultSet行数据转成StoreInfoNType对象
	 */
	@Override
	protected StoreInfoNType ResultSetToEntity(ResultSet resultSet) {
		StoreInfoNType mStoreInfoNType = new StoreInfoNType();
		try {
			if (null != resultSet) {
				long storeId = resultSet.getLong(COLUMN_STORE_ID);
				int storeTypeId = resultSet.getInt(COLUMN_STORE_TYPE_ID);

				mStoreInfoNType.setStoreId(storeId);
				mStoreInfoNType.setStypeId(storeTypeId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			LogUtil.warning(TAG, "ResultSetToEntity()... 异常");
		}
		return mStoreInfoNType;
	}

	/**
	 * 添加商家信息与类型联系
	 * 
	 * @param store
	 * @return
	 */
	@Override
	public boolean addStoreInfoNType(StoreInfoNType storeInfoNType)throws Exception {
		if (null == storeInfoNType || storeInfoNType.getStoreId() < 0
				|| storeInfoNType.getStypeId() < 0)
			throw new NullPointerException();

		setContentValues(storeInfoNType);
		try {
			return super.insert(STOREINFO_TYPE_TABLE, insertValues);
		} catch (SQLException e) {
			e.printStackTrace();
			LogUtil.warning(TAG, "addStoreInfoNType(StoreInfoNType)... 插入数据异常");
		}
		return false;
	}

	/**
	 * 删除商家信息与类型联系
	 * 
	 * @param storeId
	 * @return
	 */
	@Override
	public boolean deleteStoreInfoNType(StoreInfoNType storeInfoNType)throws Exception {
		if (null == storeInfoNType || storeInfoNType.getStoreId() < 0
				|| storeInfoNType.getStypeId() < 0)
			throw new NullPointerException();

		final String selection = COLUMN_STORE_ID + " = ? " + " and "
				+ COLUMN_STORE_TYPE_ID + " = ? ";
		final String selectionArgs[] = { storeInfoNType.getStoreId() + "",
				storeInfoNType.getStypeId() + "" };

		try {
			boolean isDeleted = super.delete(STOREINFO_TYPE_TABLE, selection,
					selectionArgs);
			if (isDeleted) {
				LogUtil.info(
						TAG,
						"删除'" + STOREINFO_TYPE_TABLE + "'表数据:"
								+ COLUMN_STORE_ID
								+ "= "
								+ storeInfoNType.getStoreId()//
								+ COLUMN_STORE_TYPE_ID + "= "
								+ storeInfoNType.getStypeId());
			}
			return isDeleted;
		} catch (SQLException e) {
			e.printStackTrace();
			LogUtil.warning(TAG,
					"deleteStoreInfoNType(StoreInfoNType)...删除数据异常");
		}
		return false;
	}

	/**
	 * 删除所有商家信息与类型联系
	 * 
	 * @return
	 */
	@Override
	public boolean deleteAllStoreInfoNType() {
		try {
			boolean isDeleted = super.delete(STOREINFO_TYPE_TABLE, null, null);
			if (isDeleted)
				LogUtil.info(TAG, "删除'" + STOREINFO_TYPE_TABLE + "'所有数据");
			return isDeleted;
		} catch (SQLException e) {
			e.printStackTrace();
			LogUtil.warning(TAG, "deleteAllStoreInfoNType()...删除数据异常");
		}
		return false;
	}

	/**
	 * 根据商家类型Id获取所有商家信息与类型联系
	 * 
	 * @param storeTypeId
	 * @return
	 */
	@Override
	public List<StoreInfoNType> fetchAllStoreInfoNTypesByTypeId(int storeTypeId)throws Exception {
		if (storeTypeId < 0)
			throw new IllegalArgumentException();

		List<StoreInfoNType> mStoreInfoNTypes = new ArrayList<StoreInfoNType>();

		final String selection = COLUMN_STORE_TYPE_ID + "= ? ";
		final String selectionArgs[] = { storeTypeId + "" };

		try {
			ResultSet mResultSet = super.query(STOREINFO_TYPE_TABLE,
					STOREINFO_TYPE_COLUMNS, selection, selectionArgs,
					COLUMN_STORE_ID);
			if (null != mResultSet) {
				while (mResultSet.next()) {
					StoreInfoNType storeInfoNType = ResultSetToEntity(mResultSet);
					if (null != storeInfoNType
							&& storeInfoNType.getStoreId() > 0
							&& storeInfoNType.getStypeId() > 0)
						mStoreInfoNTypes.add(storeInfoNType);
				}
				mResultSet.close();
			}
		
			return mStoreInfoNTypes;
		} catch (SQLException e) {
			e.printStackTrace();
			LogUtil.warning(TAG,
					"fetchAllStoreInfoNTypesByStoreId(long)...查询异常");
		}
		return null;
	}

	/**
	 * 根据商家Id获取所有商家信息与类型联系
	 * 
	 * @param storeId
	 * @return
	 */
	@Override
	public List<StoreInfoNType> fetchAllStoreInfoNTypesByStoreId(long storeId)throws Exception {
		if (storeId < 0)
			throw new IllegalArgumentException();

		List<StoreInfoNType> mStoreInfoNTypes = new ArrayList<StoreInfoNType>();

		final String selection = COLUMN_STORE_ID + "= ? ";
		final String selectionArgs[] = { storeId + "" };

		try {
			ResultSet mResultSet = super.query(STOREINFO_TYPE_TABLE,
					STOREINFO_TYPE_COLUMNS, selection, selectionArgs,
					COLUMN_STORE_ID);
			if (null != mResultSet) {
				while (mResultSet.next()) {
					StoreInfoNType storeInfoNType = ResultSetToEntity(mResultSet);
					if (null != storeInfoNType
							&& storeInfoNType.getStoreId() > 0
							&& storeInfoNType.getStypeId() > 0)
						mStoreInfoNTypes.add(storeInfoNType);
				}
				mResultSet.close();
			}
			return mStoreInfoNTypes;
		} catch (SQLException e) {
			e.printStackTrace();
			LogUtil.warning(TAG,
					"fetchAllStoreInfoNTypesByStoreId(long)...查询异常");
		}
		return null;
	}

	/**
	 * 获取所有商家信息与类型联系
	 * 
	 * @return
	 */
	@Override
	public List<StoreInfoNType> fetchAllStoreInfoNTypes() {
		List<StoreInfoNType> mStoreInfoNTypes = new ArrayList<StoreInfoNType>();
		try {
			ResultSet mResultSet = super.query(STOREINFO_TYPE_TABLE,
					STOREINFO_TYPE_COLUMNS, null, null, COLUMN_STORE_ID);
			if (null != mResultSet) {
				while (mResultSet.next()) {
					StoreInfoNType storeInfoNType = ResultSetToEntity(mResultSet);
					if (null != storeInfoNType
							&& storeInfoNType.getStoreId() > 0
							&& storeInfoNType.getStypeId() > 0)
						mStoreInfoNTypes.add(storeInfoNType);
				}
				mResultSet.close();
			}
			return mStoreInfoNTypes;
		} catch (SQLException e) {
			e.printStackTrace();
			LogUtil.warning(TAG, "fetchAllStoreInfoNTypes()...查询异常");
		}
		return null;
	}

	private void setContentValues(StoreInfoNType storeInfoNType) {
		insertValues = new ContentValues();
		insertValues.put(COLUMN_STORE_ID, storeInfoNType.getStoreId());
		insertValues.put(COLUMN_STORE_TYPE_ID, storeInfoNType.getStypeId());
	}

	public ContentValues getContentValues() {
		return insertValues;
	}

}
