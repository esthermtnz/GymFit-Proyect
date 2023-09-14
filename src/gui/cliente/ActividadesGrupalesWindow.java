package gui.cliente;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import aplicacion.Gimnasio;
import aplicacion.Reserva;
import aplicacion.actividad.Actividad;
import aplicacion.actividad.ActividadGrupal;
import aplicacion.actividad.PlanPersonalizado;
import aplicacion.actividad.sesion.SesionMonitorizada;
import aplicacion.excepciones.ExcepcionUsuario;
import aplicacion.excepciones.FechaPosterior;
import aplicacion.usuario.Monitor;
import gui.controladores.cliente.ControladorActividadesGrupales;

/**
 * Este fichero muestra todo lo que tiene que ver con la clase ActividadesGrupalesWindow
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class ActividadesGrupalesWindow extends JPanel {
	private JButton botonAtras;
	private JButton botonApuntar;
	private JButton botonListaEspera;

	private JPanel panel2;

	private SpringLayout layout;

	private JLabel etiqueta;

	private JTable tabla1;

	private JScrollPane scroll;

	private String[] titulos1 = new String[] { "Actividad", "Hora Inicio", "Hora Fin", "Fecha", "Sala", "Aforo" };
	private Object[][] filas1 = new Object[][] { {}, {}, {}, {}, {}, {} };

	private DefaultTableModel modeloDatos;

	private HashSet<ActividadGrupal> actividades = new HashSet<ActividadGrupal>();

	/**
	 * Constructor ActividadesGrupalesWindow
	 */
	public ActividadesGrupalesWindow() {
		panel2 = new JPanel();
		layout = new SpringLayout();
		this.setLayout(layout);
		/**************************/
		Font font = new Font("Arial", Font.BOLD, 20);
		/**************************/
		etiqueta = new JLabel("ACTIVIDADES GRUPALES");
		etiqueta.setFont(font);
		// etiqueta.setBackground(Color.GRAY);
		/**************************/
		botonAtras = new JButton("Atras");
		botonApuntar = new JButton("Apuntate");
		botonListaEspera = new JButton("Lista Espera");
		/**************************/
		botonAtras.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonAtras.setBackground(Color.LIGHT_GRAY);

		botonApuntar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonApuntar.setBackground(Color.LIGHT_GRAY);

		botonListaEspera.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonListaEspera.setBackground(Color.LIGHT_GRAY);
		/**************************/
		modeloDatos = new DefaultTableModel(filas1, titulos1);

		tabla1 = new JTable(modeloDatos);
		tabla1.setPreferredScrollableViewportSize(new Dimension(500, 80));
		/**************************/
		scroll = new JScrollPane(tabla1);
		/**************************/
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiqueta, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, etiqueta, 5, SpringLayout.NORTH, this);

		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, panel2, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, panel2, 20, SpringLayout.SOUTH, etiqueta);

		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonApuntar, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, botonApuntar, 10, SpringLayout.SOUTH, panel2);

		layout.putConstraint(SpringLayout.WEST, botonAtras, 5, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, botonAtras, 5, SpringLayout.NORTH, this);

		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonListaEspera, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.SOUTH, botonListaEspera, -10, SpringLayout.SOUTH, this);

		/**************************/
		this.add(etiqueta);
		this.add(panel2);
		this.add(botonAtras);
		this.add(botonApuntar);
		this.add(botonListaEspera);

		panel2.add(scroll);
	}

	/**
	 * Establece el controlador
	 * @param controladorActividadesGrupales el controlador de la ventana
	 */
	public void setControlador(ControladorActividadesGrupales controladorActividadesGrupales) {
		botonAtras.addActionListener(controladorActividadesGrupales);
		botonApuntar.addActionListener(controladorActividadesGrupales);
		botonListaEspera.addActionListener(controladorActividadesGrupales);
	}

	/**
	 * GETTER
	 * @return elemento seleccionado de la tabla
	 */
	public int getSeleccionada() {
		return this.tabla1.getSelectedRow();
	}
	
	/**
	 * GETTER
	 * @return la tabla
	 */
	public JTable getTabla()
	{
		return this.tabla1;
	}
	

	/**
	 * Actualiza la tabla con todas las actividades grupales del gimnasio
	 * @param actividades las actividades que van a estar en la tabla
	 * @throws ExcepcionUsuario salta si el usuario no existe
	 * @throws FechaPosterior salta si la fecha es posterior
	 */
	public void updateActividadesGrupales(ArrayList<ActividadGrupal> actividades) throws ExcepcionUsuario, FechaPosterior {
		int lon, i = 0, j;
		ActividadGrupal act;

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
			i++;
		}

		i=0;
		j=0;
		
		for (ActividadGrupal plan : this.actividades) {
			for (SesionMonitorizada sesion : plan.getSesionesMonitorizadas()) {
				modeloDatos.setValueAt(plan.getNombre(), j, 0);
				modeloDatos.setValueAt(sesion.getHoraInicio().toLocalTime(), j, 1); // j
				modeloDatos.setValueAt(sesion.getHoraFin().toLocalTime(), j, 2);
				modeloDatos.setValueAt(sesion.getFecha(), j, 3);
				modeloDatos.setValueAt(sesion.getSala().getNombre(), j, 4);
				modeloDatos.setValueAt(sesion.getReservas().size(), j, 5);
				++j;
			}
			i = i + j;
		}
	}
}
