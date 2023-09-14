package gui.administrador;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;

import aplicacion.Gimnasio;
import aplicacion.actividad.sesion.SesionLibre;
import aplicacion.excepciones.ExcepcionUsuario;
import aplicacion.excepciones.FechaPosterior;
import gui.controladores.administrador.ControladorConsultarSesionLibreAdmin;
import gui.controladores.cliente.ControladorSesionLibre;
/**
 * Este fichero muestra todo lo que tiene que ver con la clase ConsultarSesionLibreWindow
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class ConsultarSesionLibreAdminWindow extends JPanel {
	private JButton botonAtras;
	private JButton botonCancelar;

	private JPanel panel2;

	private SpringLayout layout;

	private JLabel etiqueta;

	private JTable tabla1;

	private JScrollPane scroll;

	private String[] titulos1 = new String[] { "Fecha", "Hora Inicio", "Hora Fin", "Sala", "Aforo", "Precio" };
	private Object[][] filas1 = new Object[][] { {}, {}, {}, {}, {}, {} };

	private DefaultTableModel modeloDatos;

	private HashSet<SesionLibre> actividades = new HashSet<SesionLibre>();

	/**
	 * Constructor SesionLibreWindow
	 */
	public ConsultarSesionLibreAdminWindow(){
		panel2 = new JPanel();
		layout = new SpringLayout();
		this.setLayout(layout);
		/**************************/
		Font font = new Font("Arial", Font.BOLD, 20);
		/**************************/
		etiqueta = new JLabel("CONSULTAR SESIONES LIBRES");
		etiqueta.setFont(font);
		// etiqueta.setBackground(Color.GRAY);
		/**************************/
		botonAtras = new JButton("Atras");
		botonCancelar = new JButton("Cancelar");
		/**************************/
		botonAtras.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonAtras.setBackground(Color.LIGHT_GRAY);

		botonCancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonCancelar.setBackground(Color.LIGHT_GRAY);
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
		
		layout.putConstraint(SpringLayout.WEST, botonAtras, 5, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, botonAtras, 5, SpringLayout.NORTH, this);

		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonCancelar, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, botonCancelar, 10, SpringLayout.SOUTH, panel2);
		/**************************/
		this.add(etiqueta);
		this.add(panel2);
		this.add(botonAtras);
		this.add(botonCancelar);
		

		panel2.add(scroll);
	}

	/**
	 * Establece el controlador
	 * @param controladorConsultarSesionLibreAdmin el controlador de la ventana
	 */
	public void setControlador(ControladorConsultarSesionLibreAdmin controladorConsultarSesionLibreAdmin) {
		botonAtras.addActionListener(controladorConsultarSesionLibreAdmin);
		botonCancelar.addActionListener(controladorConsultarSesionLibreAdmin);
	}

	/**
	 * GETTER
	 * @return el elemento seleccionado de la tabla
	 */
	public int getSeleccionada() {
		return this.tabla1.getSelectedRow();
	}

	/**
	 * GETTER
	 * @return la tabla
	 */
	public JTable getTabla() {
		return this.tabla1;
	}

	/**
	 * Actualiza la tabla con todas las sesiones del gimnasio
	 * @param actividades las actividades a añadir
	 * @throws ExcepcionUsuario si el usuario no existe
	 * @throws FechaPosterior salta si la fecha es posterior
	 */
	public void updateSesiones(ArrayList<SesionLibre> actividades) throws ExcepcionUsuario, FechaPosterior {
		int lon, i = 0;
		SesionLibre act;

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

		for (i = 0; i < lon; i++) {
			act = actividades.get(i);
			
			//fecha,horaIni,horaFin, salaSimple, precio
			modeloDatos.setValueAt(act.getFecha(), i, 0);// FECHA
			modeloDatos.setValueAt(act.getHoraInicio().toLocalTime(), i, 1);// HORA INICIO
			modeloDatos.setValueAt(act.getHoraFin().toLocalTime(), i, 2);// HORA FIN
			modeloDatos.setValueAt(act.getSala().getNombre(), i, 3);// SALA
			modeloDatos.setValueAt(act.getReservas().size(), i, 4); //AFORO
			modeloDatos.setValueAt(act.getPrecio(), i, 5);//PRECIO
		}
	}
}
