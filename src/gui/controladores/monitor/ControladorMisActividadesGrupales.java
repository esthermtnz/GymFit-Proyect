package gui.controladores.monitor;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import aplicacion.Gimnasio;
import aplicacion.actividad.Actividad;
import aplicacion.actividad.ActividadGrupal;
import aplicacion.actividad.TipoObjetivo;
import aplicacion.actividad.sesion.Sesion;
import aplicacion.actividad.sesion.SesionMonitorizada;
import aplicacion.excepciones.ExcepcionUsuario;
import aplicacion.excepciones.FechaPosterior;
import aplicacion.sala.Sala;
import aplicacion.usuario.Monitor;
import gui.Window;
import gui.monitor.MisActividadesGrupalesWindow;
import gui.monitor.MonitorWindow;
/**
 * Este fichero muestra todo lo que tiene que ver con la clase ControladorMisActividadesGrupales
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class ControladorMisActividadesGrupales implements ActionListener {

	private MisActividadesGrupalesWindow actividadesGrupales;
	private Window window;
	private Gimnasio gimnasio;
	/**
	 * Constructor ControladorMisActividadesGrupales
	 * @param window la ventana general
	 * @param gimnasio el gimnasio
	 */
	public ControladorMisActividadesGrupales(Window window, Gimnasio gimnasio) {
		this.window = window;
		this.actividadesGrupales = window.getMisActividadesGrupalesWindow();
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
		} else if (e.getActionCommand().equals("Cancelar")) {
			if (this.actividadesGrupales.getTabla().getSelectedRowCount() == 0)
				JOptionPane.showMessageDialog(null, "Asegurese de seleccionar una actividad", null,
						JOptionPane.WARNING_MESSAGE);
			else {

				int filaSeleccionada = this.actividadesGrupales.getTabla().getSelectedRow();

				if (this.actividadesGrupales.getTabla().getValueAt(filaSeleccionada, 0).toString() == "")
					JOptionPane.showMessageDialog(null, "No puede cancelar", null, JOptionPane.WARNING_MESSAGE);
				else {

					String nombre = this.actividadesGrupales.getTabla().getValueAt(filaSeleccionada, 0).toString();

					LocalDate fecha = LocalDate
							.parse(this.actividadesGrupales.getTabla().getValueAt(filaSeleccionada, 3).toString());
							
					LocalTime lt1 = LocalTime
							.parse(this.actividadesGrupales.getTabla().getValueAt(filaSeleccionada, 1).toString());
					LocalDateTime horaInicio = LocalDateTime.of(fecha, lt1);

					LocalTime lt2 = LocalTime
							.parse(this.actividadesGrupales.getTabla().getValueAt(filaSeleccionada, 2).toString());
					LocalDateTime horaFin = LocalDateTime.of(fecha, lt2);

					Sala sala = this.gimnasio.getSalaByName(
							this.actividadesGrupales.getTabla().getValueAt(filaSeleccionada, 4).toString());

					int aforo = Integer
							.valueOf(this.actividadesGrupales.getTabla().getValueAt(filaSeleccionada, 5).toString());

					Sesion sesion = this.gimnasio.getSesionByData(nombre, null, horaInicio, horaFin, fecha, sala,
							aforo);
					

					try {
						if (this.gimnasio.cancelarActividadGrupal((ActividadGrupal) this.gimnasio.getActividades()
								.get(this.actividadesGrupales.getSeleccionada())) == true)
							JOptionPane.showMessageDialog(null, "Has cancelado una actividad grupal", null,
									JOptionPane.INFORMATION_MESSAGE);
						else
							JOptionPane.showMessageDialog(null, "No puedes cancelar una actividad grupal", null,
									JOptionPane.INFORMATION_MESSAGE);
					} catch (HeadlessException e1) {
						e1.printStackTrace();
					}
					
					ArrayList<ActividadGrupal> actividades = new ArrayList<>();
					Monitor monitor = (Monitor) this.gimnasio.getUsuarioRegistrado();

					for (ActividadGrupal act : monitor.getActividadesGrupales()) {
					    actividades.add(act);
					}

					try {
						this.window.getMisActividadesGrupalesWindow().updateActividadesGrupales(actividades);
					} catch (ExcepcionUsuario | FechaPosterior e1) {
						e1.printStackTrace();
					}
				}
			}
		} else
			JOptionPane.showMessageDialog(null, "No has podido cancelar una actividad grupal", null,
					JOptionPane.WARNING_MESSAGE);

	}

}
