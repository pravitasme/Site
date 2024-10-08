<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>

<html>
    <body>
        <h1>Add New User</h1>
        <form method="post" action="save">
            <table>
                <tr>
                    <td>first name</td>
                    <td><input name="firstname" type="text"></td>
                </tr>
                <tr>
                    <td>last name</td>
                    <td><input name="lastname" type="text"></td>
                </tr>
                <tr>
                    <td>patronymic</td>
                    <td><input name="patronymic" type="text"></td>
                </tr>
            </table>
            <input type="submit" value="submit">
        </form>
    </body>
</html>

