package com.mele.dao.entity;

/**
 * 商家信息
 * 
 * @author Jinhu
 * @date 2016/6/10
 */
public class Store {
	/** 商家编码 。 */
	private long storeId;

	/** 商家名 。 */
	private String storeName;

	/** 商家Logo 。 */
	private String storeLogo;

	/** 联系电话 。 */
	private String tel;

	/** 所在城市编码 。 */
	private String cityCode;

	/** 营业时间 。 */
	private String shopHours;

	/** 商家所在经纬度 。 */
	private String latlng;

	/** 起送价 。 */
	private float startPrice;

	/** 配送费 。 */
	private float transportPrice;

	/** 通用公告 。 */
	private String notice;

	/** 减价公告 。 */
	private String cheapenNotice;

	/** 新用户公告 。 */
	private String newuserNotice;

	/** 商家类型 。 */
	private String storeType;

	/** 商家账户 。 */
	private String uAccount;

	/** 商家地址 。 */
	private String address;

	/** 商家订单总数 。 */
	private int orderNums;

	public Store() {
		this(0, "", "", "", "", "", "", 0, 0, "", "", "", "", "", "");
	}

	public Store(long storeId, String storeName, String storeLogo, String tel,
			String cityCode, String shopHours, String latlng, float startPrice,
			float transportPrice, String notice, String cheapenNotice,
			String newuserNotice, String storeType, String uAccount,
			String address) {
		this.storeId = storeId;
		this.storeName = storeName;
		this.storeLogo = storeLogo;
		this.tel = tel;
		this.cityCode = cityCode;
		this.shopHours = shopHours;
		this.latlng = latlng;
		this.startPrice = startPrice;
		this.transportPrice = transportPrice;
		this.notice = notice;
		this.cheapenNotice = cheapenNotice;
		this.newuserNotice = newuserNotice;
		this.storeType = storeType;
		this.uAccount = uAccount;
		this.address = address;
		this.orderNums = 0;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreLogo() {
		return storeLogo;
	}

	public void setStoreLogo(String storeLogo) {
		this.storeLogo = storeLogo;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getShopHours() {
		return shopHours;
	}

	public void setShopHours(String shopHours) {
		this.shopHours = shopHours;
	}

	public String getLatlng() {
		return latlng;
	}

	public void setLatlng(String latlng) {
		this.latlng = latlng;
	}

	public float getStartPrice() {
		return startPrice;
	}

	public void setStartPrice(float startPrice) {
		this.startPrice = startPrice;
	}

	public float getTransportPrice() {
		return transportPrice;
	}

	public void setTransportPrice(float transportPrice) {
		this.transportPrice = transportPrice;
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	public String getCheapenNotice() {
		return cheapenNotice;
	}

	public void setCheapenNotice(String cheapenNotice) {
		this.cheapenNotice = cheapenNotice;
	}

	public String getNewuserNotice() {
		return newuserNotice;
	}

	public void setNewuserNotice(String newuserNotice) {
		this.newuserNotice = newuserNotice;
	}

	public String getStoreType() {
		return storeType;
	}

	public void setStoreType(String storeType) {
		this.storeType = storeType;
	}

	public String getAccount() {
		return uAccount;
	}

	public void setAccount(String uAccount) {
		this.uAccount = uAccount;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getOrderNums() {
		return orderNums;
	}

	public void setOrderNums(int orderNums) {
		this.orderNums = orderNums;
	}

	@Override
	public String toString() {
		String info = //
		" StoreId: " + storeId + //
				" StoreName: " + storeName + //
				" StoreType: " + storeType + //
				" tel: " + tel + //
				" storeLogo: " + storeLogo + //
				" \n" + //
				" cityCode: " + cityCode + //
				" latlng: " + latlng + //
				" \n" + //
				" shopHours: " + shopHours + //
				" startPrice: " + startPrice + //
				" transportPrice: " + transportPrice + //
				" \n" + //
				" notice: " + notice + //
				" cheapenNotice: " + cheapenNotice + //
				" newuserNotice: " + newuserNotice + //
				" \n" + //
				" uAccount: " + uAccount + //
				" address: " + address + //
				" orderNums: " + orderNums;
		return info;
	}

	@Override
	public boolean equals(Object obj) {
		if (null == obj)
			return false;
		if (this == obj)
			return true;
		if (obj instanceof Store) {
			Store another = (Store) obj;
			return storeId == (another.storeId);
		}
		return false;
	}

	@Override
	public int hashCode() {
		if (0 == storeId) {
			return super.hashCode();
		}
		return super.hashCode() * (int) storeId;
	}

	public boolean isNullValue() {
		return storeId <= 0;
	}
}
