package com.mele.dao;

import java.util.List;

import com.mele.dao.entity.Store;
import com.mele.dao.util.ContentValues;

/**
 * 定义'storeInfo'数据访问接口模型
 * 
 * @author Jinhu
 * @date 2016/6/10
 */
public interface IStoreDao {

	/**
	 * 添加商家
	 * 
	 * @param store
	 * @return
	 * @throws Exception
	 */
	public boolean addStore(Store store) throws Exception;

	/**
	 * 添加商家列表
	 * 
	 * @param stores
	 * @return
	 * @throws Exception
	 */
	public boolean addStores(List<Store> stores) throws Exception;

	/**
	 * 根据商家Id删除
	 * 
	 * @param storeId
	 * @return
	 * @throws Exception
	 */
	public boolean deleteStore(long storeId) throws Exception;

	/**
	 * 删除某城市所有商家
	 * 
	 * @param cityCode
	 * @return
	 * @throws Exception
	 */
	public boolean deleteStoreByCityCode(String cityCode) throws Exception;

	/**
	 * 删除所有商家
	 * 
	 * @return
	 */
	public boolean deleteAllStores();

	/**
	 * 修改商家名
	 * 
	 * @param storeId
	 * @param storeName
	 * @return
	 * @throws Exception
	 */
	public int updateStoreName(long storeId, String storeName) throws Exception;

	/**
	 * 修改商家Logo
	 * 
	 * @param storeId
	 * @param storeLogo
	 * @return
	 * @throws Exception
	 */
	public int updateStoreLogo(long storeId, String storeLogo) throws Exception;

	/**
	 * 修改商家营业时间
	 * 
	 * @param storeId
	 * @param shopHours
	 * @return
	 * @throws Exception
	 */
	public int updateStoreShopHours(long storeId, String shopHours)
			throws Exception;

	/**
	 * 修改商家起送价或配送费
	 * 
	 * @param storeId
	 * @param startPrice
	 * @param transportpricePrice
	 * @return
	 */
	public int updateStorePrice(long storeId, float startPrice,
			float transportpricePrice);

	/**
	 * 修改商家公告
	 * 
	 * @param storeId
	 * @param notice
	 * @param cheapenNotice
	 * @param newUserNotice
	 * @return
	 */
	public int updateStoreNotice(long storeId, String notice,
			String cheapenNotice, String newUserNotice);

	/**
	 * 修改商家电话
	 * 
	 * @param storeId
	 * @param tel
	 * @return
	 * @throws Exception
	 */
	public int updateStoreTel(long storeId, String tel) throws Exception;

	/**
	 * 修改商家信息
	 * 
	 * @param storeId
	 * @param values
	 * @return
	 */
	public int updateStoreValues(long storeId, ContentValues values);

	/**
	 * 获取所有商家
	 * 
	 * @return
	 */
	public List<Store> fetchAllStores();

	/**
	 * 获取某城市所有商家
	 * 
	 * @param cityCode
	 * @return
	 * @throws Exception
	 */
	public List<Store> fetchAllStoresByCityCode(String cityCode)
			throws Exception;

	/**
	 * 根据起送价获取所有商家
	 * 
	 * @param minPrice
	 * @param maxPrice
	 * @return
	 * @throws Exception
	 */
	public List<Store> fetchAllStoresByStartPrice(float minPrice, float maxPrice)
			throws Exception;

	/**
	 * 根据商家类型Id获取所有商家
	 * 
	 * @param storeTypeId
	 * @return
	 * @throws Exception
	 */
	public List<Store> fetchAllStoresByTypeId(int storeTypeId) throws Exception;

	/**
	 * 根据商家Id获取商家
	 * 
	 * @param storeId
	 * @return
	 */
	public Store fetchStoreByStoreId(long storeId);

	/**
	 * 根据商家类型Id获取指定城市所有商家
	 * 
	 * @param storeTypeId
	 * @return
	 * @throws Exception
	 */
	public List<Store> fetchAllStoresByCityCodeNTypeId(String cityCode,
			int storeTypeId) throws Exception;

	/**
	 * 根据起送价获取指定城市所有商家
	 * 
	 * @param minPrice
	 * @param maxPrice
	 * @return
	 * @throws Exception
	 */
	public List<Store> fetchAllStoresByCityCodeNStartPrice(String cityCode,
			float minPrice, float maxPrice) throws Exception;

}
