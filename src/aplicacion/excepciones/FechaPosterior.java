/**
 * Este fichero muestra todo lo que tiene que ver con la clase FechaPosterior
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
package aplicacion.excepciones;

import java.time.LocalDate;

/**
 * esta es una clase que representa FechaPosterior
 * 
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 */
public class FechaPosterior extends Exception {
	private LocalDate fecha;
	
	/**
	 * Excepcion para cuando la fecha es posterior
	 * @param fecha la fecha introducida
	 */
	public FechaPosterior(LocalDate fecha)
	{
		super( "La fecha: " + fecha +  " es posterior a la actual");
		this.fecha = fecha;		
		
	}
}
