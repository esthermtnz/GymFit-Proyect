package gui.controladores.administrador;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import aplicacion.Gimnasio;
import aplicacion.equipacion.Estado;
import aplicacion.equipacion.Maquina;
import aplicacion.excepciones.AveriaPreviamenteReportada;
import aplicacion.usuario.Monitor;
import gui.Window;
import gui.administrador.CambiarEstadoMaquinasWindow;
import gui.monitor.ConsultarMaquinasWindow;

/**
 * Este fichero muestra todo lo que tiene que ver con la clase
 * ControladorCambiarEstadoMaquinas
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class ControladorCambiarEstadoMaquinas implements ActionListener {

	private CambiarEstadoMaquinasWindow maquinas;
	private Window window;
	private Gimnasio gimnasio;

	/**
	 * Constructor ControladorCambiarEstadoMaquinas
	 * 
	 * @param window   la ventana general
	 * @param gimnasio el gimnasio
	 */
	public ControladorCambiarEstadoMaquinas(Window window, Gimnasio gimnasio) {
		this.window = window;
		this.maquinas = window.getCambiarEstadoMaquinasWindow();
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

			this.window.mostrarPanel("Administrador Window");

		} else if (e.getActionCommand().equals("En Reparacion")) {
			if (this.maquinas.getTabla().getSelectedRowCount() == 0)
				JOptionPane.showMessageDialog(null, "Asegurese de seleccionar una maquina", null,
						JOptionPane.WARNING_MESSAGE);
			else {
				int filaSeleccionada = this.maquinas.getTabla().getSelectedRow();

				if (this.maquinas.getTabla().getValueAt(filaSeleccionada, 0) == null
						|| this.maquinas.getTabla().getValueAt(filaSeleccionada, 0).toString() == "")
					JOptionPane.showMessageDialog(null,
							"No puede ponerlo en reparacion. Seleccione una fila con conetnido", null,
							JOptionPane.WARNING_MESSAGE);
				else {

					Integer id = Integer.parseInt(this.maquinas.getTabla().getValueAt(filaSeleccionada, 4).toString());
					Maquina maquina = null;

					maquina = this.gimnasio.getMaquinaById(id);

					try {
						if (this.gimnasio.cambiarEstadoMaquina(maquina, Estado.EN_REPARACION)) {
							ArrayList<Maquina> nuevaLista = new ArrayList<>();

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

							JOptionPane.showMessageDialog(null, "Has reportado una maquina como en reparacion", null,
									JOptionPane.INFORMATION_MESSAGE);

						} else
							JOptionPane.showMessageDialog(null, "No has podido reportar la maquina como en reparacion",
									null, JOptionPane.WARNING_MESSAGE);
					} catch (HeadlessException e1) {
						e1.printStackTrace();
					}
				}
			}
		}

		else if (e.getActionCommand().equals("Operativa")) {

			if (this.maquinas.getTabla().getSelectedRowCount() == 0)
				JOptionPane.showMessageDialog(null, "Asegurese de seleccionar una maquina", null,
						JOptionPane.WARNING_MESSAGE);
			else {

				int filaSeleccionada = this.maquinas.getTabla().getSelectedRow();

				if (this.maquinas.getTabla().getValueAt(filaSeleccionada, 0) == null
						|| this.maquinas.getTabla().getValueAt(filaSeleccionada, 0).toString() == "")
					JOptionPane.showMessageDialog(null, "No puede ponerlo operativa. Seleccione una fila con conetnido",
							null, JOptionPane.WARNING_MESSAGE);
				else {
					Integer id = Integer.parseInt(this.maquinas.getTabla().getValueAt(filaSeleccionada, 4).toString());
					Maquina maquina = null;
					maquina = this.gimnasio.getMaquinaById(id);
					try {
						if (this.gimnasio.cambiarEstadoMaquina(maquina, Estado.OPERATIVA)) {
							ArrayList<Maquina> nuevaLista = new ArrayList<>();

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
							JOptionPane.showMessageDialog(null, "Has reportado una maquina como operativa", null,
									JOptionPane.INFORMATION_MESSAGE);

						} else
							JOptionPane.showMessageDialog(null, "No has podido reportar la maquina como opertaiva",
									null, JOptionPane.WARNING_MESSAGE);
					} catch (HeadlessException e1) {
						e1.printStackTrace();
					}
				}
			}
		} else if (e.getActionCommand().equals("Retirada")) {
			if (this.maquinas.getTabla().getSelectedRowCount() == 0)
				JOptionPane.showMessageDialog(null, "Asegurese de seleccionar una maquina", null,
						JOptionPane.WARNING_MESSAGE);
			else {
				int filaSeleccionada = this.maquinas.getTabla().getSelectedRow();

				if (this.maquinas.getTabla().getValueAt(filaSeleccionada, 0) == null
						|| this.maquinas.getTabla().getValueAt(filaSeleccionada, 0).toString() == "")
					JOptionPane.showMessageDialog(null, "No puede retirar. Seleccione una fila con conetnido", null,
							JOptionPane.WARNING_MESSAGE);
				else {
					Integer id = Integer.parseInt(this.maquinas.getTabla().getValueAt(filaSeleccionada, 4).toString());
					Maquina maquina = null;

					maquina = this.gimnasio.getMaquinaById(id);

					try {
						if (this.gimnasio.cambiarEstadoMaquina(maquina, Estado.RETIRADA)) {
							ArrayList<Maquina> nuevaLista = new ArrayList<>();

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
							JOptionPane.showMessageDialog(null, "Has reportado una maquina como retirada", null,
									JOptionPane.INFORMATION_MESSAGE);

						} else
							JOptionPane.showMessageDialog(null, "No has podido reportar la maquina como retirada", null,
									JOptionPane.WARNING_MESSAGE);
					} catch (HeadlessException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
	}
}
