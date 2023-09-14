/**
 * Este fichero muestra todo lo que tiene que ver con la clase Cliente
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
package aplicacion.usuario;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import javax.swing.JOptionPane;

import aplicacion.*;
import aplicacion.usuario.tarifa.*;
import aplicacion.actividad.sesion.*;
import aplicacion.actividad.sesion.requisito.Requisito;
import aplicacion.actividad.sesion.requisito.RequisitoEdad;
import aplicacion.actividad.sesion.requisito.RequisitoVeterania;
import aplicacion.excepciones.ApuntadoMismaHora;
import aplicacion.excepciones.ApuntarseAntesDe48h;
import aplicacion.excepciones.ExcepcionCancelaciones;
import aplicacion.excepciones.ExcepcionEdad;
import aplicacion.excepciones.ExcepcionPago;
import aplicacion.excepciones.ExcepcionRequisitos;
import aplicacion.excepciones.ExcepcionUsuario;
import aplicacion.excepciones.ExcepcionVeterania;
import aplicacion.excepciones.FechaPosterior;
import aplicacion.excepciones.FechaUnDia;
import aplicacion.excepciones.SalaLlena;
import aplicacion.sala.Sala;
import aplicacion.Reserva;
import aplicacion.actividad.TipoActividad;
import aplicacion.actividad.TipoObjetivo;
import aplicacion.actividad.Actividad;
import aplicacion.actividad.ActividadGrupal;
import aplicacion.actividad.PlanPersonalizado;

import java.io.IOException;
import java.io.Serializable;
import es.uam.eps.padsof.telecard.*;

/**
 * esta es una clase que representa el cliente
 * 
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class Cliente extends Usuario implements Serializable {
	private String nombre;
	private String telefono;
	private LocalDate fechaNacimiento;
	private Boolean penalizado;
	private LocalDate penalizacionHasta;
	private Integer cancelaciones;
	private LocalDate fechaRegistro;
	private TarjetaCredito tarjeta;
	private Boolean haPagado;

	private Tarifa tarifa;

	private List<Reserva> reservas = new ArrayList<Reserva>();

	/**
	 * Constructor de cliente
	 * 
	 * @param nombre          nombre del cliente
	 * @param telefono        telefono del cliente
	 * @param fechaNacimiento fechaNacimiento del clien
	 * @param usuario         usuario del cliente
	 * @param contrasenya     contraseña del cliente
	 * @param tarjeta         tarjeta
	 */
	public Cliente(String nombre, String telefono, LocalDate fechaNacimiento, String usuario, String contrasenya,
			TarjetaCredito tarjeta) {
		super(usuario, contrasenya);
		this.nombre = nombre;
		this.telefono = telefono;
		this.fechaNacimiento = fechaNacimiento;
		this.tarjeta = tarjeta;
		this.penalizado = false;
		this.cancelaciones = 0;
		this.haPagado = false;
		
	}

	/**
	 * GETTER
	 * 
	 * @return devuelve el nombre
	 */
	public String getNombre() {
		return this.nombre;
	}

	/**
	 * GETTER
	 * 
	 * @return devuelve el telefono
	 */
	public String getTelefono() {
		return this.telefono;
	}

	/**
	 * GETTER
	 * 
	 * @return devuelve la fecha de nacimiento
	 */
	public LocalDate getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	/**
	 * GETTER
	 * 
	 * @return deveulve la fecha de registro
	 */
	public LocalDate getFechaRegistro() {
		return this.fechaRegistro;
	}

	/**
	 * GETTER
	 * 
	 * @return devuelve si esta penalizado o no
	 */
	public Boolean getPenalizacion() {
		return this.penalizado;
	}

	/**
	 * GETTER
	 * 
	 * @return devuelve hasta cuendo esta penalizado
	 */
	public LocalDate getPenalizacionHasta() {
		return this.penalizacionHasta;
	}

	/**
	 * GETTER
	 * 
	 * @return devuelve el numero de cancelaciones
	 */
	public Integer getCancelaciones() {
		return this.cancelaciones;
	}

	/**
	 * GETTER
	 * 
	 * @return devuelve la tarjeta de credito
	 */
	public TarjetaCredito getTarjeta() {
		return this.tarjeta;
	}

	/**
	 * SETTER
	 * 
	 * @param nombre el nombre
	 * @return true si se ha puesto correctamente, de lo contrario false
	 */
	public Boolean setNombre(String nombre) {
		if (nombre == null)
			return false;

		this.nombre = nombre;
		return true;
	}

	/**
	 * SETTER
	 * 
	 * @param cancelaciones las cancelaciones
	 * @return true si se ha puesto correctamente, de lo contrario false
	 */
	public Boolean setCancelaciones(Integer cancelaciones) {
		if (cancelaciones == null)
			return false;

		this.cancelaciones = cancelaciones;
		return true;
	}

	/**
	 * SETTER
	 * 
	 * @param penalizado si esta penalizado
	 * @return true si se ha puesto correctamente, de lo contrario false
	 */
	public Boolean setPenalizado(Boolean penalizado) {
		if (penalizado == null)
			return false;

		this.penalizado = penalizado;
		return true;
	}

	/**
	 * SETTER
	 * 
	 * @param fecha fecha de penalizacion
	 * @return true si se ha puesto correctamente, de lo contrario false
	 */
	public Boolean setPenalizacionHasta(LocalDate fecha) {
		if (fecha == null)
			return false;

		this.penalizacionHasta = fecha;
		return true;
	}

	/**
	 * SETTER
	 * 
	 * @param telefono telefono del cliente
	 * @return true si se ha puesto correctamente, de lo contrario false
	 */
	public Boolean setTelefono(String telefono) {
		if (telefono == null)
			return false;

		this.telefono = telefono;
		return true;
	}

	/**
	 * SETTER
	 * 
	 * @param tarifa tarifa del cliente
	 * @return true si se ha puesto correctamente, de lo contrario false
	 */
	public Boolean setTarifa(Tarifa tarifa) {
		if (tarifa == null)
			return false;

		this.tarifa = tarifa;
		return true;
	}

	/**
	 * SETTER
	 * 
	 * @param tarjeta tarjeta del cliente
	 * @return true si se ha puesto correctamente, de lo contrario false
	 */
	public Boolean setTarjetaCredito(TarjetaCredito tarjeta) {
		if (tarjeta == null)
			return false;

		this.tarjeta = tarjeta;
		return true;
	}

	/**
	 * SETTER
	 * 
	 * @param fecha fecha de nacimiento del cliente
	 * @return true si se ha puesto correctamente, de lo contrario false
	 */
	public Boolean setFechaNacimiento(LocalDate fecha) {
		if (fecha.compareTo(LocalDate.now()) > 0)
			return false;

		this.fechaNacimiento = fecha;
		return true;
	}

	/**
	 * GETTER
	 * 
	 * @return la tarifa
	 */
	public Tarifa getTarifa() {
		return this.tarifa;
	}

	/**
	 * SETTER
	 * 
	 * @param fecha fecha de registro del cliente
	 * @return true si se ha puesto correctamente, de lo contrario false
	 */
	public Boolean setFechaRegistro(LocalDate fecha) {
		if (fecha.compareTo(LocalDate.now()) > 0)
			return false;

		this.fechaRegistro = fecha;
		return true;
	}

	/**
	 * Funcion para apuntarse a la lista de espera si el aforo está lleno
	 * 
	 * @param sesion la sesion a la que el cliente se va a apuntar
	 * @return true si se ha podido apuntar
	 * @throws ExcepcionPago excepcion por si no ha pagado
	 */
	public Boolean apuntarListaEspera(Sesion sesion) throws ExcepcionPago {

		if (haPagado == false) {
			throw new ExcepcionPago();
		}
		if (sesion == null || sesion.getSala().getAforo() > sesion.getReservas().size()
				|| sesion.getListaEspera().listaContieneCliente(this)) {
			return false;
		}

		for (Reserva res : this.reservas) {
			if (sesion.equals(res.getSesion())) {
				return false;
			}
		}

		return sesion.getListaEspera().apuntar(this);
	}

	/**
	 * Comprueba si es el administrador
	 * 
	 * @return false
	 */
	@Override
	public boolean esAdministrador() {
		return false;
	}

	/**
	 * Comprueba si es el cliente
	 * 
	 * @return true
	 */
	@Override
	public boolean esCliente() {
		return true;
	}

	/**
	 * Comprueba si es el monitor
	 * 
	 * @return false
	 */
	@Override
	public boolean esMonitor() {
		return false;
	}

	/**
	 * Realiza una reserva de una sesion
	 * 
	 * @param sesion la sesion que va a reservar
	 * @return true si se ha hecho correctamente, de lo contrario false
	 * @throws FechaPosterior         si la fecha es posterior
	 * @throws ExcepcionUsuario       si el usuario no wxiste
	 * @throws SalaLlena              si la sala esta llena
	 * @throws ExcepcionEdad          si no cumple los requisitos de edad
	 * @throws ExcepcionVeterania     si no cumple los requisitos de veterania
	 * @throws ExcepcionCancelaciones si no cumple los requisitos de cancelaciones
	 * @throws ExcepcionPago          si no ha pagado su tarifa
	 * @throws ApuntarseAntesDe48h    si se esta apuntando 48h antes de la actividad
	 * @throws ApuntadoMismaHora 	  si ya tiene otra sesion a la misma hora
	 */
	public Boolean realizarReserva(Sesion sesion) throws FechaPosterior, ExcepcionUsuario, SalaLlena, ExcepcionEdad,
			ExcepcionVeterania, ExcepcionCancelaciones, ExcepcionPago, ApuntarseAntesDe48h, ApuntadoMismaHora{

		// comprueba que el cliente ha pagado
		if (haPagado == false) {
			throw new ExcepcionPago();
		}
		
		if (sesion == null || Gimnasio.getGimnasio() == null || this.penalizado == true
				|| sesion.getFecha().isBefore(LocalDate.now()) == true)
			return false;

		if (LocalDate.now().isBefore(sesion.getFecha()) && LocalDate.now().plusDays(2).isBefore(sesion.getFecha())) {
			throw new ApuntarseAntesDe48h();
		}

		/* Si la sala está llena se le añade a la lista de espera */
		if (sesion.getSala().getAforo() <= sesion.getReservas().size()) {
			throw new SalaLlena(sesion.getSala());
			/* HabrÃ¡ que dar a opcion en la interfaz de apuntarse a la lista de espera */
		}

		if (this.cancelaciones > Gimnasio.getGimnasio().getMaxCancelaciones()) {
			if (this.checkStillPenalizado() == true) {
				return false;
			}
		}
		if (sesion instanceof SesionMonitorizada) {
			for (int i = 0; i < sesion.getActSesion().getRequisitos().size(); i++) {
				if (sesion.getActSesion().getRequisitos().get(i).comprobar(this) == false) {
					if (sesion.getActSesion().getRequisitos().get(i) instanceof RequisitoEdad)
						throw new ExcepcionEdad(sesion.getActSesion().getRequisitos().get(i));
					else if (sesion.getActSesion().getRequisitos().get(i) instanceof RequisitoVeterania)
						throw new ExcepcionVeterania(sesion.getActSesion().getRequisitos().get(i));
					else
						throw new ExcepcionCancelaciones(sesion.getActSesion().getRequisitos().get(i));
				}
			}
		}

		for (Reserva res : this.reservas) {
			if (sesion.equals(res.getSesion())) {
				return false;
			}
		}
		
		for(Reserva reserva: this.reservas) {
			if(reserva.getSesion().getHoraInicio().equals(sesion.getHoraInicio()) || sesion.getHoraInicio().isBefore(reserva.getSesion().getHoraFin()))
				throw new ApuntadoMismaHora();
		}
		
		if (sesion instanceof SesionPP) {
			if (this.getTarifa() instanceof TarifaPlana) {
				Reserva r = new Reserva(this, sesion, true);

				if (this.realizarPago(sesion.getPrecio() - (sesion.getPrecio()*Gimnasio.getGimnasio().getDescuentoTarifaPlana())) == false) {
					return false;
				}

				if (reservas.contains(r) == true)
					return false;

				r.setReservado(true);
				reservas.add(r);
				sesion.addReserva(r);
				Gimnasio.getGimnasio().addReserva(r);

				return true;
			}
		}

		if (sesion instanceof SesionLibre) {
			if (this.getTarifa() instanceof TarifaPlana) {
				// porque no se le cobra

				Reserva r = new Reserva(this, sesion, true);

				if (this.realizarPago(0.0) == false) {
					return false;
				}

				if (reservas.contains(r) == true)
					return false;

				r.setReservado(true);
				reservas.add(r);
				sesion.addReserva(r);
				Gimnasio.getGimnasio().addReserva(r);

				return true;
			} else {
				/* COBRAR AL CLIENTE LA RESERVA */
				Reserva r = new Reserva(this, sesion, true);
				if (this.realizarPago(sesion.getPrecio()) == false) {
					return false;
				}

				if (reservas.contains(r) == true)
					return false;

				r.setReservado(true);
				reservas.add(r);
				sesion.addReserva(r);
				Gimnasio.getGimnasio().addReserva(r);

				return true;
			}

		} else if (sesion instanceof SesionAG) {
			if (this.getTarifa() instanceof TarifaPlana) {
				if (((TarifaPlana) this.getTarifa()).getTipoActividad().equals(((ActividadGrupal) sesion.getActSesion()).getTipoActividad())) {

					Reserva r = new Reserva(this, sesion, true);
					if (this.realizarPago(0.0) == false) {
						return false;
					}

					if (reservas.contains(r) == true)
						return false;

					r.setReservado(true);
					reservas.add(r);
					sesion.addReserva(r);
					Gimnasio.getGimnasio().addReserva(r);

					return true;
				}
			}
		}
		Reserva r = new Reserva(this, sesion, true);

		if (this.realizarPago(sesion.getPrecio()) == false) {
			return false;
		}

		if (reservas.contains(r) == true)
			return false;

		r.setReservado(true);
		reservas.add(r);
		sesion.addReserva(r);
		Gimnasio.getGimnasio().addReserva(r);

		return true;
	}

	/**
	 * Funcion para contratar un plan personalizado
	 * 
	 * @param planPersonalizado el plan a contratar
	 * @return true si lo ha podido contratar
	 * @throws SalaLlena     lla excepcion por si el aforo esta lleno
	 * @throws ExcepcionPago excepcion por si no ha pagado la tarifa
	 * @throws ApuntadoMismaHora si ya esta apuntado a una sesion a la misma hora
	 */
	public Boolean contratarPlanPersonalizado(PlanPersonalizado planPersonalizado)
			throws FechaPosterior, ExcepcionUsuario, SalaLlena, ExcepcionEdad, ExcepcionVeterania,
			ExcepcionCancelaciones, ExcepcionPago, ApuntarseAntesDe48h, ApuntadoMismaHora {
		/* comprobar los requisitos del plan y la tarifa esta pagada */
		if (this.haPagado == false) {
			throw new ExcepcionPago();
		}

		for (Reserva reserva : this.reservas) {
			if (planPersonalizado.getSesionesMonitorizadas().contains(reserva.getSesion())) {
				return false;
			}
		}

		for (Requisito requisito : planPersonalizado.getRequisitos()) {
			if (requisito.comprobar(this) == false) {
				return false;
			}
		}
		/*
		 * Comprobamos que se pueda reservar el plan completo antes de hacer todas las
		 * reservas
		 */
		for (SesionMonitorizada sesion : planPersonalizado.getSesionesMonitorizadas()) {
			if (sesion.getSala().getAforo() <= sesion.getReservas().size()) {
				throw new SalaLlena(sesion.getSala());
				/* Habra que dar a opcion en la interfaz de apuntarse a la lista de espera */
			}
		}

		/* Asignar automaticamente las reservas de lo que contiene */
		for (SesionMonitorizada sesion : planPersonalizado.getSesionesMonitorizadas()) {
			if(this.realizarReserva(sesion)==false){
				return false;
			}
		}

		/* cobrar por el plan personalizado = su precio es la suma de lo que contiene */
		if (this.realizarPago(planPersonalizado.getPrecio()) == false) {
			return false;
		}
		return true;
	}

	/**
	 * Funcion para concelar un plan personalizados
	 * 
	 * @param planPersonalizado el plan a cancelar
	 * @return true si lo ha podido cancelar
	 * @throws FechaPosterior   si la fecha es posterior
	 * @throws ExcepcionUsuario si el usuario no existe
	 * @throws FechaUnDia si la fecha es dia de hoy
	 */
	public Boolean cancelarPlanPersonalizado(PlanPersonalizado planPersonalizado)
			throws FechaPosterior, ExcepcionUsuario, FechaUnDia {
		List<Reserva> toRemove = new ArrayList<>();
		if (planPersonalizado == null) {
			return false;
		}

		for(SesionMonitorizada sesion: planPersonalizado.getSesionesMonitorizadas()) {
			if(sesion.getFecha().equals(LocalDate.now()))
				throw new FechaUnDia();
		}
		/* Asignar automaticamente las reservas de lo que contiene */
		if (planPersonalizado.getSesionesMonitorizadas().size() > 0) {
			for (SesionMonitorizada sesion : planPersonalizado.getSesionesMonitorizadas()) {
				for (Reserva reserva : this.reservas) {
					if (reserva.getSesion().equals(sesion)) {
						toRemove.add(reserva);
					}
				}
			}
		}

		for (Reserva reserva : toRemove) {
			try {
				if (this.cancelarReserva(reserva) == false) {
					return false;
				}
			} catch (FechaPosterior | ExcepcionUsuario | FechaUnDia e) {
				e.printStackTrace();
			}
		}
		/*
		 * reembolso por el plan personalizado = su precio es la suma de lo que contiene
		 */

		if (this.realizarPago(
				(-1) * planPersonalizado.getPrecio() * Gimnasio.getGimnasio().getPorcentajeDevolucion()) == false) {
			return false;
		}

		Notificacion notificacion = new Notificacion("Te han cancelado el plan: " + planPersonalizado.getNombre());
		planPersonalizado.getMonitor().addNotificaciones(notificacion);
		return true;

	}

	/**
	 * Cancela una reserva
	 * 
	 * @param reserva la reserva a cancelar
	 * @return true si se ha hecho correctamente, de lo contrario false
	 * @throws FechaPosterior   si la fecha es posterior
	 * @throws ExcepcionUsuario si el usuario no existe
	 */
	public Boolean cancelarReserva(Reserva reserva) throws FechaPosterior, ExcepcionUsuario, FechaUnDia {

		if (reserva.getReservado() == false || reservas.contains(reserva) == false || reserva == null
				|| Gimnasio.getGimnasio() == null) {
			return false;
		}
		if (reserva.getSesion().getFecha().equals(LocalDate.now())) {
			throw new FechaUnDia();
		}

		/* Devolvemos un porcentaje del dinero al cliente */
		Double porcentaje = Gimnasio.getGimnasio().getPorcentajeDevolucion();

		Double precioDevolver = 0.0;
		if (this.getTarifa() instanceof TarifaPlana){
			if(reserva.getSesion() instanceof SesionLibre || (reserva.getSesion().getActSesion() instanceof ActividadGrupal && ((ActividadGrupal)reserva.getSesion().getActSesion()).getTipoActividad().equals(((TarifaPlana)this.getTarifa()).getTipoActividad()))) {
			precioDevolver = 0.0;
			}
		} else {
			precioDevolver = reserva.getSesion().getPrecio() * porcentaje;
		}
		

		this.realizarPago((-precioDevolver));

		/* Notificamos a los clientes de la lista de espera */
		Notificacion notificacionEspera = new Notificacion("Aforo libre en la sesion");
		for (Cliente cliente : reserva.getSesion().getListaEspera().getListaClientes()) {
			cliente.addNotificaciones(notificacionEspera);
		}

		Notificacion notificacionMonitor = new Notificacion("Se ha cancelado una sesion");
		if (reserva.getSesion().getClass() == SesionPP.class) {
			SesionPP tmpSesion = (SesionPP) reserva.getSesion();
			tmpSesion.getActividad().getMonitor().addNotificaciones(notificacionMonitor);
		}

		/* Lo pone como no reservado y eliminamos la reserva */
		reserva.setReservado(false);
		this.reservas.remove(reserva);
		reserva.getSesion().removeReserva(reserva);
		Gimnasio.getGimnasio().removeReserva(reserva);
		this.cancelaciones += 1;
		if (this.cancelaciones >= Gimnasio.getGimnasio().getMaxCancelaciones()) {
			this.penalizado = true;
			this.penalizacionHasta = LocalDate.now().plusDays(Gimnasio.getGimnasio().getNumDiasPenal());
			return false;
		}

		return true;

	}

	/**
	 * Mira a ver si esta penalizado
	 * 
	 * @return true si se ha puesto correctamente, de lo contrario false
	 */
	public Boolean checkStillPenalizado() {

		LocalDate fechaActual = LocalDate.now();
		if (fechaActual.isEqual(penalizacionHasta) || fechaActual.isAfter(penalizacionHasta)) {
			this.penalizado = false;
			this.cancelaciones = 0;
			this.penalizacionHasta = null;
			return false;
		}
		return true;
	}

	/**
	 * GETTER
	 * 
	 * @return devuelve si esta penalizado o no
	 */
	public Boolean getPenalizado() {
		if (this.penalizado == true) {
			this.checkStillPenalizado();
		}
		return this.penalizado;
	}

	/**
	 * GETTER
	 * 
	 * @return devuelve si esta penalizado o no
	 */
	public Boolean getHaPagado() {

		return this.haPagado;
	}

	/**
	 * Setter
	 * 
	 * @param haPagado tur para si ha pagado y false si no ha pagado
	 */
	public void setHaPagado(Boolean haPagado) {
		this.haPagado = haPagado;

	}

	/**
	 * Renueva la tarifa
	 * 
	 * @param tarifa   tarifa 
	 * @param fin        tiempo que añade
	 * @param tipoActividad tipo de actividad
	 * @return true si se ha renovado correctamente, de lo contrario false
	 */
	public Boolean renovarTarifa(Tarifa tarifa, LocalDate fin, TipoActividad tipoActividad) {
		
		if (tarifa == null || fin == null || fin.isBefore(LocalDate.now()))
			return false;
		if(tipoActividad == null)
		{
			return true;
			
		}
		((TarifaPlana)tarifa).setFechaInicio(LocalDate.now());
		((TarifaPlana)tarifa).setFechaFin(fin);
		return true;
	}

	/**
	 * Añade una reserva
	 * 
	 * @param reserva reseva que quiere añadir
	 * @return true si se ha añadido correctamente, de lo contrario false
	 */
	public Boolean addReserva(Reserva reserva) {
		if (reservas.contains(reserva) == true)
			return false;

		return reservas.add(reserva);
	}

	/**
	 * Obteien las reservas del cliente
	 * 
	 * @return reservas devuelve la lista de reservas del cliente
	 */
	public List<Reserva> getReservas() {
		return this.reservas;
	}

	/**
	 * Comprueba que la tarjeta es valida
	 * 
	 * @param numeroTarjeta numero de tarjeta
	 * @return true si es correcto, de lo contrario false
	 */
	public boolean comprobarTarjeta(String numeroTarjeta) {
		return TeleChargeAndPaySystem.isValidCardNumber(numeroTarjeta);
	}

	/**
	 * SETTER
	 * 
	 * @param numeroTarjeta numero de tarjeta
	 * @throws InvalidCardNumberException excepcion si no es valida la tarjeta
	 */
	public void setNumeroTarjeta(String numeroTarjeta) throws InvalidCardNumberException {
		if (TeleChargeAndPaySystem.isValidCardNumber(numeroTarjeta) == false) {
			throw new InvalidCardNumberException(numeroTarjeta);
		}
		tarjeta.setNumeroCredito(numeroTarjeta);
	}

	/**
	 * Realiza el pago
	 * 
	 * @param cantidad cantidad a pagar
	 * @return true si se ha realizado correctamente, de lo contrario false
	 */
	public boolean realizarPago(Double cantidad) {

		int cont = 0;
		int maxIntentos = 3;
		Notificacion notificacion  = null;

		while (true) {
			try {
				TeleChargeAndPaySystem.charge(tarjeta.getCardNumberString(), tarjeta.getMessage(), cantidad);
				
				notificacion = new Notificacion("Has realizado un pago de: " + cantidad);
					
				this.addNotificaciones(notificacion);
				
				this.setHaPagado(true);
				return true;
			} catch (FailedInternetConnectionException e) {
				cont++;
				if (cont == maxIntentos) {
					System.out.println("Numero de intentos agotados.");
					break;
				}
			} catch (InvalidCardNumberException e) {
				System.out.println("Numero de tarjeta inválido");
				break;
			} catch (OrderRejectedException e) {
				System.out.println("Transaccion rechazada");
				break;
			}
		}
		return false;
	}

	/**
	 * Funcion para obtener las reservas a traves de los datos
	 * 
	 * @param name       el nombre de la reserva
	 * @param tipo       el tipo de objetivo si es un plan personalizado
	 * @param horaInicio el horario de inicio
	 * @param horaFin    la hora de fin
	 * @param fecha      la fecha
	 * @param sala       la sala de la sesion
	 * @param aforo      el aforo de la sala
	 * @return la reserva que le corresponden los datos
	 */
	public Reserva getReservaByData(String name, TipoObjetivo tipo, LocalDateTime horaInicio, LocalDateTime horaFin,
			LocalDate fecha, Sala sala, int aforo) {

		for (Reserva res : this.reservas) {
			if (res.getSesion() instanceof SesionMonitorizada) {

				if (res.getSesion().getActSesion().getNombre().equals(name) == true) {
					if (res.getSesion().getActSesion() instanceof PlanPersonalizado) {
						PlanPersonalizado plan = (PlanPersonalizado) res.getSesion().getActSesion();
						if (plan.getTipoObjetivo().equals(tipo) == true
								&& res.getSesion().getFecha().equals(fecha) == true
								&& res.getSesion().getHoraInicio().equals(horaInicio)
								&& res.getSesion().getHoraFin().equals(horaFin) && res.getSesion().getSala() == sala) {
							return res;
						}
					} else {
						if (tipo == null && res.getSesion().getFecha().equals(fecha) == true
								&& res.getSesion().getHoraInicio().equals(horaInicio)
								&& res.getSesion().getHoraFin().equals(horaFin)
								&& res.getSesion().getSala().equals(sala)) {
							return res;
						}
					}
				}
			} else {
				if (res.getSesion().getHoraInicio().equals(horaInicio)
						&& res.getSesion().getHoraInicio().equals(horaInicio)
						&& res.getSesion().getHoraFin().equals(horaFin) && res.getSesion().getSala().equals(sala)
						&& res.getSesion().getFecha().equals(fecha) == true)
					return res;
			}
		}

		return null;
	}

	/**
	 * Funcion para eliminar la notificacion
	 * 
	 * @param texto la notificacion a eliminar
	 */
	public void removeNotificacion(String texto) {
		ArrayList<Notificacion> toRemove = new ArrayList<>();
		for (Notificacion notificacion : this.getNotificaciones()) {
			if (notificacion.getTexto().equals(texto)) {
				toRemove.add(notificacion);
			}
		}

		for (Notificacion notificacion : toRemove) {
			this.getNotificaciones().remove(notificacion);
		}
	}

	/**
	 * Funcion para mostrar el cliente
	 * 
	 * @return cadena cadena
	 */
	@Override
	public String toString() {
		String cadena = super.toString();
		cadena += "\n";
		cadena += "(Cliente):\n";
		cadena += "Nombre: " + this.nombre + "\n" + "Nº Telefono: " + this.telefono + "\n" + "Fecha Nacimiento: "
				+ this.fechaNacimiento + "\n" + "Fecha Registro: " + this.fechaRegistro + "\n";
		cadena += "- Informacion Tarjeta:" + this.tarjeta.toString() + "\n";
		cadena += "- Informacion Tarifa: " + this.tarifa.toString();

		if (this.getPenalizado() == true) {
			cadena += "Penalizado hasta: " + this.penalizacionHasta;
		}

		return cadena;
	}

}
