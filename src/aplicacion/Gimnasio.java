package aplicacion;

import java.util.*;
import java.time.*;

import aplicacion.actividad.Actividad;
import aplicacion.actividad.ActividadGrupal;
import aplicacion.actividad.PlanPersonalizado;
import aplicacion.actividad.TipoActividad;
import aplicacion.actividad.TipoObjetivo;
import aplicacion.actividad.sesion.Sesion;
import aplicacion.actividad.sesion.SesionLibre;
import aplicacion.actividad.sesion.SesionMonitorizada;
import aplicacion.actividad.sesion.requisito.Requisito;
import aplicacion.equipacion.*;
import aplicacion.equipacion.Propiedad;
import aplicacion.excepciones.*;
import aplicacion.sala.*;
import aplicacion.usuario.Administrador;
import aplicacion.usuario.Cliente;
import aplicacion.usuario.Monitor;
import aplicacion.usuario.Notificacion;
import aplicacion.usuario.Usuario;
import aplicacion.usuario.tarifa.Tarifa;
import aplicacion.usuario.tarifa.TarifaPlana;
import aplicacion.usuario.tarifa.TarjetaCredito;
import es.uam.eps.padsof.payrolls.ICompanyInfo;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * esta es una clase que representa el gimnasio 
 * 
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */

/**
 * Clase Gimnasio Genera las propiedades del gimanasio
 */
public final class Gimnasio implements ICompanyInfo, Serializable {
	private static Gimnasio gimnasioInstance;

	private String nombre;
	private String cif;
	private String logo;
	private Double sueldo = 1000.0;
	private Double precioExtra = 40.0;
	private Integer maxCancelaciones = 3;
	private Integer numDiasPenal = 7;
	private Usuario usuarioRegistrado;
	private Double precioSesionesLibres = 20.0;
	private Double precioSesionesPersonalizadas = 20.0;
	private Double descuentoTarifa = 0.5;
	private Double porcentajeDevolucion = 0.5;
	private Double precioTarifaPlana = 20.0;

	private List<Sala> salas = new ArrayList<Sala>();
	private List<Reserva> reservas = new ArrayList<Reserva>();
	private List<Actividad> actividades = new ArrayList<Actividad>();
	private List<SesionLibre> sesionesLibres = new ArrayList<SesionLibre>();
	private Map<String, Usuario> usuarios = new LinkedHashMap<>();
	private List<TipoActividad> tipoActividadGrupal = new ArrayList<TipoActividad>();
	private List<Equipacion> equipaciones = new ArrayList<Equipacion>();

	/**
	 * La clase gimnasio es el programa principal que se encargará de crear todo lo
	 * relacionada con el.
	 * 
	 * @param nombre           del gimnasio
	 * @param cif              numero de cuenta
	 * @param logo             path de donde se encuentra el logo
	 * @param maxCancelaciones las cancelaciones maximas que se permiten hacer antes
	 *                         de estar penalizados
	 * @throws FechaPosterior   excepcion por si la fecha es posterior a la actual
	 * @throws ExcepcionUsuario si el usuario es null
	 */
	private Gimnasio(String name, String cif, String logo) throws ExcepcionUsuario, FechaPosterior {
		this.nombre = name;
		this.cif = cif;
		this.logo = logo;
		this.usuarios.put("admin", new Administrador("admin", "admin"));

	}

	/**
	 * Funcion que te devuelve la instanica del gimnasio
	 * 
	 * @return gimnasioInstance la clase del gimansio
	 * @throws ExcepcionUsuario excepcion del usuario
	 * @throws FechaPosterior   excepcion por si la fecha es posterior a la actual.
	 */
	public static Gimnasio getGimnasio() throws ExcepcionUsuario, FechaPosterior {
		if (gimnasioInstance == null) {
			gimnasioInstance = new Gimnasio("Gymfit", "ES-76345879", "./resources/logo.jpg");

		}

		return gimnasioInstance;
	}

	/**
	 * Establece el gimnasio
	 * 
	 * @param gimnasio gimnasio que se quiere establecer
	 */
	public static void setGimnasio(Gimnasio gimnasio) {
		if (gimnasio == null) {
			return;
		}

		gimnasioInstance = gimnasio;

	}

	/**
	 * Funcion para que los usuarios inicien sesion
	 * 
	 * @param usuario     nickname para iniciar sesion
	 * @param contrasenya contrasenya para iniciar sesion
	 * @return devuelve true si el usuario existe o false, si no existe es decir, no
	 *         esta registrado
	 * @throws IOException      excepcion por interrupcion
	 * @throws FechaPosterior   excepcion por si la fecha es posterior
	 * @throws ExcepcionUsuario excepcion del usuario
	 * @throws SinTarifa        si la tarifa ha caducado
	 */
	public Boolean iniciarSesion(String usuario, String contrasenya)
			throws IOException, ExcepcionUsuario, FechaPosterior, SinTarifa {

		if (usuario == null || contrasenya == null || usuario.isEmpty() || contrasenya.isEmpty()) {
			return false;
		}
		Usuario usr = this.getUsuarioByData(usuario, contrasenya);
		if (usr instanceof Cliente) {
			Cliente cliente = (Cliente) usr;
			if (cliente.getTarifa() instanceof TarifaPlana) {
				if (((TarifaPlana) cliente.getTarifa()).getFechaFin().equals(LocalDate.now())
						|| ((TarifaPlana) cliente.getTarifa()).getFechaFin().isBefore(LocalDate.now())) {
					cliente.setTarifa(null);
				}
			}

			if (cliente.getTarifa() == null)
				throw new SinTarifa();
		}

		if (this.getUsuarioByData(usuario, contrasenya) != null) {
			this.usuarioRegistrado = this.getUsuarioByData(usuario, contrasenya);
			return true;
		}

		return false;
	}

	/**
	 * Funcion para que obtener a un usuario segun su usuario y contraseña
	 * 
	 * @param usuario     nickname para iniciar sesion
	 * @param contrasenya contrasenya para iniciar sesion
	 * @return devuelve el usuario o null si no está en el sistema
	 */
	public Usuario getUsuarioByData(String usuario, String contrasenya) {
		if (usuario == null || contrasenya == null || usuario.isEmpty() || contrasenya.isEmpty()) {
			return null;
		}
		if (usuarios.containsKey(usuario) && usuarios.get(usuario).getContrasenya().equals(contrasenya)
				&& usuarios.get(usuario).getUsuario().equals(usuario)) {

			return usuarios.get(usuario);
		}
		return null;
	}

	/**
	 * Funcion que comprueba si el usuario registrado es el cliente
	 * 
	 * @param usuario a comprobar
	 * @return cliente el cliente registrado o null
	 */
	public Cliente registradoCliente(Usuario usuario) {
		if (usuario instanceof Cliente)
			return (Cliente) usuario;

		return null;
	}

	/**
	 * Funcion que comprueba si el usuario registrado es un monitor
	 * 
	 * @param usuario a comprobar
	 * @return monitor el monitor registrado o null
	 */
	public Monitor registradoMonitor(Usuario usuario) {
		if (usuario instanceof Monitor)
			return (Monitor) usuario;

		return null;
	}

	/**
	 * Funcion que te devulev el administrador registrado
	 * 
	 * @param usuario a comprobar
	 * @return adminitrador el administrador registrado o null
	 */
	public Administrador registradoAdministrador(Usuario usuario) {
		if (usuario instanceof Administrador)
			return (Administrador) usuario;

		return null;
	}

	/**
	 * Funcion que crea el tipo de Actividad Grupal
	 * 
	 * @param nombre del tipo de actividad grupal
	 * @return devuelve el tipo de actividad
	 */
	public TipoActividad crearTipoActividad(String nombre) {

		if (nombre == null || nombre.isEmpty())
			return null;

		TipoActividad tipoActividad = new TipoActividad(nombre);
		this.tipoActividadGrupal.add(tipoActividad);

		return tipoActividad;
	}

	/**
	 * Funcion que crea las actividades grupales
	 * 
	 * @param nombre        de la actividad grupal
	 * @param descripcion   de la actividad grupal
	 * @param requisito     el requisito establecido para dicha actividad
	 * @param monitor       el monitor que impartirá la actividad
	 * @param tipoActividad el tipo de actividad que se hara
	 * @return devuelve la actividad grupal si va todo bien, sino devolvera null
	 */
	public ActividadGrupal crearActividadGrupal(String nombre, String descripcion, Requisito requisito, Monitor monitor,
			TipoActividad tipoActividad) {

		if (monitor == null || descripcion == null || nombre == null || nombre.isEmpty() || tipoActividad == null
				|| requisito == null || requisito.getMax() < 0 || requisito.getMin() < 0
				|| this.tipoActividadGrupal.contains(tipoActividad) == false)
			return null;
		ActividadGrupal actividadGrupal = new ActividadGrupal(nombre, monitor, requisito, tipoActividad);

		this.actividades.add(actividadGrupal);
		monitor.addActividadGrupal(actividadGrupal);
		actividadGrupal.addRequisito(requisito);

		return actividadGrupal;
	}

	/**
	 * Funcion para cancelar la actividad grupal
	 * 
	 * @param actividad que voy a cancelar
	 * @return true si se ha podido cancelar false si ha ocurrido algun error
	 */
	public Boolean cancelarActividadGrupal(Actividad actividad) {
		Boolean status = false;
		List<SesionMonitorizada> toRemove = new ArrayList<>();

		if (actividad == null) {
			return false;
		}

		if (actividad.getSesionesMonitorizadas().size() > 0) {
			for (SesionMonitorizada sesion : actividad.getSesionesMonitorizadas()) {
				toRemove.add(sesion);
			}
		}

		if (toRemove.size() > 0) {
			for (SesionMonitorizada sesion : toRemove) {
				status = this.cancelarSesionActividadGrupal(sesion);
			}
		}

		actividad.getMonitor().getActividadesGrupales().remove(actividad);
		status = this.getActividadesGrupales().remove(actividad);

		return status;
	}

	/**
	 * Funcion que crea los planes personalizados
	 * 
	 * @param monitor     el monitor que impartirá la actividad
	 * @param requisitos  el requisito establecido para dicha actividad
	 * @param objetivo    el objetivo de dicho plan
	 * @param descripcion de que se trata el plan
	 * @param nombre      nombre del plan
	 * @return devuleve plan personalizado o null;
	 */
	public PlanPersonalizado crearPlanPersonalizado(String nombre, Monitor monitor, Requisito requisitos,
			TipoObjetivo objetivo, String descripcion) {

		if (nombre == null || nombre.isEmpty() || monitor == null || requisitos == null
				|| (objetivo != TipoObjetivo.GANANCIAMUSCULAR && objetivo != TipoObjetivo.PERDIDAPESO
						&& objetivo != TipoObjetivo.REHABILITACION)
				|| descripcion.isEmpty() || descripcion == null)
			return null;

		PlanPersonalizado planPersonalizado = new PlanPersonalizado(nombre, monitor, requisitos, objetivo, descripcion);
		this.actividades.add(planPersonalizado);
		monitor.addPlanPersonalizado(planPersonalizado);

		return planPersonalizado;
	}

	/**
	 * Funcion que crea las sesiones libres
	 * 
	 * @param fecha      dia de la sesion
	 * @param horaIni    a que hora empieza
	 * @param horaFin    a que hora termina
	 * @param salaSimple en que sala se hace
	 * @param precio     el precio de la sesion
	 * @return devuelve la sesion libre o null
	 * @throws FueraHorarioClimatizacion si esta fuera del horario de climatizacion
	 */
	public SesionLibre crearSesionLibre(LocalDate fecha, LocalDateTime horaIni, LocalDateTime horaFin,
			SalaSimple salaSimple, Double precio) throws FueraHorarioClimatizacion {

		// Si es una sala no climatizada
		if (salaSimple.getHorarioClimatizacion() == null) {
			SesionLibre sesionLibre = new SesionLibre(fecha, horaIni, horaFin, salaSimple, precio);
			this.sesionesLibres.add(sesionLibre);
			salaSimple.addSesion(sesionLibre);
			return sesionLibre;
		}
		if (fecha.compareTo(LocalDate.now()) < 0 || horaIni.isBefore(LocalDateTime.now()) || horaIni.isAfter(horaFin)
				|| horaFin.isBefore(LocalDateTime.now()) || salas.contains(salaSimple) == false || salaSimple == null
				|| horaIni.equals(horaFin)) {
			return null;
		}

		for (Sesion sesion : salaSimple.getSesiones()) {
			if (sesion.getFecha().equals(fecha) && sesion.getHoraInicio().equals(horaIni)
					&& sesion.getHoraFin().equals(horaFin)) {
				return null;
			}
		}

		if (horaIni.isAfter(salaSimple.getHorarioClimatizacion().getHoraFin())
				&& horaFin.isAfter(salaSimple.getHorarioClimatizacion().getHoraFin())) {
			
			SesionLibre sesionLibre = new SesionLibre(fecha, horaIni, horaFin, salaSimple, precio);
			this.sesionesLibres.add(sesionLibre);
			salaSimple.addSesion(sesionLibre);
			return sesionLibre;
			
		} else if (horaIni.isBefore(salaSimple.getHorarioClimatizacion().getHoraIni())
				&& horaFin.isBefore(salaSimple.getHorarioClimatizacion().getHoraIni())) {
			SesionLibre sesionLibre = new SesionLibre(fecha, horaIni, horaFin, salaSimple, precio);
			this.sesionesLibres.add(sesionLibre);
			salaSimple.addSesion(sesionLibre);
			return sesionLibre;
		} else if ((horaIni.isAfter(salaSimple.getHorarioClimatizacion().getHoraIni())
				&& horaFin.isBefore(salaSimple.getHorarioClimatizacion().getHoraFin()))
				|| (horaIni.isAfter(salaSimple.getHorarioClimatizacion().getHoraIni())
						&& horaFin.equals(salaSimple.getHorarioClimatizacion().getHoraFin()))
				|| (horaIni.equals(salaSimple.getHorarioClimatizacion().getHoraIni())
						&& horaFin.isBefore(salaSimple.getHorarioClimatizacion().getHoraFin()))) {
			SesionLibre sesionLibre = new SesionLibre(fecha, horaIni, horaFin, salaSimple, precio);
			this.sesionesLibres.add(sesionLibre);
			salaSimple.addSesion(sesionLibre);
			return sesionLibre;
		} else if (horaIni.equals(salaSimple.getHorarioClimatizacion().getHoraIni())
				&& horaFin.equals(salaSimple.getHorarioClimatizacion().getHoraFin())) {
			SesionLibre sesionLibre = new SesionLibre(fecha, horaIni, horaFin, salaSimple, precio);
			this.sesionesLibres.add(sesionLibre);
			salaSimple.addSesion(sesionLibre);
			return sesionLibre;
		} else {
			throw new FueraHorarioClimatizacion();
		}

	}

	/**
	 * Funcion para cancelar una sesion de una actividad grupal
	 * 
	 * @param sesion la sesion que voy a cancelar
	 * @return devuelve true si se ha podido cancelar y false si ha ocurrido algun
	 *         error
	 */
	public Boolean cancelarSesionActividadGrupal(Sesion sesion) {
		if (sesion == null) {
			return false;
		}
		Notificacion not = null;
		for (ActividadGrupal plan : this.getActividadesGrupales()) {
			for (SesionMonitorizada sesionMonitorizada : plan.getSesionesMonitorizadas()) {

				if (sesionMonitorizada.equals(sesion)) {
					not = new Notificacion("Se ha cancelado la sesion " + sesion.getActSesion().getNombre());

					// Mandamos notificacion a todos los clientes que hayan reservado esa sesion
					for (String nombre : this.getUsuarios().keySet()) {
						if (this.getUsuarios().get(nombre) instanceof Cliente) {
							for (int i = 0; i < ((Cliente) this.getUsuarios().get(nombre)).getReservas().size(); i++) {
								if (((Cliente) this.getUsuarios().get(nombre)).getReservas().get(i).getSesion()
										.equals(sesion)) {
									this.getUsuarios().get(nombre).addNotificaciones(not);
									((Cliente) this.getUsuarios().get(nombre)).getReservas().remove(i);
									/* reembolso de la sesion */
									if (((Cliente) this.getUsuarios().get(nombre))
											.realizarPago((-1) * sesionMonitorizada.getPrecio()) == false) {
										return false;
									}

								}
							}
						}
					}
					if (sesion.getSala() instanceof SalaSimple) {
						SalaSimple miSala = (SalaSimple) sesion.getSala();
						miSala.removeSesion(sesion);
					}
					return plan.getSesionesMonitorizadas().remove(sesion);

				}
			}
		}
		return false;
	}

	/**
	 * Funcion para cancelar una sesion libre
	 * 
	 * @param sesion la sesion que voy a cancelar
	 * @return devuelve true si se ha podido cancelar y false si ha ocurrido algun
	 *         error
	 */
	public Boolean cancelarSesionLibre(Sesion sesion) {
		if (sesion == null) {
			return false;
		}
		Notificacion not = null;
		for (SesionLibre sesionLibre : this.sesionesLibres) {

			if (sesionLibre.equals(sesion)) {
				not = new Notificacion("Se ha cancelado la sesion libre ");

				// Mandamos notificacion a todos los clientes que hayan reservado esa sesion
				for (String nombre : this.getUsuarios().keySet()) {
					if (this.getUsuarios().get(nombre) instanceof Cliente) {
						if (((Cliente) this.getUsuarios().get(nombre)).getReservas().size() > 0) {
							for (Reserva reserva : ((Cliente) this.getUsuarios().get(nombre)).getReservas()) {
								if (reserva.getSesion().equals(sesion)) {
									this.getUsuarios().get(nombre).addNotificaciones(not);
									((Cliente) this.getUsuarios().get(nombre)).getReservas().remove(reserva);
									/* reembolso de la sesion */
									if (((Cliente) this.getUsuarios().get(nombre))
											.realizarPago((-1) * sesionLibre.getPrecio()) == false) {
										return false;
									}

								}
							}
						}
					}
				}
				if (sesion.getSala() instanceof SalaSimple) {
					SalaSimple miSala = (SalaSimple) sesion.getSala();
					miSala.removeSesion(sesion);
				}
				return this.sesionesLibres.remove(sesion);
			}
		}
		return false;
	}

	/**
	 * Funcion que crea las salas simples
	 * 
	 * @param nombre      de las salas
	 * @param descripcion lo que se imparte en la sala
	 * @param aforo       numero de personas que se pueden apuntar
	 * @param climaini    horario de climatizacion inicial
	 * @param climafin    horario de climatizacion final
	 * @return la sala simple o null
	 */
	public SalaSimple crearSalaSimple(String nombre, String descripcion, Integer aforo, LocalDateTime climaIni,
			LocalDateTime climaFin) {
		
		//Si es una sala no climatizada
		if (climaIni == null && climaFin == null) {
			SalaSimple salaSimple = new SalaSimple(nombre, descripcion, aforo);
			this.salas.add(salaSimple);
			// salaSimple.getHorarioClimatizacion() = ;
			return salaSimple;
		}

		if (nombre == null || nombre.isEmpty() || descripcion.isEmpty() || descripcion == null || aforo < 0
				|| climaIni.isBefore(LocalDateTime.now()) == true || climaIni.isAfter(climaFin) == true
				|| climaFin.isBefore(LocalDateTime.now()) == true)
			return null;

		SalaSimple salaSimple = new SalaSimple(nombre, descripcion, aforo);
		salaSimple.definirHorarioClimatizacion(climaIni, climaFin);
		this.salas.add(salaSimple);

		return salaSimple;
	}

	/**
	 * Funcion que crea las salas compuestas
	 * 
	 * @param nombre      de la sala
	 * @param descripcion de lo que se impartira en la sala
	 * @param aforo       numero de perosnas que podran reservar
	 * @param climaini    horario de climatizacion inicial
	 * @param climafin    horario de climatizacion final
	 * @return la sala compuesta o null
	 */
	public SalaCompuesta crearSalaCompuesta(String nombre, String descripcion, Integer aforo, LocalDateTime climaini,
			LocalDateTime climafin) {

		if (nombre == null || nombre.isEmpty() || descripcion.isEmpty() || descripcion == null || aforo < 0
				|| climaini.isBefore(LocalDateTime.now()) == true || climaini.isAfter(climafin) == true
				|| climafin.isBefore(LocalDateTime.now()) == true)
			return null;

		SalaCompuesta salaCompuesta = new SalaCompuesta(nombre, descripcion, aforo);
		salaCompuesta.definirHorarioClimatizacion(climaini, climafin);

		this.salas.add(salaCompuesta);

		return salaCompuesta;
	}

	/**
	 * Funcion para obtener el mapa de usuarios
	 * 
	 * @return el mapa de las usuarios del gimnasio
	 */
	public Map<String, Usuario> getUsuarios() {
		return this.usuarios;
	}

	/**
	 * Funcion que se encargar de registrar al cliente
	 * 
	 * @param usuario     nombre de usuario del cliente
	 * @param contrasenya contrasnya para iniciar sesion
	 * @param nombre      nombre del cliente
	 * @param telefono    el numero de telefono
	 * @param fecha       fecha de nacimiento
	 * @param numTarjeta  la tarjeta de credito
	 * @param pin         el pin de la tarjeta
	 * @param titular     el titular de la tarjeta
	 * @param tarifa      la tarifa que va a pagar
	 * @return devuelve el cliente registrado
	 * @throws UsuarioExiste  la excepcion del usuario
	 * @throws FechaPosterior la excepcion de si la fecha es posterior
	 */
	public Cliente registroCliente(String usuario, String contrasenya, String nombre, String telefono, LocalDate fecha,
			String numTarjeta, String pin, String titular, Tarifa tarifa) throws UsuarioExiste, FechaPosterior {
		if (usuarios.containsKey(usuario) == true)
			throw new UsuarioExiste(usuario);

		if (fecha.isAfter(LocalDate.now()))
			throw new FechaPosterior(fecha);

		if (usuario == null || usuario.isEmpty() || contrasenya == null || contrasenya.isEmpty() || pin == null
				|| pin.isEmpty() || tarifa == null || nombre == null || nombre.isEmpty() || titular.isEmpty()
				|| titular == null || telefono.isEmpty() || telefono == null || numTarjeta.isEmpty()
				|| numTarjeta == null)
			return null;

		TarjetaCredito tarjeta = new TarjetaCredito(numTarjeta, pin, titular);
		Cliente cliente = new Cliente(nombre, telefono, fecha, usuario, contrasenya, tarjeta);
		cliente.setTarjetaCredito(tarjeta);
		cliente.setTarifa(tarifa);

		// hacer lo de pagar aqui, si consigues pagar bien lo añades a los clientes y
		// sino haces null

		if (cliente.comprobarTarjeta(numTarjeta) == true && pin.isEmpty() == false && titular.isEmpty() == false) {
			if (cliente.getTarifa() instanceof TarifaPlana) {
				if (cliente.realizarPago(tarifa.getPrecio()) == true) {
					cliente.setHaPagado(true);
				}
				usuarios.put(usuario, cliente);
				cliente.setFechaRegistro(LocalDate.now());
				return cliente;
			} else {
				if (cliente.realizarPago(0.0) == true) {
					cliente.setHaPagado(true);
				}
				usuarios.put(usuario, cliente);
				cliente.setFechaRegistro(LocalDate.now());
				return cliente;
			}
		}

		return null;

	}

	/**
	 * Funcion que se encarga de registrar al monitor
	 * 
	 * @param usuario     nombre de usuario para iniciar sesion
	 * @param nia         el dni para largar la nomina
	 * @param contrasenya para iniciar sesion
	 * @param nombre      nombre del monitor
	 * @param email       correo electrónico del monitor
	 * @return devuleve el monitor registrado
	 * @throws UsuarioExiste excepcion si el usuario ya existe
	 */
	public Monitor registroMonitor(String usuario, String nia, String contrasenya, String nombre, String email)
			throws UsuarioExiste {
		if (usuarios.containsKey(usuario) == true)
			throw new UsuarioExiste(usuario);

		if (usuario == null || usuario.isEmpty() || contrasenya.isEmpty() || contrasenya == null || nombre.isEmpty()
				|| nombre == null || nia.isEmpty() || nia == null || email.isEmpty() || email == null)
			return null;

		Monitor monitor = new Monitor(usuario, nia, contrasenya, nombre, email);

		this.usuarios.put(usuario, monitor);

		return monitor;
	}

	/**
	 * GETTER Funcion para obtener precio sesiones libres
	 * 
	 * @return el precio
	 */

	public Double getPrecioSesionesLibres() {
		return this.precioSesionesLibres;
	}

	/**
	 * SETTER Funcion para establecer precio sesiones libres
	 * 
	 * @param precioSesionesLibres precio de la sesion que vamos a establecer
	 * @return true si ha salido todo o false
	 */

	public Boolean setPrecioSesionesLibres(Double precioSesionesLibres) {
		if (precioSesionesLibres < 0) {
			return false;
		}
		this.precioSesionesLibres = precioSesionesLibres;
		return true;
	}

	/**
	 * SETTER Funcion para establecer el sueldo de los monitores
	 * 
	 * @param sueldo a establecer
	 * @return true si ha salido todo o false
	 */
	public Boolean setSueldo(Double sueldo) {
		if (sueldo < 0)
			return false;

		this.sueldo = sueldo;
		return true;
	}

	/**
	 * GETTER Funcion para obtener el sueldo
	 * 
	 * @return el sueldo
	 */
	public Double getSueldo() {
		return this.sueldo;
	}

	/**
	 * GETTER Funcion para obtener precio planes personalizados
	 * 
	 * @return el precio Sesiones Personalizadas
	 */

	public Double getPrecioSesionesPersonalizadas() {
		return this.precioSesionesPersonalizadas;
	}

	/**
	 * SETTER
	 * 
	 * @param precio el precio de la sesion
	 * @return devuelve true si se ha puesto correctaente, de lo contrario falsevv
	 */
	public Boolean setPrecioSesionesPersonalizadas(Double precio) {
		if (precio < 0)
			return false;

		this.precioSesionesPersonalizadas = precio;
		return true;
	}

	/**
	 * GETTER
	 * 
	 * @return el porcentaje de devolucion
	 */
	public Double getPorcentajeDevolucion() {
		return this.porcentajeDevolucion;
	}

	/**
	 * SETTER
	 * 
	 * @param porcentaje el porcentaje a establecer
	 * @return true si ha salido todo o false
	 */
	public Boolean setPorcentajeDevolucion(Double porcentaje) {
		if (porcentaje < 0.0) {
			return false;
		}
		this.porcentajeDevolucion = porcentaje;
		return true;
	}

	/**
	 * GETTER Funcion para obtener el descuetno de la tarifa plana
	 * 
	 * @return descuentoTarifa
	 */

	public Double getDescuentoTarifaPlana() {
		return this.descuentoTarifa;
	}

	/**
	 * SETTER
	 * 
	 * @param descuento el descuento de la tarifa plana
	 * @return devuelve true si se ha puesto correctaente, de lo contrario falsevv
	 */
	public Boolean setDescuentoTarifaPlana(Double descuento) {
		if (descuento < 0)
			return false;

		this.descuentoTarifa = descuento;
		return true;
	}

	/**
	 * SETTER
	 * 
	 * @param precio el precio de la sesion
	 * @return devuelve true si se ha puesto correctaente, de lo contrario false
	 */
	public Boolean setPrecioTarifaPlana(Double precio) {
		if (precio < 0)
			return false;

		this.precioTarifaPlana = precio;
		return true;
	}

	/**
	 * GETTER Funcion para obtener el descuetno de la tarifa plana
	 * 
	 * @return descuentoTarifa
	 */

	public Double getPrecioTarifaPlana() {
		return this.precioTarifaPlana;
	}

	/**
	 * GETTER Funcion para obtener las actividades
	 * 
	 * @return actividades
	 */
	public List<Actividad> getActividades() {
		return this.actividades;
	}

	/**
	 * GETTER Funcion para obtener las salas
	 * 
	 * @return salas las salas del gimnasio
	 */
	public List<Sala> getSalas() {
		return this.salas;
	}

	/**
	 * GETTER Funcion para obtener el logo
	 * 
	 * @return logo
	 */
	public String getLogo() {
		return this.logo;
	}

	/**
	 * Establecer el logo del gimnasio
	 * 
	 * @param logo del gimnasio
	 */
	public void setLogo(String logo) {
		this.logo = logo;
		return;
	}

	/**
	 * GETTER
	 * 
	 * @return devuelve el nombre
	 */
	public String getName() {
		return this.nombre;
	}

	/**
	 * 
	 * @param nombre el nombre
	 * @return devuelve true si se ha puesto correctaente, de lo contrario falsevv
	 */
	public Boolean setName(String nombre) {
		if (nombre == null)
			return false;

		this.nombre = nombre;
		return true;
	}

	/**
	 * GETTER
	 * 
	 * @return devuelve el sueldo
	 */
	public double getBaseSalaryPerMonth() {
		if (this.sueldo == null) {
			return -1.0;
		}
		return this.sueldo;
	}

	/**
	 * SETTER
	 * 
	 * @param sueldo el sueldo
	 * @return devuelve true si se ha puesto correctaente, de lo contrario false
	 */
	public Boolean setBaseSalaryPerMonth(Double sueldo) {
		if (sueldo < 0)
			return false;

		this.sueldo = sueldo;
		return true;
	}

	/**
	 * GETTER
	 * 
	 * @return devuelve el precio extra
	 */
	public double getRateHour() {
		return this.precioExtra;
	}

	/**
	 * SETTER
	 * 
	 * @param extra el extra
	 * @return devuelve true si se ha puesto correctaente, de lo contrario false
	 */
	public Boolean setRateHour(Double extra) {
		if (extra < 0) {
			return false;
		}

		this.precioExtra = extra;
		return true;
	}

	/**
	 * GETTER
	 * 
	 * @return devuelve el cif
	 */
	public String getCif() {
		return this.cif;
	}

	/**
	 * SETTER
	 * 
	 * @param cif el cif
	 */
	public void setCif(String cif) {
		this.cif = cif;
		return;
	}

	/**
	 * GETTER
	 * 
	 * @return devuevel el numero maximo de cancelaciones
	 */
	public Integer getMaxCancelaciones() {
		return this.maxCancelaciones;
	}

	/**
	 * SETTER
	 * 
	 * @param numero numero maximo de cancelaciones
	 * @return devuelve true si se ha puesto correctaente, de lo contrario false
	 */
	public Boolean setMaxCancelaciones(Integer numero) {
		if (numero < 0) {
			return false;
		}
		this.maxCancelaciones = numero;
		return true;
	}

	/**
	 * GETTER
	 * 
	 * @return devuelve el numero de dias penalizado
	 */
	public Integer getNumDiasPenal() {
		return this.numDiasPenal;
	}

	/**
	 * SETTER
	 * 
	 * @param numero de dias para penalizar
	 * @return devuelve true si se ha puesto correctaente, de lo contrario false
	 */
	public Boolean setNumDiasPenal(Integer numero) {
		if (numero < 0) {
			return false;
		}
		this.numDiasPenal = numero;
		return true;
	}

	/**
	 * GETTER
	 * 
	 * @return devuelve el usuario registrado
	 */
	public Usuario getUsuarioRegistrado() {
		return this.usuarioRegistrado;
	}

	/**
	 * SETTER
	 * 
	 * @param usuario el usuario
	 * @return devuelve true si se ha puesto correctaente, de lo contrario false
	 */
	public Boolean setUsuarioRegistrado(Usuario usuario) {

		this.usuarioRegistrado = usuario;
		return true;
	}

	/**
	 * Cierra sesion
	 * 
	 * @param usuario el usuario que va a cerrar sesion
	 * @return devuelve true si se ha cerrado correctamente, de lo contrario false
	 * @throws IOException excepcion de la IO
	 */
	public Boolean cerrarSesion(Usuario usuario) throws IOException {
		if (this.usuarios.containsKey(usuario.getUsuario()) == false) {
			return false;
		}
		if (usuario.getUsuario().equals("admin")) {
			try {
				this.salvarAplicacion(this);
			} catch (IOException excepcion) {
				excepcion.printStackTrace();
			}

			return this.setUsuarioRegistrado(null);
		}

		if (this.usuarioRegistrado == null) {
			return false;
		}

		try {
			this.salvarAplicacion(this);
		} catch (IOException excepcion) {
			excepcion.printStackTrace();
		}
		return this.setUsuarioRegistrado(null);
	}

	/**
	 * Añade una reserva la lista de reservas
	 * 
	 * @param reserva la reserva que queremos añadir
	 * @return devuelve la lista
	 */
	public Boolean addReserva(Reserva reserva) {
		if (reserva == null || reservas.contains(reserva) == true) {
			return false;
		}
		return this.reservas.add(reserva);

	}

	/**
	 * Elimina una reserva la lista de reservas
	 * 
	 * @param reserva la reserva que queremos eliminar
	 * @return devuelve la lista de reservas sin la reserva
	 */
	public Boolean removeReserva(Reserva reserva) {
		if (reserva == null || reservas.contains(reserva) == false) {
			return false;
		}

		return reservas.remove(reserva);

	}

	/**
	 * Funcion que devuelve la lista de actividades grupales del gimnasio
	 * 
	 * @return lista de actividades grupales
	 */
	public List<TipoActividad> getTipoActividadGrupal() {
		return this.tipoActividadGrupal;
	}

	/**
	 * Funcion para añadir un tipo de actividad grupal al gimnasio
	 * 
	 * @param nombre el nombre del tipo de actividad
	 * @return true si se ha podido añadir y false si ha habido algun error
	 */
	public boolean addTipoActividad(TipoActividad nombre) {
		if (nombre == null)
			return false;

		return this.tipoActividadGrupal.add(nombre);
	}

	/**
	 * Añade una sala a una lista de salas
	 * 
	 * @param sala la sala que queremos añadir
	 * @return devuelve la lista de salas con la sala añadida
	 */
	public Boolean addSala(Sala sala) {
		if (sala == null || salas.contains(sala) == true) {
			return false;
		}
		return salas.add(sala);

	}

	/**
	 * Elimina una sala del gimnasio
	 * 
	 * @param sala sala del gimnasio
	 * @return true si lo ha eliminado correctamente, de lo contrario false
	 */
	public Boolean removeSala(Sala sala) {
		if (sala == null || salas.contains(sala) == false) {
			return false;
		}
		return salas.remove(sala);

	}

	/**
	 * Mira a ver si la lista de usuarios contiene el monitor que le pasamos
	 * 
	 * @param monitor el monitor
	 * @return true si lo contiene, false si no lo contiene, o si se pasa mal el
	 *         atributo
	 */
	public Boolean listaUsuarioContieneMonitor(Monitor monitor) {
		if (monitor == null)
			return false;

		return usuarios.containsValue(monitor);
	}

	/**
	 * Mira a ver si la lista de usuarios contiene el cliente que le pasamos
	 * 
	 * @param cliente el cliente
	 * @return true si lo contiene, false si no lo contiene, o si se pasa mal el
	 *         atributo
	 */
	public Boolean listaUsuarioContieneCliente(Cliente cliente) {
		if (cliente == null)
			return false;

		return usuarios.containsValue(cliente);
	}

	/**
	 * Mira a ver si el usuario es un cliente
	 * 
	 * @param usuario el usuario a comprobar
	 * @return true si el usuario es un cliente
	 */
	public Boolean isCliente(String usuario) {
		if (usuarios.get(usuario) instanceof Cliente)
			return true;

		return false;
	}

	/**
	 * Mira a ver si el usuario es un monitor
	 * 
	 * @param usuario el usuario a comprobar
	 * @return true si el usuario el un monitor
	 */
	public Boolean isMonitor(String usuario) {
		if (usuarios.get(usuario) instanceof Monitor)
			return true;

		return false;
	}

	/**
	 * Mira a ver si el usuario es el administrador
	 * 
	 * @param usuario el usuario a comprobar
	 * @return true si el usuario es el administrador
	 */
	public Boolean isAdministrador(String usuario) {
		if (usuarios.get(usuario) instanceof Administrador)
			return true;

		return false;
	}

	/**
	 * Mira a ver si la actividad es un plan personalizado
	 * 
	 * @param actividad la actividad a comprobar
	 * @return true si la actividad es un plan personalizado
	 */
	public Boolean isPlanPersonalizado(Actividad actividad) {
		if (actividad instanceof PlanPersonalizado) {
			return true;
		}

		return false;
	}

	/**
	 * Mira a ver si la actividad es una actividad grupal
	 * 
	 * @param actividad la actividad a comprobar
	 * @return true si la actividad es una actividad grupal
	 */
	public Boolean isActividadGrupal(Actividad actividad) {
		if (actividad instanceof ActividadGrupal) {
			return true;
		}
		return false;
	}

	/**
	 * Calcula el beneficio del gimnasio con una actividad en un mes determinado
	 * 
	 * @param mes       el mes a buscar
	 * @param actividad la actividad que queremos calcular su beneficio
	 * @return devuelve el beneficio del gimnasio
	 */
	public Double beneficioPorActividad(Month mes, Actividad actividad) {
		Double total = 0.00;
		if (mes == null || actividad == null) {
			return -1.0;
		}

		for (Sesion sesion : actividad.getSesionesMonitorizadas()) {
			Integer contaAux = 0;
			if (sesion.getFecha().getMonth() == mes) {
				contaAux++;
			}
			total += (actividad.getPrecio() * contaAux);
		}

		return total;
	}

	/**
	 * GETTER para obtener la lista de sesiones monitorizadas del gimnasio
	 * 
	 * @return lista de sesiones monitorizadas
	 */
	public List<Sesion> getSesionesGimnasio() {
		List<Sesion> totalSesiones = new ArrayList<>();
		for (Actividad actividad : this.actividades) {
			if (actividad.getSesionesMonitorizadas().size() > 0) {
				for (SesionMonitorizada sesionMonitorizada : actividad.getSesionesMonitorizadas()) {
					totalSesiones.add(sesionMonitorizada);
				}
			}
		}

		for (SesionLibre sesionLibre : this.sesionesLibres) {
			totalSesiones.add(sesionLibre);
		}
		return totalSesiones;
	}

	/**
	 * Calcula el beneficio del gimnasio
	 * 
	 * @param listaSesiones la lista de todas las sesiones del gimnasio
	 * @return devuelve el beneficio del gimnasio
	 */
	public Double beneficioGimnasio(List<Sesion> listaSesiones) {
		Double total = 0.00;

		if (listaSesiones == null) {
			return -1.0;
		}

		for (Sesion sesion : listaSesiones) {

			total += sesion.getPrecio();
		}

		return total;
	}

	/**
	 * Calcula el gasto del gimnasio con el equipamiento
	 * 
	 * @param listaEquipaciones la lista de todo las equipaciones del gimnasio
	 * @param mes               el mes del gasto
	 * @param year              el año del gasto
	 * @return devuelve el gasto del gimnasio
	 */
	public Double gastoEquipacion(List<Equipacion> listaEquipaciones, Month mes, int year) {
		Double total = 0.00;

		int numDiasMes = mes.length(Year.isLeap(year));
		for (Equipacion equipacion : listaEquipaciones) {
			total += equipacion.getPrecioMensual(numDiasMes);
		}

		return total;
	}

	/**
	 * Filtra por mes y anyo
	 * 
	 * @param mes  el mes a filtrar
	 * @param year el año a filtrar
	 * @return devuelve la lista de reservas de ese mes y anyo
	 * @throws MesNoTerminado si el mes no ha temrinado todavia
	 */
	public ArrayList<Reserva> filtradoReservasMes(Month mes, int year) throws MesNoTerminado {
		ArrayList<Reserva> listaReservas = new ArrayList<>();
		LocalDate fechaComparacion = LocalDate.of(year, mes, 1);

		for (Reserva reserva : this.getReservas()) {
			Sesion sesion = reserva.getSesion();
			if (sesion.getFecha().getMonthValue() == mes.getValue() && sesion.getFecha().getYear() == year) {
				listaReservas.add(reserva);
			}
		}

		return listaReservas;
	}

	/**
	 * Filtra por mes y anyo
	 * 
	 * @param mes  el mes a filtrar
	 * @param year el año a filtrar
	 * @return devuelve la lista de equipaciones compradas de ese mes y anyo
	 * @throws MesNoTerminado si el mes todavia no ha terminado
	 */
	public ArrayList<Equipacion> filtradoEquipacionMes(Month mes, int year) throws MesNoTerminado {
		ArrayList<Equipacion> listaEquipacion = new ArrayList<>();
		LocalDate fechaComparacion = LocalDate.of(year, mes, 1);

		if (LocalDate.now().isBefore(fechaComparacion) || LocalDate.now().getMonthValue() == mes.getValue()) {
			throw new MesNoTerminado(mes);
		}

		for (Equipacion equipacion : this.getEquipaciones()) {
			if (equipacion.getFecha().getMonthValue() == mes.getValue() && equipacion.getFecha().getYear() == year) {
				listaEquipacion.add(equipacion);
			} else if (equipacion.isMaquina() == true && ((Maquina) equipacion).esAlquilada()
					&& equipacion.getFecha().isBefore(fechaComparacion)) {
				listaEquipacion.add(equipacion);
			}
		}

		return listaEquipacion;
	}

	/**
	 * GETTER funcion para obtener los planes de un determinado monitor
	 * 
	 * @param monitor el mnitor del cual queremos obtener sus planes
	 * @return lista de planes personalizados que imparte el monitor
	 */
	public List<PlanPersonalizado> getPlanesPersonalizadosMonitor(Monitor monitor) {
		if (monitor == null || this.usuarios.containsValue(monitor) == false) {
			return null;
		}

		return monitor.getPlanesPersonalizados();
	}

	/**
	 * Funcion para guardar todos los datos del gimnasio en un fichero
	 * 
	 * @param gimnasio el gimnasio que vamos a guardar
	 * @throws IOException excepcion del IO
	 */
	public void salvarAplicacion(Gimnasio gimnasio) throws IOException {
		File direccion = new File("./resources");
		File archivo = new File(direccion, "gimnasio.txt");

		FileOutputStream fout = new FileOutputStream(archivo);
		ObjectOutputStream oos = new ObjectOutputStream(fout);

		try {
			oos.writeObject(gimnasio);
		} finally {
			oos.close();
		}
	}

	/**
	 * Funcion para cargar todos los datos de un fichero en el gimnasio
	 * 
	 * @param rutaArchivo la ruta donde se encuentran los archivos guardados
	 * @return el gimnasio con todos sus datos guardados
	 * @throws IOException            una excepcion del IO
	 * @throws ClassNotFoundException excepcion cuando no se encuentra la clase
	 */
	public Gimnasio cargarAplicacion(File rutaArchivo) throws IOException, ClassNotFoundException {
		Gimnasio gimnasioInstance = null;

		FileInputStream fin = new FileInputStream(rutaArchivo);
		ObjectInputStream ois = new ObjectInputStream(fin);

		gimnasioInstance = (Gimnasio) ois.readObject();
		Gimnasio.setGimnasio(gimnasioInstance);

		if (gimnasioInstance != null) {

			return gimnasioInstance;
		} else
			try {
				gimnasioInstance = Gimnasio.getGimnasio();
			} catch (ExcepcionUsuario | FechaPosterior e) {
				e.printStackTrace();
			}

		return gimnasioInstance;
	}

	/**
	 * Funcion para ver la ocupacion de una determinada sala
	 * 
	 * @param sala la sala a comprobar
	 * @return el numero de reservas de esas sala
	 */
	public Integer ocupacionSala(Sala sala) {
		Integer tam = 0;

		for (Actividad actividad : this.actividades) {
			for (int i = 0; i < actividad.getSesionesMonitorizadas().size(); i++) {
				if (actividad.getSesionesMonitorizadas().get(i).getSala().equals(sala) == true) {
					tam += actividad.getSesionesMonitorizadas().get(i).getReservas().size();
				}
			}
		}

		return tam;
	}

	/**
	 * Funcion que devuelve la lista de cancelaciones en un determinado mes
	 * 
	 * @param mes       el mes que queremos ver sus cancelaciones
	 * @param actividad la actividad que queremos sus cancelaciones
	 * @return la lista de reservas canceladas
	 */
	public List<Reserva> reservasCanceladasMes(Month mes, Actividad actividad) {
		if (mes == null || actividad == null) {
			return null;
		}
		List<Reserva> cancelaciones = new ArrayList<>();

		for (SesionMonitorizada sesion : actividad.getSesionesMonitorizadas()) {
			for (Reserva reserva : sesion.getReservas()) {
				if (reserva.getReservado() == false) {
					cancelaciones.add(reserva);
				}
			}
		}

		return cancelaciones;
	}

	/**
	 * Funcion para obtener una lista de reservas no canceladas
	 * 
	 * @param mes       el mes que queremos ver sus reservas
	 * @param actividad la actividad que queremos ver sus reservas
	 * @return la lista de reservas no canceladas
	 */
	public List<Reserva> reservasNoCanceladasMes(Month mes, Actividad actividad) {
		if (mes == null || actividad == null) {
			return null;
		}

		List<Reserva> reservas = new ArrayList<>();

		for (SesionMonitorizada sesion : actividad.getSesionesMonitorizadas()) {
			for (Reserva reserva : sesion.getReservas()) {
				if (reserva.getReservado() == true) {
					reservas.add(reserva);
				}
			}
		}

		return reservas;
	}

	/**
	 * Funcion para obtener la sala simple a traves del nombre
	 * 
	 * @param nombre el nombre de la sala que queremos obtener
	 * @return la sala simple con dicho nombre
	 */
	public SalaSimple getSalaSimpleByName(String nombre) {
		for (Sala sala : this.salas) {
			if (sala.getNombre().equals(nombre) && sala.isSimple() == true) {
				return (SalaSimple) sala;
			}
		}
		return null;
	}

	/**
	 * Funcion para obtener el monitor a traves del nombre de usuario
	 * 
	 * @param nombre el nombre del monitor que queremos obtener
	 * @return el monitor con dicho nombre
	 */
	public Monitor getMonitorByName(String nombre) {
		return (Monitor) this.usuarios.get(nombre);
	}

	/**
	 * Funcion para obtener el tipo de actividad a traves del nombre
	 * 
	 * @param nombre el nombre del tipo de actividad que queremos obtener
	 * @return el tipo de actividad con dicho nombre
	 */
	public TipoActividad getTipoActividadByName(String nombre) {
		for (TipoActividad tipo : this.tipoActividadGrupal) {
			if (tipo.getNombre().equals(nombre)) {
				return tipo;
			}
		}
		return null;
	}

	/**
	 * GETTER funcion para obtener las sesiones libres del gimnasio
	 * 
	 * @return la lista de sesiones libres
	 */
	public List<SesionLibre> getSesionesLibres() {
		return this.sesionesLibres;
	}

	/**
	 * Funcion para obtener el tipo de objetivo a traves de su nombre
	 * 
	 * @param nombre el nombre del tipo objetivo que queremos obtener
	 * @return el tipo objetivo con dicho nombre
	 */
	public TipoObjetivo getTipoObjetivoByName(String nombre) {
		if (nombre == null) {
			return null;
		}

		for (PlanPersonalizado plan : this.getPlanesPersonalizados()) {
			if (plan.getTipoObjetivo().equals(TipoObjetivo.GANANCIAMUSCULAR) && nombre.equals("GANANCIAMUSCULAR")) {
				return plan.getTipoObjetivo();
			} else if (plan.getTipoObjetivo().equals(TipoObjetivo.PERDIDAPESO) && nombre.equals("PERDIDAPESO")) {
				return plan.getTipoObjetivo();
			} else if (plan.getTipoObjetivo().equals(TipoObjetivo.REHABILITACION) && nombre.equals("REHABILITACION")) {
				return plan.getTipoObjetivo();
			}
		}
		return null;
	}

	/**
	 * GETTER funcion para obtener los planes personalizados del gimnasio
	 * 
	 * @return lista de planes personalizados
	 */
	public List<PlanPersonalizado> getPlanesPersonalizados() {
		List<PlanPersonalizado> planes = new ArrayList<>();
		for (Actividad actividad : this.actividades) {
			if (actividad.isPlanPersonalizado() == true) {
				planes.add((PlanPersonalizado) actividad);
			}
		}
		return planes;
	}

	/**
	 * GETTEr funcion para obtener las actividades grupales del gimnasio
	 * 
	 * @return lista de las actividades grupales
	 */
	public List<ActividadGrupal> getActividadesGrupales() {
		List<ActividadGrupal> actividades = new ArrayList<>();
		for (Actividad actividad : this.actividades) {
			if (actividad.isActividadGrupal() == true) {
				actividades.add((ActividadGrupal) actividad);
			}
		}
		return actividades;
	}

	/**
	 * Funcion para obtener la sesion a traves de los datos que le pasamos
	 * 
	 * @param name       el nombre de la sesion
	 * @param tipo       el tipo de objetivo si buscamos una sesion de plan
	 *                   personalizados
	 * @param horaInicio la hora de inicio
	 * @param horaFin    la hora de fin
	 * @param fecha      la fecha de la sesion
	 * @param sala       la sala en la que se encuentra la sesion
	 * @param aforo      el aforo de la sesion
	 * @return la sesion que le corresponden los datos pasados
	 */
	public Sesion getSesionByData(String name, TipoObjetivo tipo, LocalDateTime horaInicio, LocalDateTime horaFin,
			LocalDate fecha, Sala sala, int aforo) {

		for (Actividad act : this.actividades) {
			for (Sesion sesion : act.getSesionesMonitorizadas()) {
				if (act.getNombre().equals(name) == true) {
					if (act instanceof PlanPersonalizado) {
						PlanPersonalizado plan = (PlanPersonalizado) act;
						if (plan.getTipoObjetivo().equals(tipo) == true && sesion.getFecha().equals(fecha) == true
								&& sesion.getHoraInicio().equals(horaInicio) && sesion.getHoraFin().equals(horaFin)
								&& sesion.getSala() == sala) {
							return sesion;
						}
					} else {
						if (tipo == null && sesion.getFecha().equals(fecha) == true
								&& sesion.getHoraInicio().equals(horaInicio) && sesion.getHoraFin().equals(horaFin)
								&& sesion.getSala().equals(sala)) {
							return sesion;
						}
					}
				}
			}
		}

		return null;
	}

	/**
	 * GETTER funcion para obtener las reservas del gimnasio
	 * 
	 * @return la lista de reservas
	 */
	public List<Reserva> getReservas() {
		return this.reservas;
	}

	/**
	 * Funcion para obtener la sesion libre a traves de los datos que le pasamos
	 * 
	 * @param horaInicio la hora de inicio
	 * @param horaFin    la hora de fin
	 * @param fecha      la fecha de la sesion
	 * @param sala       la sala en la que se encuentra la sesion
	 * @param aforo      el aforo de la sesion
	 * @param precio     el precio de la sesion libre
	 * @return la sesion libre que le corresponden los datos pasados
	 */
	public SesionLibre getSesionLibreByData(LocalDate fecha, LocalDateTime horaInicio, LocalDateTime horaFin, Sala sala,
			int aforo, double precio) {

		for (SesionLibre sesion : this.sesionesLibres) {
			if (sesion.getFecha().equals(fecha) && sesion.getHoraInicio().equals(horaInicio)
					&& sesion.getHoraFin().equals(horaFin) && sesion.getSala().equals(sala)
					&& sesion.getPrecio().equals(precio))
				return sesion;
		}

		return null;
	}

	/**
	 * Funcion para obtener la sala por su nombre
	 * 
	 * @param name el nombre de la sala que queremos encontrar
	 * @return la sala que le corresponde dicho nombre
	 */
	public Sala getSalaByName(String name) {
		for (Sala sala : this.salas) {
			if (sala.getNombre().equals(name) == true) {
				return sala;
			}
		}
		return null;
	}

	/**
	 * Funcion para encontrar una actividad con los datos correspondientes que le
	 * pasamos
	 * 
	 * @param name    el nombre de la actividad
	 * @param monitor el monitor de la actividad
	 * @return la actividad que le corresponden los datos pasados
	 */
	public Actividad getActividadByData(String name, Monitor monitor) {
		for (Actividad actividad : this.actividades) {
			if (actividad.getNombre().equals(name) && actividad.getMonitor().equals(monitor)) {
				return actividad;
			}
		}
		return null;
	}

	/**
	 * Funcion para obtener la reserva con los datos pasados
	 * 
	 * @param name       el nombre de la sesion reservada
	 * @param tipo       el tipo de objetivo de la sesion reservada si es una sesion
	 *                   de plan personalizado
	 * @param horaInicio la hora de inicio
	 * @param horaFin    la hora de fin
	 * @param fecha      la fecha de la sesion reservada
	 * @param sala       la sala donde se encuentra a sesion reservada
	 * @param aforo      el aforo de la sesion
	 * @return la reserva que le corresponde los datos pasados
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
						if (res.getSesion().getFecha().equals(fecha) == true
								&& res.getSesion().getHoraInicio().getHour() == (horaInicio.getHour())
								&& res.getSesion().getHoraInicio().getMinute() == (horaInicio.getMinute())
								&& res.getSesion().getHoraFin().getHour() == (horaFin.getHour())
								&& res.getSesion().getHoraFin().getMinute() == (horaFin.getMinute())
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
	 * Obtener el monitor que imparte una determinada activadd
	 * 
	 * @param nombre el nombre de la actividad
	 * @return el monitor que imparte la actividad
	 */
	public Monitor getMonitorByActividad(String nombre) {
		if (nombre == null) {
			return null;
		}

		for (Actividad actividad : this.getActividades()) {
			if (actividad.getNombre().equals(nombre)) {
				return actividad.getMonitor();
			}
		}

		return null;
	}

	/**
	 * Funcion para dar de alta Maquinas de propiedad
	 * 
	 * @param tipo        el tipo de maquina
	 * @param descripcion la descripcion de la maquina
	 * @param marca       la marca de la maquina
	 * @param precio      el precio de la maquina
	 * @param fecha       la fecha de compra de la maquina
	 * @return la maquina creada
	 * @throws FechaPosterior
	 * @throws ExcepcionUsuario
	 */
	public Maquina darAltaMaquinaPropiedad(String tipo, String descripcion, String marca, Double precio,
			LocalDate fecha) throws ExcepcionUsuario, FechaPosterior {

		if (tipo == null || tipo.isEmpty() || descripcion == null || descripcion.isEmpty() || marca == null
				|| marca.isEmpty() || precio < 0 || fecha.isAfter(LocalDate.now()) || fecha == null)
			return null;

		Maquina maquina = new Propiedad(tipo, descripcion, marca, precio, fecha);
		this.equipaciones.add(maquina);
		return maquina;
	}

	/**
	 * Funcion para dar de alta Maquinas alquiladas
	 * 
	 * @param tipo        el tipo de maquina
	 * @param descripcion la descripcion de la maquina
	 * @param marca       la marca de la maquina
	 * @param precio      el precio de la maquina
	 * @param fecha       la fecha de compra de la maquina
	 * @return la maquina creada
	 * @throws FechaPosterior
	 * @throws ExcepcionUsuario
	 */
	public Maquina darAltaMaquinaAlquilada(String tipo, String descripcion, String marca, Double precio,
			LocalDate fecha) throws ExcepcionUsuario, FechaPosterior {

		if (tipo == null || tipo.isEmpty() || descripcion == null || descripcion.isEmpty() || marca == null
				|| marca.isEmpty() || precio < 0 || fecha.isAfter(LocalDate.now()) || fecha == null)
			return null;

		Maquina maquina = new Alquilada(tipo, descripcion, marca, precio, fecha);
		this.equipaciones.add(maquina);
		return maquina;
	}

	/**
	 * Funcio para dar de alta el material del gimnasio
	 * 
	 * @param descripcion la descripcion del material
	 * @param numUnidades el numero de unidades de dicho material
	 * @param fechaCompra la fecha de compra
	 * @param precio      el precio dle material
	 * @return el material creado
	 */
	public Material darAltaMaterial(String descripcion, Integer numUnidades, LocalDate fechaCompra, Double precio) {

		if (descripcion == null || descripcion.isEmpty() || fechaCompra.isAfter(LocalDate.now()) || fechaCompra == null)
			return null;

		Material material = new Material(descripcion, numUnidades, fechaCompra, precio);
		this.equipaciones.add(material);
		return material;
	}

	/**
	 * GETTER funcion para obtener las maquinas del gimnasio
	 * 
	 * @return la lista de maquinas del gimnasio
	 */
	public List<Maquina> getMaquinas() {
		List<Maquina> maquinas = new ArrayList<>();
		for (Equipacion equipacion : this.equipaciones) {
			if (equipacion.isMaquina() == true) {
				maquinas.add((Maquina) equipacion);
			}
		}
		return maquinas;
	}

	/**
	 * GETTER funcion para obtener la lista de material del gimnasio
	 * 
	 * @return la lista del material
	 */
	public List<Material> getMaterial() {
		List<Material> materiales = new ArrayList<>();
		for (Equipacion equipacion : this.equipaciones) {
			if (equipacion.isMaterial() == true) {
				materiales.add((Material) equipacion);
			}
		}
		return materiales;
	}

	/**
	 * Funcion para obtener toda el equipamiento del gimnasio
	 * 
	 * @return lista de todo el equipamiento del gimnasio
	 */
	public List<Equipacion> getEquipaciones() {
		return this.equipaciones;
	}

	/**
	 * Funcion para cambiar el estado de una maquina
	 * 
	 * @param maquina la maquina a cambiar
	 * @param estado  el estado que vamos a establecer
	 * @return true si se ha podido cambiar y false si ha habido un error
	 */
	public Boolean cambiarEstadoMaquina(Maquina maquina, Estado estado) {
		if (maquina == null || estado == null) {
			return false;
		}

		// primero tiene que pasar por reparacion
		if (estado.equals(Estado.OPERATIVA)) {
			if (maquina.getEstado().equals(Estado.AVERIADA)) {
				return false;
			}
		}

		for (Maquina maq : this.getMaquinas()) {
			if (maq.equals(maquina)) {
				maquina.setEstado(estado);
				return true;
			}
		}
		return false;
	}

	/**
	 * Funcion para obtener una maquina a traves de su id
	 * 
	 * @param id el id de la maquina a buscar
	 * @return la maquina que le corresponde dicho id
	 */
	public Maquina getMaquinaById(Integer id) {
		if (id < 0) {
			return null;
		}

		for (Maquina maquina : this.getMaquinas()) {
			if (maquina.getId().equals(id)) {
				return maquina;
			}
		}
		return null;
	}

	/**
	 * Funcion para obtener le material a traves de los datos pasados
	 * 
	 * @param descripcion la descripcion de los datos
	 * @param numUnidades el numero de unidades del material
	 * @param fecha       la fecha de compra del material
	 * @param precio      el precio del material
	 * @return el material que le corresponden los datos pasados
	 */
	public Material getMaterialByData(String descripcion, Integer numUnidades, LocalDate fecha, Double precio) {
		if (descripcion == null || descripcion.isEmpty() || numUnidades < 0 || fecha.isAfter(LocalDate.now())
				|| precio < 0.0) {

			return null;
		}
		for (Equipacion equip : this.equipaciones) {
			if (equip.isMaterial()) {
				Material material = (Material) equip;
				if (material.getDescripcion().equals(descripcion) && material.getNumUnidades().equals(numUnidades)
						&& material.getFecha().equals(fecha) && material.getPrecio().equals(precio))
					return material;
			}

		}

		return null;
	}

	/**
	 * Funcion para obtener el equipamiento a traves de los datos pasados
	 * 
	 * @param descripcion la descripcion de los datos
	 * @param fecha       la fecha de compra del equipamiento
	 * @param precio      el precio del equipamiento
	 * @return la equipacion que le corresponden los datos pasados
	 */
	public Equipacion getEquipacionByData(String descripcion, LocalDate fecha, Double precio) {
		if (descripcion == null || descripcion.isEmpty() || fecha.isAfter(LocalDate.now()) || precio < 0.0)
			return null;

		for (Equipacion equip : this.equipaciones) {
			if (equip.getDescripcion().equals(descripcion) && equip.getFecha().equals(fecha)
					&& equip.getPrecio().equals(precio))
				return equip;

		}
		return null;
	}

	/**
	 * Funcion para obtener un plan personalizado que contenga una sesion
	 * 
	 * @param sesion sesion que contiene
	 * @return el plan personalizado que buscamos
	 */
	public PlanPersonalizado getPlanBySesion(Sesion sesion) {
		if (sesion == null) {
			return null;
		}

		for (PlanPersonalizado plan : this.getPlanesPersonalizados()) {
			if (plan.getSesionesMonitorizadas().contains(sesion)) {
				return plan;
			}
		}

		return null;
	}

	/**
	 * Funcion para obtener una sesion de un plan personalizado
	 * 
	 * @param name       nombre
	 * @param tipo       tipo objetivo
	 * @param horaInicio hora de inicio
	 * @param horaFin    hora de fin
	 * @param fecha      fecha
	 * @param sala       sala
	 * @param aforo      aforo
	 * @return la sesion que buscamos
	 */
	public Sesion getSesionInPlanByData(String name, TipoObjetivo tipo, LocalDateTime horaInicio, LocalDateTime horaFin,
			LocalDate fecha, Sala sala, int aforo) {

		for (PlanPersonalizado plan : this.getPlanesPersonalizados()) {
			if (plan.getNombre().equals(name) == true) {
				for (SesionMonitorizada sesion : plan.getSesionesMonitorizadas()) {
					if (sesion.getHoraInicio().equals(horaInicio) && sesion.getHoraFin().equals(horaFin)
							&& sesion.getFecha().equals(fecha) && sesion.getSala().equals(sala)) {
						return sesion;
					}
				}
			}
		}

		return null;
	}

	/**
	 * Devuelve el ultimo id, es decir el mas alto
	 * 
	 * @return valor del id mas alto
	 */
	public Integer maxId() {
		Integer id = -1;
		for (Maquina maquina : this.getMaquinas()) {
			if (maquina.getId() >= id)
				id = maquina.getId();
		}
		return id;
	}

}
