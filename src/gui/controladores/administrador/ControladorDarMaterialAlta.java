package gui.controladores.administrador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JOptionPane;

import aplicacion.Gimnasio;
import gui.Window;
import gui.administrador.DarMaquinaAltaWindow;
import gui.administrador.DarMaterialAltaWindow;

/**
 * Este fichero muestra todo lo que tiene que ver con la clase ControladorDarMaterialAlta
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class ControladorDarMaterialAlta implements ActionListener{
	private DarMaterialAltaWindow altaMaterial;
	private Window window;
	private Gimnasio gimnasio;
	
	/**
	 * Constructor ControladorDarMaterialAlta
	 * @param window la ventana general
	 * @param gimnasio el gimnasio
	 */
	public ControladorDarMaterialAlta(Window window, Gimnasio gimnasio) {
		this.window = window;
		this.gimnasio = gimnasio;
		this.altaMaterial = this.window.getDarMaterialAltaWindow();
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
		else
		{
				
				Double precio = altaMaterial.getCampoPrecio();
				String descripcion = altaMaterial.getCampoDescripcion();
				Integer numUnidades = altaMaterial.getCampoNumUnidades();
				LocalDate fecha = altaMaterial.getCampoFecha();
				
				if(gimnasio.darAltaMaterial(descripcion, numUnidades, fecha, precio) != null)
				{
					JOptionPane.showMessageDialog(null, "Has dado de alta un material", null, JOptionPane.INFORMATION_MESSAGE);
					this.window.mostrarPanel("Administrador Window");
				}
				else
				{
					JOptionPane.showMessageDialog(null, "No has podido dar de alta un material", null, JOptionPane.WARNING_MESSAGE);
				}
				
		}
		this.altaMaterial.setCampoDescripcion("");	
		this.altaMaterial.setCampoNumUnidades("");	
		this.altaMaterial.setCampoPrecio("");
	}

}
