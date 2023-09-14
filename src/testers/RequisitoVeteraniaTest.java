package testers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import aplicacion.Gimnasio;
import aplicacion.actividad.sesion.requisito.RequisitoEdad;
import aplicacion.actividad.sesion.requisito.RequisitoVeterania;
import aplicacion.usuario.Cliente;
import aplicacion.usuario.tarifa.TarifaUso;
import aplicacion.usuario.tarifa.TarjetaCredito;

public class RequisitoVeteraniaTest {

	private RequisitoVeterania requisitoVeterania;
	private Cliente cliente;
	private Cliente cliente2;
	private TarjetaCredito tarjeta1,tarjeta2;
	private Gimnasio gimnasio;
	private TarifaUso tarifaUso;
	
	
	@Before
	public void setUp() throws Exception{
		requisitoVeterania = new RequisitoVeterania(0, 10);
		gimnasio = Gimnasio.getGimnasio();
		tarifaUso = new TarifaUso(88.99);
		cliente = new Cliente ("Carla", "657843210", LocalDate.of(2000, 6, 5), "luissito", "kslkncls", tarjeta2);
		cliente.setFechaRegistro(LocalDate.now().minusDays(1));
		cliente2 = new Cliente ("Luis", "687254169", LocalDate.of(1905, 6, 5), "luissito", "kslkncls", tarjeta2);
		cliente2.setFechaRegistro(LocalDate.now().minusYears(11));
		tarjeta1 = new TarjetaCredito("4507670001000009","346", "Carla");
		tarjeta2 = new TarjetaCredito("5020473565370055","957", "Luis");
		
	}
	@Test
	public void testSetMin1() {
		Boolean res = requisitoVeterania.setMax(2);
		assertTrue(res);
		
		assertEquals(requisitoVeterania.getMax(), 2, 0);
	}
	
	@Test
	public void testSetMin2() {
		Boolean res = requisitoVeterania.setMin(-1);
		assertFalse(res);
	}
	
	@Test
	public void testSetMax1() {
		Boolean res = requisitoVeterania.setMax(25);
		assertTrue(res);
		
		assertEquals(requisitoVeterania.getMax(), 25, 0);
	}
	@Test
	public void testSetMax2() {
		Boolean res = requisitoVeterania.setMax(-1);
		assertFalse(res);
	}
	@Test
	public void testComprobar1() {
		//Para el cliente null
		Boolean res = requisitoVeterania.comprobar(null);
		assertFalse(res);
	}
	
	@Test
	public void testComprobar2() {
		//Para un cliente cualquiera que cumpla los requisitos
		
		Boolean res = requisitoVeterania.comprobar(cliente);
		assertTrue(res);
	}
	
	@Test
	public void testComprobar3() {
		//Para un cliente que no cumpla el requisito
		Boolean res = requisitoVeterania.comprobar(cliente2);
		assertFalse(res);
	}

}
