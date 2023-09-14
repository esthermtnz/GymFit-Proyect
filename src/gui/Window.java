/**
 * Este fichero muestra todo lo que tiene que ver con la clase Window
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
package gui;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import aplicacion.*;
import gui.administrador.*;
import gui.cliente.*;
import gui.controladores.*;
import gui.controladores.administrador.*;
import gui.controladores.cliente.*;
import gui.controladores.monitor.*;
import gui.monitor.*;

/**
 * esta es una clase que representa Window
 * 
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class Window extends JFrame{	
	private LoginWindow loginWindow;
	private RegistroUsuariosWindow registroUsuariosWindow;
	private AdministradorWindow administradorWindow;
	private BeneficiosGimnasioWindow beneficiosGimnasioWindow;
	private ConfigurarPreciosWindow configurarPreciosWindow;
	private ConfigurarSalasWindow configurarSalasWindow;
	private ConsultarPlanesMonitorWindow consultarPlanesMonitorWindow;
	private CrearActividadGrupalWindow 	crearActividadGrupalWindow;
	private CrearPlanPersonalizadoWindow crearPlanPersonalizadoWindow;
	private CrearSesionLibreWindow crearSesionLibreWindow;
	private DarAltaMonitorWindow darAltaMonitorWindow;
	private ListaEsperaWindow listaEsperaWindow;	
	private MonitorWindow monitorWindow;
	private NotificacionClienteWindow notificacionClienteWindow;
	private PenalizacionesClienteWindow penalizacionesClienteWindow;
	private ReservasWindow reservasWindow;
	private SueldoMonitoresWindow sueldoMonitoresWindow;
	private ClienteWindow clienteWindow;
	private DatosMonitorWindow datosMonitorWindow;
	private MisActividadesGrupalesWindow misActividadesGrupalesWindow;
	private MisPlanesPersonalizadosWindow misPlanesPersonalizadosWindow; 
	private NotificacionMonitorWindow notificacionMonitorWindow;
	private DatosUsuarioWindow datosUsuarioWindow;
	private MisReservasWindow misReservasWindow;
	private PlanesPersonalizadosWindow planesPersonalizadosWindow;
	private ActividadesGrupalesWindow actividadesGrupalesWindow;
	private TipoActividadWindow tipoActividadWindow;
	private CrearSesionPPWindow crearSesionPPWindow;
	private CrearSesionAGWindow crearSesionAGWindow;
	private ConsultarActividadesGrupalesWindow consultarActividadesGrupalesWindow;
	private AddSesionAGWindow addSesionAGWindow;
	private SesionLibreWindow sesionLibreWindow;
	private ConsultarMaquinasWindow consultarMaquinasWindow;
	private DarMaquinaAltaWindow darMaquinaAltaWindow;
	private DarMaterialAltaWindow darMaterialAltaWindow;
	private ConsultarGastosEquipacionWindow consultarGastosEquipacionWindow;
	private CambiarEstadoMaquinasWindow cambiarEstadoMaquinasWindow;
	private ConsultarSesionLibreAdminWindow consultarSesionLibreAdminWindow;
	private RenovarTarifaWindow renovarTarifaWindow;
	
	private ControladorLogin controladorLogin;
	private ControladorRegistroUsuarios controladorRegistroUsuarios;
	private ControladorAdministrador controladorAdministador;
	private ControladorBeneficiosGimnasio controladorBeneficios;
	private ControladorConfigurarPrecios controladorConfigurarPrecios;
	private ControladorConfigurarSalas controladorConfigurarSalas;
	private ControladorConsultarPlanesMonitor controladorConsultarPlanesMonitor;
	private ControladorCrearActividadGrupal controladorCrearActividadGrupal;
	private ControladorCrearPlanPersonalizado controladorCrearPlanPersonalizado;
	private ControladorCrearSesionLibre controladorCrearSesionLibre;
	private ControladorDarAltaMonitor controladorDarAltaMonitor;
	private ControladorListaEspera controladorListaEspera;	
	private ControladorMonitor controladorMonitor;
	private ControladorNotificacionCliente controladorNotificacionCliente;
	private ControladorPenalizacionesCliente controladorPenalizacionesCliente;
	private ControladorReservas controladorReservas;
	private ControladorSueldoMonitores controladorSueldoMonitores;
	private ControladorCliente controladorCliente;
	private ControladorDatosMonitor controladorDatosMonitor;
	private ControladorMisActividadesGrupales controladorMisActividadesGrupales;
	private ControladorMisPlanesPersonalizados controladorMisPlanesPersonalizados;
	private ControladorNotificacionMonitor controladorNotificacionMonitor;
	private ControladorDatosUsuario controladorDatosUsuario;
	private ControladorMisReservas controladorMisReservas;
	private ControladorPlanesPersonalizados controladorPlanesPersonalizados;
	private ControladorActividadesGrupales controladorActividadesGrupales;
	private ControladorTipoActividad controladorTipoActividad;
	private ControladorCrearSesionPP controladorCrearSesionPP;
	private ControladorCrearSesionAG controladorCrearSesionAG;
	private ControladorConsultarActividadesGrupales controladorConsultarActividadesGrupales;
	private ControladorAddSesionAG controladorAddSesionAG;
	private ControladorSesionLibre controladorSesionLibre;
	private ControladorConsultarMaquinas controladorConsultarMaquinas;
	private ControladorDarMaquinaAlta controladorDarMaquinaAlta;
	private ControladorDarMaterialAlta controladorDarMaterialAlta;
	private ControladorConsultarGastosEquipacion controladorConsultarGastosEquipacion;
	private ControladorCambiarEstadoMaquinas controladorCambiarEstadoMaquinas;
	private ControladorConsultarSesionLibreAdmin controladorConsultarSesionLibreAdmin;
	private ControladorRenovarTarifa controladorRenovarTarifa;

	private JPanel card;
	
	/**
	 * El constructor de la Window
	 */
	public Window()
	{
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(900,500);
		this.setLocationRelativeTo(null);
		
		card = new JPanel(new CardLayout());
		setContentPane(card);
		card.setLayout(new CardLayout());
		
		this.loginWindow = new LoginWindow();
		this.registroUsuariosWindow = new RegistroUsuariosWindow();
		this.administradorWindow = new AdministradorWindow();
		this.beneficiosGimnasioWindow = new BeneficiosGimnasioWindow();
		this.configurarPreciosWindow = new ConfigurarPreciosWindow();
		this.configurarSalasWindow = new ConfigurarSalasWindow();
		this.consultarPlanesMonitorWindow = new ConsultarPlanesMonitorWindow();
		this.crearActividadGrupalWindow = new CrearActividadGrupalWindow();
		this.crearPlanPersonalizadoWindow = new CrearPlanPersonalizadoWindow();
		this.crearSesionLibreWindow = new CrearSesionLibreWindow();
		this.darAltaMonitorWindow = new DarAltaMonitorWindow();
		this.listaEsperaWindow = new ListaEsperaWindow();
		this.monitorWindow = new MonitorWindow();
		this.notificacionClienteWindow = new NotificacionClienteWindow();
		this.penalizacionesClienteWindow = new PenalizacionesClienteWindow();
		this.reservasWindow = new ReservasWindow();
		this.sueldoMonitoresWindow = new SueldoMonitoresWindow();
		this.clienteWindow = new ClienteWindow();
		this.datosMonitorWindow = new DatosMonitorWindow();
		this.misActividadesGrupalesWindow = new MisActividadesGrupalesWindow();
		this.misPlanesPersonalizadosWindow = new MisPlanesPersonalizadosWindow();
		this.notificacionMonitorWindow = new NotificacionMonitorWindow();
		this.datosUsuarioWindow = new DatosUsuarioWindow();
		this.misReservasWindow = new MisReservasWindow();
		this.planesPersonalizadosWindow = new PlanesPersonalizadosWindow();
		this.actividadesGrupalesWindow = new ActividadesGrupalesWindow();
		this.tipoActividadWindow = new TipoActividadWindow();
		this.crearSesionPPWindow = new CrearSesionPPWindow();
		this.crearSesionAGWindow = new CrearSesionAGWindow();
		this.consultarActividadesGrupalesWindow = new ConsultarActividadesGrupalesWindow();
		this.addSesionAGWindow = new AddSesionAGWindow();
		this.sesionLibreWindow = new SesionLibreWindow();
		this.consultarMaquinasWindow = new ConsultarMaquinasWindow();
		this.darMaquinaAltaWindow = new DarMaquinaAltaWindow();
		this.darMaterialAltaWindow = new DarMaterialAltaWindow();
		this.consultarGastosEquipacionWindow = new ConsultarGastosEquipacionWindow();
		this.cambiarEstadoMaquinasWindow = new CambiarEstadoMaquinasWindow();
		this.consultarSesionLibreAdminWindow = new ConsultarSesionLibreAdminWindow();
		this.renovarTarifaWindow = new RenovarTarifaWindow();
		
		card.add(loginWindow, "Login Window");		
		card.add(registroUsuariosWindow, "Registro Usuario Window");
		card.add(clienteWindow, "Cliente Window");
		card.add(administradorWindow, "Administrador Window");
		card.add(beneficiosGimnasioWindow, "Beneficios Window");
		card.add(configurarPreciosWindow, "Configurar Precios Window");
		card.add(configurarSalasWindow, "Configurar Salas Window");
		card.add(consultarPlanesMonitorWindow, "Consultar Planes Monitor Window");
		card.add(crearActividadGrupalWindow, "Crear Actividad Grupal Window");
		card.add(crearPlanPersonalizadoWindow, "Crear Plan Personalizado Window");
	    card.add(crearSesionLibreWindow, "Crear Sesion Libre Window");
	    card.add(darAltaMonitorWindow, "Dar Alta Monitor Window");
	    card.add(listaEsperaWindow, "Lista Espera Window");
	    card.add(monitorWindow, "Monitor Window");
	    card.add(notificacionClienteWindow, "Notificacion Cliente Window");
	    card.add(penalizacionesClienteWindow, "Penalizaciones Cliente Window");
	    card.add(reservasWindow, "Reservas Window");
	    card.add(sueldoMonitoresWindow, "Sueldo Monitores Window");	 
	    card.add(clienteWindow, "Cliente Window");
	    card.add(datosMonitorWindow, "Datos Monitor Window");
	    card.add(misActividadesGrupalesWindow, "Mis Actividades Grupales Window");
	    card.add(misPlanesPersonalizadosWindow, "Mis Planes Personalizados Window");
	    card.add(notificacionMonitorWindow, "Notificacion Monitor Window");
	    card.add(datosUsuarioWindow, "Datos Usuario Window");
	    card.add(misReservasWindow, "Mis Reservas Window");
	    card.add(planesPersonalizadosWindow, "Planes Personalizados Window");
	    card.add(actividadesGrupalesWindow, "Actividades Grupales Window");
	    card.add(tipoActividadWindow, "Tipo Actividad Window");
	    card.add(crearSesionPPWindow, "Crear Sesion Personalizada Window");
	    card.add(crearSesionAGWindow, "Crear Sesion Actividad Grupal Window");
	    card.add(consultarActividadesGrupalesWindow, "Consultar Actividades Grupales Window");
		card.add(addSesionAGWindow, "Add Sesion Grupal Window");
		card.add(sesionLibreWindow, "Sesion Libre Window");
		card.add(consultarMaquinasWindow, "Consultar Maquinas Window");
		card.add(darMaquinaAltaWindow, "Dar Maquina Alta Window");
		card.add(darMaterialAltaWindow, "Dar Material Alta Window");
		card.add(consultarGastosEquipacionWindow, "Consultar Gastos Equipacion Window");
		card.add(cambiarEstadoMaquinasWindow, "Cambiar Estado Maquinas Window");
		card.add(consultarSesionLibreAdminWindow, "Consultar Sesion Libre Admin Window");
		card.add(renovarTarifaWindow, "Renovar Tarifa Window");
	}
	
	/**
	 * Establce el controlador
	 * @param controlador el controlador de la window
	 */
	public void setControlador(Controlador controlador) {
		this.controladorLogin = controlador.getControladorLogin();
		this.controladorRegistroUsuarios = controlador.getControladorRegistroUsuarios();
		this.controladorAdministador = controlador.getControladorAdministrador();
		this.controladorBeneficios = controlador.getControladorBeneficiosGimnasio();
		this.controladorConfigurarPrecios = controlador.getControladorConfigurarPrecios();
		this.controladorConfigurarSalas = controlador.getControladorConfigurarSalas();
		this.controladorConsultarPlanesMonitor = controlador.getControladorConsultarPlanesMonitor();
		this.controladorCrearActividadGrupal = controlador.getControladorCrearActividadGrupal();
		this.controladorCrearPlanPersonalizado = controlador.getControladorCrearPlanPersonalizado();
		this.controladorCrearSesionLibre = controlador.getControladorCrearSesionLibre();
		this.controladorDarAltaMonitor = controlador.getControladorDarAltaMonitor();
		this.controladorListaEspera = controlador.getControladorListaEspera();	
		this.controladorMonitor = controlador.getControladorMonitor();
		this.controladorNotificacionCliente = controlador.getControladorNotificacionCliente();
		this.controladorPenalizacionesCliente = controlador.getControladorPenalizacionesCliente();
		this.controladorReservas = controlador.getControladorReservas();
		this.controladorSueldoMonitores = controlador.getControladorSueldoMonitores();
		this.controladorCliente = controlador.getControladorCliente();
		this.controladorDatosMonitor = controlador.getControladorDatosMonitor();
		this.controladorMisActividadesGrupales = controlador.getControladorMisActividadesGrupales();
		this.controladorMisPlanesPersonalizados = controlador.getControladorMisPlanesPersonalizados();
		this.controladorNotificacionMonitor = controlador.getControladorNotificacionMonitor();
		this.controladorDatosUsuario = controlador.getControladorDatosUsuario();
		this.controladorActividadesGrupales = controlador.getControladorActividadesGrupales();
		this.controladorMisReservas = controlador.getControladorMisReservas();
		this.controladorPlanesPersonalizados = controlador.getControladorPlanesPersonalizados();
		this.controladorTipoActividad = controlador.getControladorTipoActividad();
		this.controladorCrearSesionPP = controlador.getControladorCrearSesionPP();
		this.controladorCrearSesionAG = controlador.getControladorCrearSesionAG();
		this.controladorConsultarActividadesGrupales = controlador.getControladorConsultarActividadesGrupales();
		this.controladorAddSesionAG = controlador.getControladorAddSesionAG();
		this.controladorSesionLibre = controlador.getControladorSesionLibre();
		this.controladorConsultarMaquinas = controlador.getControladorConsultarMaquinas();
		this.controladorDarMaquinaAlta = controlador.getControladorDarMaquinaAlta();
		this.controladorDarMaterialAlta = controlador.getControladorDarMaterialAlta();
		this.controladorConsultarGastosEquipacion = controlador.getControladorConsultarGastosequipacion();
		this.controladorCambiarEstadoMaquinas = controlador.getControladorCambiarEstadoMaquinas();
		this.controladorConsultarSesionLibreAdmin = controlador.getControladorConsultarSesionLibreAdmin();
		this.controladorRenovarTarifa = controlador.getControladorRenovarTarifa();
		
		loginWindow.setControlador(controladorLogin);
		registroUsuariosWindow.setControlador(controladorRegistroUsuarios);
		administradorWindow.setControlador(controladorAdministador);
		beneficiosGimnasioWindow.setControlador(controladorBeneficios);
		configurarPreciosWindow.setControlador(controladorConfigurarPrecios);
		configurarSalasWindow.setControlador(controladorConfigurarSalas);
		consultarPlanesMonitorWindow.setControlador(controladorConsultarPlanesMonitor);
		crearActividadGrupalWindow.setControlador(controladorCrearActividadGrupal);
		crearPlanPersonalizadoWindow.setControlador(controladorCrearPlanPersonalizado);
		crearSesionLibreWindow.setControlador(controladorCrearSesionLibre);
		darAltaMonitorWindow.setControlador(controladorDarAltaMonitor);
		listaEsperaWindow.setControlador(controladorListaEspera);
		monitorWindow.setControlador(controladorMonitor);
		notificacionClienteWindow.setControlador(controladorNotificacionCliente);
		penalizacionesClienteWindow.setControlador(controladorPenalizacionesCliente);
		reservasWindow.setControlador(controladorReservas);
		sueldoMonitoresWindow.setControlador(controladorSueldoMonitores);
		clienteWindow.setControlador(controladorCliente);
		datosMonitorWindow.setControlador(controladorDatosMonitor);
		misActividadesGrupalesWindow.setControlador(controladorMisActividadesGrupales);
		misPlanesPersonalizadosWindow.setControlador(controladorMisPlanesPersonalizados);
		notificacionMonitorWindow.setControlador(controladorNotificacionMonitor);
		datosUsuarioWindow.setControlador(controladorDatosUsuario);
		misReservasWindow.setControlador(controladorMisReservas);
		planesPersonalizadosWindow.setControlador(controladorPlanesPersonalizados);
		actividadesGrupalesWindow.setControlador(controladorActividadesGrupales);
		tipoActividadWindow.setControlador(controladorTipoActividad);
		crearSesionPPWindow.setControlador(controladorCrearSesionPP);
		crearSesionAGWindow.setControlador(controladorCrearSesionAG);
		consultarActividadesGrupalesWindow.setControlador(controladorConsultarActividadesGrupales);
		addSesionAGWindow.setControlador(controladorAddSesionAG);
		sesionLibreWindow.setControlador(controladorSesionLibre);
		consultarMaquinasWindow.setControlador(controladorConsultarMaquinas);
		darMaquinaAltaWindow.setControlador(controladorDarMaquinaAlta);
		darMaterialAltaWindow.setControlador(controladorDarMaterialAlta);
		consultarGastosEquipacionWindow.setControlador(controladorConsultarGastosEquipacion);
		cambiarEstadoMaquinasWindow.setControlador(controladorCambiarEstadoMaquinas);
		consultarSesionLibreAdminWindow.setControlador(controladorConsultarSesionLibreAdmin);
		renovarTarifaWindow.setControlador(controladorRenovarTarifa);
	}

	/**
	 * GETTER
	 * @return la ventana login
	 */
	public LoginWindow getLoginWindow() {
		return this.loginWindow;
	}

	/**
	 * GETTER
	 * @return la ventana registroUsuarios
	 */
	public RegistroUsuariosWindow getRegistroUsuariosWindow() {
		return this.registroUsuariosWindow;
	}
	
	/**
	 * GETTER
	 * @return la ventana de administrados
	 */
	public AdministradorWindow getAdministradorWindow() {
		return this.administradorWindow;
	}
	
	/**
	 * GETTER
	 * @return la ventana de beneficios gimnasio
	 */

	public BeneficiosGimnasioWindow getBeneficiosGimnasioWindow() {
		return this.beneficiosGimnasioWindow;
	}

	
	/**
	 * GETTER
	 * @return la ventana de configurar precios
	 */
	public ConfigurarPreciosWindow getConfigurarPreciosWindow() {
		return this.configurarPreciosWindow;
	}
	

	
	/**
	 * GETTER
	 * @return la venta de configurar salas
	 */
	public ConfigurarSalasWindow getConfigurarSalasWindow()
	{
		return this.configurarSalasWindow;
	}

	
	/**
	 * GETTER
	 * @return la ventana de consultar planes monitor
	 */
	public ConsultarPlanesMonitorWindow getConsultarPlanesMonitorWindow()
	{
		return this.consultarPlanesMonitorWindow;
	}

	
	/**
	 * GETTER
	 * @return la ventana de crear actividad grupal
	 */
	public CrearActividadGrupalWindow getCrearActividadGrupalWindow()
	{
		return this.crearActividadGrupalWindow;
	}

	
	/**
	 * GETTER
	 * @return la ventana de crear plan personalizado
	 */
	public CrearPlanPersonalizadoWindow getCrearPlanPersonalizadoWindow() {
		return this.crearPlanPersonalizadoWindow;
	}

	
	/**
	 * GETTER
	 * @return la ventana de crear sesion libre
	 */
	public CrearSesionLibreWindow getCrearSesionLibreWindow()
	{
		
		return this.crearSesionLibreWindow;
	}

	
	/**
	 * GETTER
	 * @return la ventana de dar alta monitor
	 */
	public DarAltaMonitorWindow getDarAltaMonitorWindow()
	{
		return this.darAltaMonitorWindow;
	}

	
	/**
	 * GETTER
	 * @return la ventana de lista espera
	 */
	public ListaEsperaWindow getListaEsperaWindow()
	{
		return this.listaEsperaWindow;
	}

	
	/**
	 * GETTER
	 * @return la ventana de monitor
	 */
	public MonitorWindow getMonitorWindow()
	{
		return this.monitorWindow;
	}

	
	/**
	 * GETTER
	 * @return la ventana de notificaciones cliente
	 */
	public NotificacionClienteWindow getNotificacionClienteWindow()
	{
		return this.notificacionClienteWindow;
	}

	
	/**
	 * GETTER
	 * @return la ventana de penalizaciones cliente
	 */
	public PenalizacionesClienteWindow getPenalizacionesClienteWindow()
	{
		return this.penalizacionesClienteWindow;
	}

	
	/**
	 * GETTER
	 * @return la centana de reservas
	 */
	public ReservasWindow getReservasWindow()
	{
		return this.reservasWindow;
	}

	
	/**
	 * GETTER
	 * @return la ventana de sueldo monitores
	 */
	public SueldoMonitoresWindow getSueldoMonitoresWindow()
	{
		return this.sueldoMonitoresWindow;
	}

	
	/**
	 * GETTER 
	 * @return la ventana de cliente
	 */
	public ClienteWindow getClienteWindow()
	{
		return this.clienteWindow;
	}

	
	/**
	 * GETTER
	 * @return la ventana de datos monitor
	 */
	public DatosMonitorWindow getDatosMonitorWindow()
	{
		return this.datosMonitorWindow;
	}

	
	/**
	 * GETTER
	 * @return la ventana de mis actividades grupales
	 */
	public MisActividadesGrupalesWindow getMisActividadesGrupalesWindow()
	{
		return this.misActividadesGrupalesWindow;
	}

	
	/**
	 * GETTER
	 * @return la ventana de mis planes personalizados
	 */
	public MisPlanesPersonalizadosWindow getMisPlanesPersonalizadosWindow()
	{
		return this.misPlanesPersonalizadosWindow;
	}

	
	/**
	 * GETTER
	 * @return la ventana de notificaciones monitor
	 */
	public NotificacionMonitorWindow getNotificacionMonitorWindow()
	{
		return this.notificacionMonitorWindow;
	}

	
	/**
	 * GETTER
	 * @return la ventana de datos usuario
	 */
	public DatosUsuarioWindow getDatosUsuarioWindow() {
		return this.datosUsuarioWindow;
	}

	
	/**
	 * GETTER
	 * @return la ventana de mis reservas
	 */
	public MisReservasWindow getMisReservasWindow() {
		return this.misReservasWindow;
	}

	
	/**
	 * GETTER
	 * @return la ventana de actividades grupales
	 */
	public ActividadesGrupalesWindow getActividadesGrupalesWindow() {
		return this.actividadesGrupalesWindow;
	}

	
	/**
	 * GETTER
	 * @return la ventana de planes personalizados
	 */
	public PlanesPersonalizadosWindow getPlanesPersonalizadosWindow() {
		return this.planesPersonalizadosWindow;
	}

	
	/**
	 * GETTER
	 * @return la ventana de tipo actividad
	 */
	public TipoActividadWindow getTipoActividadWindow()
	{
		return this.tipoActividadWindow;
	}

	
	/**
	 * GETTER
	 * @return la venta de crear sesion pp
	 */
	public CrearSesionPPWindow getCrearSesionPPWindow()
	{
		return this.crearSesionPPWindow;
	}

	
	/**
	 * GETTER
	 * @return la ventana de crear sesion ag
	 */
	public CrearSesionAGWindow getCrearSesionAGWindow()
	{
		return this.crearSesionAGWindow;
	}

	
	/**
	 * GETTER
	 * @return la ventana de consultar activivdades grupales
	 */
	public ConsultarActividadesGrupalesWindow getConsultarActividadesGrupalesWindow()
	{
		return this.consultarActividadesGrupalesWindow;
	}

	
	/**
	 * GETTER
	 * @return la ventana de add sesion ag
	 */
	public AddSesionAGWindow getAddSesionAGWindow()
	{
		return this.addSesionAGWindow;
	}

	
	/**
	 * GETTER
	 * @return la ventana sesion libre
	 */
	public SesionLibreWindow getSesionLibreWindow()
	{
		return this.sesionLibreWindow;
	}

	
	/**
	 * GETTER
	 * @return la ventana consultar maquinas
	 */
	public ConsultarMaquinasWindow getConsultarMaquinasWindow()
	{
		return this.consultarMaquinasWindow;
	}

	
	/**
	 * GETTER
	 * @return la ventana dar maquina alta
	 */
	public DarMaquinaAltaWindow getDarMaquinaAltaWindow()
	{
		return this.darMaquinaAltaWindow;
	}

	
	/**
	 * GETTER
	 * @return la ventana dar material alta
	 */
	public DarMaterialAltaWindow getDarMaterialAltaWindow()
	{
		return this.darMaterialAltaWindow;
	}

	
	/**
	 * GETTER
	 * @return la ventana consultar gastos equipacion
	 */
	public ConsultarGastosEquipacionWindow getConsultarGastosEquipacionWindow(){
		return this.consultarGastosEquipacionWindow;
	}

	
	/**
	 * GETTER
	 * @return la ventana cambiar de estado
	 */
	public CambiarEstadoMaquinasWindow getCambiarEstadoMaquinasWindow(){
		return this.cambiarEstadoMaquinasWindow;
	}
	
	/**
	 * GETTER
	 * @return la ventana de consultar sesion libre admin
	 */
	public ConsultarSesionLibreAdminWindow getConsultarSesionLibreAdminWindow()
	{
		return this.consultarSesionLibreAdminWindow;
	}
	
	/**
	 * GETTER
	 * @return la ventana renovar tarifa
	 */
	public RenovarTarifaWindow getRenovarTarifaWindow()
	{
		return this.renovarTarifaWindow;
	}
	
	/**
	 * Funcion para mostrar una ventana
	 * @param texto la ventana que voy a mostrar
	 */
	public void mostrarPanel(String texto) {
		CardLayout l = (CardLayout)card.getLayout();
		l.show(card, texto);
	}

	
        
}
