package es.uco.pw.data.dao.common;

import java.sql.Connection;
import java.sql.DriverManager;

public class conexionBD {
	
	private Connection conn; //Objeto que se devolvera para realizar las operaciones con la BD
	private String url, user, pass;
	
	public conexionBD(String urlAux, String userAux, String passAux) {
		this.url = urlAux;
		this.user = userAux;
		this.pass = passAux;
	}
	
	public void Conectar() {
		try { 
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, pass);
		} catch (Exception e) {
			System.err.println("Error al conectar con la base de datos");
		}
	}
	
	public void Desconectar() {
		try { 
			conn.close();
		} catch (Exception e) {
			System.err.println("Error al desconectar");
		}
	}
	
	public Connection getConexion() {
		return conn;
	} 
}
