package com.top.cloud.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**JDBC工具类的封装 */
public class JdbcUtils {
	//Properties:是Java里面专门提供用来解析属性文件的类
    private static Properties pro=new Properties();
	private static String driverClass;
	private static String url;
	private static String user;
	private static String password;
	/**类加载的时候执行(只执行一次) */
	static{
		try {
			//加载配置文件(指定属性文件的路径),属性文件数据度被加载到pro对象里面
			pro.load(JdbcUtils.class.getClassLoader().getResourceAsStream("db.properties"));
		    
			//获取driverClass,url,user,password
			driverClass = pro.getProperty("driverClass");
			url = pro.getProperty("url");
			user = pro.getProperty("user");
			password = pro.getProperty("password");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**获取连接conn */
	public static Connection getConnection(){
		Connection conn=null;
		try {//加载驱动
			Class.forName(driverClass);
			//通过驱动管理器获取连接
			conn = DriverManager.getConnection(url,user,password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	/**关闭连接结果集ResultSet */
	public static void close(ResultSet rs){
		try {
			if(rs!=null)
			  rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**关闭连接Statement */
	public static void close(Statement state){
		try {
			if(state!=null)
			  state.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**关闭连接Connection */
	public static void close(Connection conn){
		try {
			if(conn!=null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
