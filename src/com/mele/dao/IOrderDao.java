package com.mele.dao;

import java.util.List;

import com.mele.dao.entity.Order;

/**
 * 定义'uoder'数据访问接口模型
 * 
 * @author Jinhu
 * @date 2016/6/12
 */
public interface IOrderDao {

	/**
	 * 添加订单
	 * 
	 * @param order
	 * @return
	 * @throws Exception 
	 */
	public boolean addOrder(Order order) throws Exception;

	/**
	 * 根据订单编码删除订单
	 * 
	 * @param orderCode
	 * @return
	 * @throws Exception 
	 */
	public boolean deleteOrder(String orderCode) throws Exception;

	/**
	 * 删除用户账户所有订单
	 * 
	 * @param account
	 * @return
	 * @throws Exception 
	 */
	public boolean deleteAllOrdersInAccount(String account) throws Exception;

	/**
	 * 删除商家所有订单
	 * 
	 * @param storeId
	 * @return
	 * @throws Exception 
	 */
	public boolean deleteAllOrdersInStore(long storeId) throws Exception;

	/**
	 * 修改订单评论
	 * 
	 * @param foodIds
	 * @return
	 * @throws Exception 
	 */
	public int updateOrderComment(String orderCode, String comment) throws Exception;

	/**
	 * 获取指定订单编号的订单信息
	 * 
	 * @param orderCode
	 * @return
	 */
	public Order fetchOrder(String orderCode);

	/**
	 * 获取指定用户的所有订单信息
	 * 
	 * @param account
	 * @return
	 * @throws Exception 
	 */
	public List<Order> fetchAllOrderInAccount(String account) throws Exception;

	/**
	 * 获取指定商家的所有订单信息
	 * 
	 * @param storeId
	 * @return
	 */
	public List<Order> fetchAllOrderInStore(long storeId);

	/**
	 * 获取所有订单信息
	 * 
	 * @return
	 */
	public List<Order> fetchAllOrder();
}
