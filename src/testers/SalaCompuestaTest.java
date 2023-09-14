package testers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import aplicacion.actividad.sesion.*;
import aplicacion.sala.SalaSimple;
import aplicacion.usuario.Monitor;
import aplicacion.actividad.*;
import aplicacion.actividad.sesion.requisito.*;
import aplicacion.sala.*;


public class SalaCompuestaTest {
	private Sesion sesion;
	private Monitor monitor;
	private TipoActividad tipoActividad;
	private SalaCompuesta salaCompuesta;
	private SalaSimple salaSimple;
	private ActividadGrupal actividadGrupal;
	private RequisitoCancelaciones requisito;
	
	@Before
	public void setUp() throws Exception{
		salaCompuesta = new SalaCompuesta("Pesas", "Para realizar levantamiento de pesas", 15);
		monitor = new Monitor("Manu325", "00523821","gymManu", "Manuel", "manuel.guti@gmail.com");
		requisito = new RequisitoCancelaciones(0, 15);
		tipoActividad = new TipoActividad("Yoga1");
		salaSimple = new SalaSimple("Pesas", "Para realizar levantamiento de pesas", 15);
		actividadGrupal = new ActividadGrupal("Cardio", monitor, requisito, tipoActividad);
		sesion = new SesionAG(LocalDate.now().plusDays(2), LocalDateTime.now(), LocalDateTime.now().plusHours(1), actividadGrupal, salaSimple, 40.0);
		
	}

	@Test
	public void testIsClimatizada1() {
		//comprueba que esta dentro del horario
		salaCompuesta.definirHorarioClimatizacion(LocalDateTime.now(),LocalDateTime.now().plusHours(4));;
		Boolean res = salaCompuesta.isClimatizada();
		assertTrue(res);
	}
	
	@Test
	public void testIsClimatizada2() {
		//comprueba que esta fuera del horario
		salaCompuesta.definirHorarioClimatizacion(LocalDateTime.now().minusHours(3),LocalDateTime.now().minusHours(12));
		Boolean res = salaCompuesta.isClimatizada();
		assertFalse(res);
	}

	@Test
	public void testSetAforo1() {
		Boolean resultado = salaCompuesta.setAforo(-1);
		assertFalse(resultado);
	}
	
	@Test
	public void testSetAforo2() {
		Boolean resultado = salaCompuesta.setAforo(20);
		assertTrue(resultado);
	}

	@Test
	public void testSetNombre1() {
		Boolean resultado = salaCompuesta.setNombre("Cardio");
		assertTrue(resultado);	
	}
	
	@Test
	public void testSetNombre2() {
		Boolean resultado = salaCompuesta.setNombre(null);
		assertFalse(resultado);	
	}

	@Test
	public void testSetDescripcion1() {
		Boolean resultado = salaCompuesta.setDescripcion(null);
		assertFalse(resultado);	
	}
	
	@Test
	public void testSetDescripcion2() {
		Boolean resultado = salaCompuesta.setDescripcion("Clase para realizar cardio");
		assertTrue(resultado);	
	}

	@Test
	public void testDefinirHorarioClimatizacion1() {
		//pone una fecha inicial que es anterior a la fecha actual
		Boolean res = salaSimple.definirHorarioClimatizacion(LocalDateTime.now().minusHours(6),  LocalDateTime.now().plusHours(12));
		assertFalse(res);
	}

	
	@Test
	public void testDefinirHorarioClimatizacion2() {
		//pone una fecha final que es anterior a la fecha actual
		Boolean res = salaSimple.definirHorarioClimatizacion(LocalDateTime.now(), LocalDateTime.now().minusHours(12));
		assertFalse(res);
	}

	@Test
	public void testDefinirHorarioClimatizacion3() {
		//pone una fecha inicial que es posterior a la fecha final
		Boolean res = salaSimple.definirHorarioClimatizacion(LocalDateTime.now().plusHours(3),  LocalDateTime.now());
		assertFalse(res);
	}
	
	@Test
	public void testDefinirHorarioClimatizacion4() {
		//pone una fecha inicial que es posterior a la fecha final
		Boolean res = salaSimple.definirHorarioClimatizacion(LocalDateTime.now(),  LocalDateTime.now().plusHours(3));
		assertTrue(res);
	}
	
	@Test
	public void testCrearSalaCompuesta() {
		salaCompuesta.definirHorarioClimatizacion(LocalDateTime.now(), LocalDateTime.now().plusHours(2));
		SalaCompuesta salaCompuesta1 = salaCompuesta.crearSalaCompuesta("cardio", "para gastar calorias", 30);
		Boolean res = salaCompuesta.getSubsalas().contains(salaCompuesta1);
		assertTrue(res);
	}
	
	@Test
	public void testCrearSalaCompuesta1()
	{
		assertNull(salaCompuesta.crearSalaCompuesta(null, "para gastar calorias", 17));
	}
	
	@Test
	public void testCrearSalaCompuesta2()
	{
		assertNull(salaCompuesta.crearSalaCompuesta("cardio", null, 17));
	}
	
	@Test
	public void testCrearSalaCompuesta3()
	{
		assertNull(salaCompuesta.crearSalaCompuesta("cardio", "para gastar calorias", -17));
	}

}
