package gui.controladores.administrador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import aplicacion.Gimnasio;
import aplicacion.Reserva;
import aplicacion.actividad.Actividad;
import aplicacion.actividad.TipoObjetivo;
import aplicacion.excepciones.ExcepcionUsuario;
import aplicacion.excepciones.FechaPosterior;
import aplicacion.excepciones.MesNoTerminado;
import aplicacion.sala.Sala;
import aplicacion.equipacion.*;
import gui.Window;
import gui.administrador.ConsultarGastosEquipacionWindow;

/**
 * Este fichero muestra todo lo que tiene que ver con la clase ControladorConsultarGastosEquipacion
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class ControladorConsultarGastosEquipacion implements ActionListener {

	private ConsultarGastosEquipacionWindow gastos;
	private Window window;
	private Gimnasio gimnasio;

	/**
	 * Constructor ControladorConsultarGastosEquipacion
	 * @param window la ventana general
	 * @param gimnasio el gimnasio
	 */
	public ControladorConsultarGastosEquipacion(Window window, Gimnasio gimnasio) {
		this.window = window;
		this.gastos = window.getConsultarGastosEquipacionWindow();
		this.gimnasio = gimnasio;

	}

	/**
	 * Funcion para detectar todos los movimientos que realice el usuario en la aplicacion
	 * @param e la accion
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Month month = Month.of(Integer.parseInt(this.window.getConsultarGastosEquipacionWindow().getBoxMeses()));
		int year = Integer.parseInt(this.window.getConsultarGastosEquipacionWindow().getBoxAnyos());

		if (e.getActionCommand().equals("Atras")) {
			this.window.mostrarPanel("Administrador Window");
		} else if (e.getActionCommand().equals("Calcular Gastos")) {

			if (this.gastos.getTabla().getSelectedRowCount() == 0)
				JOptionPane.showMessageDialog(null, "Asegurese de seleccionar un equipacion", null,
						JOptionPane.WARNING_MESSAGE);
			else {

				int filaSeleccionada = this.gastos.getTabla().getSelectedRow();

				if (this.gastos.getTabla().getValueAt(filaSeleccionada, 1).toString() == null
						|| this.gastos.getTabla().getValueAt(filaSeleccionada, 1).toString() == "")
					JOptionPane.showMessageDialog(null, "No puede calcular los gastos", null,
							JOptionPane.WARNING_MESSAGE);
							
				if (this.gastos.getTabla().getValueAt(filaSeleccionada, 0) == null || this.gastos.getTabla().getValueAt(filaSeleccionada, 0).toString() == "")
					JOptionPane.showMessageDialog(null, "No se puede consultar el beneficio. Selecciona una fila con contenido", null, JOptionPane.WARNING_MESSAGE);
			
				else {
					double totalBeneficio = 0.0;
					List<Equipacion> listaEquipaciones = new ArrayList<>();
					int j=0;
					int[] seleccion = this.gastos.getTabla().getSelectedRows();
					for (int i = 0; i < this.gastos.getTabla().getSelectedRowCount(); i++, j++) {

						Integer numUnidades = null;
						Integer id = null;
						String descripcion = this.gastos.getTabla().getValueAt(seleccion[j], 1).toString();

						Double precio = Double
								.valueOf(this.gastos.getTabla().getValueAt(seleccion[j], 5).toString());

						LocalDate fecha = LocalDate
								.parse(this.gastos.getTabla().getValueAt(seleccion[j], 6).toString());

						Equipacion equipacion = this.gimnasio.getEquipacionByData(descripcion, fecha, precio);
						listaEquipaciones.add(equipacion);

						if (equipacion.isMaterial()) {
							numUnidades = Integer
									.valueOf(this.gastos.getTabla().getValueAt(seleccion[j], 7).toString());

						}

						if (equipacion.isMaquina()) {
							id = Integer.valueOf(this.gastos.getTabla().getValueAt(seleccion[j], 4).toString());

						}
					}
					
					totalBeneficio = this.gimnasio.gastoEquipacion(listaEquipaciones, month, year);
					this.window.getConsultarGastosEquipacionWindow().updateCalculado(String.valueOf(totalBeneficio));

				}
			}

		} else if (e.getActionCommand().equals("Actualizar Tabla")) {
			ArrayList<Equipacion> equipacion = null;

			month = Month.of(Integer.parseInt(this.window.getConsultarGastosEquipacionWindow().getBoxMeses()));
			year = Integer.parseInt(this.window.getConsultarGastosEquipacionWindow().getBoxAnyos());

			try {
				equipacion = new ArrayList<>(this.gimnasio.filtradoEquipacionMes(month, year));
				gastos.updateGastosEquipacion(equipacion);
			} catch (MesNoTerminado e1) {
				JOptionPane.showMessageDialog(null, "El mes seleccionado no ha finalizado/empezado", null,
							JOptionPane.WARNING_MESSAGE);
			}

		}
	}

}
