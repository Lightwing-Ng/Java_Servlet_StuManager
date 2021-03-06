package com.lightwing.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.lightwing.dao.UserDao;
import com.lightwing.util.JDBCUtil;
import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

public class UserDaoImpl implements UserDao {
    @Override
    public boolean login(String userName, String password) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 1.得到连接对象
            conn = JDBCUtil.getConn();
            String sql = "SELECT * FROM t_user WHERE username=? AND password=?";

            // 2.创建ps对象
            ps = conn.prepareStatement(sql);
            ps.setString(1, userName);
            ps.setString(2, password);

            // 3. 开始执行。
            rs = ps.executeQuery();

            // 如果能够成功移到下一条记录，那么表明有这个用户。
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.release(conn, ps, rs);
        }
        return false;
    }
}
