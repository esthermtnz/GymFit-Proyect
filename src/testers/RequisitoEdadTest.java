package testers;

import org.junit.*;

import aplicacion.Gimnasio;
import aplicacion.usuario.Cliente;
import aplicacion.usuario.tarifa.TarifaUso;
import aplicacion.usuario.tarifa.TarjetaCredito;
import aplicacion.actividad.sesion.requisito.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

public class RequisitoEdadTest {

	private RequisitoEdad r;
	private Cliente c;
	private Cliente c2;
	private TarjetaCredito tc1,tc2;
	private Gimnasio g;
	private TarifaUso tu;
	
	
	@Before
	public void setUp() throws Exception{
		r = new RequisitoEdad(18, 36);
		g = Gimnasio.getGimnasio();
		tu = new TarifaUso(88.99);
		c = new Cliente ("Carla", "657843210", LocalDate.of(2000, 6, 5), "luissito", "kslkncls", tc2);
		c.setFechaRegistro(LocalDate.now().minusDays(1));
		c2 = new Cliente ("Luis", "687254169", LocalDate.of(1905, 6, 5), "luissito", "kslkncls", tc2);
		c2.setFechaRegistro(LocalDate.now().minusDays(2));
		tc1 = new TarjetaCredito("4507670001000009","346", "Carla");
		tc2 = new TarjetaCredito("5020473565370055","957", "Luis");
		
	}
	@Test
	public void testSetMin1() {
		Boolean res = r.setMax(2);
		assertTrue(res);
		
		assertEquals(r.getMax(), 2, 0);
	}
	
	@Test
	public void testSetMin2() {
		Boolean res = r.setMin(-1);
		assertFalse(res);
	}
	
	@Test
	public void testSetMax1() {
		Boolean res = r.setMax(25);
		assertTrue(res);
		
		assertEquals(r.getMax(), 25, 0);
	}
	@Test
	public void testSetMax2() {
		Boolean res = r.setMax(-1);
		assertFalse(res);
	}
	@Test
	public void testComprobar1() {
		//Para el cliente null
		Boolean res = r.comprobar(null);
		assertFalse(res);
	}
	
	@Test
	public void testComprobar2() {
		//Para un cliente cualquiera que cumpla los requisitos
		
		Boolean res = r.comprobar(c);
		assertTrue(res);
	}
	
	@Test
	public void testComprobar3() {
		//Para un cliente que no cumpla el requisito
		Boolean res = r.comprobar(c2);
		assertFalse(res);
	}

}
