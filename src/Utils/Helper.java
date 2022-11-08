package Utils;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Helper {
	// Esta variable constante me va a servir de guia para separar la informacion
	// del fichero en columna
	public static String tokenColumna = "$";
	// variable constante para formato de fecha
	private static DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

	public static Date parsearAFecha(String fecha) throws ParseException {
		formatoFecha.setLenient(false);
		return formatoFecha.parse(fecha);
	}

	public static String formatearAStringFecha(Date fecha) {
		return formatoFecha.format(fecha);
	}

}
