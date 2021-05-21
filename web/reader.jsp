<%@ page import="smytsyk.finalProject.library.service.I18N" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tf"%>
<html>
<body>
    <tf:logout/>
    <tf:tagError/>
    <tf:changeRegInfoReader/>

    <form action="Controller" method="get">
    <input type="hidden" name="command" value="go_to_reader_orders_page">
    <button type="submit"><%=I18N.translate("go_to_reader_orders_page", session)%></button>
    </form>

    <form action="Controller" method="get">
        <input type="hidden" name="command" value="go_to_catalog_page">
        <button type="submit"><%=I18N.translate("go_to_catalog_page", session)%></button>
    </form>
</body>
</html>
