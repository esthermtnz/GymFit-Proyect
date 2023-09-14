/**
 * Este fichero muestra todo lo que tiene que ver con la clase SesionPP
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
import aplicacion.sala.*;

/**
 * esta es una clase que representa la SesionPP
 * 
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class SesionPP extends SesionMonitorizada implements Serializable{
	
	/**
	 * Constructor de sesion PP
	 * @param fecha dia de la sesion
	 * @param horaIni hora inicial
	 * @param horaFin hora final
	 * @param actividad la actividad
	 * @param sala la sala de la sesion
	 * @param precio el precio de la sesion
	 */
    public SesionPP(LocalDate fecha, LocalDateTime horaIni, LocalDateTime horaFin, Actividad actividad, SalaSimple sala, Double precio)
	{
		super(fecha,horaIni, horaFin,actividad,sala, precio);
	}
	
}
