package testers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import aplicacion.sala.HorarioClimatizacion;

public class HorarioClimatizacionTest {
	
	HorarioClimatizacion horarioClimatizacion;
	
	@Before
	public void setUp() throws Exception{
		horarioClimatizacion = new HorarioClimatizacion(LocalDateTime.now(),  LocalDateTime.now().plusHours(12));
	}

	@Test
	public void testSetHoraIni1() {
		Boolean res = horarioClimatizacion.setHoraIni(LocalDateTime.now().minusHours(5));
		assertFalse(res);
	}
	
	@Test
	public void testSetHoraIni2() {
		Boolean res = horarioClimatizacion.setHoraIni(LocalDateTime.now().plusHours(5));
		assertTrue(res);
		assertEquals(horarioClimatizacion.getHoraIni(), LocalDateTime.now().plusHours(5));
	}

	@Test
	public void testSetHoraFin1() {
		Boolean res = horarioClimatizacion.setHoraFin(LocalDateTime.now().minusHours(5));
		assertFalse(res);
	}
	
	@Test
	public void testSetHoraFin2() {
		Boolean res = horarioClimatizacion.setHoraFin(LocalDateTime.now().plusHours(5));
		assertTrue(res);
	}
}
