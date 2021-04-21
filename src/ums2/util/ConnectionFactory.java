package ums2.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
	//利用静态代码块，在工具类被加载时即执行配置文件的读取
	private static Properties props = new Properties();
	static {		
		try {
			System.out.println(new FileInputStream("database.properties"));
			props.load(new FileInputStream("database.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection(){
		Connection con = null;
		try {
			String driver = props.getProperty("driver");
			String url = props.getProperty("url");
			String username = props.getProperty("user");
			String password = props.getProperty("password");
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			System.out.println("failed to register driver.");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("failed to execute sql.");
			e.printStackTrace();
		}
		return con;
	}
	
	public static void main(String[] args) {
		Connection con = getConnection();
		System.out.println(con);
	}
}
