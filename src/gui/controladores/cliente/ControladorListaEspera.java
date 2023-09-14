package gui.controladores.cliente;

import aplicacion.*;
import gui.Window;
import gui.administrador.DarAltaMonitorWindow;
import gui.cliente.ListaEsperaWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Este fichero muestra todo lo que tiene que ver con la clase ControladorListaEspera
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class ControladorListaEspera implements ActionListener{
	
	private ListaEsperaWindow listaEspera;
	private Window window;
	private Gimnasio gimnasio;

	/**
	 * Constructor ControladorListaEspera
	 * @param window la ventana general
	 * @param gimnasio el gimnasio
	 */
	public ControladorListaEspera(Window window, Gimnasio gimnasio)
	{
		this.window = window;
		this.listaEspera = window.getListaEsperaWindow();
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
			this.window.mostrarPanel("Cliente Window");
		}
		
	}

}
