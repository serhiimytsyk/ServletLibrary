<%@ page import="smytsyk.finalProject.library.service.I18N" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tf"%>
<html>
<body>
    <tf:tagError/>
    <tf:logout/>

    <form action="Controller" method="post">
        <input type="hidden" name="command" value="login">
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
                <td></td>
                <td>
                    <button type="submit"><%=I18N.translate("sign_in", session)%></button>
                </td>
            </tr>
        </table>
    </form>

</body>
</html>
