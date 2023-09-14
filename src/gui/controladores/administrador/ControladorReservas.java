package gui.controladores.administrador;

import aplicacion.*;
import gui.Window;
import gui.administrador.PenalizacionesClienteWindow;
import gui.administrador.ReservasWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Este fichero muestra todo lo que tiene que ver con la clase ControladorReservasCancelaciones
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class ControladorReservas implements ActionListener{
	
	private ReservasWindow resevas;
	private Window window;
	private Gimnasio gimnasio;

	/**
	 * Constructor ControladorReservasCancelaciones
	 * @param window la ventana general
	 * @param gimnasio el gimnasio
	 */
	public ControladorReservas(Window window, Gimnasio gimnasio)
	{
		this.window = window;
		this.resevas = window.getReservasWindow();
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
			this.window.mostrarPanel("Administrador Window");
		}
		
	}

}
