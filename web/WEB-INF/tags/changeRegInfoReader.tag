<%@ tag import="smytsyk.final_project.library.entitiy.User" %>
<%@ tag import="smytsyk.final_project.library.service.I18N" %>
<%!User user;%>
<%user = ((User)session.getAttribute("user"));%>

<form action="Controller" method="post">
    <input type="hidden" name="command" value="update_reg_reader">
    <input type="hidden" name="user_login" value=<%=user.getLogin()%>>>
    <input type="hidden" name="user_id" value=<%=user.getId()%>>
    <table>
        <tr>
            <td><%=I18N.translate("password", session)%></td>
            <td><input type="password" name="password" value=<%=user.getPassword()%>></td>
        </tr>
        <tr>
            <td><%=I18N.translate("first_name", session)%></td>
            <td><input type="text" name="first_name" value=<%=user.getFirstName()%>></td>
        </tr>
        <tr>
            <td><%=I18N.translate("last_name", session)%></td>
            <td><input type="text" name="last_name" value=<%=user.getLastName()%>></td>
        </tr>
        <tr>
            <td><%=I18N.translate("email", session)%></td>
            <td><input type="text" name="email" value=<%=user.getEmail()%>></td>
        </tr>
        <tr>
            <td></td>
            <td>
                <button type="submit"><%=I18N.translate("update_reg", session)%></button>
            </td>
        </tr>
    </table>
</form>