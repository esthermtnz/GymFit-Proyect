
package gui.monitor;

import java.awt.*;
import java.util.HashSet;

import javax.swing.*;

import gui.TextPrompt;
import gui.controladores.ControladorLogin;
import gui.controladores.monitor.ControladorCrearPlanPersonalizado;
import aplicacion.actividad.TipoObjetivo;

/**
 * Este fichero muestra todo lo que tiene que ver con la clase CrearPlanPersonalizadoWindow
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class CrearPlanPersonalizadoWindow  extends JPanel {
	private JButton botonCrearPlan;
	private JButton botonCancelar;
	 
	private JTextField campoNombre;
	private JTextField campoDescripcion;
	private JTextField campoRequisitoMin;
	private JTextField campoRequisitoMax;
	
	private SpringLayout layout;
	
	private JLabel etiqueta;
	private JLabel etiquetaNombre;
	private JLabel etiquetaObjetivo;
	private JLabel etiquetaDescripcion;
	private JLabel etiquetaRequisito;
	private JLabel etiquetaRequisitoMin;
	private JLabel etiquetaRequisitoMax;
	
	private TextPrompt promptNombre;
	private TextPrompt promptDescripcion;
    private TextPrompt promptRequisitoMin;
    private TextPrompt promptRequisitoMax;
    
    
    private JComboBox<String> boxobjetivo;
    private JComboBox<String> boxRequisitos;
    
   
	private String[] objetivo = new String[] {"Ganancia de masa muscular", "Perdida de peso", "Rehabilitacion"};
    private String[] requisitos = new String[]{"EDAD", "VETERANIA", "CANCELACIONES"};    
    
    private DefaultComboBoxModel<String> modeloDatosRequisito;
    
    /**
     * Constructor CrearPlanPersonalizadoWindow
     */
	public CrearPlanPersonalizadoWindow() {

        layout = new SpringLayout();
        this.setLayout(layout);
        /****************************************************************************/
        Font font = new Font("Arial", Font.BOLD, 20);
        /****************************************************************************/
        modeloDatosRequisito = new DefaultComboBoxModel<String>(requisitos);
        boxRequisitos = new JComboBox<String>(modeloDatosRequisito);
        boxRequisitos.setPreferredSize(new Dimension(150, 25));
        
        /****************************************************************************/        
        etiqueta = new JLabel("CREAR PLAN PERSONALIZADO");
        etiqueta.setFont(font);
        etiquetaNombre = new JLabel("Nombre");
        etiquetaObjetivo = new JLabel("Objetivo");
        etiquetaDescripcion = new JLabel("Descripción");
        etiquetaRequisito = new JLabel("Tipo Requisito");
        etiquetaRequisitoMin = new JLabel("Requisito minimo");
        etiquetaRequisitoMax = new JLabel("Requisito maximo");
        /****************************************************************************/
        campoNombre = new JTextField(20);
        campoDescripcion = new JTextField(20);
        campoDescripcion.setPreferredSize(new Dimension(20, 70));
        campoRequisitoMin = new JTextField(20);
        campoRequisitoMax = new JTextField(20);
        /****************************************************************************/
        botonCrearPlan = new JButton("Crear Plan");
        botonCancelar = new JButton("Cancelar");   
        /****************************************************************************/
        boxobjetivo = new JComboBox<String>(objetivo);        
        /****************************************************************************/ 
        promptNombre = new TextPrompt("Ganancia Rápida Músculo", campoNombre);
        promptDescripcion = new TextPrompt("Descipción Plan", campoDescripcion);
        promptRequisitoMin = new TextPrompt("Número Minimo", campoRequisitoMin);
        promptRequisitoMax = new TextPrompt("Número Maximo", campoRequisitoMax);
        /****************************************************************************/
        promptNombre.changeAlpha(0.5f);
        promptDescripcion.changeAlpha(0.5f);
        /****************************************************************************/
        botonCrearPlan.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        botonCrearPlan.setBackground(Color.LIGHT_GRAY);
        
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
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaObjetivo, 0, SpringLayout.HORIZONTAL_CENTER, campoDescripcion);
        layout.putConstraint(SpringLayout.NORTH, etiquetaObjetivo, 5, SpringLayout.SOUTH, promptNombre);
        
        layout.putConstraint(SpringLayout.WEST, boxobjetivo, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, boxobjetivo, 5, SpringLayout.SOUTH, etiquetaObjetivo);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaDescripcion, 0, SpringLayout.HORIZONTAL_CENTER, campoNombre);
        layout.putConstraint(SpringLayout.NORTH, etiquetaDescripcion, 5, SpringLayout.SOUTH, boxobjetivo);
        
        layout.putConstraint(SpringLayout.WEST, campoDescripcion, 10, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, campoDescripcion, 5, SpringLayout.SOUTH, etiquetaDescripcion);
        
        layout.putConstraint(SpringLayout.WEST, promptDescripcion, 10, SpringLayout.WEST, campoDescripcion);
        layout.putConstraint(SpringLayout.NORTH, promptDescripcion, 0, SpringLayout.NORTH, campoDescripcion);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaRequisito, 150, SpringLayout.HORIZONTAL_CENTER, etiqueta);
        layout.putConstraint(SpringLayout.NORTH, etiquetaRequisito, 5, SpringLayout.SOUTH, etiqueta);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, boxRequisitos, 0, SpringLayout.HORIZONTAL_CENTER, etiquetaRequisito);
        layout.putConstraint(SpringLayout.NORTH, boxRequisitos, 5, SpringLayout.SOUTH, etiquetaRequisito);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaRequisitoMin, 150, SpringLayout.HORIZONTAL_CENTER, etiqueta);
        layout.putConstraint(SpringLayout.NORTH, etiquetaRequisitoMin, 5, SpringLayout.SOUTH, boxRequisitos);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, campoRequisitoMin, 0, SpringLayout.HORIZONTAL_CENTER, etiquetaRequisito);
        layout.putConstraint(SpringLayout.NORTH, campoRequisitoMin, 5, SpringLayout.SOUTH, etiquetaRequisitoMin);
        
        layout.putConstraint(SpringLayout.WEST, promptRequisitoMin, 10, SpringLayout.WEST, campoRequisitoMin);
        layout.putConstraint(SpringLayout.NORTH, promptRequisitoMin, 0, SpringLayout.NORTH, campoRequisitoMin);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaRequisitoMax, 150, SpringLayout.HORIZONTAL_CENTER, etiqueta);
        layout.putConstraint(SpringLayout.NORTH, etiquetaRequisitoMax, 5, SpringLayout.SOUTH, campoRequisitoMin);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, campoRequisitoMax, 0, SpringLayout.HORIZONTAL_CENTER, etiquetaRequisito);
        layout.putConstraint(SpringLayout.NORTH, campoRequisitoMax, 5, SpringLayout.SOUTH, etiquetaRequisitoMax);
        
        layout.putConstraint(SpringLayout.WEST, promptRequisitoMax, 10, SpringLayout.WEST, campoRequisitoMax);
        layout.putConstraint(SpringLayout.NORTH, promptRequisitoMax, 0, SpringLayout.NORTH, campoRequisitoMax);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonCrearPlan, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, botonCrearPlan, 5, SpringLayout.SOUTH, campoDescripcion);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonCancelar, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, botonCancelar, 5, SpringLayout.SOUTH, botonCrearPlan);
        /****************************************************************************/
        this.add(etiqueta);
        this.add(etiquetaNombre);
        this.add(promptNombre);
        this.add(campoNombre);
        this.add(etiquetaObjetivo);
        this.add(boxobjetivo);
        this.add(etiquetaDescripcion);
        this.add(promptDescripcion);
        this.add(campoDescripcion);
        this.add(etiquetaRequisito);
        this.add(promptRequisitoMin);
        this.add(promptRequisitoMax);
        this.add(boxRequisitos);
        this.add(campoRequisitoMin);
 		this.add(campoRequisitoMax);
        this.add(botonCrearPlan);
        this.add(botonCancelar);
        this.add(etiquetaRequisitoMin);
        this.add(etiquetaRequisitoMax);
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
     * @return el requisito minimo
     */
	public Integer getCampoRequisitoMin()
    {
		return Integer.parseInt(this.campoRequisitoMin.getText());
    }
	
	/**
	 * GETTER
	 * @return el reuqisito maximo
	 */
    public Integer getCampoRequisitoMax()
    {
		return Integer.parseInt(this.campoRequisitoMax.getText());
    }
    
    /**
     * GETTER
     * @return el elemento de la caja de requisitos
     */
    public String getBoxTipoRequisito(){
		return this.boxRequisitos.getSelectedItem().toString();
	}
	
    /**
     * GETTER
     * @return el tipo de objetivo
     */
    public TipoObjetivo getCampoTipoObjetivo(){
		
		if(this.boxobjetivo.getSelectedItem().toString() == "Ganancia de masa muscular")
    	{
			return TipoObjetivo.GANANCIAMUSCULAR;
		}
		else if(this.boxobjetivo.getSelectedItem().toString() == "Perdida de peso")
		{
			return TipoObjetivo.PERDIDAPESO;
		}
		else
		{
			return TipoObjetivo.REHABILITACION;
		}
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
	public void setCampoRequisitoMin(String texto)
    {
		this.campoRequisitoMin.setText(texto);
		return;
    }
    
	/**
	 * SETTER
	 * @param texto el requisito maximo
	 */
    public void setCampoRequisitoMax(String texto)
    {
		this.campoRequisitoMax.setText(texto);
		return;
    }
    
    /**
     * Establece el controlador
     * @param controladorCrearPlanPersonalizado el controlador de la ventana
     */
	public void setControlador(ControladorCrearPlanPersonalizado controladorCrearPlanPersonalizado) {
		botonCrearPlan.addActionListener(controladorCrearPlanPersonalizado);
		botonCancelar.addActionListener(controladorCrearPlanPersonalizado);
		
	}
}