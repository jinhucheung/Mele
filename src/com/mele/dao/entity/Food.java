package com.mele.dao.entity;

/**
 * 商家菜式信息
 * 
 * @author Jinhu
 * @date 2016/6/11
 */
public class Food {
	/** 商家编号 。 */
	private long storeId;

	/** 菜式编号。 */
	private int foodId;

	/** 菜式名 。 */
	private String foodName;

	/** 菜式价格。 */
	private float foodPrices;

	/** 菜式类型。 */
	private String foodType;

	/** 菜式图标。 */
	private String foodIc;

	public Food() {
		this(0, 0, "", 0, "", "");
	}

	public Food(long storeId, int foodId, String foodName, float foodPrices,
			String foodType, String foodIc) {
		this.storeId = storeId;
		this.foodId = foodId;
		this.foodName = foodName;
		this.foodPrices = foodPrices;
		this.foodType = foodType;
		this.foodIc = foodIc;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public int getFoodId() {
		return foodId;
	}

	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public float getFoodPrices() {
		return foodPrices;
	}

	public void setFoodPrices(float foodPrices) {
		this.foodPrices = foodPrices;
	}

	public String getFoodType() {
		return foodType;
	}

	public void setFoodType(String foodType) {
		this.foodType = foodType;
	}

	public String getFoodIc() {
		return foodIc;
	}

	public void setFoodIc(String foodIc) {
		this.foodIc = foodIc;
	}

	@Override
	public String toString() {
		return "storeId: " + storeId + " foodId: " + foodId + " foodName: "
				+ foodName + " foodPrices: " + foodPrices + " foodType: "
				+ foodType + " foodIc: " + foodIc;
	}

	@Override
	public boolean equals(Object obj) {
		if (null == obj)
			return false;
		if (this == obj)
			return true;
		if (obj instanceof Food) {
			Food another = (Food) obj;
			return storeId == (another.storeId) && foodId == (another.foodId);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	public boolean isNullValue() {
		return storeId <= 0 || foodId <= 0;
	}
}
