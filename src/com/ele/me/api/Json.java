package com.ele.me.api;
import com.google.gson.Gson;
public class Json extends Api {
	@Override
	public String response(int code, String message, Object obj) {
		     Gson gson = new Gson();
		     String result = gson.toJson(    new JsonData(code, message, obj));
		     return result;
	}
}
