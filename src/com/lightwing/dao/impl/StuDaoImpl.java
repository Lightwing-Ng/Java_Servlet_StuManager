package com.lightwing.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lightwing.dao.StuDao;
import com.lightwing.domain.Student;
import com.lightwing.util.JDBCUtil;

public class StuDaoImpl implements StuDao {
    @Override
    public List<Student> findAll() {
        List<Student> list = new ArrayList<Student>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // 1.创建连接对象
            conn = JDBCUtil.getConn();
            String sql = "SELECT * FROM t_stu";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            // 用集合装
            while (rs.next()) {
                Student stu = new Student();
                stu.setId(rs.getInt("id"));
                stu.setAge(rs.getInt("age"));
                stu.setName(rs.getString("name"));
                stu.setGender(rs.getString("gender"));
                stu.setAddress(rs.getString("address"));
                list.add(stu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.release(conn, ps, rs);
        }
        return list;
    }
}
