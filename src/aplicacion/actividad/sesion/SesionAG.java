/**
 * Este fichero muestra todo lo que tiene que ver con la clase SesionAG
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
package aplicacion.actividad.sesion;



import java.io.Serializable;
import java.time.*;

import aplicacion.actividad.*;
import aplicacion.sala.Sala;
import aplicacion.sala.SalaSimple;



/**
 * esta es una clase que representa la SesionAG
 * 
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class SesionAG extends SesionMonitorizada implements Serializable{

	/**
	 * Constructor SesionAg
	 * @param fecha dia de la sesion
	 * @param horaIni hora inicial
	 * @param horaFin hora final
	 * @param actividad actividad
	 * @param sala salaSimple
	 * @param precio el precio de la sesion
	 */
    public SesionAG(LocalDate fecha, LocalDateTime horaIni, LocalDateTime horaFin, Actividad actividad, SalaSimple sala, Double precio)
	{
		super(fecha,horaIni,horaFin,actividad,sala, precio);
	}

}
