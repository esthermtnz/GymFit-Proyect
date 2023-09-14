package gui.cliente;

import java.awt.*;
import java.util.HashSet;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import aplicacion.actividad.TipoActividad;
import aplicacion.usuario.tarifa.TipoTarifaPlana;
import gui.TextPrompt;
import gui.controladores.ControladorRegistroUsuarios;
import gui.controladores.cliente.ControladorRenovarTarifa;

public class RenovarTarifaWindow extends JPanel{
	private JButton botonRenovar;
	private JButton botonCancelar;
	
	private JComboBox<String> boxTipoTarifa;
	private JComboBox<String> boxTipoActividadGrupal;
	
	private SpringLayout layout;
	
	private JTextField campoUsuario;
	private JPasswordField campoContrasenya;
	
	private JLabel etiquetaActividadGrupal;
	private JLabel etiquetaTarifa;
	private JLabel etiqueta;
	private JLabel etiquetaUsuario;
	private JLabel etiquetaContrasenya;
	
	private TextPrompt promptUsuario;
	private TextPrompt promptContrasenya;
	private String [] tipoActividad = new String [] {};
	private DefaultComboBoxModel<String> modeloDatosActividad;
	public RenovarTarifaWindow() {
		// Caja tipo actividad
		//Elegir tipo tarifa
		layout = new SpringLayout();
        this.setLayout(layout);
        /****************************************************************************/
        Font font = new Font("Arial", Font.BOLD, 20);
        /****************************************************************************/
        etiqueta = new JLabel("RENOVAR TARIFA");
        etiqueta.setFont(font);
        etiquetaTarifa = new JLabel("Tipo de Tarifa");
        etiquetaActividadGrupal = new JLabel("Actividad Grupal (Tarifa Plana)");
        etiquetaUsuario = new JLabel("Usuario");
        etiquetaContrasenya = new JLabel("Contraseña");
        
        String[] tipoTarifa = {"Tarifa Uso", "Tarifa Plana (ANUAL)", "Tarifa Plana (MENSUAL)", "Tarifa Plana (TRIMESTRAL)"};
        boxTipoTarifa = new JComboBox<String>(tipoTarifa);
        modeloDatosActividad = new DefaultComboBoxModel<String>(tipoActividad);
        boxTipoActividadGrupal = new JComboBox<String>(modeloDatosActividad);
        /****************************************************************************/  
        campoUsuario = new JTextField(20);
        campoContrasenya = new JPasswordField(20);
        /****************************************************************************/      
        botonRenovar = new JButton("Renovar");
        botonRenovar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        botonRenovar.setBackground(Color.LIGHT_GRAY);
        
        botonCancelar = new JButton("Cancelar");
        botonCancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        botonCancelar.setBackground(Color.LIGHT_GRAY);
        /****************************************************************************/
        promptUsuario = new TextPrompt("Nombre Usuario", campoUsuario);
        promptContrasenya = new TextPrompt("Contraseña", campoContrasenya);
        /****************************************************************************/ 
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiqueta, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, etiqueta, 5, SpringLayout.NORTH, this);
        
        layout.putConstraint(SpringLayout.WEST, etiquetaTarifa, 75, SpringLayout.EAST, etiqueta);
        layout.putConstraint(SpringLayout.NORTH, etiquetaTarifa, 5, SpringLayout.SOUTH, etiqueta);
        
        layout.putConstraint(SpringLayout.WEST, boxTipoTarifa, 75, SpringLayout.EAST, etiqueta);
        layout.putConstraint(SpringLayout.NORTH, boxTipoTarifa, 5, SpringLayout.SOUTH, etiquetaTarifa);
        
        layout.putConstraint(SpringLayout.WEST, etiquetaActividadGrupal, 75, SpringLayout.EAST, etiqueta);
        layout.putConstraint(SpringLayout.NORTH, etiquetaActividadGrupal, 5, SpringLayout.SOUTH, boxTipoTarifa);
        
        layout.putConstraint(SpringLayout.WEST, boxTipoActividadGrupal, 75, SpringLayout.EAST, etiqueta);
        layout.putConstraint(SpringLayout.NORTH, boxTipoActividadGrupal, 5, SpringLayout.SOUTH, etiquetaActividadGrupal);
        
        layout.putConstraint(SpringLayout.WEST, etiquetaUsuario, 95, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, etiquetaUsuario, 5, SpringLayout.SOUTH, etiqueta);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, campoUsuario, 0, SpringLayout.HORIZONTAL_CENTER, etiquetaUsuario);
        layout.putConstraint(SpringLayout.NORTH, campoUsuario, 5, SpringLayout.SOUTH, etiquetaUsuario);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, promptUsuario, 10, SpringLayout.HORIZONTAL_CENTER, campoUsuario);
        layout.putConstraint(SpringLayout.NORTH, promptUsuario, 0, SpringLayout.NORTH, campoUsuario);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaContrasenya, 0, SpringLayout.HORIZONTAL_CENTER, etiquetaUsuario);
        layout.putConstraint(SpringLayout.NORTH, etiquetaContrasenya, 5, SpringLayout.SOUTH, campoUsuario);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, campoContrasenya, 0, SpringLayout.HORIZONTAL_CENTER, etiquetaUsuario);
        layout.putConstraint(SpringLayout.NORTH, campoContrasenya, 5, SpringLayout.SOUTH, etiquetaContrasenya);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, promptContrasenya, 10, SpringLayout.HORIZONTAL_CENTER, campoContrasenya);
        layout.putConstraint(SpringLayout.NORTH, promptContrasenya, 0, SpringLayout.NORTH, campoContrasenya);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonRenovar, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.SOUTH, botonRenovar, -5, SpringLayout.NORTH, botonCancelar);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonCancelar, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.SOUTH, botonCancelar, -10, SpringLayout.SOUTH, this);
        /****************************************************************************/  
        this.add(etiqueta);
        this.add(etiquetaTarifa);
        this.add(boxTipoTarifa);
        this.add(etiquetaActividadGrupal);
        this.add(boxTipoActividadGrupal);
        this.add(promptUsuario);
        this.add(etiquetaUsuario);
        this.add(campoUsuario);
        this.add(promptContrasenya);
        this.add(etiquetaContrasenya);
        this.add(campoContrasenya);
        this.add(botonCancelar);
        this.add(botonRenovar);
        
	}
	/*
	 * GETTER
	 * @return el usuario
	 */
	public String getCampoUsuario ()
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
     * @return la tarifa
     */
    public String getTipoTarifa()
    {
    	return boxTipoTarifa.getSelectedItem().toString();
    }
    
    /**
     * Obtener el tipo de tarifa
     * @param texto el tipo de tarifa
     * @return el tipo de tarifa
     */
    public TipoTarifaPlana tipoTarifaPlana(String texto)
    {
    	if(texto.contains("ANUAL"))
    		return TipoTarifaPlana.ANUAL;
    	else if(texto.contains("MENSUAL"))
    		return TipoTarifaPlana.MENSUAL;
    	else if(texto.contains("TRIMESTRAL"))
    		return TipoTarifaPlana.TRIMESTRAL;
    		
    	return null;
    }
    
    /**
     * GETTER
     * @return la actividad grupal
     */
    public String getActividadGrupal()
    {
    	return boxTipoActividadGrupal.getSelectedItem().toString();
    }
    
    /**
     * GETTER
     * @param texto el tipo de actividad
     * @return el tipo de actividad
     */
    public TipoActividad tipoActividad(String texto)
    {
    	if(texto == null)
    			return null;
    	
    	TipoActividad tipoActividad = new TipoActividad(texto); 
    
    	return tipoActividad;

    }
    
    /**
     * SETTER
     * @param texto el usuario
     */
	public void setCampoUsuario (String texto)
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
     * Establece el controlador
     * @param controladorRenovarTarifa el controlador de la window
     */
	public void setControlador(ControladorRenovarTarifa controladorRenovarTarifa) {
		botonCancelar.addActionListener(controladorRenovarTarifa);
		botonRenovar.addActionListener(controladorRenovarTarifa);
		boxTipoTarifa.addActionListener(controladorRenovarTarifa);
		boxTipoActividadGrupal.addActionListener(controladorRenovarTarifa);
	}

	/**
	 * Eliminar el contenido
	 */
	public void updateContenidoElim1() {
	    this.remove(etiquetaActividadGrupal);
	    this.remove(boxTipoActividadGrupal);

	    this.revalidate();
	    this.repaint();
	}
	
	/**
	 * Actualizar el contenido
	 */
	public void updateContenido2()
	{
		this.updateContenidoElim1();
		
		layout.putConstraint(SpringLayout.WEST, etiquetaActividadGrupal, 75, SpringLayout.EAST, etiqueta);
        layout.putConstraint(SpringLayout.NORTH, etiquetaActividadGrupal, 5, SpringLayout.SOUTH, boxTipoTarifa);
        
        layout.putConstraint(SpringLayout.WEST, boxTipoActividadGrupal, 75, SpringLayout.EAST, etiqueta);
        layout.putConstraint(SpringLayout.NORTH, boxTipoActividadGrupal, 5, SpringLayout.SOUTH, etiquetaActividadGrupal);
        
        this.add(etiquetaActividadGrupal);
        this.add(boxTipoActividadGrupal);
	}
	
	/**
	 * Actualizar la caja
	 * @param tipoActividades la lista con el tipo de actividades
	 */
	public void updateTipoActividad(HashSet<String> tipoActividades) {
		this.tipoActividad = tipoActividades.toArray(new String[0]);
	    modeloDatosActividad=null;
	    modeloDatosActividad = new DefaultComboBoxModel<String>(tipoActividad);
		this.boxTipoActividadGrupal.setModel(modeloDatosActividad);
	}

}
