package gui.monitor;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.JButton;
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
import aplicacion.actividad.Actividad;
import aplicacion.actividad.PlanPersonalizado;
import gui.controladores.monitor.ControladorMisActividadesGrupales;
import gui.controladores.monitor.ControladorMisPlanesPersonalizados;

/**
 * Este fichero muestra todo lo que tiene que ver con la clase MisPlanesPersonalizadosWindow
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class MisPlanesPersonalizadosWindow extends JPanel {

	private JButton botonAtras;
	private JButton botonCrearPlan;
	private JButton botonCancelar;
	private JButton botonCrearSesion;
	private JButton botonAddSesionAG;
	private JButton botonCancelarPlan;

	private JPanel panel2;

	private SpringLayout layout;

	private JLabel etiqueta;

	private JTable tabla1;

	private JScrollPane scroll;

	private HashSet<PlanPersonalizado> actividades;

	private Object[][] filas1 = new Object[][] { {}, {}, {}, {}, {}, {}, {} };
	private String[] titulos1 = new String[] { "Nombre", "Objetivo", "Hora Inicio", "Hora Fin", "Fecha", "Sala",
			"Aforo" };

	private DefaultTableModel modeloDatos;

	/**
	 * Constructor MisPlanesPersonalizadosWindow
	 */
	public MisPlanesPersonalizadosWindow() {
		panel2 = new JPanel();
		layout = new SpringLayout();
		this.setLayout(layout);
		/**************************/
		Font font = new Font("Arial", Font.BOLD, 20);
		/**************************/
		etiqueta = new JLabel("MIS PLANES PERSONALIZADOS");
		etiqueta.setFont(font);
		etiqueta.setBackground(Color.GRAY);
		/**************************/
		modeloDatos = new DefaultTableModel(filas1, titulos1);

		tabla1 = new JTable(modeloDatos);
		tabla1.setPreferredScrollableViewportSize(new Dimension(500, 80));
		/**************************/
		scroll = new JScrollPane(tabla1);
		/**************************/
		botonAtras = new JButton("Atras");
		botonCancelar = new JButton("Cancelar Sesion");
		botonCrearPlan = new JButton("Crear nuevo plan");
		botonCrearSesion = new JButton("Crear nueva sesion PP");
		botonAddSesionAG = new JButton("Add sesion grupal");
		botonCancelarPlan = new JButton("Cancelar Plan");
		/**************************/
		botonAtras.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonAtras.setBackground(Color.LIGHT_GRAY);

		botonCrearPlan.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonCrearPlan.setBackground(Color.LIGHT_GRAY);
		botonCrearPlan.setPreferredSize(new Dimension(200, 25));

		botonCancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonCancelar.setBackground(Color.LIGHT_GRAY);
		
		botonCrearSesion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonCrearSesion.setBackground(Color.LIGHT_GRAY);
		botonCrearSesion.setPreferredSize(new Dimension(200, 25));
		
		botonAddSesionAG.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonAddSesionAG.setBackground(Color.LIGHT_GRAY);
		botonAddSesionAG.setPreferredSize(new Dimension(200, 25));
		
		botonCancelarPlan.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonCancelarPlan.setBackground(Color.LIGHT_GRAY);
		
		/**************************/
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiqueta, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, etiqueta, 5, SpringLayout.NORTH, this);

		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, panel2, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, panel2, 20, SpringLayout.SOUTH, etiqueta);

		layout.putConstraint(SpringLayout.WEST, botonAtras, 5, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, botonAtras, 5, SpringLayout.NORTH, this);

		layout.putConstraint(SpringLayout.EAST, botonCancelar, -5, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, botonCancelar, 10, SpringLayout.SOUTH, panel2);
		
		layout.putConstraint(SpringLayout.WEST, botonCancelarPlan, 5, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, botonCancelarPlan, 10, SpringLayout.SOUTH, panel2);

		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonCrearPlan, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.SOUTH, botonCrearPlan, -10, SpringLayout.SOUTH, this);
		
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonCrearSesion, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.SOUTH, botonCrearSesion, -10, SpringLayout.NORTH, botonCrearPlan);
		
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonAddSesionAG, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.SOUTH, botonAddSesionAG, -10, SpringLayout.NORTH, botonCrearSesion);

		/**************************/
		this.add(etiqueta);
		this.add(panel2);
		this.add(botonAtras);
		this.add(botonCrearPlan);
		this.add(botonCancelar);
		this.add(botonCrearSesion);
		this.add(botonAddSesionAG);
		this.add(botonCancelarPlan);

		panel2.add(scroll);
	}

	/**
	 * GETTER
	 * @return la tabla creada
	 */
	public JTable getTabla() {
		return this.tabla1;
	}

	/**
	 * Establece el controlador
	 * @param controladorMisPlanesPersonalizados el controlador de la ventana
	 */
	public void setControlador(ControladorMisPlanesPersonalizados controladorMisPlanesPersonalizados) {
		botonAtras.addActionListener(controladorMisPlanesPersonalizados);
		botonCrearPlan.addActionListener(controladorMisPlanesPersonalizados);
		botonCancelar.addActionListener(controladorMisPlanesPersonalizados);
		botonCrearSesion.addActionListener(controladorMisPlanesPersonalizados);
		botonAddSesionAG.addActionListener(controladorMisPlanesPersonalizados);
		botonCancelarPlan.addActionListener(controladorMisPlanesPersonalizados);
	}

	/**
	 * Actualiza la tabla con los planes personalizados
	 * @param planes los planes que imparte el monitor
	 * @throws ExcepcionUsuario si el usuario no existe
	 * @throws FechaPosterior si la fecha es posterior
	 */
	public void updatePlanes(ArrayList<PlanPersonalizado> planes) throws ExcepcionUsuario, FechaPosterior {
		Integer i=0, j=0;

		this.actividades = new HashSet<>(planes);

		while (i < modeloDatos.getRowCount() && modeloDatos.getValueAt(i, 0) != null && !modeloDatos.getValueAt(i, 0).toString().isEmpty()) {
		    modeloDatos.setValueAt("", i, 0);
		    modeloDatos.setValueAt("", i, 1);
		    modeloDatos.setValueAt("", i, 2);
		    modeloDatos.setValueAt("", i, 3);
		    modeloDatos.setValueAt("", i, 4);
		    modeloDatos.setValueAt("", i, 5);
		    modeloDatos.setValueAt("", i, 6);
		    i++;
		}
		i=0; 
		j=0;
		for(PlanPersonalizado plan: this.actividades) {
			if(plan.getSesionesMonitorizadas().size()==0){
				modeloDatos.setValueAt(plan.getNombre(), i, 0); 
				modeloDatos.setValueAt(plan.getTipoObjetivo(), i, 1);
				j++;
			}
			for (SesionMonitorizada sesion : plan.getSesionesMonitorizadas()) {
				modeloDatos.setValueAt(plan.getNombre(), j, 0); 
				modeloDatos.setValueAt(plan.getTipoObjetivo(), j, 1);
				modeloDatos.setValueAt(sesion.getHoraInicio().toLocalTime(), j, 2);													// j
				modeloDatos.setValueAt(sesion.getHoraFin().toLocalTime(), j, 3); 
				modeloDatos.setValueAt(sesion.getFecha(), j, 4); 
				modeloDatos.setValueAt(sesion.getSala().getNombre(), j, 5);
				modeloDatos.setValueAt(sesion.getReservas().size(), j, 6); 
				++j;
			}
			i=i+j;
		}
	}

}
