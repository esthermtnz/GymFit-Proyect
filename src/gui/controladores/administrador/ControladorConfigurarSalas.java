package gui.controladores.administrador;

import aplicacion.*;
import aplicacion.sala.Sala;
import gui.Window;
import gui.administrador.ConfigurarPreciosWindow;
import gui.administrador.ConfigurarSalasWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.List;

import javax.swing.JOptionPane;

/**
 * Este fichero muestra todo lo que tiene que ver con la clase
 * ControladorConfigurarSalas
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class ControladorConfigurarSalas implements ActionListener {

	private ConfigurarSalasWindow salas;
	private Window window;
	private Gimnasio gimnasio;

	/**
	 * Constructor ControladorConfigurarSalas
	 * 
	 * @param window   la ventana general
	 * @param gimnasio el gimnasio
	 */
	public ControladorConfigurarSalas(Window window, Gimnasio gimnasio) {
		this.window = window;
		this.salas = window.getConfigurarSalasWindow();
		this.gimnasio = gimnasio;

	}

	/**
	 * Funcion para detectar todos los movimientos que realice el usuario en la
	 * aplicacion
	 * 
	 * @param e la accion
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		if (this.salas.getBoxSala().equals("Sala general")) {
			this.window.getConfigurarSalasWindow().updateContenido1();
			if (this.window.getConfigurarSalasWindow().getBoxClimatizacion().equals("No")) {
				this.window.getConfigurarSalasWindow().updateContenidoElim3();
			} else {
				this.window.getConfigurarSalasWindow().updateContenido1();
			}
		} else if (this.salas.getBoxSala().equals("Subsala")) {
			this.window.getConfigurarSalasWindow().updateContenido2();
		}

		if (e.getActionCommand().equals("Cancelar")) {
			this.window.mostrarPanel("Administrador Window");

		} else if (e.getActionCommand().equals("Hecho")) {
			if (this.salas.getBoxSala().equals("Sala general")) {

				if (e.getActionCommand().equals("Si")) {
					if (this.gimnasio.crearSalaSimple(this.salas.getCampoNombre(), this.salas.getCampoDescipcion(),
							this.salas.getCampoAforo(), this.salas.getFechaIni(), this.salas.getFechaFin()) == null)
						JOptionPane.showMessageDialog(null, "Asegurese que ha rellenado todos los campos", null,
								JOptionPane.WARNING_MESSAGE);
					else {
						JOptionPane.showMessageDialog(null, "Has creado una sala", null,
								JOptionPane.INFORMATION_MESSAGE);
						this.window.mostrarPanel("Administrador Window");
					}

				}else
				{
					if (this.gimnasio.crearSalaSimple(this.salas.getCampoNombre(), this.salas.getCampoDescipcion(),
							this.salas.getCampoAforo(), null, null) == null)
						JOptionPane.showMessageDialog(null, "Asegurese que ha rellenado todos los campos", null,
								JOptionPane.WARNING_MESSAGE);
					else {
						JOptionPane.showMessageDialog(null, "Has creado una sala", null,
								JOptionPane.INFORMATION_MESSAGE);
						this.window.mostrarPanel("Administrador Window");
					}
				}

			} else if (this.salas.getBoxSala().equals("Subsala")) {
				if (this.gimnasio.crearSalaCompuesta(this.salas.getCampoNombre(), this.salas.getCampoDescipcion(),
						this.salas.getCampoAforo(),
						this.gimnasio.getSalaByName(this.salas.getBoxSalaCont()).getHorarioClimatizacion().getHoraIni(),
						this.gimnasio.getSalaByName(this.salas.getBoxSalaCont()).getHorarioClimatizacion()
								.getHoraFin()) == null)
					JOptionPane.showMessageDialog(null, "Asegurese que ha rellenado todos los campos", null,
							JOptionPane.WARNING_MESSAGE);
				else {
					JOptionPane.showMessageDialog(null, "Has creado una sala", null, JOptionPane.INFORMATION_MESSAGE);
					this.window.mostrarPanel("Administrador Window");
				}

			}
		}

		this.salas.setCampoNombre("");
		this.salas.setCampoDescipcion("");
		this.salas.setCampoAforo("");
	}

}
