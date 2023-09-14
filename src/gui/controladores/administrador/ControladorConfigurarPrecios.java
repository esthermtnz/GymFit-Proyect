package gui.controladores.administrador;

import aplicacion.*;
import gui.Window;
import gui.administrador.ConfigurarPreciosWindow;
import gui.cliente.ClienteWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

/**
 * Este fichero muestra todo lo que tiene que ver con la clase ControladorConfigurarPrecios
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class ControladorConfigurarPrecios implements ActionListener{

	private ConfigurarPreciosWindow precios;
	private Window window;
	private Gimnasio gimnasio;

	/**
	 * Constructor ControladorConfigurarPrecios
	 * @param window la ventana general
	 * @param gimnasio el gimnasio
	 */
	public ControladorConfigurarPrecios(Window window, Gimnasio gimnasio)
	{
		this.window = window;
		this.precios = window.getConfigurarPreciosWindow();
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
			if(this.gimnasio.setPrecioSesionesPersonalizadas(this.precios.getCampoPlanesPersonalizados())==false || this.gimnasio.setPrecioSesionesLibres(this.precios.getCampoSesionesLibres())==false || this.gimnasio.setPrecioTarifaPlana(this.precios.getCampoPrecioTarifa())==false || this.gimnasio.setDescuentoTarifaPlana(this.precios.getCampoDescuentoPlanesP())==false || this.gimnasio.setPorcentajeDevolucion(this.precios.getCampoDescuentoDevoluciones())==false)
			{
				JOptionPane.showMessageDialog(null, "Asegurese que ha rellenado todos los campos", null, JOptionPane.WARNING_MESSAGE);
			}
			else {
				JOptionPane.showMessageDialog(null,"Ha configurado los precios correctamente", null, JOptionPane.INFORMATION_MESSAGE);
				this.window.mostrarPanel("Administrador Window");
			}
			
		}
		this.precios.setCampoPlanesPersonalizados("");
		this.precios.setCampoSesionesLibres("");
		this.precios.setCampoPrecioTarifa("");
		this.precios.setCampoDescuentoPlanesP("");
		this.precios.setCampoDescuentoDevoluciones("");
	}

}
