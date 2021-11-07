<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="contactoBean" scope="session"
	class="es.uco.pw.display.javabean.contactoBean"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Log in</title>
</head>
<body>

	<%
	String siguientePagina = "../../control/user/loginController.jsp";
	String mensajeSiguientePagina = request.getParameter("message");
	
	if (mensajeSiguientePagina == null) {
		mensajeSiguientePagina = "";
	}
	//	Si el usuario ya está en línea se redirecciona a "index.jsp".
	if (session.getAttribute("email") != null) {
		siguientePagina = "../../index.jsp";
	} else {
	%>
	<%= mensajeSiguientePagina %><br> Le quedan
	<%=session.getAttribute("intentosRestantes") %>
	intentos.

	<form method="post" action="../../control/user/loginController.jsp">
		<label for="email">Email: </label> <input type="text" name="email"
			value="" placeholder=example@email.com><br /> <label
			for="contrasena">Contraseña: </label> <input type="text"
			name="contrasena" value=""><br /> <input type="submit"
			value="Iniciar sesión">
	</form>
	<%
	}
	%>

</body>
</html>