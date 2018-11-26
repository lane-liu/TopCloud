package com.top.cloud.utils;

import java.util.Random;

/** 动态获取4位整数验证码*/
public class EmaiCheckCode {

	public static String getEmaiCheckCode(){
		String result="";
		Random ran=new Random();
		for(int i=0;i<4;i++){
			int rNum=ran.nextInt(10);
			result=result+rNum;
		}
		return result;
	}
}
