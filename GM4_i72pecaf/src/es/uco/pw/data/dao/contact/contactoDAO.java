package es.uco.pw.data.dao.contact;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import es.uco.pw.data.dao.common.conexionBD;
import es.uco.pw.display.javabean.contactoBean;

public class contactoDAO {
	
	private conexionBD conBD;
	private Connection conn;
	
	private String INSERT = "INSERT INTO contactos(email, nombre, apellidos, fechaNacimiento, contrasena, intereses) VALUES(?, ?, ?, ?, ?, ?)";
	private String SELECTONE = "SELECT email, nombre, apellidos, fechaNacimiento, contrasena, intereses FROM contactos WHERE email = ?";
	private String SELECTLOGIN = "SELECT email, nombre, apellidos, fechaNacimiento, contrasena, intereses FROM contactos WHERE email = ? AND contrasena = ?";
	private String UPDATE = "UPDATE contactos SET nombre=?, apellidos=?, fechaNacimiento=?, contrasena=?, intereses=? WHERE email = ?";
	
	// ================================================================================================
	/* ESTAS VARIABLES SE TIENEN QUE PASAR AL INICIALIZAR EL GESTOR O EL TABLON Y LEER DESDE UN FICHERO 
	PROPERTIES */
	// ================================================================================================
	public contactoDAO(String url, String user, String pass) {
		conBD = new conexionBD(url,user,pass);
	}	
	
	public int insertar(contactoBean c) {
		int status = 0;
		PreparedStatement stat = null;	
		conBD.Conectar();
		conn = conBD.getConexion();
		
		try {
			stat = conn.prepareStatement(INSERT);
			stat.setString(1, c.getEmail());
			stat.setString(2, c.getNombre());
			stat.setString(3, c.getApellidos());
			stat.setString(4, c.getFecha_nacimiento());
			stat.setString(5, c.getContrasena());
			stat.setString(6, c.getIntereses());
			status = stat.executeUpdate();
			
			conBD.Desconectar();
		} catch (Exception e) {
			System.err.print("Error al insertar el contacto en la base de datos\n");
			status = -1;
		}

		return status;
	}
	
	public int existeEmail(String aux_email) {
		int status = 0;
		PreparedStatement stat = null;
		conBD.Conectar();
		conn = conBD.getConexion();
		
		try {
			stat = conn.prepareStatement(SELECTONE);
			stat.setString(1,aux_email);  
		    ResultSet res=stat.executeQuery();  
			
		    if(res.next()) {
		    	System.out.print("El usuario existe");
		    	status = 0;
		    } else {
		    	System.out.print("El usuario no existe");
		    	status = -1;
		    }
		    
			conBD.Desconectar();
		} catch (Exception e) {
			System.err.print(e);
		}

		return status;
	}
	
	public int comprobarLogin(String aux_email, String aux_pass) {
        int status = 0;
        PreparedStatement stat = null;
        conBD.Conectar();
        conn = conBD.getConexion();

        try {
            if (conn != null) {
                stat = conn.prepareStatement(SELECTLOGIN);
                stat.setString(1,aux_email); 
                stat.setString(2,aux_pass);
                ResultSet res=stat.executeQuery();

                if(res.next()) {
                    System.out.print("Se ha logeado con exito");
                    status = 0;
                } else {
                    System.out.print("El login ha fallado");
                    status = -1;
                }

                conBD.Desconectar();
            } else {
                System.err.print("Error en la conexión.");
                status = -1;
            }
        } catch (Exception e) {
            System.err.print(e);
        }

        return status;
    }
	
	//Remplazar Contacto por el DTO de contacto
	public contactoBean obtenerContactoPorEmail(String email_id) {
		PreparedStatement stat = null;
		contactoBean r = null;
		conBD.Conectar();
		conn = conBD.getConexion();
		
		try {
			stat = conn.prepareStatement(SELECTONE);
			stat.setString(1,email_id);  
		    ResultSet res=stat.executeQuery();  
			
		    while(res.next()) {
		    	r = new contactoBean();
		    	r.setEmail(res.getString("email"));
		    	r.setNombre(res.getString("nombre"));
		    	r.setApellidos(res.getString("apellidos"));
		    	r.setFecha_nacimiento(res.getString("fechaNacimiento"));
		    	r.setContrasena(res.getString("contrasena"));
		    	r.setIntereses(res.getString("intereses"));
		    }
		    
			conBD.Desconectar();
		} catch (Exception e) {
			System.err.print(e);
		}

		return r;
	}
	
	public int modificarContacto(contactoBean c) {
		int status = 0;
		PreparedStatement stat = null;
		conBD.Conectar();
		conn = conBD.getConexion();
		
		try {
			//La linea de abajo dice el comando SQL a usar, arriba lo tienes
			//En caso de que no vaya, prueba en la BD el comando SQL y luego lo copias arriba
			stat = conn.prepareStatement(UPDATE);
			//Aqui se le da a cada ? su correspondiente variable del objeto (Va por el orden de las ? en el comando)
			stat.setString(1, c.getNombre());
			stat.setString(2, c.getApellidos());
			stat.setString(3, c.getFecha_nacimiento());
			stat.setString(4, c.getContrasena());
			stat.setString(5, c.getIntereses());
			stat.setString(6, c.getEmail());
			//Muestra el numero de elementos afectados
			status = stat.executeUpdate();
			
		} catch (Exception e) {
			System.err.print(e);
			status = -1;
		}
		
		return status;
	}
}