package testers;
import aplicacion.usuario.Administrador;
import aplicacion.usuario.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


import org.junit.Before;
import org.junit.Test;

public class AdministradorTest {
	
	private Administrador administrador;
	
	@Before
	public void setUp() throws Exception{
		administrador = new Administrador("admin_23", "gymfit23");
	}


	@Test
	public void testSetUsuario1() {
		Boolean res = administrador.setUsuario("clau123");
		assertTrue(res);
		
		assertEquals(administrador.getUsuario(), "clau123");
	}
	
	@Test
	public void testSetUsuario2() {
		Boolean res = administrador.setUsuario(null);
		assertFalse(res);
	}

	@Test
	public void testSetContrasenya1() {
		Boolean res = administrador.setContrasenya("caracola");
		assertTrue(res);
		
		assertEquals(administrador.getContrasenya(), "caracola");
	}

	@Test
	public void testSetContrasenya2() {
		Boolean res = administrador.setContrasenya(null);
		assertFalse(res);
	}
	
	
	@Test
	public void testAddNotificaciones1(){
		//notificacion es null
		Boolean res = administrador.addNotificaciones(null);
		assertFalse(res);
	}
	
	@Test
	public void testAddNotificaciones2(){
		//lista contiene la notificacion
		Notificacion notificacion = new Notificacion ("HOLA");
		administrador.addNotificaciones(notificacion);
		Boolean res = administrador.addNotificaciones(notificacion);
		assertFalse(res);	
	}
	
	@Test
	public void testAddNotificacion3(){
		Boolean res = administrador.addNotificaciones(new Notificacion("HOLA"));
		assertTrue(res);
	}
	
	@Test
	public void testListaContieneNotifiacion1(){
		Boolean res = administrador.listaContieneNotificaion(null);
		assertFalse(res);
	}
	
	@Test
	public void testListaContieneNotifiacion2(){
		Notificacion notificacion = new Notificacion ("HOLA");
		administrador.addNotificaciones(notificacion);
		Boolean res = administrador.listaContieneNotificaion(notificacion);
		assertTrue(res);
	}

}
