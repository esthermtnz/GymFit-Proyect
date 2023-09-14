/**
 * Este fichero muestra todo lo que tiene que ver con la clase Sala
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
package aplicacion.sala;

import java.time.*;
import java.util.*;

import aplicacion.actividad.sesion.*;
import java.io.Serializable;


/**
 * esta es una clase que representa sala
 * 
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public abstract class Sala implements Serializable{
	
	private String nombre;
	private String descripcion;
	private Integer  aforo;
	private HorarioClimatizacion horarioClimatizacion;


	/**
	 * Constructor de Sala
	 * @param nombre nombre de la sala
	 * @param descripcion descripcion de la sala
	 * @param aforo numero de personas que pueden reservar
	 */
	public  Sala(String nombre, String descripcion, Integer aforo){
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.aforo = aforo;
	}
	 
	/**
	 * Obtener el aforo
	 * @return el aforo
	 */
	public Integer  getAforo()
	{
		return this.aforo;
	}

	/**
	 * Obtener el nombre
	 * @return el nombre
	 */
	public String getNombre()
	{
		return this.nombre;
	}
	
	/**
	 * Obtener la descripcion
	 * @return la descripcion
	 */
	public String getDescripcion()
	{
		return this.descripcion;
	}

	/**
	 * Comporabar si está climatizada
	 * @return true si todo funciona bien
	 */
	public Boolean isClimatizada(){
		if(this.horarioClimatizacion==null){
			return false;
		}
		
		return true;
	}

	
	/**
	 * Establece el aforo
	 * @param aforo el aforo
	 * @return true si todo funciona bien
	 */
	public Boolean setAforo(Integer aforo)
	{
		if(aforo < 0)
			return false;

		this.aforo = aforo;
		return true;
	}

	/**
	 * Establece el nombre
	 * @param nombre el nombre
	 * @return true si todo funciona bien
	 */
	public Boolean setNombre(String nombre)
	{
		if(nombre == null)
			return false;

		this.nombre = nombre;
		return true;
	}

	/**
	 * Establece la descripcion
	 * @param descripcion la descripcion
	 * @return true si todo funciona bien
	 */
	public Boolean setDescripcion(String descripcion)
	{
		if(descripcion == null)
			return false;

		this.descripcion = descripcion;
		return true;
	}
	
	/**
	 * Define el horario de climatizacion de la sala
	 * @param horaini la hora inicial
	 * @param horafin la hora final
	 * @return  true si todo funciona bien
	 */
	public Boolean definirHorarioClimatizacion(LocalDateTime horaini, LocalDateTime horafin)
	{
		if(horafin.isBefore(horaini)==true || horaini.isBefore(LocalDateTime.now())==true ||  horafin.isBefore(LocalDateTime.now())==true){
			this.horarioClimatizacion=null;
			return false;
		}
			
		HorarioClimatizacion horarioClimatizacion = new HorarioClimatizacion(horaini, horafin);

		this.horarioClimatizacion = horarioClimatizacion;
		return true;
	}
	
	/**
	* GETTER
	* Obtiene el horario de climatizacion de la sala
	* @return el horario de climatizacion
	*/
	public HorarioClimatizacion getHorarioClimatizacion (){
		return this.horarioClimatizacion;
	}
	/**
	 * Comprobar si es sala simple
	 * @return true o false
	 */
    public abstract boolean isSimple();
    /**
     * COmprobar si es sala compuesta
     * @return true o false
     */
    public abstract boolean isCompuesta();
    
    /**
     * Funcion para mostrar la sala
     * @return cadena cadena
     */
    @Override
    public String toString() {
    	return "Sala:\n" + "Horario Climatizacion" + this.horarioClimatizacion;
    }

}
