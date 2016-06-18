package com.mele.dao;

import java.util.List;

import com.mele.dao.entity.User;
import com.mele.dao.util.ContentValues;

/**
 * 定义'user'数据访问接口模型
 * 
 * @author Jinhu
 * @date 2016/6/12
 */
public interface IUserDao {

	/**
	 * 添加用户信息
	 * 
	 * @param user
	 * @return
	 */
	public boolean addUser(User user);

	/**
	 * 添加一组用户信息
	 * 
	 * @param users
	 * @return
	 * @throws Exception 
	 */
	public boolean addUsers(List<User> users) throws Exception;

	/**
	 * 根据用户账户删除用户信息
	 * 
	 * @param account
	 * @return
	 * @throws Exception 
	 */
	public boolean deleteUser(String account) throws Exception;

	/**
	 * 删除所有用户
	 * 
	 * @return
	 */
	public boolean deleteAllUser();

	/**
	 * 修改用户电话
	 * 
	 * @param account
	 * @param tel
	 * @return
	 * @throws Exception 
	 */
	public int updateUserTel(String account, String tel) throws Exception;

	/**
	 * 修改用户头像
	 * 
	 * @param account
	 * @param headIc
	 * @return
	 * @throws Exception 
	 */
	public int updateUserHeadIc(String account, String headIc) throws Exception;

	/**
	 * 修改用户级别
	 * 
	 * @param account
	 * @param level
	 * @return
	 * @throws Exception 
	 */
	public int updateUserLevel(String account, int level) throws Exception;

	/**
	 * 修改用户级别
	 * 
	 * @param account
	 * @param values
	 * @return
	 * @throws Exception 
	 */
	public int updateUserValues(String account, ContentValues values) throws Exception;

	/**
	 * 获取所有用户信息
	 * 
	 * @return
	 */
	public List<User> fetchAllUser();

	/**
	 * 获取指定账户的用户信息
	 * 
	 * @param account
	 * @return
	 */
	public User fetchUser(String account);

	/**
	 * 获取指定等级的用户信息
	 * 
	 * @param level
	 * @return
	 * @throws Exception 
	 */
	public List<User> fetchAullUserByLevel(int level) throws Exception;

	/**
	 * 获取指定商店的用户信息
	 * 
	 * @param storeId
	 * @return
	 * @throws Exception 
	 */
	public User fetchUserByStoreId(long storeId) throws Exception;
}
