package gui.administrador;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import gui.TextPrompt;
import gui.controladores.administrador.ControladorTipoActividad;

/**
 * Este fichero muestra todo lo que tiene que ver con la clase TipoActividadWindow
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class TipoActividadWindow extends JPanel{
	
	private JButton botonHecho;
	private JButton botonAtras;
	
	private JTextField campoTipoActividad;
	
	private TextPrompt promptTipoActividad;
	
	private SpringLayout layout;
	
	private JLabel etiqueta;
	private JLabel etiquetaNombre;
	
	/**
	 * Constructor TipoActividadWindow
	 */
	public TipoActividadWindow()
	{
	 	layout = new SpringLayout();
	    this.setLayout(layout);
	    /****************************************************************************/
        Font font = new Font("Arial", Font.BOLD, 20);
	    /****************************************************************************/
	    etiqueta = new JLabel("Tipo Actividad");
	    etiqueta.setFont(font);
	    etiqueta.setBackground(Color.GRAY);
	    etiquetaNombre = new JLabel("Nombre");
		/****************************************************************************/
	    botonHecho = new JButton("Hecho");
	    botonAtras = new JButton("Atras");
	    /****************************************************************************/
	    botonHecho.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	    botonHecho.setBackground(Color.LIGHT_GRAY);
		
	    botonAtras.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	    botonAtras.setBackground(Color.LIGHT_GRAY);
	    /****************************************************************************/
	    campoTipoActividad = new JTextField(20);
	    /****************************************************************************/
	    promptTipoActividad = new TextPrompt("Tipo Actividad", campoTipoActividad);
	    promptTipoActividad.changeAlpha(0.5f);
	    /****************************************************************************/
	    layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiqueta, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, etiqueta, 10, SpringLayout.NORTH, this);
		
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaNombre, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, etiquetaNombre, 20, SpringLayout.SOUTH, etiqueta);
		
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, campoTipoActividad, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, campoTipoActividad, 5, SpringLayout.SOUTH, etiquetaNombre);	
        
        layout.putConstraint(SpringLayout.WEST, promptTipoActividad, 10, SpringLayout.WEST, campoTipoActividad);
        layout.putConstraint(SpringLayout.NORTH, promptTipoActividad, 0, SpringLayout.NORTH, campoTipoActividad);
		
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonHecho, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, botonHecho, 20, SpringLayout.SOUTH, campoTipoActividad);
		
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonAtras, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, botonAtras, 5, SpringLayout.SOUTH, botonHecho);
	    /****************************************************************************/
		
		this.add(etiqueta);
		this.add(etiquetaNombre);
		this.add(promptTipoActividad);
		this.add(campoTipoActividad);
		this.add(botonHecho);
		this.add(botonAtras);
	}
	
	/**
	 * GETTER
	 * @return el tipo de actividad 
	 */
	public String getCampoTipoActividad()
    {
    	return this.campoTipoActividad.getText();
    }
	
	/**
	 * SETTER
	 * @param texto el tipo de actividad
	 */
	public void setCampoTipoActividad(String texto)
    {
		this.campoTipoActividad.setText(texto);
    	return;
    }
	
	/**
	 * Establece el controlador 
	 * @param controladorTipoActividad el controlador de la ventana
	 */
	public void setControlador(ControladorTipoActividad controladorTipoActividad) {
		botonHecho.addActionListener(controladorTipoActividad);
		botonAtras.addActionListener(controladorTipoActividad);
	}
}
