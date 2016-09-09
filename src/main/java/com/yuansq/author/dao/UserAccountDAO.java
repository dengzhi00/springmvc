package com.yuansq.author.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yuansq.author.dto.UserAccount;

@Service
public interface UserAccountDAO {
	// 查询账号信息
	public List<Map<String, Object>> queryAccount(Map<String, Object> paramMap);

	// 新增账号信息
	public int addAccount(UserAccount userAccount);

}
