/**
 * Este fichero muestra todo lo que tiene que ver con la clase Requisitos
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
 * esta es una clase que representa la Requisitos
 * 
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public abstract class Requisito implements Serializable{
	private Integer  min;
	private Integer  max;


	/**
	 * Clase que comprueba los requisitos de la actividad
	 * @param min requisito minimo
	 * @param max requisito maximo
	 */
	public Requisito(Integer  min, Integer  max)
	{
		this.min = min;
		this.max = max;
	}

	/**
	 * Obtener el requisito minimo
	 * @return el requisito minimo
	 */
	public Integer  getMin()
	{
		return this.min;
	}

	/**
	 * Obtener el requisito maximo
	 * @return el requisito maximo
	 */
	public Integer  getMax()
	{
		return this.max;
	}

	/**
	 * Establecer el requisito minimo
	 * @param min el requisito minimo
	 * @return true si todo funciona bien
	 */
	public Boolean setMin(Integer min)
	{
		if(min < 0)
			return false;

		this.min = min;
		return true;
	}

	/**
	 * Establecer el requisito maximo
	 * @param max el requisito maximo
	 * @return true si todo funciona bien
	 */
	public Boolean setMax(Integer max)
	{
		if(max < 0)
			return false;

		this.max = max;
		return true;
	}


	/**
	 * Comprobar que el cliente cumple los requsitos
	 * @param cliente El cliente a comprobar
	 * @return true si todo funciona bien
	 */
	public abstract Boolean comprobar(Cliente cliente);

}
