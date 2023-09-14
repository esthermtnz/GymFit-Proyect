/**
 * Este fichero muestra todo lo que tiene que ver con la clase ExcepcionUsuario
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
package aplicacion.excepciones;

/**
 * esta es una clase que representa ExcepcionUsuario
 * 
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 */
public class ExcepcionUsuario extends Exception {
	
	/**
	 * Constructor.
	 * Este metodo es el constructor, el cual se encarga de inicializar los datos de la clase con los pasados como argumentos.
	 *
	 * @param cadena, la cadena de la excepcion
	 */
	public ExcepcionUsuario(String cadena) {
		super(cadena);
	}
	
}
