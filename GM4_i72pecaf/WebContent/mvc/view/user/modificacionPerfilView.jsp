<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="es.uco.pw.business.contact.Contacto,es.uco.pw.data.dao.contact.contactoDAO"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modificación de usuario</title>
</head>
<body>

	<%
	//	Se comprueba que exista un usuario en línea para que se pueda realizar la modificación de los parámetros de usuario.
	if (session.getAttribute("email") != null) {%>

	<form method="post"
		action="../../control/user/modificacionPerfilController.jsp">

		<label for="contrasena">Contraseña: </label> 
		<input type="text" name="contrasena" value="" placeholder=obligatorio><br /> 
		<label for="nombre">Nombre: </label> 
		<input type="text" name="nombre" value="" placeholder=obligatorio><br /> 
		<label for="apellidos">Apellidos: </label> <input type="text" name="apellidos" value="" placeholder=obligatorio><br />
		<label for="fecha_nacimiento">Fecha de nacimiento: </label> 
		<input type="date" name="fecha_nacimiento" value="" placeholder=yyyy-MM-dd><br />

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

		<input type="hidden" id="perfilModificado" name="perfilModificado"
			value="true"><br /> <input type="submit"
			value="Guardar cambios">
	</form>

	<%
	} else {
		//	Si no se ha iniciado sesión, se redirecciona a "loginController.jsp" para que se proceda a ello.
		response.sendRedirect("../../control/user/loginController.jsp");
	}
	%>

</body>
</html>