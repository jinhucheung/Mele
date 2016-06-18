package com.mele.file.cache;

import com.mele.dao.IUserSchema;

public class Test {
	public static void main(String[] args) {
		try {
			new Test().Exception(12);
		} catch (Exception e) {
			System.out.println("Exception");
		}

		FileCache cache = new FileCache();
		cache.CacheData(IUserSchema.COLUMN_ACCOUNT, "HiKum", 10 * 1000);
	}

	public void Exception(int i) {
		if (i > 0)
			throw new NullPointerException();

	}
}
