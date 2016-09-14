package com.yuansq.author.bmo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuansq.author.dao.UserAccountDAO;
import com.yuansq.author.dto.UserAccount;
import com.yuansq.common.CommonConstant;
import com.yuansq.common.ExceptionRecords;
import com.yuansq.exception.BusinessException;

@Service
public class UserAccountBMOImpl implements UserAccountBMO {
	@Autowired
	public UserAccountDAO userAccountDAO;

	@Override
	public Map<String, Object> queryAccount(Map<String, Object> paramsMap) {
		Map<String, Object> resMap = new HashMap<>();
		String message = "";
		String userName = paramsMap.containsKey("userName") ? paramsMap.get("userName").toString() : "";
		String pwd = paramsMap.containsKey("pwd") ? paramsMap.get("pwd").toString() : "";
		boolean userNameResult = !userName.equals("");
		boolean pwdResult = !pwd.equals("");
		if (userNameResult && pwdResult) {
			paramsMap.put("state", CommonConstant.RESULT_TRUE);
			List<Map<String, Object>> list = userAccountDAO.queryAccount(paramsMap);
			resMap.put("result", CommonConstant.RESULT_TRUE);
			resMap.put("accountList", list);
		} else {
			resMap.put("result", CommonConstant.RESULT_FALSE);
			if (!userNameResult) {
				message += "用户名不能为空!";
			}
			if (!pwdResult) {
				message += "密码不能为空!";
			}
			if (!pwdResult && !userNameResult) {
				message = "用户名和密码不能为空!";
			}
		}
		resMap.put("msg", message);
		return resMap;

	}

	@Override
	public Map<String, Object> addAccount(Map<String, Object> paramMap) {

		UserAccount userAccount = new UserAccount();
		String msg = "";
		String result = "";
		Map<String, Object> resMap = new HashMap<>();
		String userName = paramMap.containsKey("userName") ? paramMap.get("userName").toString() : "";
		String nickName = paramMap.containsKey("nickName") ? paramMap.get("nickName").toString() : "";
		String pwd = paramMap.containsKey("pwd") ? paramMap.get("pwd").toString() : "";
		String state = CommonConstant.RESULT_TRUE;
		boolean userNameResult = !"".equals(userName);
		boolean nickNameResult = !"".equals(nickName);
		boolean pwdResult = !"".equals(pwd);
		if (nickNameResult && pwdResult && userNameResult) {
			userAccount.setNickName(nickName);
			userAccount.setPwd(pwd);
			userAccount.setState(state);
			userAccount.setUserName(userName);
			int id = userAccountDAO.addAccount(userAccount);
			if (id > 0) {
				// userAccount.setUserId(id);
				System.out.println(userAccount.getUserId());
				resMap.put("userAccount", userAccount);
				result = CommonConstant.RESULT_TRUE;
				msg = "成功";
			} else {
				throw new BusinessException(ExceptionRecords.USER_ADD_FAILE);
			}

		} else {
			result = CommonConstant.RESULT_FALSE;
			if (!nickNameResult) {
				msg += "昵称不能为空!";
			}
			if (!pwdResult) {
				msg += "密码不能为空!";
			}
			if (!userNameResult) {
				msg += "用户名不能为空!";
			}
		}
		resMap.put("result", result);
		resMap.put("msg", msg);
		return resMap;
	}

	@Override
	public Map<String, Object> queryAccountByUserName(Map<String, Object> paramsMap) {
		Map<String, Object> resMap = new HashMap<>();
		String message = "";
		String userName = paramsMap.containsKey("userName") ? paramsMap.get("userName").toString() : "";
		boolean userNameResult = !userName.equals("");
		if (userNameResult) {
			paramsMap.put("state", CommonConstant.RESULT_TRUE);
			List<Map<String, Object>> list = userAccountDAO.queryAccount(paramsMap);
			resMap.put("result", CommonConstant.RESULT_TRUE);
			resMap.put("accountList", list);
		} else {
			resMap.put("result", CommonConstant.RESULT_FALSE);
			if (!userNameResult) {
				message += "用户名不能为空!";
			}
		}
		resMap.put("msg", message);
		return resMap;

	}

}
