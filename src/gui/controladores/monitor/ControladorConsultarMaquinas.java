package gui.controladores.monitor;

import aplicacion.equipacion.*;
import gui.monitor.ConsultarMaquinasWindow;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import aplicacion.Gimnasio;
import aplicacion.actividad.Actividad;
import aplicacion.actividad.ActividadGrupal;
import aplicacion.actividad.PlanPersonalizado;
import aplicacion.excepciones.AveriaPreviamenteReportada;
import aplicacion.excepciones.ExcepcionUsuario;
import aplicacion.excepciones.FechaPosterior;
import aplicacion.usuario.Monitor;
import gui.Window;

/**
 * Este fichero muestra todo lo que tiene que ver con la clase ControladorConsultarMaquinas
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class ControladorConsultarMaquinas implements ActionListener {

	private ConsultarMaquinasWindow maquinas;
	private Window window;
	private Gimnasio gimnasio;
	/**
	 * Constructor ControladorConsultarMaquinas
	 * @param window la ventana general
	 * @param gimnasio el gimnasio
	 */
	public ControladorConsultarMaquinas(Window window, Gimnasio gimnasio) {
		this.window = window;
		this.maquinas = window.getConsultarMaquinasWindow();
		this.gimnasio = gimnasio;
	}
	/**
	 * Funcion para detectar todos los movimientos que realice el usuario en la aplicacion
	 * @param e la accion
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		ArrayList<Maquina> maquinasPropiedad = new ArrayList<>();
		ArrayList<Maquina> maquinasAlquiladas = new ArrayList<>();

		if (maquinas.getBoxMaquina().equals("Propiedad")) {
			for (Maquina maq : this.gimnasio.getMaquinas()) {
				if (maq.esPropiedad() == true) {
					maquinasPropiedad.add(maq);
				}
			}
			this.maquinas.updateMaquinas(maquinasPropiedad);
		} else if (maquinas.getBoxMaquina().equals("Alquilada")) {

			for (Maquina maq : this.gimnasio.getMaquinas()) {
				if (maq.esAlquilada() == true) {
					maquinasAlquiladas.add(maq);
				}
			}
			this.maquinas.updateMaquinas(maquinasAlquiladas);
		}

		if (e.getActionCommand().equals("Atras")) {
			
			this.window.mostrarPanel("Monitor Window");
		
		} else if (e.getActionCommand().equals("Averiada")) {
			
			int filaSeleccionada = this.maquinas.getTabla().getSelectedRow();

			if (this.maquinas.getTabla().getValueAt(filaSeleccionada, 0).toString() == "")
				JOptionPane.showMessageDialog(null, "No se puede marcar la maquina como averiada", null,
						JOptionPane.WARNING_MESSAGE);
			else {

				Integer id = Integer.parseInt(this.maquinas.getTabla().getValueAt(filaSeleccionada, 4).toString());
				Maquina maquina = null;
				
				maquina = this.gimnasio.getMaquinaById(id);
				
				try {
					if (((Monitor)this.gimnasio.getUsuarioRegistrado()).indicarAveria(maquina)) {
						ArrayList<Maquina> nuevaLista = new ArrayList<>();
						
						if (maquinas.getBoxMaquina().equals("Propiedad")) {
							for (Maquina maq : this.gimnasio.getMaquinas()) {
								if (maq.esPropiedad() == true) {
									nuevaLista.add(maq);
								}
							}
						} else if (maquinas.getBoxMaquina().equals("Alquilada")) {

							for (Maquina maq : this.gimnasio.getMaquinas()) {
								if (maq.esAlquilada() == true) {
									nuevaLista.add(maq);
								}
							}
						}

						this.window.getConsultarMaquinasWindow().updateMaquinas(nuevaLista);
						JOptionPane.showMessageDialog(null, "Has reportado una maquina como averiada", null,
								JOptionPane.INFORMATION_MESSAGE);

					} else
						JOptionPane.showMessageDialog(null, "No has podido reportar la maquina como averiada", null,
								JOptionPane.WARNING_MESSAGE);
				} catch (HeadlessException e1) {
					e1.printStackTrace();
				} catch (AveriaPreviamenteReportada e1) {
					JOptionPane.showMessageDialog(null, "La maquina ya ha sido reportada previamente", null,
								JOptionPane.WARNING_MESSAGE);
				}

				
			}
		}
	}

}
