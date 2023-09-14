package testers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import aplicacion.Gimnasio;
import aplicacion.actividad.sesion.requisito.RequisitoCancelaciones;
import aplicacion.actividad.sesion.requisito.RequisitoEdad;
import aplicacion.usuario.Cliente;
import aplicacion.usuario.tarifa.TarifaUso;
import aplicacion.usuario.tarifa.TarjetaCredito;

public class RequisitoCancelacionesTest {

	private RequisitoCancelaciones requisito;
	private Cliente cliente1;
	private Cliente cliente2;
	private TarjetaCredito tarjetaCredito1,tarjetaCredito2;
	private Gimnasio gimnasio;
	private TarifaUso tarifaUso;
	
	
	@Before
	public void setUp() throws Exception{
		requisito = new RequisitoCancelaciones(0, 3);
		gimnasio = Gimnasio.getGimnasio();
		tarifaUso = new TarifaUso(88.99);
		cliente1 = new Cliente ("Carla", "657843210", LocalDate.of(2000, 6, 5), "luissito", "kslkncls", tarjetaCredito2);
		cliente1.setFechaRegistro(LocalDate.now().minusDays(1));
		cliente2 = new Cliente ("Luis", "687254169", LocalDate.of(1905, 6, 5), "luissito", "kslkncls", tarjetaCredito2);
		cliente2.setFechaRegistro(LocalDate.now().minusDays(2));
		cliente2.setCancelaciones(5);
		tarjetaCredito1 = new TarjetaCredito("4507670001000009","346", "Carla");
		tarjetaCredito2 = new TarjetaCredito("5020473565370055","957", "Luis");
		
	}
	@Test
	public void testSetMin1() {
		Boolean res = requisito.setMax(2);
		assertTrue(res);
		
		assertEquals(requisito.getMax(), 2, 0);
	}
	
	@Test
	public void testSetMin2() {
		Boolean res = requisito.setMin(-1);
		assertFalse(res);
	}
	
	@Test
	public void testSetMax1() {
		Boolean res = requisito.setMax(25);
		assertTrue(res);
		
		assertEquals(requisito.getMax(), 25, 0);
	}
	@Test
	public void testSetMax2() {
		Boolean res = requisito.setMax(-1);
		assertFalse(res);
	}
	@Test
	public void testComprobar1() {
		//Para el cliente null
		Boolean res = requisito.comprobar(null);
		assertFalse(res);
	}
	
	@Test
	public void testComprobar2() {
		//Para un cliente cualquiera que cumpla los requisitos
		Boolean res = requisito.comprobar(cliente1);
		assertTrue(res);
	}
	
	@Test
	public void testComprobar3() {
		//Para un cliente que no cumpla el requisito
		
		Boolean res = requisito.comprobar(cliente2);
		assertFalse(res);
	}

}
