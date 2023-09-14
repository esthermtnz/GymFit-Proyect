/**
 * Este fichero muestra todo lo que tiene que ver con la clase ExcepcionVeterania
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
package aplicacion.excepciones;

import aplicacion.actividad.sesion.requisito.Requisito;

/**
 * esta es una clase que representa ExcepcionVeterania
 * 
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 */
public class ExcepcionVeterania extends ExcepcionRequisitos{
	/**
	 * Excepcion para cuando se sale de los limites del requisito
	 * @param requisito el requisito de la excepcion
	 */
	public ExcepcionVeterania(Requisito requisito)
	{
		super(requisito);
	}
}
