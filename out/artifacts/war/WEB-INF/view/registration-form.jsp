<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: admmj
  Date: 24.09.2020
  Time: 17:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация</title>
</head>
<body>

    <h2>Регистрация нового пользователя</h2>

    <br><br>

    <form:form action="${pageContext.request.contextPath}/registration/registrationProcess" modelAttribute="validUser">

        <c:if test="${registrationError != null}">
            <p>${registrationError}</p>
        </c:if>

        Логин: <form:input path="userName" /><form:errors cssClass="error" />
        <br><br>
        Пароль: <form:input path="password" /><form:errors cssClass="error" />
        <br><br>
        Имя: <form:input path="firstName" /><form:errors cssClass="error" />
        <br><br>
        Фамилия: <form:input path="lastName" /><form:errors cssClass="error" />
        <br><br>
        Эл. почта: <form:input path="email" /><form:errors cssClass="error" />
        <br><br>
        <button type="submit">Зарегистрироваться</button>

    </form:form>

</body>
</html>
