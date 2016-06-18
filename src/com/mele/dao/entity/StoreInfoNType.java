package com.mele.dao.entity;

/**
 * 联系商家信息表与商家类型表
 * 
 * @author Jinhu
 * @date 2016/6/11
 */
public class StoreInfoNType {

	/** 商家编号 。 */
	private long storeId;
	/** 商家类型编号 。 */
	private int stypeId;

	public StoreInfoNType() {
		this(0, 0);
	}

	public StoreInfoNType(long storeId, int stypeId) {
		this.storeId = storeId;
		this.stypeId = stypeId;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public int getStypeId() {
		return stypeId;
	}

	public void setStypeId(int stypeId) {
		this.stypeId = stypeId;
	}

	@Override
	public String toString() {
		return "storeId : " + storeId + " stypeId : " + stypeId;
	}

	@Override
	public boolean equals(Object obj) {
		if (null == obj)
			return false;
		if (this == obj)
			return true;
		if (obj instanceof StoreInfoNType) {
			StoreInfoNType another = (StoreInfoNType) obj;
			return storeId == another.storeId && stypeId == another.stypeId;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	public boolean isNullValue() {
		return storeId <= 0 || stypeId <= 0;
	}
}
