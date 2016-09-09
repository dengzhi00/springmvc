package com.yuansq.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yuansq.author.smo.LoginSMO;

@Controller

public class ManagerServiceController {
	@Resource
	public LoginSMO loginSMO;

	// 首页
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String getLoginPage(@RequestParam Map<String, Object> paramMap, HttpServletRequest request,
			HttpServletResponse response) {
		String page = "/login/login";
		try {
			// 权限判断
			if (loginSMO.permissionCheck(paramMap, request, response)) {
				page = "/main/index";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}
}
