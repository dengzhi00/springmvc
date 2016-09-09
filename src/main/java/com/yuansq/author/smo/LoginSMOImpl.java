package com.yuansq.author.smo;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.yuansq.author.bmo.UserAccountBMO;
import com.yuansq.commom.CommonConstant;
import com.yuansq.util.CheckCodeFactory;
import com.yuansq.util.StringUtil;

@Service
public class LoginSMOImpl implements LoginSMO {

	@Resource
	public UserAccountBMO userAccountBMO;

	@Override
	public Map<String, Object> login(Map<String, Object> paramsMap, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> resMap = new HashMap<>();
		String message = "";
		String checkCodeSession = StringUtil.isNotBlank(request.getSession().getAttribute("checkCode") == null ? ""
				: request.getSession().getAttribute("checkCode").toString())
						? request.getSession().getAttribute("checkCode").toString() : "";
		String checkCodeParam = paramsMap.containsKey("checkCode") ? paramsMap.get("checkCode").toString() : "";
		if (checkCodeSession.equalsIgnoreCase(checkCodeParam)) {

			String pwd = paramsMap.containsKey("pwd") ? paramsMap.get("pwd").toString() : "";
			Map<String, Object> userMap = userAccountBMO.queryAccount(paramsMap);
			if (userMap.get("result").toString().equals(CommonConstant.RESULT_TRUE)) {
				@SuppressWarnings("unchecked")
				List<Map<String, Object>> list = (List<Map<String, Object>>) userMap.get("accountList");
				if (list.size() > 1) {
					resMap.put("result", CommonConstant.RESULT_FALSE);
					message = "用户有重名！请联系管理员！";
				} else if (list.size() == 1) {
					String pwdRes = list.get(0).get("PWD").toString();
					boolean pwdResResult = pwdRes.equals(pwd);
					Object time = request.getSession().getAttribute("loginTimes") == null ? ""
							: request.getSession().getAttribute("loginTimes");
					int loginTimes = StringUtil.isNotBlank(time.toString()) ? Integer.parseInt(time.toString()) : 0;
					if (pwdResResult) {
						resMap.put("result", CommonConstant.RESULT_TRUE);
						resMap.put("loginUser", ((List<?>)userMap.get("accountList")).get(0));
						// 验证成功,将用户信息放入session
						request.getSession().setAttribute("loginUser", resMap.get("loginUser"));
						request.getSession().setAttribute("loginState", CommonConstant.LOGIN_ON_LINE);
					} else {
						request.getSession().setAttribute("loginUser", resMap.get("loginUser"));
						request.getSession().setAttribute("loginState", CommonConstant.RESULT_FALSE);
						request.getSession().setAttribute("loginTimes", loginTimes + 1);
						resMap.put("result", CommonConstant.RESULT_FALSE);
						resMap.put("loginTimes", loginTimes + 1);
						message = "密码错误!";
					}
				} else if (list.size() < 1) {
					resMap.put("result", CommonConstant.RESULT_FALSE);
					message = "用户不存在！";
				}
			} else {
				resMap.put("result", CommonConstant.RESULT_FALSE);
				message = userMap.get("msg").toString();
			}
		} else {
			resMap.put("result", CommonConstant.RESULT_FALSE);
			message = "校验码有误!";
		}
		resMap.put("msg", message);
		return resMap;

	}

	public Map<String, Object> loginOut(Map<String, Object> paramsMap, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> resMap = new HashMap<>();
		request.getSession().removeAttribute("loginUser");
		request.getSession().removeAttribute("loginState");
		resMap.put("result", CommonConstant.RESULT_TRUE);
		resMap.put("msg", "退出成功！");
		return resMap;
	}

	@Override
	public void prodCheckCode(Map<String, Object> paramMap, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CheckCodeFactory checkCode = new CheckCodeFactory();
		checkCode.service(request, response);

	}

	@Override
	public boolean permissionCheck(Map<String, Object> paramMap, HttpServletRequest request,
			HttpServletResponse response) {
		@SuppressWarnings("unchecked")
		Map<String,Object> loginUserSession	=(Map<String,Object>)request.getSession().getAttribute("loginUser");
		String userIdSession=loginUserSession==null?"":loginUserSession.get("USER_ID").toString();
	    Object loginStateSession=request.getSession().getAttribute("loginState");
	    String   loginState=loginStateSession==null?"":loginStateSession.toString();
		boolean checkResult=	paramMap.get("userId").equals(userIdSession)
				&&CommonConstant.LOGIN_ON_LINE.equals(loginState);
		
	
	   return checkResult;
	}

}
