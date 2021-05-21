<%!String message;%>
<%
    if (session.getAttribute("error") != null) {
        message = (String) session.getAttribute("error");
        session.setAttribute("error", null);
    }
%>
<%=message == null ? "" : "<script> alert(\"" + message + "\"); </script>"%>
<%message = null;%>