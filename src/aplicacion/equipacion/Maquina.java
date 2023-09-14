/**
 * Este fichero muestra todo lo que tiene que ver con la clase Maquina
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
package aplicacion.equipacion;
import java.util.*;

import aplicacion.Gimnasio;
import aplicacion.excepciones.ExcepcionUsuario;
import aplicacion.excepciones.FechaPosterior;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;

/**
 * esta es una clase que representa la Maquina
 * 
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public abstract class Maquina extends Equipacion implements Serializable {
	
	private String tipo;
	private String marca;
	private Integer id;
	private static Integer generaId = 0;
	private Estado estado;
	private Integer diasNoOperativa=0;
	private Calendar inicio;
	private Calendar fin;
	
	
	/**
	 * Constructor Clase para Maquina
	 * @param tipo el tipo de maquina
	 * @param descripcion la descripcion de la maquina
	 * @param marca la marca
	 * @param precio el precio
	 * @param fecha la fecha de compra
	 * @throws FechaPosterior 
	 * @throws ExcepcionUsuario 
	 */
	public Maquina(String tipo, String descripcion, String marca, Double precio, LocalDate fecha) throws ExcepcionUsuario, FechaPosterior{
		super(descripcion, precio, fecha);
		this.tipo = tipo;
		this.marca = marca;
		this.estado = Estado.OPERATIVA;
		generaId = Gimnasio.getGimnasio().maxId()+1;
		this.id = generaId++;
	}
	
	/**
	 * GETTER
	 * @return el tipo
	 */
	public String getTipo(){
		return this.tipo;
	}
	
	/**
	 * GETTER
	 * @return la marca
	 */
	public String getMarca(){
		return this.marca;
	}
	
	/**
	 * GETTER
	 * @return el id
	 */
	public Integer getId(){
		return this.id;
	}
	
	/**
	 * Funcion para obtener el precio por meses
	 * @param numDiasMes el numero de dias en un mes
	 * @return el precio
	 */
	@Override
	public abstract Double getPrecioMensual(int numDiasMes);
	
	/**
	 * GETTER
	 * @return el estado
	 */
	public Estado getEstado(){
		return this.estado;
	}
	
	/**
	 * GETTER
	 * @return el numero de dias no operativa
	 */
	public Integer getNumDiasNoOperativa(){
		return this.diasNoOperativa;
	}
	
	/**
	 * Establece el tipo
	 * @param tipo el tipo
	 * @return true si se ha podido establecer
	 */
	public Boolean setTipo(String tipo){
		if(tipo==null){
			return false;
		}
		this.tipo = tipo;
		return true;
	}
	
	/**
	 * Establece la marca
	 * @param marca la marca
	 * @return true si se ha podido establecer
	 */
	public Boolean setMarca(String marca){
		if(marca==null){
			return false;
		}
		this.marca = marca;
		return true;
	}
	
	/**
	 * Establece el estado
	 * @param estado el estado
	 * @return true si se ha podido establecer
	 */
	public Boolean setEstado(Estado estado){
		if(estado==null){
			return false;
		}
		
		if(this.getEstado()==Estado.OPERATIVA && estado.equals(this.getEstado())==false){
			this.inicio = Calendar.getInstance();
			
		}else if(this.getEstado()!=Estado.OPERATIVA && estado.equals(this.getEstado())==false){
			this.fin = Calendar.getInstance();
			int dias = (int) Math.round((this.fin.getTimeInMillis()-this.inicio.getTimeInMillis())) / (1000*60*60*24);
			this.diasNoOperativa += dias;
			this.inicio.clear();
			this.fin.clear();
		}
		this.estado = estado;
		return true;
	}
	
	
	
	/**
	 * Comprueba si es maquina
	 * @return true
	 */
	public Boolean isMaquina(){
		return true;
	}
	
	/**
	 * Comprueba si es material
	 * @return false
	 */
	public Boolean isMaterial(){
		return false;
	}
	
	/**
	 * Comprueba si es de porpiedad
	 * @return true o false
	 */
	public abstract Boolean esPropiedad();
	/**
	 * Comprueba si es alquilada
	 * @return true o false
	 */
	public abstract Boolean esAlquilada();
	
	/**
	 * Funcion para mostrar los datos por pantalla
	 * @return la cadena con los datos
	 */
	@Override
	public String toString(){
		return "Tipo: " + this.tipo + " Descripcion: " + this.getDescripcion() + " Marca: " + this.marca + " Id:" + this.id + " Estado: " + this.getEstado();
	}
}
