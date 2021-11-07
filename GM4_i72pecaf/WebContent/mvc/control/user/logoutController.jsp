<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="es.uco.pw.business.contact.Contacto,es.uco.pw.data.dao.contact.contactoDAO"%>
<jsp:useBean id="contactoBean" scope="session"
	class="es.uco.pw.display.javabean.contactoBean"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Desconexión</title>
</head>
<body>
	<%
	String siguientePagina = "../../../index.jsp";
	String mensajeSiguientePagina = "";
	//	Desconexión de la sesión actual.
	if (session.getAttribute("email") != null) {
		session.invalidate();
		mensajeSiguientePagina = "Sesión finalizada.";
	} else {
	//	Si se accede de forma fraudulenta a esta página, se imprime este mensaje y se redireccionará a "index.jsp".
		mensajeSiguientePagina = "Usted no ha iniciado sesión.";
	}
	%>

	<jsp:forward page="<%=siguientePagina%>">
		<jsp:param value="<%=mensajeSiguientePagina%>" name="message" />
	</jsp:forward>

</body>
</html>