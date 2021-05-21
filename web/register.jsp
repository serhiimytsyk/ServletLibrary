<%@ page import="smytsyk.finalProject.library.service.I18N" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tf"%>
<html>
<body>
    <tf:tagError/>
    <tf:logout/>

    <form action="Controller" method="post">
        <input type="hidden" name="command" value="register">
        <table>
            <tr>
                <td><%=I18N.translate("login", session)%></td>
                <td><input type="text" name="login"></td>
            </tr>
            <tr>
                <td><%=I18N.translate("password", session)%></td>
                <td><input type="password" name="password"></td>
            </tr>
            <tr>
                <td><%=I18N.translate("first_name", session)%></td>
                <td><input type="text" name="first_name"></td>
            </tr>
            <tr>
                <td><%=I18N.translate("last_name", session)%></td>
                <td><input type="text" name="last_name"></td>
            </tr>
            <tr>
                <td><%=I18N.translate("email", session)%></td>
                <td><input type="text" name="email"></td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <button type="submit"><%=I18N.translate("register", session)%></button>
                </td>
            </tr>
        </table>
    </form>

</body>
</html>
