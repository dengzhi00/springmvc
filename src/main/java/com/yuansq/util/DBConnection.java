package com.yuansq.util;
/*package com.yuansq.test.server.util;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBConnection {
    *//**
        * 通过JNDI获取数据源在获取连接对象
        * 
        * @return Connection con
        *//*
    public static Connection getCon(){      
        Connection con = null;
        try {
            Context ic = new InitialContext();
            DataSource source = (DataSource)ic.lookup("java:comp/env/mySource");          
           con = source.getConnection();       
       } catch (NamingException e) {
            System.out.println("数据源没找到�?");
           e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("获取数连接对象失败！");
            e.printStackTrace();
        }
        return con;
    }
}*/