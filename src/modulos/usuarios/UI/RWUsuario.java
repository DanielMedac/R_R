package modulos.usuarios.UI;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

/*Esta clase tiene una relacion de asociacion con la clase AdUsuario. En ella se va a delegar la responsabilidad
 * del CRUD de AdUsuario*/
public class RWUsuario {
	// variable constante porque no va a cambiar
	final String fileName = ".\\src\\ficheros\\usuarios";
	ArrayList<AdUsuario> usuarios = new ArrayList<AdUsuario>();

	/*
	 * Constructor clase AdUsuario, cuando se crea un objeto lee el fichero usuarios y
	 * por cada linea crea un objeto usuario pasando la cadena de texto. Por cada
	 * objeto que creamos lo vamos añadiendo al arraylist, de esta manera tenemos
	 * todas las lineas en el arraylist guardadas
	 */
	public RWUsuario() {

		BufferedReader bf = null;
		String indiceLinea;
		try {
			bf = new BufferedReader(new FileReader(fileName));
			do {
				indiceLinea = bf.readLine();
				if (indiceLinea != null) {
					usuarios.add(new AdUsuario(indiceLinea));
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

	public ArrayList<AdUsuario> getUsuarios() {
		return usuarios;
	}

	public void anyadir(AdUsuario usuario) {
		usuarios.add(usuario);
		guardar();
	}

	public void modificar(AdUsuario usuario) {
		AdUsuario existeUsuario = buscarUsuarioPorId(usuario.getId());
		if (existeUsuario != null) {		
			guardar();
		}
	}

	public void borrar(String id) {
		AdUsuario existeUsuario = buscarUsuarioPorId(id);
		if (existeUsuario != null) {
			this.usuarios.remove(existeUsuario);
		}
		guardar();
	}

	/*
	 * Este metodo guarda el arraylist en el fichero usuarios. Recorremos cada
	 * elemento del arraylist a través de un for cada usuario lo escribimos en el
	 * fichero a través de su método toString que añade ya el token previamente
	 * creado en la clase AdUsuario
	 */
	private void guardar() {
		try {
			BufferedWriter escribir = new BufferedWriter(new FileWriter(fileName));
			for (AdUsuario usuario : usuarios) {
				escribir.write(usuario.toString());
				escribir.newLine();
			}
			escribir.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// metodo para buscar en el arrayList el ID del evento
	public AdUsuario buscarUsuarioPorId(String id) {
		for (AdUsuario usuario : usuarios) {
			if (usuario.getId().equalsIgnoreCase(id)) {
				return usuario;
			}

		}
		return null;
	}
}