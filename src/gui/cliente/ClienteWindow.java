package gui.cliente;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import gui.controladores.*;
import gui.controladores.cliente.ControladorCliente;

/**
 * Este fichero muestra todo lo que tiene que ver con la clase ClienteWindow
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class ClienteWindow extends JPanel{
	private JButton botonNotificaciones;
	private JButton botonCerrarSesion;
	private JButton botonDatosUsuario;
	private JButton botonMisActividades;
	private JButton botonMisPlanes;
	private JButton botonReservas;
	private JButton botonSesionesLibres;
	
	private SpringLayout layout;
	
	private JLabel etiqueta;
	
	/**
	 * Constructor ClienteWindow
	 */
	public ClienteWindow()
	{
	    layout = new SpringLayout();
	    this.setLayout(layout);
	    /****************************************************************************/
        Font font = new Font("Arial", Font.BOLD, 20);
	    /****************************************************************************/
	    etiqueta = new JLabel("INTERFAZ DEL CLIENTE");
	    etiqueta.setFont(font);
	    etiqueta.setBackground(Color.GRAY);
		/****************************************************************************/
		botonNotificaciones = new JButton("Notificaciones");
		botonCerrarSesion = new JButton("Cerrar Sesion");
		botonDatosUsuario = new JButton("Datos Usuario");
		botonDatosUsuario.setPreferredSize(new Dimension(200, 25));
		botonMisActividades = new JButton("Actividades Grupales");
		botonMisActividades.setPreferredSize(new Dimension(200, 25));
		botonSesionesLibres = new JButton("Sesiones Libres");
		botonSesionesLibres.setPreferredSize(new Dimension(200, 25));
		botonMisPlanes = new JButton("Planes Personalizados");
		botonMisPlanes.setPreferredSize(new Dimension(200, 25));
		botonReservas = new JButton("Mis Reservas");
		botonReservas.setPreferredSize(new Dimension(200, 25));
		/****************************************************************************/
		
		botonNotificaciones.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonNotificaciones.setBackground(Color.LIGHT_GRAY);
		
		botonCerrarSesion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonCerrarSesion.setBackground(Color.LIGHT_GRAY);
		
		botonDatosUsuario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonDatosUsuario.setBackground(Color.LIGHT_GRAY);
		
		botonMisActividades.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonMisActividades.setBackground(Color.LIGHT_GRAY);
		
		botonMisPlanes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonMisPlanes.setBackground(Color.LIGHT_GRAY);
		
		botonReservas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonReservas.setBackground(Color.LIGHT_GRAY);
		
		botonSesionesLibres.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonSesionesLibres.setBackground(Color.LIGHT_GRAY);
		/****************************************************************************/
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonMisActividades, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, botonMisActividades, 0, SpringLayout.VERTICAL_CENTER, this);
		
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonDatosUsuario, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.SOUTH, botonDatosUsuario, -10, SpringLayout.NORTH, botonMisActividades);
		
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonMisPlanes, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, botonMisPlanes, 10, SpringLayout.SOUTH, botonMisActividades);
		
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonSesionesLibres, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, botonSesionesLibres, 10, SpringLayout.SOUTH, botonMisPlanes);
		
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonReservas, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, botonReservas, 10, SpringLayout.SOUTH, botonSesionesLibres);
		
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiqueta, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.SOUTH, etiqueta, -10, SpringLayout.NORTH, botonDatosUsuario);
        
        layout.putConstraint(SpringLayout.EAST, botonNotificaciones, -5, SpringLayout.EAST, this);
        layout.putConstraint(SpringLayout.NORTH, botonNotificaciones, 5, SpringLayout.NORTH, this);
        
        layout.putConstraint(SpringLayout.EAST, botonCerrarSesion, -10, SpringLayout.EAST, this);
        layout.putConstraint(SpringLayout.SOUTH, botonCerrarSesion, -5, SpringLayout.SOUTH, this);
        /****************************************************************************/
        this.add(etiqueta);
		this.add(botonNotificaciones);
		this.add(botonCerrarSesion);
		this.add(botonDatosUsuario);
		this.add(botonMisActividades);
		this.add(botonMisPlanes);
		this.add(botonReservas);
		this.add(botonSesionesLibres);
		
	}

	/**
	 * Establece el controlador
	 * @param controladorCliente el controlador de la ventana
	 */
	public void setControlador(ControladorCliente controladorCliente) {
		
		botonNotificaciones.addActionListener(controladorCliente);
		botonCerrarSesion.addActionListener(controladorCliente);
		botonDatosUsuario.addActionListener(controladorCliente);
		botonMisActividades.addActionListener(controladorCliente);
		botonMisPlanes.addActionListener(controladorCliente);
		botonReservas.addActionListener(controladorCliente);
		botonSesionesLibres.addActionListener(controladorCliente);
	}	
	
}
	
