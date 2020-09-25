<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: admmj
  Date: 24.09.2020
  Time: 18:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<c:forEach var="productOrder" items="${currentProducts}">
    ${productOrder.product.name} ${productOrder.product.brand.name} ${productOrder.product.type.name} ${productOrder.product.brand.country.name} ${productOrder.count} ${productOrder.price}<br>
</c:forEach>

<br><br>

<c:if test="${currentProducts.size() > 0}">
    <p>Сумма заказа = ${sum} <a href="${pageContext.request.contextPath}/insertDefaultValues">Купить</a></p>
</c:if>

</body>
</html>
