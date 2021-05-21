<%@ page import="smytsyk.finalProject.library.service.I18N" %>
<%@ page import="smytsyk.finalProject.library.entitiy.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tf"%>

<html>
<body>

<tf:tagError/>
<tf:logout/>
<tf:changeRegInfoAdmin/>

<form action="Controller" method="get">
    <input type="hidden" name="command" value="go_to_admin_users_page">
    <button type="submit"><%=I18N.translate("go_to_admin_users_page", session)%></button>
</form>

<form action="Controller" method="get">
    <input type="hidden" name="command" value="go_to_admin_books_page">
    <button type="submit"><%=I18N.translate("go_to_admin_books_page", session)%></button>
</form>

</body>
</html>
