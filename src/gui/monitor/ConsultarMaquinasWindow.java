package gui.monitor;
import java.awt.*;

import javax.swing.*;

import javax.swing.table.DefaultTableModel;

import aplicacion.equipacion.Maquina;

import java.util.HashSet;
import gui.controladores.monitor.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Este fichero muestra todo lo que tiene que ver con la clase ConsultarMaquinasWindow
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class ConsultarMaquinasWindow extends JPanel{
	private JButton botonAtras;
	private JButton botonMaquina;
	
	private JPanel panel2;
	private SpringLayout layout;
	
	private JLabel maquina;
	private JLabel etiqueta; 
	private JLabel etiquetaNombre;
	private JScrollPane scroll;
	
	private String[] maquinas = new String[]{};
	private JComboBox<String> boxMaquina;
	private Object[][] filas = new Object[][] { {}, {}, {}, {}, {}, {}, {}};
	private String[] titulos = new String[] { "Tipo", "Descripcion", "Marca", "Estado", "Id", "Precio", "Fecha" };
	private String[] tipo = new String[] {"Alquilada", "Propiedad"};
	private DefaultTableModel modeloDatos;
	
	private DefaultComboBoxModel<String> modeloDatosMaquina;
	private HashSet<Maquina> maquinas1 = new HashSet<Maquina>();
	private JTable tabla;
	
	/**
	 * Constructor ConsultarMaquinasWindow
	 */
	public ConsultarMaquinasWindow(){
		panel2 = new JPanel();
	    layout = new SpringLayout();
	    this.setLayout(layout);
	    /****************************************************************************/
        Font font = new Font("Arial", Font.BOLD, 20);
	    /****************************************************************************/
	    etiqueta = new JLabel("MAQUINAS");
	    etiqueta.setFont(font);
	    etiqueta.setBackground(Color.GRAY);
	    etiquetaNombre = new JLabel("Alquilado/Propiedad");
	    /****************************************************************************/
	    modeloDatos= new DefaultTableModel(filas, titulos);
		tabla = new JTable(modeloDatos);
		tabla.setPreferredScrollableViewportSize(new Dimension(500, 80));
		/****************************************************************************/
		//modeloDatosMaquina = new DefaultComboBoxModel<String>(maquinas);
        //boxMaquina = new JComboBox<String>(modeloDatosMaquina);
        boxMaquina = new JComboBox<String>(tipo); 
        /****************************************************************************/
		scroll = new JScrollPane(tabla);
		/****************************************************************************/
		botonAtras = new JButton("Atras");
		botonMaquina = new JButton ("Averiada");
		/****************************************************************************/
		maquina = new JLabel("");
		/****************************************************************************/
		botonAtras.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonAtras.setBackground(Color.LIGHT_GRAY);
		
		botonMaquina.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonMaquina.setBackground(Color.LIGHT_GRAY);
		/****************************************************************************/
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiqueta, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, etiqueta, 5, SpringLayout.NORTH, this);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, panel2, 5, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, panel2, 5, SpringLayout.SOUTH, etiqueta);

        layout.putConstraint(SpringLayout.WEST, botonAtras, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, botonAtras, 5, SpringLayout.NORTH, this);
        
        layout.putConstraint(SpringLayout.WEST, etiquetaNombre, 5, SpringLayout.WEST, panel2);
        layout.putConstraint(SpringLayout.NORTH, etiquetaNombre, 5, SpringLayout.SOUTH, panel2);

        layout.putConstraint(SpringLayout.WEST, boxMaquina, 5, SpringLayout.EAST, etiquetaNombre);
        layout.putConstraint(SpringLayout.NORTH, boxMaquina, 5, SpringLayout.SOUTH, panel2);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonMaquina, 5, SpringLayout.HORIZONTAL_CENTER, panel2);
        layout.putConstraint(SpringLayout.NORTH, botonMaquina, 10, SpringLayout.SOUTH, boxMaquina);
        /****************************************************************************/
        this.add(etiqueta);
        this.add(panel2);
        this.add(botonAtras);
		this.add(etiquetaNombre);
		this.add(boxMaquina);
		this.add(botonMaquina);
		
		panel2.add(scroll);
	}
	
	/**
	 * GETTER
	 * @return la tabla
	 */
	public JTable getTabla() {
		return this.tabla;
	}
	
	/**
	 * GETTER
	 * @return el elemento seleccionado de la caja de maquina
	 */
	public String getBoxMaquina()
    {
    	return this.boxMaquina.getSelectedItem().toString();
    }
    
	/**
	 * Establece el controlador
	 * @param controladorConsultarMaquinas el controlador de la ventana
	 */
    public void setControlador(ControladorConsultarMaquinas controladorConsultarMaquinas) {
		botonAtras.addActionListener(controladorConsultarMaquinas);
		botonMaquina.addActionListener(controladorConsultarMaquinas);
		boxMaquina.addActionListener(controladorConsultarMaquinas);
	}
	
    /**
     * Actualizar la tabla con todas las maquinas del gimnasio
     * @param maquinas las maquinas para meter en la tabla
     */
	public void updateMaquinas(ArrayList<Maquina> maquinas) {
		int i = 0;
		Maquina act;

		this.maquinas1 = new HashSet<>(maquinas);

		while (i < modeloDatos.getRowCount() && modeloDatos.getValueAt(i, 0) != null
				&& !modeloDatos.getValueAt(i, 0).toString().isEmpty()) {
			modeloDatos.setValueAt("", i, 0);
			modeloDatos.setValueAt("", i, 1);
			modeloDatos.setValueAt("", i, 2);
			modeloDatos.setValueAt("", i, 3);
			modeloDatos.setValueAt("", i, 4);
			modeloDatos.setValueAt("", i, 5);
			modeloDatos.setValueAt("", i, 6);
			i++;
		}
		
		
		i = 0;

		for (Maquina maquina : this.maquinas1) {
			modeloDatos.setValueAt(maquina.getTipo(), i, 0);
			modeloDatos.setValueAt(maquina.getDescripcion(), i, 1);
			modeloDatos.setValueAt(maquina.getMarca(), i, 2);
			modeloDatos.setValueAt(maquina.getEstado(), i, 3);
			modeloDatos.setValueAt(maquina.getId(), i, 4);
			modeloDatos.setValueAt(maquina.getPrecio(), i, 5);
			modeloDatos.setValueAt(maquina.getFecha(), i, 6);
			i++;
		}

	}
}
