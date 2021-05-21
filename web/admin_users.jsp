<%@ page import="smytsyk.finalProject.library.service.I18N" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tf"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<tf:logout/>
<tf:tagError/>

<form action="Controller" method="get">
    <input type="hidden" name="command" value="return_to_admin_page">
    <button type="submit"><%=I18N.translate("return", session)%></button>
</form>

<table>
    <tr>
        <td>Id</td>
        <td><%=I18N.translate("login", session)%></td>
        <td><%=I18N.translate("first_name", session)%></td>
        <td><%=I18N.translate("last_name", session)%></td>
        <td><%=I18N.translate("email", session)%></td>
        <td></td>
        <td></td>
    </tr>
    
    <c:choose>
        <c:when test="${users.isEmpty()}">
            <tr>
                <td>
                    <%=I18N.translate("no_users", session)%>
                </td>
            </tr>
        </c:when>
        <c:otherwise>
            <c:forEach var="user1" items="${users}">
                <tr>
                    <td>${user1.getId()}</td>
                    <td>${user1.getLogin()}</td>
                    <td>${user1.getFirstName()}</td>
                    <td>${user1.getLastName()}</td>
                    <td>${user1.getEmail()}</td>

                    <form action="Controller" method="post">
                        <c:choose>
                            <c:when test="${user1.getRoleId() == 0}">
                                <input type="hidden" name="command" value="unban">
                            </c:when>
                            <c:otherwise>
                                <input type="hidden" name="command" value="ban">
                            </c:otherwise>
                        </c:choose>

                        <input type="hidden" name="user_id" value=${user1.getId()}>
                        <td>
                            <c:choose>
                                <c:when test="${user1.getRoleId() == 0}">
                                    <button type="submit"> <%=I18N.translate("unban", session)%> </button>
                                </c:when>
                                <c:otherwise>
                                    <button type="submit"> <%=I18N.translate("ban", session)%> </button>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </form>

                    <form action="Controller" method="post">
                        <c:choose>
                            <c:when test="${user1.getRoleId() == 2}">
                                <input type="hidden" name="command" value="dismiss">
                            </c:when>
                            <c:otherwise>
                                <input type="hidden" name="command" value="appoint">
                            </c:otherwise>
                        </c:choose>
                        <input type="hidden" name="user_id" value=${user1.getId()}>
                        <td>
                            <c:choose>
                                <c:when test="${user1.getRoleId() == 2}">
                                    <button type="submit"> <%=I18N.translate("dismiss", session)%> </button>
                                </c:when>
                                <c:otherwise>
                                    <button type="submit"> <%=I18N.translate("appoint", session)%> </button>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </form>
                </tr>
            </c:forEach>
        </c:otherwise>
    </c:choose>
</table>

<br>
<table>
    <tr>
        <c:if test="${pageNum > 0}">
            <form action="/Controller" method="get">
                <input type="hidden" name="command" value="go_to_admin_users_page">
                <input type="hidden" name="page" value="${pageNum - 1}">

                <td>
                    <button type="submit"><%=I18N.translate("prev", session)%></button>
                </td>
            </form>
        </c:if>
        <td>${pageNum}</td>
        <form action="/Controller" method="get">
            <input type="hidden" name="command" value="go_to_admin_users_page">
            <input type="hidden" name="page" value="${pageNum + 1}">

            <td>
                <button type="submit"><%=I18N.translate("next", session)%></button>
            </td>
        </form>
    </tr>
</table>

</body>
</html>