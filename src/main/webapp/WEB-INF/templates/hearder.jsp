<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<head>
    <meta charset="UTF-8">
    <title><%= request.getAttribute("pageTitle") != null ? request.getAttribute("pageTitle") : "صفحه بدون عنوان" %></title>

    <!-- استایل اصلی -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/bootstrap.rtl.css">

    <!-- جاوااسکریپت -->
    <script src="<%= request.getContextPath() %>/assets/js/bootstrap.js" defer></script>

</head>

<!-- header.jsp -->
<header class="container py-3 border-bottom" dir="rtl">
    <div class="d-flex flex-wrap align-items-center justify-content-between">
        <h1 class="fs-3 fw-bold text-primary mb-0">سامانه دخان گروپ</h1>
        <nav>
            <ul class="nav">
                <li class="nav-item">
                    <a class="nav-link text-dark" href="<%= request.getContextPath() %>/">خانه</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-dark" href="<%= request.getContextPath() %>/accounts/list">حساب‌ها</a>
                </li>
            </ul>
        </nav>
    </div>
</header>


