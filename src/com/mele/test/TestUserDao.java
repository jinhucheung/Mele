package com.mele.test;

import java.sql.Connection;
import java.util.List;

import com.mele.control.DatabaseManager;
import com.mele.dao.IUserSchema;
import com.mele.dao.entity.User;
import com.mele.dao.impl.UserDao;

/**
 * 测试UserDao
 * 
 * @author Jinhu
 * @date 2016/6/12
 */
public class TestUserDao {
	public static void main(String[] args) throws Exception {
		Connection mCon = DatabaseManager.getInstance().open("HiKu");
		Connection mCon1 = DatabaseManager.getInstance().open("HiKu");
		Connection mCon2 = DatabaseManager.getInstance().open("HiKu");
		Connection mCon3 = DatabaseManager.getInstance().open("Bao");
		UserDao mUserDao1 = new UserDao(mCon1);
		System.out.println(mUserDao1.fetchAllUser());
		Thread.sleep(500);

		// 显示表级结构
		System.out
				.println("\n--------------------显示表级结构--------------------------");
		System.out.println(IUserSchema.USER_TABLE_CREATE);
		System.out
				.println("-----------------------------------------------------\n");

		UserDao mUserDao = new UserDao(mCon);
		// 获取所有用户信息
		System.out
				.println("\n--------------------获取所有用户信息--------------------------");
		List<User> mUsers = mUserDao.fetchAllUser();
		for (User user : mUsers) {
			System.out.println(user);
		}
		System.out
				.println("-----------------------------------------------------\n");

		// 获取指定商家的用户信息
		System.out
				.println("\n--------------------获取指定商家的用户信息--------------------------");
		User user = mUserDao.fetchUserByStoreId(3);
		System.out.println(user);
		System.out
				.println("-----------------------------------------------------\n");

		// 获取指定用户账户的用户信息
		System.out
				.println("\n--------------------获取指定用户账户的用户信息--------------------------");
		user = mUserDao.fetchUser("Jack");
		System.out.println(user);
		System.out
				.println("-----------------------------------------------------\n");

		// 获取指定级别的商家用户信息
		System.out
				.println("\n--------------------获取指定级别的商家用户信息--------------------------");
		mUsers = mUserDao.fetchAullUserByLevel(2);
		for (User user1 : mUsers) {
			System.out.println(user1);
		}
		System.out
				.println("-----------------------------------------------------\n");

		// 添加商家用户信息
		System.out
				.println("\n--------------------添加商家用户信息--------------------------");
		User addUser = new User();
		addUser.setAccount("addUser");
		addUser.setLevel(1);
		addUser.setTel("18813973777");
		addUser.setEmail("303340824@qq.com");
		addUser.setPassword("123456addUser");
		mUserDao.addUser(addUser);
		System.out.println(mUserDao.fetchUser("addUser"));
		System.out
				.println("-----------------------------------------------------\n");

		// 修改用户等级
		System.out
				.println("\n--------------------修改用户等级--------------------------");
		mUserDao.updateUserLevel("addUser", 2);
		System.out.println(mUserDao.fetchUser("addUser"));
		System.out
				.println("-----------------------------------------------------\n");

		mUserDao.deleteUser("addUser");

		// mUserDao.deleteAllUser();

		Thread.sleep(500);
		DatabaseManager.getInstance().close("HiKu");
		DatabaseManager.getInstance().close("HiKu");
		DatabaseManager.getInstance().close();
		System.exit(0);
	}
}
