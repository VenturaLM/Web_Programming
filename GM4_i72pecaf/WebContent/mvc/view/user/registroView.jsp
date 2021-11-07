<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="es.uco.pw.business.contact.Contacto,es.uco.pw.data.dao.contact.contactoDAO"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registro</title>
</head>
<body>

	<%= request.getParameter("message")%>
	<%
	//Referencia formulario:	https://www.hazunaweb.com/curso-de-html/formularios-iii-listas-opciones/
	//							https://www.studentstutorial.com/java-project/insert-multiple-checkbox.php
	
	//	Si no existe un usuario en línea, se procede al proceso de registro.
	if (session.getAttribute("email") == null || session.getAttribute("email").equals("")) {%>

	<form method="post" action="../../control/user/registroController.jsp">
		<label for="email">Email: </label> <input type="text" name="email"
			value="" placeholder=example@email.com><br /> <label
			for="contrasena">Contraseña: </label> <input type="text"
			name="contrasena" value="" placeholder=obligatorio><br /> <label
			for="nombre">Nombre: </label> <input type="text" name="nombre"
			value="" placeholder=obligatorio><br /> <label
			for="apellidos">Apellidos: </label> <input type="text"
			name="apellidos" value="" placeholder=obligatorio><br /> <label
			for="fecha_nacimiento">Fecha de nacimiento: </label> <input
			type="date" name="fecha_nacimiento" value="" placeholder=yyyy-MM-dd><br />

		<label for="intereses[]">Intereses: </label><br> 
		<input type="checkbox" name="intereses[]" value="VIDEOJUEGOS">Videojuegos<br>
		<input type="checkbox" name="intereses[]" value="MUSICA">Musica<br>
		<input type="checkbox" name="intereses[]" value="CINE">Cine<br>
		<input type="checkbox" name="intereses[]" value="DEPORTES">Deportes<br>
		<input type="checkbox" name="intereses[]" value="BAILE">Baile<br>
		<input type="checkbox" name="intereses[]" value="LECTURA">Lectura<br>
		<input type="checkbox" name="intereses[]" value="COCINA">Cocina<br>
		<input type="checkbox" name="intereses[]" value="FOTOGRAFIA">Fotografia<br>
		<input type="checkbox" name="intereses[]" value="CIENCIA">Ciencia<br>
		<input type="checkbox" name="intereses[]" value="NATURALEZA">Naturaleza<br>
		<input type="checkbox" name="intereses[]" value="DIBUJO">Dibujo<br>

		<input type="submit" value="Completar Registro">
	</form>

	<%} else {
		//	Si existe un usuario en línea, se redirecciona a "index.jsp".
		response.sendRedirect("../../../index.jsp");
	}%>

</body>
</html>