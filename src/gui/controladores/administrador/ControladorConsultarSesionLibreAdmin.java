package gui.controladores.administrador;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import aplicacion.Gimnasio;
import aplicacion.Reserva;
import aplicacion.actividad.sesion.Sesion;
import aplicacion.actividad.sesion.SesionLibre;
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
import gui.administrador.ConsultarSesionLibreAdminWindow;
import gui.cliente.SesionLibreWindow;

/**
 * Este fichero muestra todo lo que tiene que ver con la clase
 * ControladorConsultarSesionLibreAdmin
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class ControladorConsultarSesionLibreAdmin implements ActionListener {
	private ConsultarSesionLibreAdminWindow sesionesLibres;
	private Window window;
	private Gimnasio gimnasio;

	/**
	 * Constructor ControladorSesionLibre
	 * 
	 * @param window   la ventana general
	 * @param gimnasio el gimnasio
	 */
	public ControladorConsultarSesionLibreAdmin(Window window, Gimnasio gimnasio) {
		this.window = window;
		this.sesionesLibres = window.getConsultarSesionLibreAdminWindow();
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
			this.window.mostrarPanel("Administrador Window");
		} else if (e.getActionCommand().equals("Cancelar")) {
			if (this.sesionesLibres.getTabla().getSelectedRowCount() == 0){
				JOptionPane.showMessageDialog(null, "Asegurese de seleccionar una sesion", null,
						JOptionPane.WARNING_MESSAGE);
			}
			else {

				int filaSeleccionada = this.sesionesLibres.getTabla().getSelectedRow();

				if (this.sesionesLibres.getTabla().getValueAt(filaSeleccionada, 0).toString() == "")
					JOptionPane.showMessageDialog(null, "No puede apuntar a la lista de espera", null,
							JOptionPane.WARNING_MESSAGE);
				else {
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

					Sesion sesion = this.gimnasio.getSesionLibreByData(fecha, horaInicio, horaFin, sala, aforo, precio);

					if (this.gimnasio.cancelarSesionLibre(sesion)) {
						JOptionPane.showMessageDialog(null, "Has cancelado una sesion libre", null,
								JOptionPane.INFORMATION_MESSAGE);

						ArrayList<SesionLibre> sesiones = new ArrayList<>();

						for (SesionLibre act : this.gimnasio.getSesionesLibres()) {
							sesiones.add(act);
						}

						try {
							this.window.getConsultarSesionLibreAdminWindow().updateSesiones(sesiones);
						} catch (ExcepcionUsuario | FechaPosterior e1) {
							e1.printStackTrace();
						}

						try {
							for (String cliente : gimnasio.getUsuarios().keySet()) {
								if (gimnasio.getUsuarios().get(cliente) instanceof Cliente) {
									ArrayList<Reserva> reservas = new ArrayList<>();

									for (Reserva res : ((Cliente) gimnasio.getUsuarios().get(cliente)).getReservas()) {
										reservas.add(res);
									}
									this.window.getMisReservasWindow().updateMisReservas(reservas);

								}
							}
						} catch (ExcepcionUsuario | FechaPosterior e1) {
							e1.printStackTrace();

						}

					} else
						JOptionPane.showMessageDialog(null, "No has podido cancelar una sesion libre",
								null, JOptionPane.WARNING_MESSAGE);
				}
			}
		}
	}

}
