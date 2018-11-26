package com.top.cloud.utils;

import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**Cookie工具类 */
public class CookieUtils {
	private static String default_path="/dang/";
	private static int default_age=7*24*3600;
	/**添加Cookie方法:编码使用utf8 */
	public static void addCookie(String name,String value,
			            HttpServletResponse response,int MaxAge){
		try {
			//编码
			value=URLEncoder.encode(value, "utf8");
			//创建Cookie对象
			Cookie c=new Cookie(name,value);
			//设置Cookie保存时间
			c.setMaxAge(MaxAge);
			//设置Cookie路径
			c.setPath(default_path);
			//通过response将Cookie写入浏览器
			response.addCookie(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**添加Cookie方法 */
	public static void addCookie(String name,String value,HttpServletResponse response){ 
		addCookie(name,value,response,default_age);
	}
	
	/**查找指定的Cookie */
	public static Cookie findCookie(String name,HttpServletRequest request){
		Cookie[] cookies = request.getCookies();
		if(cookies==null)return null;
		for(Cookie c:cookies){
			if(c.getName().equals(name)){
				return c;
			}
		}
		return null;
	}
	
	/**删除指定的Cookie */
	public static void deleteCookie(String name,HttpServletRequest request,HttpServletResponse response){
		//先去查找到指定的Cookie
		Cookie cookie = findCookie(name,request);
		//删除
		if(cookie!=null){
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
	}
}
