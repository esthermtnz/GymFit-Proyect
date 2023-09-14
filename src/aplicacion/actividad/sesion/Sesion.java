/**
 * Este fichero muestra todo lo que tiene que ver con la clase Sesion
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

import aplicacion.Gimnasio;
import aplicacion.Reserva;
import aplicacion.actividad.Actividad;
import aplicacion.sala.*;
import aplicacion.usuario.Cliente;
import aplicacion.usuario.Notificacion;
import es.uam.eps.padsof.telecard.FailedInternetConnectionException;
import es.uam.eps.padsof.telecard.InvalidCardNumberException;
import es.uam.eps.padsof.telecard.OrderRejectedException;

/**
 * esta es una clase que representa la sesion
 * 
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public abstract class Sesion implements Serializable{
	private LocalDate fecha;
	private LocalDateTime horaInicio;
	private LocalDateTime horaFin;
	private Double precio;

	private SalaSimple sala;
	private ListaEspera listaespera;

	private List<Reserva> reservas = new ArrayList<Reserva>();

	/**
	 * Constructor de la clase sesion
	 * 
	 * @param fecha      dia de la sesion
	 * @param horainicio hora inicio
	 * @param horafin    hora fin
	 * @param sala		 la sala donde se realizará
	 * @param precio     precio de la sesion
	 */
	public Sesion(LocalDate fecha, LocalDateTime horainicio, LocalDateTime horafin, SalaSimple sala,
			Double precio) {
		this.fecha = fecha;
		this.horaInicio = horainicio;
		this.horaFin = horafin;
		this.sala = sala;
		this.precio = precio;
		this.listaespera = new ListaEspera();
	}

	/**
	 * Obtener la fecha
	 * 
	 * @return la fecha
	 */
	public LocalDate getFecha() {
		return this.fecha;
	}

	/**
	 * Obtener la Actividad
	 * 
	 * @return la actividad
	 */
	public abstract Actividad getActSesion();

	/**
	 * Obtener la hora inicial
	 * 
	 * @return la hora inicial
	 */
	public LocalDateTime getHoraInicio() {
		return this.horaInicio;
	}

	/**
	 * Obtener la hora final
	 * 
	 * @return hora final
	 */
	public LocalDateTime getHoraFin() {
		return this.horaFin;
	}

	/**
	 * Obtener la sala
	 * 
	 * @return la sala
	 */
	public Sala getSala() {
		return this.sala;
	}

	/**
	 * Obtener la lista de espera
	 * 
	 * @return la lista de espera
	 */
	public ListaEspera getListaEspera() {
		return this.listaespera;
	}

	/**
	 * Obtener las reservas
	 * 
	 * @return las reservas
	 */
	public List<Reserva> getReservas() {
		return this.reservas;
	}

	/**
	 * Establecer la fecha
	 * 
	 * @param fecha la fecha
	 * @return true si todo funciona bien
	 */
	public Boolean setFecha(LocalDate fecha) {
		if (fecha.compareTo(LocalDate.now()) < 0)
			return false;

		this.fecha = fecha;
		return true;
	}

	/**
	 * Establcer la hora de inicio
	 * 
	 * @param horaInicio hora de inicio
	 * @return true si todo funciona bien
	 */
	public Boolean setHoraInicio(LocalDateTime horaInicio) {
		if (horaInicio.isBefore(LocalDateTime.now()) == true)
			return false;

		this.horaInicio = horaInicio;
		return true;
	}

	/**
	 * Establecer la hora de fin
	 * 
	 * @param horaFin hora final
	 * @return true si todo funciona bien
	 */
	public Boolean setHoraFin(LocalDateTime horaFin) {
		if (horaFin.isBefore(LocalDateTime.now()) == true)
			return false;

		this.horaFin = horaFin;
		return true;
	}

	/**
	 * Añadir reserva
	 * 
	 * @param reserva La reserva a añadir
	 * @return true si todo funciona bien
	 */
	public Boolean addReserva(Reserva reserva) {
		if (reserva == null || reservas.contains(reserva) == true)
			return false;

		return reservas.add(reserva);
	}

	/**
	 * Eliminar reserva
	 * 
	 * @param reserva la reserva a eliminar
	 * @return true si todo funciona bien
	 */
	public Boolean removeReserva(Reserva reserva) {
		if (reserva == null || reservas.contains(reserva) == false)
			return false;

		return reservas.remove(reserva);
	}

	/**
	 * Calcular las horas de la sesion
	 * 
	 * @return el numero de horas
	 */
	public Integer calcularHorasSesion() {
		Integer res1 = this.getHoraFin().getHour() - this.getHoraInicio().getHour();
		Integer res2 = this.getHoraInicio().getHour() - this.getHoraFin().getHour();

		if (res1 > 0)
			return res1;
		else
			return 24-res2;
	}

	

	/**
	 * Obtener el precio
	 * 
	 * @return el precio
	 */
	public Double getPrecio() {

		return this.precio;

	}

	/**
	 * Establecer el precio
	 * 
	 * @param precio el precio
	 * @return true
	 */
	public Boolean setPrecio(Double precio) {

		if (precio < 0) {
			return false;
		}
		this.precio = precio;
		return true;
	}
	
	@Override
	public String toString(){
		return "\nFecha: " + this.getFecha() + "\nHoraIni: " + this.getHoraInicio() + "\nHoraFin" + this.getHoraFin() + "\nPrecio: " + this.getPrecio() + "\n";
	}
	
}
