<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>

<html>
  <body>
    <h1>Add New House</h1>
    <form method="post" action="saveHouse">
      <table>
        <tr>
          <td>address</td>
          <td><input name="address" type="text"></td>
        </tr>
        <tr>
          <td>color</td>
          <td><input name="color" type="text"></td>
        </tr>
      </table>
      <input type="submit" value="submit">
    </form>
  </body>
</html>
