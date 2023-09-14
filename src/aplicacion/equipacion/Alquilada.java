/**
 * Este fichero muestra todo lo que tiene que ver con la clase Alquilada
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
package aplicacion.equipacion;
import java.io.Serializable;

import java.time.LocalDate;
import java.time.Month;

import aplicacion.excepciones.ExcepcionUsuario;
import aplicacion.excepciones.FechaPosterior;

/**
 * esta es una clase que representa la Alquilada
 * 
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class Alquilada extends Maquina implements Serializable{
	
	/**
	 * Clase para las maquinas alquiladas
	 * @param tipo el tipo de maquina
	 * @param descripcion la descripcion de la maquina
	 * @param marca la marca de la maquina
	 * @param precio el precio
	 * @param fecha la fecha de compra
	 * @throws FechaPosterior 
	 * @throws ExcepcionUsuario 
	 */
	public Alquilada(String tipo, String descripcion, String marca, Double precio, LocalDate fecha) throws ExcepcionUsuario, FechaPosterior {
		super(tipo, descripcion, marca, precio, fecha);
	}
	
	/**
	 * Comprueba si es alquilada
	 * @return true
	 */
	public Boolean esAlquilada(){
		return true;
	}
	
	/**
	 * Comprueba si es de Propiedad
	 * @return false
	 */
	public Boolean esPropiedad(){
		return false;
	}
	
	/**
	 * Funcion para mostrar los datos de la clase
	 * @return la cadena con los datos correspondientes
	 */
	@Override
	public String toString(){
		return super.toString() + " Precio por dia: " + this.getPrecio() + " Fecha Inicio Alquiler: " + this.getFecha();
	}

	/**
	 * Funcion para obtener el precio mensual de la maquina
	 * @param numDiasMes los dias del mes
	 * @return el precio
	 */
	@Override
	public Double getPrecioMensual(int numDiasMes) {
		if(numDiasMes<0){
			return null;
		}
		int dias = this.getNumDiasNoOperativa();
		
		if (this.getNumDiasNoOperativa()>=numDiasMes){
			dias = dias - numDiasMes;
		}
		
		return this.getPrecio() * (numDiasMes - dias);
	}

}
