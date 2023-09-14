/**
 * Este fichero muestra todo lo que tiene que ver con la clase SesionLibre
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

import aplicacion.Reserva;
import aplicacion.actividad.*;
import aplicacion.sala.Sala;
import aplicacion.sala.SalaSimple;
import aplicacion.actividad.sesion.requisito.*;
import aplicacion.usuario.*;

/**
 * esta es una clase que representa la SesionLibre
 * 
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class SesionLibre extends Sesion implements Serializable{

    /**
     * Constructor Sesion Libre
     * @param fecha dia de la sesion
     * @param horaIni hora inicial
     * @param horaFin hora final
     * @param sala salaSimple
     * @param precio precio de la sesion
     */
    public SesionLibre(LocalDate fecha, LocalDateTime horaIni, LocalDateTime horaFin, SalaSimple sala, Double precio)
	{
		super(fecha,horaIni,horaFin, sala, precio);
	}

   /**
	 * Funcion para comprobar que se cumplen los requisitos
	 * @param requisito El requisito
	 * @return true si se cumple
	 */
 	public Boolean comprobarRequisitoEdad(RequisitoEdad requisito, Cliente cliente)
	{
		if(requisito == null || cliente == null)
			return false;
			
		return requisito.comprobar(cliente);
	}
	
	/**
	 * Funcion para comprobar que se cumplen los requisitos
	 * @param requisito El requisito
	 * @return true si se cumple
	 */
 	public Boolean comprobarRequisitoVeterania(RequisitoVeterania requisito, Cliente cliente)
	{
		if(requisito == null || cliente == null)
			return false;
			
		return requisito.comprobar(cliente);
	}
	
	/**
	 * Funcion para comprobar que se cumplen los requisitos
	 * @param requisito El requisito
	 * @return true si se cumple
	 */
 	public Boolean comprobarRequisitoCancelaciones(RequisitoCancelaciones requisito, Cliente cliente)
	{
		if(requisito == null || cliente == null)
			return false;
			
		return requisito.comprobar(cliente);
	}
    
    /**
     * como no tiene actividad devuelve null
     * @return devuelve null
     */
    @Override
    public Actividad getActSesion()
	{
		return null;
	}
    
    /**
     * Funcion para mostrar por pantanlla los datos de la sesion
     * @return la cadena con los datos de la sesion
     */
	@Override
	public String toString(){
        return "\nSesion Libre: " + super.toString();
    }
}
