/**
 * Este fichero muestra todo lo que tiene que ver con la clase ExcepcionRequisitos
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
package aplicacion.excepciones;

import aplicacion.actividad.sesion.requisito.Requisito;
import aplicacion.sala.Sala;

/**
 * esta es una clase que representa ExcepcionRequisitos
 * 
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 */
public class ExcepcionRequisitos extends Exception{
	private Requisito requisito;
	/**
	 * Excepcion para cuando se sale de los limites del requisito
	 * @param requisito el requisito de la excepcion
	 */
	public ExcepcionRequisitos(Requisito requisito)
	{
		super("No cumple el requisito de " + requisito.getClass().getSimpleName());
		this.requisito = requisito;
	}
}
