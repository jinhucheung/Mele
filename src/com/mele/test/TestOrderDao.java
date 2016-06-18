package com.mele.test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.mele.control.DatabaseManager;
import com.mele.dao.IOrderSchema;
import com.mele.dao.entity.Order;
import com.mele.dao.impl.OrderDao;

/**
 * 测试OrderDao
 * 
 * @author Jinhu
 * @date 2016/6/12
 */
public class TestOrderDao {
	public static void main(String[] args) throws Exception {
		Connection mCon = DatabaseManager.getInstance().open();
		Thread.sleep(500);

		// 显示表级结构
		System.out
				.println("\n--------------------显示表级结构--------------------------");
		System.out.println(IOrderSchema.ORDER_TABLE_CREATE);
		System.out
				.println("-----------------------------------------------------\n");

		OrderDao mOrderDao = new OrderDao(mCon);
		// 获取所有订单信息
		System.out
				.println("\n-------------------- 获取所有订单信息--------------------------");
		List<Order> mOrders = mOrderDao.fetchAllOrder();
		for (Order order : mOrders) {
			System.out.println(order);
		}
		System.out
				.println("-----------------------------------------------------\n");

		// 获取指定用户的所有订单信息
		System.out
				.println("\n--------------------  获取指定用户的所有订单信息--------------------------");
		mOrders = mOrderDao.fetchAllOrderInAccount("HelloWorld");
		for (Order order : mOrders) {
			System.out.println(order);
		}
		System.out
				.println("-----------------------------------------------------\n");

		// 获取指定商家的所有订单信息
		System.out
				.println("\n--------------------  获取指定商家的所有订单信息--------------------------");
		mOrders = mOrderDao.fetchAllOrderInStore(1);
		for (Order order : mOrders) {
			System.out.println(order);
		}
		System.out
				.println("-----------------------------------------------------\n");

		// 获取指定订单信息
		System.out
				.println("\n--------------------  获取指定订单信息--------------------------");
		System.out.println(mOrderDao.fetchOrder("2016060716301122"));
		System.out
				.println("-----------------------------------------------------\n");

		// 添加订单
		System.out
				.println("\n--------------------添加订单--------------------------");
		Order order = new Order();
		order.setUserAccount("HiKu");
		order.setOrderCode("2016061216531212");
		order.setOrderTime(new Timestamp(System.currentTimeMillis()));
		order.setStoreId(2);
		order.setUserAddressId(2);
		mOrderDao.addOrder(order);
		System.out.println(mOrderDao.fetchOrder("2016061216531212"));
		System.out
				.println("-----------------------------------------------------\n");

		// 添加评论信息
		System.out
				.println("\n--------------------添加评论信息--------------------------");
		mOrderDao.updateOrderComment("2016061216531212", "差评!");
		System.out.println(mOrderDao.fetchOrder("2016061216531212"));
		System.out
				.println("-----------------------------------------------------\n");

		mOrderDao.deleteOrder("2016061216531212");

		Thread.sleep(500);
		DatabaseManager.getInstance().close();
		System.exit(0);
	}
}
