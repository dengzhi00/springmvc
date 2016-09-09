package com.yuansq.author.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public interface UserInfoDAO {
	// 查询用户信息
	public List<Map<String, Object>> queryUser(Map<String, Object> paramsMap);

	// 新增账号信息
	public String addUser(Map<String, Object> paramsMap);
}
