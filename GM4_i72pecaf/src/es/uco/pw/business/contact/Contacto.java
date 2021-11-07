package es.uco.pw.business.contact;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;

/**
 * Clase "Contacto" que representa un contacto gen�rico.
 * @author Ventura Lucena Mart�nez (i72lumav@uco.es)
 * @author Francisco Javier P�rez Castillo (i72pecaf@uco.es)
 */

public class Contacto {
	private String nombre;
	private String apellidos;
	private Date fecha_nacimiento;
	private String email;
	private String contrasena;
	private ArrayList<Intereses> intereses = new ArrayList<Intereses>();
	
	/** 
	* Crea un contacto vac�o, por defecto.
	*/
	public Contacto() {
		
	}
	
	/** 
    * Crea un contacto con su nombre, apellidos, fecha de nacimiento, email y una lista de intereses asociados.
    * @param nombre Nombre del contacto.
    * @param apellidos Apellidos del contacto.
    * @param fecha_nacimiento Fecha de nacimiento del contacto.
    * @param email Email del contacto (�nico).
    * @param intereses Lista de intereses asociados al contacto.
    */
	public Contacto(String nombre, String apellidos, String fecha_nacimiento, String email, String contrasena ,String[] intereses) {
		this.nombre = nombre.toUpperCase();
		this.apellidos = apellidos.toUpperCase();
		setFechaNacimiento(fecha_nacimiento);
		this.email = email.toUpperCase();
		this.contrasena = contrasena.toUpperCase();
		for(int i = 0; i < intereses.length; i++) {
			setInteres(intereses[i]);
		}
	}
	
		/**
		 * 
		 * @param nombre Nombre del contacto.
		 */
		public void setNombre(String nombre) {
			nombre.toUpperCase();
			this.nombre = nombre;
		}
		/**
		 * 	
		 * @param apellidos Apellidos del contacto.
		 */
		public void setApellidos(String apellidos) {
			apellidos.toUpperCase();
			this.apellidos = apellidos;
		}
		/**
		 * 
		 * @param str_fecha_nacimiento Fecha de nacimiento del contacto.
		 * @exception ParseException Indica que se ha obtenido un error inesperadamente durante el uso de .parse()
		 */
		public void setFechaNacimiento(String str_fecha_nacimiento) {
			SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
			try {
				this.fecha_nacimiento = formato.parse(str_fecha_nacimiento);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		/**
		 * 
		 * @param email Email del contacto.
		 */
		public void setEmail(String email) {
			email.toUpperCase();
			this.email = email;
		}
		/**
		 * 
		 * @param tag Inter�s del contacto.
		 */
		public void setInteres(String tag) {
			try {
				tag = tag.toUpperCase();
				intereses.add(Intereses.valueOf(tag));
			}catch(Exception e) {
				System.err.println("Error: Tag " + tag + " no encontrado para el siguiente contacto: " + getEmail());
			}
		}
		
		/**
		 * 
		 * @return Nombre del contacto.
		 */
		public String getNombre() {
			return nombre;
		}
		/**
		 * 
		 * @return Apellidos del contacto.
		 */
		public String getApellidos() {
			return apellidos;
		}
		/**
		 * 
		 * @return Fecha de nacimiento del contacto.
		 */
		public String getFechaNacimiento() {
			SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
			String fecha = formato.format(fecha_nacimiento);
			
			return fecha;
		}
		/**
		 * 
		 * @return Email del contacto.
		 */
		public String getEmail() {
			return email;
		}
		/**
		 * 
		 * @return Lista del intereses asociados al contacto.
		 */
		public ArrayList<Intereses> getIntereses() {
			return intereses;
		}
		
		/**
		 * 
		 * @return Cadena con los intereses asociados al contacto.
		 */
		public String getInteresesString() {
			StringBuffer sb = new StringBuffer();
		      
		    for (Intereses aux : intereses) {
		       sb.append(aux);
		       sb.append(", ");
		    }    
		    String str = sb.toString();
		    
		    return str.substring(0, str.length()-2);
		}
		
		public String getContrasena() {
			return contrasena;
		}
		
		public void setContrasena(String contrasena) {
			this.contrasena = contrasena;
		}
		
		/**
		 * 
		 * @return Todos los atributos del contacto.
		 */
		public String getDatosContacto() {
			getIntereses();
			return getNombre() + " " + getApellidos() + "\n" + getFechaNacimiento() + "\n" + getEmail() + "\n" + getIntereses();
		}
		/**
		 * Nota: M�todo usado para la impresi�n de los datos del contacto sobre un fichero. Cada dato se sit�a sobre una l�nea del fichero.
		 * @return Todos los atributos del contacto.
		 */
		public String getDatosContactoIndexado() {
			getIntereses();
			return getNombre() + "\n" + getApellidos() + "\n" + getFechaNacimiento() + "\n" + getEmail() + "\n" + getIntereses();
		}
}
