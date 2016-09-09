package com.yuansq.exception;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 基础异常包装�?
 * @author chenf
 * @version 0.1
 * @since JDK1.5
 */
public class BaseException extends RuntimeException {
	
	/**
	 * 会话失效
	 */
	public static int INVAL_SESSION_CODE = -90001;
	
	/**
	 * 会话失效
	 */
	public static int WRAPPED_INFO_ERR_CODE = -90002;	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6358391841958323805L;
	
	/**
	 * 异常编码
	 */
	protected int code = -90000;
	
	/**
	 * 异常编号
	 */
	protected String id = null;
	
	/**
	 * 异常类信�?
	 */
	protected String error = "RuntimeException";
	
	protected String message = "";
	
	/**
	 * 关键数据
	 */
	protected Map<String,Object> datas = new HashMap<String,Object>();
	
	public BaseException(int code){
		//this.code = code;
		this(code,"");
	}
	public BaseException(int code,String message){
		this(code,message,null);
	}
	public BaseException(String message){
		this(message,null);
	}
	public BaseException(String message, Throwable cause){
		this(-90000,message,cause);
	}
	public BaseException(int code ,String message, Throwable cause){
		super(message,cause);
		this.code = code;
		this.id = UUID.randomUUID().toString();
		this.message = message;
	}
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String toString() {
		return "{'id':'"+id+"','error':'"+error+"','code':"+code+",'message':'"+message+"'}";
	}
	public Map<String, Object> getDatas() {
		return datas;
	}
	public void setDatas(Map<String, Object> datas) {
		this.datas = datas;
	}
	public void addData(String name,Object data){
		this.datas.put(name, data);
	}
}
