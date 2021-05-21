<%@ page import="smytsyk.finalProject.library.service.I18N" %>
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

    <c:if test="${pageNum == 0}">
        <form action="Controller" method="get">
            <input type="hidden" name="command" value="go_to_catalog_page">
            <table>
                <tr>
                    <td><%=I18N.translate("book_name", session)%></td>
                    <td><input type="text" name="name" value=${name}></td>
                </tr>
                <tr>
                    <td><%=I18N.translate("book_author", session)%></td>
                    <td><input type="text" name="author" value=${author}></td>
                </tr>
                <tr>
                    <td><%=I18N.translate("sort_by", session)%></td>
                    <td>
                        <select size="1" name="comparator">
                            <option value="id">Id</option>
                            <option value="name"><%=I18N.translate("book_name", session)%></option>
                            <option value="author"><%=I18N.translate("book_author", session)%></option>
                            <option value="publisher"><%=I18N.translate("book_publisher", session)%></option>
                            <option value="date"><%=I18N.translate("publication_date", session)%></option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td><button type="submit"><%=I18N.translate("search", session)%></button></td>
                </tr>
            </table>
        </form>
    </c:if>

    <table>
        <tr>
            <td>Id</td>
            <td><%=I18N.translate("book_name", session)%></td>
            <td><%=I18N.translate("book_author", session)%></td>
            <td><%=I18N.translate("book_publisher", session)%></td>
            <td><%=I18N.translate("publication_date", session)%></td>
            <td><%=I18N.translate("return_date", session)%></td>
            <td></td>
        </tr>
        <c:choose>
            <c:when test="${books.isEmpty()}">
                <tr>
                    <td>
                        <%=I18N.translate("no_books", session)%>
                    </td>
                </tr>
            </c:when>
            <c:otherwise>
                <c:forEach var="book" items="${books}">
                    <tr>
                        <td>${book.getId()}</td>
                        <td>${book.getName()}</td>
                        <td>${book.getAuthor()}</td>
                        <td>${book.getPublisher()}</td>
                        <td>${book.getPublicationDate().format(formatter)}</td>
                        <form action="Controller" method="post">
                            <input type="hidden" name="command" value="send_order">
                            <input type="hidden" name="book_id" value=${book.getId()}>
                            <input type="hidden" name="user_id" value=${user.getId()}>
                            <td>
                                <input type="text" name="date">
                            </td>
                            <td>
                                <button type="submit"><%=I18N.translate("send_order", session)%></button>
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
                    <input type="hidden" name="command" value="go_to_catalog_page">
                    <input type="hidden" name="page" value="${pageNum - 1}">
                    <input type="hidden" name="name" value="${name}">
                    <input type="hidden" name="author" value="${author}">
                    <input type="hidden" name="comparator" value="${comparator}">
                    <td>
                        <button type="submit"><%=I18N.translate("prev", session)%></button>
                    </td>
                </form>
            </c:if>
            <td>${pageNum}</td>
            <form action="/Controller" method="get">
                <input type="hidden" name="command" value="go_to_catalog_page">
                <input type="hidden" name="page" value="${pageNum + 1}">
                <input type="hidden" name="name" value="${name}">
                <input type="hidden" name="author" value="${author}">
                <input type="hidden" name="comparator" value="${comparator}">
                <td>
                    <button type="submit"><%=I18N.translate("next", session)%></button>
                </td>
            </form>
        </tr>
    </table>

</body>
</html>
