/**
 * Este fichero muestra todo lo que tiene que ver con la clase Equipacion
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
package aplicacion.equipacion;
import java.time.LocalDate;
import java.time.Month;
import java.io.Serializable;

/**
 * esta es una clase que representa la Equipacion
 * 
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public abstract class Equipacion implements Serializable{
	private String descripcion;
	private LocalDate fecha;
	private Double precio;
	
	/**
	 * Funcion para guardar los datos de la equipacion del gimnasio
	 * @param descripcion la descripcion de la equipacion
	 * @param precio el precio de la equipacion
	 * @param fecha la fecha de compra
	 */
	public Equipacion(String descripcion, Double precio, LocalDate fecha){
		this.descripcion = descripcion;
		this.fecha = fecha;
		this.precio = precio;
	}
	
	/**
	 * GETTER devuelve la decripcion
	 * @return la decripcion
	 */
	public String getDescripcion(){
		return this.descripcion;
	}
	
	/**
	 * GETTER devuelve el precio 
	 * @return el precio
	 */
	public Double getPrecio(){
		return this.precio;
	}
	
	/**
	 * Para obtener el precio por meses de la equipacion
	 * @param numDiasMes los dias del mes
	 * @return el precio
	 */
	public abstract Double getPrecioMensual(int numDiasMes);
	
	/**
	 * GETTER obtener la fecha
	 * @return la fecha
	 */
	public LocalDate getFecha(){
		return this.fecha;
	}
	
	
	/**
	 * Establecer la descripcion
	 * @param descripcion la descripcion
	 * @return true si se ha podido establecer
	 */
	public Boolean setDescripcion(String descripcion){
		if(descripcion==null){
			return false;
		}
		this.descripcion = descripcion;
		return true;
	}
	
	/**
	 * Establecer el precio
	 * @param precio el precio
	 * @return true si se ha podido establecer
	 */
	public Boolean setPrecio(Double precio){
		if(precio==null){
			return false;
		}
		this.precio = precio;
		return true;
	}
	
	/**
	 * Establecer la fecha de compra
	 * @param fecha la fecha
	 * @return true si se ha podido establecer
	 */
	public Boolean setFecha(LocalDate fecha){
		if(fecha==null){
			return false;
		}
		this.fecha = fecha;
		return true;
	}
	
	/**
	 * Comprobar si es maquina
	 * @return true o false
	 */
	public abstract Boolean isMaquina();
	/**
	 * Comprobar si es Material
	 * @return true o false;
	 */
	public abstract Boolean isMaterial();
}
