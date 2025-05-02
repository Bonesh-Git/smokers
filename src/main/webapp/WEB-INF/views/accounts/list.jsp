
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>--%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>

<html dir="rtl">
<head>
    <title>لیست حساب‌ها</title>
    <%
        request.setAttribute("pageTitle", "لیست حساب\u200Cها");
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
            <h2 class="fs-4">فهرست حساب‌ها</h2>
        </div>
        <div>

        </div>
        <div class="col text-end d-flex justify-content-end gap-2">
            <a href="javascript:history.back()" class="btn btn-outline-secondary btn-sm">
                <i class="fas fa-arrow-right"></i> بازگشت
            </a>
            <a href="<%= request.getContextPath() %>/accounts/add" class="btn btn-outline-success btn-sm">
                <i class="fas fa-plus"></i> افزودن حساب
            </a>
            <a href="<%= request.getContextPath() %>/accounts/list" class="btn btn-info btn-sm">
                <i class="fas fa-refresh"></i> فراخوانی
            </a>
        </div>
    </div>

    <div class="table-responsive">
        <table class="table table-bordered table-striped table-hover text-center align-middle">
            <thead class="table-dark">
            <tr>
                <th>شناسه</th>
                <th>شماره حساب</th>
                <th>نوع حساب</th>
                <th>تاریخ افتتاح</th>
                <th>کد مالک</th>
                <th>نام مالک</th>
                <th>وضعیت مالک</th>
                <th>عملیات</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="acc" items="${accountList}">
                <tr>
                    <td>${acc.accountUID}</td>
                    <td>${acc.accountNo}</td>
                    <td>${acc.accountType}</td>
                    <td>${acc.openDate}</td>
                    <td>${acc.people.peopleId}</td>
                    <td>${acc.people.peopleName}</td>
                    <td>${acc.people.peopleStatus}</td>
                    <td>
                        <div class="d-flex justify-content-center gap-2 flex-wrap">
                            <a href="<%= request.getContextPath() %>/accounts/more?id=${acc.accountUID}" class="btn btn-outline-primary btn-sm">
                                <i class="fas fa-eye"></i> نمایش
                            </a>
                            <a href="<%= request.getContextPath() %>/accounts/edit?id=${acc.accountUID}" class="btn btn-outline-warning btn-sm">
                                <i class="fas fa-edit"></i> ویرایش
                            </a>
                            <a href="<%= request.getContextPath() %>/accounts/remove?id=${acc.accountUID}" class="btn btn-outline-danger btn-sm"
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

