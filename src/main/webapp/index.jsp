<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>صفحه اصلی</title>
    <%
        request.setAttribute("pageTitle", "صفحه اصلی");
    %>
    <jsp:include page="/WEB-INF/templates/hearder.jsp" />

    <style>
        body, html {
            height: 100%;
            margin: 0;
        }
        .centered-container {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100%;
            text-align: center;
        }
        a {
            margin-top: 20px;
            text-decoration: none;
            color: blue;
            font-size: 18px;
        }
    </style>
</head>
<body>
<div class="centered-container">
    <h1>
        <%= "HOME PAGE" %>
    </h1>
    <br/>
    <ul>
        <br/>
        <li><a href="/Dokhan_Group_war_exploded/people/list">لیست اشخاص</a></li>
        <li><a href="/Dokhan_Group_war_exploded/accounts/list">لیست حساب‌ها</a></li>
        <li><a href="/Dokhan_Group_war_exploded/cards/list">لیست کارت‌ها</a></li>
        <br/>
        <br/>
        <li><a href="${pageContext.request.contextPath}/demo/load-sample-data">ایجاد داده‌های نمونه</a></li>

    </ul>
</div>
</body>

<jsp:include page="/WEB-INF/templates/footer.jsp" />

</html>