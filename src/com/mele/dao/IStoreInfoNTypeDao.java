package com.mele.dao;

import java.util.List;

import com.mele.dao.entity.StoreInfoNType;

/**
 * 定义'StoreInfoType'数据访问接口模型
 * 
 * @author Jinhu
 * @date 2016/6/11
 * 
 */
public interface IStoreInfoNTypeDao {

	/**
	 * 添加商家信息与类型联系
	 * 
	 * @param store
	 * @return
	 * @throws Exception 
	 */
	public boolean addStoreInfoNType(StoreInfoNType storeInfoNType) throws Exception;

	/**
	 * 删除商家信息与类型联系
	 * 
	 * @param storeId
	 * @return
	 * @throws Exception 
	 */
	public boolean deleteStoreInfoNType(StoreInfoNType storeInfoNType) throws Exception;

	/**
	 * 删除所有商家信息与类型联系
	 * 
	 * @return
	 */
	public boolean deleteAllStoreInfoNType();

	/**
	 * 根据商家类型Id获取所有商家信息与类型联系
	 * 
	 * @param storeTypeId
	 * @return
	 * @throws Exception 
	 */
	public List<StoreInfoNType> fetchAllStoreInfoNTypesByTypeId(int storeTypeId) throws Exception;

	/**
	 * 根据商家Id获取所有商家信息与类型联系
	 * 
	 * @param storeId
	 * @return
	 * @throws Exception 
	 */
	public List<StoreInfoNType> fetchAllStoreInfoNTypesByStoreId(long storeId) throws Exception;

	/**
	 * 获取所有商家信息与类型联系
	 * 
	 * @return
	 */
	public List<StoreInfoNType> fetchAllStoreInfoNTypes();

}
