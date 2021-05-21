<%@ page import="smytsyk.finalProject.library.service.I18N" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tf"%>
<html>
<body>
    <h1><%=I18N.translate("banned", session)%></h1>
    <tf:logout/>
    <tf:tagError/>
    <tf:changeRegInfoBanned/>
</body>
</html>
