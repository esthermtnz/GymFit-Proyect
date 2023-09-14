/**
 * Este fichero muestra todo lo que tiene que ver con la clase AdministradorWindow
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
package gui.administrador;

import java.awt.*;

import javax.swing.*;

import aplicacion.usuario.Administrador;
import gui.controladores.administrador.*;
import gui.controladores.ControladorLogin;

/**
 * esta es una clase que representa AdministradorWindow
 * 
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class AdministradorWindow extends JPanel{
	private JButton botonRegistroBeneficios;
	private JButton botonConfigurarSala;
	private JButton botonCrearActividadGrupal;
	private JButton botonCrearSesionLibre;
	private JButton botonConsultaReservasCancelaciones;
	private JButton botonConsultaPlanesMonitor;
	private JButton botonConfigurarSueldoMonitores;
	private JButton botonConfigurarPrecios;
	private JButton botonConfigurarPenalizacionesClientes;
	private JButton botonDarAltaMonitor;
	private JButton botonCerrarSesion;
	private JButton botonTipoActividad;
	private JButton botonConsultaActividadesGrupales;
	private JButton botonDarMaterialAlta;
	private JButton botonDarMaquinaAlta;
	private JButton botonConsultarGastosEquipacion;
	private JButton botonCambiarEstado;
	private JButton botonConsultarSesionLibreAdmin;
	
	private JLabel modeloDatosNombre;
	
	private JLabel etiqueta;
	private JLabel etiquetaDatosAdmin;
    private JLabel etiquetaDatosGenerales;
    private JLabel etiquetaSalasActividades;
    private JLabel etiquetaSueldosPrecios;
    private JLabel etiquetaConfiguracionUsuarios;
    private JLabel etiquetaUsuario;
    private JLabel etiquetaEquipacion;
	
	private SpringLayout layout;
	
	/**
	 * Constructor AdministradorWindow
	 */
	public AdministradorWindow()
	{
		layout = new SpringLayout();
        this.setLayout(layout);     
        /****************************************************************************/
        Font font = new Font("Arial", Font.BOLD, 20);
        /****************************************************************************/
        etiqueta = new JLabel("INTERFAZ ADMINISTRADOR");
        etiqueta.setFont(font);
        etiquetaDatosAdmin = new JLabel("DATOS ADMINISTRADOR");
        etiquetaDatosGenerales = new JLabel("DATOS GENERALES");
        etiquetaSalasActividades = new JLabel("SALAS Y ACTIVIDADES");
        etiquetaSueldosPrecios = new JLabel("SUELDOS Y PRECIOS");
        etiquetaConfiguracionUsuarios = new JLabel("CONFIGURACION USUARIOS");
        etiquetaUsuario = new JLabel("Usuario: ");
        etiquetaEquipacion = new JLabel("EQUIPACION");
        
	    modeloDatosNombre = new JLabel("");
        /****************************************************************************/
        botonRegistroBeneficios = new JButton("Registro de beneficios");
        botonRegistroBeneficios.setPreferredSize(new Dimension(260, 25));
    	botonConfigurarSala = new JButton("Configurar Sala");
    	botonConfigurarSala.setPreferredSize(new Dimension(280, 25));
    	botonCrearActividadGrupal = new JButton("Crear Actividad Grupal");
    	botonCrearActividadGrupal.setPreferredSize(new Dimension(280, 25));
    	botonCrearSesionLibre = new JButton("Crear Sesion Libre");
    	botonCrearSesionLibre.setPreferredSize(new Dimension(280, 25));
    	botonDarMaterialAlta = new JButton("Dar Material Alta");
    	botonDarMaterialAlta.setPreferredSize(new Dimension(280, 25));
    	botonDarMaquinaAlta = new JButton("Dar Maquina Alta");
    	botonDarMaquinaAlta.setPreferredSize(new Dimension(280, 25));
    	botonConsultarGastosEquipacion= new JButton("Consultar Gastos Equipacion");
    	botonConsultarGastosEquipacion.setPreferredSize(new Dimension(280, 25));
    	botonCambiarEstado = new JButton("Cambiar Estado Maquinas");
    	botonCambiarEstado.setPreferredSize(new Dimension(280, 25));
    	botonConsultaReservasCancelaciones = new JButton("Consulta de reservas");
    	botonConsultaReservasCancelaciones.setPreferredSize(new Dimension(280, 25));
    	botonConsultaPlanesMonitor = new JButton("Consulta de planes monitor");
    	botonConsultaPlanesMonitor.setPreferredSize(new Dimension(280, 25));
    	botonConsultaActividadesGrupales = new JButton("Consulta de actividades monitor");
    	botonConsultaActividadesGrupales.setPreferredSize(new Dimension(280, 25));
    	botonConsultarSesionLibreAdmin = new JButton("Consulta Sesiones Libres");
    	botonConsultarSesionLibreAdmin.setPreferredSize(new Dimension(280, 25));
    	botonConfigurarSueldoMonitores = new JButton("Configurar Sueldos Monitores");
    	botonConfigurarSueldoMonitores.setPreferredSize(new Dimension(260, 25));
    	botonConfigurarPrecios = new JButton("Configurar Precios");
    	botonConfigurarPrecios.setPreferredSize(new Dimension(260, 25));
    	botonConfigurarPenalizacionesClientes = new JButton("Configurar Penalizaciones a clientes");
    	botonConfigurarPenalizacionesClientes.setPreferredSize(new Dimension(260, 25));
    	botonDarAltaMonitor = new JButton("Dar de alta a un monitor");
    	botonDarAltaMonitor.setPreferredSize(new Dimension(260, 25));
    	botonCerrarSesion = new JButton("Cerrar Sesion");
    	botonTipoActividad = new JButton("Crear Tipo Actividad");
    	botonTipoActividad.setPreferredSize(new Dimension(280, 25));
    	/****************************************************************************/
    	botonRegistroBeneficios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    	botonRegistroBeneficios.setBackground(Color.LIGHT_GRAY);
    	
    	botonConfigurarSala.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    	botonConfigurarSala.setBackground(Color.LIGHT_GRAY);
    	
    	botonCrearActividadGrupal.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    	botonCrearActividadGrupal.setBackground(Color.LIGHT_GRAY);
    	
    	botonCrearSesionLibre.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    	botonCrearSesionLibre.setBackground(Color.LIGHT_GRAY);
    	
    	botonConsultaReservasCancelaciones.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    	botonConsultaReservasCancelaciones.setBackground(Color.LIGHT_GRAY);
    	
    	botonConsultaPlanesMonitor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    	botonConsultaPlanesMonitor.setBackground(Color.LIGHT_GRAY);
    	
    	botonConfigurarSueldoMonitores.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    	botonConfigurarSueldoMonitores.setBackground(Color.LIGHT_GRAY);
    	
    	botonConfigurarPrecios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    	botonConfigurarPrecios.setBackground(Color.LIGHT_GRAY);
    	
    	botonConfigurarPenalizacionesClientes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    	botonConfigurarPenalizacionesClientes.setBackground(Color.LIGHT_GRAY);
    	
    	botonDarAltaMonitor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    	botonDarAltaMonitor.setBackground(Color.LIGHT_GRAY);
		
		botonCerrarSesion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonCerrarSesion.setBackground(Color.LIGHT_GRAY);
		
		botonTipoActividad.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonTipoActividad.setBackground(Color.LIGHT_GRAY);
		
		botonConsultaActividadesGrupales.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonConsultaActividadesGrupales.setBackground(Color.LIGHT_GRAY);
		
		botonDarMaterialAlta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonDarMaterialAlta.setBackground(Color.LIGHT_GRAY);
		
		botonDarMaquinaAlta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonDarMaquinaAlta.setBackground(Color.LIGHT_GRAY);
		
		botonConsultarGastosEquipacion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonConsultarGastosEquipacion.setBackground(Color.LIGHT_GRAY);
		
		botonCambiarEstado.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonCambiarEstado.setBackground(Color.LIGHT_GRAY);
		
		botonConsultarSesionLibreAdmin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonConsultarSesionLibreAdmin.setBackground(Color.LIGHT_GRAY);
        /****************************************************************************/
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiqueta, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, etiqueta, 5, SpringLayout.NORTH, this);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaDatosAdmin, 0, SpringLayout.HORIZONTAL_CENTER, etiquetaDatosGenerales);
        layout.putConstraint(SpringLayout.NORTH, etiquetaDatosAdmin, 20, SpringLayout.SOUTH, etiqueta);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaUsuario, 0, SpringLayout.HORIZONTAL_CENTER, etiquetaDatosGenerales);
        layout.putConstraint(SpringLayout.NORTH, etiquetaUsuario, 20, SpringLayout.SOUTH, etiquetaDatosAdmin);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaSalasActividades, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, etiquetaSalasActividades, 20, SpringLayout.SOUTH, etiqueta);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaDatosGenerales, 0, SpringLayout.HORIZONTAL_CENTER, botonRegistroBeneficios);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, etiquetaDatosGenerales, 0, SpringLayout.VERTICAL_CENTER, this);
        
        layout.putConstraint(SpringLayout.WEST, botonRegistroBeneficios, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, botonRegistroBeneficios, 5, SpringLayout.SOUTH, etiquetaDatosGenerales);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonConfigurarSala, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, botonConfigurarSala, 5, SpringLayout.SOUTH, etiquetaSalasActividades);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonCrearActividadGrupal, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, botonCrearActividadGrupal, 5, SpringLayout.SOUTH, botonConfigurarSala);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonCrearSesionLibre, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, botonCrearSesionLibre, 5, SpringLayout.SOUTH, botonCrearActividadGrupal);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonConsultaReservasCancelaciones, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, botonConsultaReservasCancelaciones, 5, SpringLayout.SOUTH, botonCrearSesionLibre);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonConsultaPlanesMonitor, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, botonConsultaPlanesMonitor, 5, SpringLayout.SOUTH, botonConsultaReservasCancelaciones);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonConsultaActividadesGrupales, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, botonConsultaActividadesGrupales, 5, SpringLayout.SOUTH, botonConsultaPlanesMonitor);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonTipoActividad, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, botonTipoActividad, 5, SpringLayout.SOUTH, botonConsultaActividadesGrupales);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonConsultarSesionLibreAdmin, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, botonConsultarSesionLibreAdmin, 5, SpringLayout.SOUTH, botonTipoActividad);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaEquipacion, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, etiquetaEquipacion, 15, SpringLayout.SOUTH, botonConsultarSesionLibreAdmin);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonDarMaterialAlta, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, botonDarMaterialAlta, 5, SpringLayout.SOUTH, etiquetaEquipacion);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonDarMaquinaAlta, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, botonDarMaquinaAlta, 5, SpringLayout.SOUTH, botonDarMaterialAlta);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonConsultarGastosEquipacion, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, botonConsultarGastosEquipacion, 5, SpringLayout.SOUTH, botonDarMaquinaAlta);
		
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonCambiarEstado, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, botonCambiarEstado, 5, SpringLayout.SOUTH, botonConsultarGastosEquipacion);
        		        
        layout.putConstraint(SpringLayout.EAST, etiquetaSueldosPrecios, 300, SpringLayout.EAST, etiquetaSalasActividades);
        layout.putConstraint(SpringLayout.NORTH, etiquetaSueldosPrecios, 20, SpringLayout.SOUTH, etiqueta);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonConfigurarSueldoMonitores, 0, SpringLayout.HORIZONTAL_CENTER, etiquetaSueldosPrecios);
        layout.putConstraint(SpringLayout.NORTH, botonConfigurarSueldoMonitores, 5, SpringLayout.SOUTH, etiquetaSueldosPrecios);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonConfigurarPrecios, 0, SpringLayout.HORIZONTAL_CENTER, etiquetaSueldosPrecios);
        layout.putConstraint(SpringLayout.NORTH, botonConfigurarPrecios, 5, SpringLayout.SOUTH, botonConfigurarSueldoMonitores);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiquetaConfiguracionUsuarios, 0, SpringLayout.HORIZONTAL_CENTER, etiquetaSueldosPrecios);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, etiquetaConfiguracionUsuarios, 0, SpringLayout.VERTICAL_CENTER, this);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonConfigurarPenalizacionesClientes, 0, SpringLayout.HORIZONTAL_CENTER, etiquetaSueldosPrecios);
        layout.putConstraint(SpringLayout.NORTH, botonConfigurarPenalizacionesClientes, 5, SpringLayout.SOUTH, etiquetaConfiguracionUsuarios);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, modeloDatosNombre, 0, SpringLayout.HORIZONTAL_CENTER, etiquetaDatosGenerales);
        layout.putConstraint(SpringLayout.NORTH, modeloDatosNombre, 5, SpringLayout.SOUTH, etiquetaUsuario);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonDarAltaMonitor, 0, SpringLayout.HORIZONTAL_CENTER, etiquetaSueldosPrecios);
        layout.putConstraint(SpringLayout.NORTH, botonDarAltaMonitor, 5, SpringLayout.SOUTH, botonConfigurarPenalizacionesClientes);
        
        layout.putConstraint(SpringLayout.EAST, botonCerrarSesion, -5, SpringLayout.EAST, this);
        layout.putConstraint(SpringLayout.SOUTH, botonCerrarSesion, -5, SpringLayout.SOUTH, this);
    	/****************************************************************************/

    	this.add(botonRegistroBeneficios);
    	this.add(botonConfigurarSala);
    	this.add(botonCrearActividadGrupal);
    	this.add(botonCrearSesionLibre);
    	this.add(botonConsultaReservasCancelaciones);
    	this.add(botonConsultaPlanesMonitor);
    	this.add(botonTipoActividad);
    	this.add(botonConfigurarSueldoMonitores);
    	this.add(botonConfigurarPrecios);
    	this.add(botonConfigurarPenalizacionesClientes);
    	this.add(botonDarAltaMonitor);
    	this.add(botonCerrarSesion);
    	this.add(etiqueta);
    	this.add(etiquetaDatosAdmin);
    	this.add(etiquetaDatosGenerales);
    	this.add(etiquetaSalasActividades);
    	this.add(etiquetaSueldosPrecios);
    	this.add(etiquetaConfiguracionUsuarios);
    	this.add(etiquetaUsuario);
        this.add(modeloDatosNombre);
        this.add(botonConsultaActividadesGrupales);
        this.add(botonDarMaterialAlta);
        this.add(etiquetaEquipacion);
        this.add(botonDarMaquinaAlta);
        this.add(botonConsultarGastosEquipacion);
        this.add(botonCambiarEstado);
        this.add(botonConsultarSesionLibreAdmin);
    	
	}

	/**
	 * Establece el controlador
	 * @param controladorAdministador el controlador de la ventana
	 */
	public void setControlador(ControladorAdministrador controladorAdministador) {
		 	botonRegistroBeneficios.addActionListener(controladorAdministador);
	    	botonConfigurarSala.addActionListener(controladorAdministador);
	    	botonCrearActividadGrupal.addActionListener(controladorAdministador);
	    	botonCrearSesionLibre.addActionListener(controladorAdministador);
	    	botonConsultaReservasCancelaciones.addActionListener(controladorAdministador);
	    	botonConsultaPlanesMonitor.addActionListener(controladorAdministador);
	    	botonTipoActividad.addActionListener(controladorAdministador);
	    	botonConfigurarSueldoMonitores.addActionListener(controladorAdministador);
	    	botonConfigurarPrecios.addActionListener(controladorAdministador);
	    	botonConfigurarPenalizacionesClientes.addActionListener(controladorAdministador);
	    	botonDarAltaMonitor.addActionListener(controladorAdministador);
	    	botonCerrarSesion.addActionListener(controladorAdministador);
	    	botonConsultaActividadesGrupales.addActionListener(controladorAdministador);
	    	botonDarMaterialAlta.addActionListener(controladorAdministador);
	    	botonDarMaquinaAlta.addActionListener(controladorAdministador);
	    	botonConsultarGastosEquipacion.addActionListener(controladorAdministador);
	    	botonCambiarEstado.addActionListener(controladorAdministador);
	    	botonConsultarSesionLibreAdmin.addActionListener(controladorAdministador);
	}

	/**
	 * Actualiza el usuario
	 * @param usuario el usuario que va a actualizar
	 */
	public void update(String usuario) {
		this.modeloDatosNombre.setText(usuario);
		this.modeloDatosNombre.setForeground(Color.RED);
	}
	
}
