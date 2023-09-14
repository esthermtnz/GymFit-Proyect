package testers;

import org.junit.Test;

import aplicacion.Gimnasio;
import aplicacion.Reserva;
import aplicacion.usuario.*;
import aplicacion.actividad.Actividad;
import aplicacion.actividad.ActividadGrupal;
import aplicacion.actividad.PlanPersonalizado;
import aplicacion.actividad.TipoActividad;
import aplicacion.actividad.TipoObjetivo;
import aplicacion.actividad.sesion.Sesion;
import aplicacion.actividad.sesion.SesionAG;
import aplicacion.actividad.sesion.SesionPP;
import aplicacion.actividad.sesion.requisito.Requisito;
import aplicacion.sala.Sala;
import aplicacion.sala.SalaSimple;
import aplicacion.sala.*;
import aplicacion.usuario.Cliente;
import aplicacion.usuario.Monitor;
import aplicacion.usuario.Usuario;
import aplicacion.usuario.tarifa.Tarifa;
import aplicacion.usuario.tarifa.TarjetaCredito;
import aplicacion.actividad.sesion.SesionLibre;
import aplicacion.actividad.sesion.requisito.*;
import aplicacion.equipacion.*;
import aplicacion.usuario.tarifa.*;
import aplicacion.excepciones.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;

/**
 * TEST
 *
 */
public class GimnasioTest {

	private Gimnasio gimnasio;
	private TarjetaCredito tarjetaCredito;
	private TarifaPlana tarifaPlana, tarifaPlana2, tarifaPlana3, tarifaPlana4, tarifaPlana5;
	private RequisitoCancelaciones requisitoCancelaciones;
	private SalaSimple salaSimple;
	private Cliente cliente;
	private Cliente cliente4;
	private Cliente cliente5;
	private Monitor monitor;
	private Reserva reserva;
	private TipoActividad tipoActividad;
	private SesionAG sesionAG;
	private ActividadGrupal actividad;
	private TipoTarifaPlana tipoTarifaPlana;
	private Equipacion equipacion, equipacion1;
	private Requisito requisito;
	private Maquina maquina, maquina1;
	private Material material;
	
	/*
	 * Test
	 */
	@Before
	public void setUp() throws Exception{
		gimnasio = Gimnasio.getGimnasio();
		tarjetaCredito = new TarjetaCredito("4507670001000009","346", "Paula");
		requisito = new RequisitoEdad(18, 35);
		cliente = new Cliente ("Julia", "698574213", LocalDate.of(1999, 03, 24), "pau24", "carafassol9", tarjetaCredito);
		cliente4 = new Cliente ("Alan", "698574213", LocalDate.of(2000, 03, 24), "alaniso", "alaaan", tarjetaCredito);
		cliente5 = new Cliente ("Fred", "698574213", LocalDate.of(2003, 03, 24), "fredo", "fredofredo", tarjetaCredito);
		
		requisitoCancelaciones = new RequisitoCancelaciones(0, 4);
		tipoActividad = gimnasio.crearTipoActividad("Yoga1");
		monitor = new Monitor("Manu325", "12345678" ,"gymManu", "Manuel", "manuel.guti@gmail.com");
		salaSimple = new SalaSimple("Pesas", "Para realizar levantamiento de pesas", 15);
		reserva = new Reserva(cliente, sesionAG, true);
		cliente.addReserva(reserva);
		
		tipoTarifaPlana = TipoTarifaPlana.TRIMESTRAL;
		tarifaPlana = new TarifaPlana(LocalDate.now(), LocalDate.now().plusMonths(3), 25.99, tipoTarifaPlana, tipoActividad);
		tarifaPlana2 = new TarifaPlana(LocalDate.now(), LocalDate.now().plusMonths(3), 25.99, tipoTarifaPlana, tipoActividad);
		tarifaPlana3 = new TarifaPlana(LocalDate.now(), LocalDate.now().plusMonths(3), 25.99, tipoTarifaPlana, tipoActividad);
		tarifaPlana4 = new TarifaPlana(LocalDate.now(), LocalDate.now().plusMonths(3), 25.99, tipoTarifaPlana, tipoActividad);
		tarifaPlana5 = new TarifaPlana(LocalDate.now(), LocalDate.now().plusMonths(3), 25.99, tipoTarifaPlana, tipoActividad);
		actividad = new ActividadGrupal("Cardio", monitor, requisitoCancelaciones, tipoActividad);
		sesionAG = new SesionAG (LocalDate.now().plusDays(2), LocalDateTime.now().plusDays(2), LocalDateTime.now().plusDays(2).plusHours(1), actividad, salaSimple, 40.0);
		
		equipacion = new Material("pesas", 5, LocalDate.now().minusMonths(3), 10.99);
		maquina = gimnasio.darAltaMaquinaPropiedad("Cinta", "para correr", "Apple", 500.50, LocalDate.now());
		material = gimnasio.darAltaMaterial("para pesas", 4, LocalDate.now(), 25.10);
		maquina1 = gimnasio.darAltaMaquinaAlquilada("Peso Libre", "para correr", "Apple", 500.50, LocalDate.now());
		
		
		}
	
	@Test
	public void testIniciarSesion1() throws IOException, ExcepcionUsuario, FechaPosterior, SinTarifa {
		//para cliente
		TipoActividad tipoActividad5 = gimnasio.crearTipoActividad("Yoga4");
		TarifaPlana tarifaPlana6 = new TarifaPlana(LocalDate.now(), LocalDate.now().plusMonths(3), 25.99, tipoTarifaPlana, tipoActividad5);
		//tarifaPlana.setTipoActividad(tipoActividad);
		Cliente cliente = gimnasio.registroCliente("paula1", "gympau", "Paula", "9876545678", LocalDate.now().minusYears(18), "1234567891234567", "1234", "Paula Gonzalez Vazquez", tarifaPlana6);
		assertTrue(gimnasio.iniciarSesion("paula1", "gympau"));
	}
	
	@Test
	public void testIniciarSesion2() throws ExcepcionUsuario, FechaPosterior, IOException, SinTarifa {
		//para cliente numero telefono null
		gimnasio.registroCliente("manu", "gympau", "Paula", "9876545678", LocalDate.now().minusYears(19), "1234567891234567", "1234", "Paula Gonzalez Vazquez", tarifaPlana2);
		assertFalse(gimnasio.iniciarSesion("manu", "gympepe"));
	}
	
	@Test
	public void testIniciarSesion3() throws ExcepcionUsuario, FechaPosterior, IOException, SinTarifa{
		//para cliente
		gimnasio.registroCliente("oscar", "gympau", "Paula", "9876545678", LocalDate.now().minusYears(18), "1234567891234567", "1234", "Paula Gonzalez Vazquez", tarifaPlana3);
		assertFalse(gimnasio.iniciarSesion("marta483", "gympau"));
	}
	
	@Test
	public void testIniciarSesion4() throws ExcepcionUsuario, FechaPosterior, IOException, SinTarifa{
		//para cliente
		gimnasio.registroCliente("esther", "gympau", "Paula", "9876545678", LocalDate.now().minusYears(20), "1234567891234567", "1234","Paula Gonzalez Vazquez", tarifaPlana4);
		assertFalse(gimnasio.iniciarSesion(null, "gympau"));
	}
	
	@Test
	public void testIniciarSesion5()  throws ExcepcionUsuario, FechaPosterior, IOException, SinTarifa{
		//para cliente
		gimnasio.registroCliente("marta483", "gympau", "Paula", "9876545678", LocalDate.now(), "1234567891234567", "1234","Paula Gonzalez Vazquez", tarifaPlana5);
		assertFalse(gimnasio.iniciarSesion("marta483", null));
	}

	@Test
	public void testCrearActividadGrupal1() {
		
		ActividadGrupal ag = gimnasio.crearActividadGrupal("Cardio","para sufrir", requisitoCancelaciones, monitor, tipoActividad);
		assertTrue(ag!=null);
		assertTrue(gimnasio.getActividadesGrupales().contains(ag));
	}
	
	@Test
	public void testCrearActividadGrupal2() {
		ActividadGrupal ag = gimnasio.crearActividadGrupal("Cardio","para sufrir", requisitoCancelaciones, monitor, null);
		assertFalse(gimnasio.getActividadesGrupales().contains(ag));
	}
	
	@Test
	public void testCrearActividadGrupal3() {
		ActividadGrupal ag = gimnasio.crearActividadGrupal("Cardio","para sufrir", null, monitor, tipoActividad);
		assertFalse(gimnasio.getActividadesGrupales().contains(ag));
	}
	
	@Test
	public void testCrearActividadGrupal4() {
		ActividadGrupal ag = gimnasio.crearActividadGrupal("Cardio","para sufrir", requisitoCancelaciones, null, tipoActividad);
		assertFalse(gimnasio.getActividadesGrupales().contains(ag));
	}
	
	@Test
	public void testCrearActividadGrupal5() {
		ActividadGrupal ag = gimnasio.crearActividadGrupal("Cardio","para sufrir", requisitoCancelaciones, monitor, null);
		assertFalse(gimnasio.getActividadesGrupales().contains(ag));
	}
	
	@Test
	public void testCrearPlanPersonalizado1() {
		PlanPersonalizado pp = gimnasio.crearPlanPersonalizado("Ganar musculo", monitor, requisitoCancelaciones, TipoObjetivo.GANANCIAMUSCULAR, "Clases para ganar masa muscular");
		assertTrue(gimnasio.getPlanesPersonalizados().contains(pp));
	}
	
	@Test
	public void testCrearPlanPersonalizado2() {
		PlanPersonalizado pp = gimnasio.crearPlanPersonalizado(null, monitor, requisitoCancelaciones, TipoObjetivo.GANANCIAMUSCULAR, "Clases para ganar masa muscular");
		assertFalse(gimnasio.getPlanesPersonalizados().contains(pp));
	}
	
	@Test
	public void testCrearPlanPersonalizado3() {
		PlanPersonalizado pp = gimnasio.crearPlanPersonalizado("Ganar musculo", null, requisitoCancelaciones, TipoObjetivo.GANANCIAMUSCULAR, "Clases para ganar masa muscular");
		assertFalse(gimnasio.getPlanesPersonalizados().contains(pp));
	}
	
	@Test
	public void testCrearPlanPersonalizado4() {
		PlanPersonalizado pp = gimnasio.crearPlanPersonalizado("Ganar musculo", monitor, null, TipoObjetivo.GANANCIAMUSCULAR, "Clases para ganar masa muscular");
		assertFalse(gimnasio.getPlanesPersonalizados().contains(pp));
	}
	
	@Test
	public void testCrearPlanPersonalizado6() {
		PlanPersonalizado pp = new PlanPersonalizado("Ganar musculo", monitor, requisitoCancelaciones, TipoObjetivo.GANANCIAMUSCULAR, null);
		assertFalse(gimnasio.getPlanesPersonalizados().contains(pp));
	}
	
	
	@Test
	public void testCrearSesionLibre1() throws FueraHorarioClimatizacion {
		gimnasio.addSala(salaSimple);
		salaSimple.definirHorarioClimatizacion(LocalDateTime.now(), LocalDateTime.now().plusHours(1));
		SesionLibre sl = gimnasio.crearSesionLibre(LocalDate.now(),LocalDateTime.now(), LocalDateTime.now().plusHours(1), salaSimple, 20.0);
		assertTrue(gimnasio.getSesionesLibres().contains(sl));
	}
	
	@Test
	public void testCrearSesionLibre2() throws FueraHorarioClimatizacion {
		salaSimple.definirHorarioClimatizacion(LocalDateTime.now(), LocalDateTime.now().plusHours(5));
		gimnasio.addSala(salaSimple);
		SesionLibre sl = gimnasio.crearSesionLibre(LocalDate.now(),LocalDateTime.now().plusHours(3), LocalDateTime.now().plusHours(1), salaSimple, 20.0);
		assertFalse(gimnasio.getSesionesLibres().contains(sl));
	}
	
	@Test
	public void testCrearSesionLibre4() throws FueraHorarioClimatizacion {
		salaSimple.definirHorarioClimatizacion(LocalDateTime.now(), LocalDateTime.now().plusHours(5));
		SesionLibre sl = gimnasio.crearSesionLibre(LocalDate.now(),LocalDateTime.now().minusHours(1), LocalDateTime.now().plusHours(1), salaSimple, 20.0);
		assertFalse(gimnasio.getSesionesLibres().contains(sl));
	}
	
	@Test
	public void testCrearSesionLibre3() throws FueraHorarioClimatizacion {
		salaSimple.definirHorarioClimatizacion(LocalDateTime.now(), LocalDateTime.now().plusHours(5));
		gimnasio.addSala(salaSimple);
		SesionLibre sl = gimnasio.crearSesionLibre(LocalDate.now(),LocalDateTime.now().minusHours(1), LocalDateTime.now().plusHours(1), salaSimple, 20.0);
		assertFalse(gimnasio.getSesionesLibres().contains(sl));
	}
	
	
	@Test
	public void testCrearSalaSimple1() {
		
		Sala salaSimple1 = gimnasio.crearSalaSimple("Pesas", "Para realizar levantamiento de pesas", 15, LocalDateTime.now(),  LocalDateTime.now().plusHours(12));
		assertTrue(gimnasio.getSalas().contains(salaSimple1));
	}
	
	@Test
	public void testCrearSalaSimple2() {
		
		Sala salaSimple1 = gimnasio.crearSalaSimple(null, "Para realizar levantamiento de pesas", 15, LocalDateTime.now(),  LocalDateTime.now().plusHours(12));
		assertFalse(gimnasio.getSalas().contains(salaSimple1));
	}
	
	@Test
	public void testCrearSalaSimple3() {
		
		Sala salaSimple1 = new SalaSimple("Pesas", null , 15);
		assertFalse(gimnasio.getSalas().contains(salaSimple1));
	}
	
	@Test
	public void testCrearSalaSimple4() {
		
		Sala salaSimple1 = gimnasio.crearSalaSimple("Pesas", "Para realizar levantamiento de pesas" , -15, LocalDateTime.now(),  LocalDateTime.now().plusHours(12));
		assertFalse(gimnasio.getSalas().contains(salaSimple1));
	}
	
	@Test
	public void testCrearSalaSimple5() {
		
		Sala salaSimple1 = gimnasio.crearSalaSimple("Pesas", "Para realizar levantamiento de pesas" , 15, LocalDateTime.now().minusHours(1),  LocalDateTime.now().plusHours(12));
		assertFalse(gimnasio.getSalas().contains(salaSimple1));
	}
	
	@Test
	public void testCrearSalaSimple6() {
		
		Sala salaSimple1 = gimnasio.crearSalaSimple("Pesas", "Para realizar levantamiento de pesas" , 15, LocalDateTime.now(),  LocalDateTime.now().minusHours(1));
		assertFalse(gimnasio.getSalas().contains(salaSimple1));
	}

	@Test
	public void testCrearSalaSimple7() {
		
		Sala salaSimple1 = gimnasio.crearSalaSimple("Pesas", "Para realizar levantamiento de pesas" , 15, LocalDateTime.now().plusHours(2),  LocalDateTime.now());
		assertFalse(gimnasio.getSalas().contains(salaSimple1));
	}


	@Test
	public void testCrearSalaCompuesta1() {
		salaSimple.definirHorarioClimatizacion(LocalDateTime.now(), LocalDateTime.now().plusHours(5));
		Sala salaCompuesta1 = gimnasio.crearSalaCompuesta("sala 2", "clases de zumba", 25, LocalDateTime.now(), LocalDateTime.now().plusHours(12));
		assertTrue(gimnasio.getSalas().contains(salaCompuesta1));
	}
	
	@Test
	public void testCrearSalaCompuesta2() {
		
		Sala salaCompuesta1 = gimnasio.crearSalaCompuesta(null, "clases de zumba", 25, LocalDateTime.now(), LocalDateTime.now().plusHours(12));
		assertFalse(gimnasio.getSalas().contains(salaCompuesta1));
	}
	
	@Test
	public void testCrearSalaCompuesta3() {
		
		Sala salaCompuesta1 =new SalaCompuesta("sala 2", null, 25);
		assertFalse(gimnasio.getSalas().contains(salaCompuesta1));
	}
	
	@Test
	public void testCrearSalaCompuesta4() {
		
		Sala salaCompuesta1 = gimnasio.crearSalaCompuesta("sala 2", "clases de zumba", -25, LocalDateTime.now(), LocalDateTime.now().plusHours(12));
		assertFalse(gimnasio.getSalas().contains(salaCompuesta1));
	}
	
	@Test
	public void testCrearSalaCompuesta6() {
		
		Sala salaCompuesta1 = gimnasio.crearSalaCompuesta("sala 2", "clases de zumba", 25, LocalDateTime.now().minusHours(1), LocalDateTime.now().plusHours(12));
		assertFalse(gimnasio.getSalas().contains(salaCompuesta1));
	}
	
	@Test
	public void testCrearSalaCompuesta7() {
		
		Sala salaCompuesta1 = gimnasio.crearSalaCompuesta("sala 2", "clases de zumba", 25,LocalDateTime.now().plusHours(2),  LocalDateTime.now());
		assertFalse(gimnasio.getSalas().contains(salaCompuesta1));
	}


	@Test
	public void testRegistroCliente1() throws ExcepcionUsuario, FechaPosterior {
		Cliente cliente  = gimnasio.registroCliente("puala", "gympau", "Paula", "9876545678", LocalDate.now(), "1234567891234567", "1234", "Paula Gonzalez Vazquez", tarifaPlana);
		
	
		Boolean res1 = gimnasio.listaUsuarioContieneCliente(cliente);
		assertTrue(res1);
	}
	
	
	@Test(expected = UsuarioExiste.class)
	public void testRegistrocliente2() throws ExcepcionUsuario, FechaPosterior{
		Cliente cliente  = gimnasio.registroCliente("paula154a", "gympau", "Paula", "9876545678", LocalDate.now(), "1234567891234567", "1234", "Paula Gonzalez Vazquez", tarifaPlana);
		Cliente cliente2 =  gimnasio.registroCliente("paula154a", "gympau", "Paula", "9876545678", LocalDate.now(), "1234567891234567", "1234", "Paula Gonzalez Vazquez", tarifaPlana);
		
		assertTrue(cliente2 == null);
		
	}
	
	@Test(expected = UsuarioExiste.class)
	public void testRegistroCliente3() throws ExcepcionUsuario, FechaPosterior {
		Cliente cliente  = gimnasio.registroCliente("paula154a", "gympau", "Paula", "9876545678", LocalDate.now(), "1234567891234567", "1234", "Paula Gonzalez Vazquez", tarifaPlana);
		assertTrue(cliente==null);
	}
	
	@Test(expected = FechaPosterior.class)
	public void testRegistroCliente4() throws ExcepcionUsuario, FechaPosterior {
		Cliente cliente  = gimnasio.registroCliente("luciaaa", "gympau", "Paula", "9876545678", LocalDate.now().plusDays(5), "1234567891234567", "1234", "Paula Gonzalez Vazquez", tarifaPlana);
		assertTrue(cliente==null);
	}
	

	@Test
	public void testRegistroMonitor1() throws UsuarioExiste {
		Monitor monitor = new Monitor ("Manu325", "12345678" ,"gymManu", "Manuel", "manuel.guti@gmail.com");
		
		Boolean res = gimnasio.listaUsuarioContieneMonitor(monitor);
		assertFalse(res);
		
		monitor = gimnasio.registroMonitor("Manu325", "12345678" ,"gymManu", "Manuel", "manuel.guti@gmail.com");
	
		Boolean res1 = gimnasio.listaUsuarioContieneMonitor(monitor);
		assertTrue(res1);
	}
	
	@Test(expected = UsuarioExiste.class)
	public void testRegistroMonitor2() throws UsuarioExiste {
		Monitor monitor = new Monitor ("Manu325", "12345678" ,"gymManu", "Manuel", "manuel.guti@gmail.com");
		
		Boolean res = gimnasio.listaUsuarioContieneMonitor(monitor);
		assertFalse(res);
		
		monitor = gimnasio.registroMonitor("Manu325", "12345678" ,"gymManu", "Manuel", "manuel.guti@gmail.com");
		Boolean res1 = gimnasio.listaUsuarioContieneMonitor(monitor);
		assertTrue(res1);
		
		Monitor monitor2 = gimnasio.registroMonitor("Manu325", "12345678" ,"gymManu", "Manuel", "manuel.guti@gmail.com");
		assertEquals(monitor2, null);
		
	}
	

	@Test
	public void testSetNombre1() {
		Boolean res = gimnasio.setName("Gimnasio");
		assertTrue(res);
		
		assertEquals(gimnasio.getName(), "Gimnasio");
	}
	
	@Test
	public void testSetNombre2() {
		Boolean res = gimnasio.setName(null);
		assertFalse(res);
	}
	
	@Test
	public void testSetSueldo1() {
		Boolean res = gimnasio.setBaseSalaryPerMonth(2000.99);
		assertTrue(res);
		
		assertEquals(gimnasio.getBaseSalaryPerMonth(), 2000.99, 0.00);
	}
	
	@Test
	public void testSetSueldo2() {
		Boolean res = gimnasio.setBaseSalaryPerMonth(-1.00);
		assertFalse(res);
	}
	

	@Test
	public void testSetRateHour1() {
		Boolean res = gimnasio.setRateHour(-1.0);
		assertFalse(res);
	}
	
	@Test
	public void testSetExtra2() {
		Boolean res = gimnasio.setRateHour(23.99);
		assertTrue(res);
		
		assertEquals(gimnasio.getRateHour(), 23.99, 0);
	}

	@Test
	public void testSetMaxCancelaciones1(){
		Boolean res = gimnasio.setMaxCancelaciones(5);
		assertTrue(res);
		
		assertEquals(gimnasio.getMaxCancelaciones(), 5, 0);
	}
	
	@Test
	public void testSetMaxCancelaciones2(){
		Boolean res = gimnasio.setMaxCancelaciones(-1);
		assertFalse(res);
	}
	
	@Test
	public void testSetNumDiasPenal(){
		Boolean res = gimnasio.setNumDiasPenal(5);
		assertTrue(res);
		
		assertEquals(gimnasio.getNumDiasPenal(), 5, 0);
	}
	
	@Test
	public void testSetNumDiasPena2(){
		Boolean res = gimnasio.setNumDiasPenal(-1);
		assertFalse(res);
	}
	
	@Test
	public void testSetUsuarioRegistrado(){
		Cliente u = new Cliente("Julia", "698574213", LocalDate.of(1999, 03, 24),"pau24", "carafassol9", tarjetaCredito);
		Boolean res = gimnasio.setUsuarioRegistrado(u);
		assertTrue(res);
		assertEquals(gimnasio.getUsuarioRegistrado(), u);
	}
	
	@Test
	public void testCerrarSesion1() throws ExcepcionUsuario, FechaPosterior, IOException, SinTarifa{
		Cliente cliente = gimnasio.registroCliente("lucia", "gympau", "Paula", "9876545678", LocalDate.now(), "1234567890987654", "1234", "Paula Gonzalez Vazquez", tarifaPlana);
		cliente.setTarifa(tarifaPlana);
		gimnasio.iniciarSesion("lucia", "gympau");
		Boolean res = gimnasio.cerrarSesion(cliente);
		assertTrue(res);
	}
	
	@Test
	public void testCerrarSesion2() throws IOException{
		Cliente cliente =new Cliente("Paula", "9876545678", LocalDate.now(), "clara", "llloooosss", tarjetaCredito);
		Boolean res = gimnasio.cerrarSesion(cliente);
		assertFalse(res);
	}
	
	@Test
	public void testAddReserva1(){
		Cliente cliente1 = new Cliente("Paula", "9876545678", LocalDate.now(), "lucia", "llloooosss", tarjetaCredito);
		Reserva reserva = new Reserva(cliente1, sesionAG, true);
		assertTrue(gimnasio.addReserva(reserva));
	}
	
	@Test
	public void testAddReserva2(){
		Cliente cliente1 = new Cliente("Paula", "9876545678", LocalDate.now(), "lucia", "llloooosss", tarjetaCredito);
		Reserva reserva = new Reserva(cliente1, sesionAG, true);
		gimnasio.addReserva(reserva);
		assertFalse(gimnasio.addReserva(reserva));
	}
	
	@Test
	public void testAddReserva3(){
		Cliente cliente1 = new Cliente("Paula", "9876545678", LocalDate.now(), "lucia", "llloooosss", tarjetaCredito);
		assertFalse(gimnasio.addReserva(null));
	}

	@Test
	public void testRemoveReserva1(){
		Cliente cliente1 = new Cliente("Paula", "9876545678", LocalDate.now(), "lucia", "llloooosss", tarjetaCredito);
		Reserva reserva = new Reserva(cliente1, sesionAG, true);
		gimnasio.addReserva(reserva);
		assertTrue(gimnasio.removeReserva(reserva));
	}
	
	@Test
	public void testRemoveReserva2(){
		Cliente cliente1 = new Cliente("Paula", "9876545678", LocalDate.now(), "lucia", "llloooosss", tarjetaCredito);
		assertFalse(gimnasio.removeReserva(null));
	}
	
	@Test
	public void testRemoveReserva3(){
		Cliente cliente1 = new Cliente("Paula", "9876545678", LocalDate.now(), "lucia", "llloooosss", tarjetaCredito);
		Reserva reserva = new Reserva(cliente1, sesionAG, true);
		assertFalse(gimnasio.removeReserva(reserva));
	}
	
	@Test
	public void testAddTipoActividad1(){
		TipoActividad tipo = new TipoActividad("Kung fu");
		assertTrue(gimnasio.addTipoActividad(tipo));
	}
	
	@Test
	public void testAddTipoActividad2(){
		assertFalse(gimnasio.addTipoActividad(null));
	}
	
	@Test
	public void testAddSala1(){
		Sala sala1 = new SalaSimple("Cinta", "Para correr", 2);
		assertTrue(gimnasio.addSala(sala1));
	}
	
	@Test
	public void testAddSala2(){
		assertFalse(gimnasio.addSala(null));
	}
	
	@Test
	public void testAddSala3(){
		Sala sala1 = new SalaSimple("Cinta", "Para correr", 2);
		gimnasio.addSala(sala1);
		assertFalse(gimnasio.addSala(sala1));
	}
	
	@Test
	public void testRemoveSala1(){
		Sala sala1 = new SalaSimple("Cinta", "Para correr", 2);
		gimnasio.addSala(sala1);
		assertTrue(gimnasio.removeSala(sala1));
	}
	
	@Test
	public void testRemoveSala2(){
		assertFalse(gimnasio.removeSala(null));
	}
	
	@Test
	public void testRemoveSala3(){
		Sala sala1 = new SalaSimple("Cinta", "Para correr", 2);
		assertFalse(gimnasio.removeSala(sala1));
	}
	
	@Test
	public void testlistaUsuarioContieneMonitor1() throws ExcepcionUsuario, FechaPosterior, IOException{
		Monitor monitor = gimnasio.registroMonitor("Carlos325", "12345678" ,"gymManu", "Manuel", "manuel.guti@gmail.com");
		Boolean res = gimnasio.listaUsuarioContieneMonitor(monitor);
		assertTrue(res);
	}
	
	@Test
	public void testlistaUsuarioContieneMonitor2() {
		Monitor monitor = new Monitor("Luis325", "12345678" ,"gymManu", "Manuel", "manuel.guti@gmail.com");
		Boolean res = gimnasio.listaUsuarioContieneMonitor(monitor);
		assertFalse(res);
	}
	
	@Test
	public void testListaUsuarioContieneCliente1(){
		Cliente cliente1 = new Cliente ("Ramon", "6545678745", LocalDate.of(2003, 03, 24), "ramon34", "tyui", tarjetaCredito);
		gimnasio.getUsuarios().put("Ramon", cliente1);
		assertTrue(gimnasio.listaUsuarioContieneCliente(cliente1));
	}
	
	@Test
	public void testListaUsuarioContieneCliente2(){
		assertFalse(gimnasio.listaUsuarioContieneCliente(null));
	}
	
	@Test
	public void testBeneficioPorActividad1(){
		actividad.addSesionMonitorizada(sesionAG);
		assertTrue(gimnasio.beneficioPorActividad(LocalDate.now().getMonth(), actividad).equals(40.00));
			
	}
	
	@Test
	public void testBeneficioPorActividad2(){
		actividad.addSesionMonitorizada(sesionAG);
		assertFalse(gimnasio.beneficioPorActividad(LocalDate.now().getMonth(), null).equals(40.00));
			
	}
	
	@Test
	public void testBeneficioPorActividad3(){;
		assertFalse(gimnasio.beneficioPorActividad(null, actividad).equals(40.0));
			
	}
	
	@Test
	public void testBeneficioGimnasio1(){
		List<Sesion> listaSesiones = new ArrayList<>();
		SesionAG sesionAG1 = new SesionAG (LocalDate.now().plusDays(2), LocalDateTime.now(), LocalDateTime.now().plusHours(1), actividad, salaSimple, 40.0);
		SesionAG sesionAG2 = new SesionAG (LocalDate.now().plusDays(2), LocalDateTime.now(), LocalDateTime.now().plusHours(1), actividad, salaSimple, 60.0);
		listaSesiones.add(sesionAG1);
		listaSesiones.add(sesionAG2);
		assertTrue(gimnasio.beneficioGimnasio(listaSesiones).equals(100.0));
	}
	
	@Test
	public void testBeneficioGimnasio2(){
		List<Sesion> listaSesiones = new ArrayList<>();
		SesionAG sesionAG1 = new SesionAG (LocalDate.now().plusDays(2), LocalDateTime.now(), LocalDateTime.now().plusHours(1), actividad, salaSimple, 40.0);
		SesionAG sesionAG2 = new SesionAG (LocalDate.now().plusDays(2), LocalDateTime.now(), LocalDateTime.now().plusHours(1), actividad, salaSimple, 60.0);
		listaSesiones.add(sesionAG1);
		listaSesiones.add(sesionAG2);
		assertTrue(gimnasio.beneficioGimnasio(null).equals(-1.0));
	}
	
	@Test
	public void testGastoEquipacion(){
		ArrayList<Equipacion> toRemove = new ArrayList<>();
		for(Equipacion equipacion: gimnasio.getEquipaciones()){
			toRemove.add(equipacion);
		}
		
		for(Equipacion equipacion1: toRemove){
			gimnasio.getEquipaciones().remove(equipacion1);
		}
		gimnasio.darAltaMaterial("pesas", 5, LocalDate.now().minusMonths(3), 10.99);
		assertEquals(gimnasio.gastoEquipacion(gimnasio.getEquipaciones(), Month.JUNE, 2023), 10.99*5, 0.0);
	}
	
	@Test
	public void testFiltradoReservasMes1() throws MesNoTerminado{
		Actividad actividad1 = new ActividadGrupal("Yoga", monitor, requisitoCancelaciones, tipoActividad);
		gimnasio.getActividades().add(actividad1);
		SesionAG miSesion = new SesionAG (LocalDate.now().minusMonths(5), LocalDateTime.now().minusMonths(5), LocalDateTime.now().minusMonths(5).plusHours(1), actividad1, salaSimple, 40.0);
		actividad1.addSesionMonitorizada(miSesion);
		Reserva reserva1 = new Reserva(cliente4, miSesion, true);
		Reserva reserva2 = new Reserva(cliente5, miSesion, true);
		gimnasio.getReservas().add(reserva1);
		gimnasio.getReservas().add(reserva2);
		cliente4.addReserva(reserva);
		cliente5.addReserva(reserva);
		assertTrue(gimnasio.filtradoReservasMes(LocalDate.now().minusMonths(5).getMonth(), LocalDate.now().getYear()).size()==2);
	}
	
	@Test
	public void testFiltradoEquipacionMes() throws MesNoTerminado{
		
	}
	
	@Test
	public void testOcupacionSala() throws FechaPosterior, ExcepcionUsuario, SalaLlena, ExcepcionEdad, ExcepcionVeterania, ExcepcionCancelaciones, ExcepcionPago, ApuntarseAntesDe48h, IOException, ExcepcionUsuario, FechaPosterior, ApuntadoMismaHora {
		Cliente cliente = gimnasio.registroCliente("lorena", "gympau", "Paula", "9876545678", LocalDate.now().minusYears(18), "1234567891234567", "1234", "Paula Gonzalez Vazquez", tarifaPlana);
		gimnasio.getActividades().add(actividad);
		actividad.addSesionMonitorizada(sesionAG);
		salaSimple.setAforo(10);
		cliente.realizarReserva(sesionAG);
		assertEquals(gimnasio.ocupacionSala(salaSimple), 1, 0.0);
	}
	
	@Test
	public void testGetSalaSimpleByName1(){
		Sala salaSimple0 = new SalaSimple("Running", "para correer", 15);
		gimnasio.addSala(salaSimple0);
		Sala salaSimple1 = gimnasio.getSalaSimpleByName("Running");
		assertEquals(salaSimple0, salaSimple1);
	}
	
	@Test
	public void testGetSalaSimpleByName2(){
		gimnasio.addSala(salaSimple);
		Sala salaSimple1 = gimnasio.getSalaSimpleByName("pesas");
		assertNotEquals(salaSimple, salaSimple1);
	}
	
	@Test
	public void testGetMonitorByName1() throws ExcepcionUsuario, FechaPosterior, IOException{
		Monitor monitor = gimnasio.registroMonitor("Lucas325", "12345678" ,"gymManu", "Manuel", "manuel.guti@gmail.com");
		Monitor monitor1 = gimnasio.getMonitorByName("Lucas325");
		assertEquals(monitor, monitor1);
	}
	
	@Test
	public void testGetMonitorByName2() throws ExcepcionUsuario, FechaPosterior, IOException{
		Monitor monitor = gimnasio.registroMonitor("Sergio325", "12345678" ,"gymManu", "Manuel", "manuel.guti@gmail.com");
		Monitor monitor1 = gimnasio.getMonitorByName("Carlos325");
		assertNotEquals(monitor, monitor1);
	}
	
	@Test
	public void testGetTipoActividadByName1(){
		TipoActividad tipoOriginal = new TipoActividad("Padel");
		gimnasio.addTipoActividad(tipoOriginal);
		TipoActividad tipo = gimnasio.getTipoActividadByName("Padel");
		assertEquals(tipo,tipoOriginal);
	}
	
	@Test
	public void testGetTipoActividadByName2(){
		gimnasio.addTipoActividad(tipoActividad);
		TipoActividad tipo = gimnasio.getTipoActividadByName("Yoga");
		assertNotEquals(tipo, tipoActividad);
	}
	
	@Test
	public void testGetTipoObjetivoByName1(){
		gimnasio.crearPlanPersonalizado("Ganar musculo", monitor, requisito, TipoObjetivo.GANANCIAMUSCULAR, "Clases para ganar masa muscular");
		TipoObjetivo tipo = gimnasio.getTipoObjetivoByName("GANANCIAMUSCULAR");
		assertEquals(TipoObjetivo.GANANCIAMUSCULAR, tipo);
	}
	@Test
	public void testGetTipoObjetivoByName2(){
		gimnasio.crearPlanPersonalizado("Ganar musculo", monitor, requisito, TipoObjetivo.GANANCIAMUSCULAR, "Clases para ganar masa muscular");
		TipoObjetivo tipo = gimnasio.getTipoObjetivoByName("PERDIDAPESO");
		assertNotEquals(TipoObjetivo.GANANCIAMUSCULAR, tipo);
	}
	
	@Test
	public void testGetSesionByData1() throws FueraHorarioClimatizacion{
		salaSimple.definirHorarioClimatizacion(LocalDateTime.now(), LocalDateTime.now().plusHours(5));
		Sesion sesion = gimnasio.crearSesionLibre(LocalDate.now(), LocalDateTime.now(), LocalDateTime.now().plusHours(1), salaSimple, 10.0);
		Sesion sesionLibre = gimnasio.getSesionByData(null, null, LocalDateTime.now(), LocalDateTime.now().plusHours(1), LocalDate.now(), salaSimple, 15);
		assertEquals(sesion, sesionLibre);	
	}
	
	@Test
	public void testGetSesionByData2() throws FueraHorarioClimatizacion{
		salaSimple.definirHorarioClimatizacion(LocalDateTime.now(), LocalDateTime.now().plusHours(5));
		Sesion sesion = gimnasio.crearSesionLibre(LocalDate.now(), LocalDateTime.now(), LocalDateTime.now().plusHours(1), salaSimple, 10.0);
		Sesion sesionLibre = gimnasio.getSesionByData(null, null, LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(2), LocalDate.now(), salaSimple, 15);
		assertEquals(sesion, sesionLibre);	
	}
	@Test
	public void testGetSesionLibreByData() throws FueraHorarioClimatizacion {
		salaSimple.definirHorarioClimatizacion(LocalDateTime.now(), LocalDateTime.now().plusHours(5));
		Sesion sesion = gimnasio.crearSesionLibre(LocalDate.now(), LocalDateTime.now(), LocalDateTime.now().plusHours(1), salaSimple, 10.0);
		Sesion sesionLibre = gimnasio.getSesionByData(null, null, LocalDateTime.now(), LocalDateTime.now().plusHours(1), LocalDate.now(), salaSimple, 15);
		assertEquals(sesion, sesionLibre);
	}
	
	@Test
	public void testGetSesionLibreByData2() throws FueraHorarioClimatizacion{
		salaSimple.definirHorarioClimatizacion(LocalDateTime.now(), LocalDateTime.now().plusHours(5));
		Sesion sesion = gimnasio.crearSesionLibre(LocalDate.now(), LocalDateTime.now(), LocalDateTime.now().plusHours(1), salaSimple, 10.0);
		Sesion sesionLibre = gimnasio.getSesionByData(null, null, LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(2), LocalDate.now(), salaSimple, 15);
		assertEquals(sesion, sesionLibre);	
	}
	
	@Test
	public void testGetSalaByName1(){
		Sala salaSimple0 = new SalaSimple("Footing", "para correer", 15);
		gimnasio.addSala(salaSimple0);
		Sala salaSimple1 = gimnasio.getSalaByName("Footing");
		assertEquals(salaSimple0, salaSimple1);
	}
	
	@Test
	public void testGetSalaByName2(){
		Sala salaSimple0 = new SalaSimple("Footing", "para correer", 15);
		gimnasio.addSala(salaSimple0);
		Sala salaSimple1 = gimnasio.getSalaByName("footing");
		assertNotEquals(salaSimple0, salaSimple1);
	}
	
	@Test
	public void testGetActividadByData1(){
		Actividad actividad = gimnasio.crearActividadGrupal("zumba", "para bailar", requisito, monitor, tipoActividad);
		Actividad actividad1 = gimnasio.getActividadByData("zumba", monitor);
		assertEquals(actividad, actividad1);
		
	}
	
	@Test
	public void testGetActividadByData2(){
		Actividad actividad = gimnasio.crearActividadGrupal("zumba", "para bailar", requisito, monitor, tipoActividad);
		Actividad actividad1 = gimnasio.getActividadByData("sumba", monitor);
		assertNotEquals(actividad, actividad1);
		
	}
	
	@Test
	public void testGetReservaByData1() throws ExcepcionUsuario, FechaPosterior, ExcepcionEdad, ExcepcionVeterania, ExcepcionCancelaciones, SalaLlena, ExcepcionPago, ApuntarseAntesDe48h 
	{
		Cliente cliente1 = gimnasio.registroCliente("celia", "gympau", "Paula", "9876545678", LocalDate.now().minusYears(19), "1234567891234567", "1234","Paula Gonzalez Vazquez", tarifaPlana);
		gimnasio.getActividades().add(actividad);
		SesionAG miSesion = new SesionAG (LocalDate.now().minusMonths(3), LocalDateTime.now().minusMonths(3), LocalDateTime.now().minusMonths(3).plusHours(1), actividad, salaSimple, 40.0);
		actividad.addSesionMonitorizada(miSesion);
		Reserva reserva = new Reserva(cliente1, miSesion, true);
		miSesion.addReserva(reserva);
		gimnasio.addReserva(reserva);
		assertEquals(reserva, gimnasio.getReservaByData("Cardio", null,  LocalDateTime.now().minusMonths(3), LocalDateTime.now().minusMonths(3).plusHours(1),LocalDate.now().minusMonths(3), salaSimple, miSesion.getReservas().size()));
		
		
	}
	
	@Test
	public void testGetReservaByData2() throws ExcepcionEdad, ExcepcionVeterania, ExcepcionCancelaciones, FechaPosterior, ExcepcionUsuario, SalaLlena, ExcepcionPago, ApuntarseAntesDe48h, ApuntadoMismaHora 
	{
		Cliente cliente = gimnasio.registroCliente("ana", "gympau", "Paula", "9876545678", LocalDate.now(), "1234567891234567", "1234","Paula Gonzalez Vazquez", tarifaPlana);
		actividad.addSesionMonitorizada(sesionAG);
		cliente.realizarReserva(sesionAG);
		for(Reserva res: gimnasio.getReservas()){
			assertNotEquals(res, cliente.getReservaByData("Sumbita", null, LocalDateTime.now().plusDays(2), LocalDateTime.now().plusDays(2).plusHours(1), LocalDate.now().plusDays(2), salaSimple, 15));
		}
	}
	
	@Test
	public void testGetMonitorByActividad1(){
		Actividad actividad1 = new ActividadGrupal("baile de salon", monitor, requisitoCancelaciones, tipoActividad);
		gimnasio.getActividades().add(actividad1);
		Monitor monitorOriginal = actividad1.getMonitor();
		Monitor monitor1 = gimnasio.getMonitorByActividad("baile de salon");
		assertEquals(monitorOriginal, monitor1);
	}
	
	@Test
	public void testGetMonitorByActividad2(){
		Actividad actividad = gimnasio.crearActividadGrupal("zumba", "para bailar", requisito, monitor, tipoActividad);
		Monitor monitor1 = gimnasio.getMonitorByActividad("sumba");
		assertNotEquals(monitor, monitor1);
	}
	
	@Test
	public void testDarAltaMaquinaPropiedad1(){
		
		assertTrue(gimnasio.getMaquinas().contains(maquina));
	}
	
	@Test
	public void testDarAltaMaquinaPropiedad2() throws ExcepcionUsuario, FechaPosterior{
		Maquina maquina = new Propiedad("Cinta", "para correr", "Apple", 500.50, LocalDate.now());
		assertFalse(gimnasio.getMaquinas().contains(maquina));
	}
	
	@Test
	public void testDarAltaMaterial1(){
		assertTrue(gimnasio.getMaterial().contains(material));
	}
	
	@Test
	public void testDarAltaMaterial2(){
		Material material = new Material("para pesas", 4, LocalDate.now(), 25.10);
		assertFalse(gimnasio.getMaterial().contains(material));
	}
			
	@Test
	public void testDarAltaMaquinaAlquilada1(){
		
		assertTrue(gimnasio.getMaquinas().contains(maquina));
	}
	
	@Test
	public void testDarAltaMaquinaAlquilada2() throws ExcepcionUsuario, FechaPosterior{
		Maquina maquina = new Alquilada("Cinta", "para correr", "Apple", 500.50, LocalDate.now());
		assertFalse(gimnasio.getMaquinas().contains(maquina));
	}
	
	@Test
	public void testCambiarEstadoMaquina1 (){
		Boolean res = gimnasio.cambiarEstadoMaquina(maquina, Estado.AVERIADA);
		assertTrue(res);
	}
	
	@Test
	public void testCambiarEstadoMaquina2 () throws ExcepcionUsuario, FechaPosterior{
		Maquina maquina = new Propiedad("Spinning", "para correr", "Apple", 500.50, LocalDate.now());
		Boolean res = gimnasio.cambiarEstadoMaquina(maquina, Estado.AVERIADA);
		assertFalse(res);
	}
	
	
	@Test
	public void testGetMaterialByData1(){
		Material materialOriginal = new Material("muy elastico", 4, LocalDate.now().minusDays(1), 25.10);
		gimnasio.getEquipaciones().add(materialOriginal);
		Material material1 = gimnasio.getMaterialByData("muy elastico", 4, LocalDate.now().minusDays(1), 25.10);
		assertEquals(materialOriginal, material1);
	}
	
	@Test
	public void testGetMaterialByData2(){
		Material material = gimnasio.darAltaMaterial("para pesas", 4, LocalDate.now(), 25.10);
		Material material1 = gimnasio.getMaterialByData("para pesa", 4, LocalDate.now(), 25.10);
		assertNotEquals(material, material1);
	}
	
	@Test
	public void testGetEquipacionByData1(){
		Equipacion equipacion1 = gimnasio.darAltaMaterial("para correr", 4, LocalDate.now(), 25.10);
		Equipacion equipacion2 = gimnasio.getEquipacionByData("para correr", LocalDate.now(), 25.10);
		assertEquals(equipacion1, equipacion2);
	}
	
	@Test
	public void testGetEquipacionByData2(){
		Equipacion equipacion = gimnasio.darAltaMaterial("para correr", 4, LocalDate.now(), 25.10);
		Equipacion equipacion1 = gimnasio.getEquipacionByData("para pesitas", LocalDate.now(), 25.10);
		assertNotEquals(equipacion, equipacion1);
	}
	
	@Test
	public void testGetPlanBySesion1(){
		PlanPersonalizado planPersonalizado = new PlanPersonalizado("Ganar musculo", monitor, requisito, TipoObjetivo.GANANCIAMUSCULAR, "Clases para ganar masa muscular");
		SesionPP sesionPP = new SesionPP(LocalDate.now(), LocalDateTime.now(), LocalDateTime.now().plusHours(1), planPersonalizado, salaSimple, 40.0);
		gimnasio.getActividades().add(planPersonalizado);
		planPersonalizado.getSesionesMonitorizadas().add(sesionPP);
		PlanPersonalizado plan = gimnasio.getPlanBySesion(sesionPP);
		assertEquals(planPersonalizado, plan);
	}
	
	@Test
	public void testGetPlanBySesion2(){
		PlanPersonalizado planPersonalizado = new PlanPersonalizado("Ganar musculo", monitor, requisito, TipoObjetivo.GANANCIAMUSCULAR, "Clases para ganar masa muscular");
		SesionPP sesionPP = new SesionPP(LocalDate.now(), LocalDateTime.now(), LocalDateTime.now().plusHours(1), planPersonalizado, salaSimple, 40.0);
		gimnasio.getActividades().add(actividad);
		actividad.addSesionMonitorizada(sesionAG);
		planPersonalizado.getSesionesMonitorizadas().add(sesionPP);
		PlanPersonalizado plan = gimnasio.getPlanBySesion(sesionAG);
		assertNotEquals(planPersonalizado, plan);
	}
	
	@Test
	public void testGetSesionInPlanByData1(){
		salaSimple.definirHorarioClimatizacion( LocalDateTime.now(), LocalDateTime.now().plusHours(1));
		PlanPersonalizado planPersonalizado1 = new PlanPersonalizado("Correr mucho", monitor, requisito, TipoObjetivo.GANANCIAMUSCULAR, "Clases para ganar masa muscular");
		SesionPP sesionPP1 = new SesionPP(LocalDate.now(), LocalDateTime.now(), LocalDateTime.now().plusHours(1), planPersonalizado1, salaSimple, 50.0);
		gimnasio.getActividades().add(planPersonalizado1);
		planPersonalizado1.addSesionMonitorizada(sesionPP1);
		Sesion laSesion = gimnasio.getSesionInPlanByData("Correr mucho",TipoObjetivo.GANANCIAMUSCULAR,LocalDateTime.now(), LocalDateTime.now().plusHours(1),LocalDate.now(),salaSimple,sesionPP1.getReservas().size());
		assertEquals(sesionPP1, laSesion);
		
	}
	
	@Test
	public void testGetSesionInPlanByData2(){
		PlanPersonalizado planPersonalizado1 = new PlanPersonalizado("Correr mucho", monitor, requisito, TipoObjetivo.GANANCIAMUSCULAR, "Clases para ganar masa muscular");
		SesionPP sesionPP1 = new SesionPP(LocalDate.now(), LocalDateTime.now(), LocalDateTime.now().plusHours(1), planPersonalizado1, salaSimple, 50.0);
		gimnasio.getActividades().add(planPersonalizado1);
		planPersonalizado1.addSesionMonitorizada(sesionPP1);
		Sesion laSesion = gimnasio.getSesionInPlanByData(null,TipoObjetivo.GANANCIAMUSCULAR,LocalDateTime.now(), LocalDateTime.now().plusHours(1),LocalDate.now(),salaSimple,sesionPP1.getReservas().size());
		assertNotEquals(sesionPP1, laSesion);
		
	}
	
	@Test
	public void testReservasCanceladasMes1(){
		Actividad actividad3 = new ActividadGrupal("Actividad motora", monitor, requisitoCancelaciones, tipoActividad);
		gimnasio.getActividades().add(actividad3);
		SesionAG laSesion1 = new SesionAG (LocalDate.now(), LocalDateTime.now(), LocalDateTime.now().plusHours(1), actividad3, salaSimple, 20.0);
		actividad3.addSesionMonitorizada(laSesion1);
		Reserva reserva4 = new Reserva(cliente4, laSesion1, false);
		cliente4.addReserva(reserva);
		laSesion1.addReserva(reserva4);
		gimnasio.addReserva(reserva4);
		assertEquals(gimnasio.reservasCanceladasMes(LocalDate.now().getMonth(), actividad3).size(),1);
	}
	
	@Test
	public void testReservasCanceladasMes2(){
		assertFalse(gimnasio.reservasCanceladasMes(LocalDate.now().getMonth(), null)!=null);
	}
	
	@Test
	public void testReservasCanceladasMes3(){
		assertFalse(gimnasio.reservasCanceladasMes(null, actividad)!=null);
	}
	
	@Test
	public void testReservasNoCanceladasMes1() {
		Actividad actividad3 = new ActividadGrupal("Actividad motora", monitor, requisitoCancelaciones, tipoActividad);
		gimnasio.getActividades().add(actividad3);
		SesionAG laSesion1 = new SesionAG (LocalDate.now(), LocalDateTime.now(), LocalDateTime.now().plusHours(1), actividad3, salaSimple, 20.0);
		actividad3.addSesionMonitorizada(laSesion1);
		Reserva reserva4 = new Reserva(cliente4, laSesion1, true);
		cliente4.addReserva(reserva);
		laSesion1.addReserva(reserva4);
		gimnasio.addReserva(reserva4);
		assertEquals(gimnasio.reservasNoCanceladasMes(LocalDate.now().getMonth(), actividad3).size(),1);
	}
	
	@Test
	public void testReservasNoCanceladasMes2() {;
		assertFalse(gimnasio.reservasNoCanceladasMes(LocalDate.now().getMonth(), null)!=null);
	}
	
	@Test
	public void testReservasNoCanceladasMes3() {
		
		assertFalse(gimnasio.reservasNoCanceladasMes(null, actividad)!=null);
	}
	
	@Test 
	public void testGetUsuarioByData1(){
		Usuario user6 = new Cliente ("Vicente", "698574213", LocalDate.of(1999, 03, 24), "vinigym", "gymvini", tarjetaCredito);
		gimnasio.getUsuarios().put("vinigym", user6);
		assertTrue(gimnasio.getUsuarioByData("vinigym", "gymvini").equals(user6));
	}
	
	@Test 
	public void testGetUsuarioByData2(){
		
		assertFalse(gimnasio.getUsuarioByData(null, "gymvini")!=null);
	}
			
}
