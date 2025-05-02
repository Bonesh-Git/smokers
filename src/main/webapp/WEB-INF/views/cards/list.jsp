
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>--%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>

<html dir="rtl">
<head>
    <title>لیست کارت‌های بانکی</title>
    <%
        request.setAttribute("pageTitle", "لیست کارتها\u200Cها");
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
            <h2 class="fs-4">فهرست کارت‌های بانکی</h2>
        </div>
        <div class="col text-end d-flex justify-content-end gap-2">
            <a href="javascript:history.back()" class="btn btn-outline-secondary btn-sm">
                <i class="fas fa-arrow-right"></i> بازگشت
            </a>
            <a href="<%= request.getContextPath() %>/cards/add" class="btn btn-outline-success btn-sm">
                <i class="fas fa-plus"></i> افزودن کارت
            </a>
            <a href="<%= request.getContextPath() %>/cards/list" class="btn btn-info btn-sm">
                <i class="fas fa-refresh"></i> فراخوانی
            </a>
        </div>
    </div>

    <div class="table-responsive">
        <table class="table table-bordered table-striped table-hover text-center align-middle">
            <thead class="table-dark">
            <tr>
                <th>شناسه</th>
                <th>شماره کارت</th>
                <th>وضعیت کارت</th>
                <th>در حال کار؟</th>
                <th>تاریخ صدور کارت</th>
                <th>شماره حساب متصل</th>
                <th>نوع حساب متصل</th>
                <th>نام صاحب کارت</th>
                <th>وضعیت صاحب کارت</th>
                <th>عملیات</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="crd" items="${cardsList}">
                <tr>
                    <td>${crd.cardUID}</td>
                    <td>${crd.cardNo}</td>
                    <td>${crd.cardsStatus}</td>
                    <td>${crd.cardsIsActive}</td>
                    <td>${crd.datetimeCrt}</td>
                    <td>${crd.accounts.accountNo}</td>
                    <td>${crd.accounts.accountType}</td>
                    <td>${crd.accounts.people.peopleName}</td>
                    <td>${crd.accounts.people.peopleStatus}</td>
<%--                    <td></td>--%>
<%--                    <td></td>--%>
<%--                    <td></td>--%>
<%--                    <td></td>--%>
                    <td>
                        <div class="d-flex justify-content-center gap-2 flex-wrap">
                            <a href="<%= request.getContextPath() %>/cards/more?id=${crd.cardUID}" class="btn btn-outline-primary btn-sm">
                                <i class="fas fa-eye"></i> نمایش
                            </a>
                            <a href="<%= request.getContextPath() %>/cards/edit?id=${crd.cardUID}" class="btn btn-outline-warning btn-sm">
                                <i class="fas fa-edit"></i> ویرایش
                            </a>
                            <a href="<%= request.getContextPath() %>/cards/remove?id=${crd.cardUID}" class="btn btn-outline-danger btn-sm"
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

