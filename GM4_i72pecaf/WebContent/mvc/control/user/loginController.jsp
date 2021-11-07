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
<title>Iniciar sesión</title>
</head>
<body>

	<%
	String siguientePagina = "../../../index.jsp";
	String mensajeSiguientePagina = "";
	
	if (session.getAttribute("email") == null || session.getAttribute("email").equals("")) {
		String emailUsuario = request.getParameter("email");
		String contrasenaUsuario = request.getParameter("contrasena");
		
		//	Si se ha establecido un email de usuario en la sesión, se comprueba que existe en la base de datos.
		if (emailUsuario != null) {
			contactoDAO contactoDAO = new contactoDAO(application.getInitParameter("baseDatosEnlace") + application.getInitParameter("baseDatosNombre"), application.getInitParameter("baseDatosUsuario"), application.getInitParameter("baseDatosContrasena"));
			int validacion = contactoDAO.comprobarLogin(emailUsuario, contrasenaUsuario);
			
			//	Si se obtiene un código de validacion = 0, se establece el parámetro indicado.
			if (validacion == 0) {
				session.setAttribute("email", emailUsuario);
			} else {
				//	Si se obtiene un código de validación = -1, se redirecciona a "loginView.jsp", indicando que el email introducido o no existe, o no es válido.
				siguientePagina = "../../view/user/loginView.jsp";
				mensajeSiguientePagina = "El usuario que ha indicado no existe o no es válido.";
				//	Se disminuye el número de intentos totales para iniciar sesión, cuyo valor inicial es = 3. En caso de que se exceda el número de intentos, se redireccionará al usuario a la página de la Universidad de Córdoba: "http://www.uco.es/".
				if((int)session.getAttribute("intentosRestantes") <= 1) {
					siguientePagina = "kicked_to_UCO";
				}
				
				session.setAttribute("intentosRestantes", ((int)session.getAttribute("intentosRestantes")) - 1);
			}
		} else {
			session.setAttribute("intentosRestantes", 3);
			siguientePagina = "../../view/user/loginView.jsp";
		}
	}
	
	if(!siguientePagina.equals("kicked_to_UCO")) {%>
	<jsp:forward page="<%=siguientePagina%>">
		<jsp:param value="<%=mensajeSiguientePagina%>" name="message" />
	</jsp:forward>
	<%
	} else {
		response.sendRedirect("http://www.uco.es/");
	}
	%>

</body>
</html>