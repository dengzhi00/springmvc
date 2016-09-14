package com.yuansq.author.smo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yuansq.author.bmo.UserAccountBMO;
import com.yuansq.author.bmo.UserInfoBMO;
import com.yuansq.common.CommonConstant;

@Service
public class RegSMOImpl implements RegSMO {
	@Resource
	public UserAccountBMO userAccountBMO;

	@Resource
	public UserInfoBMO userInfoBMO;

	@Override
	public Map<String, Object> register(Map<String, Object> paramMap) {
		/*
		 * Map<String, Object> addAccountRes =
		 * userAccountBMO.addAccount(paramMap); Map<String, Object> addUserRes =
		 * new HashMap<>(); if
		 * (paramMap.get("result").equals(CommonConstant.RESULT_TRUE)) {
		 * addUserRes = userInfoBMO.addUser(addAccountRes); return addUserRes; }
		 * else { return addAccountRes; }
		 */
		// 先校验用户名
		Map<String, Object> resMap = new HashMap<String, Object>();
		Map<String, Object> checkMap = check(paramMap);
		if (checkMap.get("result").equals(CommonConstant.RESULT_TRUE)) {
			// 校验通过新增用户
			resMap = userAccountBMO.addAccount(paramMap);
		} else {
			resMap = checkMap;
		}
		return resMap;
	}

	@Override
	public Map<String, Object> check(Map<String, Object> paramMap) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Map<String, Object> accountMap = userAccountBMO.queryAccountByUserName(paramMap);

		if (accountMap.get("result").equals(CommonConstant.RESULT_TRUE)) {
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> list = (List<Map<String, Object>>) accountMap.get("accountList");
			if (list.isEmpty()) {
				resMap.put("result", CommonConstant.RESULT_TRUE);
				resMap.put("msg", paramMap.get("userName") + "可以使用!");
			} else {
				resMap.put("msg", paramMap.get("userName") + "用户名已经存在!");
				resMap.put("result", CommonConstant.RESULT_FALSE);
			}
		} else {
			resMap = accountMap;
		}
		return resMap;
	}

}
