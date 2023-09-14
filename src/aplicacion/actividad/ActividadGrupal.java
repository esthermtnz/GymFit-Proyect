/**
 * Este fichero muestra todo lo que tiene que ver con la clase Actividad Grupal
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

import aplicacion.actividad.sesion.*;
import aplicacion.actividad.sesion.requisito.*;
import aplicacion.excepciones.FueraHorarioClimatizacion;
import aplicacion.sala.HorarioClimatizacion;
import aplicacion.sala.Sala;
import aplicacion.sala.SalaSimple;
import aplicacion.usuario.*;

/**
 * esta es una clase que representa la actividad grupal
 * 
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class ActividadGrupal extends Actividad implements Serializable {
	private TipoActividad tipoActividad;

	/**
	 * Clase que crea la actividad grupal
	 * 
	 * @param nombre        de la actividad de grupal
	 * @param monitor       que impartirá la actividad
	 * @param requisito     de la actividad
	 * @param tipoActividad el tipo de actividad
	 */
	public ActividadGrupal(String nombre, Monitor monitor, Requisito requisito, TipoActividad tipoActividad) {
		super(nombre, monitor, requisito);
		this.setPrecio(40.0);
		this.tipoActividad = tipoActividad;
	}

	/**
	 * Funcion para crear la rutina de la sesiones
	 * 
	 * @param numSesiones el numero de sesiones que se van a crear
	 * @param fechaInicio la fecha de incio
	 * @param horaIni     el horario de inicio
	 * @param horaFin     el horario de fin
	 * @param salaSimple  la sala donde se hara
	 * @param monitor     el monitor que las impartira
	 * @return true si se han podido crear la rutina de sesiones
	 * @throws FueraHorarioClimatizacion si esta fuera del hoarrio de climatizacion
	 */
	public Boolean crearRutinaSesiones(int numSesiones, LocalDate fechaInicio, LocalDateTime horaIni,
			LocalDateTime horaFin, SalaSimple salaSimple, Monitor monitor) throws FueraHorarioClimatizacion {
		/* Asumimos una repeticion semanal */
		if (numSesiones <= 0 || fechaInicio == null || fechaInicio.isBefore(LocalDate.now()) == true
				|| salaSimple == null || monitor == null || horaIni.equals(horaFin)) {
			return false;
		}

		for (int i = 0; i < numSesiones; i++) {
			if (crearSesion(fechaInicio, horaIni, horaFin, salaSimple, monitor) == null) {
				return false;
			}
			fechaInicio = fechaInicio.plusDays(7);
		}
		return true;
	}

	/**
	 * Funcion para crear la sesion
	 * 
	 * @param fecha      dia de la sesion
	 * @param horaIni    hora inicial
	 * @param horaFin    hora final
	 * @param salaSimple la sala donde se hará
	 * @param monitor    el monitor que impartira la sesion
	 * @return la sesion grupal
	 * @throws FueraHorarioClimatizacion
	 */
	public SesionAG crearSesion(LocalDate fecha, LocalDateTime horaIni, LocalDateTime horaFin, SalaSimple salaSimple,
			Monitor monitor) throws FueraHorarioClimatizacion {
		
		if(salaSimple.getHorarioClimatizacion() == null) {
			if (salaSimple.getSesiones().size() == 0) {
				SesionAG sesionGrupal = new SesionAG(fecha, horaIni, horaFin, this, salaSimple, this.getPrecio());
				this.addSesionMonitorizada(sesionGrupal);
				return sesionGrupal;
			} else {
				SesionAG sesionGrupal = new SesionAG(fecha, horaIni, horaFin, this, salaSimple, this.getPrecio());
				for (Sesion sesion : salaSimple.getSesiones()) {
					if ((sesion.getFecha().equals(fecha) && sesion.getHoraInicio().equals(horaIni))
							|| (sesion.getFecha().equals(fecha) && sesion.getHoraFin().isAfter(horaIni)))
						return null;
					else {
						this.addSesionMonitorizada(sesionGrupal);
						return sesionGrupal;
					}
				}
			}
		}
		if (fecha.compareTo(LocalDate.now()) < 0 || horaIni.isBefore(LocalDateTime.now()) || horaIni.isAfter(horaFin)
				|| horaFin.isBefore(LocalDateTime.now()) || monitor == null){
				return null;	
				}
				

		for (int i = 0; i < monitor.getActividadesGrupales().size(); i++) {
			for (int j = 0; j < monitor.getActividadesGrupales().get(i).getSesionesMonitorizadas().size(); j++) {
				if (monitor.getActividadesGrupales().get(i).getSesionesMonitorizadas().get(j).getFecha().equals(fecha)
						&& monitor.getActividadesGrupales().get(i).getSesionesMonitorizadas().get(j).getHoraInicio()
								.equals(horaIni)) {
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
			if (salaSimple.getSesiones().size() == 0) {
				SesionAG sesionGrupal = new SesionAG(fecha, horaIni, horaFin, this, salaSimple, this.getPrecio());
				this.addSesionMonitorizada(sesionGrupal);
				return sesionGrupal;
			} else {
				SesionAG sesionGrupal = new SesionAG(fecha, horaIni, horaFin, this, salaSimple, this.getPrecio());
				for (Sesion sesion : salaSimple.getSesiones()) {
					if ((sesion.getFecha().equals(fecha) && sesion.getHoraInicio().equals(horaIni))
							|| (sesion.getFecha().equals(fecha) && sesion.getHoraFin().isAfter(horaIni)))
						return null;
					else {
						this.addSesionMonitorizada(sesionGrupal);
						return sesionGrupal;
					}
				}
			}
		}else if(horaIni.isBefore(salaSimple.getHorarioClimatizacion().getHoraIni()) && horaFin.isBefore(salaSimple.getHorarioClimatizacion().getHoraIni())){
			if (salaSimple.getSesiones().size() == 0) {
				SesionAG sesionGrupal = new SesionAG(fecha, horaIni, horaFin, this, salaSimple, this.getPrecio());
				this.addSesionMonitorizada(sesionGrupal);
				return sesionGrupal;
			} else {
				SesionAG sesionGrupal = new SesionAG(fecha, horaIni, horaFin, this, salaSimple, this.getPrecio());
				for (Sesion sesion : salaSimple.getSesiones()) {
					if ((sesion.getFecha().equals(fecha) && sesion.getHoraInicio().equals(horaIni))
							|| (sesion.getFecha().equals(fecha) && sesion.getHoraFin().isAfter(horaIni)))
						return null;
					else {
						this.addSesionMonitorizada(sesionGrupal);
						return sesionGrupal;
					}
				}
			}
		}else if((horaIni.isAfter(salaSimple.getHorarioClimatizacion().getHoraIni()) && horaFin.isBefore(salaSimple.getHorarioClimatizacion().getHoraFin()))
				|| (horaIni.isAfter(salaSimple.getHorarioClimatizacion().getHoraIni()) && horaFin.equals(salaSimple.getHorarioClimatizacion().getHoraFin()))
				|| (horaIni.equals(salaSimple.getHorarioClimatizacion().getHoraIni()) && horaFin.isBefore(salaSimple.getHorarioClimatizacion().getHoraFin()))) {
			if (salaSimple.getSesiones().size() == 0) {
				SesionAG sesionGrupal = new SesionAG(fecha, horaIni, horaFin, this, salaSimple, this.getPrecio());
				this.addSesionMonitorizada(sesionGrupal);
				return sesionGrupal;
			} else {
				SesionAG sesionGrupal = new SesionAG(fecha, horaIni, horaFin, this, salaSimple, this.getPrecio());
				for (Sesion sesion : salaSimple.getSesiones()) {
					if ((sesion.getFecha().equals(fecha) && sesion.getHoraInicio().equals(horaIni))
							|| (sesion.getFecha().equals(fecha) && sesion.getHoraFin().isAfter(horaIni)))
						return null;
					else {
						this.addSesionMonitorizada(sesionGrupal);
						return sesionGrupal;
					}
				}
			}
		}else if(horaIni.equals(salaSimple.getHorarioClimatizacion().getHoraIni()) && horaFin.equals(salaSimple.getHorarioClimatizacion().getHoraFin())) {
			if (salaSimple.getSesiones().size() == 0) {
				SesionAG sesionGrupal = new SesionAG(fecha, horaIni, horaFin, this, salaSimple, this.getPrecio());
				this.addSesionMonitorizada(sesionGrupal);
				return sesionGrupal;
			} else {
				SesionAG sesionGrupal = new SesionAG(fecha, horaIni, horaFin, this, salaSimple, this.getPrecio());
				for (Sesion sesion : salaSimple.getSesiones()) {
					if ((sesion.getFecha().equals(fecha) && sesion.getHoraInicio().equals(horaIni))
							|| (sesion.getFecha().equals(fecha) && sesion.getHoraFin().isAfter(horaIni)))
						return null;
					else {
						this.addSesionMonitorizada(sesionGrupal);
						return sesionGrupal;
					}
				}
			}
		}else {
			throw new FueraHorarioClimatizacion();
		}

			
		return null;
	}

	/**
	 * crea un requisito de edad en la actividad
	 * 
	 * @param min numero minimo
	 * @param max numero máximo
	 * @return true si va bien
	 */
	public Boolean crearRequisitoEdad(Integer min, Integer max) {

		if (min < 0 || max < 0)
			return false;

		Requisito requisito = new RequisitoEdad(min, max);
		super.addRequisito(requisito);

		return true;
	}

	/**
	 * crea un requisito de veterania en la actividad
	 * 
	 * @param min numero minimo
	 * @param max numero mÃ¡ximo
	 * @return true si va bien
	 */
	public Boolean crearRequisitoVeterania(Integer min, Integer max) {

		if (min < 0 || max < 0)
			return false;

		Requisito r = new RequisitoVeterania(min, max);
		super.addRequisito(r);

		return true;
	}

	/**
	 * crea un requisito de cancelaciones en la actividad
	 * 
	 * @param min numero minimo
	 * @param max numero mÃ¡ximo
	 * @return true si va bien
	 */
	public Boolean crearRequisitoCancelaciones(Integer min, Integer max) {

		if (min < 0 || max < 0)
			return false;

		Requisito r = new RequisitoEdad(min, max);
		super.addRequisito(r);

		return true;
	}

	/**
	 * Ver si ya existe la sesion
	 * 
	 * @param sesion sesion a comprobar
	 * @return true si funciona bien
	 */
	public Boolean sesionesContieneSesion(Sesion sesion) {
		if (sesion == null)
			return false;

		return this.getSesionesMonitorizadas().contains(sesion);
	}

	/**
	 * Comprueba si es un plan personalizado
	 * 
	 * @return false
	 */
	@Override
	public Boolean isPlanPersonalizado() {
		return false;
	}

	/**
	 * Comprueba si es una actividad Grupal
	 * 
	 * @return true;
	 */
	@Override
	public Boolean isActividadGrupal() {
		return true;
	}

	/**
	 * GETTER
	 * 
	 * @return tipoActividad
	 */
	public TipoActividad getTipoActividad() {
		return this.tipoActividad;
	}

	/**
	 * Funcion para mostrar los datos de la actividad
	 * 
	 * @return la cadena con todos los datos
	 */
	@Override
	public String toString() {
		return super.toString() + "\nTipo Actividad: " + this.tipoActividad;
	}

}
