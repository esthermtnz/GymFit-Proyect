package gui.administrador;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import gui.TextPrompt;
import gui.controladores.ControladorLogin;
import gui.controladores.administrador.ControladorSueldoMonitores;

/**
 * Este fichero muestra todo lo que tiene que ver con la clase SueldoMonitoresWindow
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class SueldoMonitoresWindow extends JPanel{
	private JButton botonHecho;
	private JButton botonCancelar;
	
	private JTextField campoSueldo;
	private JTextField campoSuplemento;
	
	private SpringLayout layout;
	
	private JLabel etiqueta;
	private JLabel etiquetaSueldo;
	private JLabel etiquetaSuplemento;
	
	private TextPrompt promptSuplemento;
    private TextPrompt promptSueldo;
	
    /**
     * Constructor SueldoMonitoresWindow
     */
	public SueldoMonitoresWindow()
	{
	    
	    layout = new SpringLayout();
	    this.setLayout(layout);
	    /****************************************************************************/
        Font font = new Font("Arial", Font.BOLD, 20);
	    /****************************************************************************/
	    etiqueta = new JLabel("SUELDO MONITORES");
	    etiqueta.setFont(font);
	    etiquetaSueldo = new JLabel("Sueldo Monitor");
	    etiquetaSuplemento = new JLabel("Suplemento");
	    /****************************************************************************/
	    botonHecho = new JButton("Hecho");
        botonCancelar = new JButton("Cancelar");
        /****************************************************************************/
        campoSueldo = new JTextField(20);
        campoSuplemento = new JTextField(20);
        /****************************************************************************/
        promptSuplemento = new TextPrompt("X €/hora", campoSuplemento);
        promptSueldo = new TextPrompt("X €/mes", campoSueldo);
        /****************************************************************************/
        promptSuplemento.changeAlpha(0.5f);
        promptSueldo.changeAlpha(0.5f);
        /****************************************************************************/
        botonHecho.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        botonHecho.setBackground(Color.LIGHT_GRAY);
        
        botonCancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        botonCancelar.setBackground(Color.LIGHT_GRAY);
        /****************************************************************************/
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiqueta, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, etiqueta, 5, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaSueldo, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, etiquetaSueldo, 5, SpringLayout.SOUTH, etiqueta);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, campoSueldo, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, campoSueldo, 5, SpringLayout.SOUTH, etiquetaSueldo);
        
        layout.putConstraint(SpringLayout.WEST, promptSueldo, 10, SpringLayout.WEST, campoSueldo);
        layout.putConstraint(SpringLayout.NORTH, promptSueldo, 0, SpringLayout.NORTH, campoSueldo);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaSuplemento, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, etiquetaSuplemento, 5, SpringLayout.SOUTH, promptSueldo);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, campoSuplemento, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, campoSuplemento, 5, SpringLayout.SOUTH, etiquetaSuplemento);
        
        layout.putConstraint(SpringLayout.WEST, promptSuplemento, 10, SpringLayout.WEST, campoSuplemento);
        layout.putConstraint(SpringLayout.NORTH, promptSuplemento, 0, SpringLayout.NORTH, campoSuplemento);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonHecho, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, botonHecho, 5, SpringLayout.SOUTH, promptSuplemento);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonCancelar, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, botonCancelar, 5, SpringLayout.SOUTH, botonHecho);
        /****************************************************************************/
        this.add(etiqueta);
        this.add(etiquetaSueldo);
        this.add(promptSueldo);
        this.add(campoSueldo);
        this.add(etiquetaSuplemento);
        this.add(promptSuplemento);
        this.add(campoSuplemento);
        this.add(botonHecho);
        this.add(botonCancelar);
        
	}

	/**
	 * GETTER
	 * @return el sueldo del monitor
	 */
	public Double getCampoSueldo()
	{
		return Double.parseDouble(this.campoSueldo.getText());
	}
	
	/**
	 * GETTER
	 * @return el suplemento
	 */
    public Double getCampoSuplemento()
    {
    	return Double.parseDouble(this.campoSuplemento.getText());
    }
	
    /**
     * SETTER
     * @param texto el sueldo del monitor
     */
    public void setCampoSueldo(String texto)
	{
		this.campoSueldo.setText(texto);
		return;
	}
	
    /**
     * SETTER
     * @param texto el suplemento
     */
    public void setCampoSuplemento(String texto)
    {
    	this.campoSuplemento.setText(texto);
    	return;
    }
    
    /**
     * Establece el controlador
     * @param controladorSueldoMonitores el controlador de la ventana
     */
	public void setControlador(ControladorSueldoMonitores controladorSueldoMonitores) {
		botonCancelar.addActionListener(controladorSueldoMonitores);
		botonHecho.addActionListener(controladorSueldoMonitores);
	}    
	
	
}
