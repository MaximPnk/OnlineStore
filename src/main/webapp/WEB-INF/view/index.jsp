<%--
  Created by IntelliJ IDEA.
  User: admmj
  Date: 21.09.2020
  Time: 13:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
  <head>
    <title>Main Page</title>
  </head>


  <body>


  <h2><a href="${pageContext.request.contextPath}/product/list">Открыть список товаров</a></h2>
  <p><a href="${pageContext.request.contextPath}/insertDefaultValues">Загрузить в базу юзеров и товары (делается 1 раз)</a></p>
  <p>Юзеры: 1) логин: admin, пароль: admin; 2) логин: client, пароль client</p>


  <security:authorize access="hasAnyRole('ADMIN', 'CLIENT')" >
    <form:form action="${pageContext.request.contextPath}/logout" method="POST">
      <input type="submit" value="Выйти" />
    </form:form>
  </security:authorize>

  <security:authorize access="isAnonymous()">
    <p><a href="${pageContext.request.contextPath}/registration/showForm">Регистрация</a></p>
  </security:authorize>


  </body>
</html>
