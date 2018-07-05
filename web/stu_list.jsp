<%--
  Created by IntelliJ IDEA.
  User: lightwingng
  Date: 2018/7/5
  Time: 19:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Students' Information Management System</title>
</head>
<body>
<img src="static/imgs/MIT_Seal.svg" style="width: 80px"><span style="font-size: 2cm">Students'
    Information
    List</span>
<table width="100%">
    <tr align="center">
        <td>Stu. ID</td>
        <td>Name</td>
        <td>Age</td>
        <td>Gender</td>
        <td>Address</td>
        <td>OPT.</td>
    </tr>
    <c:forEach items="${ list }" var="stu">
        <c:if test=""></c:if>
        <tr align="center">
            <td>${ stu.id }</td>
            <td>${ stu.name }</td>
            <td>${ stu.age }</td>
            <td>${ stu.gender }</td>
            <td>${ stu.address }</td>
            <td>
                <input type="submit" value="Update" style="background-color: dodgerblue; color: white">
                &nbsp;
                <input type="submit" value="Delete" style="background-color: red; color: white">
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
