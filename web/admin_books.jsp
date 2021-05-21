<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="smytsyk.final_project.library.service.I18N" %>
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

<c:if test="${pageNum == 0}">
    <form action="Controller" method="post">
        <input type="hidden" name="command" value="add_book">
        <table>
            <tr>
                <td><%=I18N.translate("book_name", session)%></td>
                <td><input type="text" name="name"></td>
            </tr>
            <tr>
                <td><%=I18N.translate("book_author", session)%></td>
                <td><input type="text" name="author"></td>
            </tr>
            <tr>
                <td><%=I18N.translate("book_publisher", session)%></td>
                <td><input type="text" name="publisher"></td>
            </tr>
            <tr>
                <td><%=I18N.translate("publication_date", session)%></td>
                <td><input type="text" name="publication_date"></td>
            </tr>
            <tr>
                <td></td>
                <td><button type="submit"><%=I18N.translate("add_book", session)%></button></td>
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
        <td></td>
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
            <c:forEach var = "book" items="${books}">
                <tr>
                    <td>${book.getId()}</td>
                    <form action="Controller" method="post">
                        <input type="hidden" name="command" value="update_book">
                        <input type="hidden" name="book_id" value=${book.getId()}>
                        <td>
                            <input type="text" name="name" value=${book.getName()}>
                        </td>
                        <td>
                            <input type="text" name="author" value=${book.getAuthor()}>
                        </td>
                        <td>
                            <input type="text" name="publisher" value=${book.getPublisher()}>
                        </td>
                        <td>
                            <input type="text" name="publication_date" value=${book.getPublicationDate().format(formatter)}>
                        </td>
                        <td>
                            <button type="submit"><%=I18N.translate("update_book", session)%></button>
                        </td>
                    </form>
                    <form action="Controller" method="post">
                        <input type="hidden" name="command" value="delete_book">
                        <input type="hidden" name="book_id" value=${book.getId()}>
                        <td>
                            <button type="submit"><%=I18N.translate("delete_book", session)%></button>
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
                <input type="hidden" name="command" value="go_to_admin_books_page">
                <input type="hidden" name="page" value="${pageNum - 1}">

                <td>
                    <button type="submit"><%=I18N.translate("prev", session)%></button>
                </td>
            </form>
        </c:if>
        <td>${pageNum}</td>
        <form action="/Controller" method="get">
            <input type="hidden" name="command" value="go_to_admin_books_page">
            <input type="hidden" name="page" value="${pageNum + 1}">

            <td>
                <button type="submit"><%=I18N.translate("next", session)%></button>
            </td>
        </form>
    </tr>
</table>

</body>
</html>