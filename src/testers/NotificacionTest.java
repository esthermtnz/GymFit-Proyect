package testers;

import org.junit.Test;

import aplicacion.usuario.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


import org.junit.Before;	


public class NotificacionTest {
	
	private Notificacion notificacion;
	
	@Before
	public void setUp() throws Exception{
		notificacion = new Notificacion("Se ha cancelado una reserva");
	}

	@Test
	public void testSetTexto1() {
		
		Boolean resultado = notificacion.setTexto("Se ha cancelado una reserva");
		assertTrue(resultado);
		assertEquals(notificacion.getTexto(), "Se ha cancelado una reserva");
	}
	
	@Test
	public void testSetTexto2() {
		Boolean resultado = notificacion.setTexto(null);
		assertFalse(resultado);
	}
	
	@Test
	public void testAddNotificaciones() {
		
		Boolean resultado = notificacion.setTexto("Se ha cancelado una reserva");
		assertTrue(resultado);
		
		assertEquals(notificacion.getTexto(), "Se ha cancelado una reserva");
		
	}
	
}
