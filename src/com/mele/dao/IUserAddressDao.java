package com.mele.dao;

import java.util.List;

import com.mele.dao.entity.UserAddress;
import com.mele.dao.util.ContentValues;

/**
 * 定义'uaddress'数据访问接口模型
 * 
 * @author Jinhu
 * @date 2016/6/12
 */
public interface IUserAddressDao {

	/**
	 * 添加用户收货地址信息
	 * 
	 * @param userAddress
	 * @return
	 * @throws Exception 
	 */
	public boolean addUserAddress(UserAddress userAddress) throws Exception;

	/**
	 * 添加用户地址列表
	 * 
	 * @param userAddressList
	 * @return
	 * @throws Exception 
	 */
	public boolean addUserAddressList(List<UserAddress> userAddressList) throws Exception;

	/**
	 * 删除用户地址信息
	 * 
	 * @param account
	 * @param addressId
	 * @return
	 * @throws Exception 
	 */
	public boolean deleteUserAddress(String account, int addressId) throws Exception;

	/**
	 * 删除所有用户地址信息
	 * 
	 * @return
	 */
	public boolean deleteAllUserAddress();

	/**
	 * 修改用户地址信息
	 * 
	 * @param account
	 * @param addressId
	 * @param values
	 * @return
	 * @throws Exception 
	 */
	public int updateUserAddressValues(String account, int addressId,
			ContentValues values) throws Exception;

	/**
	 * 修改用户地址联系人
	 * 
	 * @param account
	 * @param addressId
	 * @param contact
	 * @return
	 * @throws Exception 
	 */
	public int updateUserAddressInContact(String account, int addressId,
			String contact) throws Exception;

	/**
	 * 修改联系人性别
	 * 
	 * @param account
	 * @param addressId
	 * @param sex
	 * @return
	 * @throws Exception 
	 */
	public int updateUserAddressInSex(String account, int addressId, int sex) throws Exception;

	/**
	 * 修改联系人电话
	 * 
	 * @param account
	 * @param addressId
	 * @param tel
	 * @return
	 * @throws Exception 
	 */
	public int updateUserAddressInTel(String account, int addressId, String tel) throws Exception;

	/**
	 * 修改地址标签
	 * 
	 * @param account
	 * @param addressId
	 * @param tag
	 * @return
	 * @throws Exception 
	 */
	public int updateUserAddressInTag(String account, int addressId, String tag) throws Exception;

	/**
	 * 修改联系人地址
	 * 
	 * @param account
	 * @param addressId
	 * @param address
	 * @return
	 * @throws Exception 
	 */
	public int updateUserAddressInAddress(String account, int addressId,
			String address) throws Exception;

	/**
	 * 获取所有用户地址信息
	 * 
	 * @return
	 */
	public List<UserAddress> fetchAllUserAddress();

	/**
	 * 根据账号信息获取所有用户地址信息
	 * 
	 * @param account
	 * @return
	 * @throws Exception 
	 */
	public List<UserAddress> fetchAllUserAddressByAccount(String account) throws Exception;

	/**
	 * 根据账号信息及地址标签获取所有用户地址信息
	 * 
	 * @param account
	 * @param tag
	 * @return
	 * @throws Exception 
	 */
	public List<UserAddress> fetchAllUserAddressByAccountNTag(String account,
			String tag) throws Exception;

	/**
	 * 获取指定账号及地址Id的地址信息
	 * 
	 * @param account
	 * @param addressId
	 * @return
	 * @throws Exception 
	 */
	public UserAddress fetchUserAddress(String account, int addressId) throws Exception;
}
