<%--
  Created by IntelliJ IDEA.
  User: admmj
  Date: 21.09.2020
  Time: 14:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<p><a href="${pageContext.request.contextPath}/">На главную</a></p>

<h2>Список товаров</h2>

<input type="button" value="Добавить новый товар" onclick="window.location.href='formForAddProduct'; return false;" class="add-button" />

<br><br>

<form:form action="search" method="GET">
    Поиск по базе: <input type="text" name="searchValue" />
    <input type="submit" value="Search" class="add-button" />
</form:form>

<table>
    <tr>
        <th>Наименование товара</th>
        <th>Тип</th>
        <th>Бренд</th>
        <th>Страна</th>
        <th>Скидка</th>
        <th>Цена</th>
        <th>Цена с учетом скидки</th>
        <th>Количество</th>
        <th>Обновить</th>
        <th>Удалить</th>
    </tr>
    <c:forEach var="tmpProduct" items="${products}">
        <c:url value="/product/formForUpdateProduct" var="updateLink">
            <c:param name="productId" value="${tmpProduct.id}" />
            <c:param name="brandId" value="${tmpProduct.brand.id}" />
            <c:param name="countryId" value="${tmpProduct.brand.country.id}" />
            <c:param name="typeId" value="${tmpProduct.type.id}" />
        </c:url>
        <c:url value="/product/delete" var="deleteLink">
            <c:param name="productId" value="${tmpProduct.id}" />
        </c:url>
        <tr>
            <td> ${tmpProduct.name} </td>
            <td> ${tmpProduct.type.name} </td>
            <td> ${tmpProduct.brand.name} </td>
            <td> ${tmpProduct.brand.country.name} </td>
            <td> ${tmpProduct.brand.sale} </td>
            <td> ${tmpProduct.price} </td>
            <td> ${tmpProduct.price*(100-tmpProduct.brand.sale)/100} </td>
            <td> ${tmpProduct.amount} </td>
            <td> <a href="${updateLink}">Обновить</a> </td>
            <td> <a href="${deleteLink}" onclick="if (!(confirm('Вы уверены?'))) return false">Удалить</a> </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
