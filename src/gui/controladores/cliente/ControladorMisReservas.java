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
import aplicacion.usuario.Cliente;
import aplicacion.Reserva;
import aplicacion.actividad.Actividad;
import aplicacion.actividad.ActividadGrupal;
import aplicacion.actividad.TipoObjetivo;
import aplicacion.actividad.sesion.Sesion;
import aplicacion.excepciones.ExcepcionUsuario;
import aplicacion.excepciones.FechaPosterior;
import aplicacion.excepciones.FechaUnDia;
import aplicacion.sala.Sala;
import gui.Window;
import gui.cliente.MisReservasWindow;
import gui.monitor.MisActividadesGrupalesWindow;
import gui.monitor.MonitorWindow;

/**
 * Este fichero muestra todo lo que tiene que ver con la clase
 * ControladorMisReservas
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class ControladorMisReservas implements ActionListener {
	private MisReservasWindow reservas;
	private Window window;
	private Gimnasio gimnasio;

	/**
	 * Constructor ControladorMisReservas
	 * 
	 * @param window   la ventana general
	 * @param gimnasio el gimnasio
	 */
	public ControladorMisReservas(Window window, Gimnasio gimnasio) {
		this.window = window;
		this.reservas = window.getMisReservasWindow();
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
		} else if (e.getActionCommand().equals("Cancelar")) {
			Cliente cliente = (Cliente) this.gimnasio.getUsuarioRegistrado();

			if (this.reservas.getTabla().getSelectedRowCount() == 0)
				JOptionPane.showMessageDialog(null, "Asegurese de seleccionar una reserva", null,
						JOptionPane.WARNING_MESSAGE);
			else {

				int filaSeleccionada = this.reservas.getTabla().getSelectedRow();
				TipoObjetivo objetivo = null;
				Reserva reserva = null;
				Sesion sesion = null;

				if (this.reservas.getTabla().getValueAt(filaSeleccionada, 0) == null || this.reservas.getTabla().getValueAt(filaSeleccionada, 0).toString() == "")
					JOptionPane.showMessageDialog(null, "No se puede cancelar. Selecciona una fila con contenido", null, JOptionPane.WARNING_MESSAGE);
				else {

					String nombre = this.reservas.getTabla().getValueAt(filaSeleccionada, 0).toString();

					Actividad actividad = this.gimnasio.getActividadByData(nombre,
							this.gimnasio.getMonitorByActividad(nombre));
							
					if(nombre.equals("Sesion Libre"))
					{
						LocalDate fecha = LocalDate
								.parse(this.reservas.getTabla().getValueAt(filaSeleccionada, 4).toString());
	
						LocalTime lt1 = LocalTime
								.parse(this.reservas.getTabla().getValueAt(filaSeleccionada, 2).toString());
						LocalDateTime horaInicio = LocalDateTime.of(fecha, lt1);
	
						LocalTime lt2 = LocalTime
								.parse(this.reservas.getTabla().getValueAt(filaSeleccionada, 3).toString());
						LocalDateTime horaFin = LocalDateTime.of(fecha, lt2);
	
						Sala sala = this.gimnasio
								.getSalaByName(this.reservas.getTabla().getValueAt(filaSeleccionada, 5).toString());
	
						int aforo = Integer.valueOf(this.reservas.getTabla().getValueAt(filaSeleccionada, 6).toString());
						
						double precio = Double.valueOf(this.reservas.getTabla().getValueAt(filaSeleccionada, 7).toString());
						sesion = this.gimnasio.getSesionLibreByData(fecha, horaInicio, horaFin, sala, aforo, precio);
							
						try {
							reserva = ((Cliente) this.gimnasio.getUsuarioRegistrado()).getReservaByData("Sesion Libre",
									null, horaInicio, horaFin, fecha, sala, aforo);
							if (cliente.cancelarReserva(reserva)) {
								JOptionPane.showMessageDialog(null, "Has cancelado una reserva", null,
										JOptionPane.INFORMATION_MESSAGE);
								ArrayList<Reserva> reservas = new ArrayList<>();
								for (Reserva res : ((Cliente) this.gimnasio.getUsuarioRegistrado()).getReservas()) {
									reservas.add(res);
								}
								try {
									this.window.getMisReservasWindow().updateMisReservas(reservas);
								} catch (ExcepcionUsuario | FechaPosterior e1) {
									e1.printStackTrace();
								} 
							} else
								JOptionPane.showMessageDialog(null, "No has podido cancelar una reserva", null,
										JOptionPane.WARNING_MESSAGE);
						} catch (HeadlessException e1) {
							e1.printStackTrace();
						} catch (FechaPosterior e1) {
							e1.printStackTrace();
						} catch (ExcepcionUsuario e1) {
							e1.printStackTrace();
						}catch (FechaUnDia e2)
						{
							JOptionPane.showMessageDialog(null, "No has podido cancelar una reserva el mismo dia de la reserva", null,
								JOptionPane.WARNING_MESSAGE);
						}
							
					} else{
						
					if (actividad.isPlanPersonalizado() == true) {
						objetivo = this.gimnasio.getTipoObjetivoByName(
								this.reservas.getTabla().getValueAt(filaSeleccionada, 1).toString());
					}

					LocalDate fecha = LocalDate
							.parse(this.reservas.getTabla().getValueAt(filaSeleccionada, 4).toString());

					LocalTime lt1 = LocalTime
							.parse(this.reservas.getTabla().getValueAt(filaSeleccionada, 2).toString());
					LocalDateTime horaInicio = LocalDateTime.of(fecha, lt1);

					LocalTime lt2 = LocalTime
							.parse(this.reservas.getTabla().getValueAt(filaSeleccionada, 3).toString());
					LocalDateTime horaFin = LocalDateTime.of(fecha, lt2);

					Sala sala = this.gimnasio
							.getSalaByName(this.reservas.getTabla().getValueAt(filaSeleccionada, 5).toString());

					int aforo = Integer.valueOf(this.reservas.getTabla().getValueAt(filaSeleccionada, 6).toString());
					
					if (actividad.isPlanPersonalizado()==true) {
						 sesion = this.gimnasio.getSesionInPlanByData(nombre, objetivo, horaInicio, horaFin, fecha, sala, aforo);
						
						if(sesion.getActSesion() instanceof ActividadGrupal) {
							reserva = ((Cliente) this.gimnasio.getUsuarioRegistrado()).getReservaByData(sesion.getActSesion().getNombre(),
									null, horaInicio, horaFin, fecha, sala, aforo);
						}else {
							reserva = ((Cliente) this.gimnasio.getUsuarioRegistrado()).getReservaByData(sesion.getActSesion().getNombre(),
									objetivo, horaInicio, horaFin, fecha, sala, aforo);
						}
						 
					}else {
						 reserva = ((Cliente) this.gimnasio.getUsuarioRegistrado()).getReservaByData(nombre,
								objetivo, horaInicio, horaFin, fecha, sala, aforo);
					}
					
					if (this.gimnasio.getPlanBySesion(sesion) == null) {

						try {
							if (cliente.cancelarReserva(reserva)) {
								JOptionPane.showMessageDialog(null, "Has cancelado una reserva", null,
										JOptionPane.INFORMATION_MESSAGE);
								ArrayList<Reserva> reservas = new ArrayList<>();
								for (Reserva res : ((Cliente) this.gimnasio.getUsuarioRegistrado()).getReservas()) {
									reservas.add(res);
								}
								try {
									this.window.getMisReservasWindow().updateMisReservas(reservas);
								} catch (ExcepcionUsuario | FechaPosterior e1) {
									e1.printStackTrace();
								} 
							} else
								JOptionPane.showMessageDialog(null, "No has podido cancelar una reserva", null,
										JOptionPane.WARNING_MESSAGE);
						} catch (HeadlessException e1) {
							e1.printStackTrace();
						} catch (FechaPosterior e1) {
							e1.printStackTrace();
						} catch (ExcepcionUsuario e1) {
							e1.printStackTrace();
						}catch (FechaUnDia e2)
						{
							JOptionPane.showMessageDialog(null, "No has podido cancelar una reserva el mismo dia de la reserva", null,
								JOptionPane.WARNING_MESSAGE);
						}
					}else
					{
						try {
							if (cliente.cancelarPlanPersonalizado(this.gimnasio.getPlanBySesion(sesion))) {
								JOptionPane.showMessageDialog(null, "Has cancelado un plan", null,
										JOptionPane.INFORMATION_MESSAGE);
								ArrayList<Reserva> reservas = new ArrayList<>();
								for (Reserva res : ((Cliente) this.gimnasio.getUsuarioRegistrado()).getReservas()) {
									reservas.add(res);
								}
								try {
									this.window.getMisReservasWindow().updateMisReservas(reservas);
								} catch (ExcepcionUsuario | FechaPosterior e1) {
									e1.printStackTrace();
								}
							} else
								JOptionPane.showMessageDialog(null, "No has podido cancelar el plan", null,
										JOptionPane.WARNING_MESSAGE);
						} catch (HeadlessException e1) {
							e1.printStackTrace();
						} catch (FechaPosterior e1) {
							e1.printStackTrace();
						} catch (ExcepcionUsuario e1) {
							e1.printStackTrace();
						} catch (FechaUnDia e1) {
							JOptionPane.showMessageDialog(null, "No puedes cancelar el mismo dia de la sesion", null,
									JOptionPane.WARNING_MESSAGE);
						}
					}
				}
				}
			}

		}

	}
}
