package gui.monitor;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.table.DefaultTableModel;

import aplicacion.Gimnasio;
import aplicacion.actividad.Actividad;
import aplicacion.actividad.PlanPersonalizado;
import aplicacion.sala.Sala;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;

import gui.controladores.administrador.ControladorBeneficiosGimnasio;
import gui.controladores.monitor.ControladorNotificacionMonitor;

/**
 * Este fichero muestra todo lo que tiene que ver con la clase NotificacionMonitorWindow
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class NotificacionMonitorWindow extends JPanel{
	private JButton botonAtras;
	
	private SpringLayout layout;
	
	private JPanel panel2;
	
	private JLabel etiqueta;

	private DefaultTableModel modeloDatos;
	
	private JTable tabla1;
	
	private JScrollPane scroll;
	
	private Object[][] filas1 = new Object [] [] {{}};
	private String[] titulos1 = new String []{"Notificacion"};
	
	/**
	 * Constructor NotificacionMonitorWindow
	 */
	public NotificacionMonitorWindow()
	{
		layout = new SpringLayout();
	    this.setLayout(layout);
		panel2 = new JPanel();
		/****************************************************************************/
        Font font = new Font("Arial", Font.BOLD, 20);
	    /****************************************************************************/ 		    
	    etiqueta = new JLabel("BUZÓN DE NOTIFICACIONES");
	    etiqueta.setFont(font);
	    etiqueta.setBackground(Color.GRAY);
		/****************************************************************************/
		modeloDatos = new DefaultTableModel(filas1, titulos1);
		 
		tabla1 = new JTable(modeloDatos);
		tabla1.setPreferredScrollableViewportSize(new Dimension(500, 80));
		/**************************/
		scroll = new JScrollPane(tabla1);
		/****************************************************************************/
		botonAtras = new JButton("Atras");
		botonAtras.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonAtras.setBackground(Color.LIGHT_GRAY);
		/****************************************************************************/
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiqueta, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, etiqueta, 5, SpringLayout.NORTH, this);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, panel2, 5, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, panel2, 5, SpringLayout.SOUTH, etiqueta);

        layout.putConstraint(SpringLayout.WEST, botonAtras, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, botonAtras, 5, SpringLayout.NORTH, this);
        /****************************************************************************/
        this.add(etiqueta);
        this.add(panel2);
        this.add(botonAtras);
		/****************************************************************************/
		panel2.add(scroll);
	}
	
	/**
	 * Establece el controlador
	 * @param controladorNotificacionMonitor el controlador de la ventana
	 */
	public void setControlador(ControladorNotificacionMonitor controladorNotificacionMonitor) {
		botonAtras.addActionListener(controladorNotificacionMonitor);
	}
	
	/**
	 * Actualiza la tabla de las notificaciones
	 * @param notificaciones las notificaciones a añadir
	 */
	public void update(HashSet<String> notificaciones) {
	    int lon = modeloDatos.getRowCount();
	    for (int i = lon - 1; i >= 0; i--) 
	        modeloDatos.removeRow(i);
	    
	    
	    for (String sala : notificaciones) 
	        modeloDatos.addRow(new Object[] { sala });
	    
	    
	    tabla1.setModel(modeloDatos);
	}


}
