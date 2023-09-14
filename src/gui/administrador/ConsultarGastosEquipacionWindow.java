package gui.administrador;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;

import aplicacion.Reserva;
import aplicacion.equipacion.*;
import aplicacion.actividad.Actividad;
import aplicacion.actividad.ActividadGrupal;
import aplicacion.actividad.PlanPersonalizado;
import aplicacion.actividad.sesion.Sesion;
import gui.controladores.administrador.ControladorConsultarGastosEquipacion;
/**
 * Este fichero muestra todo lo que tiene que ver con la clase LoginWindow
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class ConsultarGastosEquipacionWindow extends JPanel{
	private JButton botonAtras;
	private JButton botonGasto;
	private JButton botonUpdate;
	
	private JPanel panel2;
	
	private SpringLayout layout;
	
	private JLabel et;
	private JLabel etiqueta;
	private JLabel etiquetaNombre;
	private JLabel etiquetaGasto;
	private JLabel etiquetaInformacion;
	
	private JLabel modeloDatosGastoCalculado;
	
	private JComboBox<Integer> boxMeses;
	private JComboBox<Integer> boxAnyos;
	
	private DefaultTableModel modeloDatos;
	
	private HashSet<Equipacion> equipaciones;
	
	private String[] titulos = new String[] {"Tipo", "Descripcion", "Marca", "Estado", "ID", "Precio", "Fecha", "NumUnidades"};
	private Object[][] filas = new Object [] [] {{}, {}, {}, {}, {}, {}, {}, {}};;
	
	private JTable tabla;
	
	private Integer[] meses = new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
	private Integer[] anyos = new Integer[] {1930, 1931, 1932, 1933, 1934, 1935, 1936, 1937, 1938, 1939, 1940, 1941, 1942, 1943, 1944, 1945, 1946, 1947, 1948, 1949, 1950, 1951, 1952, 1953, 1954, 1955, 1956, 1957, 1958, 1959, 1960, 1961, 1962, 1963, 1964, 1965, 1966, 1967, 1968, 1969, 1970, 1971, 1972, 1973, 1974, 1975, 1976, 1977, 1978, 1979, 1980, 1981, 1982, 1983, 1984, 1985, 1986, 1987, 1988, 1989, 1990, 1991, 1992, 1993, 1994, 1995, 1996, 1997, 1998, 1999, 2000, 2001, 2002, 2003, 2004, 2005, 2006, 2007, 2008, 2009, 2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017, 2018, 2019, 2020, 2021, 2022, 2023};
	
	private JScrollPane scroll;
    
	/**
	 * Constructor ConsultarGastosEquipacionWindow
	 */
	public ConsultarGastosEquipacionWindow()
	{	
		panel2 = new JPanel();
	    layout = new SpringLayout();
	    this.setLayout(layout);
	    /****************************************************************************/
        Font font = new Font("Arial", Font.BOLD, 20);
	    /****************************************************************************/
	    et = new JLabel("CONSULTAR GASTOS EQUIPACION");
	    et.setFont(font);
	    etiqueta = new JLabel("GASTOS MENSUALES");
	    etiqueta.setBackground(Color.GRAY);
	    etiquetaNombre = new JLabel("Selecciona mes y año: ");
	    etiquetaGasto = new JLabel("Gastos: ");
	    etiquetaInformacion = new JLabel("Selecciona varias filas para calcular el gasto total");
	    
	    modeloDatosGastoCalculado = new JLabel("");	    
	    modeloDatosGastoCalculado.setText("");
	    /****************************************************************************/
	    botonAtras = new JButton("Atras");
	    botonGasto = new JButton("Calcular Gastos");
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
		
		botonGasto.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonGasto.setBackground(Color.LIGHT_GRAY);
		
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
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonGasto, 5, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.SOUTH, botonGasto, -10, SpringLayout.SOUTH, this);
        
        layout.putConstraint(SpringLayout.WEST, etiquetaNombre, 5, SpringLayout.WEST, panel2);
        layout.putConstraint(SpringLayout.NORTH, etiquetaNombre, 5, SpringLayout.SOUTH, panel2);
        
        layout.putConstraint(SpringLayout.WEST, botonUpdate, 5, SpringLayout.WEST, panel2);
        layout.putConstraint(SpringLayout.NORTH, botonUpdate, 5, SpringLayout.SOUTH, etiquetaNombre);

        layout.putConstraint(SpringLayout.WEST, boxMeses, 5, SpringLayout.EAST, etiquetaNombre);
        layout.putConstraint(SpringLayout.NORTH, boxMeses, 5, SpringLayout.SOUTH, etiquetaNombre);
        
        layout.putConstraint(SpringLayout.WEST, boxAnyos, 5, SpringLayout.EAST, boxMeses);
        layout.putConstraint(SpringLayout.NORTH, boxAnyos, 5, SpringLayout.SOUTH, etiquetaNombre);
        
        layout.putConstraint(SpringLayout.WEST, etiquetaGasto, 80, SpringLayout.EAST, boxAnyos);
        layout.putConstraint(SpringLayout.NORTH, etiquetaGasto, 5, SpringLayout.SOUTH, panel2);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaInformacion, 80, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, etiquetaInformacion, 15, SpringLayout.SOUTH, botonUpdate);
 
        layout.putConstraint(SpringLayout.WEST, modeloDatosGastoCalculado, 20, SpringLayout.EAST, etiquetaGasto);
        layout.putConstraint(SpringLayout.NORTH, modeloDatosGastoCalculado, 5, SpringLayout.SOUTH, panel2);
        /****************************************************************************/
        this.add(et);
        this.add(etiqueta);
        this.add(panel2);
        this.add(botonAtras);
        this.add(etiquetaNombre);
        this.add(boxMeses);
		this.add(boxAnyos);
		this.add(etiquetaGasto);
		this.add(botonGasto);
		this.add(modeloDatosGastoCalculado);
		this.add(botonUpdate);
		/****************************************************************************/
		panel2.add(scroll); 
		
	}

	/**
	 * Establece el controlador
	 * @param controladorGastosEquipacion el controlador de la ventana
	 */
	public void setControlador(ControladorConsultarGastosEquipacion controladorGastosEquipacion) {
		botonAtras.addActionListener(controladorGastosEquipacion);
		botonGasto.addActionListener(controladorGastosEquipacion);
		botonUpdate.addActionListener(controladorGastosEquipacion);
	}	
	
	/**
	 * GETTER
	 * @return mes
	 */
	public String getBoxMeses()
    {
    	return this.boxMeses.getSelectedItem().toString();
    }
	
	/**
	 * GETTER
	 * @return año
	 */
	public String getBoxAnyos()
    {
    	return this.boxAnyos.getSelectedItem().toString();
    }
    
	/**
	 * GETTER
	 * @return tabla
	 */
    public JTable getTabla() {
		return this.tabla;
	}
	
    /**
     * Actualiza el precio calculado
     * @param GastoCalculado el gato que ha sido calculado
     */
	public void updateCalculado(String GastoCalculado)
	{
		this.modeloDatosGastoCalculado.setText(GastoCalculado);
		this.modeloDatosGastoCalculado.setForeground(Color.RED);
	}
	
	/**
	 * Actualiza la tabla
	 * @param equipaciones las nuevas equipaciones
	 */
	public void updateGastosEquipacion(ArrayList<Equipacion> equipaciones) {
		int i=0, j=0;
		
		Actividad act;

		this.equipaciones = new HashSet<>(equipaciones);
		
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
		for (Equipacion equipacion : this.equipaciones) {
			if(equipacion !=null && equipacion.isMaquina()){
				Maquina maquina = (Maquina) equipacion;
				modeloDatos.setValueAt(maquina.getTipo(), j, 0); 
				modeloDatos.setValueAt(maquina.getMarca(), j, 2);
				modeloDatos.setValueAt(maquina.getEstado(), j, 3);
				modeloDatos.setValueAt(maquina.getId(), j, 4);
			} else if (equipacion !=null && equipacion.isMaterial()){
				Material material = (Material) equipacion;
				modeloDatos.setValueAt(material.getNumUnidades(), j, 7); 
			} 
			modeloDatos.setValueAt(equipacion.getDescripcion(), j, 1);
			modeloDatos.setValueAt(equipacion.getPrecio(), j, 5);
			modeloDatos.setValueAt(equipacion.getFecha(), j, 6);
			++j;
		}
		
	}

}
