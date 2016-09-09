package com.yuansq.exception;

public class BusinessException extends BaseException {

	private static final long serialVersionUID = 198567890L;
	
	private int code;
	
	private String message;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public BusinessException(String message){
		super(INVAL_SESSION_CODE, message);
		this.message = message;
	}
	
	public BusinessException(int code,String message){
		 super(code, message);
		 this.code = code;
		 this.message = message;
	}
	
	public BusinessException(String message,Throwable cause){
		 super(message,new Throwable(message));
		 this.message = message;
	}

}
