package com.yuansq.commom;

import java.util.ArrayList;
import java.util.List;

public class Constants {
	//免校验数组
	 public static List<String> NOFILTER =new ArrayList<>();
			 
			 static{	
			 //注册服务
			 NOFILTER.add("service/register");
			 //登陆服务
			 NOFILTER.add("service/login");
			 };
			 
		
}
