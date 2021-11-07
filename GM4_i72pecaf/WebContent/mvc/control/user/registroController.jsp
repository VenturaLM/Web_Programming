<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="es.uco.pw.business.contact.Contacto,es.uco.pw.data.dao.contact.contactoDAO"%>
<jsp:useBean id="contactoBean" scope="session"
	class="es.uco.pw.display.javabean.contactoBean"></jsp:useBean>
<jsp:setProperty property="*" name="contactoBean" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registro</title>
</head>
<body>

	<%
		String siguientePagina = "../../../index.jsp";
	String mensajeSiguientePagina = "";
	//	Variable para comprobar si se ha realizado el post en "registroView.jsp".
	String estaUsuarioRegistrado = request.getParameter("email");

	if (session.getAttribute("email") == null || session.getAttribute("email").equals("")) {
		// Si se ha obtenido el parámetro en el formulario, se procede al registro.
		if (estaUsuarioRegistrado != null) {
			contactoDAO contactoDAO = new contactoDAO(
			application.getInitParameter("baseDatosEnlace") + application.getInitParameter("baseDatosNombre"),
			application.getInitParameter("baseDatosUsuario"), application.getInitParameter("baseDatosContrasena"));
			
			//	Si se obtiene código = -1, se procede al registro con normalidad. Se formatean los intereses para dejarlos en formato String y se añade el resto de parámetros de usuario.
			if (contactoDAO.existeEmail((String) request.getParameter("email")) == -1) {
				String cadenaAuxiliar = "";
				String arrayAuxiliar[] = request.getParameterValues("intereses[]");
				
				if (arrayAuxiliar != null) {
					for (int i = 0; i <= arrayAuxiliar.length - 2; i++)
						cadenaAuxiliar += arrayAuxiliar[i] + ",";
					
					cadenaAuxiliar += arrayAuxiliar[arrayAuxiliar.length - 1];
				} else {
					cadenaAuxiliar = null;
				}
				
				contactoBean.setContrasena((String)request.getParameter("contrasena"));
				contactoBean.setNombre((String)request.getParameter("nombre"));
				contactoBean.setApellidos((String)request.getParameter("apellidos"));
				contactoBean.setFecha_nacimiento((String)request.getParameter("fecha_nacimiento"));
				contactoBean.setIntereses(cadenaAuxiliar);
				
				contactoDAO.insertar(contactoBean);
				siguientePagina = "../../../index.jsp";
			} else {
				//	Si se obtiene código != -1, existe dicho email en la base de datos, por lo que se necesitará otro email para registrarse.
				siguientePagina = "../../view/user/registroView.jsp";
				mensajeSiguientePagina = "El email introducido ya está en uso.";
			}
		} else {
			//	Si no se ha completado el formulario de registro, se redirecciona a "registroView.jsp".
			siguientePagina = "../../view/user/registroView.jsp";
			mensajeSiguientePagina = "";
		}
	//	El usuario ya está en línea, por lo que se procede a la modificación de los parámetros de usuario. Se redirecciona a "modificacionPerfilController.jsp".
	} else {
		mensajeSiguientePagina = "Usted ya está en línea. Se procederá a la modificación de sus datos personales:";
		siguientePagina = "../../control/user/modificacionPerfilController.jsp";
	}
	%>
	<jsp:forward page="<%=siguientePagina%>">
		<jsp:param value="<%=mensajeSiguientePagina%>" name="message" />
	</jsp:forward>

</body>
</html>