/**
 * Este fichero muestra todo lo que tiene que ver con la clase RegistroUsuariosWindow
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.*;

import aplicacion.usuario.tarifa.Tarifa;
import aplicacion.usuario.tarifa.TarjetaCredito;
import aplicacion.usuario.tarifa.TipoTarifaPlana;
import gui.controladores.ControladorLogin;
import gui.controladores.ControladorRegistroUsuarios;
import aplicacion.*;
import aplicacion.actividad.ActividadGrupal;
import aplicacion.actividad.TipoActividad;

/**
 * esta es una clase que representa RegistroUsuariosWindow
 * 
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class RegistroUsuariosWindow extends JPanel {
	
	private JButton botonRegistrarse;
	private JButton botonCancelar;
	
	private JTextField campoNombre;
	private JTextField campoUsuario;
	private JPasswordField campoContrasenya;
	private JTextField campoTelefono;
	private JTextField campoTarjeta;
	private JTextField campoTitular;
	private JPasswordField campoPin;
    
	private JComboBox<Integer> boxDias;
	private JComboBox<Integer> boxMeses;
	private JComboBox<Integer> boxAnyos;
	private JComboBox<String> boxTipoTarifa;
	private JComboBox<String> boxTipoActividadGrupal;
	
	private SpringLayout layout;
	
	private JLabel etiqueta;
	private JLabel etiquetaNombre;
	private JLabel etiquetaFecha;
	private JLabel etiquetaUsuario;
	private JLabel etiquetaContrasenya;
	private JLabel etiquetaTelefono;
	private JLabel etiquetaTarifa;
	private JLabel etiquetaTarjeta;
	private JLabel etiquetaTitular;
	private JLabel etiquetaPin;
	private JLabel etiquetaActividadGrupal;
	
	private TextPrompt promptNombre;
	private TextPrompt promptUsuario;
	private TextPrompt promptContrasenya;
	private TextPrompt promptTelefono;
	private TextPrompt promptTarjeta;
	private TextPrompt promptTitular;
	private TextPrompt promptPin;
	private String [] tipoActividad = new String [] {};
	private DefaultComboBoxModel<String> modeloDatosActividad;
	
	/**
	 * El constructor RegistroUsuariosWindow
	 */
	public RegistroUsuariosWindow() {

		layout = new SpringLayout();
        this.setLayout(layout);
        /****************************************************************************/
        Font font = new Font("Arial", Font.BOLD, 20);
        /****************************************************************************/
        etiqueta = new JLabel("REGISTRO DE USUARIOS");
        etiqueta.setFont(font);
        etiquetaNombre = new JLabel("Nombre");
        etiquetaFecha = new JLabel("Fecha de Nacimiento ");
        etiquetaUsuario = new JLabel("Usuario");
        etiquetaContrasenya = new JLabel("Contraseña");
        etiquetaTelefono = new JLabel("Teléfono");
        etiquetaTarifa = new JLabel("Tipo de Tarifa");
        etiquetaTarjeta = new JLabel("Número Tarjeta de Crédito");
        etiquetaTitular = new JLabel("Titular de la Tarjeta");
        etiquetaPin = new JLabel("PIN Tarjeta");
        etiquetaActividadGrupal = new JLabel("Actividad Grupal (Tarifa Plana)");
        /****************************************************************************/
        campoNombre = new JTextField(20);
        campoUsuario = new JTextField(20);
        campoContrasenya = new JPasswordField(20);
        campoTelefono = new JTextField(20);
        campoTarjeta = new JTextField(20);
        campoTitular = new JTextField(20);
        campoPin = new JPasswordField(20);
        /****************************************************************************/
        //ESTO SE PUEDE PONER COMO INT Y SERIA MAS FACIL
        Integer[] dias = {1,2,3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};
        boxDias = new JComboBox<Integer>(dias);
        Integer[] meses = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        boxMeses = new JComboBox<Integer>(meses);
        Integer[] anyos = {1930, 1931, 1932, 1933, 1934, 1935, 1936, 1937, 1938, 1939, 1940, 1941, 1942, 1943, 1944, 1945, 1946, 1947, 1948, 1949, 1950, 1951, 1952, 1953, 1954, 1955, 1956, 1957, 1958, 1959, 1960, 1961, 1962, 1963, 1964, 1965, 1966, 1967, 1968, 1969, 1970, 1971, 1972, 1973, 1974, 1975, 1976, 1977, 1978, 1979, 1980, 1981, 1982, 1983, 1984, 1985, 1986, 1987, 1988, 1989, 1990, 1991, 1992, 1993, 1994, 1995, 1996, 1997, 1998, 1999, 2000, 2001, 2002, 2003, 2004, 2005, 2006, 2007, 2008, 2009, 2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017, 2018, 2019, 2020, 2021, 2022, 2023};
        boxAnyos = new JComboBox<Integer>(anyos);
        String[] tipoTarifa = {"Tarifa Uso", "Tarifa Plana (ANUAL)", "Tarifa Plana (MENSUAL)", "Tarifa Plana (TRIMESTRAL)"};
        boxTipoTarifa = new JComboBox<String>(tipoTarifa);
        modeloDatosActividad = new DefaultComboBoxModel<String>(tipoActividad);
        boxTipoActividadGrupal = new JComboBox<String>(modeloDatosActividad);
        /****************************************************************************/      
        botonRegistrarse = new JButton("Registrarse");
        botonRegistrarse.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        botonRegistrarse.setBackground(Color.LIGHT_GRAY);
        
        botonCancelar = new JButton("Cancelar");
        botonCancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        botonCancelar.setBackground(Color.LIGHT_GRAY);
        /****************************************************************************/   
        promptNombre = new TextPrompt("Nombre Apellido1 Apellido2", campoNombre);
        promptUsuario = new TextPrompt("Nombre Usuario", campoUsuario);
        promptContrasenya = new TextPrompt("Contraseña", campoContrasenya);
        promptTelefono = new TextPrompt("+34 000 000 000", campoTelefono);
        promptTarjeta = new TextPrompt("xxxx-xxxx-xxxx-xxxx", campoTarjeta);
        promptTitular = new TextPrompt("Nombre Apellido1 Apellido2", campoTitular);
        promptPin = new TextPrompt("Pin", campoPin);
        /****************************************************************************/          
        promptNombre.changeAlpha(0.5f);
        promptUsuario.changeAlpha(0.5f);
        promptContrasenya.changeAlpha(0.5f);        
        promptTelefono.changeAlpha(0.5f);
        promptTarjeta.changeAlpha(0.5f);        
        promptTitular.changeAlpha(0.5f);        
        promptPin.changeAlpha(0.5f);
        /****************************************************************************/  
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiqueta, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, etiqueta, 5, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.WEST, etiquetaNombre, 10, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, etiquetaNombre, 5, SpringLayout.SOUTH, etiqueta);
        
        layout.putConstraint(SpringLayout.WEST, campoNombre, 10, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, campoNombre, 5, SpringLayout.SOUTH, etiquetaNombre);
        
        layout.putConstraint(SpringLayout.WEST, promptNombre, 10, SpringLayout.WEST, campoNombre);
        layout.putConstraint(SpringLayout.NORTH, promptNombre, 0, SpringLayout.NORTH, campoNombre);
        
        layout.putConstraint(SpringLayout.WEST, etiquetaFecha, 10, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, etiquetaFecha, 5, SpringLayout.SOUTH, campoNombre);
        
        layout.putConstraint(SpringLayout.WEST, boxDias, 10, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, boxDias, 5, SpringLayout.SOUTH, etiquetaFecha);
        
        layout.putConstraint(SpringLayout.WEST, boxMeses, 0, SpringLayout.EAST, boxDias);
        layout.putConstraint(SpringLayout.NORTH, boxMeses, 5, SpringLayout.SOUTH, etiquetaFecha);
        
        layout.putConstraint(SpringLayout.WEST, boxAnyos, 0, SpringLayout.EAST, boxMeses);
        layout.putConstraint(SpringLayout.NORTH, boxAnyos, 5, SpringLayout.SOUTH, etiquetaFecha);        

        layout.putConstraint(SpringLayout.WEST, etiquetaUsuario, 10, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, etiquetaUsuario, 5, SpringLayout.SOUTH, boxAnyos);
        
        layout.putConstraint(SpringLayout.WEST, campoUsuario, 10, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, campoUsuario, 5, SpringLayout.SOUTH, etiquetaUsuario);
        
        layout.putConstraint(SpringLayout.WEST, promptUsuario, 10, SpringLayout.WEST, campoUsuario);
        layout.putConstraint(SpringLayout.NORTH, promptUsuario, 0, SpringLayout.NORTH, campoUsuario);
        
        layout.putConstraint(SpringLayout.WEST, etiquetaContrasenya, 10, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, etiquetaContrasenya, 5, SpringLayout.SOUTH, promptUsuario);
        
        layout.putConstraint(SpringLayout.WEST, campoContrasenya, 10, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, campoContrasenya, 5, SpringLayout.SOUTH, etiquetaContrasenya);
        
        layout.putConstraint(SpringLayout.WEST, promptContrasenya, 10, SpringLayout.WEST, campoContrasenya);
        layout.putConstraint(SpringLayout.NORTH, promptContrasenya, 0, SpringLayout.NORTH, campoContrasenya);
        
        layout.putConstraint(SpringLayout.WEST, etiquetaTelefono, 10, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, etiquetaTelefono, 5, SpringLayout.SOUTH, promptContrasenya);
        
        layout.putConstraint(SpringLayout.WEST, campoTelefono, 10, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, campoTelefono, 5, SpringLayout.SOUTH, etiquetaTelefono);
        
        layout.putConstraint(SpringLayout.WEST, promptTelefono, 10, SpringLayout.WEST, campoTelefono);
        layout.putConstraint(SpringLayout.NORTH, promptTelefono, 0, SpringLayout.NORTH, campoTelefono);
        
        layout.putConstraint(SpringLayout.WEST, etiquetaTarifa, 75, SpringLayout.EAST, etiqueta);
        layout.putConstraint(SpringLayout.NORTH, etiquetaTarifa, 5, SpringLayout.SOUTH, etiqueta);
        
        layout.putConstraint(SpringLayout.WEST, boxTipoTarifa, 75, SpringLayout.EAST, etiqueta);
        layout.putConstraint(SpringLayout.NORTH, boxTipoTarifa, 5, SpringLayout.SOUTH, etiquetaTarifa);
        
        layout.putConstraint(SpringLayout.WEST, etiquetaTarjeta, 75, SpringLayout.EAST, etiqueta);
        layout.putConstraint(SpringLayout.NORTH, etiquetaTarjeta, 5, SpringLayout.SOUTH, boxTipoTarifa);
        
        layout.putConstraint(SpringLayout.WEST, campoTarjeta, 75, SpringLayout.EAST, etiqueta);
        layout.putConstraint(SpringLayout.NORTH, campoTarjeta, 5, SpringLayout.SOUTH, etiquetaTarjeta);
        
        layout.putConstraint(SpringLayout.WEST, promptTarjeta, 10, SpringLayout.WEST, campoTarjeta);
        layout.putConstraint(SpringLayout.NORTH, promptTarjeta, 0, SpringLayout.NORTH, campoTarjeta);
        
        layout.putConstraint(SpringLayout.WEST, etiquetaTitular, 75, SpringLayout.EAST, etiqueta);
        layout.putConstraint(SpringLayout.NORTH, etiquetaTitular, 5, SpringLayout.SOUTH, promptTarjeta);
        
        layout.putConstraint(SpringLayout.WEST, campoTitular, 75, SpringLayout.EAST, etiqueta);
        layout.putConstraint(SpringLayout.NORTH, campoTitular, 5, SpringLayout.SOUTH, etiquetaTitular);
        
        layout.putConstraint(SpringLayout.WEST, promptTitular, 10, SpringLayout.WEST, campoTitular);
        layout.putConstraint(SpringLayout.NORTH, promptTitular, 0, SpringLayout.NORTH, campoTitular);
        
        layout.putConstraint(SpringLayout.WEST, etiquetaPin, 75, SpringLayout.EAST, etiqueta);
        layout.putConstraint(SpringLayout.NORTH, etiquetaPin, 5, SpringLayout.SOUTH, promptTitular);
        
        layout.putConstraint(SpringLayout.WEST, campoPin, 75, SpringLayout.EAST, etiqueta);
        layout.putConstraint(SpringLayout.NORTH, campoPin, 5, SpringLayout.SOUTH, etiquetaPin);
        
        layout.putConstraint(SpringLayout.WEST, promptPin, 10, SpringLayout.WEST, campoPin);
        layout.putConstraint(SpringLayout.NORTH, promptPin, 0, SpringLayout.NORTH, campoPin);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonRegistrarse, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, botonRegistrarse, 0, SpringLayout.SOUTH, campoTelefono);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonCancelar, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, botonCancelar, 5, SpringLayout.SOUTH, botonRegistrarse);
        
        layout.putConstraint(SpringLayout.WEST, etiquetaActividadGrupal, 75, SpringLayout.EAST, etiqueta);
        layout.putConstraint(SpringLayout.NORTH, etiquetaActividadGrupal, 5, SpringLayout.SOUTH, promptPin);
        
        layout.putConstraint(SpringLayout.WEST, boxTipoActividadGrupal, 75, SpringLayout.EAST, etiqueta);
        layout.putConstraint(SpringLayout.NORTH, boxTipoActividadGrupal, 5, SpringLayout.SOUTH, etiquetaActividadGrupal);
        /****************************************************************************/  
        this.add(etiqueta);
        this.add(etiquetaNombre);
        this.add(promptNombre);
        this.add(campoNombre);
        this.add(etiquetaFecha);
        this.add(boxDias);
        this.add(boxMeses);
        this.add(boxAnyos);
        this.add(promptUsuario);
        this.add(etiquetaUsuario);
        this.add(campoUsuario);
        this.add(etiquetaContrasenya);
        this.add(promptContrasenya);
        this.add(campoContrasenya);
        this.add(etiquetaTelefono);
        this.add(promptTelefono);
        this.add(campoTelefono);
        this.add(etiquetaTarifa);
        this.add(boxTipoTarifa);
        this.add(etiquetaTarjeta);
        this.add(promptTarjeta);
        this.add(campoTarjeta);
        this.add(etiquetaTitular);
        this.add(promptTitular);
        this.add(campoTitular);
        this.add(etiquetaPin);
        this.add(promptPin);
        this.add(campoPin);
        this.add(botonRegistrarse);
        this.add(botonCancelar);  
        this.add(etiquetaActividadGrupal);
        this.add(boxTipoActividadGrupal);
    }
	
	/**
	 * GETTER
	 * @return el nombre
	 */
	public String getCampoNombre()
	{
		return this.campoNombre.getText();
	}
	
	/**
	 * GETTER
	 * @return el usuario
	 */
	public String getCampoUsuario ()
	{
		return this.campoUsuario.getText();
	}
	
    /**
     * GETTER
     * @return la contraseña
     */
    public String getCampoContrasenya()
    {
    	return String.valueOf(this.campoContrasenya.getPassword());
    }
    
    /**
     * GETTER
     * @return el telefono
     */
    public String getCampoTelefono()
    {
    	return this.campoTelefono.getText();
    }
    
    /**
     * GETTER
     * @return la tarjeta
     */
    public String getCampoTarjeta()
    {
    	return this.campoTarjeta.getText();
    }
    
    /**
     * GETTER
     * @return el titular
     */
    public String getCampoTitular()
    {
    	return this.campoTitular.getText();
    }
    
    /**
     * GETTER
     * @return el pin
     */
    public String getCampoPin()
    {
    	return String.valueOf(this.campoPin.getPassword());
    }
    
    /**
     * GETTER
     * @return la fecha
     */
    public LocalDate getFecha()
    {
    	return LocalDate.of(Integer.parseInt(boxAnyos.getSelectedItem().toString()), Integer.parseInt(boxMeses.getSelectedItem().toString()), Integer.parseInt(boxDias.getSelectedItem().toString()));
    }
	
    /**
     * GETTER
     * @return la tarifa
     */
    public String getTipoTarifa()
    {
    	return boxTipoTarifa.getSelectedItem().toString();
    }
    
    /**
     * Obtener el tipo de tarifa
     * @param texto el tipo de tarifa
     * @return el tipo de tarifa
     */
    public TipoTarifaPlana tipoTarifaPlana(String texto)
    {
    	if(texto.contains("ANUAL"))
    		return TipoTarifaPlana.ANUAL;
    	else if(texto.contains("MENSUAL"))
    		return TipoTarifaPlana.MENSUAL;
    	else if(texto.contains("TRIMESTRAL"))
    		return TipoTarifaPlana.TRIMESTRAL;
    		
    	return null;
    }
    
    /**
     * GETTER
     * @return la actividad grupal
     */
    public String getActividadGrupal()
    {
    	return boxTipoActividadGrupal.getSelectedItem().toString();
    }
    
    /**
     * GETTER
     * @param texto el tipo de actividad
     * @return el tipo de actividad
     */
    public TipoActividad tipoActividad(String texto)
    {
    	if(texto == null)
    			return null;
    	
    	TipoActividad tipoActividad = new TipoActividad(texto); 
    
    	return tipoActividad;

    }
    
    /**
     * SETTER
     * @param texto el nombre
     */
    public void setCampoNombre(String texto)
	{
		this.campoNombre.setText(texto);
		return;
	}
	
    /**
     * SETTER
     * @param texto el usuario
     */
	public void setCampoUsuario (String texto)
	{
		this.campoUsuario.setText(texto);
		return;
	}
	
    
	/**
	 * SETTER
	 * @param texto la contraseña
	 */
    public void setCampoContrasenya(String texto)
    {
    	this.campoContrasenya.setText(texto);
    	return;
    }
    
    /**
     * SETTER
     * @param texto el telefono
     */
    public void setCampoTelefono(String texto)
    {
    	this.campoTelefono.setText(texto);
    	return;
    }
    
    /**
     * SETTER
     * @param texto la tarjeta
     */
    public void setCampoTarjeta(String texto)
    {
    	this.campoTarjeta.setText(texto);
    	return;
    }
    
    /**
     * SETTER
     * @param texto el titular
     */
    public void setCampoTitular(String texto)
    {
    	this.campoTitular.setText(texto);
    	return;
    }
    
    /**
     * SETTER
     * @param texto el pin
     */
    public void setCampoPin(String texto)
    {
    	this.campoPin.setText(texto);
    	return;
    }
 
    
    /**
     * Establece el controlador
     * @param controladorRegistroUsuarios el controlador de la window
     */
	public void setControlador(ControladorRegistroUsuarios controladorRegistroUsuarios) {
		botonCancelar.addActionListener(controladorRegistroUsuarios);
		botonRegistrarse.addActionListener(controladorRegistroUsuarios);
		boxTipoTarifa.addActionListener(controladorRegistroUsuarios);
	}

	/**
	 * Eliminar el contenido
	 */
	public void updateContenidoElim1() {
	    this.remove(etiquetaActividadGrupal);
	    this.remove(boxTipoActividadGrupal);

	    this.revalidate();
	    this.repaint();
	}
	
	/**
	 * Actualizar el contenido
	 */
	public void updateContenido2()
	{
		this.updateContenidoElim1();
		
		layout.putConstraint(SpringLayout.WEST, etiquetaActividadGrupal, 75, SpringLayout.EAST, etiqueta);
        layout.putConstraint(SpringLayout.NORTH, etiquetaActividadGrupal, 5, SpringLayout.SOUTH, promptPin);
        
        layout.putConstraint(SpringLayout.WEST, boxTipoActividadGrupal, 75, SpringLayout.EAST, etiqueta);
        layout.putConstraint(SpringLayout.NORTH, boxTipoActividadGrupal, 5, SpringLayout.SOUTH, etiquetaActividadGrupal);
        
        this.add(etiquetaActividadGrupal);
        this.add(boxTipoActividadGrupal);
	}
	
	/**
	 * Actualizar la caja
	 * @param tipoActividades la lista con el tipo de actividades
	 */
	public void updateTipoActividad(HashSet<String> tipoActividades) {
		this.tipoActividad = tipoActividades.toArray(new String[0]);
	    modeloDatosActividad=null;
	    modeloDatosActividad = new DefaultComboBoxModel<String>(tipoActividad);
		this.boxTipoActividadGrupal.setModel(modeloDatosActividad);
	}
}
