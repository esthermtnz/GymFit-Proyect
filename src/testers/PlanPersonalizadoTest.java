package testers;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import aplicacion.usuario.Monitor;
import aplicacion.sala.SalaSimple;
import aplicacion.actividad.sesion.*;
import aplicacion.actividad.sesion.requisito.*;
import aplicacion.excepciones.FueraHorarioClimatizacion;
import aplicacion.actividad.*;
public class PlanPersonalizadoTest {

	private PlanPersonalizado planPersonalizado;
	private Monitor monitor;
	private RequisitoEdad requisito;
	private TipoObjetivo tipoObjetivo;
	private SalaSimple salaSimple;
	
	@Before
	public void setUp() throws Exception{
		monitor = new Monitor("Manu325","00523821", "gymManu", "Manuel", "manuel.guti@gmail.com");
		requisito = new RequisitoEdad (12, 15);
		planPersonalizado = new PlanPersonalizado("Ganar musculo", monitor, requisito, TipoObjetivo.GANANCIAMUSCULAR, "Clases para ganar masa muscular");
		tipoObjetivo = TipoObjetivo.PERDIDAPESO;
		salaSimple = new SalaSimple("Sala1", "Sala de Zumba", 3);
	}

	@Test
	public void testDecidePlan1() {
		Boolean res = planPersonalizado.decidePlan(tipoObjetivo);
		assertTrue(res);
	}
	
	@Test
	public void testDecidePlan2() {
		Boolean res = planPersonalizado.decidePlan(null);
		assertFalse(res);
	}

	@Test
	public void testCrearSesion1() throws FueraHorarioClimatizacion {
		//pongo una fecha y una hora anterior a la actual
		salaSimple.definirHorarioClimatizacion(LocalDateTime.now().plusDays(2), LocalDateTime.now().plusDays(2).plusHours(1));
		Sesion res = planPersonalizado.crearSesion(LocalDate.now().plusDays(2), LocalDateTime.now().plusDays(2), LocalDateTime.now().plusDays(2).plusHours(1), salaSimple, monitor);
		assertTrue(planPersonalizado.sesionesContieneSesion(res));
		
	}
	
	@Test
	public void testCrearSesion2() throws FueraHorarioClimatizacion {
		salaSimple.definirHorarioClimatizacion(LocalDateTime.now(), LocalDateTime.now().plusHours(5));
		Sesion res = planPersonalizado.crearSesion(LocalDate.now().minusDays(15), LocalDateTime.now(), LocalDateTime.now().minusMinutes(15), salaSimple, monitor);
		assertNull(res);
		
	}
	
	@Test
	public void testSesionesContieneSesion1() throws FueraHorarioClimatizacion {
		salaSimple.definirHorarioClimatizacion(LocalDateTime.now(), LocalDateTime.now().plusHours(1));
		SesionPP sesion = planPersonalizado.crearSesion(LocalDate.now(), LocalDateTime.now(), LocalDateTime.now().plusHours(1), salaSimple, monitor);
		Boolean res = planPersonalizado.sesionesContieneSesion(sesion);
		assertTrue(res);
	}
	
	@Test
	public void testSesionesContieneSesion2()
	{
		//comprueba que si le pasa un argumento null da false
		Boolean res = planPersonalizado.sesionesContieneSesion(null);
		assertFalse(res);
	}
	
	@Test
	public void testAddSesionMonitorizada1() {
		Boolean res = planPersonalizado.addSesionMonitorizada(null);
		assertFalse(res);
	}
	
	@Test
	public void testAddSesionMonitorizada2() {
		SesionMonitorizada sesion = new SesionPP(LocalDate.now().plusDays(1), LocalDateTime.now(), LocalDateTime.now().plusHours(1), planPersonalizado, salaSimple, 22.5 );
		planPersonalizado.addSesionMonitorizada(sesion);
		Boolean res = planPersonalizado.addSesionMonitorizada(sesion);
		assertFalse(res);
	}
	
	@Test
	public void testAddSesionMonitorizada3() {
		//LocalDate fecha, LocalDateTime horaIni, LocalDateTime horaFin, Actividad actividad, SalaSimple sala, Double precio
		SesionMonitorizada sesion = new SesionPP(LocalDate.now().plusDays(1), LocalDateTime.now(), LocalDateTime.now().plusHours(1), planPersonalizado, salaSimple, 22.5 );
		
		Boolean res = planPersonalizado.addSesionMonitorizada(sesion);
	
	}
	
	@Test
	public void testActividadesContieneSesionMonitorizada1() {
		Boolean res = planPersonalizado.actividadesContieneSesionMonitorizada(null);
		assertFalse(res);
	}
	
	@Test
	public void testActividadesContieneSesionMonitorizada2() {
		SesionMonitorizada sesion = new SesionPP(LocalDate.now().plusDays(1), LocalDateTime.now(), LocalDateTime.now().plusHours(1), planPersonalizado, salaSimple, 25.99);
		planPersonalizado.addSesionMonitorizada(sesion);
		
		Boolean res = planPersonalizado.actividadesContieneSesionMonitorizada(sesion);
		assertTrue(res);

	}
	
}
