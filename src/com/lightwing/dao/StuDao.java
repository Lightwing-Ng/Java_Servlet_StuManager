package com.lightwing.dao;

import java.util.List;

import com.lightwing.domain.Student;

public interface StuDao {

    /**
     * 查询出来所有的学生信息
     *
     * @return List集合
     */
    List<Student> findAll();
}
