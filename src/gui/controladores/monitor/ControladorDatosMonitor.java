package gui.controladores.monitor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JOptionPane;

import aplicacion.Gimnasio;
import aplicacion.excepciones.ExcepcionUsuario;
import aplicacion.excepciones.FechaPosterior;
import aplicacion.excepciones.SueldoNoDefinido;
import aplicacion.usuario.Monitor;
import es.uam.eps.padsof.payrolls.exceptions.InvalidPeriod;
import es.uam.eps.padsof.payrolls.exceptions.NonExistentFileException;
import es.uam.eps.padsof.payrolls.exceptions.UnsupportedImageTypeException;
import gui.Window;
import gui.monitor.DatosMonitorWindow;
import gui.monitor.MonitorWindow;
/**
 * Este fichero muestra todo lo que tiene que ver con la clase ControladorDatosMonitor
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class ControladorDatosMonitor implements ActionListener{
	private DatosMonitorWindow datosMonitor;
	private Window window;
	private Gimnasio gimnasio;
	/**
	 * Constructor ControladorDatosMonitor
	 * @param window la ventana general
	 * @param gimnasio el gimnasio
	 */
	public ControladorDatosMonitor(Window window, Gimnasio gimnasio)
	{
		this.window = window;
		this.datosMonitor = window.getDatosMonitorWindow();
		this.gimnasio = gimnasio;
	}
	/**
	 * Funcion para detectar todos los movimientos que realice el usuario en la aplicacion
	 * @param e la accion
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Atras"))
			this.window.mostrarPanel("Monitor Window");
		else if(e.getActionCommand().equals("Descargar Nomina"))
		{
			try {
				((Monitor)this.gimnasio.getUsuarioRegistrado()).descargarNomina(LocalDate.now().getMonth().minus(1));
					JOptionPane.showMessageDialog(null,"Nomina descargada", null, JOptionPane.INFORMATION_MESSAGE);
			}catch(NonExistentFileException e1) {
				e1.printStackTrace();
			}catch(UnsupportedImageTypeException e2){
				e2.printStackTrace();
			}catch(InvalidPeriod e3) {
				e3.printStackTrace();
			}catch (ExcepcionUsuario e1) {
				e1.printStackTrace();
			}catch (FechaPosterior e1) {
				e1.printStackTrace();
			}catch (SueldoNoDefinido sne) {
				JOptionPane.showMessageDialog(null,"El administrador debe definir el sueldo preeviamente", null, JOptionPane.ERROR_MESSAGE);
				sne.printStackTrace();
			}
			
		}
		
	}
}
