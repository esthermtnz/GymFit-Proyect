package gui.administrador;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;

import aplicacion.equipacion.Maquina;
import gui.controladores.administrador.ControladorCambiarEstadoMaquinas;
import gui.controladores.administrador.ControladorCambiarEstadoMaquinas;
import gui.controladores.monitor.ControladorConsultarMaquinas;
/**
 * Este fichero muestra todo lo que tiene que ver con la clase CambiarEstadoMaquinasWindow
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class CambiarEstadoMaquinasWindow extends JPanel{
	private JButton botonAtras;
	private JButton botonOperativa;
	private JButton botonEnReparacion;
	private JButton botonRetirada;
	
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
	 * Controlador CambiarEstadoMaquinasWindow
	 */
	public CambiarEstadoMaquinasWindow(){
		panel2 = new JPanel();
	    layout = new SpringLayout();
	    this.setLayout(layout);
	    /****************************************************************************/
        Font font = new Font("Arial", Font.BOLD, 20);
	    /****************************************************************************/
	    etiqueta = new JLabel("CAMBIAR ESTADO MAQUINAS");
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
		botonOperativa = new JButton ("Operativa");
		botonEnReparacion = new JButton ("En Reparacion");
		botonRetirada = new JButton ("Retirada");
		/****************************************************************************/
		maquina = new JLabel("");
		/****************************************************************************/
		botonAtras.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonAtras.setBackground(Color.LIGHT_GRAY);
		
		botonOperativa.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonOperativa.setBackground(Color.LIGHT_GRAY);
		
		botonEnReparacion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonEnReparacion.setBackground(Color.LIGHT_GRAY);
		
		botonRetirada.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonRetirada.setBackground(Color.LIGHT_GRAY);
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
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonOperativa, 5, SpringLayout.HORIZONTAL_CENTER, panel2);
        layout.putConstraint(SpringLayout.NORTH, botonOperativa, 10, SpringLayout.SOUTH, boxMaquina);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonEnReparacion, 5, SpringLayout.HORIZONTAL_CENTER, panel2);
        layout.putConstraint(SpringLayout.NORTH, botonEnReparacion, 10, SpringLayout.SOUTH, botonOperativa);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonRetirada, 5, SpringLayout.HORIZONTAL_CENTER, panel2);
        layout.putConstraint(SpringLayout.NORTH, botonRetirada, 10, SpringLayout.SOUTH, botonEnReparacion);
        /****************************************************************************/
        this.add(etiqueta);
        this.add(panel2);
        this.add(botonAtras);
		this.add(etiquetaNombre);
		this.add(boxMaquina);
		this.add(botonOperativa);
		this.add(botonEnReparacion);
		this.add(botonRetirada);
		
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
	 * @return la maquina
	 */
	public String getBoxMaquina()
    {
    	return this.boxMaquina.getSelectedItem().toString();
    }
    
	/**
	 * Estbalece el controlador
	 * @param controladorCambiarEstadoMaquinas el controlador de la ventana
	 */
    public void setControlador(ControladorCambiarEstadoMaquinas controladorCambiarEstadoMaquinas) {
		botonAtras.addActionListener(controladorCambiarEstadoMaquinas);
		botonOperativa.addActionListener(controladorCambiarEstadoMaquinas);
		botonEnReparacion.addActionListener(controladorCambiarEstadoMaquinas);
		botonRetirada.addActionListener(controladorCambiarEstadoMaquinas);
		boxMaquina.addActionListener(controladorCambiarEstadoMaquinas);
	}
	
    /**
     * Actualiza la tabla
     * @param maquinas la lista de maquinas
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
