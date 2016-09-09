package com.yuansq.util;

public class NumberUtil {
	private final static String[] CN_Digits = { "�?", "�?", "�?", "�?", "�?", "�?", "�?", "�?", "�?", "�?", };

	public static String CNValueOf(String moneyValue) {
		// 使用正则表达式，去除前面的零及数字中的�?�号
		String value = moneyValue.replaceFirst("^0+", "");
		value = value.replaceAll(",", "");
		// 分割小数部分与整数部�?
		int dot_pos = value.indexOf('.');
		String int_value;
		String fraction_value;
		if (dot_pos == -1) {
			int_value = value;
			fraction_value = "00";
		} else {
			int_value = value.substring(0, dot_pos);
			fraction_value = value.substring(dot_pos + 1, value.length()) + "00".substring(0, 2);// 也加两个0，便于后面统�?处理
		}

		int len = int_value.length();
		if (len > 16)
			return "值过�?";
		StringBuffer cn_currency = new StringBuffer();
		String[] CN_Carry = new String[] { "", "�?", "�?", "�?" };
		// 数字分组处理，计数组�?
		int cnt = len / 4 + (len % 4 == 0 ? 0 : 1);
		// 左边第一组的长度
		int partLen = len - (cnt - 1) * 4;
		String partValue = null;
		boolean bZero = false;// 有过�?
		String curCN = null;
		for (int i = 0; i < cnt; i++) {
			partValue = int_value.substring(0, partLen);
			int_value = int_value.substring(partLen);
			curCN = Part2CN(partValue, i != 0 && !"�?".equals(curCN));
			// System.out.println(partValue+":"+curCN);
			// 若上次为零，这次不为零，则加入零
			if (bZero && !"�?".equals(curCN)) {
				cn_currency.append("�?");
				bZero = false;
			}
			if ("�?".equals(curCN))
				bZero = true;
			// 若数字不是零，加入中文数字及单位
			if (!"�?".equals(curCN)) {
				cn_currency.append(curCN);
				cn_currency.append(CN_Carry[cnt - 1 - i]);
			}
			// 除最左边�?组长度不定外，其它长度都�?4
			partLen = 4;
			partValue = null;
		}
		cn_currency.append("�?");
		// 处理小数部分
		int fv1 = Integer.parseInt(fraction_value.substring(0, 1));
		int fv2 = Integer.parseInt(fraction_value.substring(1, 2));
		if (fv1 + fv2 == 0) {
			cn_currency.append("�?");
		} else {
			cn_currency.append(CN_Digits[fv1]).append("�?");
			cn_currency.append(CN_Digits[fv2]).append("�?");
		}
		return cn_currency.toString();
	}

	private static String Part2CN(String partValue, boolean bInsertZero) {
		// 使用正则表达式，去除前面�?0
		partValue = partValue.replaceFirst("^0+", "");
		int len = partValue.length();
		if (len == 0)
			return "�?";
		StringBuffer sbResult = new StringBuffer();
		int digit;
		String[] CN_Carry = new String[] { "", "�?", "�?", "�?" };
		for (int i = 0; i < len; i++) {
			digit = Integer.parseInt(partValue.substring(i, i + 1));
			if (digit != 0) {
				sbResult.append(CN_Digits[digit]);
				sbResult.append(CN_Carry[len - 1 - i]);
			} else {
				// 若不是最后一位，且下不位不为零，追加�?
				if (i != len - 1 && Integer.parseInt(partValue.substring(i + 1, i + 2)) != 0)
					sbResult.append("�?");
			}
		}
		if (bInsertZero && len != 4)
			sbResult.insert(0, "�?");
		return sbResult.toString();
	}

	public static void main(String[] args) {
		System.out.println(CNValueOf("200002308857.999987"));
		System.out.println(CNValueOf("200002308857.00"));

	}
}
