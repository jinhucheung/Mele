package com.mele.dao.entity;

import com.mele.util.TextUtils;

/**
 * 用户收货地址信息
 * 
 * @author Jinhu
 * @date 2016/6/12
 */
public class UserAddress {
	/** 收货地址Id。 */
	private int addressId;
	/** 用户账户。 */
	private String userAccount;
	/** 联系人 。 */
	private String userContact;
	/** 联系人性别。 */
	private int userSex;
	/** 联系人电话。 */
	private String userTel;
	/** 地址标签。 */
	private String userTag;
	/** 联系人地址 。 */
	private String userAddress;

	public UserAddress() {
		this(0, "", "", 1, "", "", "");
	}

	public UserAddress(int addressId, String userAccount, String userContact,
			int userSex, String userTel, String userTag, String userAddress) {
		this.addressId = addressId;
		this.userAccount = userAccount;
		this.userContact = userContact;
		this.userSex = userSex;
		this.userTel = userTel;
		this.userTag = userTag;
		this.userAddress = userAddress;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getUserContact() {
		return userContact;
	}

	public void setUserContact(String userContact) {
		this.userContact = userContact;
	}

	public int getUserSex() {
		return userSex;
	}

	public void setUserSex(int userSex) {
		this.userSex = userSex;
	}

	public String getUserTel() {
		return userTel;
	}

	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}

	public String getUserTag() {
		return userTag;
	}

	public void setUserTag(String userTag) {
		this.userTag = userTag;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	@Override
	public String toString() {
		return " addressId : " + addressId + //
				" userAccount : " + userAccount + //
				" userContact : " + userContact + //
				" userSex : " + userSex + //
				" userTel : " + userTel + //
				" userTag : " + userTag + //
				" userAddress: " + userAddress;
	}

	@Override
	public boolean equals(Object obj) {
		if (null == obj)
			return false;
		if (this == obj)
			return true;
		if (obj instanceof UserAddress) {
			UserAddress another = (UserAddress) obj;
			return addressId == (another.addressId)
					&& userAccount.equals(another.userAccount);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	public boolean isNullValues() {
		return addressId <= 0 || TextUtils.isEmpty(userAccount);
	}
}
