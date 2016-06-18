package com.mele.control;

import static com.mele.Constants.ISDEBUG;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mele.dao.util.ContentValues;
import com.mele.dao.util.SQLQueryBuilder;
import com.mele.util.LogUtil;
import com.mele.util.TextUtils;

/**
 * 封装数据库操作
 * 
 * @author Jinhu
 * @date 2016/6/9
 */
public abstract class DbContentProvider {
	private static final String TAG = DbContentProvider.class.getSimpleName();

	/** 数据库连接 。 */
	protected Connection mConn;

	protected DbContentProvider(Connection conn) {
		if (null == conn)
			throw new NullPointerException();
		mConn = conn;
	}

	/**
	 * 将关系表中一行数据转成对象
	 * 
	 * @param resultSet
	 *            表数据集合
	 * @return 对象
	 */
	protected abstract <T> T ResultSetToEntity(ResultSet resultSet);

	/**
	 * 设置是否启动事件操作<br/>
	 * false则启动
	 * 
	 * @param auto
	 */
	protected void setAutoCommit(boolean auto) {
		if (null == mConn)
			throw new NullPointerException();
		try {
			mConn.setAutoCommit(auto);
		} catch (SQLException e) {
			e.printStackTrace();
			LogUtil.warning(TAG, "setAutoCommit(boolean)...启动事件操作异常");
		}
	}

	/**
	 * 提交事件操作
	 * 
	 */
	protected void commit() {
		if (null == mConn)
			throw new NullPointerException();
		try {
			mConn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			LogUtil.warning(TAG, "commit()...提交事件异常");
		}
	}

	/**
	 * 撤销事件
	 * 
	 */
	protected void rollback() {
		if (null == mConn)
			throw new NullPointerException();
		try {
			mConn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
			LogUtil.warning(TAG, "rollback()...撤销事件异常");
		}
	}

	/**
	 * 插入数据
	 * 
	 * @param tableName
	 *            操作表
	 * @param initialValues
	 *            插入数据集
	 * @return boolean
	 * @throws SQLException
	 */
	protected boolean insert(String tableName, ContentValues initialValues)
			throws SQLException {
		if (null == mConn)
			throw new NullPointerException();

		synchronized (mConn) {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT");
			sql.append(" INTO ");
			sql.append(tableName);
			sql.append('(');
			Object[] bindArgs = null;
			int size = (initialValues != null && initialValues.size() > 0) ? initialValues
					.size() : 0;
			if (size > 0) {
				bindArgs = new Object[size];
				int i = 0;
				for (String colName : initialValues.keySet()) {
					sql.append((i > 0) ? "," : "");
					sql.append(colName);
					bindArgs[i++] = initialValues.get(colName);
				}
				sql.append(')');
				sql.append(" VALUES (");
				for (i = 0; i < size; i++) {
					sql.append((i > 0) ? ",?" : "?");
				}
			} else {
				sql.append(") VALUES (NULL");
			}
			sql.append(')');

			PreparedStatement statement = mConn
					.prepareStatement(sql.toString());
			if (ISDEBUG)
				System.out.print("<" + TAG + "> " + sql.toString() + "\t\t");
			for (int i = 0; null != bindArgs && i < bindArgs.length; i++) {
				statement.setObject(i + 1, bindArgs[i]);
				if (ISDEBUG)
					System.out.print(bindArgs[i] + "\t");
			}
			if (ISDEBUG)
				System.out.println();

			statement.execute();
			statement.close();
			return true;
		}
	}

	/**
	 * 删除数据
	 * 
	 * @param deleteSql
	 * @param selection
	 * @param selectionArgs
	 * @return
	 * @throws SQLException
	 */
	protected boolean delete(String tableName, String selection,
			String... selectionArgs) throws SQLException {
		if (null == mConn)
			throw new NullPointerException();

		synchronized (mConn) {
			String sql = "DELETE FROM "
					+ tableName
					+ (!TextUtils.isEmpty(selection) ? " WHERE " + selection
							: "");

			PreparedStatement statement = mConn.prepareStatement(sql);
			if (ISDEBUG)
				System.out.print("<" + TAG + "> " + sql + "\t\t");
			for (int i = 0; null != selectionArgs && i < selectionArgs.length; i++) {
				statement.setObject(i + 1, selectionArgs[i]);
				if (ISDEBUG)
					System.out.print(selectionArgs[i] + "\t");
			}
			if (ISDEBUG)
				System.out.println();
			statement.execute();
			statement.close();
			return true;
		}

	}

	/**
	 * 更新数据
	 * 
	 * @param tableName
	 * @param values
	 * @param selection
	 * @param selectionArgs
	 * @return
	 * @throws SQLException
	 */
	protected int update(String tableName, ContentValues values,
			String selection, String... selectionArgs) throws SQLException {
		if (null == mConn)
			throw new NullPointerException();

		if (values == null || values.size() == 0) {
			throw new IllegalArgumentException("Empty values");
		}

		synchronized (mConn) {
			StringBuilder sql = new StringBuilder(120);
			sql.append("UPDATE ");
			sql.append(tableName);
			sql.append(" SET ");

			int setValuesSize = values.size();
			int bindArgsSize = (selectionArgs == null) ? setValuesSize
					: (setValuesSize + selectionArgs.length);
			Object[] bindArgs = new Object[bindArgsSize];
			int i = 0;
			for (String colName : values.keySet()) {
				sql.append((i > 0) ? "," : "");
				sql.append(colName);
				bindArgs[i++] = values.get(colName);
				sql.append("=?");
			}
			if (selectionArgs != null) {
				for (i = setValuesSize; i < bindArgsSize; i++) {
					bindArgs[i] = selectionArgs[i - setValuesSize];
				}
			}
			if (!TextUtils.isEmpty(selection)) {
				sql.append(" WHERE ");
				sql.append(selection);
			}

			PreparedStatement statement = mConn
					.prepareStatement(sql.toString());
			if (ISDEBUG)
				System.out.print("<" + TAG + "> " + sql.toString() + "\t\t");
			for (i = 0; null != bindArgs && i < bindArgs.length; i++) {
				statement.setObject(i + 1, bindArgs[i]);
				if (ISDEBUG)
					System.out.print(bindArgs[i] + "\t");
			}
			if (ISDEBUG)
				System.out.println();

			int updateedRows = statement.executeUpdate();
			statement.close();
			return updateedRows;
		}
	}

	/**
	 * 搜索数据
	 * 
	 * @param tableName
	 * @param columns
	 * @param selection
	 * @param selectionArgs
	 * @param sortOrder
	 * @return
	 * @throws SQLException
	 */
	protected ResultSet query(String tableName, String[] columns,
			String selection, String[] selectionArgs, String sortOrder)
			throws SQLException {
		return query(false, tableName, columns, selection, selectionArgs, null,
				null, sortOrder, null);
	}

	/**
	 * 
	 * @param distinct
	 * @param tableName
	 * @param columns
	 * @param selection
	 * @param selectionArgs
	 * @param sortOrder
	 * @return
	 * @throws SQLException
	 */
	protected ResultSet query(boolean distinct, String tableName,
			String[] columns, String selection, String[] selectionArgs,
			String sortOrder) throws SQLException {
		return query(distinct, tableName, columns, selection, selectionArgs,
				null, null, sortOrder, null);
	}

	/**
	 * 
	 * @param tableName
	 * @param columns
	 * @param selection
	 * @param selectionArgs
	 * @param group
	 * @param having
	 * @param sortOrder
	 * @param limit
	 * @return
	 * @throws SQLException
	 */
	protected ResultSet query(String tableName, String[] columns,
			String selection, String[] selectionArgs, String group,
			String having, String sortOrder, String limit) throws SQLException {
		return query(false, tableName, columns, selection, selectionArgs,
				group, having, sortOrder, null);
	}

	/**
	 * 
	 * @param distinct
	 * @param table
	 * @param columns
	 * @param selection
	 * @param selectionArgs
	 * @param groupBy
	 * @param having
	 * @param orderBy
	 * @param limit
	 * @return
	 * @throws SQLException
	 */
	protected ResultSet query(boolean distinct, String table, String[] columns,
			String selection, String[] selectionArgs, String groupBy,
			String having, String orderBy, String limit) throws SQLException {
		if (null == mConn)
			throw new NullPointerException();

		synchronized (mConn) {
			String sql = SQLQueryBuilder.buildQueryString(distinct, table,
					columns, selection, groupBy, having, orderBy, limit);

			PreparedStatement statement = mConn.prepareStatement(sql);
			if (ISDEBUG)
				System.out.print("<" + TAG + "> " + sql + "\t\t");
			for (int i = 0; null != selectionArgs && i < selectionArgs.length; i++) {
				statement.setObject(i + 1, selectionArgs[i]);
				if (ISDEBUG)
					System.out.print(selectionArgs[i] + "\t");
			}
			if (ISDEBUG)
				System.out.println();
			ResultSet mResultSet = statement.executeQuery();
			// statement.close(); //这里不要关闭statement 会连同ResultSet也关闭
			return mResultSet;
		}

	}

}
