package com.yuansq.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import oracle.sql.CLOB;

public class OracleClobTypeHandler implements TypeHandler<Object> {

	public Object valueOf(String param) {
		return null;
	}

	@Override
	public Object getResult(ResultSet arg0, String arg1) throws SQLException {
		CLOB clob = (CLOB) arg0.getClob(arg1);
		return (clob == null || clob.length() == 0) ? null : clob.getSubString((long) 1, (int) clob.length());
	}

	@Override
	public Object getResult(ResultSet arg0, int arg1) throws SQLException {
		return null;
	}

	@Override
	public Object getResult(CallableStatement arg0, int arg1) throws SQLException {
		return null;
	}

	@Override
	public void setParameter(PreparedStatement arg0, int arg1, Object arg2, JdbcType arg3) throws SQLException {
		CLOB clob = CLOB.getEmptyCLOB();
		clob.setString(1, (String) arg2);
		arg0.setClob(arg1, clob);
	}
	 public static String ClobToString(Clob clob) {
	        String reString = "";
	        Reader is = null;
	        try {
	            is = clob.getCharacterStream();
	        } catch (SQLException e) {
	           
	            e.printStackTrace();
	        }
	        // 得到�?
	        BufferedReader br = new BufferedReader(is);
	        String s = null;
	        try {
	            s = br.readLine();
	        } catch (IOException e) {
	           
	            e.printStackTrace();
	        }
	        StringBuffer sb = new StringBuffer();
	        while (s != null) {
	            //执行循环将字符串全部取出付�?�给StringBuffer由StringBuffer转成STRING
	            sb.append(s);
	            try {
	                s = br.readLine();
	            } catch (IOException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	        }
	        reString = sb.toString();
	        return reString;
	    }
}
