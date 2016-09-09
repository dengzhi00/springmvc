package com.yuansq.author.bmo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuansq.author.dao.UserInfoDAO;

@Service
public class UserInfoBMOImpl implements UserInfoBMO {
	@Autowired
	public UserInfoDAO userInfoDAO;

	@Override
	public List<Map<String, Object>> queryUser(Map<String, Object> paramsMap) {

		return userInfoDAO.queryUser(paramsMap);
	}

	@Override
	public Map<String, Object> addUser(Map<String, Object> paramsMap) {
		// userInfoDAO.addUser(paramsMap)
		return null;
	}

}
