package gui.controladores.administrador;

import aplicacion.*;
import gui.Window;
import gui.administrador.ReservasWindow;
import gui.administrador.SueldoMonitoresWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

/**
 * Este fichero muestra todo lo que tiene que ver con la clase ControladorSueldoMonitores
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class ControladorSueldoMonitores implements ActionListener{
	
	private SueldoMonitoresWindow sueldo;
	private Window window;
	private Gimnasio gimnasio;

	/**
	 * Constructor ControladorSueldoMonitores
	 * @param window la ventana general
	 * @param gimnasio el gimnasio
	 */
	public ControladorSueldoMonitores(Window window, Gimnasio gimnasio)
	{
		this.window = window;
		this.sueldo = window.getSueldoMonitoresWindow();
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
			if(this.gimnasio.setSueldo(this.sueldo.getCampoSueldo())==false || this.gimnasio.setRateHour(this.sueldo.getCampoSuplemento())==false)
			{
				JOptionPane.showMessageDialog(null, "Asegurese que ha rellenado todos los campos", null, JOptionPane.WARNING_MESSAGE);
			}
			else {
				JOptionPane.showMessageDialog(null,"Ha configurado los sueldos correctamente", null, JOptionPane.INFORMATION_MESSAGE);
				this.window.mostrarPanel("Administrador Window");
			}
		}
		this.sueldo.setCampoSueldo("");
		this.sueldo.setCampoSuplemento("");
	}

}
