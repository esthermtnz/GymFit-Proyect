package testers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import aplicacion.Reserva;
import aplicacion.actividad.ActividadGrupal;
import aplicacion.actividad.TipoActividad;
import aplicacion.actividad.sesion.SesionAG;
import aplicacion.sala.SalaSimple;
import aplicacion.usuario.Monitor;
import aplicacion.actividad.sesion.requisito.*;
import aplicacion.usuario.*;
import aplicacion.usuario.tarifa.*;

public class SesionAGTest {
	
	private SesionAG sesion;
	private ActividadGrupal actividad;
	private SalaSimple salaSimple;
	private Monitor monitor;
	private RequisitoCancelaciones requisito;
	private TipoActividad tipoActividad;
	private Cliente cliente;
	private TarjetaCredito tarjetaCredito1;

	@Before
	public void setUp() throws Exception{
		sesion = new SesionAG(LocalDate.now().plusDays(2), LocalDateTime.now(), LocalDateTime.now().plusHours(1), actividad, salaSimple, 40.0);
		salaSimple = new SalaSimple("Pesas", "Para realizar levantamiento de pesas", 15);
		tipoActividad = new TipoActividad("Yoga1");
		monitor = new Monitor("Manu325","00523821", "gymManu", "Manuel", "manuel.guti@gmail.com");
		actividad = new ActividadGrupal("Zumba",monitor, requisito,tipoActividad);
		requisito = new RequisitoCancelaciones (0, 3);
		tarjetaCredito1 = new TarjetaCredito("4507670001000009","346", "Julia"); 
		cliente = new Cliente ("Paula", "698574213", LocalDate.of(1999, 03, 24), "pau24", "caracol9", tarjetaCredito1);
		
	}
	
	@Test
	public void testSetFecha1() {
		Boolean res = sesion.setFecha(LocalDate.now().plusDays(4));
		assertTrue(res);
	}
	
	@Test
	public void testSetFecha2() {
		Boolean res = sesion.setFecha(LocalDate.now().minusDays(31));
		assertFalse(res);
	}
	
	@Test
	public void testSetHoraIni1() {
		Boolean res = sesion.setHoraInicio(LocalDateTime.now().plusMinutes(5));
		assertTrue(res);
	}
	
	@Test
	public void testSetHoraIni2() {
		Boolean res = sesion.setHoraInicio(LocalDateTime.now().minusDays(2));
		assertFalse(res);
	}
	
	@Test
	public void testSetHoraFin1() {
		Boolean res = sesion.setHoraFin(LocalDateTime.now().plusHours(3));
		assertTrue(res);
	}
	
	@Test
	public void testSetHoraFin2() {
		Boolean res = sesion.setHoraFin(LocalDateTime.now().minusHours(34));
		assertFalse(res);
	}
	

	@Test
	public void testsetActividad1(){
		assertFalse(sesion.setActividad(null));
	}

	@Test
	public void testsetActividad2(){
		assertTrue(sesion.setActividad(actividad));
	}
	
	@Test
	public void testAddReserva1()
	{
		SesionAG sesionAG = new SesionAG(LocalDate.now(), LocalDateTime.now(), LocalDateTime.now().plusHours(2), actividad, salaSimple, 20.0);
		Reserva reserva = new Reserva(cliente, sesion, true);
		Boolean res = sesion.addReserva(reserva);
		assertTrue(res);
	}
	
	@Test
	public void testAddReserva2()
	{
		Reserva reserva = new Reserva(cliente, sesion, true);
		sesion.addReserva(reserva);
		Boolean res = sesion.addReserva(reserva);
		assertFalse(res);
	}
	
	@Test
	public void testAddReserva3()
	{
		Boolean res = sesion.addReserva(null);
		assertFalse(res);
	}
	
	@Test
	public void testRemoveReserva1() {
		Boolean res = sesion.removeReserva(null);
		assertFalse(res);
	}
	
	@Test
	public void testRemoveReserva2() {
		Reserva reserva = new Reserva(cliente, sesion, true);
		sesion.addReserva(reserva);
		Boolean res = sesion.removeReserva(reserva);
		assertTrue(res);
	}
	
	@Test
	public void testRemoveReserva3() {
		Reserva reserva = new Reserva(cliente, sesion, true);
		sesion.addReserva(reserva);
		sesion.removeReserva(reserva);
		Boolean res = sesion.removeReserva(reserva);
		assertFalse(res);
	}
	
	@Test
	public void testCalcularHorasSesion1()
	{
		Integer horas = sesion.calcularHorasSesion();
		assertEquals(1, horas, 0.0);
	}
	
	@Test
	public void testCalcularHorasSesion2()
	{
		SesionAG sesionAG = new SesionAG(LocalDate.now(), LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 0)), LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(1, 0)), actividad, salaSimple, 20.0);
		Integer horas = sesionAG.calcularHorasSesion();
		assertEquals(2,horas, 0.0);
	}
	
	
}
