package gui.administrador;

import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import aplicacion.*;
import aplicacion.actividad.Actividad;
import aplicacion.actividad.ActividadGrupal;
import aplicacion.actividad.PlanPersonalizado;
import aplicacion.actividad.sesion.*;
import gui.controladores.ControladorLogin;
import gui.controladores.administrador.ControladorBeneficiosGimnasio;
/**
 * Este fichero muestra todo lo que tiene que ver con la clase BeneficiosGimnasioWindow
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class BeneficiosGimnasioWindow extends JPanel{
	private JButton botonAtras;
	private JButton botonBeneficio;
	private JButton botonUpdate;
	
	private JPanel panel2;
	
	private SpringLayout layout;
	
	private JLabel et;
	private JLabel etiqueta;
	private JLabel etiquetaNombre;
	private JLabel etiquetaBeneficio;
	
	private JLabel modeloDatosBeneficioCalculado;
	
	private JComboBox<Integer> boxMeses;
	private JComboBox<Integer> boxAnyos;
	
	private DefaultTableModel modeloDatos;
	
	private HashSet<Reserva> reservas;
	
	private String[] titulos = new String[] {"Nombre", "Tipo Objetivo", "Hora Inicio", "Hora Fin", "Fecha", "Sala", "Aforo", "Ingresos"};
	private Object[][] filas = new Object [] [] {{}, {}, {}, {}, {}, {}, {}, {}};;
	
	private JTable tabla;
	
	private Integer[] meses = new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
	private Integer[] anyos = new Integer[] {1930, 1931, 1932, 1933, 1934, 1935, 1936, 1937, 1938, 1939, 1940, 1941, 1942, 1943, 1944, 1945, 1946, 1947, 1948, 1949, 1950, 1951, 1952, 1953, 1954, 1955, 1956, 1957, 1958, 1959, 1960, 1961, 1962, 1963, 1964, 1965, 1966, 1967, 1968, 1969, 1970, 1971, 1972, 1973, 1974, 1975, 1976, 1977, 1978, 1979, 1980, 1981, 1982, 1983, 1984, 1985, 1986, 1987, 1988, 1989, 1990, 1991, 1992, 1993, 1994, 1995, 1996, 1997, 1998, 1999, 2000, 2001, 2002, 2003, 2004, 2005, 2006, 2007, 2008, 2009, 2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017, 2018, 2019, 2020, 2021, 2022, 2023};
	
	private JScrollPane scroll;
    
	/**
	 * Contructor de BeneficiosGimnasioWindow
	 */
	public BeneficiosGimnasioWindow()
	{	
		panel2 = new JPanel();
	    layout = new SpringLayout();
	    this.setLayout(layout);
	    /****************************************************************************/
        Font font = new Font("Arial", Font.BOLD, 20);
	    /****************************************************************************/
	    et = new JLabel("REGISTRO DE BENEFICIOS DEL GIMNASIO");
	    et.setFont(font);
	    etiqueta = new JLabel("INGRESOS MENSUALES");
	    etiqueta.setBackground(Color.GRAY);
	    etiquetaNombre = new JLabel("Selecciona mes y año: ");
	    etiquetaBeneficio = new JLabel("Beneficio: ");
	    
	    
	    modeloDatosBeneficioCalculado = new JLabel("");	    
	    modeloDatosBeneficioCalculado.setText("");
	    /****************************************************************************/
	    botonAtras = new JButton("Atras");
	    botonBeneficio = new JButton("Calcular Beneficio Actividad");
	    botonUpdate = new JButton("Actualizar Tabla");
		/****************************************************************************/
        boxMeses = new JComboBox<Integer>(meses);
        boxAnyos = new JComboBox<Integer>(anyos);
        /****************************************************************************/
        modeloDatos = new DefaultTableModel(filas, titulos);
        tabla = new JTable(modeloDatos);
		tabla.setPreferredScrollableViewportSize(new Dimension(500, 80));
		/****************************************************************************/
		scroll = new JScrollPane(tabla);
		/****************************************************************************/
		botonAtras.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonAtras.setBackground(Color.LIGHT_GRAY);
		
		botonBeneficio.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonBeneficio.setBackground(Color.LIGHT_GRAY);
		
		botonUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonUpdate.setBackground(Color.LIGHT_GRAY);
		/****************************************************************************/
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, et, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, et, 5, SpringLayout.NORTH, this);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiqueta, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, etiqueta, 5, SpringLayout.SOUTH, et);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, panel2, 5, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, panel2, 5, SpringLayout.SOUTH, etiqueta);

        layout.putConstraint(SpringLayout.WEST, botonAtras, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, botonAtras, 5, SpringLayout.NORTH, this);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonBeneficio, 5, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.SOUTH, botonBeneficio, -10, SpringLayout.SOUTH, this);
        
        layout.putConstraint(SpringLayout.WEST, etiquetaNombre, 5, SpringLayout.WEST, panel2);
        layout.putConstraint(SpringLayout.NORTH, etiquetaNombre, 5, SpringLayout.SOUTH, panel2);
        
        layout.putConstraint(SpringLayout.WEST, botonUpdate, 5, SpringLayout.WEST, panel2);
        layout.putConstraint(SpringLayout.NORTH, botonUpdate, 5, SpringLayout.SOUTH, etiquetaNombre);

        layout.putConstraint(SpringLayout.WEST, boxMeses, 5, SpringLayout.EAST, etiquetaNombre);
        layout.putConstraint(SpringLayout.NORTH, boxMeses, 5, SpringLayout.SOUTH, etiquetaNombre);
        
        layout.putConstraint(SpringLayout.WEST, boxAnyos, 5, SpringLayout.EAST, boxMeses);
        layout.putConstraint(SpringLayout.NORTH, boxAnyos, 5, SpringLayout.SOUTH, etiquetaNombre);
        
        layout.putConstraint(SpringLayout.WEST, etiquetaBeneficio, 80, SpringLayout.EAST, boxAnyos);
        layout.putConstraint(SpringLayout.NORTH, etiquetaBeneficio, 5, SpringLayout.SOUTH, panel2);
        
        layout.putConstraint(SpringLayout.WEST, modeloDatosBeneficioCalculado, 20, SpringLayout.EAST, etiquetaBeneficio);
        layout.putConstraint(SpringLayout.NORTH, modeloDatosBeneficioCalculado, 5, SpringLayout.SOUTH, panel2);
        /****************************************************************************/
        this.add(et);
        this.add(etiqueta);
        this.add(panel2);
        this.add(botonAtras);
        this.add(etiquetaNombre);
        this.add(boxMeses);
		this.add(boxAnyos);
		this.add(etiquetaBeneficio);
		this.add(botonBeneficio);
		this.add(modeloDatosBeneficioCalculado);
		this.add(botonUpdate);
		/****************************************************************************/
		panel2.add(scroll); 
		
	}
	
	/**
	 * Establece el controlador
	 * @param controladorBeneficios el controlador de la ventana
	 */
	public void setControlador(ControladorBeneficiosGimnasio controladorBeneficios) {
		botonAtras.addActionListener(controladorBeneficios);
		botonBeneficio.addActionListener(controladorBeneficios);
		botonUpdate.addActionListener(controladorBeneficios);
	}	
	
	/**
	 * GETTER
	 * @return el mes
	 */
	public String getBoxMeses()
    {
    	return this.boxMeses.getSelectedItem().toString();
    }
	
	/**
	 * GETTEr
	 * @return el año
	 */
	public String getBoxAnyos()
    {
    	return this.boxAnyos.getSelectedItem().toString();
    }
    
	/**
	 * GETTER
	 * @return la tabla
	 */
    public JTable getTabla() {
		return this.tabla;
	}
	
    /**
     * Actualiza el beneficio
     * @param beneficioCalculado el beneficio
     */
	public void updateCalculado(String beneficioCalculado)
	{
		this.modeloDatosBeneficioCalculado.setText(beneficioCalculado);
		this.modeloDatosBeneficioCalculado.setForeground(Color.RED);
	}
	
	/**
	 * Actualiza la tabla
	 * @param reservas lista de reservas
	 */
	public void updateBeneficios(ArrayList<Reserva> reservas) {
		int i=0, j=0;
		
		Actividad act;

		this.reservas = new HashSet<>(reservas);
		
		while (i < modeloDatos.getRowCount() && modeloDatos.getValueAt(i, 0) != null && !modeloDatos.getValueAt(i, 2).toString().isEmpty()) {
		    modeloDatos.setValueAt("", i, 0);
		    modeloDatos.setValueAt("", i, 1);
		    modeloDatos.setValueAt("", i, 2);
		    modeloDatos.setValueAt("", i, 3);
		    modeloDatos.setValueAt("", i, 4);
		    modeloDatos.setValueAt("", i, 5);
		    modeloDatos.setValueAt("", i, 6);
		    modeloDatos.setValueAt("", i, 7);
		    i++;
		}
		
		i=0; 
		j=0;
			for (Reserva reserva : this.reservas) {
				Sesion sesion = reserva.getSesion();
				if(sesion.getActSesion()!=null && sesion.getActSesion() instanceof ActividadGrupal){
					modeloDatos.setValueAt(sesion.getActSesion().getNombre(), j, 0); 
				} else if (sesion.getActSesion()!=null && sesion.getActSesion() instanceof PlanPersonalizado){
					modeloDatos.setValueAt(sesion.getActSesion().getNombre(), j, 0); 
					modeloDatos.setValueAt(((PlanPersonalizado)sesion.getActSesion()).getTipoObjetivo(), j, 1);
				} else{
					modeloDatos.setValueAt("Sesion Libre", j, 0);
				}
				modeloDatos.setValueAt(sesion.getHoraInicio().toLocalTime(), j, 2);													// j
				modeloDatos.setValueAt(sesion.getHoraFin().toLocalTime(), j, 3); 
				modeloDatos.setValueAt(sesion.getFecha(), j, 4); 
				modeloDatos.setValueAt(sesion.getSala().getNombre(), j, 5);
				modeloDatos.setValueAt(sesion.getReservas().size(), j, 6); 
				modeloDatos.setValueAt(sesion.getPrecio(), j, 7); 
				++j;
			}
			i=i+j;
		}
		
			
	}