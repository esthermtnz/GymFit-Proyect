/**
 * Este fichero muestra todo lo que tiene que ver con la clase AveriaPreviamenteReportada
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
package aplicacion.excepciones;

import aplicacion.equipacion.Maquina;

/**
 * esta es una clase que representa AveriaPreviamenteReportada
 * 
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 **/
public class AveriaPreviamenteReportada extends Exception {
	Maquina maquina;
	/**
	 * Clase para reportada con una averia ya ha sido reportada
	 * @param maquina la maquina que tiene la averia
	 */
	public AveriaPreviamenteReportada(Maquina maquina) {
		super("La maquina con id " + maquina.getId() + " ya ha sido reportada como averiada.");
		this.maquina = maquina;
	}

}
