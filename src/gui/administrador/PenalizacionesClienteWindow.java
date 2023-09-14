package gui.administrador;

import java.awt.*;

import javax.swing.*;

import gui.TextPrompt;
import gui.controladores.*;
import gui.controladores.administrador.ControladorPenalizacionesCliente;

/**
 * Este fichero muestra todo lo que tiene que ver con la clase PenalizacionesClienteWindow
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class PenalizacionesClienteWindow extends JPanel{
	private JButton botonHecho;
	private JButton botonCancelar;
	
	private JTextField campoCancelaciones;
	private JTextField campoDias;
	
	private SpringLayout layout;
	
	private JLabel etiqueta;
	private JLabel etiquetaCancelaciones;
	private JLabel etiquetaDias;
	
	private TextPrompt promptCancelaciones;
	private TextPrompt promptDias;
	
	/**
	 * Constructor PenalizacionesClienteWindow
	 */
	public PenalizacionesClienteWindow()
	{
	   
	    layout = new SpringLayout();
	    this.setLayout(layout);
	    /****************************************************************************/
        Font font = new Font("Arial", Font.BOLD, 20);
        /****************************************************************************/ 
	    etiqueta = new JLabel("PENALIZACIONES CLIENTES");
	    etiqueta.setFont(font);
	    etiquetaCancelaciones = new JLabel("Nº de cancelaciones seguidas no permitidas");
	    etiquetaDias = new JLabel("Días de penalizacion");
	    /****************************************************************************/ 
	    botonHecho = new JButton("Hecho");
        botonCancelar = new JButton("Cancelar");
        /****************************************************************************/ 
        campoCancelaciones = new JTextField(20);
        campoDias = new JTextField(20);
        /****************************************************************************/ 
        promptCancelaciones = new TextPrompt("X", campoCancelaciones);
        promptDias = new TextPrompt("X", campoDias);
        /****************************************************************************/ 
        promptCancelaciones.changeAlpha(0.5f);
        promptDias.changeAlpha(0.5f);
        /****************************************************************************/ 
        botonHecho.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        botonHecho.setBackground(Color.LIGHT_GRAY);
        
        botonCancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        botonCancelar.setBackground(Color.LIGHT_GRAY);
        /****************************************************************************/ 
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiqueta, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, etiqueta, 5, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaCancelaciones, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, etiquetaCancelaciones, 5, SpringLayout.SOUTH, etiqueta);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, campoCancelaciones, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, campoCancelaciones, 5, SpringLayout.SOUTH, etiquetaCancelaciones);
        
        layout.putConstraint(SpringLayout.WEST, promptCancelaciones, 10, SpringLayout.WEST, campoCancelaciones);
        layout.putConstraint(SpringLayout.NORTH, promptCancelaciones, 0, SpringLayout.NORTH, campoCancelaciones);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaDias, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, etiquetaDias, 5, SpringLayout.SOUTH, promptCancelaciones);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, campoDias, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, campoDias, 5, SpringLayout.SOUTH, etiquetaDias);
        
        layout.putConstraint(SpringLayout.WEST, promptDias, 10, SpringLayout.WEST, campoDias);
        layout.putConstraint(SpringLayout.NORTH, promptDias, 0, SpringLayout.NORTH, campoDias);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonHecho, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, botonHecho, 5, SpringLayout.SOUTH, promptDias);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonCancelar, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, botonCancelar, 5, SpringLayout.SOUTH, botonHecho);
        /****************************************************************************/ 
        this.add(etiqueta);
        this.add(etiquetaCancelaciones);
        this.add(promptCancelaciones);
        this.add(campoCancelaciones);
        this.add(etiquetaDias);
        this.add(promptDias);
        this.add(campoDias);
        this.add(botonHecho);
        this.add(botonCancelar);
        
	}
	
	/**
	 * GETTER
	 * @return numero de cancelaciones
	 */
	public int getCampoCancelaciones()
	{
		return Integer.parseInt(this.campoCancelaciones.getText());
	}
	
	/**
	 * GETTER
	 * @return dias penalizado
	 */
    public int getCampoDias()
    {
    	return Integer.parseInt(this.campoDias.getText());
    }
    
    /**
     * SETTER
     * @param texto el numero de cancelaciones
     */
    public void setCampoCancelaciones(String texto)
	{
		this.campoCancelaciones.setText(texto);
		return;
	}
	
    /**
     * SETTER
     * @param texto los dias penalizado
     */
    public void setCampoDias(String texto)
    {
    	this.campoDias.setText(texto);
    	return;
    }

    /**
     * Establece el controlador
     * @param controladorPenalizacionesCliente el controlador de la ventana
     */
	public void setControlador(ControladorPenalizacionesCliente controladorPenalizacionesCliente) {
		botonCancelar.addActionListener(controladorPenalizacionesCliente);
		botonHecho.addActionListener(controladorPenalizacionesCliente);
		
	}    
	 
}