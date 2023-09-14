package gui.cliente;

import java.awt.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import aplicacion.actividad.Actividad;
import aplicacion.actividad.sesion.ListaEspera;
import aplicacion.usuario.Usuario;
import gui.controladores.ControladorLogin;
import gui.controladores.cliente.ControladorListaEspera;

/**
 * Este fichero muestra todo lo que tiene que ver con la clase ListaEsperaWindow
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class ListaEsperaWindow extends JPanel{
	private JButton botonAtras;
	
	private JPanel panel2;
	private SpringLayout layout;
	
	private JLabel etiqueta;
	
	private ListaEspera listaE;
	
	private DefaultListModel<String> modeloDatos;
	
	private JList<String> lista;
	
	/**
	 * Constructor ListaEsperaWindow
	 */
	public ListaEsperaWindow()
	{
			panel2 = new JPanel();
		    layout = new SpringLayout();
		    this.setLayout(layout);
		    /****************************************************************************/
	        Font font = new Font("Arial", Font.BOLD, 20);
		    /****************************************************************************/
		    etiqueta = new JLabel("LISTA DE ESPERA");
		    etiqueta.setFont(font);
		    etiqueta.setBackground(Color.GRAY);
		    /****************************************************************************/
			modeloDatos= new DefaultListModel<String>();
			lista = new JList<String>(modeloDatos);
			
			lista.setBackground(Color.LIGHT_GRAY);
			/****************************************************************************/
			botonAtras = new JButton("Atras");
			botonAtras.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			botonAtras.setBackground(Color.LIGHT_GRAY);
			/****************************************************************************/
			layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiqueta, 0, SpringLayout.HORIZONTAL_CENTER, this);
	        layout.putConstraint(SpringLayout.NORTH, etiqueta, 5, SpringLayout.NORTH, this);
	        
	        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lista, 0, SpringLayout.HORIZONTAL_CENTER, this);
	        layout.putConstraint(SpringLayout.NORTH, lista, 50, SpringLayout.SOUTH, etiqueta);
	        
	        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, panel2, 5, SpringLayout.HORIZONTAL_CENTER, this);
	        layout.putConstraint(SpringLayout.NORTH, panel2, 5, SpringLayout.SOUTH, etiqueta);

	        layout.putConstraint(SpringLayout.WEST, botonAtras, 5, SpringLayout.WEST, this);
	        layout.putConstraint(SpringLayout.NORTH, botonAtras, 5, SpringLayout.NORTH, this);
	        /****************************************************************************/
	        this.add(etiqueta);
	        this.add(panel2);
	        this.add(botonAtras);
	        this.add(lista);
		}

	/**
	 * Establece el controlador
	 * @param controladorListaEspera el controlador de la ventana
	 */
	public void setControlador(ControladorListaEspera controladorListaEspera) {
		botonAtras.addActionListener(controladorListaEspera);
		
	}
	
	/**
	 * Actualiza la lista de espera
	 * @param nuevaLista la nueva lista que va a apaerecer
	 */
	public void updateListaEspera(ListaEspera nuevaLista) {
    	modeloDatos.removeAllElements();

    	for (Usuario usuario : nuevaLista.getListaClientes()) 
        	modeloDatos.addElement(usuario.getUsuario());
    	
	}



	
}