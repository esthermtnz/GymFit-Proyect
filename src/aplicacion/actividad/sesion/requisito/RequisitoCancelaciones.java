/**
 * Este fichero muestra todo lo que tiene que ver con la clase RequisitoCancelaciones
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */

package aplicacion.actividad.sesion.requisito;
import java.io.Serializable;

import aplicacion.usuario.*;

/**
 * esta es una clase que representa RequisitoCancelaciones
 * 
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 **/

public class RequisitoCancelaciones extends Requisito implements Serializable{
	/**
	 * Clase que comprueba los requisitos
	 * @param min las cancelaciones minimas
	 * @param max las cancelaciones maximas
	 */
	public RequisitoCancelaciones(Integer min, Integer max) {
		super(min, max);
	}

	/**
	 * Comprobar que el cliente cumple los requsitos
	 * @param cliente El cliente a comprobar
	 * @return true si todo funciona bien
	 */
	public Boolean comprobar(Cliente cliente) {
		if (cliente == null)
			return false;

		Integer cancelCliente = cliente.getCancelaciones();
		if ((cancelCliente >= super.getMin()) && (cancelCliente <= super.getMax())) {
			return true;
		} else {
			return false;
		}

	}
}
