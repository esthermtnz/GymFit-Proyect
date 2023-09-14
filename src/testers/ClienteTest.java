package testers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import aplicacion.Gimnasio;
import aplicacion.Reserva;
import aplicacion.actividad.ActividadGrupal;
import aplicacion.actividad.PlanPersonalizado;
import aplicacion.actividad.TipoActividad;
import aplicacion.actividad.TipoObjetivo;
import aplicacion.actividad.Actividad;
import aplicacion.actividad.sesion.requisito.*;
import aplicacion.actividad.sesion.SesionAG;
import aplicacion.actividad.sesion.SesionPP;
import aplicacion.sala.SalaSimple;
import aplicacion.usuario.Cliente;
import aplicacion.usuario.Monitor;
import aplicacion.usuario.tarifa.TarifaPlana;
import aplicacion.usuario.tarifa.TarjetaCredito;
import aplicacion.usuario.tarifa.TipoTarifaPlana;
import aplicacion.usuario.*;
import aplicacion.excepciones.*;
import es.uam.eps.padsof.telecard.*;
/**
 * Test
 */
public class ClienteTest {
	
	private Cliente cliente,  cliente1,cliente2, cliente3, cliente4, cliente5;
	private TarifaPlana tarifaPlana;
	private SesionAG sesion1, sesion2, sesion3, sesion4;
	private Reserva reserva1, reserva2, reserva3, reserva4;
	private Monitor monitor;
	private ActividadGrupal actividad1, actividad2, actividad3;
	private Requisito requisito, requisito2;
	private SalaSimple salaSimple, salaSimple1, salaSimple2;
	private TarjetaCredito tarjetaCredito1, tarjetaCredito2, tarjetaCredito3, tarjetaCredito4, tarjetaCredito5;
	private TipoActividad tipoActividad;
	private TipoTarifaPlana tipoTarifaPlana;
	private Gimnasio gimnasio;
	private TipoObjetivo tipoObjetivo;
	private PlanPersonalizado planPersonalizado;
	
	/**
	 * Test
	 */
	@Before
	public void setUp() throws Exception{
		gimnasio = gimnasio.getGimnasio();
		tarjetaCredito1 = new TarjetaCredito("4507670001000009","346", "Julia");
		tarjetaCredito2 = new TarjetaCredito("5020470001370055","997", "Marcos");
		tarjetaCredito3 = new TarjetaCredito("5020473565370055","957", "Marta");
		tarjetaCredito4 = new TarjetaCredito("5020473565370055","957", "Fernando");
		tarjetaCredito5 = new TarjetaCredito("3333","123", "Manuel");
		tipoTarifaPlana = TipoTarifaPlana.TRIMESTRAL;
		
		tarifaPlana = new TarifaPlana(LocalDate.now(), LocalDate.now().plusMonths(3), 25.99, tipoTarifaPlana, tipoActividad);
		cliente1 = new Cliente ("Daniela", "695774213", LocalDate.of(2003, 03, 24),"danielagym", "gymdaniela", tarjetaCredito2);
		cliente1.setFechaRegistro(LocalDate.now().minusDays(2));
		cliente2 = new Cliente ("Marcos", "698574213", LocalDate.of(2003, 03, 24),"marc24", "caracasdol9", tarjetaCredito2);
		cliente2.setFechaRegistro(LocalDate.now().minusDays(3));
		cliente3 = new Cliente ("Marta", "698574213", LocalDate.of(2003, 03, 24), "mart24", "caracodd9", tarjetaCredito3);
		cliente3.setFechaRegistro(LocalDate.now().minusDays(5));
		cliente4 = new Cliente ("Fernando", "692274213", LocalDate.of(2003, 03, 24),"fereserva24", "caracodd9", tarjetaCredito4);
		cliente4.setFechaRegistro(LocalDate.now().minusDays(6));
		cliente5 = new Cliente ("Manuel", "692274211", LocalDate.of(2004, 02, 24), "manu24", "caracodd9", tarjetaCredito5);
		cliente5.setFechaRegistro(LocalDate.now().minusDays(7));
		
		
		monitor = new Monitor("Manu325", "00523821","gymManu", "Manuel", "manuel.guti@gmail.com");
		
		tipoActividad = new TipoActividad("Zumba2");
		
		salaSimple = new SalaSimple("sala1", "para zumba", 1);
		salaSimple1 = new SalaSimple("sala2", "para jovenes", 3);
		salaSimple2 = new SalaSimple("sala3", "correr", 6);
		requisito2 = new RequisitoEdad(18,20);
		actividad1 = new ActividadGrupal("Zumba",monitor, requisito2,tipoActividad);
		actividad2 = new ActividadGrupal("Aerobic",monitor, requisito2,tipoActividad);
		actividad3 = new ActividadGrupal("Aerobic",monitor, requisito2,tipoActividad);
		
		
		sesion1 = new SesionAG (LocalDate.of(2023,3,28), LocalDateTime.now(), LocalDateTime.now().plusHours(1), actividad1, salaSimple2, 40.0);
		sesion2 = new SesionAG (LocalDate.of(2023,3,18), LocalDateTime.now(), LocalDateTime.now().plusHours(1), actividad2, salaSimple2, 40.0);
		sesion3 = new SesionAG (LocalDate.of(2023,3,23), LocalDateTime.now(), LocalDateTime.now().plusHours(1), actividad3, salaSimple2, 40.0);
		sesion4 = new SesionAG (LocalDate.of(2023,3,21), LocalDateTime.now(), LocalDateTime.now().plusHours(1), actividad1, salaSimple2, 40.0);
		
		tipoTarifaPlana = TipoTarifaPlana.TRIMESTRAL;
		
		monitor = new Monitor("Manu325","00523821", "gymManu", "Manuel", "manuel.guti@gmail.com");
		requisito = new RequisitoEdad (18, 50);
		planPersonalizado = new PlanPersonalizado("Ganar musculo", monitor, requisito, TipoObjetivo.GANANCIAMUSCULAR, "Clases para ganar masa muscular");
		tipoObjetivo = TipoObjetivo.PERDIDAPESO;
	}
	/**
	 * Test
	 */
	@Test
	public void testSetNombre1() {
		Boolean res = cliente2.setNombre("Fran");
		assertTrue(res);
		
		assertEquals(cliente2.getNombre(), "Fran");
	}
	/**
	 * Test
	 */
	@Test
	public void testSetNombre2() {
		Boolean res = cliente2.setNombre(null);
		assertFalse(res);
		
	}
	/**
	 * Test
	 */
	@Test
	public void testSetTelefono1() {
		Boolean res = cliente2.setTelefono("642859566");
		assertTrue(res);
		
		assertEquals(cliente2.getTelefono(), "642859566");
	}
	/**
	 * Test
	 */
	@Test
	public void testSetTelefono2() {
		Boolean res = cliente2.setTelefono("null");
		assertTrue(res);
	}
	/**
	 * Test
	 */
	@Test
	public void testSetFechaNacimiento1() {
		Boolean res = cliente2.setFechaNacimiento(LocalDate.now());
		assertTrue(res);
		
		assertEquals(cliente2.getFechaNacimiento(), LocalDate.now());
	}
	/**
	 * Test
	 */
	@Test
	public void testSetFechaNacimiento2() {
		Boolean res = cliente2.setFechaNacimiento(LocalDate.now().minusYears(20));
		assertTrue(res);
	}
	/**
	 * Test
	 */
	@Test
	public void testSetFechaRegistro1()
	{
		Boolean res = cliente2.setFechaRegistro(LocalDate.now().plusDays(5));
		assertFalse(res);
	}
	/**
	 * Test
	 */
	@Test
	public void testSetFechaRegistro2()
	{
		Boolean res = cliente2.setFechaRegistro(LocalDate.now());
		assertTrue(res);
		
		assertEquals(cliente2.getFechaRegistro(), LocalDate.now());
	}
	/**
	 * Test
	 */
	@Test
	public void testSetFechaRegistro3()
	{
		Boolean res = cliente2.setFechaRegistro(LocalDate.now().minusDays(5));
		assertTrue(res);
		
		assertEquals(cliente2.getFechaRegistro(), LocalDate.now().minusDays(5));
	}

	
	
	@Test
	public void testCancelarReserva1()throws ExcepcionUsuario, FechaPosterior, ExcepcionEdad, ExcepcionVeterania, ExcepcionCancelaciones, SalaLlena, ExcepcionPago, ApuntarseAntesDe48h, FechaUnDia, ApuntadoMismaHora {
		ArrayList <Reserva> toRemove = new ArrayList<>();
		Cliente cliente6 = gimnasio.registroCliente("Margarita", "luuciia", "juuuuuul", "123456789", LocalDate.of(2003, 03, 24), "4507670001000009","346", "Julia", tarifaPlana);
		SesionAG sesion4 = new SesionAG (LocalDate.now().plusDays(1), LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1).plusHours(1), actividad1, salaSimple1, 40.0);
		SesionAG sesion5 = new SesionAG (LocalDate.now().plusDays(2), LocalDateTime.now().plusDays(2), LocalDateTime.now().plusDays(2).plusHours(2), actividad1, salaSimple1, 40.0);
		SesionAG sesion6 = new SesionAG (LocalDate.now().plusDays(2), LocalDateTime.now().plusDays(2).plusHours(3), LocalDateTime.now().plusDays(3).plusHours(4), actividad1, salaSimple1, 40.0);
		tarifaPlana.setTipoActividad(tipoActividad);
		assertTrue(cliente6.realizarReserva(sesion4));
		assertTrue(cliente6.realizarReserva(sesion5));
		assertTrue(cliente6.realizarReserva(sesion6));
		for(Reserva reserva : cliente6.getReservas()){
			toRemove.add(reserva);
		}
		
		for(Reserva reserva : toRemove){
			cliente6.cancelarReserva(reserva);
		}
		
		assertTrue(cliente6.getPenalizado());
		assertTrue(cliente6.checkStillPenalizado());
		
	}
	
	@Test
	public void testCancelarReserva2() throws ExcepcionUsuario, FechaPosterior, ExcepcionEdad, ExcepcionVeterania, ExcepcionCancelaciones, SalaLlena, ExcepcionPago, ApuntarseAntesDe48h, FechaUnDia {
		cliente = gimnasio.registroCliente("Pedro", "luuciia", "juuuuuul", "123456789", LocalDate.of(1999, 03, 24), "4507670001000009","346", "Julia", tarifaPlana);
		
		reserva1 = new Reserva(cliente, sesion1, true);
		reserva2 = new Reserva(cliente, sesion2, true);
		cliente.addReserva(reserva1);
		cliente.addReserva(reserva2);
		cliente.cancelarReserva(reserva1);
		cliente.cancelarReserva(reserva2);
		assertFalse(cliente.getPenalizado());
		assertEquals(cliente.getCancelaciones(), 2, 0);
	}

		
	@Test
	public void testRenovarTarifa1() throws ExcepcionUsuario, FechaPosterior{
		cliente = gimnasio.registroCliente("Juan", "luuciia", "juuuuuul", "123456789", LocalDate.of(1999, 03, 24), "4507670001000009","346", "Julia", tarifaPlana);
		tarifaPlana.setCaducada(true);
		Boolean res = cliente.renovarTarifa(tarifaPlana, LocalDate.now().plusMonths(3), tipoActividad);
		assertTrue(res);
		
	}

	@Test
	public void testRenovarTarifa2() throws ExcepcionUsuario, FechaPosterior{
		cliente = gimnasio.registroCliente("Diego", "luuciia", "juuuuuul", "123456789", LocalDate.of(1999, 03, 24), "4507670001000009","346", "Julia", tarifaPlana);
		tarifaPlana = new TarifaPlana(LocalDate.now(), LocalDate.now().plusDays(5), 25.95,tipoTarifaPlana, tipoActividad);
		Boolean res = cliente.renovarTarifa(tarifaPlana, LocalDate.now().plusMonths(3), tipoActividad);
		assertTrue(res);
	}

	/**
	 * Test
	 */
	@Test
	public void testSetUsuario1() {
		Boolean res = cliente2.setUsuario("clau123");
		assertTrue(res);
		
		assertEquals(cliente2.getUsuario(), "clau123");
	}
	/**
	 * Test
	 */
	@Test
	public void testSetUsuario2() {
		Boolean res = cliente2.setUsuario(null);
		assertFalse(res);
	}

	/**
	 * Test
	 */
	@Test
	public void testSetContrasenya1() {
		Boolean res = cliente2.setContrasenya("caracola");
		assertTrue(res);
		
		assertEquals(cliente2.getContrasenya(), "caracola");
	}
	/**
	 * Test
	 */
	@Test
	public void testSetContrasenya2() {
		Boolean res = cliente2.setContrasenya(null);
		assertFalse(res);
	}
	/**
	 * Test
	 */
	@Test
	public void testCheckStillPenalizado1()
	{		
		cliente1.setPenalizado(true);
		cliente1.setPenalizacionHasta(LocalDate.now().minusDays(2));
		Boolean res = cliente1.checkStillPenalizado();
		assertFalse(res);
		
		assertFalse(cliente1.getPenalizado());
		
		assertEquals(cliente1.getCancelaciones(), 0, 0);
	}
	
	/**
	 * Test
	 */
	@Test
	public void testCheckStillPenalizado2()
	{		
		cliente1.setPenalizado(true);
		cliente1.setPenalizacionHasta(LocalDate.now().plusDays(2));
		Boolean res = cliente1.checkStillPenalizado();
		assertTrue(res);
		
	}
	
	/**
	 * Test
	 */
	@Test
	public void testAddReserva1() throws ExcepcionUsuario, FechaPosterior{
		cliente = gimnasio.registroCliente("Lucia", "luuciia", "juuuuuul", "123456789", LocalDate.of(1999, 03, 24), "4507670001000009","346", "Julia", tarifaPlana);
		Boolean res = cliente.addReserva(reserva4);	
		assertTrue(res);
	}
	/**
	 * Test
	 */
	@Test
	public void testAddReserva2()throws ExcepcionUsuario, FechaPosterior {
		cliente = gimnasio.registroCliente("Lorena", "lllll", "juuuuuul", "123456789", LocalDate.of(1999, 03, 24), "4507670001000009","346", "Julia", tarifaPlana);
		Boolean res = cliente.addReserva(reserva4);	
		assertTrue(res);
		Boolean resesion1 = cliente.addReserva(reserva4);	
		assertFalse(resesion1);
	}
	/**
	 * Test
	 */
	@Test
	public void testAddNotificaciones1() throws ExcepcionUsuario, FechaPosterior{
		//notificacion es null
		cliente = gimnasio.registroCliente("bruno", "luuciia", "juuuuuul", "123456789", LocalDate.of(1999, 03, 24), "4507670001000009","346", "Julia", tarifaPlana);
		Boolean res = cliente.addNotificaciones(null);
		assertFalse(res);
	}
	
	@Test
	public void testAddNotificaciones2() throws ExcepcionUsuario, FechaPosterior{
		//lista contiene la notificacion
		cliente = gimnasio.registroCliente("Carlos", "luuciia", "juuuuuul", "123456789", LocalDate.of(1999, 03, 24), "4507670001000009","346", "Julia", tarifaPlana);
		
		Notificacion notificacion = new Notificacion ("HOLA");
		cliente.addNotificaciones(notificacion);
		Boolean res = cliente.addNotificaciones(notificacion);
		assertFalse(res);	
	}
	
	@Test
	public void testAddNotificacion3() throws ExcepcionUsuario, FechaPosterior{
		cliente = gimnasio.registroCliente("Pablo", "luuciia", "juuuuuul", "123456789", LocalDate.of(1999, 03, 24), "4507670001000009","346", "Julia", tarifaPlana);
		
		Boolean res = cliente.addNotificaciones(new Notificacion("HOLA"));
		assertTrue(res);
	}
	
	@Test
	public void testListaContieneNotifiacion1() throws ExcepcionUsuario, FechaPosterior{
		cliente = gimnasio.registroCliente("Luciana", "luuciia", "juuuuuul", "123456789", LocalDate.of(1999, 03, 24), "4507670001000009","346", "Julia", tarifaPlana);
		
		Boolean res = cliente.listaContieneNotificaion(null);
		assertFalse(res);
	}
	
	@Test
	public void testListaContieneNotifiacion2() throws ExcepcionUsuario, FechaPosterior{
		cliente = gimnasio.registroCliente("Alba", "luuciia", "juuuuuul", "123456789", LocalDate.of(1999, 03, 24), "4507670001000009","346", "Julia", tarifaPlana);
		
		Notificacion notificacion = new Notificacion ("HOLA");
		cliente.addNotificaciones(notificacion);
		Boolean res = cliente.listaContieneNotificaion(notificacion);
		assertTrue(res);
	}
	
	
	@Test
	public void testRealizarReserva1() throws FechaPosterior, ExcepcionUsuario, SalaLlena, ExcepcionEdad, ExcepcionVeterania, ExcepcionCancelaciones, ExcepcionPago, ApuntarseAntesDe48h, ApuntadoMismaHora
	{
		cliente = gimnasio.registroCliente("Oscar", "luuciia", "juuuuuul", "123456789", LocalDate.of(1999, 03, 24), "4507670001000009","346", "Julia", tarifaPlana);
		Boolean res = cliente.realizarReserva(null);
		assertFalse(res);
	}
	
	@Test
	public void testRealizarReserva2()throws FechaPosterior, ExcepcionUsuario, SalaLlena, ExcepcionEdad, ExcepcionVeterania, ExcepcionCancelaciones, ExcepcionPago, ApuntarseAntesDe48h, FechaUnDia, ApuntadoMismaHora
	{
		//cliente penalizado
		cliente = gimnasio.registroCliente("Borja", "luuciia", "juuuuuul", "123456789", LocalDate.of(1999, 03, 24), "4507670001000009","346", "Julia", tarifaPlana);
		
		cliente.realizarReserva(sesion1);
		cliente.realizarReserva(sesion2);
		cliente.realizarReserva(sesion3);
		
		reserva1 = new Reserva(cliente, sesion1, true);
		reserva2 = new Reserva(cliente, sesion2, true);
		reserva3 = new Reserva(cliente, sesion3, true);
		cliente.cancelarReserva(reserva1);
		cliente.cancelarReserva(reserva2);
		cliente.cancelarReserva(reserva3);
		
		
		Boolean res = cliente.realizarReserva(sesion4);
		assertFalse(res);
	}
	
	@Test(expected=SalaLlena.class)
	public void testRealizarReserva4() throws FechaPosterior, ExcepcionUsuario, SalaLlena, ExcepcionEdad, ExcepcionVeterania, ExcepcionCancelaciones, ExcepcionPago, ApuntarseAntesDe48h, ApuntadoMismaHora
	{
		//supera el aforo de la sala
		cliente = gimnasio.registroCliente("Laura", "luuciia", "juuuuuul", "123456789", LocalDate.of(2003, 03, 24), "4507670001000009","346", "Julia", tarifaPlana);
		cliente2 = gimnasio.registroCliente("Ruben", "luuciia", "juuuuuul", "123456789", LocalDate.of(2003, 03, 24), "4507670001000009","346", "Julia", tarifaPlana);
		cliente3 = gimnasio.registroCliente("Jose", "luuciia", "juuuuuul", "123456789", LocalDate.of(2003, 03, 24), "4507670001000009","346", "Julia", tarifaPlana);
		cliente4 = gimnasio.registroCliente("Nacho", "nachooooo", "juuuuuul", "123456789", LocalDate.of(2003, 03, 24), "4507670001000009","346", "Julia", tarifaPlana);
		SesionAG sesion4 = new SesionAG (LocalDate.now().plusDays(1), LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1).plusHours(1), actividad1, salaSimple1, 40.0);
		tarifaPlana.setTipoActividad(tipoActividad);
		assertTrue(cliente.realizarReserva(sesion4));
		assertTrue(cliente2.realizarReserva(sesion4));
		assertTrue(cliente3.realizarReserva(sesion4));
		
		assertTrue(cliente4.realizarReserva(sesion4));
		
	}
	
	@Test
	public void testRealizarReserva5() throws FechaPosterior, ExcepcionUsuario, SalaLlena, ExcepcionEdad, ExcepcionVeterania, ExcepcionCancelaciones, ExcepcionPago, ApuntarseAntesDe48h, ApuntadoMismaHora
	{
		//comprueba que no cumple los requisitos
		cliente5 = gimnasio.registroCliente("Daniel", "luuciia", "juuuuuul", "123456789", LocalDate.of(1999, 03, 24), "4507670001000009","346", "Julia", tarifaPlana);
		assertFalse(cliente5.realizarReserva(sesion1));
	}
	
	@Test
	public void testRealizarReserva7() throws FechaPosterior, ExcepcionUsuario, SalaLlena, ExcepcionEdad, ExcepcionVeterania, ExcepcionCancelaciones, ExcepcionPago, ApuntarseAntesDe48h, ApuntadoMismaHora
	{
		cliente = gimnasio.registroCliente("Esther", "luuciia", "juuuuuul", "123456789", LocalDate.of(1999, 03, 24), "4507670001000009","346", "Julia", tarifaPlana);
		Boolean res = cliente.realizarReserva(null);
		assertFalse(res);
	}
	
	@Test
	public void testRealizarPago1()throws FechaPosterior, ExcepcionUsuario, InvalidCardNumberException, OrderRejectedException, FailedInternetConnectionException {
		Double precio;
		precio = sesion1.getPrecio();
		cliente = gimnasio.registroCliente("Celia", "luuciia", "juuuuuul", "123456789", LocalDate.of(1999, 03, 24), "4507670001000009","346", "Julia", tarifaPlana);
		Boolean res = cliente.realizarPago(precio);
		assertTrue(res);
	}
	
	@Test
	public void testRealizarPago4()throws FechaPosterior, ExcepcionUsuario, InvalidCardNumberException, OrderRejectedException, FailedInternetConnectionException {
		Double precio;
		precio = sesion1.getPrecio();
		cliente = gimnasio.registroCliente("Ana", "annaaa", "juuuuuul", "123456789", LocalDate.of(2003, 03, 24), "333","346", "Julia", tarifaPlana);
		Boolean res = cliente5.realizarPago(precio);
		assertFalse(res);
	}
	
	@Test(expected=ExcepcionPago.class)
	public void testApuntarListaEspera1()throws ExcepcionPago, InvalidCardNumberException, OrderRejectedException, FailedInternetConnectionException, ExcepcionEdad, ExcepcionVeterania, ExcepcionCancelaciones, FechaPosterior, ExcepcionUsuario, SalaLlena, ExcepcionPago, ApuntarseAntesDe48h, ApuntadoMismaHora {
		//no ha pagado
		cliente1.setHaPagado(false);
		cliente1.realizarReserva(sesion1);
		Boolean res = cliente2.apuntarListaEspera(sesion1);
		assertTrue(res);
	}
	
	@Test
	public void testApuntarListaEspera2() throws InvalidCardNumberException, OrderRejectedException, FailedInternetConnectionException, ExcepcionEdad, ExcepcionVeterania, ExcepcionCancelaciones, FechaPosterior, ExcepcionUsuario, SalaLlena, ExcepcionPago, ApuntarseAntesDe48h {
		//si sesion es null
		cliente1.setHaPagado(true);
		Boolean res = cliente1.apuntarListaEspera(null);
		assertFalse(res);
	}
	
	@Test
	public void testApuntarListaEspera3() throws InvalidCardNumberException, OrderRejectedException, FailedInternetConnectionException, ExcepcionEdad, ExcepcionVeterania, ExcepcionCancelaciones, FechaPosterior, ExcepcionUsuario, SalaLlena, ExcepcionPago, ApuntarseAntesDe48h {
		//si aforo no esta lleno
		cliente1.setHaPagado(true);
		sesion1.getSala().setAforo(2);
		Boolean res = cliente1.apuntarListaEspera(sesion1);
		assertFalse(res);
	}
	
	@Test
	public void testApuntarListaEspera4() throws InvalidCardNumberException, OrderRejectedException, FailedInternetConnectionException, ExcepcionEdad, ExcepcionVeterania, ExcepcionCancelaciones, FechaPosterior, ExcepcionUsuario, SalaLlena, ExcepcionPago, ApuntarseAntesDe48h, ApuntadoMismaHora {
		//si la sesion = reserva sesion
		cliente1.setHaPagado(true);
		sesion1.getSala().setAforo(1);
		cliente1.realizarReserva(sesion1);
		Boolean res = cliente1.apuntarListaEspera(sesion1);
		assertFalse(res);
	}
	
	@Test
	public void testApuntarListaEspera5() throws InvalidCardNumberException, OrderRejectedException, FailedInternetConnectionException, ExcepcionEdad, ExcepcionVeterania, ExcepcionCancelaciones, FechaPosterior, ExcepcionUsuario, SalaLlena, ExcepcionPago, ApuntarseAntesDe48h, ApuntadoMismaHora {
		//si lista de espera contiene al cliente
		cliente1.setHaPagado(true);
		sesion1.getSala().setAforo(1);
		cliente1.realizarReserva(sesion1);
		cliente2.setHaPagado(true);
		cliente2.apuntarListaEspera(sesion1);
		Boolean res = cliente2.apuntarListaEspera(sesion1);
		assertFalse(res);
	}
	
	@Test(expected=ExcepcionPago.class)
	public void testContratarPlanPersonalizado1() throws SalaLlena, ExcepcionPago, ExcepcionEdad, ExcepcionVeterania, ExcepcionCancelaciones, FechaPosterior, ExcepcionUsuario, ApuntarseAntesDe48h, ApuntadoMismaHora{
		//no ha pagado
		Boolean res = cliente1.contratarPlanPersonalizado(planPersonalizado);
	}
	
	@Test
	public void testContratarPlanPersonalizado2() throws SalaLlena, ExcepcionPago, ExcepcionEdad, ExcepcionVeterania, ExcepcionCancelaciones, FechaPosterior, ExcepcionUsuario, ApuntarseAntesDe48h, ApuntadoMismaHora{
		//actividad de la sesion de la reserva es igual al plan personalizado
		cliente1.setHaPagado(true);
		salaSimple.setAforo(1);
		SesionAG sesion4 = new SesionAG (LocalDate.now().plusDays(1), LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1).plusHours(1), actividad1, salaSimple1, 40.0);
		SesionPP sesion5 = new SesionPP (LocalDate.now().plusDays(1), LocalDateTime.now().plusDays(1).plusHours(1), LocalDateTime.now().plusDays(1).plusHours(2), actividad1, salaSimple1, 40.0);
		planPersonalizado.addSesionMonitorizada(sesion4);
		planPersonalizado.addSesionMonitorizada(sesion5);
		assertTrue(cliente1.contratarPlanPersonalizado(planPersonalizado));
		Boolean res = cliente1.contratarPlanPersonalizado(planPersonalizado);
		assertFalse(res);
	}
	
	@Test
	public void testContratarPlanPersonalizado3() throws SalaLlena, ExcepcionPago, ExcepcionEdad, ExcepcionVeterania, ExcepcionCancelaciones, FechaPosterior, ExcepcionUsuario, ApuntarseAntesDe48h, ApuntadoMismaHora{
		//si nocumple los requisitos
		cliente1.setHaPagado(true);
		cliente1.setFechaNacimiento(LocalDate.now());
		Boolean res = cliente1.contratarPlanPersonalizado(planPersonalizado);
		assertFalse(res);
	}
	
	@Test(expected = SalaLlena.class)
	public void testContratarPlanPersonalizado4() throws SalaLlena, ExcepcionPago, ExcepcionEdad, ExcepcionVeterania, ExcepcionCancelaciones, FechaPosterior, ExcepcionUsuario, ApuntarseAntesDe48h, ApuntadoMismaHora{
		//aforo esta lleno
		SesionPP sesion5 = new SesionPP (LocalDate.now().plusDays(1), LocalDateTime.now().plusDays(1).plusHours(1), LocalDateTime.now().plusDays(1).plusHours(2), actividad1, salaSimple1, 40.0);
		planPersonalizado.addSesionMonitorizada(sesion5);
		salaSimple1.setAforo(1);
		cliente1.setHaPagado(true);
		cliente1.contratarPlanPersonalizado(planPersonalizado);
		cliente2.setHaPagado(true);
		Boolean res = cliente2.contratarPlanPersonalizado(planPersonalizado);
		assertTrue(res);
	}

	@Test
	public void testContratarPlanPersonalizado6() throws SalaLlena, ExcepcionPago, ExcepcionEdad, ExcepcionVeterania, ExcepcionCancelaciones, FechaPosterior, ExcepcionUsuario, ApuntarseAntesDe48h, ApuntadoMismaHora{
		cliente1.setHaPagado(true);
		Boolean res = cliente1.contratarPlanPersonalizado(planPersonalizado);
		assertTrue(res);
	}
	
	@Test
	public void testComprobarTarjeta1()
	{
		Boolean res = cliente1.comprobarTarjeta(cliente1.getTarjeta().getCardNumberString());
		assertTrue(res);
	}
	
	@Test
	public void testComprobarTarjeta2()
	{
		Boolean res = cliente1.comprobarTarjeta("33");
		assertFalse(res);
	}
	
	@Test
	public void testGetReservaByData1() throws ExcepcionUsuario, FechaPosterior, ExcepcionEdad, ExcepcionVeterania, ExcepcionCancelaciones, SalaLlena, ExcepcionPago, ApuntarseAntesDe48h, ApuntadoMismaHora
	{
		cliente1.setHaPagado(true);
		cliente1.realizarReserva(sesion1);
		for(Reserva res: cliente1.getReservas()){
			assertEquals(res, cliente.getReservaByData("Zumba", null, LocalDateTime.now(), LocalDateTime.now().plusHours(1), LocalDate.of(2023,3,28), salaSimple, 6));
		}
		
	}
	
	@Test
	public void testGetReservaByData2() throws ExcepcionEdad, ExcepcionVeterania, ExcepcionCancelaciones, FechaPosterior, ExcepcionUsuario, SalaLlena, ExcepcionPago, ApuntarseAntesDe48h, ApuntadoMismaHora
	{
		cliente1.setHaPagado(true);
		cliente1.realizarReserva(sesion1);
		for(Reserva res: cliente1.getReservas()){
			assertNotEquals(res, cliente.getReservaByData("Sumbita", null, LocalDateTime.now(), LocalDateTime.now().plusHours(1), LocalDate.of(2023,3,28), salaSimple, 6));
		}
	}
	
	@Test
	public void testRemoveNotificacion1()
	{
		Notificacion notificacion = new Notificacion("Hola");
		cliente1.addNotificaciones(notificacion);
		assertEquals(cliente1.getNotificaciones().size(), 1, 0.0);
		cliente1.removeNotificacion("Hola");
		assertEquals(cliente1.getNotificaciones().size(), 0, 0.0);
	}
}
