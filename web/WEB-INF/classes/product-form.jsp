<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: admmj
  Date: 21.09.2020
  Time: 17:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Создание нового товара</title>
    <style>
        .error {
            color: red;
        }
    </style>
</head>
<body>
<h2>Введите данные о товаре</h2>
<form:form action="saveProduct" modelAttribute="validProduct" method="post">

    <table>
        <tbody>
        <tr>
            <td><label>Наименование товара:</label></td>
            <td><form:input path="name" /><form:errors path="name" cssClass="error" /></td>
        </tr>
        <tr>
            <td><label>Название бренда:</label></td>
            <td><form:input path="brandName" /><form:errors path="brandName" cssClass="error" /></td>
        </tr>
        <tr>
            <td><label>Страна производитель:</label></td>
            <td><form:input path="countryName" /><form:errors path="countryName" cssClass="error" /></td>
        </tr>
        <tr>
            <td><label>Тип товара:</label></td>
            <td><form:input path="typeName" /><form:errors path="typeName" cssClass="error" /></td>
        </tr>
        <tr>
            <td><label>Скидка:</label></td>
            <td><form:input path="brandSale" /><form:errors path="brandSale" cssClass="error" /></td>
        </tr>
        <tr>
            <td><label>Цена:</label></td>
            <td><form:input path="price" /><form:errors path="price" cssClass="error" /></td>
        </tr>
        <tr>
            <td><label>Количество:</label></td>
            <td><form:input path="amount" /><form:errors path="amount" cssClass="error" /></td>
        </tr>
        <tr>
            <td><label></label></td>
            <td><input type="submit" value="Сохранить" class="save" /></td>
        </tr>
        </tbody>
    </table>

    <p>
        <a href="${pageContext.request.contextPath}/product/list">К списку товаров</a>
    </p>
</form:form>
</body>
</html>
