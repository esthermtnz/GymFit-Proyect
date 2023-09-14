package gui.cliente;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;

import aplicacion.Gimnasio;
import aplicacion.actividad.Actividad;
import aplicacion.excepciones.ExcepcionUsuario;
import aplicacion.excepciones.FechaPosterior;
import gui.controladores.cliente.ControladorPlanesPersonalizados;
import gui.controladores.monitor.ControladorMisPlanesPersonalizados;
import aplicacion.actividad.PlanPersonalizado;
import aplicacion.actividad.sesion.SesionMonitorizada;

/**
 * Este fichero muestra todo lo que tiene que ver con la clase PlanesPersonalizadosWindow
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class PlanesPersonalizadosWindow extends JPanel {
	private JButton botonAtras;
	private JButton botonCrearPlan;
	private JButton botonApuntar;

	private HashSet<PlanPersonalizado> actividades = new HashSet<PlanPersonalizado>();

	private JPanel panel2;

	private SpringLayout layout;

	private JLabel etiqueta;

	private JLabel etiquetaCancelar;

	private JTable tabla1;

	private Object[][] filas = new Object[][] { {}, {}, {}, {}, {}, {}, {} };
	private String[] titulos = new String[] { "Nombre", "Objetivo", "Hora Inicio", "Hora Fin", "Fecha", "Sala",
			"Aforo" };

	private DefaultTableModel modeloDatos;
	private JScrollPane scroll;

	/**
	 * Constructor PlanesPersonalizadosWindow
	 */
	public PlanesPersonalizadosWindow() {
		panel2 = new JPanel();
		layout = new SpringLayout();
		this.setLayout(layout);
		/**************************/
		Font font = new Font("Arial", Font.BOLD, 20);
		/**************************/
		etiqueta = new JLabel("PLANES PERSONALIZADOS");
		etiqueta.setFont(font);
		etiqueta.setBackground(Color.GRAY);
		/**************************/
		etiquetaCancelar = new JLabel("Cancelar");
		etiquetaCancelar.setForeground(Color.BLUE.darker());
		etiquetaCancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		/**************************/
		modeloDatos = new DefaultTableModel(filas, titulos);

		tabla1 = new JTable(modeloDatos);
		tabla1.setPreferredScrollableViewportSize(new Dimension(500, 80));

		/**************************/
		scroll = new JScrollPane(tabla1);
		/**************************/
		botonAtras = new JButton("Atras");
		botonApuntar = new JButton("Apuntate");
		/**************************/
		botonAtras.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonAtras.setBackground(Color.LIGHT_GRAY);

		botonApuntar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonApuntar.setBackground(Color.LIGHT_GRAY);
		/**************************/
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiqueta, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, etiqueta, 5, SpringLayout.NORTH, this);

		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, panel2, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, panel2, 20, SpringLayout.SOUTH, etiqueta);

		layout.putConstraint(SpringLayout.WEST, botonAtras, 5, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, botonAtras, 5, SpringLayout.NORTH, this);

		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonApuntar, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, botonApuntar, 10, SpringLayout.SOUTH, panel2);

		/**************************/
		this.add(etiqueta);
		this.add(panel2);
		this.add(botonAtras);
		this.add(botonApuntar);

		panel2.add(scroll);
	}

	public void setControlador(ControladorPlanesPersonalizados controladorPlanesPersonalizados) {
		botonAtras.addActionListener(controladorPlanesPersonalizados);
		botonApuntar.addActionListener(controladorPlanesPersonalizados);
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
	 * Actualiza la tabla con todos los planes del gimnasio
	 * @param planes los planes que vana a estar en la tabla
	 * @throws ExcepcionUsuario salta si el usuario no existe
	 * @throws FechaPosterior salta si la fecha es posterior
	 */
	public void updatePlanes(ArrayList<PlanPersonalizado> planes) throws ExcepcionUsuario, FechaPosterior {
		Integer i = 0, j = 0;

		this.actividades = new HashSet<>(planes);

		while (i < modeloDatos.getRowCount() && modeloDatos.getValueAt(i, 0) != null
				&& !modeloDatos.getValueAt(i, 0).toString().isEmpty()) {
			modeloDatos.setValueAt("", i, 0);
			modeloDatos.setValueAt("", i, 1);
			modeloDatos.setValueAt("", i, 2);
			modeloDatos.setValueAt("", i, 3);
			modeloDatos.setValueAt("", i, 4);
			modeloDatos.setValueAt("", i, 5);
			modeloDatos.setValueAt("", i, 6);
			i++;
		}

		i = 0;
		j = 0;
		for (PlanPersonalizado plan : this.actividades) {
			for (SesionMonitorizada sesion : plan.getSesionesMonitorizadas()) {
				modeloDatos.setValueAt(plan.getNombre(), j, 0);
				modeloDatos.setValueAt(plan.getTipoObjetivo(), j, 1);
				modeloDatos.setValueAt(sesion.getHoraInicio().toLocalTime(), j, 2); // j
				modeloDatos.setValueAt(sesion.getHoraFin().toLocalTime(), j, 3);
				modeloDatos.setValueAt(sesion.getFecha(), j, 4);
				modeloDatos.setValueAt(sesion.getSala().getNombre(), j, 5);
				modeloDatos.setValueAt(sesion.getReservas().size(), j, 6);
				++j;
			}
			i = i + j;
		}
	}
}
