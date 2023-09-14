package gui.monitor;

import javax.swing.table.DefaultTableModel;

import aplicacion.Gimnasio;
import aplicacion.actividad.Actividad;
import aplicacion.actividad.ActividadGrupal;
import aplicacion.actividad.PlanPersonalizado;
import aplicacion.actividad.sesion.SesionMonitorizada;
import aplicacion.excepciones.ExcepcionUsuario;
import aplicacion.excepciones.FechaPosterior;
import aplicacion.usuario.Monitor;
import gui.controladores.monitor.ControladorMisActividadesGrupales;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.*;

/**
 * Este fichero muestra todo lo que tiene que ver con la clase MisActividadesGrupalesWindow
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class MisActividadesGrupalesWindow extends JPanel {
	private JButton botonAtras;

	private JPanel panel2;

	private SpringLayout layout;

	private Object[][] filas1 = new Object[][] { {}, {}, {}, {}, {}, {} };
	private String[] titulos1 = new String[] { "Actividad", "Hora Inicio", "Hora Fin", "Fecha", "Sala", "Aforo" };

	private JLabel etiqueta;

	private JTable tabla1;

	private HashSet<ActividadGrupal> actividades;

	private DefaultTableModel modeloDatos;

	private JScrollPane scroll;

	/**
	 * Constructor MisActividadesGrupalesWindow
	 */
	public MisActividadesGrupalesWindow() {
		panel2 = new JPanel();
		layout = new SpringLayout();
		this.setLayout(layout);
		/**************************/
		Font font = new Font("Arial", Font.BOLD, 20);
		/**************************/
		etiqueta = new JLabel("MIS ACTIVIDADES GRUPALES");
		etiqueta.setFont(font);
		etiqueta.setBackground(Color.GRAY);
		/**************************/
		botonAtras = new JButton("Atras");
		/**************************/
		botonAtras.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonAtras.setBackground(Color.LIGHT_GRAY);

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

		layout.putConstraint(SpringLayout.WEST, botonAtras, 5, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, botonAtras, 5, SpringLayout.NORTH, this);

		/**************************/
		this.add(etiqueta);
		this.add(panel2);
		this.add(botonAtras);

		panel2.add(scroll);
	}

	/**
	 * GETTER
	 * @return la tabla
	 */
	public JTable getTabla() {
		return this.tabla1;
	}

	/**
	 * Establece el controlador
	 * @param controladorMisActividadesGrupales el controlador de la ventana
	 */
	public void setControlador(ControladorMisActividadesGrupales controladorMisActividadesGrupales) {
		botonAtras.addActionListener(controladorMisActividadesGrupales);
	}

	/**
	 * GETTER
	 * @return el elemento seleccionado de la tabla
	 */
	public int getSeleccionada() {
		return this.tabla1.getSelectedRow();
	}

	/**
	 * Actualiza la tabla con las actividades grupales
	 * @param actividades las actividades grupales que imparte el monitor
	 * @throws ExcepcionUsuario si el usuario no existe
	 * @throws FechaPosterior si la fecha es posterior
	 */
	public void updateActividadesGrupales(ArrayList<ActividadGrupal> actividades)
			throws ExcepcionUsuario, FechaPosterior {
		
		int i=0, j=0;

		this.actividades = new HashSet<>(actividades);


		while (i < modeloDatos.getRowCount() && modeloDatos.getValueAt(i, 0) != null && !modeloDatos.getValueAt(i, 0).toString().isEmpty()) {
		    modeloDatos.setValueAt("", i, 0);
		    modeloDatos.setValueAt("", i, 1);
		    modeloDatos.setValueAt("", i, 2);
		    modeloDatos.setValueAt("", i, 3);
		    modeloDatos.setValueAt("", i, 4);
		    modeloDatos.setValueAt("", i, 5);
		    i++;
		}
		
		i=0; 
		j=0;

	    for(ActividadGrupal act: this.actividades) {
			modeloDatos.setValueAt(act.getNombre(), i, 0); 
			for (SesionMonitorizada sesion : act.getSesionesMonitorizadas()) {
				modeloDatos.setValueAt(act.getNombre(), j, 0); 
				modeloDatos.setValueAt(sesion.getHoraInicio().toLocalTime(), j, 1);													// j
				modeloDatos.setValueAt(sesion.getHoraFin().toLocalTime(), j, 2); 
				modeloDatos.setValueAt(sesion.getFecha(), j, 3); 
				modeloDatos.setValueAt(sesion.getSala().getNombre(), j, 4);
				modeloDatos.setValueAt(sesion.getReservas().size(), j, 5); 
				++j;
			}
		i=i+j;
	}
		
		
	}
}
