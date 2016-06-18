package com.mele.dao;

import java.util.List;

import com.mele.dao.entity.StoreType;

/**
 * 定义'storeType'数据访问接口模型
 * 
 * @author Jinhu
 * @date 2016/6/11
 */
public interface IStoreTypeDao {

	/**
	 * 添加商家类型
	 * 
	 * @param storeInfo
	 * @return
	 * @throws Exception 
	 */
	public boolean addStoreType(StoreType store) throws Exception;

	/**
	 * 添加商家类型列表
	 * 
	 * @param storeInfos
	 * @return
	 * @throws Exception 
	 */
	public boolean addStoreTypes(List<StoreType> storeTypes) throws Exception;

	/**
	 * 根据类型Id删除
	 * 
	 * @param storeId
	 * @return
	 * @throws Exception 
	 */
	public boolean deleteStoreType(int storeTypeId) throws Exception;

	/**
	 * 删除所有商家类型
	 * 
	 * @return
	 */
	public boolean deleteAllStoreTypes();

	/**
	 * 修改商家类型名
	 * 
	 * @param storeId
	 * @param storeTypeName
	 * @return
	 * @throws Exception 
	 */
	public int updateStoreTypeName(int storeTypeId, String storeTypeName) throws Exception;

	/**
	 * 获取所有商家类型
	 * 
	 * @return
	 */
	public List<StoreType> fetchAllStoreTypes();

	/**
	 * 根据类型ID获取指定商家类型
	 * 
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	public StoreType fetchStoreTypeById(int id) throws Exception;

	/**
	 * 根据父级类型Id获取指定商家类型集
	 * 
	 * @param superId
	 * @return
	 * @throws Exception 
	 */
	public List<StoreType> fetchStoreTypeBySuperId(int superId) throws Exception;
	

}
