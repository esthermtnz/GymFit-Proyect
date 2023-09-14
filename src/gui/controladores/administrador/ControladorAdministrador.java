package gui.controladores.administrador;

import aplicacion.*;
import aplicacion.equipacion.*;
import aplicacion.actividad.Actividad;
import aplicacion.actividad.TipoActividad;
import aplicacion.actividad.sesion.Sesion;
import aplicacion.actividad.sesion.SesionLibre;
import aplicacion.sala.Sala;
import aplicacion.sala.SalaSimple;
import aplicacion.sala.SalaCompuesta;
import aplicacion.usuario.Administrador;
import aplicacion.usuario.Monitor;
import aplicacion.excepciones.ExcepcionUsuario;
import aplicacion.excepciones.FechaPosterior;
import aplicacion.excepciones.MesNoTerminado;
import aplicacion.actividad.*;
import gui.Window;
import gui.administrador.AdministradorWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;

/**
 * Este fichero muestra todo lo que tiene que ver con la clase
 * ControladorAdministrador
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class ControladorAdministrador implements ActionListener {
	private AdministradorWindow administrador;
	private Window window;
	private Gimnasio gimnasio;

	/**
	 * Constructor ControladorAdministrador
	 * 
	 * @param window   la ventana general
	 * @param gimnasio el gimnasio
	 */
	public ControladorAdministrador(Window window, Gimnasio gimnasio) {
		this.window = window;
		this.administrador = window.getAdministradorWindow();
		this.gimnasio = gimnasio;

	}

	/**
	 * Funcion para detectar todos los movimientos que realice el usuario en la
	 * aplicacion
	 * 
	 * @param e la accion
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Cerrar Sesion")) {
			try {
				gimnasio.cerrarSesion(this.gimnasio.getUsuarioRegistrado());
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			this.window.mostrarPanel("Login Window");
		} else if (e.getActionCommand().equals("Registro de beneficios")) {
			ArrayList<Reserva> reservas = new ArrayList<>();

			Month month = Month.of(Integer.parseInt(this.window.getBeneficiosGimnasioWindow().getBoxMeses()));
			int year = Integer.parseInt(this.window.getBeneficiosGimnasioWindow().getBoxAnyos());

			try {
				reservas = this.gimnasio.filtradoReservasMes(month, year);
			} catch (MesNoTerminado e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			this.window.getBeneficiosGimnasioWindow().updateBeneficios(reservas);
			this.window.mostrarPanel("Beneficios Window");

		} else if (e.getActionCommand().equals("Configurar Sala")) {
			if (this.window.getConfigurarSalasWindow().getBoxSala().equals("Sala general")) {
				this.window.getConfigurarSalasWindow().updateContenido1();
				if (this.window.getConfigurarSalasWindow().getBoxClimatizacion().equals("No")) {
					this.window.getConfigurarSalasWindow().updateContenidoElim3();
				} else {
					this.window.getConfigurarSalasWindow().updateContenido1();
				}
			} else if (this.window.getConfigurarSalasWindow().getBoxSala().equals("Subsala")) {
				this.window.getConfigurarSalasWindow().updateContenido2();
			}
			
			HashSet<String> tipoSala = new HashSet<>();
			for (Sala sala : this.gimnasio.getSalas()) {
				tipoSala.add(sala.getNombre());
			}
			this.window.getConfigurarSalasWindow().updateSalas(tipoSala);
			this.window.mostrarPanel("Configurar Salas Window");

		} else if (e.getActionCommand().equals("Crear Actividad Grupal")) {

			HashSet<String> clavesMonitores = new HashSet<>();
			for (String clave : this.gimnasio.getUsuarios().keySet()) {
				if (this.gimnasio.isMonitor(clave)) {
					clavesMonitores.add(clave);
				}
			}
			this.window.getCrearActividadGrupalWindow().updateMonitores(clavesMonitores);

			HashSet<String> tipoActividadGrupal = new HashSet<>();
			for (TipoActividad tipoActividad : this.gimnasio.getTipoActividadGrupal()) {
				tipoActividadGrupal.add(tipoActividad.getNombre());
			}
			this.window.getCrearActividadGrupalWindow().updateTipoActividad(tipoActividadGrupal);

			this.window.mostrarPanel("Crear Actividad Grupal Window");

		} else if (e.getActionCommand().equals("Crear Sesion Libre")) {
			HashSet<String> tipoSala = new HashSet<>();
			for (Sala sala : this.gimnasio.getSalas()) {
				if (sala instanceof SalaSimple) {
					SalaSimple salaSimple = (SalaSimple) sala;
					tipoSala.add(salaSimple.getNombre());
				}
			}
			this.window.getCrearSesionLibreWindow().updateSalas(tipoSala);
			this.window.mostrarPanel("Crear Sesion Libre Window");

		} else if (e.getActionCommand().equals("Consulta de reservas")) {
			ArrayList<Reserva> reservas = new ArrayList<>();
			ArrayList<Reserva> cancelaciones = new ArrayList<>();

			for (Actividad act : this.gimnasio.getActividadesGrupales()) {
				for (Sesion ses : act.getSesionesMonitorizadas()) {
					for (Reserva reserva : ses.getReservas()) {
						reservas.add(reserva);
					}
				}
			}

			for (int i = 0; i < 12; i++) {
				for (Actividad act : this.gimnasio.getActividadesGrupales()) {
					for (Reserva res : this.gimnasio.reservasCanceladasMes(LocalDate.now().getMonth(), act)) {
						cancelaciones.add(res);
					}
				}
			}

			try {
				this.window.getReservasWindow().updateReservas(reservas);
			} catch (ExcepcionUsuario e1) {
				System.out.println(e1);
			} catch (FechaPosterior e2) {
				System.out.println(e2);
			}

			this.window.mostrarPanel("Reservas Window");

		} else if (e.getActionCommand().equals("Consulta de planes monitor")) {
			HashSet<String> monitoresSet = new HashSet<>();
			for (String clave : this.gimnasio.getUsuarios().keySet()) {
				if (this.gimnasio.isMonitor(clave)) {
					monitoresSet.add(clave);
				}
			}
			this.window.getConsultarPlanesMonitorWindow().updateMonitores(monitoresSet);

			ArrayList<PlanPersonalizado> actividades = new ArrayList<>();
			for (PlanPersonalizado act : this.gimnasio
					.getMonitorByName(this.window.getConsultarPlanesMonitorWindow().getBoxMonitor())
					.getPlanesPersonalizados()) {
				actividades.add((PlanPersonalizado) act);
			}
			try {
				this.window.getConsultarPlanesMonitorWindow().updatePlanes(actividades);
			} catch (ExcepcionUsuario | FechaPosterior e1) {
				e1.printStackTrace();
			}

			this.window.mostrarPanel("Consultar Planes Monitor Window");

		} else if (e.getActionCommand().equals("Configurar Sueldos Monitores")) {
			this.window.mostrarPanel("Sueldo Monitores Window");

		} else if (e.getActionCommand().equals("Configurar Precios")) {
			this.window.mostrarPanel("Configurar Precios Window");

		} else if (e.getActionCommand().equals("Configurar Penalizaciones a clientes")) {
			this.window.mostrarPanel("Penalizaciones Cliente Window");

		} else if (e.getActionCommand().equals("Dar de alta a un monitor")) {
			this.window.mostrarPanel("Dar Alta Monitor Window");

		} else if (e.getActionCommand().equals("Crear Tipo Actividad")) {
			this.window.mostrarPanel("Tipo Actividad Window");
		} else if (e.getActionCommand().equals("Consulta de actividades monitor")) {
			HashSet<String> monitoresSet = new HashSet<>();
			for (String clave : this.gimnasio.getUsuarios().keySet()) {
				if (this.gimnasio.isMonitor(clave)) {
					monitoresSet.add(clave);
				}
			}
			this.window.getConsultarActividadesGrupalesWindow().updateMonitores(monitoresSet);

			ArrayList<ActividadGrupal> actividades = new ArrayList<>();

			for (ActividadGrupal act : this.gimnasio
					.getMonitorByName(this.window.getConsultarActividadesGrupalesWindow().getBoxMonitor().toString())
					.getActividadesGrupales()) {
				actividades.add(act);
			}
			try {
				this.window.getConsultarActividadesGrupalesWindow().updateActividades(actividades);
			} catch (ExcepcionUsuario | FechaPosterior e1) {
				e1.printStackTrace();
			}
			this.window.mostrarPanel("Consultar Actividades Grupales Window");
		} else if (e.getActionCommand().equals("Dar Material Alta")) {
			this.window.mostrarPanel("Dar Material Alta Window");
		} else if (e.getActionCommand().equals("Dar Maquina Alta")) {
			this.window.mostrarPanel("Dar Maquina Alta Window");
		} else if (e.getActionCommand().equals("Consultar Gastos Equipacion")) {
			ArrayList<Equipacion> equipacion = new ArrayList<>();

			Month month = Month.of(Integer.parseInt(this.window.getConsultarGastosEquipacionWindow().getBoxMeses()));
			int year = Integer.parseInt(this.window.getConsultarGastosEquipacionWindow().getBoxAnyos());

			try {
				equipacion = this.gimnasio.filtradoEquipacionMes(month, year);
			} catch (MesNoTerminado e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.window.getConsultarGastosEquipacionWindow().updateGastosEquipacion(equipacion);
			this.window.mostrarPanel("Consultar Gastos Equipacion Window");
		} else if (e.getActionCommand().equals("Cambiar Estado Maquinas")) {
			ArrayList<Maquina> maquinasPropiedad = new ArrayList<>();
			ArrayList<Maquina> maquinasAlquiladas = new ArrayList<>();

			if (this.window.getCambiarEstadoMaquinasWindow().getBoxMaquina().equals("Propiedad")) {
				for (Maquina maq : this.gimnasio.getMaquinas()) {
					if (maq.esPropiedad() == true) {
						maquinasPropiedad.add(maq);
					}
				}
				this.window.getCambiarEstadoMaquinasWindow().updateMaquinas(maquinasPropiedad);

			} else if (this.window.getCambiarEstadoMaquinasWindow().getBoxMaquina().equals("Alquilada")) {

				for (Maquina maq : this.gimnasio.getMaquinas()) {
					if (maq.esAlquilada() == true) {
						maquinasAlquiladas.add(maq);
					}
				}
				this.window.getCambiarEstadoMaquinasWindow().updateMaquinas(maquinasAlquiladas);
			}

			this.window.mostrarPanel("Cambiar Estado Maquinas Window");
		} else if (e.getActionCommand().equals("Consulta Sesiones Libres")) {
			ArrayList<SesionLibre> sesionesLibres = new ArrayList<>();
			for (SesionLibre sesion : this.gimnasio.getSesionesLibres()) {
				sesionesLibres.add(sesion);
			}
			try {
				this.window.getConsultarSesionLibreAdminWindow().updateSesiones(sesionesLibres);
			} catch (ExcepcionUsuario | FechaPosterior e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.window.mostrarPanel("Consultar Sesion Libre Admin Window");
		}

	}

}
