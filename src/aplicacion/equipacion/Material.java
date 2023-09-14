/**
 * Este fichero muestra todo lo que tiene que ver con la clase Material
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

/**
 * esta es una clase que representa la Material
 * 
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class Material extends Equipacion implements Serializable{
	private Integer numUnidades;
	/**
	 * Constructor de la clase material
	 * @param descripcion la descripcion del material
	 * @param numUnidades el numero de unidades del material
	 * @param fechaCompra la fecha de compra
	 * @param precio el precio
	 */
	public Material(String descripcion, Integer numUnidades, LocalDate fechaCompra, Double precio)
	{
		super(descripcion, precio, fechaCompra);
		this.numUnidades = numUnidades;
	}
	
	/**
	 * GETTER
	 * @return el numero de unidades
	 */
	public Integer getNumUnidades(){
		return this.numUnidades;
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
		return this.getPrecio()*this.getNumUnidades();
	}
	
	/**
	 * Establece el numero de unidades
	 * @param numero el numero de unidades
	 */
	public void setNumUnidades(Integer numero){
		if(numero<0)
			return;
		this.numUnidades = numero;
	}
	
	/**
	 * Comprueba si es maquina
	 * @return false
	 */
	public Boolean isMaquina(){
		return false;
	}
	
	/**
	 * Comprueba si es material
	 * @return true
	 */
	public Boolean isMaterial(){
		return true;
	}
}
