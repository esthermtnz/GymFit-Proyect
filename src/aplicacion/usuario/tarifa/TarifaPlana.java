/**
 * Este fichero muestra todo lo que tiene que ver con la clase TarifaPlana
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
package aplicacion.usuario.tarifa;

import java.io.Serializable;
import java.time.*;

import aplicacion.actividad.sesion.*;
import aplicacion.actividad.TipoActividad;

/**
 * esta es una clase que representa TarifaPlana
 * 
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class TarifaPlana extends Tarifa implements Serializable{
	private Boolean caducada;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;

	private TipoActividad tipoActividad;
	private TipoTarifaPlana tipoTarifaPlana;

	/**
	 * Constructor de Tarifa Plana
	 * @param fechaInicio fecha de inicio de la tarifa
	 * @param fechaFin fecha fin
	 * @param precio el precio de la tarifa
	 * @param tipotarifaplana el tipo de tarifa plana
	 * @param tipoActividad el tipo de actividad
	 */
	public TarifaPlana(LocalDate fechaInicio, LocalDate fechaFin, Double precio, TipoTarifaPlana tipotarifaplana, TipoActividad tipoActividad)
	{
		super(precio);
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.tipoTarifaPlana = tipotarifaplana;
		this.tipoActividad = tipoActividad;
		this.caducada = false;
	}

	/**
	 * Obtener si estÃ¡ caducada
	 * @return si estÃ¡ caducada
	 */
	public Boolean getCaducada()
	{
		return this.caducada;
	}

	/**
	 * Establecer caducada
	 * @param caducada boolean de si estÃ¡ caducada
	 * @return true si todo funciona bien
	 */
	public Boolean setCaducada(Boolean caducada)
	{
			this.caducada = caducada;
			return true;
	}

	/**
	 * Obtener la fecha de inicio
	 * @return la fecha de inicio
	 */
	public LocalDate getFechaInicio()
	{
		return this.fechaInicio;
	}

	/**
	 * Establecer la fecha de inicio
	 * @param fechaInicio la fecha de inicio
	 * @return true si todo funciona bien
	 */
	public Boolean setFechaInicio(LocalDate fechaInicio)
	{
		if(fechaInicio.compareTo(LocalDate.now())<0)
			return false;

		this.fechaInicio = fechaInicio;
		return true;
	}

	/**
	 * Obtener la fecha fin
	 * @return la fecha fin
	 */
	public LocalDate getFechaFin()
	{
		return this.fechaFin;
	}
	
	/**
	 * Obtener el tipo de actividad
	 * @return el tipo de actividad
	 */
	public TipoActividad getTipoActividad()
	{
		return this.tipoActividad;
	}
	
	/**
	 * SETTER
	 * @param tipo el tipo de tarifa
	 */
	public void setTipoActividad(TipoActividad tipo) {
		this.tipoActividad = tipo;
	}

	/**
	 * Establecer la fecha fin
	 * @param fechaFin la fecha fin
	 * @return true si todo funciona bien
	 */
	public Boolean setFechaFin(LocalDate fechaFin)
	{
		if(fechaFin.compareTo(LocalDate.now())<0 || fechaFin.compareTo(getFechaInicio())<0)
			return false;

		this.fechaFin = fechaFin;
		return true;
	}
	
	/**
	 * Obtener el tipoTarifaPlana
	 * @return el tipoTarifaPlana
	 */
	public TipoTarifaPlana getTipoTarifaPlana()
	{
		return this.tipoTarifaPlana;
	}

	/**
	 * Establecer el tipoTarifaPlana
	 * @param tipoTarifaPlana el tipoTarifaPlana
	 * @return true si todo funciona bien
	 */
	public Boolean setTipoTarifaPlana(TipoTarifaPlana tipoTarifaPlana)
	{
		if(tipoTarifaPlana==null)
			return false;

		this.tipoTarifaPlana = tipoTarifaPlana;
		return true;
	}
	
	/**
	 * FunciÃ³n para renovar
	 * @param tiempo el tiempo que se quiere renovar
	 * @param tipoActividad el tipo de actividad
	 * @return true si todo funciona bien
	 */
	public Boolean renovar(Integer  tiempo, TipoActividad tipoActividad){
		if(this.caducada == false || tiempo < 0)
			return false;
		
		this.tipoActividad = tipoActividad;
		this.fechaFin = this.fechaFin.plusDays(tiempo);
		this.caducada = false;
		
		
		
		return true;
	}
	
	/**
	 * 	FunciÃ³n para calcular el descuento
	 * @param descuento el descuento de la tarifa
	 * @return el precio
	 */
	public Double descuento(Double descuento){
		if(descuento<0){
			return -1.0;
		}
		
		super.setPrecio((super.getPrecio() - (descuento/100)*super.getPrecio()));
		
		return super.getPrecio();
	}
	
	/**
	 * Calcular el precio
	 * @param sesionMonitorizada Sesion monitorizada
	 * @return el precio
	 */
	public Double precioDefinitivo(SesionMonitorizada sesionMonitorizada)
	{
		if(sesionMonitorizada==null)
			return -1.0;
			
		super.setPrecio(super.getPrecio() - sesionMonitorizada.getPrecio());
		
		return super.getPrecio();	
	}
	
	/**
	 * FunciÃ³n para mostrar la tarifaPlana
	 * @return cadena
	 */
	@Override
	public String toString(){
		String cadena = super.toString();
		cadena += "Fecha Inicio: " + this.fechaInicio + "\nFecha Fin: " + this.fechaFin + "\nCaducada: " + this.caducada + this.tipoActividad + "\nTipo Tarifa Plana: " + this.tipoTarifaPlana;
		return cadena;
	}


}

