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
<title>Modificación de usuario</title>
</head>
<body>

	<%
	String siguientePagina = "../../../index.jsp";
	String mensajeSiguientePagina = "";
	//	Variable oculta en el post de "modificarPerfilView.jsp", que sirve como indicador de que se ha realizado una modificación de perfil.
	String perfilModificado = request.getParameter("perfilModificado");
	
	//	En caso de que no se haya iniciado sesión, no se podrá realizar la modificación del perfil, por lo que se redireccionará a la página "loginController.jsp" para que el usuario proceda a iniciar sesión.
	if (session.getAttribute("email") == null || session.getAttribute("email").equals("")) {
		siguientePagina = "../../control/user/loginController.jsp";
		mensajeSiguientePagina = "Todavía no ha iniciado sesión.";
	} else {
		//	Si la variable oculta = null, todavía no se ha realizado la modificación del perfil; se redirecciona a "modificacionPerfilView.jsp".
		if (perfilModificado == null)
		{
			siguientePagina = "../../view/user/modificacionPerfilView.jsp";
		} else {
			//	En este caso se formatean los intereses indicados en el post y se modificacn junto al resto de parámetros de usuario.
			String cadenaAuxiliar = "";
			String arrayAuxiliar[] = request.getParameterValues("intereses[]");
			
			if (arrayAuxiliar != null) {
				for (int i = 0; i <= arrayAuxiliar.length - 2; i++)
					cadenaAuxiliar += arrayAuxiliar[i] + ",";
				
				cadenaAuxiliar += arrayAuxiliar[arrayAuxiliar.length - 1];
			} else {
				cadenaAuxiliar = null;
			}
			
			contactoBean.setEmail((String)session.getAttribute("email"));
			contactoBean.setContrasena((String)request.getParameter("contrasena"));
			contactoBean.setNombre((String)request.getParameter("nombre"));
			contactoBean.setApellidos((String)request.getParameter("apellidos"));
			contactoBean.setFecha_nacimiento((String)request.getParameter("fecha_nacimiento"));
			contactoBean.setIntereses(cadenaAuxiliar);
			
			//	Se procede a la modificación del contacto.
			contactoDAO contactoDAO = new contactoDAO(application.getInitParameter("baseDatosEnlace") + application.getInitParameter("baseDatosNombre"), application.getInitParameter("baseDatosUsuario"), application.getInitParameter("baseDatosContrasena"));
			int validacion = contactoDAO.modificarContacto(contactoBean);
		}
	}
	%>
	<jsp:forward page="<%=siguientePagina%>">
		<jsp:param value="<%=mensajeSiguientePagina%>" name="message" />
	</jsp:forward>

</body>
</html>