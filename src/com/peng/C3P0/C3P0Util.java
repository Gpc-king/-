package com.peng.C3P0;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author Peng
 * @Date 2022/3/29 17:38
 * @Version 1.0
 */

public class C3P0Util {

    private static ComboPooledDataSource dataSource = new ComboPooledDataSource();

    public Connection getConn(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            System.out.println(getClass() + " --> getConn exception");
            throw new RuntimeException();
        }
    }

    public void setClose(Connection conn, PreparedStatement ps, ResultSet rs){
        if (rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                System.out.println(getClass() + " --> setClose : rs close exception");
            }
        }
        if (ps != null){
            try {
                ps.close();
            } catch (SQLException e) {
                System.out.println(getClass() + " --> setClose : ps close exception");
            }
        }
        if (conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println(getClass() + " --> setClose : conn close exception");
            }
        }
    }
}
