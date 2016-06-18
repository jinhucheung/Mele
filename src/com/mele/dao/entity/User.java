package com.mele.dao.entity;

import com.mele.util.TextUtils;

/**
 * 用户信息
 * 
 * @author Jinhu
 * @date 2016/6/13
 */
public class User {

	/** 用户名 。 */
	private String account;

	/** 登录密码 。 */
	private String password;

	/** 联系电话 。 */
	private String tel;

	/** 邮箱 。 */
	private String email;

	/** 支付密码 。 */
	private String paypassword;

	/** 头像图片链接 。 */
	private String headic;

	/** 用户等级 。 */
	private int level;

	public User() {
		this("", "", "", "", "", "", 1);
	}

	public User(String account, String password, String tel, String email,
			String paypassword, String headic, int level) {
		this.account = account;
		this.password = password;
		this.tel = tel;
		this.email = email;
		this.paypassword = paypassword;
		this.headic = headic;
		this.level = level;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPaypassword() {
		return paypassword;
	}

	public void setPaypassword(String paypassword) {
		this.paypassword = paypassword;
	}

	public String getHeadic() {
		return headic;
	}

	public void setHeadic(String headic) {
		this.headic = headic;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	@Override
	public String toString() {
		return " account: " + account + //
				" password: " + password + //
				" tel: " + tel + //
				" email: " + email + //
				" paypassword: " + paypassword + //
				" headic: " + headic + //
				" level: " + level;
	}

	@Override
	public boolean equals(Object obj) {
		if (null == obj)
			return false;
		if (this == obj)
			return true;
		if (obj instanceof User) {
			User another = (User) obj;
			return account.equals(another.account);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return super.hashCode() + account.hashCode();
	}

	public boolean isNullValues() {
		return TextUtils.isEmpty(account);
	}
}
