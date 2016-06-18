package com.mele.dao.entity;

/**
 * 商家类型信息
 * 
 * @author Jinhu
 * @date 2016/6/11
 */
public class StoreType {

	/** 类型编号 。 */
	private int stypeId;

	/** 类型名 。 */
	private String typeName;

	/** 父级类型编号 。 */
	private int superTypeId;

	public StoreType() {
		this(0, "", 0);
	}

	public StoreType(String typeName, int superTypeId) {
		this(0, typeName, superTypeId);
	}

	public StoreType(int stypeId, String typeName, int superTypeId) {
		this.stypeId = stypeId;
		this.typeName = typeName;
		this.superTypeId = superTypeId;
	}

	public int getStypeId() {
		return stypeId;
	}

	public void setStypeId(int stypeId) {
		this.stypeId = stypeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public int getSuperTypeId() {
		return superTypeId;
	}

	public void setSuperTypeId(int superTypeId) {
		this.superTypeId = superTypeId;
	}

	@Override
	public String toString() {
		return "stypeId :" + stypeId + " typeName: " + typeName
				+ " superTypeId: " + superTypeId;
	}

	@Override
	public boolean equals(Object obj) {
		if (null == obj)
			return false;
		if (this == obj)
			return true;
		if (obj instanceof StoreType) {
			StoreType another = (StoreType) obj;
			return stypeId == another.stypeId
					&& superTypeId == another.superTypeId;
		}
		return false;
	}

	@Override
	public int hashCode() {
		if (0 == stypeId) {
			return super.hashCode();
		}
		return stypeId;
	}

	public boolean isNullValue() {
		return stypeId <= 0;
	}
}
