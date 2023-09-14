package gui.controladores.cliente;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.JOptionPane;

import aplicacion.Gimnasio;
import aplicacion.usuario.Cliente;
import aplicacion.usuario.Monitor;
import aplicacion.actividad.Actividad;
import aplicacion.actividad.ActividadGrupal;
import aplicacion.actividad.PlanPersonalizado;
import aplicacion.actividad.TipoObjetivo;
import aplicacion.actividad.sesion.Sesion;
import aplicacion.excepciones.ApuntadoMismaHora;
import aplicacion.excepciones.ApuntarseAntesDe48h;
import aplicacion.excepciones.ExcepcionCancelaciones;
import aplicacion.excepciones.ExcepcionEdad;
import aplicacion.excepciones.ExcepcionPago;
import aplicacion.excepciones.ExcepcionUsuario;
import aplicacion.excepciones.ExcepcionVeterania;
import aplicacion.excepciones.FechaPosterior;
import aplicacion.excepciones.SalaLlena;
import aplicacion.sala.Sala;
import gui.Window;
import gui.cliente.PlanesPersonalizadosWindow;
import gui.monitor.MisPlanesPersonalizadosWindow;

/**
 * Este fichero muestra todo lo que tiene que ver con la clase
 * ControladorPlanesPersonalizados
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class ControladorPlanesPersonalizados implements ActionListener {
	private PlanesPersonalizadosWindow planesPersonalizados;
	private Window window;
	private Gimnasio gimnasio;

	/**
	 * Constructor ControladorPlanesPersonalizados
	 * 
	 * @param window   la ventana general
	 * @param gimnasio el gimnasio
	 */
	public ControladorPlanesPersonalizados(Window window, Gimnasio gimnasio) {
		this.window = window;
		this.planesPersonalizados = window.getPlanesPersonalizadosWindow();
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
		if (e.getActionCommand().equals("Atras")) {
			this.window.mostrarPanel("Cliente Window");
		} else if (e.getActionCommand().equals("Apuntate")) {
			if (this.planesPersonalizados.getTabla().getSelectedRowCount() == 0)
				JOptionPane.showMessageDialog(null, "Asegurese de seleccionar una actividad", null,
						JOptionPane.WARNING_MESSAGE);
			else {

				int filaSeleccionada = this.planesPersonalizados.getTabla().getSelectedRow();

				if (this.planesPersonalizados.getTabla().getValueAt(filaSeleccionada, 0) == null
						|| this.planesPersonalizados.getTabla().getValueAt(filaSeleccionada, 0).toString() == "")
					JOptionPane.showMessageDialog(null, "No se puede apuntar. Selecciona una fila con contenido", null,
							JOptionPane.WARNING_MESSAGE);
				else {

					Cliente cliente = (Cliente) this.gimnasio.getUsuarioRegistrado();
					String nombre = this.planesPersonalizados.getTabla().getValueAt(filaSeleccionada, 0).toString();

					TipoObjetivo objetivo = this.gimnasio.getTipoObjetivoByName(
							this.planesPersonalizados.getTabla().getValueAt(filaSeleccionada, 1).toString());

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

					int aforo = Integer
							.valueOf(this.planesPersonalizados.getTabla().getValueAt(filaSeleccionada, 6).toString());

					Sesion sesion = this.gimnasio.getSesionByData(nombre, objetivo, horaInicio, horaFin, fecha, sala,
							aforo);
					try {
						if (cliente.contratarPlanPersonalizado(this.gimnasio.getPlanBySesion(sesion)) == true) {

							ArrayList<PlanPersonalizado> actividades = new ArrayList<>();

							for (PlanPersonalizado act : this.gimnasio.getPlanesPersonalizados()) {
								actividades.add(act);
							}
							try {
								this.window.getPlanesPersonalizadosWindow().updatePlanes(actividades);
							} catch (ExcepcionUsuario | FechaPosterior e1) {
								e1.printStackTrace();
							}
							JOptionPane.showMessageDialog(null, "Te has apuntado a un plan personalizado", null,
									JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, "No te has podido apuntar a un plan personalizado",
									null, JOptionPane.WARNING_MESSAGE);
						}
					} catch (HeadlessException e1) {
						e1.printStackTrace();
					} catch (SalaLlena e2) {
						JOptionPane.showMessageDialog(null, "La sala esta llena", null, JOptionPane.WARNING_MESSAGE);
					} catch (ExcepcionPago e1) {
						JOptionPane.showMessageDialog(null, "No has pagado", null, JOptionPane.WARNING_MESSAGE);
					} catch (ExcepcionEdad e1) {
						JOptionPane.showMessageDialog(null, "No cumples el requisito de EDAD", null,
								JOptionPane.WARNING_MESSAGE);
					} catch (ExcepcionVeterania e1) {
						JOptionPane.showMessageDialog(null, "No cumples el requisito de VETERANIA", null,
								JOptionPane.WARNING_MESSAGE);
					} catch (ExcepcionCancelaciones e1) {
						JOptionPane.showMessageDialog(null, "No cumples el requisito de CANCELACIONES", null,
								JOptionPane.WARNING_MESSAGE);
					} catch (FechaPosterior e1) {
						e1.printStackTrace();
					} catch (ExcepcionUsuario e1) {
						e1.printStackTrace();
					} catch (ApuntarseAntesDe48h e1) {
						JOptionPane.showMessageDialog(null, "No te puedes apuntar antes de 48 horas de la sesion", null,
								JOptionPane.WARNING_MESSAGE);

					} catch (ApuntadoMismaHora e1) {
						JOptionPane.showMessageDialog(null, "Ya esta apuntado a otra sesion a la misma hora", null,
								JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		}
	}
}
