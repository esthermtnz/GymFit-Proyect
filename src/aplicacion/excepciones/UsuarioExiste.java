/**
 * Este fichero muestra todo lo que tiene que ver con la clase UsuarioExiste
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
package aplicacion.excepciones;

import aplicacion.usuario.Usuario;

/**
 * esta es una clase que representa UsuarioExiste
 * 
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 */
public class UsuarioExiste extends ExcepcionUsuario{
	private String usuario;
	
	/**
	 * Excepcion para cunado el usuario ya existe
	 * @param usuario el usuario de la excepcion
	 */
	public UsuarioExiste(String usuario)
	{
		super( "El usuario: " + usuario +  " está repetido");
		this.usuario = usuario;
		
		
	}
}
