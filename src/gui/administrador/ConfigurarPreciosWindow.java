
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
import gui.controladores.administrador.ControladorConfigurarPrecios;
/**
 * Este fichero muestra todo lo que tiene que ver con la clase ConfigurarPreciosWindow
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class ConfigurarPreciosWindow extends JPanel{
	private JButton botonHecho;
	private JButton botonCancelar; 
	
	private JTextField campoPlanesPersonalizados;
	private JTextField campoSesionesLibres;
	private JTextField campoPrecioTarifa;
	private JTextField campoDescuentoPlanesP;
	private JTextField campoDescuentoDevoluciones;
	
	private SpringLayout layout;
    
	private JLabel etiqueta;   
	private JLabel etiqueta1;
	private JLabel etiquetaPlanesPersonalizados;
	private JLabel etiquetaSesionesLibres;
	private JLabel etiqueta2;
	private JLabel etiquetaPrecioTarifa;
	private JLabel etiquetaDescuentoPlanesP;
	private JLabel etiqueta3;
	private JLabel etiquetaDescuentoDevoluciones;
	
	private TextPrompt promptPlanesPersonalizados;
	private TextPrompt promptSesionesLibres;
	private TextPrompt promptPrecioTarifa;
	private TextPrompt promptDescuentoPlanesP ;
	private TextPrompt promptDescuentoDevoluciones;
	
	/**
	 * Constructor ConfigurarPreciosWindow
	 */
	public ConfigurarPreciosWindow()
	{
	    layout = new SpringLayout();
	    this.setLayout(layout);
	    /****************************************************************************/
        Font font = new Font("Arial", Font.BOLD, 20);
	    /****************************************************************************/ 
	    etiqueta = new JLabel("CONFIGURAR PRECIOS");
	    etiqueta.setFont(font);
	    etiqueta1 = new JLabel("PRECIO ACTIVIDADES");
	    etiquetaPlanesPersonalizados = new JLabel("Planes Personalizados");
	    etiquetaSesionesLibres = new JLabel("Sesiones Libres");
	    etiqueta2 = new JLabel("PRECIO TARIFA PLANA");
        etiquetaPrecioTarifa = new JLabel("Precio de la tarifa");
        etiquetaDescuentoPlanesP = new JLabel("Descuento planes personalizados");
        etiqueta3 = new JLabel("PRECIO DEVOLUCIONES AL CANCELAR");
        etiquetaDescuentoDevoluciones = new JLabel("Descuento de devoluciones");
	    /****************************************************************************/ 
        campoPlanesPersonalizados = new JTextField(20);
        campoSesionesLibres = new JTextField(20);
        campoPrecioTarifa = new JTextField(20);
        campoDescuentoPlanesP = new JTextField(20);
        campoDescuentoDevoluciones = new JTextField(20);
        /****************************************************************************/ 
        botonHecho = new JButton("Hecho");
        botonCancelar = new JButton("Cancelar");
        /****************************************************************************/ 
        promptPlanesPersonalizados = new TextPrompt("X €", campoPlanesPersonalizados);
        promptSesionesLibres = new TextPrompt("X €", campoSesionesLibres);
        promptPrecioTarifa = new TextPrompt("X €", campoPrecioTarifa);
        promptDescuentoPlanesP = new TextPrompt("X %", campoDescuentoPlanesP);
        promptDescuentoDevoluciones = new TextPrompt("X %", campoDescuentoDevoluciones);
        /****************************************************************************/ 
        promptPlanesPersonalizados.changeAlpha(0.5f);
        promptSesionesLibres.changeAlpha(0.5f);
        promptPrecioTarifa.changeAlpha(0.5f);
        promptDescuentoPlanesP.changeAlpha(0.5f);
        promptDescuentoDevoluciones.changeAlpha(0.5f);
        /****************************************************************************/ 
        botonHecho.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        botonHecho.setBackground(Color.LIGHT_GRAY);
        
        botonCancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        botonCancelar.setBackground(Color.LIGHT_GRAY);
        /****************************************************************************/ 
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiqueta, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, etiqueta, 5, SpringLayout.NORTH, this);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiqueta1, 0, SpringLayout.HORIZONTAL_CENTER, etiquetaPlanesPersonalizados);
        layout.putConstraint(SpringLayout.NORTH, etiqueta1, 5, SpringLayout.SOUTH, etiqueta);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaPlanesPersonalizados, 0, SpringLayout.HORIZONTAL_CENTER, campoPlanesPersonalizados);
        layout.putConstraint(SpringLayout.NORTH, etiquetaPlanesPersonalizados, 5, SpringLayout.SOUTH, etiqueta1);
        
        layout.putConstraint(SpringLayout.WEST, campoPlanesPersonalizados, 10, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, campoPlanesPersonalizados, 5, SpringLayout.SOUTH, etiquetaPlanesPersonalizados);
        
        layout.putConstraint(SpringLayout.WEST, promptPlanesPersonalizados, 10, SpringLayout.WEST, campoPlanesPersonalizados);
        layout.putConstraint(SpringLayout.NORTH, promptPlanesPersonalizados, 0, SpringLayout.NORTH, campoPlanesPersonalizados);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaSesionesLibres, 0, SpringLayout.HORIZONTAL_CENTER, campoSesionesLibres);
        layout.putConstraint(SpringLayout.NORTH, etiquetaSesionesLibres, 5, SpringLayout.SOUTH, promptPlanesPersonalizados);
        
        layout.putConstraint(SpringLayout.WEST, campoSesionesLibres, 10, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, campoSesionesLibres, 5, SpringLayout.SOUTH, etiquetaSesionesLibres);
        
        layout.putConstraint(SpringLayout.WEST, promptSesionesLibres, 10, SpringLayout.WEST, campoSesionesLibres);
        layout.putConstraint(SpringLayout.NORTH, promptSesionesLibres, 0, SpringLayout.NORTH, campoSesionesLibres);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiqueta2, 0, SpringLayout.HORIZONTAL_CENTER, etiquetaPrecioTarifa);
        layout.putConstraint(SpringLayout.NORTH, etiqueta2, 5, SpringLayout.SOUTH, etiqueta);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaPrecioTarifa, 0, SpringLayout.HORIZONTAL_CENTER, campoPrecioTarifa);
        layout.putConstraint(SpringLayout.NORTH, etiquetaPrecioTarifa, 5, SpringLayout.SOUTH, etiqueta2);
        
        layout.putConstraint(SpringLayout.EAST, campoPrecioTarifa, -5, SpringLayout.EAST, this);
        layout.putConstraint(SpringLayout.NORTH, campoPrecioTarifa, 5, SpringLayout.SOUTH, etiquetaPrecioTarifa);
        
        layout.putConstraint(SpringLayout.WEST, promptPrecioTarifa, 10, SpringLayout.WEST, campoPrecioTarifa);
        layout.putConstraint(SpringLayout.NORTH, promptPrecioTarifa, 0, SpringLayout.NORTH, campoPrecioTarifa);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaDescuentoPlanesP, 0, SpringLayout.HORIZONTAL_CENTER, campoDescuentoPlanesP);
        layout.putConstraint(SpringLayout.NORTH, etiquetaDescuentoPlanesP, 5, SpringLayout.SOUTH, promptPrecioTarifa);
        
        layout.putConstraint(SpringLayout.EAST, campoDescuentoPlanesP, -10, SpringLayout.EAST, this);
        layout.putConstraint(SpringLayout.NORTH, campoDescuentoPlanesP, 5, SpringLayout.SOUTH, etiquetaDescuentoPlanesP);
        
        layout.putConstraint(SpringLayout.WEST, promptDescuentoPlanesP, 10, SpringLayout.WEST, campoDescuentoPlanesP);
        layout.putConstraint(SpringLayout.NORTH, promptDescuentoPlanesP, 0, SpringLayout.NORTH, campoDescuentoPlanesP);
        
        layout.putConstraint(SpringLayout.EAST, etiqueta3, -5, SpringLayout.EAST, this);
        layout.putConstraint(SpringLayout.NORTH, etiqueta3, 5, SpringLayout.SOUTH, promptDescuentoPlanesP);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaDescuentoDevoluciones, 0, SpringLayout.HORIZONTAL_CENTER, campoDescuentoDevoluciones);
        layout.putConstraint(SpringLayout.NORTH, etiquetaDescuentoDevoluciones, 5, SpringLayout.SOUTH, etiqueta3);
        
        layout.putConstraint(SpringLayout.EAST, campoDescuentoDevoluciones, -10, SpringLayout.EAST, this);
        layout.putConstraint(SpringLayout.NORTH, campoDescuentoDevoluciones, 5, SpringLayout.SOUTH, etiquetaDescuentoDevoluciones);
        
        layout.putConstraint(SpringLayout.WEST, promptDescuentoDevoluciones, 10, SpringLayout.WEST, campoDescuentoDevoluciones);
        layout.putConstraint(SpringLayout.NORTH, promptDescuentoDevoluciones, 0, SpringLayout.NORTH, campoDescuentoDevoluciones);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonHecho, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, botonHecho, 5, SpringLayout.SOUTH, promptDescuentoDevoluciones);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonCancelar, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, botonCancelar, 5, SpringLayout.SOUTH, botonHecho);
        /****************************************************************************/ 
        this.add(etiqueta);
        this.add(etiqueta1);
        this.add(etiquetaPlanesPersonalizados);
        this.add(promptPlanesPersonalizados);
        this.add(campoPlanesPersonalizados);        
        this.add(etiquetaSesionesLibres);
        this.add(promptSesionesLibres);
        this.add(campoSesionesLibres);
        this.add(etiqueta2);
        this.add(etiquetaPrecioTarifa);
        this.add(promptPrecioTarifa);
        this.add(campoPrecioTarifa);
        this.add(etiquetaDescuentoPlanesP);
        this.add(promptDescuentoPlanesP);
        this.add(campoDescuentoPlanesP);
        this.add(etiquetaDescuentoDevoluciones);
        this.add(promptDescuentoDevoluciones);
        this.add(campoDescuentoDevoluciones);
        this.add(etiqueta3);
        this.add(botonHecho);
        this.add(botonCancelar);
        /****************************************************************************/ 
        
	}
	
	/**
	 * GETTER
	 * @return precio planes
	 */
    public Double getCampoPlanesPersonalizados()
    {
    	return Double.parseDouble(this.campoPlanesPersonalizados.getText());
    }
    
    /**
     * GETTER
     * @return precio sesion libre
     */
    public Double getCampoSesionesLibres()
    {
    	return Double.parseDouble(this.campoSesionesLibres.getText());
    }
    
    /**
     * GETTER
     * @return precio tarifa
     */
    public double getCampoPrecioTarifa()
    {
    	return Double.parseDouble(this.campoPrecioTarifa.getText());
    }
    
    /**
     * GETTER
     * @return descuento planes
     */
    public double getCampoDescuentoPlanesP()
    {
    	return Double.parseDouble(this.campoDescuentoPlanesP.getText());
    }
    
    /**
     * GETTER
     * @return descuento devolucion
     */
    public double getCampoDescuentoDevoluciones()
    {
    	return Double.parseDouble(this.campoDescuentoDevoluciones.getText());
    }
	
    /****************/
    /**
     * Establece el precio planes
     * @param texto el preio
     */
    public void setCampoPlanesPersonalizados(String texto)
    {
    	this.campoPlanesPersonalizados.setText(texto);
    	return;
    }
    
    /**
     * Establece el precio sesiones libre
     * @param texto el preio
     */
    public void setCampoSesionesLibres(String texto)
    {
    	this.campoSesionesLibres.setText(texto);
    	return;
    }
    
    /**
     * Establece el precio tarifa
     * @param texto el precio
     */
    public void setCampoPrecioTarifa(String texto)
    {
    	this.campoPrecioTarifa.setText(texto);
    	return;
    }
    
    /**
     * Establece el descuento planes
     * @param texto el descuent
     */
    public void setCampoDescuentoPlanesP(String texto)
    {
    	this.campoDescuentoPlanesP.setText(texto);
    	return;
    }
    
    /**
     * Establece el descuento devolucion
     * @param texto el descuento
     */
    public void setCampoDescuentoDevoluciones(String texto)
    {
    	this.campoDescuentoDevoluciones.setText(texto);
    	return;
    }

    /**
     * Establece el controlador
     * @param controladorConfigurarPrecios el controlador de la ventana
     */
	public void setControlador(ControladorConfigurarPrecios controladorConfigurarPrecios) {
		botonHecho.addActionListener(controladorConfigurarPrecios);
		botonCancelar.addActionListener(controladorConfigurarPrecios);		
	}    
	
}