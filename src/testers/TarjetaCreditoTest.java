package testers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import aplicacion.usuario.tarifa.TarjetaCredito;


public class TarjetaCreditoTest {
	
	private TarjetaCredito tarjetaCredito;
	
	@Before
	public void setUp() throws Exception{
		tarjetaCredito = new TarjetaCredito("5540500001000004", "989", "Carlos");
	}

	@Test
	public void testSetNumeroCredito1() {
		Boolean res = tarjetaCredito.setNumeroCredito(null);
		assertFalse(res);
		
	}

	@Test
	public void testSetNumeroCredito2() {
		Boolean res = tarjetaCredito.setNumeroCredito("5020470001370055");
		assertTrue(res);
		
		assertEquals(tarjetaCredito.getCardNumberString(), "5020470001370055");
	}

	@Test
	public void testSetPin1()
	{
		Boolean res = tarjetaCredito.setPin(null);
		assertFalse(res);
	}
	
	
	@Test
	public void testSetPin2()
	{
		Boolean res = tarjetaCredito.setPin("184");
		assertTrue(res);
		
	}


	@Test
	public void testSetTitular1() {
		Boolean res = tarjetaCredito.setTitular(null);
		assertFalse(res);
	}
	
	@Test
	public void testSetTitular2() {
		Boolean res = tarjetaCredito.setTitular("Fernando");
		assertTrue(res);
		
		assertEquals(tarjetaCredito.getTitular(), "Fernando");
	}

}
