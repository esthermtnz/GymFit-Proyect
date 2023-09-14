/**
 * Este fichero muestra todo lo que tiene que ver con la clase SesionMonitorizada
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
package aplicacion.actividad.sesion;
import java.io.Serializable;
import java.time.*;
import java.util.*;
import aplicacion.actividad.*;
import aplicacion.actividad.sesion.requisito.*;
import aplicacion.sala.*;
import aplicacion.sala.SalaSimple;
import aplicacion.usuario.*;

/**
 * esta es una clase que representa la SesionMonitorizada
 * 
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public abstract class SesionMonitorizada extends Sesion implements Serializable{

	private Actividad actividad;
	
	/**
	 * Constructor de sesion monitorizada
	 * @param fecha dia de la sesion
	 * @param horaIni hora inicial
	 * @param horaFin hora final
	 * @param actividad activdad
	 * @param sala sala de la sesion
	 * @param precio precio de la sesion
	 */
    public SesionMonitorizada(LocalDate fecha, LocalDateTime horaIni, LocalDateTime horaFin, Actividad actividad, SalaSimple sala, Double precio)
	{
		super(fecha,horaIni,horaFin, sala, precio);
		this.actividad = actividad;
	}
    
    /**
     * Obtener la actividad
     * @return la actividad
     */
    public Actividad getActividad()
    {
    	return this.actividad;
    }
    
    /**
     * coge la actividad de esta sesion
     * @return la actividad
     */
    @Override
    public Actividad getActSesion()
	{
		return this.actividad;
	}
	
    
    /**
     * Establece la actividad
     * @param actividad la actividad
     * @return true si todo funciona bien
     */
    public Boolean setActividad(Actividad actividad)
    {
    	if(actividad==null)
    		return false;
    	
    	this.actividad = actividad;
    	return true;
    }
    
   
    
}
