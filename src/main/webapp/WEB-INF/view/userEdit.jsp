<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false"%>

<html>
    <body>
        <h1>Edit User</h1>
        <%--@elvariable id="user" type="ru.lanit.mo.web.entity.UserDTO"--%>
        <form:form method="post" action="/saveEditedUser" modelAttribute="user">
            <table>
                <tr>
                    <td></td>
                    <td><form:hidden path="id"/></td>
                </tr>
                <tr>
                    <td>first name : </td>
                    <td><form:input path="firstname"/></td>
                </tr>
                <tr>
                    <td>last name :</td>
                    <td><form:input path="lastname"/></td>
                </tr>
                <tr>
                    <td>patronymic :</td>
                    <td><form:input path="patronymic"/></td>
                </tr>
                <tr>
                    <td>House Address: </td>
                    <td>
                        <form:select path="house.house_id">
                            <jsp:useBean id="houses" scope="request" type="java.util.List"/>
                            <c:forEach var="item" items="${houses}">
                                <form:option value="${item.house_id}">${item.address} ${item.color}</form:option>
                            </c:forEach>
                        </form:select>
                    </td>
                </tr>

            </table>
            <input type="submit" value="save edited user" />
        </form:form>
    </body>
</html>
