package com.mele.test;

import java.sql.Connection;
import java.util.List;

import com.mele.control.DatabaseManager;
import com.mele.dao.IUserAddressSchema;
import com.mele.dao.entity.UserAddress;
import com.mele.dao.impl.UserAddressDao;

/**
 * 测试UserAddressDao
 * 
 * @author Jinhu
 * @date 2016/6/12
 */
public class TestUserAddressDao {
	public static void main(String[] args) throws Exception {
		Connection mCon = DatabaseManager.getInstance().open();
		Thread.sleep(500);

		// 显示表级结构
		System.out
				.println("\n--------------------显示表级结构--------------------------");
		System.out.println(IUserAddressSchema.USER_ADDRESS_TABLE_CREATE);
		System.out
				.println("-----------------------------------------------------\n");

		UserAddressDao mUserAddressDao = new UserAddressDao(mCon);

		// 获取所有用户地址信息
		System.out
				.println("\n--------------------获取所有用户地址信息--------------------------");
		List<UserAddress> userAddressList = mUserAddressDao
				.fetchAllUserAddress();
		for (UserAddress userAddress : userAddressList) {
			System.out.println(userAddress);
		}
		System.out
				.println("-----------------------------------------------------\n");

		// 根据账号信息获取所有用户地址信息
		System.out
				.println("\n--------------------根据账号信息获取所有用户地址信息--------------------------");
		userAddressList = mUserAddressDao.fetchAllUserAddressByAccount("HiKu");
		for (UserAddress userAddress : userAddressList) {
			System.out.println(userAddress);
		}
		System.out
				.println("-----------------------------------------------------\n");

		// 根据账号信息及地址标签获取所有用户地址信息
		System.out
				.println("\n--------------------根据账号信息及地址标签获取所有用户地址信息--------------------------");
		userAddressList = mUserAddressDao.fetchAllUserAddressByAccountNTag(
				"HiKu", "宿舍");
		for (UserAddress userAddress : userAddressList) {
			System.out.println(userAddress);
		}
		System.out
				.println("-----------------------------------------------------\n");

		// 获取指定账号及地址Id的地址信息
		System.out
				.println("\n--------------------获取指定账号及地址Id的地址信息--------------------------");
		System.out.println(mUserAddressDao.fetchUserAddress("Bao", 1));
		System.out
				.println("-----------------------------------------------------\n");

		// 添加用户收货地址信息
		System.out
				.println("\n--------------------添加用户收货地址信息--------------------------");
		userAddressList = mUserAddressDao.fetchAllUserAddressByAccount("HiEle");
		if (null != userAddressList) {
			UserAddress newUserAddress = new UserAddress();
			newUserAddress.setAddressId(userAddressList.size() + 1);
			newUserAddress.setUserAccount("HiEle");
			newUserAddress.setUserAddress("梅州市梅江区江边路2号");
			newUserAddress.setUserContact("李小明");
			newUserAddress.setUserSex(1);
			newUserAddress.setUserTag("家里");
			newUserAddress.setUserTel("18819973772");
			mUserAddressDao.addUserAddress(newUserAddress);

			System.out.println(mUserAddressDao.fetchUserAddress("HiEle",
					userAddressList.size() + 1));
		}
		System.out
				.println("-----------------------------------------------------\n");

		// 删除指定数据
		System.out
				.println("\n--------------------删除指定数据--------------------------");
		mUserAddressDao.deleteUserAddress("HiEle", userAddressList.size() + 1);
		UserAddress userAddress = mUserAddressDao.fetchUserAddress("HiEle",
				userAddressList.size() + 1);
		System.out.println(null == userAddress ? "没有查找到相关数据" : userAddress);
		System.out
				.println("-----------------------------------------------------\n");

		// mUserAddressDao.deleteAllUserAddress();

		Thread.sleep(500);
		DatabaseManager.getInstance().close();
		System.exit(0);
	}
}
