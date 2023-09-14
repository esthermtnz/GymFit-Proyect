package gui.controladores.cliente;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import aplicacion.Gimnasio;
import aplicacion.actividad.TipoObjetivo;
import aplicacion.actividad.Actividad;
import aplicacion.actividad.ActividadGrupal;
import aplicacion.actividad.PlanPersonalizado;
import aplicacion.actividad.TipoObjetivo;
import aplicacion.actividad.sesion.Sesion;
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

/**
 * Este fichero muestra todo lo que tiene que ver con la clase
 * ControladorActividadesGrupales
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class ControladorActividadesGrupales implements ActionListener {
	private ActividadesGrupalesWindow actividadesGrupales;
	private Window window;
	private Gimnasio gimnasio;

	/**
	 * Constructor ControladorActividadesGrupales
	 * 
	 * @param window   la ventana general
	 * @param gimnasio el gimnasio
	 */
	public ControladorActividadesGrupales(Window window, Gimnasio gimnasio) {
		this.window = window;
		this.actividadesGrupales = window.getActividadesGrupalesWindow();
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
			if (this.actividadesGrupales.getTabla().getSelectedRowCount() == 0)
				JOptionPane.showMessageDialog(null, "Asegurese de seleccionar una sesion", null,
						JOptionPane.WARNING_MESSAGE);
			else {
				int filaSeleccionada = this.actividadesGrupales.getTabla().getSelectedRow();

				if (this.actividadesGrupales.getTabla().getValueAt(filaSeleccionada, 0) == null
						|| this.actividadesGrupales.getTabla().getValueAt(filaSeleccionada, 0).toString() == "")
					JOptionPane.showMessageDialog(null, "No se puede apuntar. Selecciona una fila con contenido", null,
							JOptionPane.WARNING_MESSAGE);
				else {

					Cliente cliente = (Cliente) this.gimnasio.getUsuarioRegistrado();

					String nombre = this.actividadesGrupales.getTabla().getValueAt(filaSeleccionada, 0).toString();
					TipoObjetivo tipo = null;
					if (this.actividadesGrupales.getTabla().getValueAt(filaSeleccionada, 1).toString()
							.equals(TipoObjetivo.GANANCIAMUSCULAR.toString()))
						tipo = TipoObjetivo.GANANCIAMUSCULAR;
					else if (this.actividadesGrupales.getTabla().getValueAt(filaSeleccionada, 1).toString()
							.equals(TipoObjetivo.PERDIDAPESO.toString()))
						tipo = TipoObjetivo.PERDIDAPESO;
					else if(this.actividadesGrupales.getTabla().getValueAt(filaSeleccionada, 1).toString()
							.equals(TipoObjetivo.REHABILITACION.toString()))
						tipo = TipoObjetivo.REHABILITACION;

					LocalDate fecha = LocalDate
							.parse(this.actividadesGrupales.getTabla().getValueAt(filaSeleccionada, 3).toString());
					LocalTime lt1 = LocalTime
							.parse(this.actividadesGrupales.getTabla().getValueAt(filaSeleccionada, 1).toString());
					LocalDateTime horaInicio = LocalDateTime.of(fecha, lt1);

					LocalTime lt2 = LocalTime
							.parse(this.actividadesGrupales.getTabla().getValueAt(filaSeleccionada, 2).toString());
					LocalDateTime horaFin = LocalDateTime.of(fecha, lt2);

					Sala sala = this.gimnasio.getSalaByName(
							this.actividadesGrupales.getTabla().getValueAt(filaSeleccionada, 4).toString());

					int aforo = Integer
							.valueOf(this.actividadesGrupales.getTabla().getValueAt(filaSeleccionada, 5).toString());

					try {
						if (cliente.realizarReserva(this.gimnasio.getSesionByData(nombre, tipo, horaInicio, horaFin,
								fecha, sala, aforo)) == true) {
							ArrayList<ActividadGrupal> actividades = new ArrayList<>();

							for (ActividadGrupal act : this.gimnasio.getActividadesGrupales()) {
								actividades.add(act);
							}
							try {
								this.window.getActividadesGrupalesWindow().updateActividadesGrupales(actividades);
							} catch (ExcepcionUsuario | FechaPosterior e1) {
								e1.printStackTrace();
							}

							JOptionPane.showMessageDialog(null, "Te has apuntado a una actividad grupal", null,
									JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, "No se ha podido apuntar", null,
									JOptionPane.WARNING_MESSAGE);
						}
					} catch (ApuntarseAntesDe48h e1) {
						JOptionPane.showMessageDialog(null, "No te puedes apuntar antes de 48 horas de la sesion", null,
								JOptionPane.WARNING_MESSAGE);
						e1.printStackTrace();
					} catch (ApuntadoMismaHora e1) {
						JOptionPane.showMessageDialog(null, "Ya esta apuntado a otra sesion a la misma hora", null,
								JOptionPane.WARNING_MESSAGE);

					} catch (ExcepcionEdad e1) {
						JOptionPane.showMessageDialog(null, "No cumples el requisito de EDAD", null,
								JOptionPane.WARNING_MESSAGE);

					} catch (ExcepcionVeterania e2) {
						JOptionPane.showMessageDialog(null, "No cumples el requisito de VETERANIA", null,
								JOptionPane.WARNING_MESSAGE);

					} catch (ExcepcionCancelaciones e3) {
						JOptionPane.showMessageDialog(null, "No cumples el requisito de CANCELACIONES", null,
								JOptionPane.WARNING_MESSAGE);
					} catch (HeadlessException | FechaPosterior | ExcepcionUsuario e1) {
						e1.printStackTrace();
					} catch (SalaLlena e2) {
						JOptionPane.showMessageDialog(null,
								"La sala esta llena, tiene que apuntarse a la lista de espera", null,
								JOptionPane.WARNING_MESSAGE);
					} catch (ExcepcionPago e3) {
						JOptionPane.showMessageDialog(null, "No has pagado", null, JOptionPane.WARNING_MESSAGE);
					}

				}
			}

		} else if (e.getActionCommand().equals("Lista Espera")) {

			if (this.actividadesGrupales.getTabla().getSelectedRowCount() == 0)
				JOptionPane.showMessageDialog(null, "Asegurese de seleccionar una actividad", null,
						JOptionPane.WARNING_MESSAGE);
			else {

				int filaSeleccionada = this.actividadesGrupales.getTabla().getSelectedRow();

				if (this.actividadesGrupales.getTabla().getValueAt(filaSeleccionada, 0) == null
						|| this.actividadesGrupales.getTabla().getValueAt(filaSeleccionada, 0).toString() == "")
					JOptionPane.showMessageDialog(null,
							"No se puede apuntar a la lista de espera. Selecciona una fila con contenido", null,
							JOptionPane.WARNING_MESSAGE);
				else {

					Cliente cliente = (Cliente) this.gimnasio.getUsuarioRegistrado();

					String nombre = this.actividadesGrupales.getTabla().getValueAt(filaSeleccionada, 0).toString();
					TipoObjetivo tipo = null;
					if (this.actividadesGrupales.getTabla().getValueAt(filaSeleccionada, 1).toString()
							.equals(TipoObjetivo.GANANCIAMUSCULAR.toString()))
						tipo = TipoObjetivo.GANANCIAMUSCULAR;
					else if (this.actividadesGrupales.getTabla().getValueAt(filaSeleccionada, 1).toString()
							.equals(TipoObjetivo.PERDIDAPESO.toString()))
						tipo = TipoObjetivo.PERDIDAPESO;
					else if(this.actividadesGrupales.getTabla().getValueAt(filaSeleccionada, 1).toString()
							.equals(TipoObjetivo.REHABILITACION.toString()))
						tipo = TipoObjetivo.REHABILITACION;

					LocalDate fecha = LocalDate
							.parse(this.actividadesGrupales.getTabla().getValueAt(filaSeleccionada, 3).toString());
					LocalTime lt1 = LocalTime
							.parse(this.actividadesGrupales.getTabla().getValueAt(filaSeleccionada, 1).toString());
					LocalDateTime horaInicio = LocalDateTime.of(fecha, lt1);

					LocalTime lt2 = LocalTime
							.parse(this.actividadesGrupales.getTabla().getValueAt(filaSeleccionada, 2).toString());
					LocalDateTime horaFin = LocalDateTime.of(fecha, lt2);

					Sala sala = this.gimnasio.getSalaByName(
							this.actividadesGrupales.getTabla().getValueAt(filaSeleccionada, 4).toString());

					int aforo = Integer
							.valueOf(this.actividadesGrupales.getTabla().getValueAt(filaSeleccionada, 5).toString());
										
					Sesion sesion = this.gimnasio.getSesionByData(nombre, tipo, horaInicio, horaFin, fecha, sala, aforo);

					try {
						if (cliente.apuntarListaEspera(sesion)) {

							this.window.getListaEsperaWindow().updateListaEspera(sesion.getListaEspera());

							JOptionPane.showMessageDialog(null, "Te has apuntado a la lista de espera", null,
									JOptionPane.INFORMATION_MESSAGE);

							this.window.mostrarPanel("Lista Espera Window");
						}else
							JOptionPane.showMessageDialog(null, "No te has podido apuntar a la lista de espera", null,
									JOptionPane.WARNING_MESSAGE);
					} catch (NumberFormatException e1) {
						e1.printStackTrace();
					} catch (HeadlessException e1) {
						e1.printStackTrace();
					} catch (ExcepcionPago e1) {
						JOptionPane.showMessageDialog(null, "No has pagado", null, JOptionPane.WARNING_MESSAGE);
					}
				}

			}

		} 
	}

}
