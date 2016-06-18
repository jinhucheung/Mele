package com.ele.me.api;

public class JsonData {
   private int code;
   private String  message;
   private Object data;
	public JsonData() {
	
	}
	public JsonData(int code, String message, Object obj) {
		super();
		this.code = code;
		this.message = message;
		this.data = obj;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getObj() {
		return data;
	}
	public void setObj(Object obj) {
		this.data = obj;
	}

}
