
package gui.administrador;

import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import aplicacion.actividad.sesion.requisito.Requisito;
import aplicacion.actividad.sesion.requisito.*;
import gui.TextPrompt;
import gui.controladores.ControladorLogin;
import gui.controladores.administrador.ControladorCrearActividadGrupal;

/**
 * Este fichero muestra todo lo que tiene que ver con la clase CrearActividadGrupalWindow
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class CrearActividadGrupalWindow  extends JPanel {
	private JButton botonHecho;
	private JButton botonCancelar;
	
	private JTextField campoNombre;
	private JTextField campoDescripcion;
	private JTextField campoRequisitoMin;
	private JTextField campoRequisitoMax;
	
	private SpringLayout layout;
	
	private JLabel etiqueta;
	private JLabel etiquetaNombre;
	private JLabel etiquetaDescripcion;
	private JLabel etiquetaRequisito;
	private JLabel etiquetaMonitor;
	private JLabel etiquetaTipoActividad;
	private JLabel etiquetaReqMin;
	private JLabel etiquetaReqMax;
	
	private JLabel monitor;
	private JLabel sala;
	private JLabel tipo;
	
	private TextPrompt promptNombre;
	private TextPrompt promptDescripcion;
	private TextPrompt promptRequisitoMin;
	private TextPrompt promptRequisitoMax;
	
	private JComboBox<String> boxRequisito;
	private JComboBox<String> boxMonitor;
	private JComboBox<String> boxTipoActividad;
	
	private String[] monitores = new String[]{};
	private String[] salas = new String[]{};
	private String[] tipoActividad = new String[]{};
	
	private DefaultComboBoxModel<String> modeloDatosMonitor;
	private DefaultComboBoxModel<String> modeloDatosTipoAct;
	
	private String[] requisitos = new String[] {"EDAD", "VETERANIA", "CANCELACIONES"};
	
	
	/**
	 * Constructor CrearActividadGrupalWindow
	 */
	public CrearActividadGrupalWindow() {

        layout = new SpringLayout();
        this.setLayout(layout);
        /****************************************************************************/
        Font font = new Font("Arial", Font.BOLD, 20);
        /****************************************************************************/
        etiqueta = new JLabel("CREAR ACTIVIDAD GRUPAL");
        etiqueta.setFont(font);
        etiquetaNombre = new JLabel("Nombre");
        etiquetaDescripcion = new JLabel("Descripcion");
        etiquetaRequisito = new JLabel("Tipo Requisito");
        etiquetaMonitor = new JLabel("Monitor");
        etiquetaTipoActividad = new JLabel("Tipo Actividad");
        etiquetaReqMin = new JLabel("Requisito Minimo");
        etiquetaReqMax = new JLabel("Requisito Maximo");
        
        monitor = new JLabel("");
        sala = new JLabel("");
        tipo = new JLabel("");
        /****************************************************************************/
        campoNombre = new JTextField(20);
        campoDescripcion = new JTextField(20);
        campoDescripcion.setPreferredSize(new Dimension(20, 70));
        campoRequisitoMin = new JTextField(20);
        campoRequisitoMax = new JTextField(20);
        /****************************************************************************/
        botonHecho = new JButton("Hecho");
        botonCancelar = new JButton("Cancelar");
        /****************************************************************************/
        promptNombre = new TextPrompt("Ganancia Rapida Musculo", campoNombre);
        promptDescripcion = new TextPrompt("Sala general de la planta 1.", campoDescripcion);
        promptRequisitoMin = new TextPrompt("Numero Requisito Minimo", campoRequisitoMin);
        promptRequisitoMax = new TextPrompt("Numero Requisito Maximo", campoRequisitoMax);
        /****************************************************************************/
        promptNombre.changeAlpha(0.5f);
        promptDescripcion.changeAlpha(0.5f);
        promptRequisitoMin.changeAlpha(0.5f);
        promptRequisitoMax.changeAlpha(0.5f);
        
        boxRequisito = new JComboBox<String>(requisitos);
        
        modeloDatosMonitor = new DefaultComboBoxModel<String>(monitores);
        boxMonitor = new JComboBox<String>(modeloDatosMonitor);
        
        modeloDatosTipoAct = new DefaultComboBoxModel<String>(tipoActividad);
        boxTipoActividad = new JComboBox<String>(modeloDatosTipoAct);
        /****************************************************************************/
        botonHecho.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        botonHecho.setBackground(Color.LIGHT_GRAY);
        
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
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaDescripcion, 0, SpringLayout.HORIZONTAL_CENTER, campoDescripcion);
        layout.putConstraint(SpringLayout.NORTH, etiquetaDescripcion, 5, SpringLayout.SOUTH, promptNombre);
        
        layout.putConstraint(SpringLayout.WEST, campoDescripcion, 10, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, campoDescripcion, 5, SpringLayout.SOUTH, etiquetaDescripcion);
        
        layout.putConstraint(SpringLayout.WEST, promptDescripcion, 10, SpringLayout.WEST, campoDescripcion);
        layout.putConstraint(SpringLayout.NORTH, promptDescripcion, 0, SpringLayout.NORTH, campoDescripcion);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonHecho, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, botonHecho, 20, SpringLayout.SOUTH, boxMonitor);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonCancelar, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, botonCancelar, 5, SpringLayout.SOUTH, botonHecho);
        
        layout.putConstraint(SpringLayout.WEST, etiquetaRequisito, 220, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, etiquetaRequisito, 5, SpringLayout.SOUTH, etiqueta);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, boxRequisito, 0, SpringLayout.HORIZONTAL_CENTER, etiquetaRequisito);
        layout.putConstraint(SpringLayout.NORTH, boxRequisito, 5, SpringLayout.SOUTH, etiquetaRequisito);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaReqMin, 10, SpringLayout.HORIZONTAL_CENTER, etiquetaRequisito);
        layout.putConstraint(SpringLayout.NORTH, etiquetaReqMin, 5, SpringLayout.SOUTH, boxRequisito);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, campoRequisitoMin, 10, SpringLayout.HORIZONTAL_CENTER, etiquetaRequisito);
        layout.putConstraint(SpringLayout.NORTH, campoRequisitoMin, 5, SpringLayout.SOUTH, etiquetaReqMin);
        
        layout.putConstraint(SpringLayout.WEST, promptRequisitoMin, 10, SpringLayout.WEST, campoRequisitoMin);
        layout.putConstraint(SpringLayout.NORTH, promptRequisitoMin, 0, SpringLayout.NORTH, campoRequisitoMin);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaReqMax, 10, SpringLayout.HORIZONTAL_CENTER, etiquetaRequisito);
        layout.putConstraint(SpringLayout.NORTH, etiquetaReqMax, 5, SpringLayout.SOUTH, campoRequisitoMin);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, campoRequisitoMax, 10, SpringLayout.HORIZONTAL_CENTER, etiquetaRequisito);
        layout.putConstraint(SpringLayout.NORTH, campoRequisitoMax, 5, SpringLayout.SOUTH, etiquetaReqMax);
        
        layout.putConstraint(SpringLayout.WEST, promptRequisitoMax, 10, SpringLayout.WEST, campoRequisitoMax);
        layout.putConstraint(SpringLayout.NORTH, promptRequisitoMax, 0, SpringLayout.NORTH, campoRequisitoMax);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaMonitor, 0, SpringLayout.HORIZONTAL_CENTER, etiquetaRequisito);
        layout.putConstraint(SpringLayout.NORTH, etiquetaMonitor, 5, SpringLayout.SOUTH, campoRequisitoMax);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, boxMonitor, 0, SpringLayout.HORIZONTAL_CENTER, etiquetaRequisito);
        layout.putConstraint(SpringLayout.NORTH, boxMonitor, 5, SpringLayout.SOUTH, etiquetaMonitor);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaTipoActividad, 0, SpringLayout.HORIZONTAL_CENTER, campoDescripcion);
        layout.putConstraint(SpringLayout.NORTH, etiquetaTipoActividad, 5, SpringLayout.SOUTH, campoDescripcion);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, boxTipoActividad, 10, SpringLayout.HORIZONTAL_CENTER, campoDescripcion);
        layout.putConstraint(SpringLayout.NORTH, boxTipoActividad, 5, SpringLayout.SOUTH, etiquetaTipoActividad);
        
        /****************************************************************************/
        this.add(etiqueta);
        this.add(etiquetaNombre);
        this.add(promptNombre);
        this.add(campoNombre);
        this.add(etiquetaDescripcion);
        this.add(promptDescripcion);
        this.add(campoDescripcion);
        this.add(botonHecho);
        this.add(botonCancelar);
        this.add(etiquetaReqMin);
        this.add(promptRequisitoMin);
        this.add(campoRequisitoMin);
        this.add(etiquetaReqMax);
        this.add(promptRequisitoMax);
        this.add(campoRequisitoMax);
        this.add(boxRequisito);
        this.add(etiquetaRequisito);
        this.add(etiquetaMonitor);
        this.add(boxMonitor);
        this.add(etiquetaTipoActividad);
        this.add(boxTipoActividad);

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
	 * @return la descripcion
	 */
    public String getCampoDescripcion()
    {
    	return this.campoDescripcion.getText();
    }
    
    /**
     * GETTER
     * @return el requisito de la actividad
     */
    public String getBoxRequisito()
    {
    	return this.boxRequisito.getSelectedItem().toString();
    }
    
    /**
     * GETTER
     * @return el tipo de actividad
     */
    public String getCampoTipoActividad()
    {
    	return this.boxTipoActividad.getSelectedItem().toString();
    }
    
    /**
     * GETTER
     * @return el requisito minimo
     */
    public Integer getCampoRequisitoMin()
    {
    	return Integer.parseInt(this.campoRequisitoMin.getText());
    }
    
    /**
     * GETTER
     * @return el requisito maximo
     */
    public Integer getCampoRequisitoMax()
    {
    	return Integer.parseInt(this.campoRequisitoMax.getText());
    }
    
    /**
     * GETTER
     * @return el monitor que la impartira
     */
    public String getCampoMonitor()
    {
    	return boxMonitor.getSelectedItem().toString();
    }
    
    
    /**
     * GETTER
     * @return el tipo de requisito
     */
    public Requisito getTipoRequisito()
    {

    	if(this.boxRequisito.getSelectedItem().toString().equals("EDAD"))
    	{
			return new RequisitoEdad(getCampoRequisitoMin(), getCampoRequisitoMax());
		}    	
    	else if(this.boxRequisito.getSelectedItem().toString().equals("VETERANIA"))
    	{
    		return new RequisitoVeterania(getCampoRequisitoMin(), getCampoRequisitoMax());
    	}
    	else if(this.boxRequisito.getSelectedItem().toString().equals("CANCELACIONES"))
    	{
    		return new RequisitoCancelaciones(getCampoRequisitoMin(), getCampoRequisitoMax());
    	}
    	
    	return null;
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
	 * @param texto la descripcion
	 */
    public void setCampoDescripcion(String texto)
    {
    	this.campoDescripcion.setText(texto);
    	return;
    }
    
    /**
     * SETTER
     * @param texto el requisito minimo
     */
    public void setCampoRequisitosMin(String texto)
    {
    	this.campoRequisitoMin.setText(texto);
    	return;
    }
    
    /**
     * SETTER
     * @param texto el requisito maximo
     */
    public void setCampoRequisitosMax(String texto)
    {
    	this.campoRequisitoMax.setText(texto);
    	return;
    }

    /**
     * Establece el controlados
     * @param controladorCrearActividadGrupal el controlador de la ventana
     */
	public void setControlador(ControladorCrearActividadGrupal controladorCrearActividadGrupal) {
		botonHecho.addActionListener(controladorCrearActividadGrupal);
		botonCancelar.addActionListener(controladorCrearActividadGrupal);
		
	}
	
	/**
	 * Actualiza la caja con los monitores del gimnasio
	 * @param nuevosMonitores los nuevos monitores que estaran en la tabla
	 */
	public void updateMonitores(HashSet<String> nuevosMonitores) {
		
	    this.monitores = nuevosMonitores.toArray(new String[0]);
	    modeloDatosMonitor.removeAllElements();
	    for (String monitor : this.monitores) {
	    	modeloDatosMonitor.addElement(monitor);
	    }
	    this.boxMonitor.setModel(modeloDatosMonitor);
	}
	
	/**
	 * Actualiza la caja con los tipo de actividad
	 * @param nuevosTipos los tipos de actividad que se van a introducir en la caja
	 */
	public void updateTipoActividad(HashSet<String> nuevosTipos) {

	    this.tipoActividad = nuevosTipos.toArray(new String[0]);
	    modeloDatosTipoAct.removeAllElements();
	    
	    for (String tipo : this.tipoActividad) {
	    	modeloDatosTipoAct.addElement(tipo);
	    }
	    this.boxTipoActividad.setModel(modeloDatosTipoAct);
	}


}
