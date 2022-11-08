package modulos.eventos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

/*Esta clase tiene una relacion de asociacion con la clase evento. En ella se va a delegar la responsabilidad
 * del CRUD de evento*/
public class AFEvento {
	// variable constante porque no va a cambiar
	final String fileName = ".\\src\\ficheros\\eventos";
	private ArrayList<Evento> eventos = new ArrayList<Evento>();

	/*
	 * Constructor clase evento, cuando se crea un objeto lee el fichero eventos y
	 * por cada linea crea un objeto evento pasando la cadena de texto. Por cada
	 * objeto que creamos lo vamos añadiendo al arraylist, de esta manera tenemos
	 * todas las lineas en el arraylist guardadas
	 */
	public AFEvento() {
		BufferedReader bf = null;
		String indiceLinea;
		try {
			bf = new BufferedReader(new FileReader(fileName));
			do {
				indiceLinea = bf.readLine();
				if (indiceLinea != null) {
					eventos.add(new Evento(indiceLinea));
				}
			} while (indiceLinea != null);
			bf.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<Evento> getEventos() {
		return eventos;
	}

	public void anyadir(Evento evento) {
		eventos.add(evento);
		guardar();
	}

	public void modificar(Evento evento) {
		Evento existeEvento = buscarEventoPorId(evento.getId());
		if (existeEvento != null) {		
			guardar();
		}
	}

	public void borrar(String id) {
		Evento existeEvento = buscarEventoPorId(id);
		if (existeEvento != null) {
			this.eventos.remove(existeEvento);
		}
		guardar();
	}

	/*
	 * Este metodo guarda el arraylist en el fichero eventos. Recorremos cada
	 * elemento del arraylist a través de un for cada evento lo escribimos en el
	 * fichero a través de su método toString que añade ya el token previamente
	 * creado en la clase Evento
	 */
	private void guardar() {
		try {
			BufferedWriter escribir = new BufferedWriter(new FileWriter(fileName));
			for (Evento evento : eventos) {
				escribir.write(evento.toString());
				escribir.newLine();
			}
			escribir.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// metodo para buscar en el arrayList el ID del evento
	public Evento buscarEventoPorId(String id) {
		for (Evento evento : eventos) {
			if (evento.getId().equalsIgnoreCase(id)) {
				return evento;
			}

		}
		return null;
	}
}