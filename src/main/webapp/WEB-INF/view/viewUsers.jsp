<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<%@ page import="java.util.List" %>
<html>
  <body>
  <h1>Users List</h1>
  <table border="2" width="70%" cellpadding="2">
    <tr><th>Id</th><th>FirstName</th><th>LastName</th><th>Patronymic</th><th>Edit</th><th>Delete</th></tr>
    <c:forEach var="emp" items="${list}">
      <tr>
        <td>${emp.id}</td>
        <td>${emp.firstname}</td>
        <td>${emp.lastname}</td>
        <td>${emp.patronymic}</td>
        <td><a href="editUser/${emp.id}">Edit</a></td>
        <td><a href="deleteUser/${emp.id}">Delete</a></td>
      </tr>
    </c:forEach>
  </table>
  <br/>
  <a href="addUser">Add New User</a>
  </body>
</html>
