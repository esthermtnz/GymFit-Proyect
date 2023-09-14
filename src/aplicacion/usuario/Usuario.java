/**
 * Este fichero muestra todo lo que tiene que ver con la clase Usuario
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
package aplicacion.usuario;

import java.util.*;
import java.io.Serializable;
/**
 * esta es una clase que representa usuario
 * 
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public abstract class Usuario implements Serializable{
	private String usuario;
	private String contrasenya;
	protected LinkedList <Notificacion> notificaciones = new LinkedList<Notificacion>();

	/**
	 * constructor de usuario
	 * @param usuario usuario
	 * @param contrasenya2 contraseña
	 */
	public Usuario(String usuario, String contrasenya2){
		this.usuario = usuario;
		this.contrasenya = contrasenya2;
	}

	/**
	 * GETTER
	 * @return devuelve la lista de notificaciones
	 */
	public List <Notificacion> getNotificaciones()
	{
		return this.notificaciones;
	}
	
	/**
	 * GETTER
	 * @return devuelve el usuario
	 */
	public String getUsuario()
	{
		return this.usuario;
	}
	
	/**
	 * GETTER
	 * @return devuelve la contraseña
	 */
	public String getContrasenya() 
	{
		return this.contrasenya;
	}
	
	/**
	 * SETTER
	 * @param usuario usuario que se va a poner
	 * @return true si se ha puesto correctamente, de lo contrario false
	 */
	public Boolean setUsuario(String usuario)
	{
		if(usuario == null)
			return false;

		this.usuario = usuario;
		return true;
	}

	/**
	 * SETTER
	 * @param contrasenya contraseña que se va a poner
	 * @return true si se ha puesto correctamente, de lo contrario false
	 */
	public Boolean setContrasenya(String contrasenya)
	{
		if(contrasenya == null)
			return false;

		this.contrasenya = contrasenya;
		return true;
	}
	
	/**
	 * añade la notificacion al array de notificaciones
	 * @param notificacion notificacion que va a añadir
	 * @return true si se ha añadido correctamente, de lo contrario false
	 */
	public Boolean addNotificaciones(Notificacion notificacion)
	{
		if(notificacion == null || listaContieneNotificaion(notificacion)==true)
			return false;
		
		return getNotificaciones().add(notificacion);
	}
	
	/**
	 * comprueba si la lista de notificaciones contiene esa notificacion
	 * @param notificacion notificacion que va a comprobar
	 * @return true si la contiene, de lo contrario false
	 */
	public Boolean listaContieneNotificaion(Notificacion notificacion)
    {
    	if(notificacion == null)
    		return false;
    	
    	return notificaciones.contains(notificacion);
    }
	
    
	/**
	 * Funcion para mostrar el usuario
	 * @return devuelve la cadena
	 */
    public String toString(){
		String cadena = "\n-------------------------------\n";
		
		cadena += "USUARIO: " + this.getUsuario() + " CONTRASEÑA: " + this.contrasenya + "\n";
		
		return cadena;
	}

    /**
     * COmprueba si es cliente
     * @return false
     */
	public boolean esCliente() {
		return false;
	}

	/**
	 * Comprueba si es administrador
	 * @return false
	 */
	public boolean esAdministrador() {
		return false;
	}

	/**
	 * Comprueba si es monitor
	 * @return false
	 */
	public boolean esMonitor() {
		return false;
	}

}


