/**
 * Este fichero muestra todo lo que tiene que ver con la clase Propiedad
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
package aplicacion.equipacion;
import java.io.Serializable;
import java.time.LocalDate;

import aplicacion.excepciones.ExcepcionUsuario;
import aplicacion.excepciones.FechaPosterior;

/**
 * esta es una clase que representa la Propiedad
 * 
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class Propiedad extends Maquina implements Serializable{

	/**
	 * Contructor de la clase Propiedad
	 * @param tipo el tipo de maquina
	 * @param descripcion la descripcion de la maquina
	 * @param marca la marca
	 * @param precio el precio
	 * @param fecha la fecha de compra
	 * @throws FechaPosterior si la fecha es posterior
	 * @throws ExcepcionUsuario si el usuario no existe
	 */
	public Propiedad(String tipo, String descripcion, String marca, Double precio, LocalDate fecha) throws ExcepcionUsuario, FechaPosterior {
		super(tipo, descripcion, marca, precio, fecha);
	}
	
	/**
	 * Comprueba si es alquilada
	 * @return false
	 */
	public Boolean esAlquilada(){
		return false;
	}
	
	/**
	 * Comprueba si es Propiedad
	 * @return true
	 */
	public Boolean esPropiedad(){
		return true;
	}
	

	/**
	 * Funcion para obtener el precio por meses
	 * @param numDiasMes el numero de dias en un mes
	 * @return el precio
	 */
	@Override
	public Double getPrecioMensual(int numDiasMes){
		if(numDiasMes<0){
			return null;
		}
		/*Hacerlo con el mes*/
		return this.getPrecio();
	}
	
	/**
	 * Funcion para mostrar los datos por pantalla
	 * @return la cadena con los datos
	 */
	@Override
	public String toString(){
		return super.toString() + " Precio adquisicion: " + this.getPrecio() + " Fecha adquisicion: " + this.getFecha();
	}

}
