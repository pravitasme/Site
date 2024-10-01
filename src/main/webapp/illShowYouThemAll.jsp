<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html>

<html>
<head>
    <title>It's them</title>
</head>
<body>
    <sql:setDataSource var="db" driver="org.postgresql.Driver"
                       url="jdbc:postgresql://localhost:5432/postgres"
                       user="postgres" password="1324"/>
    <sql:query dataSource="${db}" var="result">
        select * from users;
    </sql:query>
    <c:forEach var="user" items="result.rows">
        ${user.id} ${user.firstname}<br>
    </c:forEach>
</body>
</html>
