## JSP & EL & JSTL

### 1 JSP

​	JSP（全称 JavaServer Pages）是由 Sun Microsystems 公司主导建立的一种动态网页技术标准。JSP 部署于网络服务器上，可以响应客户端发送的请求，并根据请求内容动态地生成 HTML、XML 或其他格式文档的 Web 网页，然后返回给请求者。JSP技术以Java语言作为脚本语言，为用户的 HTTP 请求提供服务，并能与服务器上的其它Java程序共同处理复杂的业务需求。

​	JSP 将 Java 代码和特定变动内容嵌入到静态的页面中，实现以静态页面为模板，动态生成其中的部分内容。JSP 引入了被称为「JSP动作」的 XML 标签，用来调用内建功能。另外，可以创建 JSP 标签库，然后像使用标准 HTML 或 XML 标签一样使用它们。标签库能增强功能和服务器性能，而且不受跨平台问题的限制。JSP 文件在运行时会被其编译器转换成更原始的 Servlet 代码。JSP 编译器可以把 JSP 文件编译成用 Java 代码写的 Servlet，然后再由 Java 编译器来编译成能快速执行的二进制机器码，也可以直接编译成二进制码。

#### 1.1 什么是 JSP

​	从用户角度看待 ，就是是一个网页；从程序员角度看待 ， 其实是一个 Java 类， 它继承了 Servlet，所以可以直接说 JSP 就是一个 Servlet.

#### 1.2 为什么会有 JSP?

​	HTML 多数情况下用来显示静态内容，一成不变的。 但是有时候我们需要在网页上显示一些动态数据， 比如： 查询所有的学生信息， 根据姓名去查询具体某个学生。  这些动作都需要去查询数据库，然后在网页上显示。 HTML 是不支持写 Java 代码 ，JSP 里面可以写 JSP 代码。 

#### 1.3 怎么用JSP

##### 1.3.1 指令写法

```jsp
<%@ 指令名字 %>
```

##### 1.3.2 page 指令

1. `language`

表明 JSP 页面中可以写 Java 代码

2. contentType

其实即使说这个文件是什么类型，告诉浏览器我是什么内容类型，以及使用什么编码

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
```

`text/html  MIMEType` 这是一个文本，html 网页

| page 指令    | 涵义                                                         |
| :----------- | :----------------------------------------------------------- |
| pageEncoding | JSP 内容编码                                                 |
| extends      | 用于指定 JSP 翻译成 Java 文件后，继承的父类是谁，一般不用改  |
| import       | 导包使用的，一般不用手写                                     |
| session      | 值可选 true/false 用于控制在这个 JSP 页面里面，能够直接使用 session 对象 具体的区别是，请看翻译后的 java 文件，如果该值是 true , 那么在代码里面会有 getSession() 的调用，如果是 false，那么就不会有该方法调用，也就是没有 session 对象了。在页面上自然也就不能使用session了。 |
3. errorPage

指的是错误的页面， 值需要给错误的页面路径

4. isErrorPage

上面的 errorPage 用于指定错误的时候跑到哪一个页面去。 那么这个 isErroPage，就是声明某一个页面到底是不是错误的页面。

##### 1.3.3 include 

包含另外一个 JSP 的内容。

```jsp
<%@ include file="other02.jsp"%>
```

* 背后细节:

把另外一个页面的所有内容拿过来一起输出。 所有的标签元素都包含进来。

##### 1.3.4 `taglib`

```jsp
<%@ taglib prefix=""  uri=""%>  
```

uri: 标签库路径
prefix : 标签库的别名  

#### 1.4 JSP 动作标签

```jsp
<jsp:include page=""></jsp:include>
<jsp:param value="" name=""/>
<jsp:forward page=""></jsp:forward>
```

1. `jsp:include`

```jsp
<jsp:include page="index.jsp"></jsp:include>
```

​	包含指定的页面， 这里是**动态包含**。 也就是不把包含的页面所有元素标签全部拿过来输出，而是把**它的运行结果拿过来**。 

2. `jsp:forward`

```jsp
<jsp:forward page="other02.jsp"></jsp:forward>
```

   	前往哪一个页面。  


```jsp
<% 	request.getRequestDispatcher("other02.jsp").forward(request, response);
%>	
```

3. `jsp:param`

   意思是： 在包含某个页面的时候，或者在跳转某个页面的时候，加入这个参数。

```jsp
<jsp:forward page="other02.jsp">
    <jsp:param value="Beijing" name="Lightwing"/>
</jsp:forward>
```

​	在 `other02.jsp` 中获取参数


```jsp
<br>收到的参数是：<br>
<%= request.getParameter("address")%>
```

#### 1.5 JSP 内置对象(**)

​	所谓内置对象，就是我们可以直接在 JSP 页面中使用这些对象。 不用创建。

- pageContext
- request
- session
- application

以上4个是作用域对象 , 

##### 1.5.1 作用域 

表示这些对象可以存值，他们的取值范围有限定。`setAttribute` 和 `getAttribute`


```jsp
使用作用域来存储数据<br>
<%
    pageContext.setAttribute("name", "page");
    request.setAttribute("name", "request");
    session.setAttribute("name", "session");
    application.setAttribute("name", "application");
%>

取出四个作用域中的值<br>
<%=pageContext.getAttribute("name")%>
<%=request.getAttribute("name")%>
<%=session.getAttribute("name")%>
<%=application.getAttribute("name")%>
```

运行效果

```
这是other03的页面  
使用作用域来存储数据 
取出四个作用域中的值 
page request session application
```

作用域范围大小：

`pageContext > request > session > application` 

##### 1.5.2 四个作用域的区别

`other04.jsp`

```jsp
<body>
<h3>这是04的页面</h3><br>
取出四个作用域中的值<br>
<%=pageContext.getAttribute("name")%>
<%=request.getAttribute("name")%>
<%=session.getAttribute("name")%>
<%=application.getAttribute("name")%>
</body>
```

运行测试

```html
<body>
    
<h3>这是04的页面</h3><br>

取出四个作用域中的值<br>

null
null
session
application



</body>
```

1. pageContext (PageContext)

- 作用域仅限于当前的页面
- 还可以获取到其它八个内置对象

2. request (`HttpServletRequest`)

作用域仅限于一次请求， 只要服务器对该请求做出了响应。 这个域中存的值就没有了。

3. session (`HttpSession`)

作用域限于一次会话（多次请求与响应） 当中。 

4. application (`ServletContext`)

整个工程都可以访问， 服务器关闭后就不能访问了。 


- out		 (JspWriter)
  - response  (HttpServletResponse)

![icon](img/img01.png)


- exception  (Throwable)
  - page	 (Object) ---就是这个jsp翻译成的java类的实例对象
    - config (ServletConfig)

### 2 EL

​	表达式语言（Expression Language），或称 EL 表达式，简称 EL，是 Java 中的一种特殊的通用编程语言，借鉴于 JavaScript 和 XPath。主要作用是在 Java Web 应用程序嵌入到网页（如 JSP）中，用以访问页面的上下文以及不同作用域中的对象 ，取得对象属性的值，或执行简单的运算或判断操作。EL 在得到某个数据时，会自动进行数据类型的转换。

#### 语法

以「`${`」开始，以「`}`」作为结束：

```jsp
${ EL表达式 }
```

如果从作用域中取值，会先从小的作用域开始取，如果没有，就往下一个作用域取。  一直把四个作用域取完都没有， 就没有显示。

```jsp
${ user.name }
${ user.age }
```

#### 2.1 如何使用

##### 2.1.1 取出 4 个作用域中存放的值。


```jsp
<%
    pageContext.setAttribute("name", "page");
    request.setAttribute("name", "request");
    session.setAttribute("name", "session");
    application.setAttribute("name", "application");
%>

按普通手段取值<br>

<%= pageContext.getAttribute("name")%>
<%= request.getAttribute("name")%>
<%= session.getAttribute("name")%>
<%= application.getAttribute("name")%>

<br>使用 EL 表达式取出作用域中的值<br>

${ pageScope.name }
${ requestScope.name }
${ sessionScope.name }
${ applicationScope.name }
```

##### 2.2.2 如果域中所存的是数组

```jsp
<%
    String[] a = {"aa", "bb", "cc", "dd"};
    pageContext.setAttribute("array", a);
%>
```

```jsp
使用EL表达式取出作用域中数组的值<br>
${ array[0] }, ${ array[1] }, ${ array[2] }, ${ array[3] }
```
##### 2.2.3 如果域中锁存的是集合

```jsp
<%
    List list = new ArrayList();
    list.add("11");
    list.add("22");
    list.add("33");
    list.add("44");
    session.setAttribute("li", list);
%>
使用EL表达式取出作用域中集合的值<br>
${ li[0] } , ${ li[1] }, ${ li[2] }, ${ li[7] }
```
4. 取出Map集合的值

```jsp
<%
    Map map = new HashMap();
    map.put("name", "zhangsna");
    map.put("age", 18);
    map.put("address", "北京..");
    map.put("address.aa", "深圳..");

    application.setAttribute("m", map);
%>
使用EL表达式取出作用域中Map的值<br>
${ applicationScope.m.name }, ${ m.age } , ${ m.address } , ${ m["address.aa"]}
```

##### 2.2.4 取值细节

1. 从域中取值，得先存值

```jsp
<%
    // pageContext.setAttribute("name", "zhangsan");
    session.setAttribute("name", "lisi...");
%>

<br>直接指定说了，到这个作用域里面去找这个name<br>
${ pageScope.name }


<br>先从page里面找，没有去request找，去session，去application <br>
${ name }

<br>指定从session中取值<br>
${ sessionScope.name }
```

2. 取值方式

如果这份值是有下标的，那么直接使用[]

```jsp
<%
    String[] array = {"aa", "bb", "cc"};
    session.setAttribute("array", array);
%>
${ array[1] } --> 这里 array 说的是 attribute 的 name
```

如果没有下标， 直接使用 .的方式去取


```jsp
<%
    User user = new User("zhangsan", 18);
    session.setAttribute("u", user);
%>

${ u.name }, ${ u.age }
```

​	一般使用 EL 表达式，用的比较多的，都是从一个对象中取出它的属性值，比如取出某一个学生的姓名。

#### 2.2 EL 表达式 的 11 个内置对象

${ 对象名.成员 }
1. pageContext 

作用域相关对象
2. pageScope
3. requestScope
4. sessionScope
5. applicationScope

头信息相关对象
6. header
7. headerValues

参数信息相关对象
8. param
9. paramValues
10. cookie

全局初始化参数
11. initParam

### 3 JSTL

​	JSP **标准标签库**（JSP Standard Tag Library）是 Java EE 网络应用程序开发平台的组成部分。它在 JSP 规范的基础上，扩充了一个 JSP 的标签库来完成一些通用任务，比如 XML 数据处理、条件执行、数据库访问、循环和国际化。

​	简化 JSP 的代码编写，替换 `<%%>` 写法。 一般与 EL 表达式配合

#### 3.1 怎么使用

1. 导入 jar 文件到工程的 `WebContent/Web-Inf/lib  `

   `jstl.jar `

   `standard.jar`

2. 在 JSP 页面上，使用 `taglib` 指令，来引入标签库

3. 注意： 如果想支持 EL 表达式，那么引入的标签库必须选择1.1的版本，1.0的版本不支持EL表达式。


```jsp
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
```


3.2 常用 JSTL 标签

```jsp
c:set></c:set>
<c:if test=""></c:if>
<c:forEach></c:forEach>>
```


1. `c:set`

声明一个对象 name， 对象的值 zhangsan , 存储到了page（默认） ， 指定是session


```jsp
<c:set var="name" value="zhangsan" scope="session"></c:set>
${ sessionScope.name }
```


2. `c:if`

判断 `test` 里面的表达式是否满足，如果满足，就执行 `c:if` 标签中的输出 ， c:if 是没有else的。 

```jsp
<c:if test="${ age > 16 }">
    你已经年满 16 岁，可以试驾<strong>顶配辉腾</strong>。
</c:if>
```


定义一个变量名 `flag` 去接收前面表达式的值，然后存在 `session` 域中

```jsp
<c:if test="${ age > 26 }" var="flag" scope="session">
	年龄大于了26岁...
</c:if>
```


3. `c:forEach`

		从 1 开始遍历到 10 ，得到的结果 ，赋值给 `i` ，并且会存储到 page 域中， step , 增幅为2， 

```jsp
<c:forEach begin="1" end="10" var="i" step="2">
	${ i }
</c:forEach>
```




```jsp
<!-- items : 表示遍历哪一个对象，注意，这里必须写EL表达式。 
var: 遍历出来的每一个元素用user 去接收。 -->
<c:forEach var="user" items="${list }">
	${ user.name } ----${user.age }
</c:forEach>
```

### 4 学生信息管理系统

#### 4.1 步骤分析

登陆页面

![icon](/Users/lightwingng/Desktop/Java学习笔记/JSP & EL & JSTL/login.png)


1. 先写 `login.jsp`, 并且搭配一个 `LoginServlet` 去获取登录信息，项目结构应如下：

```powershell
.
├── .idea
│   ├── artifacts
│   │   └── StuManager_war_exploded.xml
│   ├── misc.xml
│   ├── modules.xml
│   └── workspace.xml
├── StuManager.iml
├── out
│   ├── artifacts
│   │   └── StuManager_war_exploded
│   │       ├── WEB-INF
│   │       │   ├── classes
│   │       │   │   ├── com
│   │       │   │   │   └── lightwing
│   │       │   │   │       ├── dao
│   │       │   │   │       │   ├── StuDao.class
│   │       │   │   │       │   ├── UserDao.class
│   │       │   │   │       │   └── impl
│   │       │   │   │       │       ├── StuDaoImpl.class
│   │       │   │   │       │       └── UserDaoImpl.class
│   │       │   │   │       ├── domain
│   │       │   │   │       │   └── Student.class
│   │       │   │   │       ├── servlet
│   │       │   │   │       │   └── LoginServlet.class
│   │       │   │   │       └── util
│   │       │   │   │           └── JDBCUtil.class
│   │       │   │   └── jdbc.properties
│   │       │   ├── lib
│   │       │   │   ├── jstl.jar
│   │       │   │   ├── mysql-connector-java-5.1.7-bin.jar
│   │       │   │   └── standard.jar
│   │       │   └── web.xml
│   │       ├── index.jsp
│   │       ├── login.jsp
│   │       ├── static
│   │       │   └── imgs
│   │       │       └── MIT_Seal.svg
│   │       └── stu_list.jsp
│   └── production
│       └── StuManager
│           ├── com
│           │   └── lightwing
│           │       ├── dao
│           │       │   ├── StuDao.class
│           │       │   ├── UserDao.class
│           │       │   └── impl
│           │       │       ├── StuDaoImpl.class
│           │       │       └── UserDaoImpl.class
│           │       ├── domain
│           │       │   └── Student.class
│           │       ├── servlet
│           │       │   └── LoginServlet.class
│           │       └── util
│           │           └── JDBCUtil.class
│           └── jdbc.properties
├── src
│   ├── com
│   │   ├── .DS_Store
│   │   └── lightwing
│   │       ├── dao
│   │       │   ├── StuDao.java
│   │       │   ├── UserDao.java
│   │       │   └── impl
│   │       │       ├── StuDaoImpl.java
│   │       │       └── UserDaoImpl.java
│   │       ├── domain
│   │       │   └── Student.java
│   │       ├── servlet
│   │       │   └── LoginServlet.java
│   │       └── util
│   │           └── JDBCUtil.java
│   └── jdbc.properties
└── web
    ├── .DS_Store
    ├── WEB-INF
    │   ├── lib
    │   │   ├── jstl.jar
    │   │   ├── mysql-connector-java-5.1.7-bin.jar
    │   │   └── standard.jar
    │   └── web.xml
    ├── index.jsp
    ├── login.jsp
    ├── static
    │   ├── css
    │   └── imgs
    │       └── MIT_Seal.svg
    └── stu_list.jsp

```


2. 创建用户表 `t_user`， 里面只要有 `id` , `username` 和 `password` 等三个字段

```powershell
+--------+--------------+----------------------+
| id     | username     | password             |
+--------+--------------+----------------------+
|      1 | admin        | admin                |
| 105983 | Zeb_Atlas    | ShJV3e^LvTO@9YUdjfKM |
| 113876 | Arpad_Miklos | ltKzhqel+f$2Y!Q4EXVX |
| 264669 | Li_Si        | 2u!u^_O3uWN3B1q?FywQ |
| 431369 | Zhang_San    | 6~g@ad5-CDVxFrO@Tn8S |
| 751302 | Lee_Kaifu    | hr?y6QIsjZdjT*g^Sg3o |
| 949457 | Erik_Rhode   | h8JhYJ_HU=z-KN_v3^b^ |
+--------+--------------+----------------------+
```
3. 创建 `UserDao`, 定义登录的方法 

```java
package com.lightwing.dao;

public interface UserDao {
    /**
     * 这里简单就返回一个 Boolean 类型， 成功或者失败即可。
     * 但是开发的时候，登录的方法，一旦成功。这里应该返回该用户的个人信息
     *
     * @param userName
     * @param password
     * @return true : 登录成功， false : 登录失败。
     */
    boolean login(String userName, String password);
}
```

4. 创建 `UserDaoImpl.java`, 实现刚才定义的登录方法。


```java
package com.lightwing.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

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

public class UserDaoImpl implements UserDao {

    @Override
    public boolean login(String userName, String password) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs   = null;
        
        try {
            // 1. 得到连接对象
            conn = JDBCUtil.getConn();
            String sql = "select * from t_user where username=? and password=?";
            // 2. 创建ps对象
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
```

5. 在 `LoginServlet` 里面访问 `UserDao`， 判断登录结果。 以区分对待
6. 创建 `stu_list.jsp` , 让登录成功的时候跳转过去。

```jsp
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
```

7. 创建学生表，里面字段随机生成

```powershell
mysql> SELECT id, name, age, gender FROM t_stu;
+---------+------------------+-----+--------+
| id      | name             | age | gender |
+---------+------------------+-----+--------+
| 3122170 | Sam Wells        |  27 | Male   |
| 3341721 | Cameron Matthews |  23 | Male   |
| 3629184 | Brynn Kinney     |  29 | Female |
| 4394504 | Lynn Michael     |  26 | Male   |
| 4857327 | Terry Duffy      |  29 | Male   |
| 5014210 | Phoenix Stein    |  21 | Female |
| 5434036 | Leslie Maldonado |  27 | Female |
| 5467071 | Denny Andrews    |  20 | Male   |
| 7692938 | Adelynn Chapman  |  28 | Female |
| 9517473 | Blake Dickerson  |  25 | Female |
+---------+------------------+-----+--------+
10 rows in set (0.00 sec)
```

8. 定义学生的 `StuDao.java`  


```java
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
```


9. 对上面定义的 `StuDao` 做出实现 `StuDaoImpl.java`

```java
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
		
```

10. 在登录成功的时候，完成三件事情。
    a. 查询所有的学生
    b. 把这个所有的学生集合存储到作用域中。
    c. 跳转到 `stu_list.jsp`


```java
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // 提交的数据有可能有中文， 怎么处理。
    request.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;charset=utf-8");
    // 1. 获取客户端提交的信息
    String userName = request.("username");
    String password = request.getParameter("password");
    // 2. 去访问dao ， 看看是否满足登录。
    UserDao dao = new UserDaoImpl();
    boolean isSuccess = dao.login(userName, password);
    
    // 3. 针对dao的返回结果，做出响应
    if (isSuccess) {
        // response.getWriter().write("登录成功.");
        // 1. 查询出来所有的学生信息。
        StuDao stuDao = new StuDaoImpl();
        List<Student> list = stuDao.findAll();
        // 2. 先把这个集合存到作用域中。
        request.getSession().setAttribute("list", list);
        // 2. 重定向
        response.sendRedirect("stu_list.jsp");
    } else
        response.getWriter().write("用户名或者密码错误！");
}
```

11. 在 `stu_list.jsp` 中，取出域中的集合，然后使用 c 标签 去遍历集合


```jsp
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
```

#### 4.2 运行结果

![icon](/Users/lightwingng/Desktop/Java学习笔记/JSP & EL & JSTL/information.png)





​	