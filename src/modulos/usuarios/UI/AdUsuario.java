package modulos.usuarios.UI;

import java.text.ParseException;
import java.util.StringTokenizer;

import Utils.Helper;
import java.util.*;

public class AdUsuario {
	private String id;
	private String nombre, apellido, dni, contrasena;

	public AdUsuario(String nombre, String apellido, String dni, String contrasena) {
		this.id = UUID.randomUUID().toString();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.contrasena = contrasena;

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
		return this.id + Helper.tokenColumna + this.nombre + Helper.tokenColumna + this.apellido + Helper.tokenColumna
				+ this.dni + Helper.tokenColumna + this.contrasena;
	}
}