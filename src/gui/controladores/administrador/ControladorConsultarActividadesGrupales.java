package gui.controladores.administrador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import javax.swing.JOptionPane;

import aplicacion.Gimnasio;
import aplicacion.actividad.ActividadGrupal;
import aplicacion.actividad.Actividad;
import aplicacion.actividad.PlanPersonalizado;
import aplicacion.actividad.TipoObjetivo;
import aplicacion.actividad.sesion.Sesion;
import aplicacion.excepciones.ExcepcionUsuario;
import aplicacion.excepciones.FechaPosterior;
import aplicacion.sala.Sala;
import aplicacion.sala.SalaSimple;
import aplicacion.*;
import aplicacion.usuario.*;
import aplicacion.usuario.Monitor;
import gui.Window;
import gui.administrador.ConsultarActividadesGrupalesWindow;
import gui.monitor.MisPlanesPersonalizadosWindow;

/**
 * Este fichero muestra todo lo que tiene que ver con la clase
 * ControladorConsultarActividadesGrupales
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class ControladorConsultarActividadesGrupales implements ActionListener {
	private ConsultarActividadesGrupalesWindow actividades;
	private Window window;
	private Gimnasio gimnasio;

	/**
	 * Constructor ControladorConsultarActividadesGrupales
	 * 
	 * @param window   la ventana general
	 * @param gimnasio el gimnasio
	 */
	public ControladorConsultarActividadesGrupales(Window window, Gimnasio gimnasio) {
		this.window = window;
		this.actividades = window.getConsultarActividadesGrupalesWindow();
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
		ArrayList<ActividadGrupal> listaPlanes = null;

		listaPlanes = new ArrayList<ActividadGrupal>(
				gimnasio.getMonitorByName(this.window.getConsultarActividadesGrupalesWindow().getBoxMonitor())
						.getActividadesGrupales());

		try {
			this.window.getConsultarActividadesGrupalesWindow().updateActividades(listaPlanes);
		} catch (ExcepcionUsuario | FechaPosterior e1) {
			e1.printStackTrace();
		}

		if (e.getActionCommand().equals("Atras")) {
			this.window.mostrarPanel("Administrador Window");

		} else if (e.getActionCommand().equals("Cancelar Sesion")) {

			if (this.actividades.getTabla().getSelectedRowCount() == 0)
				JOptionPane.showMessageDialog(null, "Asegurese de seleccionar una sesion", null,
						JOptionPane.WARNING_MESSAGE);
			else {
				int filaSeleccionada = this.actividades.getTabla().getSelectedRow();

				if (this.actividades.getTabla().getValueAt(filaSeleccionada, 0) == null
						|| this.actividades.getTabla().getValueAt(filaSeleccionada, 0).toString() == "")
					JOptionPane.showMessageDialog(null, "No se puede cancelar. Seleccione una fila con conetenido.",
							null, JOptionPane.WARNING_MESSAGE);
				else {

					String nombre = this.actividades.getTabla().getValueAt(filaSeleccionada, 0).toString();

					Actividad actividad = this.gimnasio.getActividadByData(nombre,
							this.gimnasio.getMonitorByActividad(nombre));

					if (actividad.getSesionesMonitorizadas().size() > 0) {

						LocalDate fecha = LocalDate
								.parse(this.actividades.getTabla().getValueAt(filaSeleccionada, 3).toString());

						LocalTime lt1 = LocalTime
								.parse(this.actividades.getTabla().getValueAt(filaSeleccionada, 1).toString());
						LocalDateTime horaInicio = LocalDateTime.of(fecha, lt1);

						LocalTime lt2 = LocalTime
								.parse(this.actividades.getTabla().getValueAt(filaSeleccionada, 2).toString());
						LocalDateTime horaFin = LocalDateTime.of(fecha, lt2);

						Sala sala = this.gimnasio
								.getSalaByName(this.actividades.getTabla().getValueAt(filaSeleccionada, 4).toString());

						int aforo = Integer
								.valueOf(this.actividades.getTabla().getValueAt(filaSeleccionada, 5).toString());

						Sesion sesion = this.gimnasio.getSesionByData(nombre, null, horaInicio, horaFin, fecha, sala,
								aforo);

						if (this.gimnasio.cancelarSesionActividadGrupal(sesion)) {

							JOptionPane.showMessageDialog(null, "Has cancelado una sesion de actividad grupal", null,
									JOptionPane.INFORMATION_MESSAGE);

							ArrayList<ActividadGrupal> planes = new ArrayList<>();

							for (ActividadGrupal act : (this.gimnasio
									.getMonitorByName(this.actividades.getBoxMonitor())).getActividadesGrupales()) {
								planes.add(act);
							}

							try {
								this.window.getConsultarActividadesGrupalesWindow().updateActividades(planes);
							} catch (ExcepcionUsuario | FechaPosterior e1) {
								e1.printStackTrace();
							}

							try {
								for (String cliente : gimnasio.getUsuarios().keySet()) {
									if (gimnasio.getUsuarios().get(cliente) instanceof Cliente) {
										ArrayList<Reserva> reservas = new ArrayList<>();

										for (Reserva res : ((Cliente) gimnasio.getUsuarios().get(cliente))
												.getReservas()) {
											reservas.add(res);
										}
										this.window.getMisReservasWindow().updateMisReservas(reservas);

									}
								}
							} catch (ExcepcionUsuario | FechaPosterior e1) {
								e1.printStackTrace();

							}

						} else
							JOptionPane.showMessageDialog(null, "No has podido cancelar una sesion de actividad grupal",
									null, JOptionPane.WARNING_MESSAGE);
					}
				}
			}

		} else if (e.getActionCommand().equals("Crear nueva sesion")) {
			if (this.actividades.getTabla().getSelectedRowCount() == 0)
				JOptionPane.showMessageDialog(null, "Asegurese de seleccionar un plan", null,
						JOptionPane.WARNING_MESSAGE);
			else {
				int filaSeleccionada = this.actividades.getTabla().getSelectedRow();

				if (this.actividades.getTabla().getValueAt(filaSeleccionada, 0) == null
						|| this.actividades.getTabla().getValueAt(filaSeleccionada, 0).toString() == "")
					JOptionPane.showMessageDialog(null, "No se puede apuntar. Seleccione una fila con contenido", null,
							JOptionPane.WARNING_MESSAGE);
				else {
					HashSet<String> tipoSala = new HashSet<>();
					for (Sala sala : this.gimnasio.getSalas()) {
						if (sala instanceof SalaSimple) {
							SalaSimple salaSimple = (SalaSimple) sala;
							tipoSala.add(salaSimple.getNombre());
						}
					}
					this.window.getCrearSesionAGWindow().updateSalas(tipoSala);

					this.window.mostrarPanel("Crear Sesion Actividad Grupal Window");
				}
			}

		} else if (e.getActionCommand().equals("Cancelar Actividad")) {
			if (this.actividades.getTabla().getSelectedRowCount() == 0)
				JOptionPane.showMessageDialog(null, "Asegurese de seleccionar una reserva", null,
						JOptionPane.WARNING_MESSAGE);
			else {
				int filaSeleccionada = this.actividades.getTabla().getSelectedRow();

				if (this.actividades.getTabla().getValueAt(filaSeleccionada, 0) == null
						|| this.actividades.getTabla().getValueAt(filaSeleccionada, 0).toString() == "")
					JOptionPane.showMessageDialog(null, "No puede cancelar. Seleccione una fila con conetnido", null,
							JOptionPane.WARNING_MESSAGE);
				else {

					String nombre = this.actividades.getTabla().getValueAt(filaSeleccionada, 0).toString();
					Monitor monitor = this.gimnasio.getMonitorByActividad(nombre);
					Actividad actividad = this.gimnasio.getActividadByData(nombre, monitor);

					if (this.gimnasio.cancelarActividadGrupal(actividad)) {

						ArrayList<ActividadGrupal> actividades = new ArrayList<>();

						for (ActividadGrupal act : monitor.getActividadesGrupales()) {

							actividades.add(act);
						}

						try {
							this.window.getConsultarActividadesGrupalesWindow().updateActividades(actividades);
						} catch (ExcepcionUsuario | FechaPosterior e1) {
							e1.printStackTrace();
						}

						JOptionPane.showMessageDialog(null, "Has cancelado una actividad", null,
								JOptionPane.INFORMATION_MESSAGE);

					} else
						JOptionPane.showMessageDialog(null, "No has podido cancelar una actividad", null,
								JOptionPane.WARNING_MESSAGE);
				}
			}
		}

	}
}
