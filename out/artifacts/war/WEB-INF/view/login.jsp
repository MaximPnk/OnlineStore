<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>

<head>
    <title>Вход</title>
    <style>
        .failed {
            color: red;
        }
        .logout {
            color: greenyellow;
        }
    </style>
</head>

<body>

<h2>Форма входа:</h2>
<hr>

    <form:form action="${pageContext.request.contextPath}/authenticate" method="post">

        <c:if test="${param.error != null}">
            <i class="failed">Неправильный логин или пароль.</i>
        </c:if>
        <c:if test="${param.logout != null}">
            <i class="logout">Вы успешно вышли.</i>
        </c:if>

        <p>Логин: <input type="text" name="username"></p>
        <p>Пароль: <input type="text" name="password"></p>
        <p><input type="submit" value="Login"></p>

    </form:form>

    <security:authorize access="isAnonymous()">
        <p><a href="${pageContext.request.contextPath}/registration/showForm">Регистрация</a></p>
    </security:authorize>


    <p><a href="${pageContext.request.contextPath}/">На главную</a></p>



</body>

</html>