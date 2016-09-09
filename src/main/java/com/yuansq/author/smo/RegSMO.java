package com.yuansq.author.smo;

import java.util.Map;

public interface RegSMO {
	/**
	 * 校验用户名
	 * 
	 * @param paramMap
	 * @return
	 */
	public Map<String, Object> check(Map<String, Object> paramMap);

	/**
	 * 注册
	 * 
	 * @param paramMap
	 * @return
	 */
	public Map<String, Object> register(Map<String, Object> paramMap);
}
