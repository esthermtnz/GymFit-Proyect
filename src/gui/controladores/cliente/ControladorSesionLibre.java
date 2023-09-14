package gui.controladores.cliente;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import aplicacion.Gimnasio;
import aplicacion.actividad.sesion.SesionLibre;
import aplicacion.actividad.Actividad;
import aplicacion.actividad.ActividadGrupal;
import aplicacion.actividad.TipoObjetivo;
import aplicacion.actividad.sesion.SesionMonitorizada;
import aplicacion.excepciones.ApuntadoMismaHora;
import aplicacion.excepciones.ApuntarseAntesDe48h;
import aplicacion.excepciones.ExcepcionCancelaciones;
import aplicacion.excepciones.ExcepcionEdad;
import aplicacion.excepciones.ExcepcionPago;
import aplicacion.excepciones.ExcepcionUsuario;
import aplicacion.excepciones.ExcepcionVeterania;
import aplicacion.excepciones.FechaPosterior;
import aplicacion.excepciones.SalaLlena;
import aplicacion.sala.Sala;
import aplicacion.usuario.Cliente;
import gui.Window;
import gui.cliente.ActividadesGrupalesWindow;
import gui.cliente.SesionLibreWindow;

/**
 * Este fichero muestra todo lo que tiene que ver con la clase
 * ControladorSesionLibre
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class ControladorSesionLibre implements ActionListener {
	private SesionLibreWindow sesionesLibres;
	private Window window;
	private Gimnasio gimnasio;

	/**
	 * Constructor ControladorSesionLibre
	 * 
	 * @param window   la ventana general
	 * @param gimnasio el gimnasio
	 */
	public ControladorSesionLibre(Window window, Gimnasio gimnasio) {
		this.window = window;
		this.sesionesLibres = window.getSesionLibreWindow();
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
		if (e.getActionCommand().equals("Atras")) {
			this.window.mostrarPanel("Cliente Window");
		} else if (e.getActionCommand().equals("Apuntate")) {
			if (this.sesionesLibres.getTabla().getSelectedRowCount() == 0)
				JOptionPane.showMessageDialog(null, "Asegurese de seleccionar una sesion", null,
						JOptionPane.WARNING_MESSAGE);
			else {
				int filaSeleccionada = this.sesionesLibres.getTabla().getSelectedRow();

				if (this.sesionesLibres.getTabla().getValueAt(filaSeleccionada, 0).toString() == "")
					JOptionPane.showMessageDialog(null, "No puede apuntar", null, JOptionPane.WARNING_MESSAGE);
				else {

					Cliente cliente = (Cliente) this.gimnasio.getUsuarioRegistrado();

					LocalDate fecha = LocalDate
							.parse(this.sesionesLibres.getTabla().getValueAt(filaSeleccionada, 0).toString());
					LocalTime lt1 = LocalTime
							.parse(this.sesionesLibres.getTabla().getValueAt(filaSeleccionada, 1).toString());
					LocalDateTime horaInicio = LocalDateTime.of(fecha, lt1);

					LocalTime lt2 = LocalTime
							.parse(this.sesionesLibres.getTabla().getValueAt(filaSeleccionada, 2).toString());
					LocalDateTime horaFin = LocalDateTime.of(fecha, lt2);

					Sala sala = this.gimnasio
							.getSalaByName(this.sesionesLibres.getTabla().getValueAt(filaSeleccionada, 3).toString());

					int aforo = Integer
							.valueOf(this.sesionesLibres.getTabla().getValueAt(filaSeleccionada, 4).toString());

					double precio = Double
							.valueOf(this.sesionesLibres.getTabla().getValueAt(filaSeleccionada, 5).toString());

					SesionLibre sesion = this.gimnasio.getSesionLibreByData(fecha, horaInicio, horaFin, sala, aforo,
							precio);

					try {
						try {
							if (cliente.realizarReserva(sesion) == true) {
								ArrayList<SesionLibre> actividades = new ArrayList<>();

								for (SesionLibre act : this.gimnasio.getSesionesLibres()) {
									actividades.add(act);
								}
								try {
									this.window.getSesionLibreWindow().updateSesiones(actividades);
								} catch (ExcepcionUsuario | FechaPosterior e1) {
									e1.printStackTrace();
								}

								JOptionPane.showMessageDialog(null, "Te has apuntado a una sesion libre", null,
										JOptionPane.INFORMATION_MESSAGE);
							} else {
								JOptionPane.showMessageDialog(null, "No se ha podido apuntar", null,
										JOptionPane.WARNING_MESSAGE);
							}
						} catch (ExcepcionEdad e1) {
							JOptionPane.showMessageDialog(null, "No cumples el requisito de EDAD", null,
									JOptionPane.WARNING_MESSAGE);

						} catch (ExcepcionVeterania e2) {
							JOptionPane.showMessageDialog(null, "No cumples el requisito de VETERANIA", null,
									JOptionPane.WARNING_MESSAGE);

						} catch (ExcepcionCancelaciones e3) {
							JOptionPane.showMessageDialog(null, "No cumples el requisito de CANCELACIONES", null,
									JOptionPane.WARNING_MESSAGE);

						} catch (ExcepcionPago e4) {
							JOptionPane.showMessageDialog(null, "No has pagado", null, JOptionPane.WARNING_MESSAGE);
						} catch (ApuntarseAntesDe48h e1) {
							JOptionPane.showMessageDialog(null, "No te puedes apuntar antes de 48 horas de la sesion",
									null, JOptionPane.WARNING_MESSAGE);
						} catch (ApuntadoMismaHora e1) {
							JOptionPane.showMessageDialog(null, "Ya esta apuntado a otra sesion a la misma hora", null,
									JOptionPane.WARNING_MESSAGE);
						}
					} catch (HeadlessException | FechaPosterior | ExcepcionUsuario e1) {
						e1.printStackTrace();
					} catch (SalaLlena e1) {
						JOptionPane.showMessageDialog(null, "La sala esta llena", null, JOptionPane.WARNING_MESSAGE);
					}
				}
			}

		}
	}
}
