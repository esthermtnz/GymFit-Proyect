package gui.administrador;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import aplicacion.Gimnasio;
import aplicacion.Reserva;
import aplicacion.actividad.Actividad;
import aplicacion.actividad.PlanPersonalizado;
import aplicacion.excepciones.ExcepcionUsuario;
import aplicacion.excepciones.FechaPosterior;
import gui.controladores.ControladorLogin;
import gui.controladores.administrador.ControladorReservas;
import aplicacion.actividad.sesion.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Este fichero muestra todo lo que tiene que ver con la clase ReservasCancelacionesWindow
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class ReservasWindow extends JPanel {
	private JButton botonAtras;

	private JPanel panel2;
	private JPanel panel3;

	private SpringLayout layout;

	private JLabel etiqueta;
	private JLabel etiquetaReservas;

	private Object[][] filas1 = new Object[][] { {}, {} ,{}, {}, {}, {}, {}, {} };
	private String[] titulos1 = new String[] { "Actividad",  "Objetivo" ,"Hora Inicio", "Hora fin", "Fecha", "Sala", "Usuario", "Aforo" };
	
	private JTable tabla1;
	private JScrollPane scroll;

	private DefaultTableModel modeloDatos1;
	
	private HashSet<Reserva> reservas;
	

	/**
	 * Constructor ReservasCancelacionesWindow
	 */
	public ReservasWindow() {

		panel2 = new JPanel();
		panel3 = new JPanel();
		layout = new SpringLayout();
		this.setLayout(layout);
		/****************************************************************************/
		Font font = new Font("Arial", Font.BOLD, 20);
		/****************************************************************************/
		etiqueta = new JLabel("CONSULTAR RESERVAS");
		etiqueta.setBackground(Color.GRAY);
		etiqueta.setFont(font);
		etiquetaReservas = new JLabel("RESERVAS");
		/****************************************************************************/
		botonAtras = new JButton("Atras");
		/****************************************************************************/
		modeloDatos1 = new DefaultTableModel(filas1, titulos1);

		tabla1 = new JTable(modeloDatos1);
		tabla1.setPreferredScrollableViewportSize(new Dimension(500, 80));
		/**************************/
		scroll = new JScrollPane(tabla1);
		/****************************************************************************/
		botonAtras.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonAtras.setBackground(Color.LIGHT_GRAY);
		/****************************************************************************/
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiqueta, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, etiqueta, 5, SpringLayout.NORTH, this);

		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, panel2, 0, SpringLayout.HORIZONTAL_CENTER, etiqueta);
		layout.putConstraint(SpringLayout.NORTH, panel2, 40, SpringLayout.SOUTH, etiqueta);

		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, panel3, 0, SpringLayout.HORIZONTAL_CENTER, etiqueta);
		layout.putConstraint(SpringLayout.NORTH, panel3, 40, SpringLayout.SOUTH, panel2);

		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaReservas, 0, SpringLayout.HORIZONTAL_CENTER,panel2);
		layout.putConstraint(SpringLayout.SOUTH, etiquetaReservas, -5, SpringLayout.NORTH, panel2);

		layout.putConstraint(SpringLayout.WEST, botonAtras, 5, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, botonAtras, 5, SpringLayout.NORTH, this);
		/****************************************************************************/
		this.add(etiqueta);
		this.add(panel2);
		this.add(etiquetaReservas);
		this.add(botonAtras);

		panel2.add(scroll);

	}

	/**
	 * Establece el constructor
	 * @param controladorReservas el constructor de la ventana
	 */
	public void setControlador(ControladorReservas controladorReservas) {
		botonAtras.addActionListener(controladorReservas);

	}

	/**
	 * Actualiza la tabla con las nuevas reservas
	 * @param reservas las reservas que se vana introducir en la tabla
	 * @throws ExcepcionUsuario salta si no existe el usuario
	 * @throws FechaPosterior salta si la fecha es posterior
	 */
	public void updateReservas(ArrayList<Reserva> reservas) throws ExcepcionUsuario, FechaPosterior {
		
		int lon, i;
	    Reserva res;

	    this.reservas = new HashSet<>(reservas);

	    lon = reservas.size();
	    
	    for(i=0; i<Gimnasio.getGimnasio().getActividades().size()+1; i++){
			modeloDatos1.setValueAt("", i, 0);
			modeloDatos1.setValueAt("", i, 1);
			modeloDatos1.setValueAt("", i, 2);
			modeloDatos1.setValueAt("", i, 3);
			modeloDatos1.setValueAt("", i, 4);
			modeloDatos1.setValueAt("", i, 5);
			modeloDatos1.setValueAt("", i, 6);
			modeloDatos1.setValueAt("", i, 7);
		}

		for (i = 0; i < lon; i++) {
			res = reservas.get(i);
			modeloDatos1.setValueAt(res.getSesion().getActSesion().getNombre(), i, 0);
			if(res.getSesion() instanceof SesionPP){
				PlanPersonalizado plan = (PlanPersonalizado)res.getSesion().getActSesion();
			modeloDatos1.setValueAt(plan.getTipoObjetivo(), i, 1);
			}
			modeloDatos1.setValueAt(res.getSesion().getHoraInicio(), i, 2);
			modeloDatos1.setValueAt(res.getSesion().getHoraFin(), i, 3);
			modeloDatos1.setValueAt(res.getSesion().getFecha(), i, 4);
			modeloDatos1.setValueAt(res.getSesion().getSala().getNombre(), i, 5);
			modeloDatos1.setValueAt(res.getCliente().getUsuario(), i, 6);
			modeloDatos1.setValueAt(res.getSesion().getReservas().size(), i, 7);
		}

	}

}
