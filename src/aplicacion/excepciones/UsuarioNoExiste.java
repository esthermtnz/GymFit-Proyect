/**
 * Este fichero muestra todo lo que tiene que ver con la clase public UsuarioNoExiste
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
package aplicacion.excepciones;

/**
 * esta es una clase que representa public UsuarioNoExiste
 * 
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 */
public class UsuarioNoExiste extends ExcepcionUsuario{
	private String usuario;
	
	/**
	 * Excepcion para cunado el usuario no existe
	 * @param usuario el usuario de la excepcion
	 */
	public UsuarioNoExiste(String usuario)
	{
		super( "El usuario: " + usuario +  " no existe");
		this.usuario = usuario;
		
		
	}
}
