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
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<p><a href="${pageContext.request.contextPath}/">На главную</a></p>

<security:authorize access="isAnonymous()">
    <p><a href="${pageContext.request.contextPath}/registration/showForm">Регистрация</a></p>
</security:authorize>

<security:authorize access="hasAnyRole('ADMIN', 'CLIENT')" >
    <form:form action="${pageContext.request.contextPath}/logout" method="POST">
        <input type="submit" value="Выйти" />
    </form:form>
</security:authorize>

<p><a href="/basket">Корзина</a></p>

<h2>Список товаров</h2>

<security:authorize access="hasRole('ADMIN')" >
    <input type="button" value="Добавить новый товар" onclick="window.location.href='${pageContext.request.contextPath}/admin/formForAddProduct'; return false;" class="add-button" />
</security:authorize>

<br><br>

<form:form action="search" method="GET">
    <h4>Фильтр</h4>
    Наименование: <input type="text" name="product" />
    Тип: <input type="text" name="type" />
    Бренд: <input type="text" name="brand" />
    Страна: <input type="text" name="country" />
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
        <security:authorize access="hasRole('CLIENT')">
            <th>Добавить в заказ</th>
        </security:authorize>
        <security:authorize access="hasRole('ADMIN')">
            <th>Обновить</th>
            <th>Удалить</th>
        </security:authorize>
    </tr>
    <c:forEach var="tmpProduct" items="${products}">
        <c:url value="/admin/formForUpdateProduct" var="updateLink">
            <c:param name="productId" value="${tmpProduct.id}" />
            <c:param name="brandId" value="${tmpProduct.brand.id}" />
            <c:param name="countryId" value="${tmpProduct.brand.country.id}" />
            <c:param name="typeId" value="${tmpProduct.type.id}" />
        </c:url>
        <c:url value="/admin/delete" var="deleteLink">
            <c:param name="productId" value="${tmpProduct.id}" />
        </c:url>
        <c:url value="/order" var="orderLink">
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
            <security:authorize access="hasRole('CLIENT')">
                <td><a href="${orderLink}">В корзину</a></td>
            </security:authorize>
            <security:authorize access="hasRole('ADMIN')">
                <td> <a href="${updateLink}">Обновить</a> </td>
                <td> <a href="${deleteLink}" onclick="if (!(confirm('Вы уверены?'))) return false">Удалить</a> </td>
            </security:authorize>
        </tr>
    </c:forEach>
</table>
</body>
</html>
