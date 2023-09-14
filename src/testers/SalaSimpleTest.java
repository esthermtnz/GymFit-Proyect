package testers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import aplicacion.actividad.ActividadGrupal;
import aplicacion.actividad.TipoActividad;
import aplicacion.actividad.sesion.SesionAG;
import aplicacion.actividad.sesion.requisito.RequisitoEdad;
import aplicacion.sala.SalaSimple;
import aplicacion.usuario.Cliente;
import aplicacion.usuario.Monitor;
import aplicacion.usuario.tarifa.TarjetaCredito;

public class SalaSimpleTest {

	private SalaSimple salaSimple;
	private Monitor monitor;
	private RequisitoEdad requisito;
	private ActividadGrupal actividad;
	private SesionAG sesion;
	private Cliente cliente;
	private TarjetaCredito tarjetaCredito1;
	private TipoActividad tipoActividad;
	
	

	@Before
	public void setUp() throws Exception{
		salaSimple = new SalaSimple("Pesas", "Para realizar levantamiento de pesas", 15);
		monitor = new Monitor("Manu325", "00523821","gymManu", "Manuel", "manuel.guti@gmail.com");
		requisito = new RequisitoEdad (2, 15);
		tipoActividad = new TipoActividad("Yoga1");
		actividad = new ActividadGrupal("Zumba",monitor, requisito, tipoActividad);
		sesion = new SesionAG(LocalDate.now(), LocalDateTime.now(), LocalDateTime.now().plusHours(1), actividad, salaSimple, 40.0);
		cliente = new Cliente ("Paula", "698574213", LocalDate.of(1999, 03, 24), "pau24", "caracol9", tarjetaCredito1);
		tarjetaCredito1 = new TarjetaCredito("4507670001000009","346", "Julia");
	}
	
	@Test
	public void testIsClimatizada2() {
		//comprueba que esta fuera del horario
		salaSimple.definirHorarioClimatizacion(LocalDateTime.now(),  LocalDateTime.now().minusHours(12));
		Boolean res = salaSimple.isClimatizada();
		assertFalse(salaSimple.isClimatizada());
	}

	@Test
	public void testSetAforo1() {
		Boolean resultado = salaSimple.setAforo(-1);
		assertFalse(resultado);
	}
	
	@Test
	public void testSetAforo2() {
		Boolean resultado = salaSimple.setAforo(20);
		assertTrue(resultado);
	}

	@Test
	public void testSetNombre1() {
		Boolean resultado = salaSimple.setNombre("Cardio");
		assertTrue(resultado);	
	}
	
	@Test
	public void testSetNombre2() {
		Boolean resultado = salaSimple.setNombre(null);
		assertFalse(resultado);	
	}

	@Test
	public void testSetDescripcion1() {
		Boolean resultado = salaSimple.setDescripcion(null);
		assertFalse(resultado);	
	}
	
	@Test
	public void testSetDescripcion2() {
		Boolean resultado = salaSimple.setDescripcion("Clase para realizar cardio");
		assertTrue(resultado);	
	}

	@Test
	public void testDefinirHorarioClimatizacion1() {
		//pone una fecha inicial que es anterior a la fecha actual
		Boolean res = salaSimple.definirHorarioClimatizacion(LocalDateTime.now().minusHours(6),LocalDateTime.now());
		assertFalse(res);
	}


	@Test
	public void testAddSesion1() {
		//comprobar que no esta en la lista y lo aÃ±ade
		Boolean res = salaSimple.salaContieneSesion(sesion);
		assertFalse(res);
		
		Boolean resultado = salaSimple.addSesion(sesion);
		assertTrue(resultado);
	}
	
	@Test
	public void testAddSesion2() {
		//lo aÃ±ade y comprueba que si esta en la lista
		
		Boolean resultado = salaSimple.addSesion(sesion);
		assertTrue(resultado);
		
		Boolean res = salaSimple.salaContieneSesion(sesion);
		assertTrue(res);
		
		
	}
	
	@Test
	public void testAddSesion3() {
		//aÃ±ade una sesion que ya esta en la lista		
		Boolean resultado = salaSimple.addSesion(sesion);
		assertTrue(resultado);
		
		Boolean res = salaSimple.salaContieneSesion(sesion);
		assertTrue(res);
		
		Boolean resultado1 = salaSimple.addSesion(sesion);
		assertFalse(resultado1);
	}

	@Test
	public void testRemoveSesion1() {
		//comprobar que esta en la lista, lo elimina y comprueba que no esta en la lista
		Boolean res2 = salaSimple.addSesion(sesion);
		assertTrue(res2);
		
		Boolean res = salaSimple.salaContieneSesion(sesion);
		assertTrue(res);
		
		Boolean resultado = salaSimple.removeSesion(sesion);
		assertTrue(resultado);
		
		Boolean res1 = salaSimple.salaContieneSesion(sesion);
		assertFalse(res1);
		
	}
	
	@Test
	public void testRemoveSesion2() {
		//comprobar que esta en la lista, lo elimina y comprueba que no esta en la lista
		Boolean res = salaSimple.salaContieneSesion(sesion);
		assertFalse(res);
		
		Boolean resultado = salaSimple.removeSesion(sesion);
		assertFalse(resultado);
		
	}

	@Test
	public void testContainsSesion1() {
		Boolean res = salaSimple.salaContieneSesion(null);
		assertFalse(res);
	}
	
	@Test
	public void testContainsSesion2() {
		//aÃ±ado la sesion y compruebo que esta
		salaSimple.addSesion(sesion);
		
		Boolean res = salaSimple.salaContieneSesion(sesion);
		assertTrue(res);
	}

	
}

