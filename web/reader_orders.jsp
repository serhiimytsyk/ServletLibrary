<%@ page import="smytsyk.final_project.library.service.I18N" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tf"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
    <tf:logout/>
    <tf:tagError/>

    <form action="Controller" method="get">
        <input type="hidden" name="command" value="return_to_readers_page">
        <button type="submit"><%=I18N.translate("return", session)%></button>
    </form>

    <table>
        <tr>
            <td><%=I18N.translate("book_id", session)%></td>
            <td><%=I18N.translate("book_name", session)%></td>
            <td><%=I18N.translate("return_date", session)%></td>
            <td><%=I18N.translate("fine", session)%></td>
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
                        <td>${order.getBookId()}</td>
                        <td>${orderIdToBookName.get(order.getId())}</td>
                        <td>${order.getReturnDate().format(formatter)}</td>
                        <td>${orderIdToFine.get(order.getId())}</td>
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
                    <input type="hidden" name="command" value="go_to_reader_orders_page">
                    <input type="hidden" name="page" value="${pageNum - 1}">

                    <td>
                        <button type="submit"><%=I18N.translate("prev", session)%></button>
                    </td>
                </form>
            </c:if>
            <td>${pageNum}</td>
            <form action="/Controller" method="get">
                <input type="hidden" name="command" value="go_to_reader_orders_page">
                <input type="hidden" name="page" value="${pageNum + 1}">

                <td>
                    <button type="submit"><%=I18N.translate("next", session)%></button>
                </td>
            </form>
        </tr>
    </table>
</body>
</html>
