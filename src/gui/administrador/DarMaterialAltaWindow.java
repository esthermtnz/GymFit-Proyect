package gui.administrador;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.util.HashSet;
import java.time.LocalDate;

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
import gui.controladores.administrador.ControladorDarMaterialAlta;

/**
 * Este fichero muestra todo lo que tiene que ver con la clase DarMaterialAltaWindow
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class DarMaterialAltaWindow extends JPanel{
	private JButton botonDarAlta;
	private JButton botonAtras;
	
	private JTextField campoNumUnidades;
	private JTextField campoDescripcion;
	private JTextField campoPrecio;
	
	private SpringLayout layout;
	
	private JLabel etiqueta;
	private JLabel etiquetaNumUnidades;
	private JLabel etiquetaDescripcion;
	private JLabel etiquetaPrecio;
	private JLabel etiquetaFecha;
	
	private Integer[] dias = new Integer[] {1,2,3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};
	private Integer[] meses = new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
	private Integer[] anyos = new Integer[] {1930, 1931, 1932, 1933, 1934, 1935, 1936, 1937, 1938, 1939, 1940, 1941, 1942, 1943, 1944, 1945, 1946, 1947, 1948, 1949, 1950, 1951, 1952, 1953, 1954, 1955, 1956, 1957, 1958, 1959, 1960, 1961, 1962, 1963, 1964, 1965, 1966, 1967, 1968, 1969, 1970, 1971, 1972, 1973, 1974, 1975, 1976, 1977, 1978, 1979, 1980, 1981, 1982, 1983, 1984, 1985, 1986, 1987, 1988, 1989, 1990, 1991, 1992, 1993, 1994, 1995, 1996, 1997, 1998, 1999, 2000, 2001, 2002, 2003, 2004, 2005, 2006, 2007, 2008, 2009, 2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017, 2018, 2019, 2020, 2021, 2022, 2023};
	
	
	private JComboBox<Integer> boxDias;
	private JComboBox<Integer> boxMeses;
	private JComboBox<Integer> boxAnyos;
	
	private TextPrompt promptNumUnidades;
	private TextPrompt promptDescripcion;
	private TextPrompt promptPrecio;
	
	
	/**
	 * Constructor DarMaterialAltaWindow
	 */
	public DarMaterialAltaWindow() {

        layout = new SpringLayout();
        this.setLayout(layout);
        /****************************************************************************/
        Font font = new Font("Arial", Font.BOLD, 20);
        /****************************************************************************/
        etiqueta = new JLabel("DAR MATERIAL DE ALTA");
        etiqueta.setFont(font);
        etiquetaNumUnidades = new JLabel("Numero Unidades");
        etiquetaDescripcion = new JLabel("Descripcion");
        etiquetaPrecio = new JLabel("Precio");
        etiquetaFecha = new JLabel("Fecha");
        
        /****************************************************************************/
        campoNumUnidades = new JTextField(20);
        campoDescripcion = new JTextField(20);
        campoPrecio = new JTextField(20);
        campoDescripcion.setPreferredSize(new Dimension(20, 70));
        /****************************************************************************/
        botonDarAlta = new JButton("Dar de alta");
        botonAtras = new JButton("Atras");
        /****************************************************************************/
        boxDias = new JComboBox<Integer>(dias);        
        boxMeses = new JComboBox<Integer>(meses);        
        boxAnyos = new JComboBox<Integer>(anyos);     
	    /****************************************************************************/
        promptNumUnidades = new TextPrompt("10", campoNumUnidades);
        promptDescripcion = new TextPrompt("Mancuernas", campoDescripcion);
        promptPrecio = new TextPrompt("15.99", campoPrecio);
        /****************************************************************************/
        promptNumUnidades.changeAlpha(0.5f);
        promptDescripcion.changeAlpha(0.5f);
        promptPrecio.changeAlpha(0.5f);
        
        /****************************************************************************/
        botonDarAlta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        botonDarAlta.setBackground(Color.LIGHT_GRAY);
        
        botonAtras.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        botonAtras.setBackground(Color.LIGHT_GRAY);
        /****************************************************************************/
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiqueta, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, etiqueta, 5, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaNumUnidades, 0, SpringLayout.HORIZONTAL_CENTER, campoNumUnidades);
        layout.putConstraint(SpringLayout.NORTH, etiquetaNumUnidades, 5, SpringLayout.SOUTH, etiqueta);
        
        layout.putConstraint(SpringLayout.WEST, campoNumUnidades, 10, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, campoNumUnidades, 5, SpringLayout.SOUTH, etiquetaNumUnidades);
        
        layout.putConstraint(SpringLayout.WEST, promptNumUnidades, 10, SpringLayout.WEST, campoNumUnidades);
        layout.putConstraint(SpringLayout.NORTH, promptNumUnidades, 0, SpringLayout.NORTH, campoNumUnidades);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaDescripcion, 0, SpringLayout.HORIZONTAL_CENTER, campoDescripcion);
        layout.putConstraint(SpringLayout.NORTH, etiquetaDescripcion, 5, SpringLayout.SOUTH, promptNumUnidades);
        
        layout.putConstraint(SpringLayout.WEST, campoDescripcion, 10, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, campoDescripcion, 5, SpringLayout.SOUTH, etiquetaDescripcion);
        
        layout.putConstraint(SpringLayout.WEST, promptDescripcion, 10, SpringLayout.WEST, campoDescripcion);
        layout.putConstraint(SpringLayout.NORTH, promptDescripcion, 0, SpringLayout.NORTH, campoDescripcion);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaFecha, 0, SpringLayout.HORIZONTAL_CENTER, etiqueta);
        layout.putConstraint(SpringLayout.NORTH, etiquetaFecha, 5, SpringLayout.SOUTH, etiqueta);
        
        layout.putConstraint(SpringLayout.WEST, etiquetaPrecio, 220, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, etiquetaPrecio, 5, SpringLayout.SOUTH, etiqueta);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, campoPrecio, 10, SpringLayout.HORIZONTAL_CENTER, etiquetaPrecio);
        layout.putConstraint(SpringLayout.NORTH, campoPrecio, 5, SpringLayout.SOUTH, etiquetaPrecio);
        
        layout.putConstraint(SpringLayout.WEST, promptPrecio, 10, SpringLayout.WEST, campoPrecio);
        layout.putConstraint(SpringLayout.NORTH, promptPrecio, 0, SpringLayout.NORTH, campoPrecio);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaFecha, 0, SpringLayout.HORIZONTAL_CENTER, etiquetaPrecio);
        layout.putConstraint(SpringLayout.NORTH, etiquetaFecha, 5, SpringLayout.SOUTH, campoPrecio);
        
        layout.putConstraint(SpringLayout.WEST, boxAnyos, 0, SpringLayout.EAST, boxMeses);
        layout.putConstraint(SpringLayout.NORTH, boxAnyos, 5, SpringLayout.SOUTH, etiquetaFecha);
        
        layout.putConstraint(SpringLayout.WEST, boxMeses, 0, SpringLayout.EAST, boxDias);
        layout.putConstraint(SpringLayout.NORTH, boxMeses, 5, SpringLayout.SOUTH, etiquetaFecha);
        
        layout.putConstraint(SpringLayout.WEST, boxDias, -75, SpringLayout.HORIZONTAL_CENTER, etiquetaFecha);
        layout.putConstraint(SpringLayout.NORTH, boxDias, 5, SpringLayout.SOUTH, etiquetaFecha);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonDarAlta, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, botonDarAlta, 15, SpringLayout.SOUTH, campoDescripcion);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonAtras, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, botonAtras, 5, SpringLayout.SOUTH, botonDarAlta);
          
       	/****************************************************************************/
        this.add(etiqueta);
        this.add(etiquetaNumUnidades);
        this.add(promptNumUnidades);
        this.add(campoNumUnidades);
        this.add(etiquetaDescripcion);
        this.add(promptDescripcion);
        this.add(campoDescripcion);
        this.add(etiquetaPrecio);
        this.add(promptPrecio);
        this.add(campoPrecio);
        this.add(boxAnyos);
	    this.add(boxMeses);
	    this.add(boxDias);
        this.add(botonDarAlta);
        this.add(botonAtras);
        this.add(etiquetaFecha);

    }
	
	/**
	 * GETTER
	 * @return el numero de unidades
	 */
	public Integer getCampoNumUnidades()
	{
		return Integer.parseInt(this.campoNumUnidades.getText());
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
     * @return el precio por unidad
     */
      public Double getCampoPrecio()
    {
    	return Double.parseDouble(this.campoPrecio.getText());
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
      * SETTER
      * @param texto el numero de unidades
      */
    public void setCampoNumUnidades(String texto)
	{
		this.campoNumUnidades.setText(texto);
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
     * @param texto el precio por unidad
     */
    public void setCampoPrecio(String texto)
    {
    	this.campoPrecio.setText(texto);
    	return;
    }

    /**
     * Establece el controlador
     * @param controladorDarMaterialAlta el controlador de la ventana
     */
	public void setControlador(ControladorDarMaterialAlta controladorDarMaterialAlta) {
		botonAtras.addActionListener(controladorDarMaterialAlta);
		botonDarAlta.addActionListener(controladorDarMaterialAlta);
		
	}

}
