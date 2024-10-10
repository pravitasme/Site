<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<%@ page import="java.util.List" %>
<html>
  <body>
  <h1>Users List</h1>
  <table border="2" width="50%">
    <tr><th>Id</th><th>FirstName</th><th>LastName</th><th>Patronymic</th><th>Edit</th><th>Delete</th></tr>
    <c:forEach var="user" items="${users}">
      <tr>
        <td>${user.id}</td>
        <td>${user.firstname}</td>
        <td>${user.lastname}</td>
        <td>${user.patronymic}</td>
        <td><a href="userEdit/${user.id}">Edit</a></td>
        <td><a href="deleteUser/${user.id}">Delete</a></td>
      </tr>
    </c:forEach>
  </table>
  <br/>
  <a href="userAdd">Add New User</a>
  </body>
</html>
