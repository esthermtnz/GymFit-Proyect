/**
 * Este fichero muestra todo lo que tiene que ver con la clase ApuntarseAntesDe48hs
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
package aplicacion.excepciones;

/**
 * esta es una clase que representa ApuntarseAntesDe48hs
 * 
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 **/
public class ApuntarseAntesDe48h extends Exception {
	/**
	 * Clase por si te intentas apuntar antes de 48 horas
	 */
	public ApuntarseAntesDe48h() {
		super("Solo te puedes apuntar desde 48 horas antes del inicio de la sesion");
	}

}
