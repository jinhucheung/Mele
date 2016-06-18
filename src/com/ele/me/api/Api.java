package com.ele.me.api;

/**
 * 抽象类Api实现了Xml与Json的工厂方法
 * 以实现灵活切换数据格式的功能
 * @author Administrator
 *
 */

public abstract class Api {
   public  final static String XML="Xml";
   public  final static String JSON="Json";
   public final static String DataType=JSON;//默认数据格式
   
   /**
    * 创建一个Xml或者Json对象
    * @param dataType 数据类型
    * @return
    */
	public static Api  Factory(String dataType){
		   try {
			Class<?> cls = Class.forName("com.ele.me.api."+dataType);
			Object newInstance = cls.newInstance();
			return (Api) newInstance;
		} catch (Exception e) {
			System.out.println("Error:请注意当前文件所在包");
			e.printStackTrace();
		}
		   return null;
	}
	
	/**
	 * 无参默认创建json对象
	 * @return
	 */
	public static Api  Factory(){
	   return Api.Factory(DataType);
	}
	
	/**
	 * 
	 * @param code 状态码
	 * @param message 提示信息
	 * @param objs  数据
	 * @return
	 */
    public  abstract String response(int code,String message,Object obj);
}
