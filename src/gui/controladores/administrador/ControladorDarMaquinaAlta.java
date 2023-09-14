package gui.controladores.administrador;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JOptionPane;

import aplicacion.Gimnasio;
import aplicacion.excepciones.ExcepcionUsuario;
import aplicacion.excepciones.FechaPosterior;
import gui.Window;
import gui.administrador.*;

/**
 * Este fichero muestra todo lo que tiene que ver con la clase ControladorDarMaquinaAlta
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class ControladorDarMaquinaAlta implements ActionListener{
	private DarMaquinaAltaWindow altaMaquina;
	private Window window;
	private Gimnasio gimnasio;
	
	
	/**
	 * Constructor ControladorDarMaquinaAlta
	 * @param window la ventana general
	 * @param gimnasio el gimnasio
	 */
	public ControladorDarMaquinaAlta(Window window, Gimnasio gimnasio) {
		this.window = window;
		this.gimnasio = gimnasio;
		this.altaMaquina = this.window.getDarMaquinaAltaWindow();
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
		}else
		{
			if(this.window.getDarMaquinaAltaWindow().getBoxPropietario().equals("Propiedad"))
			{
				
				String tipo = altaMaquina.getCampoTipo();
				String descripcion = altaMaquina.getCampoDescripcion();
				String marca = altaMaquina.getCampoMarca();
				Double precio = altaMaquina.getCampoPrecio();
				LocalDate fecha = altaMaquina.getCampoFecha();
				
				
				try {
					if(gimnasio.darAltaMaquinaPropiedad(tipo, descripcion, marca, precio, fecha) != null)
					{
						JOptionPane.showMessageDialog(null, "Has dado de alta una maquina", null, JOptionPane.INFORMATION_MESSAGE);
						this.window.mostrarPanel("Administrador Window");
					}else
					{
						JOptionPane.showMessageDialog(null, "No has podido dar de alta una maquina", null, JOptionPane.WARNING_MESSAGE);
					}
				} catch (HeadlessException | ExcepcionUsuario | FechaPosterior e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else
			{
				String tipo = altaMaquina.getCampoTipo();
				String descripcion = altaMaquina.getCampoDescripcion();
				String marca = altaMaquina.getCampoMarca();
				Double precio = altaMaquina.getCampoPrecio();
				LocalDate fecha = altaMaquina.getCampoFecha();
				
				try {
					if(gimnasio.darAltaMaquinaAlquilada(tipo, descripcion, marca, precio, fecha) != null)
					{
						JOptionPane.showMessageDialog(null, "Has dado de alta una maquina", null, JOptionPane.INFORMATION_MESSAGE);
						this.window.mostrarPanel("Administrador Window");
					}else
					{
						JOptionPane.showMessageDialog(null, "No has podido dar de alta una maquina", null, JOptionPane.WARNING_MESSAGE);
					}
				} catch (HeadlessException | ExcepcionUsuario | FechaPosterior e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		
		this.altaMaquina.setCampoTipo("");
		this.altaMaquina.setCampoDescripcion("");	
		this.altaMaquina.setCampoMarca("");	
		this.altaMaquina.setCampoPrecio("");	
	}

}
