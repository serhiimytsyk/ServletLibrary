<%@ page import="smytsyk.finalProject.library.service.I18N" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tf"%>
<html>
  <body>

    <h1><%=I18N.translate("welcome", session)%></h1>

    <tf:logout/>
    <tf:tagError/>

    <form action="Controller" method="get">
      <input type="hidden" name="command" value="switch_lang">
      <select size="1" name="lang">
        <option value="ru">Русский</option>
        <option value="en">English</option>
      </select>
      <button type="submit"><%=I18N.translate("switch_lang", session)%></button>
    </form>

    <table>
      <tr>
        <td><%=I18N.translate("ready_to_login", session)%></td>
        <td>
          <form action="Controller" method="get">
            <input type="hidden" name="command" value="go_to_login_page">
            <button type="submit"><%=I18N.translate("sign_in", session)%></button>
          </form>
        </td>
      </tr>
      <tr>
        <td><%=I18N.translate("not_ready_to_login", session)%></td>
        <td>
          <form action="Controller" method="get">
            <input type="hidden" name="command" value="go_to_register_page">
            <button type="submit"><%=I18N.translate("register", session)%></button>
          </form>
        </td>
      </tr>
    </table>

  </body>
</html>
