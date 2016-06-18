package com.mele.control;

import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import javax.sql.DataSource;

import com.mele.util.LogUtil;

/**
 * 数据库管理
 * 
 * @author Jinhu
 * @date 2016/6/9
 */
public class DatabaseManager implements DataSource {
	private static final String TAG = DatabaseManager.class.getSimpleName();

	/** JDBC驱动连接 。 */
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/";
	/** JDBC驱动类型 。 */
	private static final String JDBC_NAME = "com.mysql.jdbc.Driver";
	/** JDBC连接配置 。 */
	private static final String JDBC_PRO = "?useUnicode=true&characterEncoding=utf-8&useSSL=false";
	/** 数据库连接池容量 。 */
	private static final int JDBC_CONN_POOL = 50;

	/** 数据库名 。 */
	private static final String DATABASE_NAME = "ele";
	/** 管理账号 。 */
	private static final String USER = "root";
	/** 管理密码 。 */
	private static final String PASSWORD = "5631";

	/** 数据库管理器单例 。 */
	private static DatabaseManager mDBMag;
	/* 数据库连接池 。 */
	private LinkedList<Connection> mConnectPool;
	/** 当前所有连接数据库的对象 。 */
	private LinkedHashMap<String, Connection> mCurConnects;

	private DatabaseManager() {
		mConnectPool = new LinkedList<Connection>();
		mCurConnects = new LinkedHashMap<String, Connection>();
		initDatabase();
	}

	/*
	 * @return 数据库管理单例
	 */
	public static DatabaseManager getInstance() {
		if (null == mDBMag) {
			mDBMag = new DatabaseManager();
		}
		return mDBMag;
	}

	/**
	 * 初始化加载数据库
	 */
	private void initDatabase() {
		try {
			Class.forName(JDBC_NAME); // 指定连接驱动类型
			for (int i = 0; i < JDBC_CONN_POOL; i++) {
				Connection conn = DriverManager.getConnection( //
						JDBC_URL + DATABASE_NAME + JDBC_PRO, //
						USER, PASSWORD); // 获取数据库连接
				mConnectPool.add(conn);
			}
			LogUtil.info(TAG, "已初始化数据库连接池,连接池大小=" + mConnectPool.size());
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.warning(TAG, "初始化数据库连接池异常!");
		}
	}

	/**
	 * 打开一个数据库连接
	 * 
	 * @return 数据库连接
	 */
	public Connection open() {
		try {
			return this.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Connection open(String account) {
		// 当前用户已经连接
		if (mCurConnects.containsKey(account)) {
			LogUtil.info(account + "获取其维护的数据库连接,连接池大小=" + mConnectPool.size());
			return mCurConnects.get(account);
		} else {// 打开一个新连接
			try {
				final Connection conn = this.getConnection();
				mCurConnects.put(account, conn);
				LogUtil.info(account + "新开一个数据库连接,连接池大小=" + mConnectPool.size());
				return conn;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 数据库关闭一个现有的连接
	 * 
	 * @param conn
	 */
	public void close(Connection conn) {
		if (null == conn)
			throw new NullPointerException("conncation is null");
		try {
			mCurConnects.remove(conn);
			conn.close();
			conn = null;
			LogUtil.info(TAG, "已关闭一个数据库连接:)");
		} catch (SQLException e) {
			e.printStackTrace();
			LogUtil.warning(TAG, "关闭数据库连接发生错误!");
		}
	}

	public void close(String account) {
		Connection conn = mCurConnects.remove(account);
		if (null != conn) {
			try {
				conn.close();
				conn = null;
				LogUtil.info(TAG, account + "已关闭一个数据库连接:)");
			} catch (SQLException e) {
				e.printStackTrace();
				LogUtil.warning(TAG, "关闭数据库连接发生错误!");
			}
		} else {
			LogUtil.info(TAG, account + "没有打开数据库连接:)");
		}
	}

	/**
	 * 关闭所有连接
	 */
	public void close() {
		Collection<Connection> conns = mCurConnects.values();
		for (Connection conn : conns) {
			try {
				conn.close();
				conn = null;
				LogUtil.info(TAG, "已关闭一个数据库连接:)");
			} catch (SQLException e) {
				e.printStackTrace();
				LogUtil.warning(TAG, "关闭数据库连接发生错误!");
			}
		}
		mCurConnects.clear();
	}

	@Override
	public PrintWriter getLogWriter() throws SQLException {
		return null;
	}

	@Override
	public int getLoginTimeout() throws SQLException {
		return 0;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		return null;
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {

	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {

	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return false;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		return null;
	}

	@Override
	public Connection getConnection() throws SQLException {
		// 如果数据库连接池中的连接对象的个数大于0
		if (mConnectPool.size() > 0) {
			final Connection conn = mConnectPool.removeFirst();
			// LogUtil.info(TAG, "打开一个数据库连接,当前连接池大小=" + mConnectPool.size());
			// 返回Connection对象的代理对象
			return (Connection) Proxy.newProxyInstance(DatabaseManager.class
					.getClassLoader(), conn.getClass().getInterfaces(),
					new InvocationHandler() {
						@Override
						public Object invoke(Object proxy, Method method,
								Object[] args) throws Throwable {
							if (!method.getName().equals("close")) {
								return method.invoke(conn, args);
							} else {
								// 如果调用的是Connection对象的close方法，就把conn还给数据库连接池
								mConnectPool.add(conn);
								LogUtil.info(TAG, conn + "被还给数据库连接池了!当前连接池大小="
										+ mConnectPool.size());
								return null;
							}
						}
					});
		} else {
			LogUtil.warning(TAG, "数据库正忙!");
		}
		return null;
	}

	@Override
	public Connection getConnection(String username, String password)
			throws SQLException {
		return null;
	}

}
