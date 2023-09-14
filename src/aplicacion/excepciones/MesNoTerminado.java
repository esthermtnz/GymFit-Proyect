package aplicacion.excepciones;

import java.time.Month;

/**
 * Este fichero muestra todo lo que tiene que ver con la clase MesNoTerminado
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class MesNoTerminado extends Exception {
	Month mes;
	/**
	 * Clase para un mes que no haya terminado o ni siquiera haya empezado
	 * @param mes el mes que no ha terminado
	 */
	public MesNoTerminado(Month mes) {
		super("El mes de " + mes + " aun no ha terminado, por lo que no puede ser consultado.");
		this.mes = mes;
	}

}
