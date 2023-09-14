package gui.controladores.administrador;

import aplicacion.*;
import aplicacion.actividad.Actividad;
import aplicacion.excepciones.ExcepcionUsuario;
import aplicacion.excepciones.FechaPosterior;
import aplicacion.actividad.*;
import gui.Window;
import gui.administrador.ConfigurarSalasWindow;
import gui.administrador.ConsultarPlanesMonitorWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.JComboBox;

/**
 * Este fichero muestra todo lo que tiene que ver con la clase ControladorConsultarPlanesMonitor
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class ControladorConsultarPlanesMonitor implements ActionListener {

	private ConsultarPlanesMonitorWindow planes;
	private Window window;
	private Gimnasio gimnasio;

	/**
	 * Constructor ControladorConsultarPlanesMonitor
	 * @param window la ventana general
	 * @param gimnasio el gimnasio
	 */
	public ControladorConsultarPlanesMonitor(Window window, Gimnasio gimnasio) {
		this.window = window;
		this.planes = window.getConsultarPlanesMonitorWindow();
		this.gimnasio = gimnasio;

	}

	/**
	 * Funcion para detectar todos los movimientos que realice el usuario en la aplicacion
	 * @param e la accion
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
        ArrayList<PlanPersonalizado> listaPlanes = new ArrayList<PlanPersonalizado>(gimnasio.getMonitorByName(this.planes.getBoxMonitor()).getPlanesPersonalizados());
        try {
			planes.updatePlanes(listaPlanes);
		} catch (ExcepcionUsuario | FechaPosterior e1) {
			e1.printStackTrace();
		}
		
	    if (e.getActionCommand().equals("Atras")) {
	        this.window.mostrarPanel("Administrador Window");
	    }
	}

}
