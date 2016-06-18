package com.mele.file.cache;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Date;

/**
 * 缓存类
 * 
 * @author Administrator
 *
 */
public class FileCache {
	private String _ext = ".txt";// 静态缓存文件名后缀
	private String _dir = "E://javaspace//eleme//cachefile";// 在当前目录下创建缓存文件夹命名为"cachefile"

	public FileCache() {

	}

	public FileCache(String _ext, String _dir) {
		this._ext = _ext;
		this._dir = _dir;
	}

	/**
	 * 缓存数据
	 * 
	 * @param key
	 *            文件名
	 * @param value
	 *            data值
	 * @param time
	 *            缓存时间
	 */
	public void CacheData(String key, String value, int time) {
		File file = new File(this._dir);
		if (!file.exists() && !file.isDirectory()) {
			file.mkdirs();
		}
		String pathName = this._dir + "\\" + key + this._ext;
		file = new File(pathName);
		DecimalFormat df = new DecimalFormat("0000000000000");
		value = df.format(time) + value;

		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			PrintWriter printWriter = new PrintWriter(file);
			printWriter.write(value);
			printWriter.close();
		} catch (FileNotFoundException e) {
			System.out.println("找不到文件:" + e.getMessage());
		}
	}

	/**
	 * 获取缓存
	 * 
	 * @param key
	 * @return null->获取失败
	 */
	public String getCacheData(String key) {
		String pathName = this._dir + "\\" + key + this._ext;
		File file = new File(pathName);
		if (!file.exists()) {
			return null;
		}
		String result = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String s = null;
			while ((s = br.readLine()) != null) {
				result += s;
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String strTime = result.substring(0, 13);
		int i;
		for (i = 0; i < strTime.length(); i++) {
			if (strTime.charAt(i) != '0') {
				break;
			}
		}
		String resultData = result.substring(13);
		String relStrTime = strTime.substring(i);

		int time;
		if (i < 13) {
			time = Integer.parseInt(relStrTime);
		} else {
			time = 0;
		}

		if (file.lastModified() + time < new Date().getTime()) {
			file.delete();
			return null;
		}
		return resultData;
	}
}
