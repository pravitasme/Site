<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.lanit.mo.web.entity.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>

<html>
<head>
    <title>It's them</title>
</head>
<body>
    <table border="1" width="50%">
        <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Patronymic</th>
        </tr>
        <c:forEach var="table" items="${exportData}">
            <tr>
                <td><c:out value="${table.firstname}"/></td>
                <td><c:out value="${table.lastname}"/></td>
                <td><c:out value="${table.patronymic}"/></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
