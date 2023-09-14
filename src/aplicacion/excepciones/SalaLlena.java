/**
 * Este fichero muestra todo lo que tiene que ver con la clase SalaLlena
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
package aplicacion.excepciones;

import aplicacion.sala.Sala;

/**
 * esta es una clase que representa SalaLlena
 * 
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 */
public class SalaLlena extends Exception{
	private Sala sala;
	/**
	 * Excepcion para cuando la sala ha completado su aforo
	 * @param sala la sala de la excepcion
	 */
	public SalaLlena(Sala sala)
	{
		super("La sala " + sala.getNombre() + " esta llena. Apuntese a la lista de espera.");
		this.sala = sala;
	}
}
