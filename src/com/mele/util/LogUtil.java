package com.mele.util;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.XMLFormatter;

/**
 * 日志记录
 * 
 * @author Jinhu
 * @date 2016/6/9
 */
public class LogUtil {
	/** 本地日志输出文件 。 */
	private static final String LOGFILE = "mele.log";
	/** 本地日志管理器名 。 */
	private static final String LOGNAME = "meleLog";

	/** 本地日志管理 器。 */
	private static Logger mLogger;

	private LogUtil() {

	}

	/**
	 * 初始化本地日志管理器
	 */
	public static void initLocalLogger() {
		mLogger = Logger.getLogger(LOGNAME);
		mLogger.setLevel(Level.FINE); // 不输出低于FINE级别的日志信息
		try {
			Handler handler = new FileHandler(LOGFILE, 0, 1, true);
			handler.setLevel(Level.FINE);
			handler.setFormatter(new XMLFormatter());
			mLogger.addHandler(handler);
			mLogger.setUseParentHandlers(false);
		} catch (IOException e) {
			e.printStackTrace();
			mLogger = null;
		}
	}

	/**
	 * 本地输出:错误信息
	 * 
	 * @param msg
	 */
	public static void severeLocal(String msg) {
		if (null == mLogger)
			return;
		mLogger.severe(msg);
	}

	/**
	 * 本地输出:警告信息
	 * 
	 * @param msg
	 */
	public static void warningLocal(String msg) {
		if (null == mLogger)
			return;
		mLogger.warning(msg);
	}

	/**
	 * 本地输出:调试信息
	 * 
	 * @param msg
	 */
	public static void infoLocal(String msg) {
		if (null == mLogger)
			return;
		mLogger.info(msg);
	}

	/**
	 * 本地输出:配置信息
	 * 
	 * @param msg
	 */
	public static void configLocal(String msg) {
		if (null == mLogger)
			return;
		mLogger.config(msg);
	}

	/**
	 * 本地输出:提示信息
	 * 
	 * @param msg
	 */
	public static void fineLocal(String msg) {
		if (null == mLogger)
			return;
		mLogger.fine(msg);
	}

	/**
	 * 控制台输出:错误信息
	 * 
	 * @param msg
	 */
	public static void severe(String msg) {
		Logger.getGlobal().severe(msg);
	}

	/**
	 * 控制台输出:警告信息
	 * 
	 * @param msg
	 */
	public static void warning(String msg) {
		Logger.getGlobal().warning(msg);
	}

	/**
	 * 控制台输出:调试信息
	 * 
	 * @param msg
	 */
	public static void info(String msg) {
		Logger.getGlobal().info(msg);
	}

	/**
	 * 控制台输出:配置信息
	 * 
	 * @param msg
	 */
	public static void config(String msg) {
		Logger.getGlobal().config(msg);
	}

	/**
	 * 控制器输出:提示信息
	 * 
	 * @param msg
	 */
	public static void fine(String msg) {
		Logger.getGlobal().fine(msg);
	}

	/**
	 * 带标签的调试信息
	 * 
	 * @param tag
	 * @param msg
	 */
	public static void info(String tag, String msg) {
		Logger.getGlobal().info("<" + tag + "> " + msg);
	}

	/**
	 * 带标签的警告信息
	 * 
	 * @param tag
	 * @param msg
	 */
	public static void warning(String tag, String msg) {
		Logger.getGlobal().warning("<" + tag + "> " + msg);
	}
}
