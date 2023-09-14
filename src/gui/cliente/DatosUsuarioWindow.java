package gui.cliente;

import java.awt.Button;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultTreeCellEditor.DefaultTextField;

import aplicacion.Gimnasio;
import gui.controladores.cliente.ControladorDatosUsuario;
import gui.controladores.monitor.ControladorDatosMonitor;

/**
 * Este fichero muestra todo lo que tiene que ver con la clase DatosUsuarioWindow
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class DatosUsuarioWindow extends JPanel{
	private JButton botonAtras;
	
    private JLabel etiqueta;
    private JLabel etiquetaNombre;
    private JLabel etiquetaUsuario;
    private JLabel etiquetaTelefono;
    private JLabel etiquetaTarifa;
    private JLabel etiquetaActividad;
    private JLabel etiquetaCancelaciones;
    
    private SpringLayout layout;
    
    private JLabel modeloDatosNombre;
    private JLabel modeloDatosUsuario;
    private JLabel modeloDatosTelefono;
    private JLabel modeloDatosTarifa;
    private JLabel modeloDatosActividad;
    private JLabel modeloDatosCancelaciones;
	
    /**
     * Constructor DatosUsuarioWindow
     */
	public DatosUsuarioWindow()
	{

		layout = new SpringLayout();
	    this.setLayout(layout);
	    /****************************************************************************/
	    Font font = new Font("Arial", Font.BOLD, 20);
	    /****************************************************************************/
	    etiqueta = new JLabel("DATOS DEL CLIENTE");
	    etiqueta.setFont(font);
	    etiqueta.setBackground(Color.GRAY);
	    etiquetaNombre = new JLabel("Nombre");
	    etiquetaUsuario = new JLabel("Usuario");
	    etiquetaTelefono = new JLabel("Telefono");
	    etiquetaTarifa = new JLabel("Tarifa");
	    etiquetaActividad = new JLabel("Tipo Actividad");
	    etiquetaCancelaciones = new JLabel ("Cancelaciones");
	    
	    modeloDatosNombre = new JLabel("");
	    modeloDatosUsuario = new JLabel("");
	    modeloDatosTelefono = new JLabel("");
	    modeloDatosTarifa = new JLabel("");
	    modeloDatosActividad = new JLabel("");
	    modeloDatosCancelaciones = new JLabel("");
	    
	    modeloDatosNombre.setText("");
	    modeloDatosUsuario.setText("");
	    modeloDatosTelefono.setText("");
	    modeloDatosTarifa.setText("");
	    modeloDatosActividad.setText("");
	    modeloDatosCancelaciones.setText("");
	    /****************************************************************************/
	    botonAtras = new JButton("Atras");
		botonAtras.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonAtras.setBackground(Color.LIGHT_GRAY);
		/****************************************************************************/
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiqueta, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, etiqueta, 10, SpringLayout.NORTH, this);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaNombre, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, etiquetaNombre, 50, SpringLayout.SOUTH, etiqueta);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, modeloDatosNombre, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, modeloDatosNombre, 5, SpringLayout.SOUTH, etiquetaNombre);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaUsuario, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, etiquetaUsuario, 20, SpringLayout.SOUTH, etiquetaNombre);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, modeloDatosUsuario, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, modeloDatosUsuario, 5, SpringLayout.SOUTH, etiquetaUsuario);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaTelefono, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, etiquetaTelefono, 20, SpringLayout.SOUTH, etiquetaUsuario);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, modeloDatosTelefono, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, modeloDatosTelefono, 5, SpringLayout.SOUTH, etiquetaTelefono);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaTarifa, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, etiquetaTarifa, 20, SpringLayout.SOUTH, etiquetaTelefono);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaActividad, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, etiquetaActividad, 20, SpringLayout.SOUTH, etiquetaTarifa);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, modeloDatosTarifa, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, modeloDatosTarifa, 5, SpringLayout.SOUTH, etiquetaTarifa);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, modeloDatosActividad, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, modeloDatosActividad, 5, SpringLayout.SOUTH, this.etiquetaActividad);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaCancelaciones, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, etiquetaCancelaciones, 20, SpringLayout.SOUTH, etiquetaActividad);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, modeloDatosCancelaciones, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, modeloDatosCancelaciones, 5, SpringLayout.SOUTH, etiquetaCancelaciones);
		
        
        layout.putConstraint(SpringLayout.WEST, botonAtras, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, botonAtras, 5, SpringLayout.NORTH, this);
        
		/****************************************************************************/
		this.add(etiqueta);
        this.add(botonAtras);
        this.add(etiquetaTelefono);
        this.add(etiquetaUsuario);
        this.add(etiquetaNombre);
        this.add(etiquetaTarifa);
        this.add(etiquetaActividad);
        this.add(etiquetaCancelaciones);
        this.add(modeloDatosNombre);
        this.add(modeloDatosTarifa);
        this.add(modeloDatosTelefono);
        this.add(modeloDatosUsuario);
        this.add(modeloDatosActividad);
        this.add(modeloDatosCancelaciones);
	}
	
	/**
	 * Establece el controlador
	 * @param controladorDatosUsuario el controlador de la ventana
	 */
	public void setControlador(ControladorDatosUsuario controladorDatosUsuario) {	
		botonAtras.addActionListener(controladorDatosUsuario);
	}
	
	/**
	 * Actualiza los datos del cliente
	 * @param nombre el nombre del cliente
	 * @param usuario el nombre de usuario del cliente
	 * @param telefono el telefono del cliente
	 * @param tarifa la tarifa que ha contratado el cliente
	 * @param actividad el tipo de actividad que ha seleccionado el cliente
	 * @param cancelaciones el numero de cancelaciones
	 */
	public void update(String nombre, String usuario, String telefono, String tarifa, String actividad, Integer cancelaciones) {
		
		this.modeloDatosNombre.setText(nombre);
		this.modeloDatosNombre.setForeground(Color.RED);
		this.modeloDatosUsuario.setText(usuario);
		this.modeloDatosUsuario.setForeground(Color.RED);
		this.modeloDatosTelefono.setText(telefono);
		this.modeloDatosTelefono.setForeground(Color.RED);
		this.modeloDatosTarifa.setText(tarifa);
		this.modeloDatosTarifa.setForeground(Color.RED);
		this.modeloDatosActividad.setText(actividad);
		this.modeloDatosActividad.setForeground(Color.RED);
		this.modeloDatosCancelaciones.setText(cancelaciones.toString());
		this.modeloDatosCancelaciones.setForeground(Color.RED);
		
		
		}
	

}