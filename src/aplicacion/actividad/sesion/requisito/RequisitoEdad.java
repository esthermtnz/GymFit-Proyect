/**
 * Este fichero muestra todo lo que tiene que ver con la clase RequisitoEdad
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
package aplicacion.actividad.sesion.requisito;
import java.io.Serializable;
import java.time.LocalDate;
import aplicacion.usuario.*;

/**
 * esta es una clase que representa RequisitoEdad
 * 
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 **/
public class RequisitoEdad extends Requisito implements Serializable{
	
	/**
	 * Clase que comprueba los requisitos
	 * @param min la edad minimas
	 * @param max la edad maximas
	 */
	public RequisitoEdad(Integer min, Integer max) {
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

		Integer edadCliente = (LocalDate.now().getYear() - cliente.getFechaNacimiento().getYear());
		if ((edadCliente >= super.getMin()) && (edadCliente <= super.getMax())) {
			return true;
		} else {
			return false;
		}
	}
}
