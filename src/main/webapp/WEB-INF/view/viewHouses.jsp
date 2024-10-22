<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<%@ page import="java.util.List" %>
<html>
    <body>
        <h1>Houses List</h1>
        <table border="2" width="50%">
            <tr><th>Id</th><th>Address</th><th>Color</th><th>Delete</th></tr>
            <c:forEach var="house" items="${houses}">
                <tr>
                    <td>${house.house_id}</td>
                    <td>${house.address}</td>
                    <td>${house.color}</td>
                    <td><a href="deleteHouse/${house.house_id}">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
        <br/>
        <a href="houseAdd">Add New House</a>
        <br/>
        <a href="mainPage">Main page</a>
    </body>
</html>
