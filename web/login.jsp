<%--
  Created by IntelliJ IDEA.
  User: lightwingng
  Date: 2018/7/5
  Time: 19:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Student Manage System</title>
</head>
<body>
<h2>Welcomet to Use the Student Management</h2>
<form action="LoginServlet" method="post">
    <table>
        <tr>
            <td>Account</td>
            <td>
                <input type="text" name="username">
            </td>
        </tr>
        <tr>
            <td>Password</td>
            <td>
                <input type="password" name="password">
            </td>
        </tr>
        <tr align="center">
            <td></td>
            <td style="font-size: 2em">
                <input type="submit" value="Login"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
