package com.mele.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mele.control.DbContentProvider;
import com.mele.dao.IUserAddressDao;
import com.mele.dao.IUserAddressSchema;
import com.mele.dao.entity.UserAddress;
import com.mele.dao.util.ContentValues;
import com.mele.util.LogUtil;
import com.mele.util.TextUtils;

public class UserAddressDao extends DbContentProvider implements
		IUserAddressDao, IUserAddressSchema {
	private static final String TAG = UserAddressDao.class.getSimpleName();

	private ContentValues insertValues;

	public UserAddressDao(Connection conn) {
		super(conn);
	}

	/**
	 * 添加用户收货地址信息
	 * 
	 * @param userAddress
	 * @return
	 */
	@Override
	public boolean addUserAddress(UserAddress userAddress)throws Exception  {
		if (null == userAddress || userAddress.isNullValues())
			throw new NullPointerException();
		setContentValues(userAddress);
		try {
			return super.insert(USER_ADDRESS_TABLE, insertValues);
		} catch (SQLException e) {
			e.printStackTrace();
			LogUtil.warning(TAG, "addUserAddress(UserAddress)... 插入数据异常");
		}
		return false;
	}

	/**
	 * 添加用户地址列表
	 * 
	 * @param userAddressList
	 * @return
	 */
	@Override
	public boolean addUserAddressList(List<UserAddress> userAddressList)throws Exception  {
		if (null == userAddressList || 0 == userAddressList.size())
			throw new NullPointerException("stores 数据不能为空！");

		try {
			super.setAutoCommit(false); // 启动事务提交
			for (UserAddress userAddress : userAddressList) {
				setContentValues(userAddress);
				super.insert(USER_ADDRESS_TABLE, insertValues);
			}
			super.commit();
			super.setAutoCommit(true);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			super.rollback();
			LogUtil.warning(TAG,
					"addUserAddressList(List<UserAddress>)...增加用户收货地址列表数据异常,已回退操作!!!");
		}
		super.setAutoCommit(true);
		return false;
	}

	/**
	 * 删除用户地址信息
	 * 
	 * @param account
	 * @param addressId
	 * @return
	 */
	@Override
	public boolean deleteUserAddress(String account, int addressId)throws Exception {
		if (addressId <= 0 || TextUtils.isEmpty(account))
			throw new NullPointerException();
		final String selection = COLUMN_ID + " = ?  AND " + COLUMN_USER_ACCOUNT
				+ " = ? ";
		final String selectionArgs[] = { addressId + "", account };
		try {
			boolean isDeleted = super.delete(USER_ADDRESS_TABLE, selection,
					selectionArgs);
			if (isDeleted) {
				LogUtil.info(TAG, "删除'" + USER_ADDRESS_TABLE + "'表数据:"
						+ COLUMN_ID + "= " + addressId + " "
						+ COLUMN_USER_ACCOUNT + "= " + account);
			}
			return isDeleted;
		} catch (SQLException e) {
			e.printStackTrace();
			LogUtil.warning(TAG, "deleteUserAddress(String,int)...删除数据异常");
		}
		return false;
	}

	/**
	 * 删除所有用户地址信息
	 * 
	 * @return
	 */
	@Override
	public boolean deleteAllUserAddress() {
		try {
			boolean isDeleted = super.delete(USER_ADDRESS_TABLE, null, null);
			if (isDeleted)
				LogUtil.info(TAG, "删除'" + USER_ADDRESS_TABLE + "'所有数据");
			return isDeleted;
		} catch (SQLException e) {
			e.printStackTrace();
			LogUtil.warning(TAG, "deleteAllUserAddress()...删除数据异常");
		}
		return false;
	}

	/**
	 * 修改用户地址信息
	 * 
	 * @param account
	 * @param addressId
	 * @param values
	 * @return
	 */
	@Override
	public int updateUserAddressValues(String account, int addressId,
			ContentValues values)throws Exception  {
		if (null == values || TextUtils.isEmpty(account) || addressId <= 0)
			throw new NullPointerException();

		final String selection = COLUMN_ID + "= ? AND " + COLUMN_USER_ACCOUNT
				+ "= ?";
		final String selectionArgs[] = { addressId + "", account };
		try {
			int updatedRows = super.update(USER_ADDRESS_TABLE, values,
					selection, selectionArgs);
			if (updatedRows > 0)
				LogUtil.info(TAG,
						"updateUserAddressValues()...  updated row : "
								+ updatedRows);
			return updatedRows;
		} catch (SQLException e) {
			e.printStackTrace();
			LogUtil.warning(TAG, "updateUserAddressValues()... 更新商家信息 异常");
		}
		return 0;
	}

	/**
	 * 修改用户地址联系人
	 * 
	 * @param account
	 * @param addressId
	 * @param contact
	 * @return
	 */
	@Override
	public int updateUserAddressInContact(String account, int addressId,
			String contact)throws Exception  {
		if (TextUtils.isEmpty(contact))
			throw new NullPointerException();

		final ContentValues values = new ContentValues();
		values.put(COLUMN_CONTACT, contact);
		return this.updateUserAddressValues(account, addressId, values);
	}

	/**
	 * 修改联系人性别
	 * 
	 * @param account
	 * @param addressId
	 * @param sex
	 * @return
	 */
	@Override
	public int updateUserAddressInSex(String account, int addressId, int sex)throws Exception  {
		if (sex != 1 && sex != 2)
			throw new IllegalArgumentException("sex must be 1 and 2");

		final ContentValues values = new ContentValues();
		values.put(COLUMN_SEX, sex);
		return this.updateUserAddressValues(account, addressId, values);
	}

	/**
	 * 修改联系人电话
	 * 
	 * @param account
	 * @param addressId
	 * @param tel
	 * @return
	 */
	@Override
	public int updateUserAddressInTel(String account, int addressId, String tel)throws Exception  {
		if (TextUtils.isEmpty(tel))
			throw new NullPointerException();

		final ContentValues values = new ContentValues();
		values.put(COLUMN_TEL, tel);
		return this.updateUserAddressValues(account, addressId, values);
	}

	/**
	 * 修改地址标签
	 * 
	 * @param account
	 * @param addressId
	 * @param tag
	 * @return
	 */
	@Override
	public int updateUserAddressInTag(String account, int addressId, String tag)throws Exception  {
		if (TextUtils.isEmpty(tag))
			throw new NullPointerException();

		final ContentValues values = new ContentValues();
		values.put(COLUMN_TAG, tag);
		return this.updateUserAddressValues(account, addressId, values);
	}

	/**
	 * 修改联系人地址
	 * 
	 * @param account
	 * @param addressId
	 * @param address
	 * @return
	 */
	@Override
	public int updateUserAddressInAddress(String account, int addressId,
			String address) throws Exception {
		if (TextUtils.isEmpty(address))
			throw new NullPointerException();

		final ContentValues values = new ContentValues();
		values.put(COLUMN_ADDRESS, address);
		return this.updateUserAddressValues(account, addressId, values);
	}

	/**
	 * 获取所有用户地址信息
	 * 
	 * @return
	 */
	@Override
	public List<UserAddress> fetchAllUserAddress() {
		List<UserAddress> userAddressList = new ArrayList<UserAddress>();
		try {
			ResultSet mResultSet = super.query(USER_ADDRESS_TABLE,
					USER_ADDRESS_COLUMNS, null, null, COLUMN_USER_ACCOUNT);
			if (null != mResultSet) {
				while (mResultSet.next()) {
					UserAddress userAddress = ResultSetToEntity(mResultSet);
					if (null != userAddress && !userAddress.isNullValues()) {
						userAddressList.add(userAddress);
					}
				}
				mResultSet.close();
				return userAddressList;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			LogUtil.warning(TAG, "fetchAllUserAddress()...查询异常");
		}
		return null;
	}

	/**
	 * 根据账号信息获取所有用户地址信息
	 * 
	 * @param account
	 * @return
	 */
	@Override
	public List<UserAddress> fetchAllUserAddressByAccount(String account) throws Exception {
		if (TextUtils.isEmpty(account))
			throw new NullPointerException();

		List<UserAddress> userAddressList = new ArrayList<UserAddress>();
		final String selection = COLUMN_USER_ACCOUNT + " = ? ";
		final String selectionArgs[] = { account };
		try {
			ResultSet mResultSet = super.query(USER_ADDRESS_TABLE,
					USER_ADDRESS_COLUMNS, selection, selectionArgs,
					COLUMN_USER_ACCOUNT);
			if (null != mResultSet) {
				while (mResultSet.next()) {
					UserAddress userAddress = ResultSetToEntity(mResultSet);
					if (null != userAddress && !userAddress.isNullValues()) {
						userAddressList.add(userAddress);
					}
				}
				mResultSet.close();
				return userAddressList;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			LogUtil.warning(TAG, "fetchAllUserAddressByAccount()...查询异常");
		}
		return null;
	}

	/**
	 * 根据账号信息及地址标签获取所有用户地址信息
	 * 
	 * @param account
	 * @param tag
	 * @return
	 */
	@Override
	public List<UserAddress> fetchAllUserAddressByAccountNTag(String account,
			String tag)throws Exception  {
		if (TextUtils.isEmpty(account) || TextUtils.isEmpty(tag))
			throw new NullPointerException();

		List<UserAddress> userAddressList = new ArrayList<UserAddress>();
		final String selection = COLUMN_USER_ACCOUNT + " = ? AND " + COLUMN_TAG
				+ "= ? ";
		final String selectionArgs[] = { account, tag };
		try {
			ResultSet mResultSet = super.query(USER_ADDRESS_TABLE,
					USER_ADDRESS_COLUMNS, selection, selectionArgs,
					COLUMN_USER_ACCOUNT);
			if (null != mResultSet) {
				while (mResultSet.next()) {
					UserAddress userAddress = ResultSetToEntity(mResultSet);
					if (null != userAddress && !userAddress.isNullValues()) {
						userAddressList.add(userAddress);
					}
				}
				mResultSet.close();
				return userAddressList;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			LogUtil.warning(TAG, "fetchAllUserAddressByAccountNTag()...查询异常");
		}
		return null;
	}

	/**
	 * 获取指定账号及地址Id的地址信息
	 * 
	 * @param account
	 * @param addressId
	 * @return
	 */
	@Override
	public UserAddress fetchUserAddress(String account, int addressId)throws Exception  {
		if (TextUtils.isEmpty(account) || addressId <= 0)
			throw new NullPointerException();

		final String selection = COLUMN_USER_ACCOUNT + " = ? AND " + COLUMN_ID
				+ "= ? ";
		final String selectionArgs[] = { account, addressId + "" };
		try {
			ResultSet mResultSet = super.query(USER_ADDRESS_TABLE,
					USER_ADDRESS_COLUMNS, selection, selectionArgs,
					COLUMN_USER_ACCOUNT);
			if (null != mResultSet && mResultSet.next()) {
				UserAddress userAddress = ResultSetToEntity(mResultSet);
				mResultSet.close();
				return userAddress;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			LogUtil.warning(TAG, "fetchUserAddress()...查询异常");
		}
		return null;
	}

	@Override
	protected UserAddress ResultSetToEntity(ResultSet resultSet) {
		UserAddress userAddress = new UserAddress();
		try {
			if (null != resultSet) {
				int addressId = resultSet.getInt(COLUMN_ID);
				String userAccount = resultSet.getString(COLUMN_USER_ACCOUNT);
				String userAddressStr = resultSet.getString(COLUMN_ADDRESS);
				String userContact = resultSet.getString(COLUMN_CONTACT);
				int userSex = resultSet.getInt(COLUMN_SEX);
				String userTag = resultSet.getString(COLUMN_TAG);
				String userTel = resultSet.getString(COLUMN_TEL);

				userAddress.setAddressId(addressId);
				userAddress.setUserAccount(userAccount);
				userAddress.setUserAddress(userAddressStr);
				userAddress.setUserContact(userContact);
				userAddress.setUserSex(userSex);
				userAddress.setUserTag(userTag);
				userAddress.setUserTel(userTel);
				return userAddress;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ContentValues getContentValues() {
		return insertValues;
	}

	private void setContentValues(UserAddress userAddress)throws Exception  {
		if (null == userAddress || userAddress.isNullValues())
			throw new NullPointerException();

		insertValues = new ContentValues();

		insertValues.put(COLUMN_ID, userAddress.getAddressId());
		insertValues.put(COLUMN_CONTACT, userAddress.getUserContact());
		insertValues.put(COLUMN_ADDRESS, userAddress.getUserAddress());
		insertValues.put(COLUMN_SEX, userAddress.getUserSex());
		insertValues.put(COLUMN_TAG, userAddress.getUserTag());
		insertValues.put(COLUMN_TEL, userAddress.getUserTel());
		insertValues.put(COLUMN_USER_ACCOUNT, userAddress.getUserAccount());
	}

}
