package gui.cliente;

import javax.swing.table.DefaultTableModel;

import aplicacion.Gimnasio;
import aplicacion.Reserva;
import aplicacion.actividad.Actividad;
import aplicacion.actividad.ActividadGrupal;
import aplicacion.actividad.PlanPersonalizado;
import aplicacion.actividad.sesion.SesionMonitorizada;
import aplicacion.excepciones.ExcepcionUsuario;
import aplicacion.excepciones.FechaPosterior;
import gui.controladores.cliente.ControladorMisReservas;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.*;

/**
 * Este fichero muestra todo lo que tiene que ver con la clase MisReservasWindow
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class MisReservasWindow extends JPanel {
	private JButton botonAtras;
	private JButton botonCancelar;

	private JPanel panel2;

	private SpringLayout layout;

	private JLabel etiqueta;

	private JTable tabla1;

	private Object[][] filas1 = new Object[][] { {}, {}, {}, {}, {}, {}, {}, {} };
	private String[] titulos1 = new String[] { "Nombre", "Tipo Objetivo", "Hora Inicio", "Hora Fin", "Fecha", "Sala",
			"Aforo", "Precio" };

	private JScrollPane scroll;

	private DefaultTableModel modeloDatos;

	private HashSet<Reserva> reservas;

	/**
	 * Constructor MisReservasWindow
	 */
	public MisReservasWindow() {
		panel2 = new JPanel();
		layout = new SpringLayout();
		this.setLayout(layout);
		/**************************/
		Font font = new Font("Arial", Font.BOLD, 20);
		/**************************/
		etiqueta = new JLabel("MIS RESERVAS");
		etiqueta.setFont(font);
		etiqueta.setBackground(Color.GRAY);
		/**************************/
		botonAtras = new JButton("Atras");
		botonCancelar = new JButton("Cancelar");
		/**************************/
		botonAtras.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonAtras.setBackground(Color.LIGHT_GRAY);

		botonCancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonCancelar.setBackground(Color.LIGHT_GRAY);
		/**************************/
		modeloDatos = new DefaultTableModel(filas1, titulos1);

		tabla1 = new JTable(modeloDatos);
		tabla1.setPreferredScrollableViewportSize(new Dimension(500, 80));
		/**************************/
		scroll = new JScrollPane(tabla1);
		/**************************/
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiqueta, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, etiqueta, 5, SpringLayout.NORTH, this);

		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, panel2, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, panel2, 20, SpringLayout.SOUTH, etiqueta);

		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonCancelar, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, botonCancelar, 10, SpringLayout.SOUTH, panel2);

		layout.putConstraint(SpringLayout.WEST, botonAtras, 5, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, botonAtras, 5, SpringLayout.NORTH, this);

		/**************************/
		this.add(etiqueta);
		this.add(panel2);
		this.add(botonAtras);
		this.add(botonCancelar);

		panel2.add(scroll);
	}

	/**
	 * Establece el controlador
	 * @param controladorMisReservas el controlador de la ventana
	 */
	public void setControlador(ControladorMisReservas controladorMisReservas) {
		botonAtras.addActionListener(controladorMisReservas);
		botonCancelar.addActionListener(controladorMisReservas);
	}

	/**
	 * GETTER
	 * @return el elemento seleccionado de la tabla
	 */
	public int getSeleccionada() {
		return this.tabla1.getSelectedRow();
	}

	/**
	 * GETTER
	 * @return la tabla
	 */
	public JTable getTabla() {
		return this.tabla1;
	}

	/**
	 * Actualiza la tabla con las reservas del cliente
	 * @param reservas las reservas del cliente
	 * @throws ExcepcionUsuario salta si el usuario no existe
	 * @throws FechaPosterior salta si la fecha es posterior
	 */
	public void updateMisReservas(ArrayList<Reserva> reservas) throws ExcepcionUsuario, FechaPosterior {
		int lon, i = 0;
		Reserva res;

		this.reservas = new HashSet<>(reservas);

		lon = reservas.size();

		while (i < modeloDatos.getRowCount() && modeloDatos.getValueAt(i, 0) != null
				&& !modeloDatos.getValueAt(i, 0).toString().isEmpty()) {
			modeloDatos.setValueAt("", i, 0);
			modeloDatos.setValueAt("", i, 1);
			modeloDatos.setValueAt("", i, 2);
			modeloDatos.setValueAt("", i, 3);
			modeloDatos.setValueAt("", i, 4);
			modeloDatos.setValueAt("", i, 5);
			modeloDatos.setValueAt("", i, 6);
			modeloDatos.setValueAt("", i, 7);
			i++;
		}

		for (i = 0; i < lon; i++) {
			res = reservas.get(i);
			if (res.getSesion() instanceof SesionMonitorizada) {
				if(Gimnasio.getGimnasio().getPlanBySesion(res.getSesion())==null){
					modeloDatos.setValueAt(res.getSesion().getActSesion().getNombre(), i, 0); // ACTIVIDAD
				}else {
					modeloDatos.setValueAt(Gimnasio.getGimnasio().getPlanBySesion(res.getSesion()).getNombre(), i, 0); // ACTIVIDAD
					modeloDatos.setValueAt(Gimnasio.getGimnasio().getPlanBySesion(res.getSesion()).getTipoObjetivo(), i,
							1);
				}
				
				modeloDatos.setValueAt(res.getSesion().getHoraInicio().toLocalTime(), i, 2);// HORA INI
				modeloDatos.setValueAt(res.getSesion().getHoraFin().toLocalTime(), i, 3);// HORA FIN
				modeloDatos.setValueAt(res.getSesion().getFecha(), i, 4);// FECHA
				modeloDatos.setValueAt(res.getSesion().getSala().getNombre(), i, 5);// SALA
				modeloDatos.setValueAt(res.getSesion().getReservas().size(), i, 6);// AFORO
				modeloDatos.setValueAt(res.getSesion().getPrecio(), i, 7);// PRECIO

			} else {
				modeloDatos.setValueAt("Sesion Libre", i, 0);
				
				modeloDatos.setValueAt(res.getSesion().getHoraInicio().toLocalTime(), i, 2);// HORA INI
				modeloDatos.setValueAt(res.getSesion().getHoraFin().toLocalTime(), i, 3);// HORA FIN
				modeloDatos.setValueAt(res.getSesion().getFecha(), i, 4);// FECHA
				modeloDatos.setValueAt(res.getSesion().getSala().getNombre(), i, 5);// SALA
				modeloDatos.setValueAt(res.getSesion().getReservas().size(), i, 6);// AFORO
				modeloDatos.setValueAt(res.getSesion().getPrecio(), i, 7);// PRECIO
			}
		}

	}
}
