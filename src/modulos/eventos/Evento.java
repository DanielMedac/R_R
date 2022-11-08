package modulos.eventos;

import java.text.ParseException;
import java.util.Date;
import java.util.StringTokenizer;
import Utils.Helper;
import java.util.*;

public class Evento {
	private String id;
	private String nombre, patrocinador, lugar;
	private Date fecha;

	public Evento(String nombre, String patrocinador, String lugar, Date fecha) {
		this.id = UUID.randomUUID().toString();
		this.nombre = nombre;
		this.patrocinador = patrocinador;
		this.lugar = lugar;
		this.fecha = fecha;

	}
	
	// constructor sobrecargado, que recibe una linea del fichero, lanza una
	// excepcion por si se guarda los datos en otro formato
	public Evento(String lineaEvento) throws ParseException {
		StringTokenizer token = new StringTokenizer(lineaEvento, Helper.tokenColumna);
		this.id = token.nextToken();
		this.nombre = token.nextToken();
		this.patrocinador = token.nextToken();
		this.lugar = token.nextToken();
		// con formatoFecha estoy parseando la varible a tipo fecha

		try {
			// this.fecha = formatoFecha.parse(token.nextToken());
			// Variable global, que permite a todos parsear la fecha
			this.fecha = Helper.parsearAFecha(token.nextToken());
		} catch (ParseException e) {

			e.getStackTrace();
		}

	}

	public void Actualizar(String nombre, String patrocinador, String lugar, Date fecha) {
		this.nombre = nombre;
		this.patrocinador = patrocinador;
		this.lugar = lugar;
		this.fecha = fecha;
	}	

	public String getId() {
		return this.id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public String getPatrocinador() {
		return this.patrocinador;

	}

	public String getLugar() {
		return this.lugar;
	}

	public Date getFecha() {
		return this.fecha;
	}

//Este toString sobreescribe el fichero con el separador
	@Override
	public String toString() {
		return this.id + Helper.tokenColumna + this.nombre + Helper.tokenColumna + this.patrocinador
				+ Helper.tokenColumna + this.lugar + Helper.tokenColumna + Helper.formatearAStringFecha(fecha);
	}
}