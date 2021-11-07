<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean  id="contactoBean" scope="session" class="es.uco.pw.display.javabean.contactoBean"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Página principal</title>
</head>
<body>
	<%
	String mensajeSiguientePagina = request.getParameter("message");
	if (mensajeSiguientePagina == null) {
		mensajeSiguientePagina = "";
	}%>
	<%
	if (session.getAttribute("email") == null || session.getAttribute("email").equals("")) {
	%>
	<a href="/GM4_i72pecaf/mvc/control/user/loginController.jsp">Iniciar sesión</a>
	<a href="/GM4_i72pecaf/mvc/control/user/registroController.jsp">Registro</a>
	<%
	} else {
	%>
		<a href="/GM4_i72pecaf/mvc/control/user/logoutController.jsp">Cerrar sesión</a><br></br>
		<a href="/GM4_i72pecaf/mvc/control/user/modificacionPerfilController.jsp">Editar perfil</a><br></br>
		¡Bienvenido <%= session.getAttribute("email")%>!
	<%
	}
	%>
	<br/><br/><%= mensajeSiguientePagina %>
</body>
</html>