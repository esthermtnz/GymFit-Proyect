package testers;

import org.junit.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import aplicacion.actividad.sesion.ListaEspera;
import aplicacion.usuario.Cliente;
import aplicacion.usuario.tarifa.TarjetaCredito;
/**
 * TEST
 *
 */
public class ListaEsperaTest {

	private ListaEspera lista;
	private TarjetaCredito tarjetaCredito1, tarjetaCredito2, tarjetaCredito3, tarjetaCredito4, tarjetaCredito5;
	private Cliente cliente1, cliente2, cliente3, cliente4, cliente5;
	/*
	 * Test
	 */
	@Before
	public void setUp() throws Exception{
		tarjetaCredito1 = new TarjetaCredito("4507670001000009","346", "Paula");
		tarjetaCredito2 = new TarjetaCredito("5020470001370055","997", "Carla");
		tarjetaCredito3 = new TarjetaCredito("5020473565370055","957", "Marta");
		tarjetaCredito4 = new TarjetaCredito("5020473565370055","957", "Sofia");
		tarjetaCredito5 = new TarjetaCredito("1122473565370055","123", "Gonzalo");
		
		cliente1 = new Cliente("Paula", "678965440", LocalDate.of(1991, 07, 02), "paula154a", "gympau", tarjetaCredito1);
		cliente2 = new Cliente("Carla", "606797865", LocalDate.of(2000, 05, 06), "carlal54a", "gymcarla", tarjetaCredito2);
		cliente3 = new Cliente("Marta", "667893465", LocalDate.of(2004, 01, 02), "martal54a", "gymmarta", tarjetaCredito3);
		cliente4 = new Cliente("Sofia", "606797865", LocalDate.of(2000, 05, 06), "sofia54a", "gymsofia", tarjetaCredito4);
		cliente5 = new Cliente("Gonzalo", "665867341", LocalDate.of(2002, 03, 21), "gonzalol54a", "gymgonzalo",tarjetaCredito5);
		
		lista = new ListaEspera();
	}
	/*
	 * Test
	 */
	@Test
	public void testApuntar1() {
		//comprobar que no esta en la lista y lo añade
		Boolean res = lista.listaContieneCliente(cliente1);
		assertFalse(res);
		
		Boolean resultado = lista.apuntar(cliente1);
		assertTrue(resultado);
		
	}
	/*
	 * Test
	 */
	@Test
	public void testApuntar2() {
		//lo añade y comprueba que si esta en la lista
		
		Boolean resultado = lista.apuntar(cliente2);
		assertTrue(resultado);
		
		Boolean res = lista.listaContieneCliente(cliente2);
		assertTrue(res);	
		
	}
	/*
	 * Test
	 */
	@Test
	public void testApuntar3() {
		//añade un cliente que ya esta en la lista
		Boolean resultado = lista.apuntar(cliente3);
		assertTrue(resultado);
		
		Boolean res = lista.listaContieneCliente(cliente3);
		assertTrue(res);	
		
		Boolean resultado1 = lista.apuntar(cliente3);
		assertFalse(resultado1);
	}
	
	/*
	 * Test
	 */
	@Test
	public void testNotificar1() {
		//enviar una notificacion a un cliente que esta en la lista
		Boolean res = lista.listaContieneCliente(cliente4);
		assertFalse(res);
		
		Boolean resultado = lista.apuntar(cliente4);
		assertTrue(resultado);
		
		Boolean resultado1 = lista.listaContieneCliente(cliente4);
		assertTrue(resultado1);
		
		Boolean resultado2 = lista.notificar("Estas en la lista de espera");
		assertTrue(resultado2);
	}
	
	/*
	 * Test
	 */
	@Test
	public void testNotificar2() {
		//enviar una notificacion a un cliente que no esta en la lista
		
		Boolean res = lista.listaContieneCliente(cliente5);
		assertFalse(res);
		
		Boolean resultado2 = lista.notificar("Estas en la lista de espera");
		assertFalse(resultado2);
	}
	
	/*
	 * Test
	 */
	@Test
	public void testlistaContieneCliente1()
	{
		//comprueba que si le pasa un argumento null da false
		Boolean res = lista.listaContieneCliente(null);
		assertFalse(res);
	}
	/*
	 * Test
	 */
	@Test
	public void testlistaContieneCliente2()
	{		
		lista.apuntar(cliente5);
		Boolean res = lista.listaContieneCliente(cliente5);
		assertTrue(res);
	}
}
