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
import aplicacion.actividad.sesion.SesionLibre;
import aplicacion.actividad.sesion.requisito.*;
import aplicacion.sala.SalaSimple;
import aplicacion.usuario.Cliente;
import aplicacion.usuario.tarifa.TarjetaCredito;

public class SesionLibreTest {
	
	private SesionLibre sesionLibre;
	private SalaSimple salaSimple;
	private Reserva reserva;
	private Cliente cliente;
	private TarjetaCredito tarjetaCredito;
	
	@Before
	public void setUp() throws Exception{
		salaSimple = new SalaSimple("Pesas", "Para realizar levantamiento de pesas", 15);
		sesionLibre = new SesionLibre(LocalDate.now(), LocalDateTime.now(), LocalDateTime.now().plusHours(1), salaSimple, 20.0);
		cliente = new Cliente("Paula", "678965440", LocalDate.of(1991, 07, 02), "paula154a", "gympau", tarjetaCredito);
		cliente.setFechaRegistro(LocalDate.now());
		tarjetaCredito = new TarjetaCredito("4507670001000009","346", "Julia");
		reserva = new Reserva(cliente, sesionLibre, true);
		
	}

	@Test
	public void testSetFecha1() {
		Boolean res = sesionLibre.setFecha(LocalDate.now().plusDays(4));
		assertTrue(res);
	}
	
	@Test
	public void testSetFecha2() {
		Boolean res = sesionLibre.setFecha(LocalDate.now().minusDays(31));
		assertFalse(res);
	}

	@Test
	public void testSetHoraIni1() {
		Boolean res = sesionLibre.setHoraInicio(LocalDateTime.now().plusMinutes(5));
		assertTrue(res);
	}
	
	@Test
	public void testSetHoraIni2() {
		Boolean res = sesionLibre.setHoraInicio(LocalDateTime.now().minusDays(2));
		assertFalse(res);
	}
	
	@Test
	public void testSetHoraFin1() {
		Boolean res = sesionLibre.setHoraFin(LocalDateTime.now().plusHours(3));
		assertTrue(res);
	}
	
	@Test
	public void testSetHoraFin2() {
		Boolean res = sesionLibre.setHoraFin(LocalDateTime.now().minusHours(34));
		assertFalse(res);
	}
	
	@Test
	public void testComprobarRequisitoEdad1(){
		
		Boolean res = sesionLibre.comprobarRequisitoEdad(null, cliente);
		assertFalse(res);
	}
	
	@Test
	public void testComprobarRequisitoEdad2(){
		RequisitoEdad requisitoEdad = new RequisitoEdad(18, 20);
		Boolean res = sesionLibre.comprobarRequisitoEdad(requisitoEdad, null);
		assertFalse(res);
	}
	
	@Test
	public void testComprobarRequisitoEdad3(){
		RequisitoEdad requisitoEdad = new RequisitoEdad(18, 50);
		Boolean res = sesionLibre.comprobarRequisitoEdad(requisitoEdad, cliente);
		assertTrue(res);
	}
	
	@Test
	public void testComprobarRequisitoVeterania1(){
		Boolean res = sesionLibre.comprobarRequisitoVeterania(null, cliente);
		assertFalse(res);
	}
	
	@Test
	public void testComprobarRequisitoVeterania2(){
		RequisitoVeterania requisitoVeterania = new RequisitoVeterania(18, 20);
		Boolean res = sesionLibre.comprobarRequisitoVeterania(requisitoVeterania, null);
		assertFalse(res);
	}
	
	@Test
	public void testComprobarRequisitoVeterania3(){
		RequisitoVeterania requisitoVeterania = new RequisitoVeterania(0, 2);
		Boolean res = sesionLibre.comprobarRequisitoVeterania(requisitoVeterania, cliente);
		assertTrue(res);
	}
	
	@Test
	public void testComprobarRequisitoCancelaciones1(){
		Boolean res = sesionLibre.comprobarRequisitoCancelaciones(null, cliente);
		assertFalse(res);
	}
	
	@Test
	public void testComprobarRequisitoCancelaciones2(){
		RequisitoCancelaciones requisitoCancelaciones = new RequisitoCancelaciones(18, 20);
		Boolean res = sesionLibre.comprobarRequisitoCancelaciones(requisitoCancelaciones, null);
		assertFalse(res);
	}
	
	@Test
	public void testComprobarRequisitoCancelaciones3(){
		RequisitoCancelaciones requisitoCancelaciones = new RequisitoCancelaciones(0, 1);
		Boolean res = sesionLibre.comprobarRequisitoCancelaciones(requisitoCancelaciones, cliente);
		assertTrue(res);
	}
	
	@Test
	public void testAddReserva1()
	{
		Reserva reserva = new Reserva(cliente, sesionLibre, true);
		Boolean res = sesionLibre.addReserva(reserva);
		assertTrue(res);
	}
	
	@Test
	public void testAddReserva2()
	{
		Reserva reserva = new Reserva(cliente, sesionLibre, true);
		sesionLibre.addReserva(reserva);
		Boolean res = sesionLibre.addReserva(reserva);
		assertFalse(res);
	}
	
	@Test
	public void testAddReserva3()
	{
		Boolean res = sesionLibre.addReserva(null);
		assertFalse(res);
	}
	
	@Test
	public void testRemoveReserva1() {
		Boolean res = sesionLibre.removeReserva(null);
		assertFalse(res);
	}
	
	@Test
	public void testRemoveReserva2() {
		Reserva reserva = new Reserva(cliente, sesionLibre, true);
		sesionLibre.addReserva(reserva);
		Boolean res = sesionLibre.removeReserva(reserva);
		assertTrue(res);
	}
	
	@Test
	public void testRemoveReserva3() {
		Reserva reserva = new Reserva(cliente, sesionLibre, true);
		sesionLibre.addReserva(reserva);
		sesionLibre.removeReserva(reserva);
		Boolean res = sesionLibre.removeReserva(reserva);
		assertFalse(res);
	}
	
	@Test
	public void testCalcularHorasSesion1()
	{
		Integer horas = sesionLibre.calcularHorasSesion();
		assertEquals(1, horas, 0.0);
	}
	
	@Test
	public void testCalcularHorasSesion2()
	{
		SesionLibre sesionLibre = new SesionLibre(LocalDate.now(), LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 0)), LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(1, 0)), salaSimple, 20.0);
		Integer horas = sesionLibre.calcularHorasSesion();
		assertEquals(2,horas, 0.0);
	}
}
