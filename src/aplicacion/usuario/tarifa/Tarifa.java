/**
 * Este fichero muestra todo lo que tiene que ver con la clase Tarifa
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
package aplicacion.usuario.tarifa;

import java.io.Serializable;


/**
 * esta es una clase que representa Tarifa
 * 
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public abstract class Tarifa implements Serializable{
	private static Double precio;

	/**
	 * Constructor de Tarifa
	 * 
	 * @param precio precio de la tarifa
	 */
	public Tarifa(Double precio) {
		this.precio = precio;
	}

	/**
	 * Obtener el precio
	 * 
	 * @return el precio
	 */
	public Double getPrecio() {
		return this.precio;
	}

	/**
	 * Establecer el precio
	 * 
	 * @param precio el precio
	 * @return true si todo funciona bien
	 */
	public Boolean setPrecio(Double precio) {
		if (precio < 0)
			return false;

		this.precio = precio;
		return true;
	}

	/**
	 * Función para mostrar la tarifa
	 * 
	 * @return cadena
	 */
	@Override
	public String toString() {
		String cadena;

		cadena = "\nPrecio: " + getPrecio() + "\n";
		return cadena;
	}

}
