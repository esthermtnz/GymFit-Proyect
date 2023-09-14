package gui.controladores.monitor;

import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;

import gui.Window;
import gui.monitor.AddSesionAGWindow;
import aplicacion.Gimnasio;
import aplicacion.actividad.Actividad;
import aplicacion.actividad.PlanPersonalizado;
import aplicacion.actividad.TipoObjetivo;
import aplicacion.actividad.sesion.Sesion;
import aplicacion.actividad.sesion.SesionMonitorizada;
import aplicacion.excepciones.ExcepcionUsuario;
import aplicacion.excepciones.FechaPosterior;
import aplicacion.sala.Sala;
import aplicacion.usuario.Monitor;

/**
 * Este fichero muestra todo lo que tiene que ver con la clase ControladorAddSesionAG
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class ControladorAddSesionAG implements ActionListener {
	private AddSesionAGWindow sesionAG;
	private Window window;
	private Gimnasio gimnasio;
	/**
	 * Constructor ControladorAddSesionAG
	 * @param window la ventana general
	 * @param gimnasio el gimnasio
	 */
	public ControladorAddSesionAG(Window window, Gimnasio gimnasio) {
		this.window = window;
		this.sesionAG = window.getAddSesionAGWindow();
		this.gimnasio = gimnasio;
	}
	/**
	 * Funcion para detectar todos los movimientos que realice el usuario en la aplicacion
	 * @param e la accion
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Atras")) {
			this.window.mostrarPanel("Mis Planes Personalizados Window");
			
		} else if (e.getActionCommand().equals("Add")) {

			if(this.window.getMisPlanesPersonalizadosWindow().getTabla().getSelectedRowCount() == 0) {
				JOptionPane.showMessageDialog(null, "Asegurese de seleccionar un plan", null,
						JOptionPane.WARNING_MESSAGE);}
			
			int filaSeleccionadaAnterior = this.window.getMisPlanesPersonalizadosWindow().getTabla().getSelectedRow();

			String nombreActividad = this.window.getMisPlanesPersonalizadosWindow().getTabla().getValueAt(filaSeleccionadaAnterior, 0)
					.toString();

			
			Actividad actividad = this.gimnasio.getActividadByData(nombreActividad, (Monitor)this.gimnasio.getUsuarioRegistrado());

			/***************************************/
			
			int filaSeleccionada = this.sesionAG.getTabla().getSelectedRow();

			if (this.sesionAG.getTabla().getValueAt(filaSeleccionada, 0).toString() == "")
				JOptionPane.showMessageDialog(null, "No se puede anadir", null, JOptionPane.WARNING_MESSAGE);
			else {
				String nombre = this.sesionAG.getTabla().getValueAt(filaSeleccionada, 0).toString();

				LocalDate fecha = LocalDate
							.parse(this.sesionAG.getTabla().getValueAt(filaSeleccionada, 3).toString());

				LocalTime lt1 = LocalTime
						.parse(this.sesionAG.getTabla().getValueAt(filaSeleccionada, 1).toString());
				LocalDateTime horaInicio = LocalDateTime.of(fecha, lt1);

				LocalTime lt2 = LocalTime
						.parse(this.sesionAG.getTabla().getValueAt(filaSeleccionada, 2).toString());
				LocalDateTime horaFin = LocalDateTime.of(fecha, lt2);

				Sala sala = this.gimnasio.getSalaByName(
						this.sesionAG.getTabla().getValueAt(filaSeleccionada, 4).toString());

				int aforo = Integer
						.valueOf(this.sesionAG.getTabla().getValueAt(filaSeleccionada, 5).toString());

				Sesion sesion = this.gimnasio.getSesionByData(nombre, null, horaInicio, horaFin, fecha, sala,
						aforo);
				
				
				if (actividad.addSesionMonitorizada((SesionMonitorizada)sesion)) {
					
					ArrayList<PlanPersonalizado> planes = new ArrayList<>();
					
					for (PlanPersonalizado act : ((Monitor)this.gimnasio.getUsuarioRegistrado()).getPlanesPersonalizados()) {
						
						planes.add(act);
					}

					try {
						this.window.getMisPlanesPersonalizadosWindow().updatePlanes(planes);
					} catch (ExcepcionUsuario | FechaPosterior e1) {
						e1.printStackTrace();
					}
					
					JOptionPane.showMessageDialog(null, "Has aniadido una sesion grupal correctamente", null,
							JOptionPane.INFORMATION_MESSAGE);
				}
				else
					JOptionPane.showMessageDialog(null, "No has podido aniadir una sesion grupal", null, JOptionPane.WARNING_MESSAGE);

				this.window.mostrarPanel("Mis Planes Personalizados Window");
				
				}
					
			
		}
	}

}
