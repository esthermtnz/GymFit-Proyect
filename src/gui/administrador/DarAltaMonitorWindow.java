package gui.administrador;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import gui.TextPrompt;
import gui.controladores.ControladorLogin;
import gui.controladores.administrador.ControladorDarAltaMonitor;

/**
 * Este fichero muestra todo lo que tiene que ver con la clase DarAltaMonitorWindow
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class DarAltaMonitorWindow extends JPanel{
	private JButton botonDarAlta;
	private JButton botonCancelar;
	
	private JTextField campoNombre;
	private JTextField campoEmail;
	private JTextField campoUsuario;
	private JPasswordField campoContrasenya;
	private JTextField campoNif;
	
	private SpringLayout layout;
	
	private JLabel etiqueta;
	private JLabel etiquetaNombre;
	private JLabel etiquetaEmail;
	private JLabel etiquetaUsuario;
	private JLabel etiquetaContrasenya;
	private JLabel etiquetaNif;
	
	private TextPrompt promptNombre;
	private TextPrompt promptEmail;
	private TextPrompt promptUsuario;
	private TextPrompt promptContrasenya;
	private TextPrompt promptNif;
	
	/**
	 * Constructor DarAltaMonitorWindow
	 */
	public DarAltaMonitorWindow()
	{
	    layout = new SpringLayout();
	    this.setLayout(layout);
	    /****************************************************************************/
        Font font = new Font("Arial", Font.BOLD, 20);
	    /****************************************************************************/
	    etiqueta = new JLabel("DAR DE ALTA A MONITOR");
	    etiqueta.setFont(font);
	    etiquetaNombre = new JLabel("Nombre");
	    etiquetaEmail = new JLabel("Email");
	    etiquetaUsuario = new JLabel("Usuario");
	    etiquetaContrasenya = new JLabel("Contraseña");
	    etiquetaNif = new JLabel("NIF");
	    /****************************************************************************/
        campoNombre = new JTextField(20);
        campoEmail = new JTextField(20);
        campoUsuario = new JTextField(20);
        campoContrasenya = new JPasswordField(20);
        campoNif = new JTextField(20);
        /****************************************************************************/
        promptNombre = new TextPrompt("Nombre Apellido1 Apellido2", campoNombre);
        promptEmail = new TextPrompt("X", campoEmail);
        promptUsuario = new TextPrompt("X", campoUsuario);
        promptContrasenya = new TextPrompt("Contraseña", campoContrasenya);
        promptNif = new TextPrompt("NIF", campoNif);
        /****************************************************************************/
        promptNombre.changeAlpha(0.5f);
        promptEmail.changeAlpha(0.5f);
        promptUsuario.changeAlpha(0.5f);
        promptContrasenya.changeAlpha(0.5f);
        promptNif.changeAlpha(0.5f);
        /****************************************************************************/
        botonDarAlta = new JButton("Dar de alta");
        botonCancelar = new JButton("Cancelar");
        /****************************************************************************/
        botonDarAlta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        botonDarAlta.setBackground(Color.LIGHT_GRAY);
        
        botonCancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        botonCancelar.setBackground(Color.LIGHT_GRAY);
        /****************************************************************************/
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiqueta, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, etiqueta, 5, SpringLayout.NORTH, this);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaNombre, 0, SpringLayout.HORIZONTAL_CENTER, campoNombre);
        layout.putConstraint(SpringLayout.NORTH, etiquetaNombre, 5, SpringLayout.SOUTH, etiqueta);
        
        layout.putConstraint(SpringLayout.WEST, campoNombre, 10, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, campoNombre, 5, SpringLayout.SOUTH, etiquetaNombre);
        
        layout.putConstraint(SpringLayout.WEST, promptNombre, 10, SpringLayout.WEST, campoNombre);
        layout.putConstraint(SpringLayout.NORTH, promptNombre, 0, SpringLayout.NORTH, campoNombre);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaEmail, 0, SpringLayout.HORIZONTAL_CENTER, campoEmail);
        layout.putConstraint(SpringLayout.NORTH, etiquetaEmail, 5, SpringLayout.SOUTH, promptNombre);
        
        layout.putConstraint(SpringLayout.WEST, campoEmail, 10, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, campoEmail, 5, SpringLayout.SOUTH, etiquetaEmail);
        
        layout.putConstraint(SpringLayout.WEST, promptEmail, 10, SpringLayout.WEST, campoEmail);
        layout.putConstraint(SpringLayout.NORTH, promptEmail, 0, SpringLayout.NORTH, campoEmail);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaUsuario, 0, SpringLayout.HORIZONTAL_CENTER, campoUsuario);
        layout.putConstraint(SpringLayout.NORTH, etiquetaUsuario, 5, SpringLayout.SOUTH, etiqueta);
        
        layout.putConstraint(SpringLayout.EAST, campoUsuario, -10, SpringLayout.EAST, this);
        layout.putConstraint(SpringLayout.NORTH, campoUsuario, 5, SpringLayout.SOUTH, etiquetaUsuario);
        
        layout.putConstraint(SpringLayout.WEST, promptUsuario, 10, SpringLayout.WEST, campoUsuario);
        layout.putConstraint(SpringLayout.NORTH, promptUsuario, 0, SpringLayout.NORTH, campoUsuario);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaContrasenya, 0, SpringLayout.HORIZONTAL_CENTER, campoContrasenya);
        layout.putConstraint(SpringLayout.NORTH, etiquetaContrasenya, 5, SpringLayout.SOUTH, promptUsuario);
        
        layout.putConstraint(SpringLayout.EAST, campoContrasenya, -10, SpringLayout.EAST, this);
        layout.putConstraint(SpringLayout.NORTH, campoContrasenya, 5, SpringLayout.SOUTH, etiquetaContrasenya);
        
        layout.putConstraint(SpringLayout.WEST, promptContrasenya, 10, SpringLayout.WEST, campoContrasenya);
        layout.putConstraint(SpringLayout.NORTH, promptContrasenya, 0, SpringLayout.NORTH, campoContrasenya);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaNif, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, etiquetaNif, 5, SpringLayout.SOUTH, etiqueta);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, campoNif, -5, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, campoNif, 5, SpringLayout.SOUTH, etiquetaNif);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, promptNif, 10, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, promptNif, 0, SpringLayout.NORTH, campoNif);   
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonDarAlta, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, botonDarAlta, 5, SpringLayout.SOUTH, campoEmail);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonCancelar, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, botonCancelar, 5, SpringLayout.SOUTH, botonDarAlta);
        /****************************************************************************/
        this.add(etiqueta);
        this.add(etiquetaNombre);
        this.add(promptNombre);
        this.add(campoNombre);
        this.add(etiquetaEmail);
        this.add(promptEmail);
        this.add(campoEmail);
        this.add(etiquetaUsuario);
        this.add(promptUsuario);
        this.add(campoUsuario);
        this.add(etiquetaContrasenya);
        this.add(promptContrasenya);
        this.add(campoContrasenya);
        this.add(botonDarAlta);
        this.add(botonCancelar);
        this.add(etiquetaNif);
        this.add(promptNif);
        this.add(campoNif);
        
	}

	/**
	 * GETTER
	 * @return el nombre
	 */
	public String getCampoNombre() 
	{
		return this.campoNombre.getText();
	}
	
	/**
	 * GETTER
	 * @return la direccion de correo
	 */
    public String getCampoEmail()
    {
    	return this.campoEmail.getText();
    }
    
    /**
	 * GETTER
	 * @return el nombre de usuario
	 */
    public String getCampoUsuario()
    {
    	return this.campoUsuario.getText();
    }
    
    /**
	 * GETTER
	 * @return la contraseña
	 */
    public String getCampoContrasenya()
    {
    	return String.valueOf(this.campoContrasenya.getPassword());
    }
	
    /**
	 * GETTER
	 * @return el nif
	 */
    public String getCampoNif() {
		return this.campoNif.getText();
	}
    
    /**
     * SETTER
     * @param texto el nombre
     */
    public void setCampoNombre(String texto) 
	{
		this.campoNombre.setText(texto);
		return;
	}
	
    /**
     * SETTER
     * @param texto la direccion de correo
     */
    public void setCampoEmail(String texto)
    {
    	this.campoEmail.setText(texto);
    	return;
    }
    
    /**
     * SETTER
     * @param texto el nombre de usuario
     */
    public void setCampoUsuario(String texto)
    {
    	this.campoUsuario.setText(texto);
    	return;
    }
    
    /**
     * SETTER
     * @param texto la contraseña
     */
    public void setCampoContrasenya(String texto)
    {
    	this.campoContrasenya.setText(texto);
    	return;
    }
	
    /**
     * SETTER
     * @param texto el nif
     */
    public void setCampoNif(String texto) {
		this.campoNif.setText(texto);
		return;		
	}
    
    /**
     * Establece el controlador
     * @param controladorDarAltaMonitor el controlador de la ventana
     */
	public void setControlador(ControladorDarAltaMonitor controladorDarAltaMonitor) {
		botonDarAlta.addActionListener(controladorDarAltaMonitor);
		botonCancelar.addActionListener(controladorDarAltaMonitor);
		
	}

	    
	
}