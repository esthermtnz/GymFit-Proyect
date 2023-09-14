package gui.controladores.administrador;

import aplicacion.*;
import gui.Window;
import gui.administrador.PenalizacionesClienteWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

/**
 * Este fichero muestra todo lo que tiene que ver con la clase ControladorPenalizacionesCliente
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class ControladorPenalizacionesCliente implements ActionListener{
	
	private PenalizacionesClienteWindow penalizaciones;
	private Window window;
	private Gimnasio gimnasio;

	/**
	 * Constructor ControladorPenalizacionesCliente
	 * @param window la ventana general
	 * @param gimnasio el gimnasio
	 */
	public ControladorPenalizacionesCliente(Window window, Gimnasio gimnasio)
	{
		this.window = window;
		this.penalizaciones = window.getPenalizacionesClienteWindow();
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
			if(this.gimnasio.setMaxCancelaciones(this.penalizaciones.getCampoCancelaciones())==false || 					this.gimnasio.setNumDiasPenal(this.penalizaciones.getCampoDias())==false)
			{
				JOptionPane.showMessageDialog(null, "Asegurese que ha rellenado todos los campos", null, JOptionPane.WARNING_MESSAGE);
			}
			else {
				JOptionPane.showMessageDialog(null,"Ha configurado las penalizaciones correctamente", null, JOptionPane.INFORMATION_MESSAGE);
				this.window.mostrarPanel("Administrador Window");
			}
			
		}
		this.penalizaciones.setCampoCancelaciones("");
		this.penalizaciones.setCampoDias("");
	}

}
