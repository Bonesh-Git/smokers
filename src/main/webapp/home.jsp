<%--
  Created by IntelliJ IDEA.
  User: farzin
  Date: 4/1/25
  Time: 2:42 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%
    request.setAttribute("pageTitle", "خانه");
%>

<jsp:include page="/WEB-INF/templates/hearder.jsp" />

<div class="centered-container">
    <h2>خوش آمدید</h2>
    <p>به صفحه اصلی وارد شدید.</p>
    <br><br><br>
    <h1>پروژه کلاسی جاوا حرفه‌ای</h1>
    <h2>گروه دخانیات - مجتمع فنی تهران</h2>
    <p>این پروژه در دست تکمیل است.</p>
    <br>
    <p>${var_in_bag}</p>
    <form action="http://localhost:8010/Bank_Project_Java_war_exploded/home" method="get">
        <input type="text" placeholder="نام کاربری" name="uName">
        <input type="text" placeholder="کلمه عبور" name="pWord">
        <input type="submit">
    </form>

</div>

<jsp:include page="/WEB-INF/templates/footer.jsp" />

</body>
</html>