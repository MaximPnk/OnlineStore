<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: admmj
  Date: 25.09.2020
  Time: 13:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>История заказов</title>
</head>
<body>

<c:forEach var="order" items="${orders}">

    <c:forEach var="po" items="${order.productOrderList}">

        ${po.product.name} ${po.price} ${po.count}<br>

    </c:forEach>
    <br><br><br>

</c:forEach>

<a href="${pageContext.request.contextPath}/product/list">К списку товаров</a>

</body>
</html>
