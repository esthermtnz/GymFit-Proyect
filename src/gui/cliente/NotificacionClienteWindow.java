package gui.cliente;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.*;

import aplicacion.Gimnasio;
import gui.controladores.ControladorLogin;
import gui.controladores.cliente.ControladorNotificacionCliente;

import javax.swing.table.DefaultTableModel;

/**
 * Este fichero muestra todo lo que tiene que ver con la clase NotificacionClienteWindow
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class NotificacionClienteWindow extends JPanel{
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
	 * Constructor NotificacionClienteWindow
	 */
	public NotificacionClienteWindow()
	{	
		    layout = new SpringLayout();
		    this.setLayout(layout);
			panel2 = new JPanel();
			/****************************************************************************/
	        Font font = new Font("Arial", Font.BOLD, 20);
		    /****************************************************************************/ 		    
		    etiqueta = new JLabel("BUZON DE NOTIFICACIONES");
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
	 * @param controladorNotificacionCliente el controlador de la ventana
	 */
	public void setControlador(ControladorNotificacionCliente controladorNotificacionCliente) {
		botonAtras.addActionListener(controladorNotificacionCliente);
		
	}
	
	/**
	 * Actualiza la tabla con las notificaciones
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

