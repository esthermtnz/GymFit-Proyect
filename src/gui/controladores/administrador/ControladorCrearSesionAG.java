package gui.controladores.administrador;

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
import gui.administrador.CrearSesionAGWindow;
import gui.cliente.ClienteWindow;
import aplicacion.*;
import aplicacion.actividad.Actividad;
import aplicacion.actividad.ActividadGrupal;
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
 * Este fichero muestra todo lo que tiene que ver con la clase ControladorCrearSesionAG
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class ControladorCrearSesionAG implements ActionListener {

	private CrearSesionAGWindow crearSesionAG;
	private Window window;
	private Gimnasio gimnasio;

	/**
	 * Constructor ControladorCrearSesionAG
	 * @param window la ventana general
	 * @param gimnasio el gimnasio
	 */
	public ControladorCrearSesionAG(Window window, Gimnasio gimnasio) {
		this.window = window;
		this.crearSesionAG = window.getCrearSesionAGWindow();
		this.gimnasio = gimnasio;
	}

	/**
	 * Funcion para detectar todos los movimientos que realice el usuario en la aplicacion
	 * @param e la accion
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Hecho")) {
			if(this.window.getConsultarActividadesGrupalesWindow().getTabla().getSelectedRowCount() == 0)
				JOptionPane.showMessageDialog(null, "Asegurese de seleccionar una actividad", null,
						JOptionPane.WARNING_MESSAGE);
						
			int filaSeleccionada = this.window.getConsultarActividadesGrupalesWindow().getTabla().getSelectedRow();

			String nombre = this.window.getConsultarActividadesGrupalesWindow().getTabla().getValueAt(filaSeleccionada, 0)
					.toString();
			
			Actividad actividad = this.gimnasio.getActividadByData(nombre, this.gimnasio.getMonitorByName(this.window.getConsultarActividadesGrupalesWindow().getBoxMonitor()));

			
			try {
				if(actividad.crearSesion(this.crearSesionAG.getCampoFecha(), this.crearSesionAG.getCampoHoraIni(), this.crearSesionAG.getCampoHoraFin(), (SalaSimple)this.gimnasio.getSalaByName(this.crearSesionAG.getBoxSala()), this.gimnasio.getMonitorByName(this.window.getConsultarActividadesGrupalesWindow().getBoxMonitor()))!=null)
				{
					ArrayList<ActividadGrupal> actividades = new ArrayList<>();
					
					for (ActividadGrupal act : (this.gimnasio.getMonitorByName(this.window.getConsultarActividadesGrupalesWindow().getBoxMonitor())).getActividadesGrupales()) {
						actividades.add(act);
					}

					try {
						this.window.getConsultarActividadesGrupalesWindow().updateActividades(actividades);
					} catch (ExcepcionUsuario | FechaPosterior e1) {
						e1.printStackTrace();
					}
					
					
					JOptionPane.showMessageDialog(null, "Has creado una sesion correctamente", null, JOptionPane.INFORMATION_MESSAGE);
				}
				else
					JOptionPane.showMessageDialog(null, "No has podido crear una sesion", null, JOptionPane.WARNING_MESSAGE);
			} catch (FueraHorarioClimatizacion e2)
			{
				JOptionPane.showMessageDialog(null, "Asegurese que esta dentro del horario de climatizacion", null, JOptionPane.WARNING_MESSAGE);
			}

			this.window.mostrarPanel("Consultar Actividades Grupales Window");
			
			} else if (e.getActionCommand().equals("Cancelar")) {

				this.window.mostrarPanel("Administrador Window");

			}
		}
	}

