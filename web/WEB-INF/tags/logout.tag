<%@ tag import="smytsyk.finalProject.library.service.I18N" %>
<form action="Controller" method="get">
    <input type="hidden" name="command" value="go_to_main_page">
    <button type="submit"><%=I18N.translate("logout", session)%></button>
</form>