package gui.controladores.monitor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import javax.swing.JOptionPane;

import aplicacion.actividad.TipoObjetivo;
import aplicacion.actividad.ActividadGrupal;
import aplicacion.Gimnasio;
import aplicacion.actividad.Actividad;
import aplicacion.actividad.PlanPersonalizado;
import aplicacion.actividad.sesion.Sesion;
import aplicacion.actividad.sesion.SesionMonitorizada;
import aplicacion.actividad.sesion.SesionPP;
import aplicacion.excepciones.ExcepcionUsuario;
import aplicacion.excepciones.FechaPosterior;
import aplicacion.sala.Sala;
import aplicacion.sala.SalaSimple;
import aplicacion.usuario.Monitor;
import aplicacion.*;
import aplicacion.usuario.*;
import gui.Window;
import gui.monitor.MisActividadesGrupalesWindow;
import gui.monitor.MisPlanesPersonalizadosWindow;
/**
 * Este fichero muestra todo lo que tiene que ver con la clase ControladorMisPlanesPersonalizados
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class ControladorMisPlanesPersonalizados implements ActionListener {

	private MisPlanesPersonalizadosWindow planesPersonalizados;
	private Window window;
	private Gimnasio gimnasio;
	/**
	 * Constructor ControladorMisPlanesPersonalizados
	 * @param window la ventana general
	 * @param gimnasio el gimnasio
	 */
	public ControladorMisPlanesPersonalizados(Window window, Gimnasio gimnasio) {
		this.window = window;
		this.planesPersonalizados = window.getMisPlanesPersonalizadosWindow();
		this.gimnasio = gimnasio;
	}
	/**
	 * Funcion para detectar todos los movimientos que realice el usuario en la aplicacion
	 * @param e la accion
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Atras")) {
			this.window.mostrarPanel("Monitor Window");
		} else if (e.getActionCommand().equals("Crear nuevo plan")) {

			this.window.mostrarPanel("Crear Plan Personalizado Window");

		} else if (e.getActionCommand().equals("Cancelar Sesion")) {

			if (this.planesPersonalizados.getTabla().getSelectedRowCount() == 0)
				JOptionPane.showMessageDialog(null, "Asegurese de seleccionar una sesion", null,
						JOptionPane.WARNING_MESSAGE);
			else {
				int filaSeleccionada = this.planesPersonalizados.getTabla().getSelectedRow();

				if (this.planesPersonalizados.getTabla().getValueAt(filaSeleccionada, 0) == null || this.planesPersonalizados.getTabla().getValueAt(filaSeleccionada, 0).toString() == "")
					JOptionPane.showMessageDialog(null, "No se puede cancelar una sesion. Selecciona una fila con contenido", null, JOptionPane.WARNING_MESSAGE);
				else {

					String nombre = this.planesPersonalizados.getTabla().getValueAt(filaSeleccionada, 0).toString();

					TipoObjetivo objetivo = this.gimnasio.getTipoObjetivoByName(
							this.planesPersonalizados.getTabla().getValueAt(filaSeleccionada, 1).toString());

					Actividad actividad = this.gimnasio.getActividadByData(nombre,
							(Monitor) this.gimnasio.getUsuarioRegistrado());

					if (actividad.getSesionesMonitorizadas().size() > 0) {
						LocalDate fecha = LocalDate
								.parse(this.planesPersonalizados.getTabla().getValueAt(filaSeleccionada, 4).toString());

						LocalTime lt1 = LocalTime
								.parse(this.planesPersonalizados.getTabla().getValueAt(filaSeleccionada, 2).toString());
						LocalDateTime horaInicio = LocalDateTime.of(fecha, lt1);

						LocalTime lt2 = LocalTime
								.parse(this.planesPersonalizados.getTabla().getValueAt(filaSeleccionada, 3).toString());
						LocalDateTime horaFin = LocalDateTime.of(fecha, lt2);

						Sala sala = this.gimnasio.getSalaByName(
								this.planesPersonalizados.getTabla().getValueAt(filaSeleccionada, 5).toString());

						int aforo = Integer.valueOf(
								this.planesPersonalizados.getTabla().getValueAt(filaSeleccionada, 6).toString());

						Sesion sesion = this.gimnasio.getSesionByData(nombre, objetivo, horaInicio, horaFin, fecha,
								sala, aforo);

						if (((Monitor) this.gimnasio.getUsuarioRegistrado()).cancelarSesionPlanPersonalizado(sesion)) {

							ArrayList<PlanPersonalizado> planes = new ArrayList<>();

							for (PlanPersonalizado act : ((Monitor) this.gimnasio.getUsuarioRegistrado())
									.getPlanesPersonalizados()) {

								planes.add(act);
							}

							try {
								this.window.getMisPlanesPersonalizadosWindow().updatePlanes(planes);
							} catch (ExcepcionUsuario | FechaPosterior e1) {
								e1.printStackTrace();
							}
							
							/*Actualizar la lista de reservas de los clientes*/
							try{
								for(String cliente: gimnasio.getUsuarios().keySet()){
									if(gimnasio.getUsuarios().get(cliente) instanceof Cliente){
										ArrayList<Reserva> reservas = new ArrayList<>();
										
										for(Reserva res : ((Cliente)gimnasio.getUsuarios().get(cliente)).getReservas())
										{
											reservas.add(res);
										}
										this.window.getMisReservasWindow().updateMisReservas(reservas);
										
									}
								}
							}catch(ExcepcionUsuario | FechaPosterior e1){
								e1.printStackTrace();

							}
							
							JOptionPane.showMessageDialog(null, "Has cancelado una sesion", null,
									JOptionPane.INFORMATION_MESSAGE);

						} else
							JOptionPane.showMessageDialog(null, "No has podido cancelar una sesion", null,
									JOptionPane.WARNING_MESSAGE);

					}
				}

			}
		} else if (e.getActionCommand().equals("Crear nueva sesion PP")) {

			if (this.planesPersonalizados.getTabla().getSelectedRowCount() == 0)
				JOptionPane.showMessageDialog(null, "Asegurese de seleccionar un plan", null,
						JOptionPane.WARNING_MESSAGE);
			else {
				int filaSeleccionada = this.planesPersonalizados.getTabla().getSelectedRow();

				if (this.planesPersonalizados.getTabla().getValueAt(filaSeleccionada, 0) == null || this.planesPersonalizados.getTabla().getValueAt(filaSeleccionada, 0).toString() == "")
					JOptionPane.showMessageDialog(null, "No se puede crear una nueva sesion PP. Selecciona una fila con contenido", null, JOptionPane.WARNING_MESSAGE);
				else {
					HashSet<String> tipoSala = new HashSet<>();
					for (Sala sala : this.gimnasio.getSalas()) {
						if(sala instanceof SalaSimple) {
							SalaSimple salaSimple = (SalaSimple)sala;
							tipoSala.add(salaSimple.getNombre());
						}
					}

					this.window.getCrearSesionPPWindow().updateSalas(tipoSala);

					this.window.mostrarPanel("Crear Sesion Personalizada Window");
				}

			}

		} else if (e.getActionCommand().equals("Add sesion grupal")) {
			if (this.planesPersonalizados.getTabla().getSelectedRowCount() == 0)
				JOptionPane.showMessageDialog(null, "Asegurese de seleccionar un plan", null,
						JOptionPane.WARNING_MESSAGE);
			else {

				int filaSeleccionada = this.planesPersonalizados.getTabla().getSelectedRow();

				if (this.planesPersonalizados.getTabla().getValueAt(filaSeleccionada, 0) == null || this.planesPersonalizados.getTabla().getValueAt(filaSeleccionada, 0).toString() == "")
					JOptionPane.showMessageDialog(null, "No se puede añadir una sesion grupal. Selecciona una fila con contenido", null, JOptionPane.WARNING_MESSAGE);
				else {
					ArrayList<ActividadGrupal> listaPlanes = new ArrayList<ActividadGrupal>(
							((Monitor) this.gimnasio.getUsuarioRegistrado()).getActividadesGrupales());
					try {
						this.window.getAddSesionAGWindow().updateActividades(listaPlanes);
					} catch (ExcepcionUsuario | FechaPosterior e1) {
						e1.printStackTrace();
					}

					this.window.mostrarPanel("Add Sesion Grupal Window");
				}

			}
		} else if (e.getActionCommand().equals("Cancelar Plan")) {
			if (this.planesPersonalizados.getTabla().getSelectedRowCount() == 0)
				JOptionPane.showMessageDialog(null, "Asegurese de seleccionar un plan", null,
						JOptionPane.WARNING_MESSAGE);
			else {
				int filaSeleccionada = this.planesPersonalizados.getTabla().getSelectedRow();

				if (this.planesPersonalizados.getTabla().getValueAt(filaSeleccionada, 0) == null
						|| this.planesPersonalizados.getTabla().getValueAt(filaSeleccionada, 0).toString() == "")
					JOptionPane.showMessageDialog(null,
							"No se puede cancelar. Seleccione una fila con contenido", null,
							JOptionPane.WARNING_MESSAGE);
				else{

					String nombre = this.planesPersonalizados.getTabla().getValueAt(filaSeleccionada, 0).toString();

					TipoObjetivo objetivo = this.gimnasio.getTipoObjetivoByName(
							this.planesPersonalizados.getTabla().getValueAt(filaSeleccionada, 1).toString());

					Actividad actividad = this.gimnasio.getActividadByData(nombre,
							(Monitor) this.gimnasio.getUsuarioRegistrado());

					if (((Monitor) this.gimnasio.getUsuarioRegistrado()).cancelarPlanPersonalizado(actividad)) {

						ArrayList<PlanPersonalizado> planes = new ArrayList<>();

						for (PlanPersonalizado act : ((Monitor) this.gimnasio.getUsuarioRegistrado())
								.getPlanesPersonalizados()) {

							planes.add(act);
						}

						try {
							this.window.getMisPlanesPersonalizadosWindow().updatePlanes(planes);
						} catch (ExcepcionUsuario | FechaPosterior e1) {
							e1.printStackTrace();
						}

						JOptionPane.showMessageDialog(null, "Has cancelado un plan", null,
								JOptionPane.INFORMATION_MESSAGE);

					} else
						JOptionPane.showMessageDialog(null, "No has podido cancelar un plan", null,
								JOptionPane.WARNING_MESSAGE);

				}
			}
		}

	}
}
