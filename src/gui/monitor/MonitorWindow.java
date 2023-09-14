package gui.monitor;

import java.awt.*;

import javax.swing.*;

import gui.controladores.ControladorLogin;
import gui.controladores.monitor.ControladorMonitor;

/**
 * Este fichero muestra todo lo que tiene que ver con la clase MonitorWindow
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class MonitorWindow extends JPanel{
	private JButton botonNotificaciones;
	private JButton botonCerrarSesion;
	private JButton botonDatosMonitor;
	private JButton botonMisActividades;
	private JButton botonMisPlanes;
	private JButton botonEquipacion;
	
	
	private SpringLayout layout;
	
	private JLabel etiqueta;
	
	/**
	 * Constructor MonitorWindow
	 */
	public MonitorWindow()
	{
	    layout = new SpringLayout();
	    this.setLayout(layout);
	    /****************************************************************************/
        Font font = new Font("Arial", Font.BOLD, 20);
	    /****************************************************************************/
	    etiqueta = new JLabel("INTERFAZ DEL MONITOR");
	    etiqueta.setFont(font);
	    etiqueta.setBackground(Color.GRAY);
		/****************************************************************************/
		botonNotificaciones = new JButton("Notificaciones");
		botonCerrarSesion = new JButton("Cerrar Sesion");
		botonDatosMonitor = new JButton("Datos Monitor");
		botonDatosMonitor.setPreferredSize(new Dimension(200, 25));
		botonMisActividades = new JButton("Mis Actividades Grupales");
		botonMisActividades.setPreferredSize(new Dimension(200, 25));
		botonMisPlanes = new JButton("Mis Planes Personalizados");
		botonMisPlanes.setPreferredSize(new Dimension(200, 25));
		botonEquipacion = new JButton("Consultar Maquinas");
		botonEquipacion.setPreferredSize(new Dimension(200, 25));
		/****************************************************************************/
		
		botonNotificaciones.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonNotificaciones.setBackground(Color.LIGHT_GRAY);
		
		botonCerrarSesion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonCerrarSesion.setBackground(Color.LIGHT_GRAY);
		
		botonDatosMonitor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonDatosMonitor.setBackground(Color.LIGHT_GRAY);
		
		botonMisActividades.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonMisActividades.setBackground(Color.LIGHT_GRAY);
		
		botonMisPlanes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonMisPlanes.setBackground(Color.LIGHT_GRAY);
		
		botonEquipacion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonEquipacion.setBackground(Color.LIGHT_GRAY);
		/****************************************************************************/
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonMisActividades, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, botonMisActividades, 0, SpringLayout.VERTICAL_CENTER, this);
		
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonDatosMonitor, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.SOUTH, botonDatosMonitor, -10, SpringLayout.NORTH, botonMisActividades);
		
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonMisPlanes, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, botonMisPlanes, 10, SpringLayout.SOUTH, botonMisActividades);
		
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonEquipacion, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, botonEquipacion, 10, SpringLayout.SOUTH, botonMisPlanes);
		
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiqueta, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.SOUTH, etiqueta, -10, SpringLayout.NORTH, botonDatosMonitor);
        
        layout.putConstraint(SpringLayout.EAST, botonNotificaciones, -5, SpringLayout.EAST, this);
        layout.putConstraint(SpringLayout.NORTH, botonNotificaciones, 5, SpringLayout.NORTH, this);
        
        layout.putConstraint(SpringLayout.EAST, botonCerrarSesion, -10, SpringLayout.EAST, this);
        layout.putConstraint(SpringLayout.SOUTH, botonCerrarSesion, -5, SpringLayout.SOUTH, this);
        /****************************************************************************/
        this.add(etiqueta);
		this.add(botonNotificaciones);
		this.add(botonCerrarSesion);
		this.add(botonDatosMonitor);
		this.add(botonMisActividades);
		this.add(botonMisPlanes);
		this.add(botonEquipacion);
		
	}

	/**
	 * Establece el controlador
	 * @param controladorMonitor el controlador de la ventana
	 */
	public void setControlador(ControladorMonitor controladorMonitor) {
		
		botonNotificaciones.addActionListener(controladorMonitor);
		botonCerrarSesion.addActionListener(controladorMonitor);
		botonDatosMonitor.addActionListener(controladorMonitor);
		botonMisActividades.addActionListener(controladorMonitor);
		botonMisPlanes.addActionListener(controladorMonitor);
		botonEquipacion.addActionListener(controladorMonitor);
		
	}	
	
}