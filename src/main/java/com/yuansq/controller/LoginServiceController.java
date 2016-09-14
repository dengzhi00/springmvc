package com.yuansq.controller;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yuansq.author.smo.LoginSMO;
import com.yuansq.common.CommonConstant;
import com.yuansq.util.JsonUtil;

@Controller
@RequestMapping("/login")
public class LoginServiceController {

	@Autowired
	public LoginSMO loginSMO;
	@Autowired
	HttpServletRequest request;
	@Context
	HttpServletResponse response;
	//登陆首页
	@RequestMapping(value="/main" ,method = RequestMethod.GET)
	public String getLoginPage(HttpServletRequest request, Model model) {
		return "/login/login";
	}
	//登陆模板
	@RequestMapping(value="/getLoginTemplate" ,method = RequestMethod.GET)
	public String getLoginTemplate(HttpServletRequest request, Model model) {
		return "/login/loginTemplate";
	}
	//注册页面
	@RequestMapping(value="/getReg" ,method = RequestMethod.GET)
	public String getReg(HttpServletRequest request, Model model) {
		return "/login/reg";
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public String login(@RequestBody String json,HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> resMap = new HashMap<>();
		try {
			Map<String, Object> paramsMap = JsonUtil.fromJSON(json);
			String methodName = paramsMap!=null?(paramsMap.containsKey(CommonConstant.METHOD)
					? paramsMap.get(CommonConstant.METHOD).toString() : ""):"";
			if (!"".equals(methodName)) {
				Class<?>[] cArg = new Class[3];
				cArg[0] = Map.class;
				cArg[1] = HttpServletRequest.class;
				cArg[2] = HttpServletResponse.class;
				Method loginMeth = loginSMO.getClass().getDeclaredMethod(methodName, cArg);
				if (loginMeth != null) {
					resMap = (Map<String, Object>) loginMeth.invoke(loginSMO, paramsMap, request, response);
				} else {
					resMap.put("result", CommonConstant.RESULT_FALSE);
					resMap.put("msg", "method值有误!");
				}
			} else {
				resMap.put("result", CommonConstant.RESULT_FALSE);
				resMap.put("msg", "method值不能为空!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("result", CommonConstant.RESULT_EXCEPTION);
			resMap.put("msg", e.getStackTrace());
		}
		return JsonUtil.getJsonAsString(resMap);
	}
	@RequestMapping(value="/checkCode" ,method = RequestMethod.GET)
	public void checkCode(HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> paramsMap = null;
		try {
			loginSMO.prodCheckCode(paramsMap, request, response);
		} catch (ServletException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	

}
