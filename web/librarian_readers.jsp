<%@ page import="smytsyk.finalProject.library.service.I18N" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tf"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
    <tf:logout/>
    <tf:tagError/>

    <form action="Controller" method="get">
        <input type="hidden" name="command" value="return_to_librarian_page">
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
        </tr>
        <c:choose>
            <c:when test="${readers.isEmpty()}">
                <tr>
                    <td>
                        <%=I18N.translate("no_users", session)%>
                    </td>
                </tr>
            </c:when>
            <c:otherwise>
                <c:forEach var="reader" items="${readers}">
                    <tr>
                        <td>${reader.getId()}</td>
                        <td>${reader.getLogin()}</td>
                        <td>${reader.getFirstName()}</td>
                        <td>${reader.getLastName()}</td>
                        <td>${reader.getEmail()}</td>
                        <form action="Controller" method="post">
                            <input type="hidden" name="command" value="go_to_librarian_reader_orders_page">
                            <input type="hidden" name="user_id" value=${reader.getId()}>
                            <td>
                                <button type="submit"> <%=I18N.translate("go_to_librarian_reader_orders_page", session)%> </button>
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
                    <input type="hidden" name="command" value="go_to_librarian_readers_page">
                    <input type="hidden" name="page" value="${pageNum - 1}">

                    <td>
                        <button type="submit"><%=I18N.translate("prev", session)%></button>
                    </td>
                </form>
            </c:if>
            <td>${pageNum}</td>
            <form action="/Controller" method="get">
                <input type="hidden" name="command" value="go_to_librarian_readers_page">
                <input type="hidden" name="page" value="${pageNum + 1}">

                <td>
                    <button type="submit"><%=I18N.translate("next", session)%></button>
                </td>
            </form>
        </tr>
    </table>
</body>
</html>
