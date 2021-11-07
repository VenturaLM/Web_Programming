package es.uco.pw.business.contact;

import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * Clase "gestorContactos" que almacena y gestiona contactos. Implementado a partir del patr�n de dise�o "Singleton".
 * @author Ventura Lucena Mart�nez (i72lumav@uco.es)
 * @author Francisco Javier P�rez Castillo (i72pecaf@uco.es)
 */

public class gestorContactos {
	
	private static gestorContactos instance = null;
	
	/**
	 * Crea un gestor de contactor vac�o.
	 */
	private gestorContactos() {
		
	}
	
	/**
	 * 
	 * @return Instacia del gestor de contactos.
	 */
	public static gestorContactos getInstance() {
		if(instance == null) {
			instance = new gestorContactos();
		}
		return instance;
	}
	
	/**
	 * Lista que almacena los contactos del gestor de contactos.
	 */
	private ArrayList<Contacto> contactos = new ArrayList<Contacto>();
	
	/**
	 * 
	 * @param c Contacto a dar de alta en el gestor de contactos.
	 * @return "True" si el contacto se ha a�adido correctamente al gestor de contactos, o "False" si ya existe un contacto con un email igual al introducido en el gestor de contactos.
	 */
	public boolean darAltaContacto(Contacto c) {
		for(int i = 0; i < contactos.size(); i++) {
			if(contactos.get(i).getEmail().equals(c.getEmail())) {
				System.err.println("Error: el email " + c.getEmail() + " ya existe en el sistema.");
				return false;
			}
		}
		contactos.add(c);
		return true;
	}
	
	/**
	 * 
	 * @param email Email identificativo del contacto a eliminar.
	 * @return "True" si el contacto se ha eliminado correctamente, o "False" si el contacto no se encuentra en el gestor del contactos.
	 */
	public boolean darBajaContacto(String email) {
		email = email.toUpperCase();
		for(int i = 0; i < contactos.size(); i++) {
			if(contactos.get(i).getEmail().equals(email)) {
				contactos.remove(i);
				System.out.println("Contacto eliminado correctamente.");
				return true;
			}
		}
		System.err.println("Error: No se ha encontrado el contacto.");
		return false;
	}
	
	/**
	 * 
	 * @param email Email identificativo del contacto del cual se quiere consultar los datos.
	 * @return "True" si el contacto est� en el gestor de contactos, o "False" si no se ha encontrado el contacto.
	 */
	public boolean consultarDatosContacto(String email) {
		email = email.toUpperCase();
		for(int i = 0; i < contactos.size(); i++) {
			if(contactos.get(i).getEmail().equals(email)) {
				System.out.println(contactos.get(i).getDatosContacto());
				return true;
			}
		}
		System.err.println("Error: No se ha encontrado el contacto.");
		return false;
	}
	
	/**
	 * 
	 * @param c Contacto al que se le va a actualizar el nombre.
	 */
	private void actualizarNombreContacto(Contacto c) {
		Scanner cin = new Scanner(System.in);
		System.out.print("Introduzca el nuevo nombre: ");
		String nuevo_nombre = cin.nextLine();
		cin.close();
		c.setNombre(nuevo_nombre);
	}
	/**
	 * 
	 * @param c Contacto al que se le van a actualizar los apellidos.
	 */
	private void actualizarApellidosContacto(Contacto c) {
		Scanner cin = new Scanner(System.in);
		System.out.print("Introduzca los nuevos apellidos: ");
		String nuevos_apellidos = cin.nextLine();
		cin.close();
		c.setApellidos(nuevos_apellidos);
	}
	/**
	 * @param c Contacto al que se le van a actualizar la fecha de nacimiento.
	 */
	private void actualizarFechaNacimientoContacto(Contacto c) {
		Scanner cin = new Scanner(System.in);
		System.out.print("Introduzca la nueva fecha de nacimiento con la siguiente nomenclatura [dd/mm/yyyy]: ");
		String nueva_fecha_nacimiento = cin.nextLine();
		cin.close();
		c.setFechaNacimiento(nueva_fecha_nacimiento);
	}
	/**
	 * 
	 * @param c Contacto al que se le van a actualizar el email.
	 * @return "True" si el email se ha actualizado correctamente, o "False" si el email actualizado ya se encuentra en el gestor de contactos.
	 */
	private boolean actualizarEmailContacto(Contacto c) {
		Scanner cin = new Scanner(System.in);
		System.out.print("Introduzca el nuevo email: ");
		String nuevo_email = cin.nextLine();
		nuevo_email = nuevo_email.toUpperCase();
		cin.close();
		
		for(int i = 0; i < contactos.size(); i++) {
			if(contactos.get(i).getEmail().equals(nuevo_email)) {
				System.err.println("Error: el email " + nuevo_email + " ya existe en el sistema.");
				return false;
			}
		}
		c.setEmail(nuevo_email);
		return true;
	}
	
	/**
	 * 
	 * @param email Email identificativo del contacto al que le va a actualizar todos los datos.
	 */
	public void actualizarContacto(String email) {
		email = email.toUpperCase();
		for(int i = 0; i < contactos.size(); i++) {
			if(contactos.get(i).getEmail().equals(email)) {
				System.out.println("\n�Qu� elemento desea actualizar?\n1. Nombre.\n2. Apellidos.\n3. Fecha de nacimiento.\n4. Email.\n5. Todos los datos.");
				
				Scanner cin = new Scanner(System.in);
				int opcion = Integer.parseInt(cin.nextLine());
				
				switch(opcion) {
				case 1:
					actualizarNombreContacto(contactos.get(i));
					break;
				case 2:
					actualizarApellidosContacto(contactos.get(i));
					break;
				case 3:
					actualizarFechaNacimientoContacto(contactos.get(i));
					break;
				case 4:
					actualizarEmailContacto(contactos.get(i));
					break;
				case 5:
					actualizarNombreContacto(contactos.get(i));
					actualizarApellidosContacto(contactos.get(i));
					actualizarFechaNacimientoContacto(contactos.get(i));
					actualizarEmailContacto(contactos.get(i));
					break;
				}
				cin.close();
			} else {
				//System.err.println("Error: No se ha encontrado el contacto.");
			}
		}		
	}
	
	/**
	 * 
	 * @param nombre Nombre del contacto a buscar.
	 * @return "True" si el contacto se encuentra en el gestor de contactos, o "False" si el contacto no se encuentra en el gestor de contactos.
	 */
	public boolean buscarContactoNombre(String nombre) {
		nombre = nombre.toUpperCase();
		boolean flag = false;
		for(int i = 0; i < contactos.size(); i++) {
			if(contactos.get(i).getNombre().equals(nombre)) {
				System.out.println(contactos.get(i).getDatosContacto() + "\n");
				flag = true;
			}
		}
		
		if(flag == true) {
			return true;
		}else {
			System.err.println("Error: No se ha encontrado el contacto.");
			return false;
		}
	}
	
	/**
	 * 
	 * @param apellidos Apellidos del contacto a buscar.
	 * @return "True" si el contacto se encuentra en el gestor de contactos, o "False" si el contacto no se encuentra en el gestor de contactos.
	 */
	public boolean buscarContactoApellidos(String apellidos) {
		apellidos = apellidos.toUpperCase();
		boolean flag = false;
		for(int i = 0; i < contactos.size(); i++) {
			if(contactos.get(i).getApellidos().equals(apellidos)) {
				System.out.println(contactos.get(i).getDatosContacto() + "\n");
				flag = true;
			}
		}
		
		if(flag == true) {
			return true;
		}else {
			System.err.println("Error: No se ha encontrado el contacto.");
			return false;
		}
	}
	
	/**
	 * 
	 * @param tag Inter�s del contacto a buscar.
	 * @return "True" si el inter�s se encuentra en alg�n contacto del gestor de contactos, o "False" si el inter�s del contacto no se encuentra en ning�n contacto del gestor de contactos.
	 */
	public boolean buscarContactoIntereses(String tag) {
		
			tag = tag.toUpperCase();
			Intereses.valueOf(tag);
			
			boolean flag = false;
			for(int i = 0; i < contactos.size(); i++) {
				if(contactos.get(i).getIntereses().contains(Intereses.valueOf(tag))) {
					System.out.println(contactos.get(i).getDatosContacto() + "\n");
					flag = true;
				}
			}
			
			if(flag == true) {
				return true;
			}else {
				System.err.println("Error: No se ha encontrado ning�n contacto con dicho interes o intereses.");
				return false;
			}
	}
	
	/**
	 * 
	 * @param edad Edad del contacto a buscar.
	 * @return "True" si alg�n contacto del gestor de contactos tiene dicha edad, o "False" si ning�n contacto del gestor de contactos tiene dicha edad.
	 * @exception ParseException Indica que se ha obtenido un error inesperadamente durante el uso de .parse()
	 */
	@SuppressWarnings("deprecation")
	public boolean buscarContactoEdad(long edad) {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date fecha_contacto = null;
		Date fecha_actual = new Date();
		
		boolean flag = false;
		for(int i = 0; i < contactos.size(); i++) {
			try {
				fecha_contacto = formato.parse(contactos.get(i).getFechaNacimiento());
				if(fecha_actual.getYear() - fecha_contacto.getYear() == edad) {
					System.out.println(contactos.get(i).getDatosContacto() + "\n");
					flag = true;
				}
			}catch (ParseException e) {
				System.out.println(e);
			}
		}
		
		if(flag == true) {
			return true;
		}else {
			System.err.println("Error: No se ha encontrado ning�n contacto con dicha edad.");
			return false;
		}
	}
	
	/**
	 * Imprime la lista de contactos del gestor de contactos.
	 */
	public void imprimirContactos() {
		System.out.println("Lista de contactos:");
		for(int i = 0; i < contactos.size(); i++) {
			System.out.println(i + 1 + ". " + contactos.get(i).getDatosContacto() + "\n");
		}
	}
	/**
	 * Guarda los cambios realizados al gestor de contactos en un fichero.
	 * @exception IOException Indica que se ha producido una excepci�n de E/S de alg�n tipo. Esta clase es la clase general de excepciones producidas por operaciones de E/S fallidas o interrumpidas.
	 */
	public void guardarCambios() {
		try {
			FileWriter escritura = new FileWriter("datos_gestorContactos.txt");
			
			for(int i = 0; i < contactos.size(); i++) {
				escritura.write(contactos.get(i).getDatosContactoIndexado() + "\n");
			}
			escritura.close();
			System.out.println("Se han guardado los cambios.");
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @param email Email del contacto a buscar.
	 * @return Datos del contacto.
	 */
	public String obtenerDatosContacto(String email) {
		email = email.toUpperCase();
		String datos = null;
		for(int i = 0; i < contactos.size(); i++) {
			if(contactos.get(i).getEmail().equals(email)) {
				datos = contactos.get(i).getDatosContacto();
			}
		}
		return datos;
	}
	
}
