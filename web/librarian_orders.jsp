<%@ page import="smytsyk.final_project.library.service.I18N" %>
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
            <td><%=I18N.translate("reader_id", session)%></td>
            <td><%=I18N.translate("book_id", session)%></td>
            <td><%=I18N.translate("return_date", session)%></td>
            <td></td>
            <td></td>
        </tr>
        <c:choose>
            <c:when test="${orders.isEmpty()}">
                <tr>
                    <td>
                        <%=I18N.translate("no_orders", session)%>
                    </td>
                </tr>
            </c:when>
            <c:otherwise>
                <c:forEach var="order" items="${orders}">
                    <tr>
                        <td>${order.getId()}</td>
                        <td>${order.getReaderId()}</td>
                        <td>${order.getBookId()}</td>
                        <td>${order.getReturnDate().format(formatter)}</td>

                        <form action="Controller" method="post">
                            <input type="hidden" name="command" value="accept_order">
                            <input type="hidden" name="order_id" value=${order.getId()}>
                            <td>
                                <button type="submit"> <%=I18N.translate("accept", session)%> </button>
                            </td>
                        </form>

                        <form action="Controller" method="post">
                            <input type="hidden" name="command" value="reject_order">
                            <input type="hidden" name="order_id" value=${order.getId()}>
                            <td>
                                <button type="submit"> <%=I18N.translate("reject", session)%> </button>
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
                    <input type="hidden" name="command" value="go_to_librarian_orders_page">
                    <input type="hidden" name="page" value="${pageNum - 1}">

                    <td>
                        <button type="submit"><%=I18N.translate("prev", session)%></button>
                    </td>
                </form>
            </c:if>
            <td>${pageNum}</td>
            <form action="/Controller" method="get">
                <input type="hidden" name="command" value="go_to_librarian_orders_page">
                <input type="hidden" name="page" value="${pageNum + 1}">

                <td>
                    <button type="submit"><%=I18N.translate("next", session)%></button>
                </td>
            </form>
        </tr>
    </table>
</body>
</html>
