<%--
  Created by IntelliJ IDEA.
  User: farzin
  Date: 4/18/25
  Time: 10:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>--%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>

<html dir="rtl">
<head>
    <title>لیست اشخاص</title>
    <%
        request.setAttribute("pageTitle", "لیست اشخاص");
    %>
    <jsp:include page="/WEB-INF/templates/hearder.jsp" />
    <!-- استایل گرید -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/bootstrap-grid.rtl.min.css">
    <%--    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">--%>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/fontawesome-free-6.7.2-web/css/all.min.css">

</head>
<body>

<div class="container my-4">
    <div class="row mb-3">
        <div class="col text-start">
            <h2 class="fs-4">فهرست اشخاص</h2>
        </div>
        <div class="col text-end d-flex justify-content-end gap-2">
            <a href="javascript:history.back()" class="btn btn-outline-secondary btn-sm">
                <i class="fas fa-arrow-right"></i> بازگشت
            </a>
            <a href="<%= request.getContextPath() %>/people/add" class="btn btn-outline-success btn-sm">
                <i class="fas fa-plus"></i> افزودن حساب
            </a>
            <a href="<%= request.getContextPath() %>/people/list" class="btn btn-info btn-sm">
                <i class="fas fa-refresh"></i> فراخوانی
            </a>
        </div>
    </div>

    <div class="table-responsive">
        <table class="table table-bordered table-striped table-hover text-center align-middle">
            <thead class="table-dark">
            <tr>
                <th>کد شخص</th>
                <th>شناسه</th>
                <th>نام شخص</th>
                <th>وضعیت شخص</th>
                <th>منتشر شده؟</th>
                <th>عملیات</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="plp" items="${peopleList}">
                <tr>
                    <td>${plp.peopleId}</td>
                    <td>${plp.peopleUID}</td>
                    <td>${plp.peopleName}</td>
                    <td>${plp.peopleStatus}</td>
                    <td>${plp.peopleIsActive}</td>
                    <td>
                        <div class="d-flex justify-content-center gap-2 flex-wrap">
                            <a href="<%= request.getContextPath() %>/people/more?id=${plp.peopleId}" class="btn btn-outline-primary btn-sm">
                                <i class="fas fa-eye"></i> نمایش
                            </a>
                            <a href="<%= request.getContextPath() %>/people/edit?id=${plp.peopleId}" class="btn btn-outline-warning btn-sm">
                                <i class="fas fa-edit"></i> ویرایش
                            </a>
                            <a href="<%= request.getContextPath() %>/people/remove?id=${plp.peopleId}" class="btn btn-outline-danger btn-sm"
                               onclick="return confirm('آیا مطمئنید که می‌خواهید این حساب را حذف کنید؟')">
                                <i class="fas fa-trash-alt"></i> حذف
                            </a>
                        </div>

                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

</body>

<jsp:include page="/WEB-INF/templates/footer.jsp" />

</html>


