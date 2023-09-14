
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
import gui.controladores.administrador.ControladorConfigurarSalas;

/**
 * Este fichero muestra todo lo que tiene que ver con la clase
 * ConfigurarSalasWindow
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class ConfigurarSalasWindow extends JPanel {
	private JButton botonHecho;
	private JButton botonCancelar;

	private JTextField campoNombre;
	private JTextField campoDescipcion;
	private JTextField campoAforo;

	private SpringLayout layout;

	private JLabel etiqueta;
	private JLabel etiquetaNombre;
	private JLabel etiquetaDescipcion;
	private JLabel etiquetaAforo;
	private JLabel etiquetaSala;
	private JLabel etiquetaSalaCont;
	private JLabel etiquetaClimatizacion;
	private JLabel etiquetaHora;
	private JLabel etiquetaDesde;
	private JLabel etiquetaHasta;

	private TextPrompt promptNombre;
	private TextPrompt promptDescipcion;
	private TextPrompt promptAforo;

	private String[] salas = new String[] { "Subsala", "Sala general" };

	private String[] climatizacion = new String[] { "Si", "No" };

	private String[] salasCont = new String[] {};

	private JComboBox<String> boxSala;
	private JComboBox<String> boxSalaCont;
	private JComboBox<String> boxClimatizacion;
	private JComboBox<Integer> boxHora;
	private JComboBox<Integer> boxMinuto;
	private JComboBox<Integer> boxHora2;
	private JComboBox<Integer> boxMinuto2;

	private Integer[] horas = new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
			21, 22, 23 };
	private Integer[] minutos = new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
			20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46,
			47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59 };
	private Integer[] horas2 = new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
			21, 22, 23 };
	private Integer[] minutos2 = new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
			20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46,
			47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59 };

	private DefaultComboBoxModel<String> modeloDatosSala;
	private DefaultComboBoxModel<String> modeloDatosSalaElim;

	/**
	 * Constructor ConfigurarSalasWindow
	 */
	public ConfigurarSalasWindow() {

		layout = new SpringLayout();
		this.setLayout(layout);
		/****************************************************************************/
		Font font = new Font("Arial", Font.BOLD, 20);
		/****************************************************************************/
		boxSala = new JComboBox<String>(salas);
		boxSala.setPreferredSize(new Dimension(150, 25));
		/****************************************************************************/
		modeloDatosSala = new DefaultComboBoxModel<String>(salasCont);
		boxSalaCont = new JComboBox<String>(modeloDatosSala);
		boxSalaCont.setPreferredSize(new Dimension(150, 25));
		/****************************************************************************/
		etiqueta = new JLabel("CONFIGURAR SALAS");
		etiqueta.setFont(font);
		etiquetaNombre = new JLabel("Nombre Sala");
		etiquetaDescipcion = new JLabel("Descripcion");
		etiquetaAforo = new JLabel("Aforo Máximo");
		etiquetaSala = new JLabel("Tipo de Sala");
		etiquetaSalaCont = new JLabel("Sala Contenedor (Padre)");
		etiquetaClimatizacion = new JLabel("Climatizacion");
		etiquetaHora = new JLabel("Horario Climatizacion");
		etiquetaDesde = new JLabel("Desde las");
		etiquetaHasta = new JLabel("Hasta las");
		/****************************************************************************/
		botonHecho = new JButton("Hecho");
		botonHecho.setPreferredSize(new Dimension(150, 25));
		botonCancelar = new JButton("Cancelar");
		botonCancelar.setPreferredSize(new Dimension(150, 25));
		/****************************************************************************/
		campoNombre = new JTextField(20);
		campoDescipcion = new JTextField(20);
		campoAforo = new JTextField(20);
		/****************************************************************************/
		promptNombre = new TextPrompt("Sala 1", campoNombre);
		promptDescipcion = new TextPrompt("Sala general de la planta 1.", campoDescipcion);
		promptAforo = new TextPrompt("22", campoAforo);
		/****************************************************************************/

		boxSalaCont = new JComboBox<String>(salasCont);
		boxSalaCont.setPreferredSize(new Dimension(200, 25));

		boxSala = new JComboBox<String>(salas);
		boxSala.setPreferredSize(new Dimension(200, 25));

		boxClimatizacion = new JComboBox<String>(climatizacion);
		boxClimatizacion.setPreferredSize(new Dimension(200, 25));
		boxHora = new JComboBox<Integer>(horas);
		boxMinuto = new JComboBox<Integer>(minutos);
		boxHora2 = new JComboBox<Integer>(horas2);
		boxMinuto2 = new JComboBox<Integer>(minutos2);
		/****************************************************************************/
		promptNombre.changeAlpha(0.5f);
		promptDescipcion.changeAlpha(0.5f);
		promptAforo.changeAlpha(0.5f);
		/****************************************************************************/
		botonHecho.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonHecho.setBackground(Color.LIGHT_GRAY);

		botonCancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonCancelar.setBackground(Color.LIGHT_GRAY);
		/****************************************************************************/
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiqueta, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, etiqueta, 5, SpringLayout.NORTH, this);

		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaNombre, 0, SpringLayout.HORIZONTAL_CENTER,
				campoNombre);
		layout.putConstraint(SpringLayout.NORTH, etiquetaNombre, 5, SpringLayout.SOUTH, etiqueta);

		layout.putConstraint(SpringLayout.WEST, campoNombre, 10, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, campoNombre, 5, SpringLayout.SOUTH, etiquetaNombre);

		layout.putConstraint(SpringLayout.WEST, promptNombre, 10, SpringLayout.WEST, campoNombre);
		layout.putConstraint(SpringLayout.NORTH, promptNombre, 0, SpringLayout.NORTH, campoNombre);

		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaDescipcion, 0, SpringLayout.HORIZONTAL_CENTER,
				campoDescipcion);
		layout.putConstraint(SpringLayout.NORTH, etiquetaDescipcion, 5, SpringLayout.SOUTH, promptNombre);

		layout.putConstraint(SpringLayout.WEST, campoDescipcion, 10, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, campoDescipcion, 5, SpringLayout.SOUTH, etiquetaDescipcion);

		layout.putConstraint(SpringLayout.WEST, promptDescipcion, 10, SpringLayout.WEST, campoDescipcion);
		layout.putConstraint(SpringLayout.NORTH, promptDescipcion, 0, SpringLayout.NORTH, campoDescipcion);

		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaAforo, 0, SpringLayout.HORIZONTAL_CENTER,
				campoAforo);
		layout.putConstraint(SpringLayout.NORTH, etiquetaAforo, 5, SpringLayout.SOUTH, promptDescipcion);

		layout.putConstraint(SpringLayout.WEST, campoAforo, 10, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, campoAforo, 5, SpringLayout.SOUTH, etiquetaAforo);

		layout.putConstraint(SpringLayout.WEST, promptAforo, 10, SpringLayout.WEST, campoAforo);
		layout.putConstraint(SpringLayout.NORTH, promptAforo, 0, SpringLayout.NORTH, campoAforo);

		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaSala, 270, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, etiquetaSala, 5, SpringLayout.SOUTH, etiqueta);

		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, boxSala, 0, SpringLayout.HORIZONTAL_CENTER, etiquetaSala);
		layout.putConstraint(SpringLayout.NORTH, boxSala, 5, SpringLayout.SOUTH, etiquetaSala);

		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonHecho, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, botonHecho, 150, SpringLayout.SOUTH, campoAforo);

		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonCancelar, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, botonCancelar, 5, SpringLayout.SOUTH, botonHecho);

		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaSalaCont, 0, SpringLayout.HORIZONTAL_CENTER,
				etiquetaSala);
		layout.putConstraint(SpringLayout.NORTH, etiquetaSalaCont, 5, SpringLayout.SOUTH, boxSala);

		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, boxSalaCont, 0, SpringLayout.HORIZONTAL_CENTER, boxSala);
		layout.putConstraint(SpringLayout.NORTH, boxSalaCont, 5, SpringLayout.SOUTH, etiquetaSalaCont);
		/****************************************************************************/

		this.add(etiqueta);
		this.add(etiquetaNombre);
		this.add(promptNombre);
		this.add(campoNombre);
		this.add(etiquetaDescipcion);
		this.add(promptDescipcion);
		this.add(campoDescipcion);
		this.add(etiquetaAforo);
		this.add(promptAforo);
		this.add(campoAforo);
		this.add(etiquetaSala);
		this.add(boxSala);
		this.add(botonHecho);
		this.add(botonCancelar);
		this.add(etiquetaSalaCont);
		this.add(boxSalaCont);
	}

	/**
	 * GETTER
	 * 
	 * @return nombre
	 */
	public String getCampoNombre() {
		return this.campoNombre.getText();
	}

	/**
	 * GETTER
	 * 
	 * @return descripcion
	 */
	public String getCampoDescipcion() {
		return this.campoDescipcion.getText();
	}

	/**
	 * GETTER
	 * 
	 * @return aforo
	 */
	public Integer getCampoAforo() {
		return Integer.parseInt(this.campoAforo.getText());
	}
	
	/**
	 * GETTER
	 * 
	 * @return Climatizacion
	 */
	public String getBoxClimatizacion() {
		return this.boxClimatizacion.getSelectedItem().toString();
	}

	/**
	 * GETTER
	 * 
	 * @return sala
	 */
	public String getBoxSala() {
		return this.boxSala.getSelectedItem().toString();
	}

	/**
	 * GETTER
	 * 
	 * @return sala padre
	 */
	public String getBoxSalaCont() {
		return this.boxSalaCont.getSelectedItem().toString();
	}

	/**
	 * GETTER
	 * 
	 * @return fecha inicial
	 */
	public LocalDateTime getFechaIni() {
		return LocalDateTime.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue(),
				LocalDate.now().getDayOfMonth(), Integer.parseInt(boxHora.getSelectedItem().toString()),
				Integer.parseInt(boxMinuto.getSelectedItem().toString()));
	}

	/**
	 * GETTER
	 * 
	 * @return fecha fin
	 */
	public LocalDateTime getFechaFin() {
		return LocalDateTime.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue(),
				LocalDate.now().getDayOfMonth(), Integer.parseInt(boxHora2.getSelectedItem().toString()),
				Integer.parseInt(boxMinuto2.getSelectedItem().toString()));
	}

	/**
	 * Establece el nombre
	 * 
	 * @param texto el nombre
	 */
	public void setCampoNombre(String texto) {
		this.campoNombre.setText(texto);
		return;
	}

	/**
	 * Establece la descripcion
	 * 
	 * @param texto la descripcion
	 */
	public void setCampoDescipcion(String texto) {
		this.campoDescipcion.setText(texto);
		return;
	}

	/**
	 * Establece el aforo
	 * 
	 * @param texto afor
	 */
	public void setCampoAforo(String texto) {
		this.campoAforo.setText(texto);
		return;
	}

	/**
	 * Establece el controlador
	 * 
	 * @param controladorConfigurarSalas el controlador de la ventana
	 */
	public void setControlador(ControladorConfigurarSalas controladorConfigurarSalas) {
		botonHecho.addActionListener(controladorConfigurarSalas);
		botonCancelar.addActionListener(controladorConfigurarSalas);
		boxSala.addActionListener(controladorConfigurarSalas);
		boxClimatizacion.addActionListener(controladorConfigurarSalas);
	}

	/**
	 * Actualiza la caja
	 * 
	 * @param nuevasSalas las nuevas salas para añadir
	 */
	public void updateSalas(HashSet<String> nuevasSalas) {
		this.salasCont = nuevasSalas.toArray(new String[0]);
		modeloDatosSala.removeAllElements();

		for (String sala : this.salasCont) {
			modeloDatosSala.addElement(sala);
		}
		this.boxSalaCont.setModel(modeloDatosSala);
	}

	/**
	 * Eliminar el contenido
	 */
	public void updateContenidoElim1() {
		this.remove(etiquetaClimatizacion);
		this.remove(boxClimatizacion);
		this.remove(etiquetaHora);
		this.remove(etiquetaDesde);
		this.remove(etiquetaHasta);
		this.remove(boxHora);
		this.remove(boxMinuto);
		this.remove(boxHora2);
		this.remove(boxMinuto2);

		this.revalidate();
		this.repaint();
	}

	/**
	 * Eliminar el contenido
	 */
	public void updateContenidoElim2() {
		this.remove(etiquetaSalaCont);
		this.remove(boxSalaCont);
		this.revalidate();
		this.repaint();
	}

	/**
	 * Eliminar el contenido
	 */
	public void updateContenidoElim3() {
		this.remove(etiquetaHora);
		this.remove(etiquetaDesde);
		this.remove(etiquetaHasta);
		this.remove(boxHora);
		this.remove(boxMinuto);
		this.remove(boxHora2);
		this.remove(boxMinuto2);

		this.revalidate();
		this.repaint();
	}

	/**
	 * Actualizar el contenido
	 */
	public void updateContenido2() {
		this.updateContenidoElim1();
		this.updateContenidoElim2();

		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaSalaCont, 0, SpringLayout.HORIZONTAL_CENTER,
				etiquetaSala);
		layout.putConstraint(SpringLayout.NORTH, etiquetaSalaCont, 5, SpringLayout.SOUTH, boxSala);

		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, boxSalaCont, 0, SpringLayout.HORIZONTAL_CENTER, boxSala);
		layout.putConstraint(SpringLayout.NORTH, boxSalaCont, 5, SpringLayout.SOUTH, etiquetaSalaCont);

		this.add(etiquetaSalaCont);
		this.add(boxSalaCont);
	}

	/**
	 * Actualizar el contenido
	 */
	public void updateContenido1() {
		this.updateContenidoElim1();
		this.updateContenidoElim2();

		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaClimatizacion, 0, SpringLayout.HORIZONTAL_CENTER,
				boxSala);
		layout.putConstraint(SpringLayout.NORTH, etiquetaClimatizacion, 5, SpringLayout.SOUTH, boxSala);

		layout.putConstraint(SpringLayout.WEST, boxClimatizacion, 75, SpringLayout.EAST, etiqueta);
		layout.putConstraint(SpringLayout.NORTH, boxClimatizacion, 5, SpringLayout.SOUTH, etiquetaClimatizacion);

		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaHora, 0, SpringLayout.HORIZONTAL_CENTER, boxSala);
		layout.putConstraint(SpringLayout.NORTH, etiquetaHora, 5, SpringLayout.SOUTH, boxClimatizacion);

		layout.putConstraint(SpringLayout.WEST, boxHora, 95, SpringLayout.EAST, etiqueta);
		layout.putConstraint(SpringLayout.NORTH, boxHora, 5, SpringLayout.SOUTH, etiquetaHora);

		layout.putConstraint(SpringLayout.WEST, boxMinuto, 0, SpringLayout.EAST, boxHora);
		layout.putConstraint(SpringLayout.NORTH, boxMinuto, 5, SpringLayout.SOUTH, etiquetaHora);

		layout.putConstraint(SpringLayout.EAST, etiquetaDesde, -5, SpringLayout.WEST, boxHora);
		layout.putConstraint(SpringLayout.NORTH, etiquetaDesde, 5, SpringLayout.SOUTH, etiquetaHora);

		layout.putConstraint(SpringLayout.WEST, boxHora2, 95, SpringLayout.EAST, etiqueta);
		layout.putConstraint(SpringLayout.NORTH, boxHora2, 10, SpringLayout.SOUTH, etiquetaDesde);

		layout.putConstraint(SpringLayout.WEST, boxMinuto2, 0, SpringLayout.EAST, boxHora2);
		layout.putConstraint(SpringLayout.NORTH, boxMinuto2, 10, SpringLayout.SOUTH, etiquetaDesde);

		layout.putConstraint(SpringLayout.EAST, etiquetaHasta, -5, SpringLayout.WEST, boxHora2);
		layout.putConstraint(SpringLayout.NORTH, etiquetaHasta, 10, SpringLayout.SOUTH, etiquetaDesde);

		this.add(etiquetaClimatizacion);
		this.add(boxClimatizacion);
		this.add(etiquetaHora);
		this.add(etiquetaDesde);
		this.add(etiquetaHasta);
		this.add(boxHora);
		this.add(boxMinuto);
		this.add(boxHora2);
		this.add(boxMinuto2);
	}

}
