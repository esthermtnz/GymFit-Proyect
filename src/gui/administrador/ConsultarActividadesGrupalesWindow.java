package gui.administrador;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;

import aplicacion.actividad.sesion.Sesion;
import aplicacion.usuario.*;
import aplicacion.actividad.sesion.SesionMonitorizada;
import aplicacion.excepciones.ExcepcionUsuario;
import aplicacion.excepciones.FechaPosterior;
import aplicacion.Gimnasio;
import aplicacion.actividad.*;
import gui.controladores.*;
import gui.controladores.administrador.ControladorConsultarActividadesGrupales;
import gui.controladores.monitor.ControladorMisActividadesGrupales;
/**
 * Este fichero muestra todo lo que tiene que ver con la clase ConsultarActividadesGrupalesWindow
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class ConsultarActividadesGrupalesWindow extends JPanel {

	private JButton botonAtras;
	private JButton botonCancelar;
	private JButton botonCancelarAct;
	private JButton botonCrearSesion;

	private JPanel panel2;

	private SpringLayout layout;

	private JLabel etiqueta;
	private JLabel etiquetaCancelar;

	private JTable tabla1;

	private JComboBox<String> boxMonitor;

	private JScrollPane scroll;

	private HashSet<ActividadGrupal> actividades = new HashSet<ActividadGrupal>();

	private String[] monitores = new String[] {};;

	private Object[][] filas1 = new Object[][] { {}, {}, {}, {}, {}, {} };
	private String[] titulos1 = new String[] { "Nombre", "Hora Inicio", "Hora Fin", "Fecha", "Sala", "Aforo" };

	private DefaultTableModel modeloDatos;

	private DefaultComboBoxModel<String> modeloDatosMonitor;

	/**
	 * Constructor ConsultarActividadesGrupalesWindow
	 */
	public ConsultarActividadesGrupalesWindow() {
		panel2 = new JPanel();
		layout = new SpringLayout();
		this.setLayout(layout);
		/**************************/
		Font font = new Font("Arial", Font.BOLD, 20);
		/**************************/
		etiqueta = new JLabel("ACTIVIDADES GRUPALES MONITOR");
		etiqueta.setFont(font);
		etiqueta.setBackground(Color.GRAY);
		/**************************/
		etiquetaCancelar = new JLabel("Cancelar");
		etiquetaCancelar.setForeground(Color.BLUE.darker());
		etiquetaCancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		/**************************/
		modeloDatos = new DefaultTableModel(filas1, titulos1);

		tabla1 = new JTable(modeloDatos);
		tabla1.setPreferredScrollableViewportSize(new Dimension(500, 80));
		/**************************/
		modeloDatosMonitor = new DefaultComboBoxModel<String>(monitores);
		boxMonitor = new JComboBox<String>(modeloDatosMonitor);
		/**************************/
		scroll = new JScrollPane(tabla1);
		/**************************/
		botonAtras = new JButton("Atras");
		botonCancelar = new JButton("Cancelar Sesion");
		botonCrearSesion = new JButton("Crear nueva sesion");
		botonCancelarAct = new JButton("Cancelar Actividad");
		/**************************/
		botonAtras.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonAtras.setBackground(Color.LIGHT_GRAY);

		botonCancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonCancelar.setBackground(Color.LIGHT_GRAY);

		botonCrearSesion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonCrearSesion.setBackground(Color.LIGHT_GRAY);

		botonCancelarAct.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonCancelarAct.setBackground(Color.LIGHT_GRAY);
		/**************************/
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiqueta, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, etiqueta, 5, SpringLayout.NORTH, this);

		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, panel2, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, panel2, 20, SpringLayout.SOUTH, etiqueta);

		layout.putConstraint(SpringLayout.WEST, botonAtras, 5, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, botonAtras, 5, SpringLayout.NORTH, this);

		layout.putConstraint(SpringLayout.EAST, botonCancelar, -5, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, botonCancelar, 10, SpringLayout.SOUTH, panel2);

		layout.putConstraint(SpringLayout.WEST, botonCancelarAct, 5, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, botonCancelarAct, 10, SpringLayout.SOUTH, panel2);

		layout.putConstraint(SpringLayout.WEST, boxMonitor, 5, SpringLayout.WEST, panel2);
		layout.putConstraint(SpringLayout.NORTH, boxMonitor, 5, SpringLayout.SOUTH, panel2);

		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonCrearSesion, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.SOUTH, botonCrearSesion, -10, SpringLayout.SOUTH, this);
		/**************************/
		this.add(etiqueta);
		this.add(panel2);
		this.add(botonAtras);
		this.add(botonCancelar);
		this.add(botonCrearSesion);
		this.add(boxMonitor);
		this.add(botonCancelarAct);

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
	 * @param controladorConsultarActividadesGrupales el controlador de la ventana
	 */
	public void setControlador(ControladorConsultarActividadesGrupales controladorConsultarActividadesGrupales) {
		botonAtras.addActionListener(controladorConsultarActividadesGrupales);
		botonCancelar.addActionListener(controladorConsultarActividadesGrupales);
		botonCrearSesion.addActionListener(controladorConsultarActividadesGrupales);
		boxMonitor.addActionListener(controladorConsultarActividadesGrupales);
		botonCancelarAct.addActionListener(controladorConsultarActividadesGrupales);
	}

	/**
	 * Actualiza la tabla
	 * @param actividades lista de actividades para actualizar
	 * @throws ExcepcionUsuario si el usuario no existe
	 * @throws FechaPosterior si la fecha es posterior
	 */
	public void updateActividades(ArrayList<ActividadGrupal> actividades) throws ExcepcionUsuario, FechaPosterior {
		int i = 0, j = 0;
		ActividadGrupal act;

		this.actividades = new HashSet<>(actividades);

		while (i < modeloDatos.getRowCount() && modeloDatos.getValueAt(i, 0) != null
				&& !modeloDatos.getValueAt(i, 0).toString().isEmpty()) {
			modeloDatos.setValueAt("", i, 0);
			modeloDatos.setValueAt("", i, 1);
			modeloDatos.setValueAt("", i, 2);
			modeloDatos.setValueAt("", i, 3);
			modeloDatos.setValueAt("", i, 4);
			modeloDatos.setValueAt("", i, 5);
			i++;
		}
		
		
		i = 0;
		j = 0;
		
		for (ActividadGrupal actividad : this.actividades) {
			if(actividad.getSesionesMonitorizadas().size()==0){
				modeloDatos.setValueAt(actividad.getNombre(), i, 0);
		   		j++;
			}
		    for (SesionMonitorizada sesion : actividad.getSesionesMonitorizadas()) {
		        modeloDatos.setValueAt(actividad.getNombre(), j, 0);
		        modeloDatos.setValueAt(sesion.getHoraInicio().toLocalTime(), j, 1);
		        modeloDatos.setValueAt(sesion.getHoraFin().toLocalTime(), j, 2);
		        modeloDatos.setValueAt(sesion.getFecha(), j, 3);
		        modeloDatos.setValueAt(sesion.getSala().getNombre(), j, 4);
		        modeloDatos.setValueAt(sesion.getReservas().size(), j, 5);
		        ++j;
		    }
		    i +=j; 
		}

	}

	/**
	 * Actualiza la caja de los monitores
	 * @param nuevosMonitores los nuevos monitores
	 */
	public void updateMonitores(HashSet<String> nuevosMonitores) {
		this.monitores = nuevosMonitores.toArray(new String[0]);
		modeloDatosMonitor = null;
		modeloDatosMonitor = new DefaultComboBoxModel<String>(monitores);
	
		this.boxMonitor.setModel(modeloDatosMonitor);
	}

	/**
	 * GETTER
	 * @return la caja del monitor
	 */
	public String getBoxMonitor() {
		return this.boxMonitor.getSelectedItem().toString();
	}

}
