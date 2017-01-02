package com.mele.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


import com.mele.control.DbContentProvider;
import com.mele.dao.IUserDao;
import com.mele.dao.IUserSchema;
import com.mele.dao.entity.Store;
import com.mele.dao.entity.User;
import com.mele.dao.util.ContentValues;
import com.mele.util.LogUtil;
import com.mele.util.TextUtils;

/**
 * 'User'数据访问接口
 * 
 * @author Jinhu
 * @date 2016/6/12
 */
public class UserDao extends DbContentProvider implements IUserSchema, IUserDao {
	private static final String TAG = UserDao.class.getSimpleName();

	/** 插入值 。 */
	private ContentValues insertValues;

	public UserDao(Connection conn) {
		super(conn);
	}

	@Override
	protected User ResultSetToEntity(ResultSet resultSet) {
		User mUser = new User();
		try {
			if (null != resultSet) {
				String account = resultSet.getString(COLUMN_ACCOUNT);
				String password = resultSet.getString(COLUMN_PASSWORD);
				String tel = resultSet.getString(COLUMN_TEL);
				String email = resultSet.getString(COLUMN_EMAIL);
				String payPassword = resultSet.getString(COLUMN_PAYPASSWORD);
				String headIc = resultSet.getString(COLUMN_HEAD_IC);
				int level = resultSet.getInt(COLUMN_LEVEL);

				mUser.setAccount(account);
				mUser.setEmail(email);
				mUser.setHeadic(headIc);
				mUser.setLevel(level);
				mUser.setPassword(password);
				mUser.setPaypassword(payPassword);
				mUser.setTel(tel);
				return mUser;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 添加用户信息
	 * 
	 * @param user
	 * @return
	 */
	@Override
	public boolean addUser(User user) {
		System.out.println(user);
		setContentValues(user);
		try {
			return super.insert(USER_TABLE, insertValues);
		} catch (SQLException e) {
			e.printStackTrace();
			LogUtil.warning(TAG, "addUser(User)... 插入数据异常");
		}
		return false;
	}

	/**
	 * 添加一组用户信息
	 * 
	 * @param users
	 * @return
	 */
	@Override
	public boolean addUsers(List<User> users) throws Exception {
		if (null == users || users.size() <= 0)
			throw new NullPointerException();

		try {
			super.setAutoCommit(false); // 启动事务提交
			for (User user : users) {
				setContentValues(user);
				super.insert(USER_TABLE, insertValues);
			}
			super.commit();
			super.setAutoCommit(true);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			super.rollback();
			LogUtil.warning(TAG, "addUsers(List<User>)...增加用户信息列表数据异常,已回退操作!!!");
		}
		super.setAutoCommit(true);
		return false;
	}

	/**
	 * 根据用户账户删除用户信息
	 * 
	 * @param account
	 * @return
	 */
	@Override
	public boolean deleteUser(String account) throws Exception {
		if (TextUtils.isEmpty(account))
			throw new NullPointerException();

		final String selection = COLUMN_ACCOUNT + " = ? ";
		final String selectionArgs[] = { account };
		try {
			return super.delete(USER_TABLE, selection, selectionArgs);
		} catch (SQLException e) {
			e.printStackTrace();
			LogUtil.warning(TAG, "deleteUser(String>)...删除数据失败!!!");
		}
		return false;
	}

	/**
	 * 删除所有用户
	 * 
	 * @return
	 */
	@Override
	public boolean deleteAllUser() {
		try {
			boolean isDeleted = super.delete(USER_TABLE, null, null);
			if (isDeleted)
				LogUtil.info(TAG, "删除'" + USER_TABLE + "'所有数据");
			return isDeleted;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 修改用户电话
	 * 
	 * @param account
	 * @param tel
	 * @return
	 */
	@Override
	public int updateUserTel(String account, String tel) throws Exception {
		if (TextUtils.isEmpty(tel))
			throw new NullPointerException();
		final ContentValues values = new ContentValues();
		values.put(COLUMN_TEL, tel);
		return updateUserValues(account, values);
	}

	/**
	 * 修改用户头像
	 * 
	 * @param account
	 * @param headIc
	 * @return
	 */
	@Override
	public int updateUserHeadIc(String account, String headIc) throws Exception {
		if (TextUtils.isEmpty(headIc))
			throw new NullPointerException();
		final ContentValues values = new ContentValues();
		values.put(COLUMN_HEAD_IC, headIc);
		return updateUserValues(account, values);
	}

	/**
	 * 修改用户级别
	 * 
	 * @param account
	 * @param level
	 *            1 2 3
	 * @return
	 */
	@Override
	public int updateUserLevel(String account, int level) throws Exception {
		if (level < 1 || level > 3)
			throw new IllegalArgumentException();

		final ContentValues values = new ContentValues();
		values.put(COLUMN_LEVEL, level);
		return updateUserValues(account, values);
	}

	/**
	 * 修改用户级别
	 * 
	 * @param account
	 * @param values
	 * @return
	 */
	@Override
	public int updateUserValues(String account, ContentValues values)
			throws Exception {
		if (TextUtils.isEmpty(account) || null == values)
			throw new NullPointerException();

		final String selection = COLUMN_ACCOUNT + "= ?";
		final String selectionArgs[] = { account + "" };
		try {
			int updatedRows = super.update(USER_TABLE, values, selection,
					selectionArgs);
			if (updatedRows > 0)
				LogUtil.info(TAG, "updateUserValues()...  updated row : "
						+ updatedRows);
			return updatedRows;
		} catch (SQLException e) {
			e.printStackTrace();
			LogUtil.warning(TAG, "updateUserValues()... 更新商家信息 异常");
		}
		return 0;
	}

	/**
	 * 获取所有用户信息
	 * 
	 * @return
	 */
	@Override
	public List<User> fetchAllUser() {
		List<User> users = new ArrayList<User>();
		try {
			ResultSet mResultSet = super.query(USER_TABLE, USER_COLUMNS, null,
					null, COLUMN_ACCOUNT);
			if (null != mResultSet) {
				while (mResultSet.next()) {
					User user = ResultSetToEntity(mResultSet);
					if (null != user && !user.isNullValues()) {
						users.add(user);
					}
				}
				mResultSet.close();
				return users;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取指定账户的用户信息
	 * 
	 * @param account
	 * @return
	 */
	@Override
	public User fetchUser(String account) {
		final String selection = COLUMN_ACCOUNT + "= ? ";
		final String selectionArgs[] = { account };

		User mUser = null;
		try {
			ResultSet mResultSet = super.query(USER_TABLE, USER_COLUMNS,
					selection, selectionArgs, COLUMN_ACCOUNT);
			if (null != mResultSet && mResultSet.next()) {
				mUser = ResultSetToEntity(mResultSet);
				mResultSet.close();
			}
			return mUser;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取指定等级的用户信息
	 * 
	 * @param level
	 * @return
	 */
	@Override
	public List<User> fetchAullUserByLevel(int level) throws Exception {
		if (level < 1 || level > 3)
			throw new IllegalArgumentException();
		List<User> users = new ArrayList<User>();

		final String selection = COLUMN_LEVEL + " = ? ";
		final String selectionArgs[] = { level + "" };
		try {
			ResultSet mResultSet = super.query(USER_TABLE, USER_COLUMNS,
					selection, selectionArgs, COLUMN_ACCOUNT);
			if (null != mResultSet) {
				while (mResultSet.next()) {
					User user = ResultSetToEntity(mResultSet);
					if (null != user && !user.isNullValues()) {
						users.add(user);
					}
				}
				mResultSet.close();
				return users;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取指定商店的用户信息
	 * 
	 * @param storeId
	 * @return
	 */
	@Override
	public User fetchUserByStoreId(long storeId) throws Exception {
		if (storeId <= 0)
			throw new IllegalArgumentException("storeId must >=1");

		StoreDao mStoreDao = new StoreDao(mConn);
		Store mStore = mStoreDao.fetchStoreByStoreId(storeId);
		if (null == mStore || mStore.isNullValue())
			return null;

		String account = mStore.getAccount();
		if (TextUtils.isEmpty(account))
			return null;

		return fetchUser(account);
	}

	public void setContentValues(User user) {
		insertValues = new ContentValues();

		insertValues.put(COLUMN_ACCOUNT, user.getAccount());
		insertValues.put(COLUMN_PASSWORD, user.getPassword());
		insertValues.put(COLUMN_EMAIL, user.getEmail());
		insertValues.put(COLUMN_HEAD_IC, user.getHeadic());
		insertValues.put(COLUMN_LEVEL, user.getLevel());
		insertValues.put(COLUMN_PAYPASSWORD, user.getPaypassword());
		insertValues.put(COLUMN_TEL, user.getTel());
	}

	public ContentValues getContentValues() {
		return insertValues;
	}
}
