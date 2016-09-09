package com.yuansq.util;

import java.util.regex.Pattern;

/**
 * 校验器：利用正则表达式校验邮箱�?�手机号�?
 * 
 * 
 */
public class ValidateParams {

	/**
	 * 正则表达式：验证用户�?
	 */
	public static final String REGEX_USERNAME = "^[a-zA-Z]\\w{5,17}$";

	/**
	 * 正则表达式：验证密码
	 */
	public static final String REGEX_PASSWORD = "^[a-zA-Z0-9]{6,16}$";

	/**
	 * 正则表达式：验证手机�?
	 */
	public static final String REGEX_MOBILE = "^((13[0-9])|(15[^4,\\D])|(18[0-9])|14[57]|17([05-8]))\\d{8}$";

	/**
	 * 正则表达式：验证邮箱
	 */
	public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

	/**
	 * 正则表达式：验证汉字
	 */
	public static final String REGEX_CHINESE = "^[\u4e00-\u9fa5]{1,20}$";

	/**
	 * 正则表达式：验证身份�?
	 */
	public static final String REGEX_ID_CARD = "(^\\d{18}$)|(^\\d{15}$)";

	/**
	 * 正则表达式：验证URL
	 */
	public static final String REGEX_URL = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";

	/**
	 * 正则表达式：验证IP地址
	 */
	public static final String REGEX_IP_ADDR = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";
	/**
	 * 正则表达式：验证发票代码
	 */
	public static final String REGEX_INVOICE_CODE = "^([0-2]\\d{9}|[0-2]\\d{11})$";
	/**
	 * 正则表达式：验证发票号码
	 */
	public static final String REGEX_INVOICE_ID = "^\\d{8}$";
	/**
	 * 正则表达式：验证用户ID
	 */

	public static final String REGEX_USER_ID = "^\\d{4,16}$";

	/**
	 * 正则表达式：验证数字
	 */
	public static final String POSITIVE_INTEGER = "^[1-9]\\d*$";
	/**
	 * 正则表达式： 验证日期
	 */
	public static final String REGEX_DATE_TIME = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$";

	/**
	 * 正则表达式： 验证金额
	 */
	public static final String REGEX_AMOUNT = "^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,5})?$";
	/**
	 * 正则表达式： 验证企业识别号org_number
	 */

	public static final String REGEX_ORGNUMBER = "^\\d{15}$";

	/**
	 * 正则表达式：验证银行账号
	 */
	public static final String REGEX_BANK_ACT = "^(\\d{16}|\\d{19})$";
	/**
	 * 正则表达式：验证座机�?
	 */
	public static final String REGEX_TEL = "^(0[1-9]{2})-\\d{8}$|^(0[1-9]{3}-(\\d{7,8}))$";

	/**
	 * 校验银行账号
	 * @param bankAct
	 * @return
	 */
	public static boolean isBankAct(String bankAct){
		return Pattern.matches(REGEX_BANK_ACT, bankAct);
	}

	/**
	 * 校验座机电话�?
	 * @param regTel
	 * @return
	 */
	public static boolean isTel(String regTel) {
		return Pattern.matches(REGEX_TEL, regTel);
	}

	/**
	 * 校验用户�?
	 * 
	 * @param username
	 * @return 校验通过返回true，否则返回false
	 */
	public static boolean isUsername(String userName) {
		return Pattern.matches(REGEX_USERNAME, userName);
	}

	/**
	 * 校验用户�?
	 * 
	 * @param account
	 * @return 校验通过返回true，否则返回false
	 */
	public static boolean isAccount(String account) {
		return Pattern.matches(REGEX_USERNAME, account);
	}

	/**
	 * 校验密码
	 * 
	 * @param password
	 * @return 校验通过返回true，否则返回false
	 */
	public static boolean isPassword(String password) {
		return Pattern.matches(REGEX_PASSWORD, password);
	}

	/**
	 * 校验手机�?
	 * 
	 * @param mobile
	 * @return 校验通过返回true，否则返回false
	 */
	public static boolean isMobile(String mobile) {
		return Pattern.matches(REGEX_MOBILE, mobile);
	}

	/**
	 * 校验邮箱
	 * 
	 * @param email
	 * @return 校验通过返回true，否则返回false
	 */
	public static boolean isEmail(String email) {
		return Pattern.matches(REGEX_EMAIL, email);
	}

	/**
	 * 校验汉字
	 * 
	 * @param chinese
	 * @return 校验通过返回true，否则返回false
	 */
	public static boolean isChinese(String chinese) {
		return Pattern.matches(REGEX_CHINESE, chinese);
	}

	/**
	 * 校验身份�?
	 * 
	 * @param idCard
	 * @return 校验通过返回true，否则返回false
	 */
	public static boolean isIDCard(String idCard) {
		return Pattern.matches(REGEX_ID_CARD, idCard);
	}

	/**
	 * 校验URL
	 * 
	 * @param url
	 * @return 校验通过返回true，否则返回false
	 */
	public static boolean isUrl(String url) {
		return Pattern.matches(REGEX_URL, url);
	}

	/**
	 * 校验IP地址
	 * 
	 * @param ipAddr
	 * @return
	 */
	public static boolean isIPAddr(String ipAddr) {
		return Pattern.matches(REGEX_IP_ADDR, ipAddr);
	}

	public static boolean isMustExist(Object obj) {
		if (obj == null || "".equals(String.valueOf(obj))) {
			return false;
		}
		return true;
	}

	/**
	 * 校验 发票代码
	 * 
	 * @return
	 */
	public static boolean isInvoiceCODE(String obj) {
		return Pattern.matches(REGEX_INVOICE_CODE, obj);
	}

	/**
	 * 校验 发票号码
	 * 
	 * @return
	 */
	public static boolean isInvoiceID(String obj) {
		return Pattern.matches(REGEX_INVOICE_ID, obj);
	}

	/**
	 * 校验 用户ID
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isUserID(String obj) {
		return Pattern.matches(REGEX_USER_ID, obj);
	}

	/**
	 * 校验 登录表单的内�?
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isAccountName(String obj) {
		return Pattern.matches(REGEX_EMAIL, obj) || Pattern.matches(REGEX_MOBILE, obj)
				|| Pattern.matches(REGEX_USERNAME, obj);

	}

	/**
	 * 校验 日期时间
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isDateTime(String obj) {
		return Pattern.matches(REGEX_DATE_TIME, obj);
	}

	/**
	 * 校验 金额
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isAmount(String obj) {
		return Pattern.matches(REGEX_AMOUNT, obj);
	}

	/**
	 * 校验 纳税登记�?
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isOrgNumber(String obj) {
		return Pattern.matches(REGEX_ORGNUMBER, obj);
	}

	/**
	 * 校验正整�?
	 */
	public static boolean isPositiveInteger(String obj) {
		return Pattern.matches(POSITIVE_INTEGER, obj);
	}

	/**
	 * 校验输入字符串是否为空串或全是空�?
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNullStr(String str) {
		if (str == null) {
			return true;
		} else {
			if ("".equals(str.trim())) {
				return true;
			}
		}
		return false;
	}
}