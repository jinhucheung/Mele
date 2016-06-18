package com.mele.dao.entity;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.mele.util.TextUtils;

/**
 * 订单信息
 * 
 * @author Jinhu
 * @date 2016/6/12
 */
public class Order {
	/** 用户账户 。 */
	private String userAccount;

	/** 订单编码(16位) 。 */
	private String orderCode;

	/** 商家Id 。 */
	private long storeId;

	/** 用户选择的收货地址Id 。 */
	private int userAddressId;

	/** 商家菜式Id串(以;分隔) 。 */
	private String orderFoodIds;

	/** 商家菜式价格及数量串(相对与Id串,格式:价格X数量) 。 */
	private String orderFoodPriceXNum;

	/** 用户下单备注信息 。 */
	private String userNote;

	/** 订单生成时间 。 */
	private Timestamp orderTime;

	/** 用户评价 。 */
	private String comment;

	/** 商家菜式 。 */
	private List<Food> mFoods;

	/** 商家信息 . */
	private Store mStore;

	public Order() {
		this("", "", 0, 0, "", "", "", null, "");
	}

	public Order(String userAccount, String orderCode, long storeId,
			int userAddressId, String orderFoodId, String orderFoodPriceXNum,
			String userNote, Timestamp orderTime, String comment) {
		this.userAccount = userAccount;
		this.orderCode = orderCode;
		this.storeId = storeId;
		this.userAddressId = userAddressId;
		this.orderFoodIds = orderFoodId;
		this.orderFoodPriceXNum = orderFoodPriceXNum;
		this.userNote = userNote;
		this.orderTime = orderTime;
		this.comment = comment;
		this.mFoods = new ArrayList<Food>();
		this.mStore = new Store();
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public int getUserAddressId() {
		return userAddressId;
	}

	public void setUserAddressId(int userAddressId) {
		this.userAddressId = userAddressId;
	}

	public String getOrderFoodIds() {
		return orderFoodIds;
	}

	public void setOrderFoodIds(String orderFoodIds) {
		this.orderFoodIds = orderFoodIds;
	}

	public String getOrderFoodNumNPrice() {
		return orderFoodPriceXNum;
	}

	public void setOrderFoodNumNPrice(String orderFoodNumNPrice) {
		this.orderFoodPriceXNum = orderFoodNumNPrice;
	}

	public String getUserNote() {
		return userNote;
	}

	public void setUserNote(String userNote) {
		this.userNote = userNote;
	}

	public Timestamp getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Timestamp orderTime) {
		this.orderTime = orderTime;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public List<Food> getFoods() {
		return mFoods;
	}

	public void setFoods(List<Food> mFoods) {
		this.mFoods = mFoods;
	}

	public Store getStore() {
		return mStore;
	}

	public void setStore(Store mStore) {
		this.mStore = mStore;
	}

	@Override
	public String toString() {
		final SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		return " orderCode: " + orderCode + //
				" userAccount: " + userAccount + //
				" storeId: " + storeId + //
				" store: " + mStore + //
				" userAddressId: " + userAddressId + //
				" orderFoodId: " + orderFoodIds + //
				" orderFoods: " + mFoods + //
				" orderFoodPriceXNum: " + orderFoodPriceXNum + //
				" userNote: " + userNote + //
				" orderDate: " + dateFormat.format(orderTime) + //
				" comment: " + comment;
	}

	@Override
	public boolean equals(Object obj) {
		if (null == obj)
			return false;
		if (this == obj)
			return true;
		if (obj instanceof Order) {
			Order another = (Order) obj;
			return orderCode.equals(another.orderCode);
		}
		return false;
	}

	@Override
	public int hashCode() {
		if (TextUtils.isEmpty(orderCode)) {
			return super.hashCode();
		}
		return super.hashCode() + orderCode.hashCode();
	}

	//-------------------------辅助方法------------------------
	/**
	 * 判断空值
	 * @return
	 */
	public boolean isNullValue() {
		return TextUtils.isEmpty(orderCode);
	}
	
}
