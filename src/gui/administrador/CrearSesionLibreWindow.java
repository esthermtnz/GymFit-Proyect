
package gui.administrador;

import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.*;

import gui.TextPrompt;
import gui.controladores.ControladorLogin;
import gui.controladores.administrador.ControladorCrearSesionLibre;

/**
 * Este fichero muestra todo lo que tiene que ver con la clase CrearSesionLibreWindow
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class CrearSesionLibreWindow  extends JPanel {
	private JButton botonHecho;
	private JButton botonCancelar;
	
	private JTextField campoNombre;
	private JTextField campoDescripcion;
	
	private SpringLayout layout;
	
	private JLabel etiqueta;
	private JLabel etiquetaNombre;
	private JLabel etiquetaDescripcion;
	private JLabel etiquetaFecha;
	private JLabel etiquetaHora;
	private JLabel etiquetaDesde;
	private JLabel etiquetaHasta;
	private JLabel etiquetaSala;
	
	private JLabel sala;
	
	private TextPrompt promptNombre;
	private TextPrompt promptDescripcion;
	
	private Integer [] dias= new Integer[] {1,2,3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};
	private Integer[] meses = new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
	private Integer[] anyos = new Integer[] {1930, 1931, 1932, 1933, 1934, 1935, 1936, 1937, 1938, 1939, 1940, 1941, 1942, 1943, 1944, 1945, 1946, 1947, 1948, 1949, 1950, 1951, 1952, 1953, 1954, 1955, 1956, 1957, 1958, 1959, 1960, 1961, 1962, 1963, 1964, 1965, 1966, 1967, 1968, 1969, 1970, 1971, 1972, 1973, 1974, 1975, 1976, 1977, 1978, 1979, 1980, 1981, 1982, 1983, 1984, 1985, 1986, 1987, 1988, 1989, 1990, 1991, 1992, 1993, 1994, 1995, 1996, 1997, 1998, 1999, 2000, 2001, 2002, 2003, 2004, 2005, 2006, 2007, 2008, 2009, 2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017, 2018, 2019, 2020, 2021, 2022, 2023};
	private Integer[] horas = new Integer[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23};
	private Integer[] minutos = new Integer[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59};
	private Integer[] horas2 = new Integer[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23};
	private Integer[] minutos2 = new Integer[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59};
	
	private JComboBox<Integer> boxDias;
	private JComboBox<Integer> boxMeses;
	private JComboBox<Integer> boxAnyos;
	private JComboBox<Integer> boxHora;
	private JComboBox<Integer> boxMinuto;
	private JComboBox<Integer> boxHora2;
	private JComboBox<Integer> boxMinuto2;
	private JComboBox<String> boxSala;
   
	private String[] salas = new String[]{};
	
	private DefaultComboBoxModel<String> modeloDatosSala;
    
	/**
	 * Constructor CrearSesionLibreWindow
	 */
	public CrearSesionLibreWindow() {
    
        layout = new SpringLayout();
        this.setLayout(layout);
        /****************************************************************************/
        Font font = new Font("Arial", Font.BOLD, 20);
        /****************************************************************************/
        etiqueta = new JLabel("CREAR SESION LIBRE");
        etiqueta.setFont(font);
        etiquetaNombre = new JLabel("Nombre");
        etiquetaDescripcion = new JLabel("Descripcion");
        etiquetaFecha = new JLabel("Fecha");
        etiquetaHora = new JLabel("Hora");
        etiquetaDesde = new JLabel("Desde");
        etiquetaHasta = new JLabel("Hasta");
        etiquetaSala = new JLabel("Sala");
        
        sala = new JLabel("");
        /****************************************************************************/
        botonHecho = new JButton("Hecho");
        botonCancelar = new JButton("Cancelar");
        /****************************************************************************/
        campoNombre = new JTextField(20);
        campoDescripcion = new JTextField(20);
        campoDescripcion.setPreferredSize(new Dimension(20, 70));
        /****************************************************************************/
        modeloDatosSala = new DefaultComboBoxModel<String>(salas);
        boxSala = new JComboBox<String>(modeloDatosSala);
        boxSala.setPreferredSize(new Dimension(150, 25));
        /****************************************************************************/
        promptNombre = new TextPrompt("Sesion Libre 1", campoNombre);
        promptDescripcion = new TextPrompt("Descripcion Sesion Libre", campoDescripcion);
        /****************************************************************************/ 
        boxDias = new JComboBox<Integer>(dias);
        boxMeses = new JComboBox<Integer>(meses);
        boxAnyos = new JComboBox<Integer>(anyos);
        boxHora = new JComboBox<Integer>(horas);
        boxMinuto = new JComboBox<Integer>(minutos);
        boxHora2 = new JComboBox<Integer>(horas2);
        boxMinuto2 = new JComboBox<Integer>(minutos2);
        
        boxSala = new JComboBox<String>(salas);
        boxSala.setPreferredSize(new Dimension(150, 25));
        /****************************************************************************/ 
        promptNombre.changeAlpha(0.5f);
        promptDescripcion.changeAlpha(0.5f);
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
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaFecha, 0, SpringLayout.HORIZONTAL_CENTER, etiquetaSala);
        layout.putConstraint(SpringLayout.NORTH, etiquetaFecha, 5, SpringLayout.SOUTH, etiqueta);
        
        layout.putConstraint(SpringLayout.WEST, boxAnyos, 0, SpringLayout.EAST, boxMeses);
        layout.putConstraint(SpringLayout.NORTH, boxAnyos, 5, SpringLayout.SOUTH, etiquetaFecha);
        
        layout.putConstraint(SpringLayout.WEST, boxMeses, 0, SpringLayout.EAST, boxDias);
        layout.putConstraint(SpringLayout.NORTH, boxMeses, 5, SpringLayout.SOUTH, etiquetaFecha);
        
        layout.putConstraint(SpringLayout.WEST, boxDias, 75, SpringLayout.EAST, etiqueta);
        layout.putConstraint(SpringLayout.NORTH, boxDias, 5, SpringLayout.SOUTH, etiquetaFecha);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaHora, 0, SpringLayout.HORIZONTAL_CENTER, etiquetaSala);
        layout.putConstraint(SpringLayout.NORTH, etiquetaHora, 5, SpringLayout.SOUTH, boxDias);
        
        layout.putConstraint(SpringLayout.WEST, boxHora, 95, SpringLayout.EAST, etiqueta);
        layout.putConstraint(SpringLayout.NORTH, boxHora, 5, SpringLayout.SOUTH, etiquetaHora);
        
        layout.putConstraint(SpringLayout.WEST, boxMinuto, 0, SpringLayout.EAST, boxHora);
        layout.putConstraint(SpringLayout.NORTH, boxMinuto, 5, SpringLayout.SOUTH, etiquetaHora);
        
        layout.putConstraint(SpringLayout.EAST, etiquetaDesde, -5,SpringLayout.WEST, boxHora);
        layout.putConstraint(SpringLayout.NORTH, etiquetaDesde, 5, SpringLayout.SOUTH, etiquetaHora);
        
        layout.putConstraint(SpringLayout.WEST, boxHora2, 95, SpringLayout.EAST, etiqueta);
        layout.putConstraint(SpringLayout.NORTH, boxHora2, 10, SpringLayout.SOUTH, etiquetaDesde);
        
        layout.putConstraint(SpringLayout.WEST, boxMinuto2, 0, SpringLayout.EAST, boxHora2);
        layout.putConstraint(SpringLayout.NORTH, boxMinuto2, 10, SpringLayout.SOUTH, etiquetaDesde);
        
        layout.putConstraint(SpringLayout.EAST, etiquetaHasta, -5,SpringLayout.WEST, boxHora2);
        layout.putConstraint(SpringLayout.NORTH, etiquetaHasta, 10, SpringLayout.SOUTH, etiquetaDesde);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaSala, 0, SpringLayout.HORIZONTAL_CENTER, boxSala);
        layout.putConstraint(SpringLayout.NORTH, etiquetaSala, 5, SpringLayout.SOUTH, boxMinuto2);
        
        layout.putConstraint(SpringLayout.WEST, boxSala, 75, SpringLayout.EAST, etiqueta);
        layout.putConstraint(SpringLayout.NORTH, boxSala, 5, SpringLayout.SOUTH, etiquetaSala);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonHecho, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, botonHecho, 5, SpringLayout.SOUTH, boxSala);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonCancelar, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, botonCancelar, 5, SpringLayout.SOUTH, botonHecho);
        /****************************************************************************/
        this.add(etiqueta);
        this.add(etiquetaNombre);
        this.add(promptNombre);
        this.add(campoNombre);
        this.add(etiquetaDescripcion);
        this.add(promptDescripcion);
        this.add(campoDescripcion);
        this.add(etiquetaFecha);
        this.add(boxAnyos);
        this.add(boxMeses);
        this.add(boxDias);
        this.add(etiquetaHora);
        this.add(etiquetaDesde);
        this.add(etiquetaHasta);
        this.add(boxHora);
        this.add(boxMinuto);
        this.add(boxHora2);
        this.add(boxMinuto2);
        this.add(etiquetaSala);
        this.add(boxSala);
        this.add(botonHecho);
        this.add(botonCancelar);

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
	 * @return la sala seleccionada
	 */
    public String getCampoSala()
    {
    	return boxSala.getSelectedItem().toString();
    }
    
    /**
	 * GETTER
	 * @return la fecha
	 */
    public LocalDate getCampoFecha()
    {
    	return LocalDate.of(Integer.parseInt(boxAnyos.getSelectedItem().toString()), Integer.parseInt(boxMeses.getSelectedItem().toString()), Integer.parseInt(boxDias.getSelectedItem().toString()));
    }
    
    /**
	 * GETTER
	 * @return la hora de inicio de la sesion
	 */
    public LocalDateTime getCampoHoraIni()
    {
    	return LocalDateTime.of(getCampoFecha(), LocalTime.of(Integer.parseInt(boxHora.getSelectedItem().toString()), Integer.parseInt(boxMinuto.getSelectedItem().toString())));
    	
    }
    
    /**
	 * GETTER
	 * @return la hora de fin de la sesion
	 */
    public LocalDateTime getCampoHoraFin()
    {
		return LocalDateTime.of(getCampoFecha(), LocalTime.of(Integer.parseInt(boxHora2.getSelectedItem().toString()), Integer.parseInt(boxMinuto2.getSelectedItem().toString())));
    	
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
     * Establece el controlador
     * @param controladorCrearSesionLibre el controlador de la ventana
     */
	public void setControlador(ControladorCrearSesionLibre controladorCrearSesionLibre) {
		botonCancelar.addActionListener(controladorCrearSesionLibre);
		botonHecho.addActionListener(controladorCrearSesionLibre);
		
	}
	
	/**
	 * Actualiza la caja con las salas que hay en el gimnasio
	 * @param nuevasSalas las salas que va  a meter en la caja
	 */
	public void updateSalas(HashSet<String> nuevasSalas) {
		this.salas = nuevasSalas.toArray(new String[0]);
	    modeloDatosSala.removeAllElements();
	    
	    for (String sala : this.salas) {
	    	modeloDatosSala.addElement(sala);
	    }
	    this.boxSala.setModel(modeloDatosSala);

	}

}
