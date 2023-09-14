package gui.monitor;

import java.awt.Button;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import aplicacion.Gimnasio;
import gui.controladores.monitor.ControladorDatosMonitor;

/**
 * Este fichero muestra todo lo que tiene que ver con la clase DatosMonitorWindow
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class DatosMonitorWindow extends JPanel{
	private JButton botonAtras;
	private JButton botonNomina;
	
    private JLabel modeloDatosNombre;
    private JLabel modeloDatosUsuario;
    private JLabel modeloDatosCorreo;
    private JLabel modeloDatosNomina;
    
    private JLabel etiqueta;
    private JLabel etiquetaNombre;
    private JLabel etiquetaUsuario;
    private JLabel etiquetaCorreo;
    private JLabel etiquetaNomina;
    
    private SpringLayout layout;
	
    /**
     * Constructor DatosMonitorWindow
     */
	public DatosMonitorWindow()
	{

		layout = new SpringLayout();
	    this.setLayout(layout);
	    /****************************************************************************/
	    Font font = new Font("Arial", Font.BOLD, 20);
	    /****************************************************************************/
	    etiqueta = new JLabel("DATOS DEL MONITOR");
	    etiqueta.setFont(font);
	    etiqueta.setBackground(Color.GRAY);
	    etiquetaNombre = new JLabel("Nombre");
	    etiquetaUsuario = new JLabel("Usuario");
	    etiquetaCorreo = new JLabel("Correo");
	    etiquetaNomina = new JLabel("Mi nomina");
	    
	    modeloDatosNombre = new JLabel("");
	    modeloDatosUsuario = new JLabel("");
	    modeloDatosCorreo = new JLabel("");
	    modeloDatosNomina = new JLabel("");
	    /****************************************************************************/
	    botonAtras = new JButton("Atras");
	    
	    botonNomina = new JButton("Descargar Nomina");
		botonNomina.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonNomina.setBorder(null); 
		botonNomina.setContentAreaFilled(false); 
		botonNomina.setForeground(Color.BLUE);
	    /****************************************************************************/
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
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaCorreo, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, etiquetaCorreo, 20, SpringLayout.SOUTH, etiquetaUsuario);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, modeloDatosCorreo, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, modeloDatosCorreo, 5, SpringLayout.SOUTH, etiquetaCorreo);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaNomina, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, etiquetaNomina, 20, SpringLayout.SOUTH, etiquetaCorreo);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonNomina, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, botonNomina, 5, SpringLayout.SOUTH, etiquetaNomina);
        
        layout.putConstraint(SpringLayout.WEST, botonAtras, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, botonAtras, 5, SpringLayout.NORTH, this);
        
        
        
		/****************************************************************************/
        this.add(botonNomina);
		this.add(etiqueta);
        this.add(botonAtras);
        this.add(etiquetaCorreo);
        this.add(etiquetaUsuario);
        this.add(etiquetaNombre);
        this.add(etiquetaNomina);
        this.add(modeloDatosCorreo);
        this.add(modeloDatosNombre);
        this.add(modeloDatosNomina);
        this.add(modeloDatosUsuario);
	}
	
	/**
	 * Establece el controlador
	 * @param controladorDatosMonitor el controlador de la aplicacion
	 */
	public void setControlador(ControladorDatosMonitor controladorDatosMonitor) {
		botonNomina.addActionListener(controladorDatosMonitor);		
		botonAtras.addActionListener(controladorDatosMonitor);
	}
	
	/**
	 * GETTER
	 * @return el boton para descarga rla nomina
	 */
	public JButton getButtonNomina()
	{
		return this.botonNomina;
	}
	
	/**
	 * Actualiza los datos del monitor
	 * @param nombre el nombre del monitor
	 * @param usuario el nombre de usuario del monitor
	 * @param correo el correo electronico del monitor
	 */
	public void update(String nombre, String usuario, String correo) {
		
		this.modeloDatosNombre.setText(nombre);
		this.modeloDatosNombre.setForeground(Color.RED);
		this.modeloDatosUsuario.setText(usuario);
		this.modeloDatosUsuario.setForeground(Color.RED);
		this.modeloDatosCorreo.setText(correo);
		this.modeloDatosCorreo.setForeground(Color.RED);
		
	}
	

}
