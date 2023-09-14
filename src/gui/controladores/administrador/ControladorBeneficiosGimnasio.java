package gui.controladores.administrador;

import aplicacion.*;
import aplicacion.actividad.Actividad;
import aplicacion.actividad.TipoObjetivo;
import aplicacion.excepciones.ExcepcionUsuario;
import aplicacion.excepciones.FechaPosterior;
import aplicacion.excepciones.MesNoTerminado;
import aplicacion.sala.Sala;
import aplicacion.actividad.sesion.*;
import aplicacion.usuario.Cliente;
import aplicacion.usuario.Monitor;
import gui.Window;
import gui.administrador.AdministradorWindow;
import gui.administrador.BeneficiosGimnasioWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

/**
 * Este fichero muestra todo lo que tiene que ver con la clase ControladorBeneficiosGimnasio
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class ControladorBeneficiosGimnasio implements ActionListener {

	private BeneficiosGimnasioWindow beneficios;
	private Window window;
	private Gimnasio gimnasio;

	/**
	 * Constructor ControladorBeneficiosGimnasio
	 * @param window la ventana general
	 * @param gimnasio el gimnasio
	 */
	public ControladorBeneficiosGimnasio(Window window, Gimnasio gimnasio) {
		this.window = window;
		this.beneficios = window.getBeneficiosGimnasioWindow();
		this.gimnasio = gimnasio;

	}

	/**
	 * Funcion para detectar todos los movimientos que realice el usuario en la aplicacion
	 * @param e la accion
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Month month = Month.of(Integer.parseInt(this.window.getBeneficiosGimnasioWindow().getBoxMeses()));
		int year = Integer.parseInt(this.window.getBeneficiosGimnasioWindow().getBoxAnyos());

		if (e.getActionCommand().equals("Atras")) {
			this.window.mostrarPanel("Administrador Window");
		} else if (e.getActionCommand().equals("Calcular Beneficio Actividad")) {

			if (this.beneficios.getTabla().getSelectedRowCount() == 0)
				JOptionPane.showMessageDialog(null, "Asegurese de seleccionar una reserva", null,
						JOptionPane.WARNING_MESSAGE);
			else {
				
				int filaSeleccionada = this.beneficios.getTabla().getSelectedRow();
				
				if (this.beneficios.getTabla().getValueAt(filaSeleccionada, 0) == null || this.beneficios.getTabla().getValueAt(filaSeleccionada, 0).toString() == "")
					JOptionPane.showMessageDialog(null, "No se puede consultar el beneficio. Selecciona una fila con contenido", null, JOptionPane.WARNING_MESSAGE);
				else {
					double totalBeneficio = 0.0;
					List<Sesion> sesionesSeleccionadas = new ArrayList<>();
					int[] seleccion = this.beneficios.getTabla().getSelectedRows();
					int j=0;
					for (int i = 0; i < this.beneficios.getTabla().getSelectedRowCount(); i++, j++) {
						
						TipoObjetivo objetivo = null;
						
						String nombre = this.beneficios.getTabla().getValueAt(seleccion[j], 0).toString();
	
						Actividad actividad = this.gimnasio.getActividadByData(nombre,
								this.gimnasio.getMonitorByActividad(nombre));
	
						if (actividad.isPlanPersonalizado() == true) {
							objetivo = this.gimnasio.getTipoObjetivoByName(
									this.beneficios.getTabla().getValueAt(seleccion[j], 1).toString());
							
						}
						LocalDate fecha = LocalDate
								.parse(this.beneficios.getTabla().getValueAt(seleccion[j], 4).toString());
						LocalTime lt1 = LocalTime
								.parse(this.beneficios.getTabla().getValueAt(seleccion[j], 2).toString());
						LocalDateTime horaInicio = LocalDateTime.of(fecha, lt1);
						LocalTime lt2 = LocalTime
								.parse(this.beneficios.getTabla().getValueAt(seleccion[j], 3).toString());
						LocalDateTime horaFin = LocalDateTime.of(fecha, lt2);
						Sala sala = this.gimnasio
								.getSalaByName(this.beneficios.getTabla().getValueAt(seleccion[j], 5).toString());
						int aforo = Integer
								.valueOf(this.beneficios.getTabla().getValueAt(seleccion[j], 6).toString());
	
						Reserva reserva = null;
						try {
							reserva = Gimnasio.getGimnasio().getReservaByData(nombre, objetivo, horaInicio, horaFin,
									fecha, sala, aforo);
							sesionesSeleccionadas.add(reserva.getSesion());
							
						} catch (ExcepcionUsuario | FechaPosterior e1) {
							e1.printStackTrace();
						}
					}
					totalBeneficio = this.gimnasio.beneficioGimnasio(sesionesSeleccionadas);		
					this.window.getBeneficiosGimnasioWindow().updateCalculado(String.valueOf(totalBeneficio));

				}
			}

		} else if (e.getActionCommand().equals("Actualizar Tabla")) {
			ArrayList<Reserva> reservas = null;

			month = Month.of(Integer.parseInt(this.window.getBeneficiosGimnasioWindow().getBoxMeses()));
			year = Integer.parseInt(this.window.getBeneficiosGimnasioWindow().getBoxAnyos());

			try {
				reservas = new ArrayList<>(this.gimnasio.filtradoReservasMes(month, year));
				beneficios.updateBeneficios(reservas);
			} catch (MesNoTerminado e1) {
				JOptionPane.showMessageDialog(null, "El mes seleccionado no ha finalizado/empezado", null,
							JOptionPane.WARNING_MESSAGE);
			}

		}
	}
}
