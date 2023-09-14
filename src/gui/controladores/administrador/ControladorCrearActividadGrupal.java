package gui.controladores.administrador;

import aplicacion.*;
import aplicacion.actividad.Actividad;
import aplicacion.actividad.PlanPersonalizado;
import aplicacion.actividad.TipoActividad;
import aplicacion.actividad.TipoObjetivo;
import aplicacion.actividad.sesion.Sesion;
import aplicacion.excepciones.ExcepcionUsuario;
import aplicacion.excepciones.FechaPosterior;
import aplicacion.sala.Sala;
import aplicacion.usuario.Monitor;
import gui.Window;
import gui.administrador.CrearActividadGrupalWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * Este fichero muestra todo lo que tiene que ver con la clase ControladorCrearActividadGrupal
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class ControladorCrearActividadGrupal implements ActionListener{
	
	private CrearActividadGrupalWindow actividadGrupal;
	private Window window;
	private Gimnasio gimnasio;

	/**
	 * Constructor ControladorCrearActividadGrupal
	 * @param window la ventana general
	 * @param gimnasio el gimnasio
	 */
	public ControladorCrearActividadGrupal(Window window, Gimnasio gimnasio)
	{
		this.window = window;
		this.actividadGrupal = window.getCrearActividadGrupalWindow();
		this.gimnasio = gimnasio;
		
	}

	/**
	 * Funcion para detectar todos los movimientos que realice el usuario en la aplicacion
	 * @param e la accion
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Cancelar"))
		{
			this.window.mostrarPanel("Administrador Window");
			
		}else if(e.getActionCommand().equals("Hecho"))
		{
			if(this.gimnasio.crearActividadGrupal(this.actividadGrupal.getCampoNombre(), this.actividadGrupal.getCampoDescripcion(), this.actividadGrupal.getTipoRequisito(), this.gimnasio.getMonitorByName(this.actividadGrupal.getCampoMonitor()), this.gimnasio.getTipoActividadByName(this.actividadGrupal.getCampoTipoActividad()))==null)
			{
				JOptionPane.showMessageDialog(null, "Asegurese que ha rellenado todos los campos", null, JOptionPane.WARNING_MESSAGE);
			}
			else
			{				
				JOptionPane.showMessageDialog(null, "Has creado una actividad grupal", null, JOptionPane.INFORMATION_MESSAGE);
				this.window.mostrarPanel("Administrador Window");
			}
				
		}
		this.actividadGrupal.setCampoNombre("");
		this.actividadGrupal.setCampoDescripcion("");
		this.actividadGrupal.setCampoRequisitosMin("");
		this.actividadGrupal.setCampoRequisitosMax("");
	}

}
