package com.mele.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mele.control.DbContentProvider;
import com.mele.dao.IStoreTypeDao;
import com.mele.dao.IStoreTypeSchema;
import com.mele.dao.entity.StoreType;
import com.mele.dao.util.ContentValues;
import com.mele.util.LogUtil;
import com.mele.util.TextUtils;

/**
 * 'StoreType'数据访问接口
 * 
 * @author Jinhu
 * @date 2016/6/11
 */
public class StoreTypeDao extends DbContentProvider implements
		IStoreTypeSchema, IStoreTypeDao {
	private static final String TAG = StoreTypeDao.class.getSimpleName();

	/** 插入数据集 。 */
	private ContentValues insertValues;

	public StoreTypeDao(Connection conn) {
		super(conn);
	}

	/**
	 * 将当前ResultSet行数据转成StoreType对象
	 */
	@Override
	protected StoreType ResultSetToEntity(ResultSet resultSet) {
		StoreType mStoreType = new StoreType();
		try {
			if (null != resultSet) {
				int typeId = resultSet.getInt(COLUMN_ID);
				String typeName = resultSet.getString(COLUMN_TYPE_NAME);
				int superTypeId = resultSet.getInt(COLUMN_SUPER_ID);

				mStoreType.setStypeId(typeId);
				mStoreType.setTypeName(typeName);
				mStoreType.setSuperTypeId(superTypeId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			LogUtil.warning(TAG, "ResultSetToEntity()... 异常");
		}
		return mStoreType;
	}

	/**
	 * 添加商家类型
	 * 
	 * @param storeInfo
	 * @return
	 */
	@Override
	public boolean addStoreType(StoreType storeType)throws Exception  {
		if (null == storeType || storeType.getStypeId() <= 0)
			throw new NullPointerException();

		setContentValues(storeType);
		try {
			return super.insert(STORE_TYPE_TABLE, insertValues);
		} catch (SQLException e) {
			e.printStackTrace();
			LogUtil.warning(TAG, "addStoreType(StoreType)... 插入数据异常");
		}
		return false;
	}

	/**
	 * 添加商家类型列表
	 * 
	 * @param storeInfos
	 * @return
	 */
	@Override
	public boolean addStoreTypes(List<StoreType> storeTypes)throws Exception  {
		if (null == storeTypes || 0 == storeTypes.size())
			throw new NullPointerException("cities 数据不能为空！");
		try {
			super.setAutoCommit(false); // 启动事务提交
			for (StoreType storeType : storeTypes) {
				setContentValues(storeType);
				super.insert(STORE_TYPE_TABLE, insertValues);
			}
			super.commit();
			super.setAutoCommit(true);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			super.rollback();
			LogUtil.warning(TAG,
					"addStoreTypes(List<StoreType>)...增加商家类型列表数据异常,已回退操作!!!");
		}
		return false;
	}

	/**
	 * 根据类型Id删除
	 * 
	 * @param storeId
	 * @return
	 */
	@Override
	public boolean deleteStoreType(int storeTypeId)throws Exception  {
		if (storeTypeId < 0)
			throw new IllegalArgumentException("storeTypeId must > 0");
		final String selection = COLUMN_ID + " = ? ";
		final String selectionArgs[] = { storeTypeId + "" };
		try {
			boolean isDeleted = super.delete(STORE_TYPE_TABLE, selection,
					selectionArgs);
			if (isDeleted) {
				LogUtil.info(TAG, "删除'" + STORE_TYPE_TABLE + "'表数据:"
						+ COLUMN_ID + "= " + storeTypeId);
			}
			return isDeleted;
		} catch (SQLException e) {
			e.printStackTrace();
			LogUtil.warning(TAG, "deleteStoreType(int)...删除数据异常");
		}
		return false;
	}

	/**
	 * 删除所有商家类型
	 * 
	 * @return
	 */
	@Override
	public boolean deleteAllStoreTypes() {
		try {
			boolean isDeleted = super.delete(STORE_TYPE_TABLE, null, null);
			if (isDeleted)
				LogUtil.info(TAG, "删除'" + STORE_TYPE_TABLE + "'所有数据");
			return isDeleted;
		} catch (SQLException e) {
			e.printStackTrace();
			LogUtil.warning(TAG, "deleteAllStoreTypes()...删除数据异常");
		}
		return false;
	}

	/**
	 * 修改商家类型名
	 * 
	 * @param storeId
	 * @param storeTypeName
	 * @return
	 */
	@Override
	public int updateStoreTypeName(int storeTypeId, String storeTypeName)throws Exception  {
		if (storeTypeId < 0 || TextUtils.isEmpty(storeTypeName))
			throw new IllegalArgumentException(
					"storeTypeId must > 0 And storeTypeName should be no-null");

		final ContentValues updateValue = new ContentValues();
		updateValue.put(COLUMN_TYPE_NAME, storeTypeName);

		final String selection = COLUMN_ID + "= ?";
		final String selectionArgs[] = { storeTypeId + "" };

		try {
			int updatedRows = super.update(STORE_TYPE_TABLE, updateValue,
					selection, selectionArgs);
			if (updatedRows > 0)
				LogUtil.info(TAG, "updateStoreTypeName()...  updated row : "
						+ updatedRows);
			return updatedRows;
		} catch (SQLException e) {
			e.printStackTrace();
			LogUtil.warning(TAG, "updateStoreTypeName()...更新城市名异常");
		}
		return 0;
	}

	/**
	 * 获取所有商家类型
	 * 
	 * @return
	 */
	@Override
	public List<StoreType> fetchAllStoreTypes() {
		List<StoreType> storeTypes = new ArrayList<StoreType>();
		try {
			ResultSet mResultSet = super.query(STORE_TYPE_TABLE,
					STORE_TYPE_COLUMNS, null, null, COLUMN_ID);
			if (null != mResultSet) {
				while (mResultSet.next()) {
					StoreType storeType = ResultSetToEntity(mResultSet);
					if (null != storeType && storeType.getStypeId() > 0)
						storeTypes.add(storeType);
				}
				mResultSet.close();
			}
			return storeTypes;
		} catch (SQLException e) {
			e.printStackTrace();
			LogUtil.warning(TAG, "fetchAllStoreTypes()...查询异常");
		}
		return null;
	}

	/**
	 * 根据类型ID获取指定商家类型
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public StoreType fetchStoreTypeById(int id) throws Exception {
		if (id < 0)
			throw new IllegalArgumentException("storeTypeId must > 0 ");

		final String selection = COLUMN_ID + "= ? ";
		final String selectionArgs[] = { id + "" };

		StoreType storeType = new StoreType();
		try {
			ResultSet mResultSet = super.query(STORE_TYPE_TABLE,
					STORE_TYPE_COLUMNS, selection, selectionArgs, COLUMN_ID);
			if (null != mResultSet && mResultSet.next()) {
				storeType = ResultSetToEntity(mResultSet);
				mResultSet.close();
			}
			return storeType;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据父级类型Id获取指定商家类型集
	 * 
	 * @param superId
	 * @return
	 */
	@Override
	public List<StoreType> fetchStoreTypeBySuperId(int superId)throws Exception  {
		if (superId < 0)
			throw new IllegalArgumentException("superStoreTypeId must > 0 ");

		final String selection = COLUMN_SUPER_ID + "= ? ";
		final String selectionArgs[] = { superId + "" };

		List<StoreType> storeTypes = new ArrayList<StoreType>();
		try {
			ResultSet mResultSet = super.query(STORE_TYPE_TABLE,
					STORE_TYPE_COLUMNS, selection, selectionArgs, COLUMN_ID);
			if (null != mResultSet) {
				while (mResultSet.next()) {
					StoreType storeType = ResultSetToEntity(mResultSet);
					if (null != storeType && storeType.getStypeId() > 0)
						storeTypes.add(storeType);
				}
				mResultSet.close();
			}
			return storeTypes;
		} catch (SQLException e) {
			e.printStackTrace();
			LogUtil.warning(TAG, "fetchAllCities()...查询异常");
		}
		return null;
	}

	private void setContentValues(StoreType storeType) {
		insertValues = new ContentValues();
		// if (storeType.getStypeId() > 0)
		insertValues.put(COLUMN_ID, storeType.getStypeId());
		insertValues.put(COLUMN_TYPE_NAME, storeType.getTypeName());
		insertValues.put(COLUMN_SUPER_ID, storeType.getSuperTypeId());
	}

	public ContentValues getContentValues() {
		return insertValues;
	}

}
