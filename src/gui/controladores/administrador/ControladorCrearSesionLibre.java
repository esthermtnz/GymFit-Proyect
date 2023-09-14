package gui.controladores.administrador;

import aplicacion.*;
import aplicacion.actividad.sesion.SesionLibre;
import aplicacion.excepciones.FueraHorarioClimatizacion;
import aplicacion.sala.Sala;
import gui.Window;
import gui.administrador.CrearSesionLibreWindow;
import gui.monitor.CrearPlanPersonalizadoWindow;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.List;

import javax.swing.JOptionPane;
/**
 * Este fichero muestra todo lo que tiene que ver con la clase ControladorCrearSesionLibre
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class ControladorCrearSesionLibre implements ActionListener{
	
	private CrearSesionLibreWindow sesionLibre;
	private Window window;
	private Gimnasio gimnasio;

	/**
	 * Constructor ControladorCrearSesionLibre
	 * @param window la ventana general
	 * @param gimnasio el gimnasio
	 */
	public ControladorCrearSesionLibre(Window window, Gimnasio gimnasio)
	{
		this.window = window;
		this.sesionLibre = window.getCrearSesionLibreWindow();
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
		}
		else if(e.getActionCommand().equals("Hecho"))
		{
			
			try {
				if(this.gimnasio.crearSesionLibre(this.sesionLibre.getCampoFecha(), this.sesionLibre.getCampoHoraIni(),  this.sesionLibre.getCampoHoraFin(), this.gimnasio.getSalaSimpleByName(this.sesionLibre.getCampoSala()), this.gimnasio.getPrecioSesionesLibres())!=null)
				{
					JOptionPane.showMessageDialog(null, "Has creado una sesion libre", null, JOptionPane.INFORMATION_MESSAGE);
					this.window.mostrarPanel("Administrador Window");
				}
				else
					JOptionPane.showMessageDialog(null, "No has podido crear una sesion libre", null, JOptionPane.WARNING_MESSAGE);
			} catch (FueraHorarioClimatizacion e1) {
				JOptionPane.showMessageDialog(null, "Asegurese que esta dentro del horario de climatizacion", null, JOptionPane.WARNING_MESSAGE);
			}
		}
		
		this.sesionLibre.setCampoNombre("");
		this.sesionLibre.setCampoDescripcion("");
	}

}
