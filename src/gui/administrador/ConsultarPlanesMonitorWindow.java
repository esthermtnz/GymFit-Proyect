package gui.administrador;

import java.awt.*;

import javax.management.remote.SubjectDelegationPermission;
import javax.swing.*;

import gui.controladores.ControladorLogin;
import gui.controladores.administrador.ControladorConsultarPlanesMonitor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;

import javax.swing.table.DefaultTableModel;

import aplicacion.Gimnasio;
import aplicacion.actividad.Actividad;
import aplicacion.excepciones.ExcepcionUsuario;
import aplicacion.excepciones.FechaPosterior;
import aplicacion.usuario.Monitor;
import aplicacion.actividad.PlanPersonalizado;
/**
 * Este fichero muestra todo lo que tiene que ver con la clase ConsultarPlanesMonitorWindow
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class ConsultarPlanesMonitorWindow extends JPanel{
	private JButton botonAtras;
	
	private HashSet<PlanPersonalizado> actividades = new HashSet<PlanPersonalizado>();
	
	private JPanel panel2;
	private SpringLayout layout;
	
	private JLabel monitor;
	private JLabel etiqueta; 
	private JLabel etiquetaNombre;
	
	private JTable tabla;
	private JComboBox<String> boxMonitor;
	
	private JScrollPane scroll;
	private String[] monitores = new String[]{};
	
	private Object[][] filas = new Object[][] { {}, {}, {}, {}, {}, {}, {}};
	private String[] titulos = new String[] { "Nombre", "Objetivo", "Hora Inicio", "Hora Fin", "Fecha", "Sala", "Aforo" };

	private DefaultTableModel modeloDatos;
	
	private DefaultComboBoxModel<String> modeloDatosMonitor;
	
	/**
	 * Constructor ConsultarPlanesMonitorWindow
	 */
	public ConsultarPlanesMonitorWindow()
	{
		panel2 = new JPanel();
	    layout = new SpringLayout();
	    this.setLayout(layout);
	    /****************************************************************************/
        Font font = new Font("Arial", Font.BOLD, 20);
	    /****************************************************************************/
	    etiqueta = new JLabel("PLANES MONITOR");
	    etiqueta.setFont(font);
	    etiqueta.setBackground(Color.GRAY);
	    etiquetaNombre = new JLabel("Selecciona usuario monitor: ");
		/****************************************************************************/
	    modeloDatos= new DefaultTableModel(filas, titulos);
	    
		tabla = new JTable(modeloDatos);
		tabla.setPreferredScrollableViewportSize(new Dimension(500, 80));
		/****************************************************************************/
		modeloDatosMonitor = new DefaultComboBoxModel<String>(monitores);
        boxMonitor = new JComboBox<String>(modeloDatosMonitor);
        /****************************************************************************/
		scroll = new JScrollPane(tabla);
		/****************************************************************************/
		botonAtras = new JButton("Atras");
		/****************************************************************************/
		monitor = new JLabel("");
		/****************************************************************************/
		botonAtras.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonAtras.setBackground(Color.LIGHT_GRAY);
		/****************************************************************************/
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiqueta, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, etiqueta, 5, SpringLayout.NORTH, this);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, panel2, 5, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, panel2, 5, SpringLayout.SOUTH, etiqueta);

        layout.putConstraint(SpringLayout.WEST, botonAtras, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, botonAtras, 5, SpringLayout.NORTH, this);
        
        layout.putConstraint(SpringLayout.WEST, etiquetaNombre, 5, SpringLayout.WEST, panel2);
        layout.putConstraint(SpringLayout.NORTH, etiquetaNombre, 5, SpringLayout.SOUTH, panel2);

        layout.putConstraint(SpringLayout.WEST, boxMonitor, 5, SpringLayout.EAST, etiquetaNombre);
        layout.putConstraint(SpringLayout.NORTH, boxMonitor, 5, SpringLayout.SOUTH, panel2);
        /****************************************************************************/
        this.add(etiqueta);
        this.add(panel2);
        this.add(botonAtras);
		this.add(etiquetaNombre);
		this.add(boxMonitor);
		
		panel2.add(scroll); 
	}
	
	/**
	 * GETTER
	 * @return la caja de los monitores
	 */
	public String getBoxMonitor()
    {
    	return this.boxMonitor.getSelectedItem().toString();
    }
		

	/**
	 * Establece el controladaor
	 * @param controladorConsultarPlanesMonitor el controlador de la ventana
	 */
	public void setControlador(ControladorConsultarPlanesMonitor controladorConsultarPlanesMonitor) {
		botonAtras.addActionListener(controladorConsultarPlanesMonitor);
		boxMonitor.addActionListener(controladorConsultarPlanesMonitor);
	}
	
	/**
	 * Actualiza la caja con los monitores
	 * @param nuevosMonitores los nuevos monitores a añadir
	 */
	public void updateMonitores(HashSet<String> nuevosMonitores) {
		this.monitores = nuevosMonitores.toArray(new String[0]);
	    modeloDatosMonitor=null;
	    modeloDatosMonitor = new DefaultComboBoxModel<String>(monitores);
		this.boxMonitor.setModel(modeloDatosMonitor);
	}
	
	/**
	 * Actualiza la tabla con todos los planes personalizadas
	 * @param actividades las actividades que vana a estar en la tabla
	 * @throws ExcepcionUsuario sale si el usuario no existe
	 * @throws FechaPosterior sale si la fecha es posterior
	 */
	public void updatePlanes(ArrayList<PlanPersonalizado> actividades) throws ExcepcionUsuario, FechaPosterior {
	    int lon, i = 0, j;
	    PlanPersonalizado act;
	    this.actividades = new HashSet<>(actividades);

	    lon = actividades.size();
	    
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
	   
	    for (i = 0; i < lon; i++) {
	        act = actividades.get(i);
	        for (j = 0; j < act.getSesionesMonitorizadas().size(); j++) {
	            modeloDatos.setValueAt(act.getNombre(), i, 0);
                PlanPersonalizado plan = (PlanPersonalizado) act;
                modeloDatos.setValueAt(plan.getTipoObjetivo(), i, 1);
	            modeloDatos.setValueAt(act.getSesionesMonitorizadas().get(j).getHoraInicio().toLocalTime(), i, 2);
	            modeloDatos.setValueAt(act.getSesionesMonitorizadas().get(j).getHoraFin().toLocalTime(), i, 3);
	            modeloDatos.setValueAt(act.getSesionesMonitorizadas().get(j).getFecha(), i, 4);
	            modeloDatos.setValueAt(act.getSesionesMonitorizadas().get(j).getSala().getNombre(), i, 5);
	            modeloDatos.setValueAt(act.getSesionesMonitorizadas().get(j).getReservas().size(), i, 6);
	        }
	    }
	}
	
}