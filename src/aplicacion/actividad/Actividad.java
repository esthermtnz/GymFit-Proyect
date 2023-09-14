/**
 * Este fichero muestra todo lo que tiene que ver con la clase Actividad
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */

package aplicacion.actividad;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import aplicacion.actividad.sesion.Sesion;
import aplicacion.actividad.sesion.SesionMonitorizada;
import aplicacion.actividad.sesion.requisito.Requisito;
import aplicacion.excepciones.FueraHorarioClimatizacion;
import aplicacion.usuario.Monitor;
import aplicacion.sala.Sala;
import aplicacion.sala.SalaSimple;
import java.io.Serializable;

/**
 * esta es una clase que representa la actividad 
 * 
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public abstract class Actividad implements Serializable{
	private String nombre;
	private Monitor monitor;
	private Double precio;

	private List<SesionMonitorizada> sesionesMonitorizadas = new ArrayList<SesionMonitorizada>();
	private List<Requisito> requisitos = new ArrayList<Requisito>();

	/**
	 * Esta es la clase donde se guardaran toda clase de actividades
	 * @param nombre de la actividad
	 * @param monitor que impartirá la actividad
	 * @param requisito el requisito establecido para la actividad
	 */
	public Actividad(String nombre, Monitor monitor, Requisito requisito) {
		this.nombre = nombre;
		this.monitor = monitor;

		this.addRequisito(requisito);
	}

	/**
	 * Obtener el nombre de la actividad
	 * @return el nombre
	 */
	public String getNombre() {
		return this.nombre;
	}

	/**
	 * Establecer el nombre de la actividad
	 * @param nombre de la actividad
	 * @return true si va bien, false si no.
	 */
	public Boolean setNombre(String nombre) {
		if (nombre == null)
			return false;

		this.nombre = nombre;
		return true;
	}

	/**
	 * Obtener los requisitos de la actividad
	 * @return los requisitos
	 */
	public List<Requisito> getRequisitos() {
		return this.requisitos;
	}

	/**
	 * Crear la sesión de la actividad
	 * @param fecha dia de la sesión
	 * @param horaIni hora inicial
	 * @param horaFin hora final
	 * @param salaSimple la sala donde se hará
	 * @param monitor el monitor que imparte la actividad
	 * @throws FueraHorarioClimatizacion
	 * @return la sesion
	 */
	public abstract Sesion crearSesion(LocalDate fecha, LocalDateTime horaIni, LocalDateTime horaFin, SalaSimple salaSimple, Monitor monitor) throws FueraHorarioClimatizacion;

	/**
	 * Añade el requsito a la actividad
	 * @param requisito el requisito
	 * @return true si funciona bien
	 */
	public Boolean addRequisito(Requisito requisito) {
		if (requisito == null|| requisitos.contains(requisito)==true) {
			return false;
		}

		return requisitos.add(requisito);
	}

	/**
	 * Obtener las sesiones monitorizados
	 * @return sesiones monitorizadas
	 */
	public List<SesionMonitorizada> getSesionesMonitorizadas() {
		return this.sesionesMonitorizadas;
	}

	/**
	 * Añade sesiones monitorizados
	 * @param sesionMonitorizada sesion monitorizada para añadir
	 * @return true si funciona bien.
	 */
	public Boolean addSesionMonitorizada(SesionMonitorizada sesionMonitorizada) {
		if (sesionMonitorizada == null || actividadesContieneSesionMonitorizada(sesionMonitorizada) == true)
			return false;

		return sesionesMonitorizadas.add(sesionMonitorizada);
	}
	
	/**
	 * Ver si ya está creada la sesion
	 * @param sesionMonitorizada sesion a comprobar
	 * @return true si funciona bien
	 */
	public Boolean actividadesContieneSesionMonitorizada(SesionMonitorizada sesionMonitorizada)
	{
		if(sesionMonitorizada == null)
				return false;
		
		return sesionesMonitorizadas.contains(sesionMonitorizada);
	}
	
	/**
	 * GETTER funcion para obtener el monitor
	 * @return el monitor
	 */
	public Monitor getMonitor(){
		return this.monitor;
	}
	
	/**
	 * Obtener el precio de la actividad
	 * @return precio
	 */
	public Double getPrecio()
	{
		return this.precio;
	}
	
	/**
	 * Estbalecer el precio
	 * @param precio precio de la actividad
	 * @return true si funciona bien
	 */
	public Boolean setPrecio(Double precio)
	{
		if(precio < 0)
			return false;
		
		this.precio = precio;
		return true;
	}
	
	public abstract Boolean isActividadGrupal();
	public abstract Boolean isPlanPersonalizado();
	
	@Override
	public String toString() {
		String cadena = "";
		cadena += "Nombre: " + this.nombre + "/n SESIONES: " + this.sesionesMonitorizadas;
		return cadena;
	}
}

