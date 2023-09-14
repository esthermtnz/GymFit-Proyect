package gui.controladores.monitor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import aplicacion.Gimnasio;
import gui.Window;
import gui.cliente.NotificacionClienteWindow;
import gui.monitor.NotificacionMonitorWindow;
/**
 * Este fichero muestra todo lo que tiene que ver con la clase ControladorNotificacionMonitor
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class ControladorNotificacionMonitor implements ActionListener {
	
	private NotificacionMonitorWindow notificacion;
	private Window window;
	private Gimnasio gimnasio;
	/**
	 * Constructor ControladorNotificacionMonitor
	 * @param window la ventana general
	 * @param gimnasio el gimnasio
	 */
	public ControladorNotificacionMonitor(Window window, Gimnasio gimnasio)
	{
		this.window = window;
		this.notificacion = window.getNotificacionMonitorWindow();
		this.gimnasio = gimnasio;
	}
	/**
	 * Funcion para detectar todos los movimientos que realice el usuario en la aplicacion
	 * @param e la accion
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Atras"))
		{
			this.window.mostrarPanel("Monitor Window");
		}
		
	}

}
