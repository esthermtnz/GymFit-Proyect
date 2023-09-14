package gui.controladores.administrador;

import aplicacion.*;
import aplicacion.excepciones.UsuarioExiste;
import aplicacion.usuario.Usuario;
import gui.Window;
import gui.administrador.CrearSesionLibreWindow;
import gui.administrador.DarAltaMonitorWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JOptionPane;
/**
 * Este fichero muestra todo lo que tiene que ver con la clase ControladorDarAltaMonitor
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class ControladorDarAltaMonitor implements ActionListener{

	private DarAltaMonitorWindow altaMonitor;
	private Window window;
	private Gimnasio gimnasio;

	/**
	 * Constructor ControladorDarAltaMonitor
	 * @param window la ventana general
	 * @param gimnasio el gimnasio
	 */
	public ControladorDarAltaMonitor(Window window, Gimnasio gimnasio)
	{
		this.window = window;
		this.altaMonitor = window.getDarAltaMonitorWindow();
		this.gimnasio = gimnasio;
		
	}
	
	/**
	 * Funcion para detectar todos los movimientos que realice el usuario en la aplicacion
	 * @param e la accion
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Dar de alta"))
		{
				try {
					if(gimnasio.registroMonitor(this.altaMonitor.getCampoUsuario(), this.altaMonitor.getCampoNif(), this.altaMonitor.getCampoContrasenya(), this.altaMonitor.getCampoNombre(), this.altaMonitor.getCampoEmail())!= null)
					{
						JOptionPane.showMessageDialog(null,"Has dado de alta a un monitor correctamente", null, JOptionPane.INFORMATION_MESSAGE);
						this.window.mostrarPanel("Administrador Window");
					}
				} catch (UsuarioExiste e1) {
					System.err.println(e1);
				}
		}else if(e.getActionCommand().equals("Cancelar"))
		{
			this.window.mostrarPanel("Administrador Window");
		}
		
		this.altaMonitor.setCampoNombre("");
		this.altaMonitor.setCampoEmail("");
		this.altaMonitor.setCampoUsuario("");
		this.altaMonitor.setCampoContrasenya("");
		this.altaMonitor.setCampoNif("");
	}

}
