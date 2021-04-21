package ums2.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import ums2.dao.UserDao;

public class DaoFactory {
	//利用静态代码块，在工具类被加载时即执行配置文件的读取
	private static Properties props = new Properties();
	static {		
		try {
			props.load(new FileInputStream("dao.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static Object getDao(String name) {
		Object dao = null;
		String userDao = props.getProperty(name);
		try {
			dao = Class.forName(userDao).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
		} 
		return dao;
	}
}
