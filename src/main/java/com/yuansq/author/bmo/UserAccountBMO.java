package com.yuansq.author.bmo;

import java.util.Map;

public interface UserAccountBMO {
	/**
	 * 查询账号信息
	 * 
	 * @param paramMap
	 * @return
	 */
	public Map<String, Object> queryAccount(Map<String, Object> paramMap);

	/**
	 * 新增账户
	 * 
	 * @param paramMap
	 * @return
	 */
	public Map<String, Object> addAccount(Map<String, Object> paramMap);

	/**
	 * 通过用户名查询账号信息
	 * 
	 * @param paramMap
	 * @return
	 */
	public Map<String, Object> queryAccountByUserName(Map<String, Object> paramMap);
}
