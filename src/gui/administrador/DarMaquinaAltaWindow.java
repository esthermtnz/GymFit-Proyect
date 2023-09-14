package gui.administrador;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.time.LocalDate;
import java.util.HashSet;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import aplicacion.actividad.sesion.requisito.Requisito;
import aplicacion.actividad.sesion.requisito.RequisitoCancelaciones;
import aplicacion.actividad.sesion.requisito.RequisitoEdad;
import aplicacion.actividad.sesion.requisito.RequisitoVeterania;
import gui.TextPrompt;
import gui.controladores.administrador.ControladorCrearActividadGrupal;
import gui.controladores.administrador.ControladorDarMaquinaAlta;

/**
 * Este fichero muestra todo lo que tiene que ver con la clase DarMaquinaAltaWindow
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class DarMaquinaAltaWindow extends JPanel{
	private JButton botonDarAlta;
	private JButton botonAtras;
	
	private JTextField campoTipo;
	private JTextField campoDescripcion;
	private JTextField campoPrecio;
	private JTextField campoMarca;
	
	private SpringLayout layout;
	
	private JLabel etiqueta;
	private JLabel etiquetaTipo;
	private JLabel etiquetaDescripcion;
	private JLabel etiquetaPrecio;
	private JLabel etiquetaPropetario;
	private JLabel etiquetaMarca;
	private JLabel etiquetaFecha;
	
	private JLabel propietario;
	
	private TextPrompt promptTipo;
	private TextPrompt promptDescripcion;
	private TextPrompt promptPrecio;
	private TextPrompt promptMarca;
	
	private JComboBox<Integer> boxDias;
	private JComboBox<Integer> boxMeses;
	private JComboBox<Integer> boxAnyos;
	
	private JComboBox<String> boxPropietario;
	
	private String[] propietarios = new String[]{"Propiedad", "Alquiler"};
	
	private DefaultComboBoxModel<String> modeloDatosPropietario;
	
	private Integer[] dias = new Integer[] {1,2,3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};
	private Integer[] meses = new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
	private Integer[] anyos = new Integer[] {1930, 1931, 1932, 1933, 1934, 1935, 1936, 1937, 1938, 1939, 1940, 1941, 1942, 1943, 1944, 1945, 1946, 1947, 1948, 1949, 1950, 1951, 1952, 1953, 1954, 1955, 1956, 1957, 1958, 1959, 1960, 1961, 1962, 1963, 1964, 1965, 1966, 1967, 1968, 1969, 1970, 1971, 1972, 1973, 1974, 1975, 1976, 1977, 1978, 1979, 1980, 1981, 1982, 1983, 1984, 1985, 1986, 1987, 1988, 1989, 1990, 1991, 1992, 1993, 1994, 1995, 1996, 1997, 1998, 1999, 2000, 2001, 2002, 2003, 2004, 2005, 2006, 2007, 2008, 2009, 2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017, 2018, 2019, 2020, 2021, 2022, 2023};
	
	
	/**
	 * Constructor DarMaquinaAltaWindow
	 */
	public DarMaquinaAltaWindow() {

        layout = new SpringLayout();
        this.setLayout(layout);
        /****************************************************************************/
        Font font = new Font("Arial", Font.BOLD, 20);
        /****************************************************************************/
        etiqueta = new JLabel("DAR MAQUINA DE ALTA");
        etiqueta.setFont(font);
        etiquetaTipo = new JLabel("Tipo");
        etiquetaDescripcion = new JLabel("Descripcion");
        etiquetaPrecio = new JLabel("Precio");
        etiquetaPropetario = new JLabel("Propietario");
        etiquetaMarca = new JLabel("Marca");
        etiquetaFecha = new JLabel("Fecha");
        
        propietario = new JLabel("");
        /****************************************************************************/
        boxDias = new JComboBox<Integer>(dias);        
        boxMeses = new JComboBox<Integer>(meses);        
        boxAnyos = new JComboBox<Integer>(anyos);
        /****************************************************************************/
        campoTipo = new JTextField(20);
        campoDescripcion = new JTextField(20);
        campoDescripcion.setPreferredSize(new Dimension(20, 70));
        campoMarca = new JTextField(20);
        campoPrecio = new JTextField(20);
        /****************************************************************************/
        botonDarAlta = new JButton("Dar de Alta");
        botonAtras = new JButton("Atras");
        /****************************************************************************/
        promptTipo = new TextPrompt("Correr", campoTipo);
        promptDescripcion = new TextPrompt("Quemar calorias", campoDescripcion);
        promptPrecio = new TextPrompt("23", campoPrecio);
        promptMarca = new TextPrompt("Samsung", campoMarca);
        /****************************************************************************/
        promptTipo.changeAlpha(0.5f);
        promptDescripcion.changeAlpha(0.5f);
        promptPrecio.changeAlpha(0.5f);
        promptMarca.changeAlpha(0.5f);
        
        modeloDatosPropietario = new DefaultComboBoxModel<String>(propietarios);
        boxPropietario = new JComboBox<String>(modeloDatosPropietario);
        
        /****************************************************************************/
        botonDarAlta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        botonDarAlta.setBackground(Color.LIGHT_GRAY);
        
        botonAtras.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        botonAtras.setBackground(Color.LIGHT_GRAY);
        /****************************************************************************/
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiqueta, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, etiqueta, 5, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaTipo, 10, SpringLayout.HORIZONTAL_CENTER, campoTipo);
        layout.putConstraint(SpringLayout.NORTH, etiquetaTipo, 5, SpringLayout.SOUTH, etiqueta);
        
        layout.putConstraint(SpringLayout.WEST, campoTipo, 10, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, campoTipo, 5, SpringLayout.SOUTH, etiquetaTipo);
        
        layout.putConstraint(SpringLayout.WEST, promptTipo, 10, SpringLayout.WEST, campoTipo);
        layout.putConstraint(SpringLayout.NORTH, promptTipo, 0, SpringLayout.NORTH, campoTipo);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaDescripcion, 0, SpringLayout.HORIZONTAL_CENTER, campoDescripcion);
        layout.putConstraint(SpringLayout.NORTH, etiquetaDescripcion, 5, SpringLayout.SOUTH, promptTipo);
        
        layout.putConstraint(SpringLayout.WEST, campoDescripcion, 10, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, campoDescripcion, 5, SpringLayout.SOUTH, etiquetaDescripcion);
        
        layout.putConstraint(SpringLayout.WEST, promptDescripcion, 10, SpringLayout.WEST, campoDescripcion);
        layout.putConstraint(SpringLayout.NORTH, promptDescripcion, 0, SpringLayout.NORTH, campoDescripcion);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonDarAlta, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, botonDarAlta, 20, SpringLayout.SOUTH, campoMarca);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonAtras, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, botonAtras, 5, SpringLayout.SOUTH, botonDarAlta);
        
        layout.putConstraint(SpringLayout.WEST, etiquetaPrecio, 220, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, etiquetaPrecio, 5, SpringLayout.SOUTH, etiqueta);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, campoPrecio, 0, SpringLayout.HORIZONTAL_CENTER, etiquetaPrecio);
        layout.putConstraint(SpringLayout.NORTH, campoPrecio, 5, SpringLayout.SOUTH, etiquetaPrecio);
        
        layout.putConstraint(SpringLayout.WEST, promptPrecio, 0, SpringLayout.WEST, campoPrecio);
        layout.putConstraint(SpringLayout.NORTH, promptPrecio, 0, SpringLayout.NORTH, campoPrecio);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaFecha, 0, SpringLayout.HORIZONTAL_CENTER, etiquetaPrecio);
        layout.putConstraint(SpringLayout.NORTH, etiquetaFecha, 5, SpringLayout.SOUTH, campoPrecio);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaPropetario, 0, SpringLayout.HORIZONTAL_CENTER, etiquetaPrecio);
        layout.putConstraint(SpringLayout.NORTH, etiquetaPropetario, 5, SpringLayout.SOUTH, boxDias);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, boxPropietario, 0, SpringLayout.HORIZONTAL_CENTER, etiquetaPrecio);
        layout.putConstraint(SpringLayout.NORTH, boxPropietario, 5, SpringLayout.SOUTH, etiquetaPropetario);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaMarca, 0, SpringLayout.HORIZONTAL_CENTER, campoDescripcion);
        layout.putConstraint(SpringLayout.NORTH, etiquetaMarca, 5, SpringLayout.SOUTH, campoDescripcion);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, campoMarca, 10, SpringLayout.HORIZONTAL_CENTER, campoDescripcion);
        layout.putConstraint(SpringLayout.NORTH, campoMarca, 5, SpringLayout.SOUTH, etiquetaMarca);
        
        layout.putConstraint(SpringLayout.WEST, boxAnyos, 0, SpringLayout.EAST, boxMeses);
        layout.putConstraint(SpringLayout.NORTH, boxAnyos, 5, SpringLayout.SOUTH, etiquetaFecha);
        
        layout.putConstraint(SpringLayout.WEST, boxMeses, 0, SpringLayout.EAST, boxDias);
        layout.putConstraint(SpringLayout.NORTH, boxMeses, 5, SpringLayout.SOUTH, etiquetaFecha);
        
        layout.putConstraint(SpringLayout.WEST, boxDias, -75, SpringLayout.HORIZONTAL_CENTER, etiquetaFecha);
        layout.putConstraint(SpringLayout.NORTH, boxDias, 5, SpringLayout.SOUTH, etiquetaFecha);
        
        layout.putConstraint(SpringLayout.WEST, promptMarca, 0, SpringLayout.WEST, campoMarca);
        layout.putConstraint(SpringLayout.NORTH, promptMarca, 0, SpringLayout.NORTH, campoMarca);
        
        /****************************************************************************/
        this.add(etiqueta);
        this.add(promptTipo);
        this.add(campoTipo);
        this.add(etiquetaTipo);
        this.add(etiquetaDescripcion);
        this.add(promptDescripcion);
        this.add(campoDescripcion);
        this.add(botonDarAlta);
        this.add(botonAtras);
        this.add(etiquetaFecha);
        this.add(boxDias);
        this.add(boxMeses);
        this.add(boxAnyos);
        this.add(promptPrecio);
        this.add(campoPrecio);
        this.add(etiquetaPrecio);
        this.add(etiquetaPropetario);
        this.add(boxPropietario);
        this.add(etiquetaMarca);
        this.add(promptMarca);
        this.add(campoMarca);
        

    }
	
	/**
	 * GETTER
	 * @return el tipo de maquina
	 */
	public String getCampoTipo()
	{
		return this.campoTipo.getText();
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
     * @return la marca
     */
    public String getCampoMarca()
    {
    	return this.campoMarca.getText();
    }
    
    /**
     * GETTER
     * @return la fecha de compra
     */
    public LocalDate getCampoFecha()
    {
    	return LocalDate.of(Integer.parseInt(boxAnyos.getSelectedItem().toString()), Integer.parseInt(boxMeses.getSelectedItem().toString()), Integer.parseInt(boxDias.getSelectedItem().toString()));
    }
    
    /**
     * GETTER
     * @return el precio
     */
    public Double getCampoPrecio()
    {
    	return Double.parseDouble(this.campoPrecio.getText());
    }
    
    /**
     * GETTER
     * @return si es del gimnasio
     */
    public String getCampoPropietario()
    {
    	return boxPropietario.getSelectedItem().toString();
    }
    
    /**
     * GETTER
     * @return la caja con alquilada/propietario
     */
    public String getBoxPropietario()
    {
    	return this.boxPropietario.getSelectedItem().toString();
    }
    
    
    /**
     * SETTER
     * @param texto el tipo de maquina
     */
    public void setCampoTipo(String texto)
	{
		this.campoTipo.setText(texto);
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
     * @param texto el precio
     */
    public void setCampoPrecio(String texto)
    {
    	this.campoPrecio.setText(texto);
    	return;
    }
    
    /**
     * SETTER
     * @param texto la marca
      */
    public void setCampoMarca(String texto)
    {
    	this.campoMarca.setText(texto);
    	return;
    }
    

    /**
     * Establece el controlador
     * @param controladorDarMaquinaAlta el controlador de la ventana
     */
	public void setControlador(ControladorDarMaquinaAlta controladorDarMaquinaAlta) {
		botonDarAlta.addActionListener(controladorDarMaquinaAlta);
		botonAtras.addActionListener(controladorDarMaquinaAlta);
		
	}
	

}
