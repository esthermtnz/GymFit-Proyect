package gui.controladores.monitor;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import aplicacion.Gimnasio;
import gui.Window;
import gui.administrador.CrearActividadGrupalWindow;
import gui.cliente.ClienteWindow;
import gui.monitor.CrearSesionPPWindow;
import aplicacion.*;
import aplicacion.actividad.Actividad;
import aplicacion.actividad.PlanPersonalizado;
import aplicacion.actividad.TipoActividad;
import aplicacion.actividad.TipoObjetivo;
import aplicacion.actividad.sesion.Sesion;
import aplicacion.excepciones.ExcepcionUsuario;
import aplicacion.excepciones.FechaPosterior;
import aplicacion.excepciones.FueraHorarioClimatizacion;
import aplicacion.sala.Sala;
import aplicacion.sala.SalaSimple;
import aplicacion.usuario.Monitor;
/**
 * Este fichero muestra todo lo que tiene que ver con la clase ControladorCrearSesionPP
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class ControladorCrearSesionPP implements ActionListener {

	private CrearSesionPPWindow crearSesionPP;
	private Window window;
	private Gimnasio gimnasio;
	/**
	 * Constructor ControladorCrearSesionPP
	 * @param window la ventana general
	 * @param gimnasio el gimnasio
	 */
	public ControladorCrearSesionPP(Window window, Gimnasio gimnasio) {
		this.window = window;
		this.crearSesionPP = window.getCrearSesionPPWindow();
		this.gimnasio = gimnasio;
	}
	/**
	 * Funcion para detectar todos los movimientos que realice el usuario en la aplicacion
	 * @param e la accion
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Hecho")) {
			if(this.window.getMisPlanesPersonalizadosWindow().getTabla().getSelectedRowCount() == 0)
				JOptionPane.showMessageDialog(null, "Asegurese de seleccionar un plan", null,
						JOptionPane.WARNING_MESSAGE);
						
			int filaSeleccionada = this.window.getMisPlanesPersonalizadosWindow().getTabla().getSelectedRow();

			String nombre = this.window.getMisPlanesPersonalizadosWindow().getTabla().getValueAt(filaSeleccionada, 0)
					.toString();

			TipoObjetivo objetivo = this.gimnasio.getTipoObjetivoByName(this.window.getMisPlanesPersonalizadosWindow()
					.getTabla().getValueAt(filaSeleccionada, 1).toString());
			
			Actividad actividad = this.gimnasio.getActividadByData(nombre, (Monitor)this.gimnasio.getUsuarioRegistrado());

	
			try {
				if(actividad.crearSesion(this.crearSesionPP.getCampoFecha(), this.crearSesionPP.getCampoHoraIni(), this.crearSesionPP.getCampoHoraFin(), (SalaSimple)this.gimnasio.getSalaByName(this.crearSesionPP.getBoxSala()), (Monitor)this.gimnasio.getUsuarioRegistrado())!=null)
				{
					ArrayList<PlanPersonalizado> planes = new ArrayList<>();
					
					for (PlanPersonalizado act : ((Monitor)this.gimnasio.getUsuarioRegistrado()).getPlanesPersonalizados()) {
						
						planes.add(act);
					}

					try {
						this.window.getMisPlanesPersonalizadosWindow().updatePlanes(planes);
					} catch (ExcepcionUsuario | FechaPosterior e1) {
						e1.printStackTrace();
					}
					
					
					JOptionPane.showMessageDialog(null, "Has creado una sesion correctamente", null, JOptionPane.INFORMATION_MESSAGE);
				}
				else
					JOptionPane.showMessageDialog(null, "No has podido crear una sesion", null, JOptionPane.WARNING_MESSAGE);
			} catch (FueraHorarioClimatizacion  e2)
			{
				JOptionPane.showMessageDialog(null, "Asegurese que esta dentro del horario de climatizacion", null, JOptionPane.WARNING_MESSAGE);
			}

			this.window.mostrarPanel("Mis Planes Personalizados Window");
			
			} else if (e.getActionCommand().equals("Cancelar")) {

				this.window.mostrarPanel("Mis Planes Personalizados Window");

			}
		}
	}

