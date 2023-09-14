/**
 * Este fichero muestra todo lo que tiene que ver con la clase Plan Personalizado
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
package aplicacion.actividad;

import java.io.Serializable;
import java.time.*;
import java.util.*;

import aplicacion.Gimnasio;
import aplicacion.actividad.sesion.*;
import aplicacion.actividad.sesion.requisito.Requisito;
import aplicacion.excepciones.FueraHorarioClimatizacion;
import aplicacion.sala.SalaSimple;
import aplicacion.usuario.*;


/**
 * esta es una clase que representa el plan personalizado
 * 
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class PlanPersonalizado extends Actividad implements Serializable{
    private TipoObjetivo tipoObjetivo;
    
    private String descripcion;
    private ListaEspera listaEspera;
    
    
    /**
     * Clase que se encarga de crear todo lo relacionado con el plan personalizado
     * @param nombre del plan
     * @param monitor que impartirá el plan
     * @param requisito requisitos del plan 
     * @param tipoObjetivo objetivo del plan
     * @param descripcion del plan
     */
    public PlanPersonalizado(String nombre, Monitor monitor, Requisito requisito, TipoObjetivo tipoObjetivo,String descripcion)
    {
    	super(nombre,monitor,requisito);
    	this.setPrecio(0.0);/*EL PRECIO ES LA SUMA DEL PRECIO QUE CONTIENE*/
    	this.descripcion = descripcion;
        this.tipoObjetivo = tipoObjetivo;
       
    }
    
	
	/**
	 * Obtener el objetivo del plan
	 * @return objetivo
	 */
    public TipoObjetivo getTipoObjetivo()
    {
        return this.tipoObjetivo;
    }
    
 
    /**
     * Decidir el plan
     * @param objetivo para crear el plan
     * @return true si va todo bien
     */
    public Boolean decidePlan(TipoObjetivo objetivo)
    {
        if(objetivo == null || !(objetivo == TipoObjetivo.GANANCIAMUSCULAR || objetivo == TipoObjetivo.PERDIDAPESO || objetivo ==TipoObjetivo.REHABILITACION))
            return false;
        
        this.tipoObjetivo  = objetivo;
        return true;
    }
    
    /**
     * Funcion para crear sesion de plan personalizado
	 * @param fecha dia del plan
	 * @param horaIni hora inicial
	 * @param horaFin hora final
	 * @param salaSimple la sala del plan
	 * @throws FueraHorarioClimatizacion excepcion fuera del horario
	 * @return sesion del plan
	 */
    public SesionPP crearSesion(LocalDate fecha, LocalDateTime horaIni, LocalDateTime horaFin, SalaSimple salaSimple, Monitor monitor) throws FueraHorarioClimatizacion{

    	if(salaSimple.getHorarioClimatizacion() == null) {
    		Double precio  = 15.99;
	        SesionPP sesion = new SesionPP(fecha, horaIni, horaFin,this, salaSimple, precio);
			this.setPrecio(this.getPrecio() + precio);
			
			this.addSesionMonitorizada(sesion);
			
			return sesion;
    	}
        if (fecha.compareTo(LocalDate.now()) < 0 || horaIni.isBefore(LocalDateTime.now()) || horaIni.isAfter(horaFin)
				|| horaFin.isBefore(LocalDateTime.now()) || monitor == null || horaIni.equals(horaFin)){
				return null;	
				}
        
        for(int i=0; i<monitor.getPlanesPersonalizados().size(); i++){
        	for(int j=0 ; j<monitor.getPlanesPersonalizados().get(i).getSesionesMonitorizadas().size(); j++) {
				if(monitor.getPlanesPersonalizados().get(i).getSesionesMonitorizadas().get(i).getHoraInicio().equals(horaIni) && (monitor.getPlanesPersonalizados().get(i).getSesionesMonitorizadas().get(i).getFecha().equals(fecha))){
					return null;
				}
        	}
		}
        
        for (Sesion sesion : salaSimple.getSesiones()) {
			if (sesion.getFecha().equals(fecha) && sesion.getHoraInicio().equals(horaIni)
					&& sesion.getHoraFin().equals(horaFin)) {
				return null;
			}
		}
        
        if (horaIni.isAfter(salaSimple.getHorarioClimatizacion().getHoraFin()) && horaFin.isAfter(salaSimple.getHorarioClimatizacion().getHoraFin())) {
        	Double precio  = 15.99;
            SesionPP sesion = new SesionPP(fecha, horaIni, horaFin,this, salaSimple, precio);
    		this.setPrecio(this.getPrecio() + precio);
    		
    		this.addSesionMonitorizada(sesion);
    		
    		return sesion;
		}else if(horaIni.isBefore(salaSimple.getHorarioClimatizacion().getHoraIni()) && horaFin.isBefore(salaSimple.getHorarioClimatizacion().getHoraIni())){
			Double precio  = 15.99;
	        SesionPP sesion = new SesionPP(fecha, horaIni, horaFin,this, salaSimple, precio);
			this.setPrecio(this.getPrecio() + precio);
			
			this.addSesionMonitorizada(sesion);
			
			return sesion;
		}else if((horaIni.isAfter(salaSimple.getHorarioClimatizacion().getHoraIni()) && horaFin.isBefore(salaSimple.getHorarioClimatizacion().getHoraFin()))
				|| (horaIni.isAfter(salaSimple.getHorarioClimatizacion().getHoraIni()) && horaFin.equals(salaSimple.getHorarioClimatizacion().getHoraFin()))
				|| (horaIni.equals(salaSimple.getHorarioClimatizacion().getHoraIni()) && horaFin.isBefore(salaSimple.getHorarioClimatizacion().getHoraFin()))) {
			Double precio  = 15.99;
	        SesionPP sesion = new SesionPP(fecha, horaIni, horaFin,this, salaSimple, precio);
			this.setPrecio(this.getPrecio() + precio);
			
			this.addSesionMonitorizada(sesion);
			
			return sesion;
		}else if(horaIni.equals(salaSimple.getHorarioClimatizacion().getHoraIni()) && horaFin.equals(salaSimple.getHorarioClimatizacion().getHoraFin())) {
			Double precio  = 15.99;
	        SesionPP sesion = new SesionPP(fecha, horaIni, horaFin,this, salaSimple, precio);
			this.setPrecio(this.getPrecio() + precio);
			
			this.addSesionMonitorizada(sesion);
			
			return sesion;
		}else {
			throw new FueraHorarioClimatizacion();
		}
        
		
    }
    
    
    /**
     * Comporbar si ya existe el plan
     * @param sesion sesion a comprobar
     * @return false si no existe y true si sí existe
     */
    public Boolean sesionesContieneSesion(Sesion sesion)
	{
		if(sesion == null)
    		return false;
    	
    	return this.getSesionesMonitorizadas().contains(sesion);
	}
	
    /**
	 * Comprueba si es un plan personalizado
	 * @return true
	 */
	@Override
	public Boolean isPlanPersonalizado(){
		return true;
	}
	
	/**
	 * Comprueba si es una actividad Grupal
	 * @return false
	 */
	@Override
	public Boolean isActividadGrupal(){
		return false;
	}
	
	/**
	 * Funcion para mostrar los datos dl plan personalizado
	 * @return la cadena con todos los datos
	 */
	@Override
	public String toString(){
		return super.toString() + "Plan personalizado: \n" + "descripcion: " + this.descripcion + "Tipo Objetivo" + this.tipoObjetivo;
	}
}
