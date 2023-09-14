package gui.controladores.cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JOptionPane;

import aplicacion.Gimnasio;
import aplicacion.usuario.Monitor;
import es.uam.eps.padsof.payrolls.exceptions.InvalidPeriod;
import es.uam.eps.padsof.payrolls.exceptions.NonExistentFileException;
import es.uam.eps.padsof.payrolls.exceptions.UnsupportedImageTypeException;
import gui.Window;
import gui.cliente.DatosUsuarioWindow;
import gui.monitor.DatosMonitorWindow;
import gui.monitor.MonitorWindow;

/**
 * Este fichero muestra todo lo que tiene que ver con la clase ControladorDatosUsuario
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class ControladorDatosUsuario implements ActionListener{
	private DatosUsuarioWindow datosUsuario;
	private Window window;
	private Gimnasio gimnasio;

	/**
	 * Constructor ControladorDatosUsuario
	 * @param window la ventana general
	 * @param gimnasio el gimnasio
	 */
	public ControladorDatosUsuario(Window window, Gimnasio gimnasio)
	{
		this.window = window;
		this.datosUsuario = window.getDatosUsuarioWindow();
		this.gimnasio = gimnasio;
	}
	
	/**
	 * Funcion para detectar todos los movimientos que realice el usuario en la aplicacion
	 * @param e la accion
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Atras"))
			this.window.mostrarPanel("Cliente Window");
		
	}
}
