package modulos.usuarios.UI;

import java.text.ParseException;
import java.util.Date;
import java.util.StringTokenizer;
import Utils.Helper;
import java.util.*;

public class AdUsuario {
	private String id;
	private String nombre, apellido, dni, contrasena;
/**
 * 
 * @param contrasenya. Cuando se anyade un usuario, por defecto su contrasenya
 * será igual que su dni.
 */
	public AdUsuario(String nombre, String apellido, String dni) {
		this.id = UUID.randomUUID().toString();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.contrasena = dni;

	}
	/**
	 * Cliente debe cambiar cuando accede por primera vez su contrasenya, no 
	 * puede ser igual a su dni.
	 * metodo verificarCambioContrasenya() 
	 * @return true si campo contrasenya es diferente a dni
	 */ 
	public boolean verificarCambioContrasenya() {
		if (this.dni != this.contrasena) {
			return true;
		}
		return false;
	}

	public void Actualizar(String nombre, String apellido, String dni, String contrasena) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.contrasena = contrasena;

	}

	// constructor sobrecargado, que recibe una linea del fichero, lanza una
	// excepcion por si se guarda los datos en otro formato
	public AdUsuario(String lineaEvento) throws ParseException {
		StringTokenizer token = new StringTokenizer(lineaEvento, Helper.tokenColumna);
		this.id = token.nextToken();
		this.nombre = token.nextToken();
		this.apellido = token.nextToken();
		this.dni = token.nextToken();
		this.contrasena = token.nextToken();
		

	}

	public String getId() {
		return this.id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public String getApellido() {
		return this.apellido;

	}

	public String getDni() {
		return this.dni;
	}

	public String getContrasena() {
		return this.contrasena;
	}

//Este toString sobreescribe el fichero con el separador
	@Override
	public String toString() {
		return this.id + Helper.tokenColumna + this.nombre + Helper.tokenColumna + this.apellido
				+ Helper.tokenColumna + this.dni + Helper.tokenColumna + this.contrasena;
	}
}