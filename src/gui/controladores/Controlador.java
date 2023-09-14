package gui.controladores;

import aplicacion.Gimnasio;

import gui.Window;
import gui.controladores.administrador.*;
import gui.controladores.cliente.*;
import gui.controladores.monitor.*;

/**
 * Este fichero muestra todo lo que tiene que ver con la clase Controlador
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class Controlador {

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
	private ControladorActividadesGrupales controladorActividadesGrupales;
	private ControladorPlanesPersonalizados controladorPlanesPersonalizados;
	private ControladorTipoActividad controladorTipoActividad;
	private ControladorCrearSesionPP controladorCrearSesionPP;
	private ControladorCrearSesionAG controladorCrearSesionAG;
	private ControladorConsultarActividadesGrupales controladorConsultarActividadesGrupales;
	private ControladorAddSesionAG controladorAddSesionAG;
	private ControladorSesionLibre controladorSesionLibre;
	private ControladorConsultarMaquinas controladorConsultarMaquinas;
	private ControladorDarMaquinaAlta controladorDarMaquinaAlta;
	private ControladorDarMaterialAlta controladorDarMaterialAlta;
	private ControladorConsultarGastosEquipacion controladorConsultarGastosequipacion;
	private ControladorCambiarEstadoMaquinas controladorCambiarEstadoMaquinasWindow;
	private ControladorConsultarSesionLibreAdmin controladorConsultarSesionLibreAdmin;
	private ControladorRenovarTarifa controladorRenovarTarifa;
	
	private Window window;
	private Gimnasio gimnasio;
	
	/**
	 * Constructor Controlador
	 * @param window la ventana general
	 * @param gimnasio el gimnasio
	 */
	public Controlador (Window window, Gimnasio gimnasio)
	{
		this.window = window;
		this.gimnasio = gimnasio;
		this.controladorLogin = new ControladorLogin(this.window, this.gimnasio);
		this.controladorRegistroUsuarios = new ControladorRegistroUsuarios(this.window, this.gimnasio);
		this.controladorAdministador = new ControladorAdministrador(this.window, this.gimnasio);
		this.controladorBeneficios = new ControladorBeneficiosGimnasio(this.window, this.gimnasio);
		this.controladorConfigurarPrecios = new ControladorConfigurarPrecios(this.window, this.gimnasio);
		this.controladorConfigurarSalas = new ControladorConfigurarSalas(this.window, this.gimnasio);
		this.controladorConsultarPlanesMonitor = new ControladorConsultarPlanesMonitor(this.window, this.gimnasio);
		this.controladorCrearActividadGrupal = new ControladorCrearActividadGrupal(this.window, this.gimnasio);
		this.controladorCrearPlanPersonalizado = new ControladorCrearPlanPersonalizado(this.window, this.gimnasio);
		this.controladorCrearSesionLibre = new ControladorCrearSesionLibre(this.window, this.gimnasio);
		this.controladorDarAltaMonitor = new ControladorDarAltaMonitor(this.window, this.gimnasio);
		this.controladorListaEspera = new ControladorListaEspera(this.window, this.gimnasio);	
		this.controladorMonitor = new ControladorMonitor(this.window, this.gimnasio);
		this.controladorNotificacionCliente = new ControladorNotificacionCliente(this.window, this.gimnasio);
		this.controladorPenalizacionesCliente = new ControladorPenalizacionesCliente(this.window, this.gimnasio);
		this.controladorReservas = new ControladorReservas(this.window, this.gimnasio);
		this.controladorSueldoMonitores = new ControladorSueldoMonitores(this.window, this.gimnasio);
		this.controladorCliente = new ControladorCliente(this.window, this.gimnasio);
		this.controladorDatosMonitor = new ControladorDatosMonitor(this.window, this.gimnasio);
		this.controladorMisActividadesGrupales = new ControladorMisActividadesGrupales(this.window, this.gimnasio);
		this.controladorMisPlanesPersonalizados = new ControladorMisPlanesPersonalizados(this.window, this.gimnasio);
		this.controladorNotificacionMonitor = new ControladorNotificacionMonitor(this.window, this.gimnasio);
		this.controladorDatosUsuario = new ControladorDatosUsuario(this.window, this.gimnasio);
		this.controladorActividadesGrupales = new ControladorActividadesGrupales(this.window, this.gimnasio);
		this.controladorMisReservas = new ControladorMisReservas(this.window, this.gimnasio);
		this.controladorPlanesPersonalizados = new ControladorPlanesPersonalizados(this.window, this.gimnasio);
		this.controladorTipoActividad = new ControladorTipoActividad(this.window, this.gimnasio);
		this.controladorCrearSesionPP = new ControladorCrearSesionPP(this.window, this.gimnasio);
		this.controladorCrearSesionAG = new ControladorCrearSesionAG(this.window, this.gimnasio);
		this.controladorConsultarActividadesGrupales = new ControladorConsultarActividadesGrupales(this.window, this.gimnasio);
		this.controladorAddSesionAG =  new ControladorAddSesionAG(this.window, this.gimnasio);
		this.controladorSesionLibre = new ControladorSesionLibre(this.window, this.gimnasio);
		this.controladorConsultarMaquinas = new ControladorConsultarMaquinas(this.window, this.gimnasio);
		this.controladorDarMaquinaAlta = new ControladorDarMaquinaAlta(this.window, this.gimnasio);
		this.controladorDarMaterialAlta = new ControladorDarMaterialAlta(this.window, this.gimnasio);
		this.controladorConsultarGastosequipacion = new ControladorConsultarGastosEquipacion(this.window, this.gimnasio);
		this.controladorCambiarEstadoMaquinasWindow = new ControladorCambiarEstadoMaquinas(this.window, this.gimnasio);
		this.controladorConsultarSesionLibreAdmin = new ControladorConsultarSesionLibreAdmin(this.window, this.gimnasio);
		this.controladorRenovarTarifa = new ControladorRenovarTarifa(this.window, this.gimnasio);
	}
	
	/**
	 * GETTER
	 * @return el controladorLogin
	 */
	public ControladorLogin getControladorLogin() {
		return this.controladorLogin;
	}
	
	/**
	 * GETTER
	 * @return el controladorRegistroUsuarios
	 */
	public ControladorRegistroUsuarios getControladorRegistroUsuarios() {
		return this.controladorRegistroUsuarios;
	}
	
	/**
	 * GETTER
	 * @return el controladorAdministador
	 */
	public ControladorAdministrador getControladorAdministrador() {
		return this.controladorAdministador;
	}
	
	/**
	 * GETTER
	 * @return el controladorBeneficios
	 */
	public ControladorBeneficiosGimnasio getControladorBeneficiosGimnasio() {
		return this.controladorBeneficios;
	}
	
	/**
	 * GETTER
	 * @return el controladorConfigurarPrecios
	 */
	public ControladorConfigurarPrecios getControladorConfigurarPrecios() {
		return this.controladorConfigurarPrecios;
	}
	
	/**
	 * GETTER
	 * @return el controladorConfigurarSalas
	 */
	public ControladorConfigurarSalas getControladorConfigurarSalas()
	{
		return this.controladorConfigurarSalas;
	}
	
	/**
	 * GETTER
	 * @return el controladorConsultarPlanesMonitor
	 */
	public ControladorConsultarPlanesMonitor getControladorConsultarPlanesMonitor()
	{
		return this.controladorConsultarPlanesMonitor;
	}
	
	/**
	 * GETTER
	 * @return el controladorCrearActividadGrupal
	 */
	public ControladorCrearActividadGrupal getControladorCrearActividadGrupal()
	{
		return this.controladorCrearActividadGrupal;
	}
	
	/**
	 * GETTER
	 * @return el controladorCrearPlanPersonalizado
	 */
	public ControladorCrearPlanPersonalizado getControladorCrearPlanPersonalizado() {
		return this.controladorCrearPlanPersonalizado;
	}
	
	/**
	 * GETTER
	 * @return el controladorCrearSesionLibre
	 */
	public ControladorCrearSesionLibre getControladorCrearSesionLibre()
	{
		
		return this.controladorCrearSesionLibre;
	}
	
	/**
	 * GETTER
	 * @return el controladorDarAltaMonitor
	 */
	public ControladorDarAltaMonitor getControladorDarAltaMonitor()
	{
		return this.controladorDarAltaMonitor;
	}
	
	/**
	 * GETTER
	 * @return el controladorListaEspera
	 */
	public ControladorListaEspera getControladorListaEspera()
	{
		return this.controladorListaEspera;
	}
	
	/**
	 * GETTER
	 * @return el controladorMonitor
	 */
	public ControladorMonitor getControladorMonitor()
	{
		return this.controladorMonitor;
	}
	
	/**
	 * GETTER
	 * @return el controladorNotificacionCliente
	 */
	public ControladorNotificacionCliente getControladorNotificacionCliente()
	{
		return this.controladorNotificacionCliente;
	}
	
	/**
	 * GETTER
	 * @return el controladorPenalizacionesCliente
	 */
	public ControladorPenalizacionesCliente getControladorPenalizacionesCliente()
	{
		return this.controladorPenalizacionesCliente;
	}
	
	/**
	 * GETTER
	 * @return el controladorReservas
	 */
	public ControladorReservas getControladorReservas()
	{
		return this.controladorReservas;
	}
	
	/**
	 * GETTER
	 * @return el controladorSueldoMonitores
	 */
	public ControladorSueldoMonitores getControladorSueldoMonitores()
	{
		return this.controladorSueldoMonitores;
	}
	
	/**
	 * GETTER
	 * @return el controladorCliente
	 */
	public ControladorCliente getControladorCliente()
	{
		return this.controladorCliente;
	}
	
	/**
	 * GETTER
	 * @return el controladorDatosMonitor
	 */
	public ControladorDatosMonitor getControladorDatosMonitor()
	{
		return this.controladorDatosMonitor;
	}

	/**
	 * GETTER
	 * @return el controladorMisActividadesGrupales
	 */
	public ControladorMisActividadesGrupales getControladorMisActividadesGrupales() {
		return this.controladorMisActividadesGrupales;
	}
	
	/**
	 * GETTER
	 * @return el controladorMisPlanesPersonalizados
	 */
	public ControladorMisPlanesPersonalizados getControladorMisPlanesPersonalizados()
	{
		return this.controladorMisPlanesPersonalizados;
	}
	
	/**
	 * GETTER
	 * @return el controladorNotificacionMonitor
	 */
	public ControladorNotificacionMonitor getControladorNotificacionMonitor()
	{
		return this.controladorNotificacionMonitor;
	}
	
	/**
	 * GETTER
	 * @return el controladorDatosUsuario
	 */
	public ControladorDatosUsuario getControladorDatosUsuario()
	{
		return this.controladorDatosUsuario;
	}
	
	/**
	 * GETTER
	 * @return el controladorActividadesGrupales
	 */
	public ControladorActividadesGrupales getControladorActividadesGrupales()
	{
		return this.controladorActividadesGrupales;
	}
	
	/**
	 * GETTER
	 * @return el controladorMisReservas
	 */
	public ControladorMisReservas getControladorMisReservas()
	{
		return this.controladorMisReservas;
	}
	
	/**
	 * GETTER
	 * @return el controladorPlanesPersonalizados
	 */
	public ControladorPlanesPersonalizados getControladorPlanesPersonalizados()
	{
		return this.controladorPlanesPersonalizados;
	}
	
	/**
	 * GETTER
	 * @return el controladorTipoActividad
	 */
	public ControladorTipoActividad getControladorTipoActividad()
	{
		return this.controladorTipoActividad;
	}
	
	/**
	 * GETTER
	 * @return el controladorCrearSesionPP
	 */
	public ControladorCrearSesionPP getControladorCrearSesionPP()
	{
		return this.controladorCrearSesionPP;
	}

	/**
	 * GETTER
	 * @return el controladorCrearSesionAG
	 */
	public ControladorCrearSesionAG getControladorCrearSesionAG()
	{
		return this.controladorCrearSesionAG;
	}
	
	/**
	 * GETTER
	 * @return el controladorConsultarActividadesGrupales
	 */
	public ControladorConsultarActividadesGrupales getControladorConsultarActividadesGrupales()
	{
		return this.controladorConsultarActividadesGrupales;
	}
	
	/**
	 * GETTER
	 * @return el controladorAddSesionAG
	 */
	public ControladorAddSesionAG getControladorAddSesionAG()
	{
		return this.controladorAddSesionAG;
	}

	/**
	 * GETTER
	 * @return el controladorSesionLibre
	 */
	public ControladorSesionLibre getControladorSesionLibre() {
		return this.controladorSesionLibre;
	}
	
	/**
	 * GETTER
	 * @return el controladorConsultarMaquinas
	 */
	public ControladorConsultarMaquinas getControladorConsultarMaquinas()
	{
		return this.controladorConsultarMaquinas;
	}
	
	/**
	 * GETTER
	 * @return el controladorDarMaquinaAlta
	 */
	public ControladorDarMaquinaAlta getControladorDarMaquinaAlta()
	{
		return this.controladorDarMaquinaAlta;
	}
	
	/**
	 * GETTER
	 * @return el controladorDarMaterialAlta
	 */
	public ControladorDarMaterialAlta getControladorDarMaterialAlta() 
	{
		return this.controladorDarMaterialAlta;
	}
	
	/**
	 * GETTER
	 * @return el controladorConsultarGastosequipacion
	 */
	public ControladorConsultarGastosEquipacion getControladorConsultarGastosequipacion()
	{
		return this.controladorConsultarGastosequipacion;	
	}
	
	/**
	 * GETTER
	 * @return el controladorCambiarEstadoMaquinas
	 */
	public ControladorCambiarEstadoMaquinas getControladorCambiarEstadoMaquinas()
	{
		return this.controladorCambiarEstadoMaquinasWindow;
	}
	
	/**
	 * GETTER
	 * @return el controladorConsultarSesionLibreAdmin
	 */
	public ControladorConsultarSesionLibreAdmin getControladorConsultarSesionLibreAdmin()
	{
		return this.controladorConsultarSesionLibreAdmin;
	}
	
	/**
	 * GETTER
	 * @return el ControladorRenovarTarifa
	 */
	public ControladorRenovarTarifa getControladorRenovarTarifa()
	{
		return this.controladorRenovarTarifa;
	}
	
}
