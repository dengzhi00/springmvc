package com.yuansq.author.smo;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LoginSMO {
	/**
	 * 验证码生成
	 * 
	 * @param paramMap
	 * @return paramsMap,request,response
	 * @throws IOException 
	 */

	public void prodCheckCode(Map<String, Object> paramMap,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException;

	/**
	 * 登陆
	 * 
	 * @param paramMap
	 * @param paramsMap,request,response
	 * @return
	 */
	public Map<String, Object> login(Map<String, Object> paramMap, HttpServletRequest request,HttpServletResponse response);
	/**
	 * 登出
	 * @param paramsMap
	 * @param request
	 * @param paramsMap,request,response
	 * @return
	 */
	public Map<String, Object> loginOut(Map<String, Object> paramsMap, HttpServletRequest request,	HttpServletResponse response) ;
	/**
	 * 权限校验
	 */
	public boolean  permissionCheck(Map<String, Object> paramMap,HttpServletRequest request,	HttpServletResponse response) ;

}
