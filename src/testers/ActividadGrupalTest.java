package testers;

import org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import aplicacion.actividad.sesion.*;
import aplicacion.actividad.sesion.requisito.Requisito;
import aplicacion.excepciones.FueraHorarioClimatizacion;
import aplicacion.sala.Sala;
import aplicacion.sala.SalaSimple;
import aplicacion.usuario.*;
import aplicacion.Gimnasio;
import aplicacion.actividad.ActividadGrupal;
import aplicacion.actividad.TipoActividad;
import aplicacion.actividad.sesion.requisito.*;

public class ActividadGrupalTest {

	private ActividadGrupal actividadGrupal;
	private RequisitoCancelaciones requisito;
	private Monitor monitor;
	private TipoActividad tipoActividad;
	private SalaSimple salaSimple;

	@Before
	public void setUp() throws Exception{
		monitor = new Monitor("Manu325", "00523821","gymManu", "Manuel", "manuel.guti@gmail.com");
		requisito = new RequisitoCancelaciones(0, 15);
		tipoActividad = new TipoActividad("Yoga1");
		actividadGrupal = new ActividadGrupal("Cardio", monitor, requisito, tipoActividad);
		salaSimple = new SalaSimple("Sala1", "Sala de Cardio", 3);
	}
	@Test
	public void testCrearSesion1() throws FueraHorarioClimatizacion {
		//pongo una fecha anterior a la actual
		salaSimple.definirHorarioClimatizacion(LocalDateTime.now(), LocalDateTime.now().plusHours(1));
		Sesion res = actividadGrupal.crearSesion(LocalDate.now().minusDays(15), LocalDateTime.now(), LocalDateTime.now().plusHours(1), salaSimple, monitor);
		assertNull(res);
	}
	
	@Test
	public void testCrearSesion2() throws FueraHorarioClimatizacion {
		//pongo una fecha y una hora anterior a la actual
		salaSimple.definirHorarioClimatizacion(LocalDateTime.now(), LocalDateTime.now().plusHours(1));
		Sesion res = actividadGrupal.crearSesion(LocalDate.now().minusDays(15), LocalDateTime.now(), LocalDateTime.now().minusMinutes(15), salaSimple, monitor);
		assertNull(res);
	}
	
	@Test
	public void testCrearSesion3() throws FueraHorarioClimatizacion {
		//pongo una fecha y una hora anterior a la actual
		salaSimple.definirHorarioClimatizacion(LocalDateTime.now().plusDays(2), LocalDateTime.now().plusDays(2).plusHours(1));
		Sesion res = actividadGrupal.crearSesion(LocalDate.now().plusDays(2), LocalDateTime.now().plusDays(2), LocalDateTime.now().plusDays(2).plusHours(1), salaSimple, monitor);
		
		assertTrue(actividadGrupal.sesionesContieneSesion(res));
		
	}

	@Test
	public void testSetNombre1() {
		Boolean res = actividadGrupal.setNombre("Gimnasio");
		assertTrue(res);
		
		assertEquals(actividadGrupal.getNombre(), "Gimnasio");
	}
	
	@Test
	public void testSetNombre2() {
		Boolean res = actividadGrupal.setNombre(null);
		assertFalse(res);
	}

	
	@Test
	public void testSesionesContieneSesion1() throws FueraHorarioClimatizacion
	{
		salaSimple.definirHorarioClimatizacion(LocalDateTime.now().plusDays(2), LocalDateTime.now().plusDays(2).plusHours(1));
		SesionAG sesionAG = actividadGrupal.crearSesion(LocalDate.now().plusDays(2),LocalDateTime.now().plusDays(2), LocalDateTime.now().plusDays(2).plusHours(1), salaSimple, monitor);
		Boolean res = actividadGrupal.sesionesContieneSesion(sesionAG);
		assertTrue(res);
	}
	
	@Test
	public void testSesionesContieneSesion2()
	{
		Boolean res = actividadGrupal.sesionesContieneSesion(null);
		assertFalse(res);
	}
	
	@Test
	public void testCrearRutinaSesiones1() throws FueraHorarioClimatizacion{
		salaSimple.definirHorarioClimatizacion(LocalDateTime.now().plusDays(2), LocalDateTime.now().plusDays(2).plusHours(5));
		Boolean res = actividadGrupal.crearRutinaSesiones(5, LocalDate.now().plusDays(2), LocalDateTime.now().plusDays(2), LocalDateTime.now().plusDays(2).plusHours(1), salaSimple, monitor);
		assertTrue(actividadGrupal.getSesionesMonitorizadas().size() == 5);
	}
	
	@Test
	public void testCrearRutinaSesiones2() throws FueraHorarioClimatizacion{
	//Comprobar fecha inicio antes de la actual
		Boolean res = actividadGrupal.crearRutinaSesiones(5, LocalDate.now().minusDays(3), LocalDateTime.now(), LocalDateTime.now(), salaSimple, monitor);
		assertFalse(res);
	}
	
	@Test
	public void testCrearRutinaSesiones3() throws FueraHorarioClimatizacion{
	//comprobar que crear sesion sea null
		Boolean res = actividadGrupal.crearRutinaSesiones(5, LocalDate.now().minusDays(3), LocalDateTime.now(), LocalDateTime.now(), null, monitor);
		assertFalse(res);
	}
	
	@Test
	public void testCrearRutinaSesiones4() throws FueraHorarioClimatizacion{
	//comprobar num sesiones <=0
		Boolean res = actividadGrupal.crearRutinaSesiones(-5, LocalDate.now().minusDays(3), LocalDateTime.now(), LocalDateTime.now(), salaSimple, monitor);
		assertFalse(res);
	}
	
	@Test
	public void testCrearRutinaSesiones5() throws FueraHorarioClimatizacion{
	//comprobar que la hora fin = null
		Boolean res = actividadGrupal.crearRutinaSesiones(5, null, LocalDateTime.now(), LocalDateTime.now(), salaSimple, monitor);
		assertFalse(res);
	}
	
	@Test
	public void testCrearRutinaSesiones6() throws FueraHorarioClimatizacion{
	//comprobar que la sala simple = null
		Boolean res = actividadGrupal.crearRutinaSesiones(5, LocalDate.now().minusDays(3), LocalDateTime.now(), LocalDateTime.now(), null, monitor);
		assertFalse(res);
	}
	
	@Test
	public void testCrearRutinaSesiones7() throws FueraHorarioClimatizacion{
	//comprobar que monitor = null
		Boolean res = actividadGrupal.crearRutinaSesiones(5, LocalDate.now().minusDays(3), LocalDateTime.now(), LocalDateTime.now(), salaSimple, null);
		assertFalse(res);
	}
	
	
	@Test
	public void testCrearRequisitoEdad1(){
		Boolean res = actividadGrupal.crearRequisitoEdad(-1, 4);
		assertFalse(res);
	}
	
	@Test
	public void testCrearRequisitoEdad2(){
		Boolean res = actividadGrupal.crearRequisitoEdad(1, -4);
		assertFalse(res);
	}
	
	@Test
	public void testCrearRequisitoEdad3(){
		Boolean res = actividadGrupal.crearRequisitoEdad(1, 4);
		assertTrue(res);
	}
	
	@Test
	public void testCrearRequisitoVeterania1(){
		Boolean res = actividadGrupal.crearRequisitoVeterania(-1, 4);
		assertFalse(res);
	}
	
	@Test
	public void testCrearRequisitoVeterania2(){
		Boolean res = actividadGrupal.crearRequisitoVeterania(1, -4);
		assertFalse(res);
	}
	
	@Test
	public void testCrearRequisitoVeterania3(){
		Boolean res = actividadGrupal.crearRequisitoVeterania(1, 4);
		assertTrue(res);
	}
	
	@Test
	public void testCrearRequisitoCancelaciones1(){
		Boolean res = actividadGrupal.crearRequisitoCancelaciones(-1, 4);
		assertFalse(res);
	}
	
	@Test
	public void testCrearRequisitoCancelaciones2(){
		Boolean res = actividadGrupal.crearRequisitoCancelaciones(1, -4);
		assertFalse(res);
	}
	
	@Test
	public void testCrearRequisitoCancelaciones3(){
		Boolean res = actividadGrupal.crearRequisitoVeterania(1, 4);
		assertTrue(res);
	}
		
}
