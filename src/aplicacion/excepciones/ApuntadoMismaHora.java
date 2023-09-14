/**
 * Este fichero muestra todo lo que tiene que ver con la clase ApuntadoMismaHora
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
package aplicacion.excepciones;

 /**
	 * esta es una clase que representa ApuntadoMismaHora
	 * 
	 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
	 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
	 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
	 **/
public class ApuntadoMismaHora extends Exception {
	
	 /**
	 * Clase por si te intentas apuntar a la misma hora que otra sesion
	 */
	public ApuntadoMismaHora() {
		super("Ya esta apuntado a otra sesion a la misma hora");
	}

}
