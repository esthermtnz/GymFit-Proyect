/**
 * Este fichero muestra todo lo que tiene que ver con la clase LoginWindow
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
package gui;

import aplicacion.*;
import aplicacion.actividad.TipoActividad;
import aplicacion.usuario.Cliente;
import aplicacion.usuario.tarifa.TarifaPlana;
import aplicacion.usuario.tarifa.TarjetaCredito;
import aplicacion.usuario.tarifa.TipoTarifaPlana;
import gui.controladores.ControladorLogin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.*;
import javax.swing.border.Border;

/**
 * esta es una clase que representa LoginWindow
 * 
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class LoginWindow extends JPanel {
	private JButton botonInicSesion;
	private JButton botonRegistrate;
	private JButton botonBorrar;
	private JButton botonRenovar;
	
	private JTextField campoUsuario;
	private JPasswordField campoContrasenya;
	
	private SpringLayout layout;
	
	private JLabel etiqueta;
	private JLabel etiquetaPregunta;
	private JLabel logo; 
	
	private TextPrompt promptContrasenya;
    private TextPrompt promptUsuario;
	
    /**
     * COnstructor de LoginWindow
     */
    public LoginWindow() {
        
        layout = new SpringLayout();
        this.setLayout(layout);     
        /****************************************************************************/
        Font font = new Font("Arial", Font.BOLD, 20);
        /****************************************************************************/
        ImageIcon imagen = new ImageIcon("./resources/logo.jpg");
        Image imagenPequena = imagen.getImage().getScaledInstance(200,100, Image.SCALE_SMOOTH);
        ImageIcon imagenPequenaIcono = new ImageIcon(imagenPequena);
        /****************************************************************************/
        etiqueta = new JLabel("INICIA SESION");
        etiqueta.setFont(font);
        etiquetaPregunta = new JLabel("¿Aún no tienes cuenta?");
        etiquetaPregunta.setForeground(Color.BLACK);
        logo = new JLabel (imagenPequenaIcono); 
        /****************************************************************************/
        campoUsuario = new JTextField(20);
        campoContrasenya = new JPasswordField(20);      
        /****************************************************************************/
        botonInicSesion = new JButton("Iniciar Sesion");
        botonRegistrate = new JButton("Registrate");
        botonBorrar = new JButton("Borrar backup");
        botonRenovar = new JButton("Renovar tarifa");
        /****************************************************************************/
        promptContrasenya = new TextPrompt("Contraseña", campoContrasenya);
        promptUsuario = new TextPrompt("Usuario", campoUsuario);
        /****************************************************************************/
        promptUsuario.changeAlpha(0.5f);
        promptContrasenya.changeAlpha(0.5f);
        /****************************************************************************/
        botonInicSesion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        botonInicSesion.setBackground(Color.LIGHT_GRAY);
        
        botonBorrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        botonBorrar.setBackground(Color.LIGHT_GRAY);
        
        botonRenovar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        botonRenovar.setBackground(Color.LIGHT_GRAY);
        
        botonRegistrate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        botonRegistrate.setBorder(null); 
        botonRegistrate.setContentAreaFilled(false); 
        botonRegistrate.setForeground(Color.BLUE);
        /****************************************************************************/
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiqueta, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, etiqueta, -60, SpringLayout.VERTICAL_CENTER, this);
       
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, campoUsuario, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, campoUsuario, 5, SpringLayout.SOUTH, etiqueta);
        
        layout.putConstraint(SpringLayout.WEST, promptUsuario, 10, SpringLayout.WEST, campoUsuario);
        layout.putConstraint(SpringLayout.NORTH, promptUsuario, 0, SpringLayout.NORTH, campoUsuario);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, campoContrasenya, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, campoContrasenya, 5, SpringLayout.SOUTH, campoUsuario);	
        
        layout.putConstraint(SpringLayout.WEST, promptContrasenya, 10, SpringLayout.WEST, campoContrasenya);
        layout.putConstraint(SpringLayout.NORTH, promptContrasenya, 0, SpringLayout.NORTH, campoContrasenya);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonInicSesion, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, botonInicSesion, 5, SpringLayout.SOUTH, campoContrasenya);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaPregunta, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, etiquetaPregunta, 5, SpringLayout.SOUTH, botonInicSesion);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonRegistrate, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, botonRegistrate, 5, SpringLayout.SOUTH, etiquetaPregunta);
        
        layout.putConstraint(SpringLayout.WEST, botonBorrar, 10, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.SOUTH, botonBorrar, -10, SpringLayout.SOUTH, this);
        
        layout.putConstraint(SpringLayout.EAST, botonRenovar, -10, SpringLayout.EAST, this);
        layout.putConstraint(SpringLayout.SOUTH, botonRenovar, -10, SpringLayout.SOUTH, this);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, logo, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.SOUTH, logo, -10, SpringLayout.NORTH, etiqueta);
        /****************************************************************************/       
        this.add(etiqueta);
        this.add(promptUsuario);
        this.add(campoUsuario);
        this.add(promptContrasenya);
        this.add(campoContrasenya);
        this.add(botonInicSesion);
        this.add(etiquetaPregunta);
        this.add(botonRegistrate);
        this.add(logo);
        this.add(botonBorrar);
        this.add(botonRenovar);
    }
    
    /**
     * GETTER
     * @return el campoUsuario
     */
    public String getCampoUsuario()
    {
    	return this.campoUsuario.getText();
    }

    /**
     * GETTER
     * @return el compoContrasenya
     */
    public String getCampoContrasenya()
    {
    	return String.valueOf(this.campoContrasenya.getPassword());
    }
    
    /**
     * Estbalece el campoUsuario
     * @param texto el nombre ddel usuario
     */
    public void setCampoUsuario(String texto)
    {
    	 this.campoUsuario.setText(texto);
    	 return;
    }
    
    /**
     * Establece el compoContrasenya
     * @param texto la contraaseña del usuario
     */
    public void setCampoContrasenya(String texto)
    {
    	 this.campoContrasenya.setText(texto);
    	 return;
    }
    
    /**
     * Establece el controlador
     * @param controladorLogin el controlador de la ventana
     */
	public void setControlador(ControladorLogin controladorLogin) {
		botonRegistrate.addActionListener(controladorLogin);
		botonInicSesion.addActionListener(controladorLogin);
		botonBorrar.addActionListener(controladorLogin);
		botonRenovar.addActionListener(controladorLogin);
	}
}