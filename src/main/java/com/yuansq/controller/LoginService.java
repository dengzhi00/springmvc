package com.yuansq.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

public interface LoginService {
	// 登陆验证
	public String login(Map<String, Object> paramsMap ,HttpServletRequest request,HttpServletResponse response);

	// 验证码
	public void checkCode(HttpServletRequest request,HttpServletResponse response);
	
	//
	public String getLoginPage(HttpServletRequest request, Model model);
}
