package modulos.usuarios.UI;

public class UsuarioLoggeado {
	//Me creo una variable static del mismo tipo que la clase.
	private static UsuarioLoggeado instancia_unica = null;
	private String usuario;
	//Haciendo el constructor privado, evito que se puedan crear instancias de la clase.
	private UsuarioLoggeado()
    {
    }
	//Para obtener una instancia de la clase se debe hacer a través de este método.
	//Este método inicializa la variable instancia_unica la primera vez y el resto de 
	//veces que se llame devuelve el objeto instancia_unica ya inicializado.
	public static UsuarioLoggeado getInstance()
    {
        if (instancia_unica == null)
    	{
    		instancia_unica = new UsuarioLoggeado();
    	}
  
        return instancia_unica;
    }
	
	public void setUsuario(String usuario) {
		this.usuario =  usuario;
	}
	
	public String getUsuario() {
		return  "Usuario: " + usuario;
	}
}
