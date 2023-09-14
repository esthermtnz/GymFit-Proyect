/**
 * Este fichero muestra todo lo que tiene que ver con la clase HorarioClimatizacion
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
package aplicacion.sala;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * esta es una clase que representa el horario de climatizacion 
 * 
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class HorarioClimatizacion implements Serializable{
	private LocalDateTime horaini;
	private LocalDateTime horafin;

	/**
	 * Constructor De Horario Climatizacion
	 * @param horaini hora inicial
	 * @param horafin hora final
	 */
	public  HorarioClimatizacion(LocalDateTime horaini, LocalDateTime horafin){
		this.horaini = horaini;
		this.horafin = horafin;
	}

	/**
	 * Obtener la hora inicial
	 * @return hora inicial
	 */
	public LocalDateTime getHoraIni()
	{
		return this.horaini;
	}

	/**
	 * Obtener la hora final
	 * @return la hora final
	 */
	public LocalDateTime getHoraFin()
	{
		return this.horafin;
	}

	/**
	 * Establece el hora inicial
	 * @param hora la hora inicial
	 * @return true si todo funciona bien
	 */
	public Boolean setHoraIni(LocalDateTime hora)
	{
		if(hora.isBefore(LocalDateTime.now())==true)
			return false;
		
			this.horaini = hora;
			return true;

		
	}

	/**
	 * Establece la hora final
	 * @param hora la hora final
	 * @return true si todo funciona bien
	 */
	public Boolean setHoraFin(LocalDateTime hora)
	{
		if(hora.isBefore(LocalDateTime.now())==true)
			return false;

		this.horafin = hora;
		return true;
	}
	
	/**
	 * Funcion para mostrar los datos de la clase por pantalla
	 * @return la cadena con los datos
	 */
	@Override
	public String toString(){
		return "\nHora Inicio: " +this.horaini + "\nHora fin: " + this.horafin;
	}
}


