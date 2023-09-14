package gui.controladores.administrador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import aplicacion.Gimnasio;
import aplicacion.actividad.TipoActividad;
import gui.Window;
import gui.administrador.TipoActividadWindow;
import gui.cliente.ActividadesGrupalesWindow;

/**
 * Este fichero muestra todo lo que tiene que ver con la clase ControladorTipoActividad
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class ControladorTipoActividad implements ActionListener {
	private TipoActividadWindow tipoActividad;
	private Window window;
	private Gimnasio gimnasio;
	
	/**
	 * Constructor ControladorTipoActividad
	 * @param window la ventana general
	 * @param gimnasio el gimnasio
	 */
	public ControladorTipoActividad(Window window, Gimnasio gimnasio)
	{
		this.window = window;
		this.tipoActividad = window.getTipoActividadWindow();
		this.gimnasio = gimnasio;
	}
	
	/**
	 * Funcion para detectar todos los movimientos que realice el usuario en la aplicacion
	 * @param e la accion
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Hecho"))
		{
			if(this.gimnasio.crearTipoActividad(this.tipoActividad.getCampoTipoActividad())!=null)
			{
				JOptionPane.showMessageDialog(null,"Ha creado un tipo de actividad", null, JOptionPane.INFORMATION_MESSAGE);
				this.window.mostrarPanel("Administrador Window");
			}
				
			else
				JOptionPane.showMessageDialog(null,"Asegurese que ha rellenado todos los campos", null, JOptionPane.WARNING_MESSAGE);
			
		}else if(e.getActionCommand().equals("Atras"))
		{
			this.window.mostrarPanel("Administrador Window");
		}
		
		this.tipoActividad.setCampoTipoActividad("");
	}
}
