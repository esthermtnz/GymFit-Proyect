/**
 * Este fichero muestra todo lo que tiene que ver con la clase ExcepcionPago
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
package aplicacion.excepciones;

/**
 * esta es una clase que representa ExcepcionPago
 * 
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 */
public class ExcepcionPago extends Exception{
	
	/**
	 * Funcion pra cuando salta un error en el pago
	 * @return una cadena 
	 */
	public String ExcepcionPago()
	{
		return("No has pagado");
	}
}
