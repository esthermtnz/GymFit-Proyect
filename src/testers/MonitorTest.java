package testers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;

import es.uam.eps.padsof.payrolls.exceptions.InvalidPeriod;
import es.uam.eps.padsof.payrolls.exceptions.NonExistentFileException;
import es.uam.eps.padsof.payrolls.exceptions.UnsupportedImageTypeException;

import aplicacion.excepciones.*;
import org.junit.Before;
import org.junit.Test;

import aplicacion.Gimnasio;
import aplicacion.actividad.Actividad;
import aplicacion.actividad.ActividadGrupal;
import aplicacion.actividad.TipoActividad;
import aplicacion.actividad.sesion.SesionAG;
import aplicacion.actividad.sesion.SesionMonitorizada;
import aplicacion.actividad.sesion.requisito.Requisito;
import aplicacion.sala.SalaSimple;
import aplicacion.usuario.Monitor;
import aplicacion.usuario.*;
import aplicacion.actividad.*;
import aplicacion.actividad.sesion.SesionPP;
import aplicacion.actividad.sesion.requisito.*;
import aplicacion.equipacion.Maquina;
import aplicacion.equipacion.Propiedad;

import java.time.*;

public class MonitorTest {

	/*@Test
	void testMostrarDatos() {
		fail("Not yet implemented");
	}*/
	
	private Monitor monitor;
	private RequisitoCancelaciones requisito;
	private Actividad actividad1, actividad2;
	private SalaSimple salaSimple;
	private TipoActividad tipoActividad;
	
	
	@Before
	public void setUp() throws Exception{
		monitor = new Monitor("Manu325","00523821", "gymManu", "Manuel", "manuel.guti@gmail.com");
		tipoActividad = new TipoActividad("Yoga1");
		requisito = new RequisitoCancelaciones (2, 15);
		actividad1 = new ActividadGrupal("Zumba",monitor, requisito,tipoActividad);
		
	}

	@Test
	public void testSetNombre1() {
		Boolean res = monitor.setName("Fran");
		assertTrue(res);
		
		assertEquals(monitor.getName(), "Fran");
	}
	
	@Test
	public void testSetNombre2() {
		Boolean res = monitor.setName(null);
		assertFalse(res);
		
	}

	@Test
	public void testSetEmail1() {
		Boolean res = monitor.setEmail("f.gutierrez@gmai.com");
		assertTrue(res);
		
		assertEquals(monitor.getEmail(), "f.gutierrez@gmai.com");
	}
	
	@Test
	public void testSetEmail2() {
		Boolean res = monitor.setEmail(null);
		assertFalse(res);
	}
	
	@Test
	public void testAddActividadGrupal1()
	{
		Boolean res = monitor.addActividadGrupal(null);
		assertFalse(res);
	}
	
	@Test
	public void testAddActividadGrupal2()
	{
		ActividadGrupal actividadGrupal = new ActividadGrupal("padel", monitor, requisito, tipoActividad);
		monitor.addActividadGrupal(actividadGrupal);
		Boolean res = monitor.addActividadGrupal(actividadGrupal);
		assertFalse(res);
	}

	@Test
	public void testAddActividadGrupal3()
	{
		//String nombre, Monitor monitor, Requisito requisito, TipoActividad tipoActividad
		ActividadGrupal actividadGrupal = new ActividadGrupal("padel", monitor, requisito, tipoActividad);
		Boolean res = monitor.addActividadGrupal(actividadGrupal);
		assertTrue(res);
	}

	@Test
	public void testAddPlanPersonalizado1()
	{
		Boolean res = monitor.addPlanPersonalizado(null);
		assertFalse(res);
	}
	
	@Test
	public void testAddPlanPersonalizado2()
	{
		//String nombre, Monitor monitor, Requisito requisito, TipoObjetivo tipoObjetivo,String descripcion
		PlanPersonalizado planPersonalizado = new PlanPersonalizado("padel", monitor, requisito, TipoObjetivo.PERDIDAPESO, "para amigos");
		monitor.addPlanPersonalizado(planPersonalizado);
		Boolean res = monitor.addPlanPersonalizado(planPersonalizado);
		assertFalse(res);
	}

	@Test
	public void testAddPlanPersonalizado3()
	{
		//String nombre, Monitor monitor, Requisito requisito, TipoActividad tipoActividad
		PlanPersonalizado planPersonalizado = new PlanPersonalizado("padel", monitor, requisito, TipoObjetivo.PERDIDAPESO, "para amigos");
		Boolean res = monitor.addPlanPersonalizado(planPersonalizado);
		assertTrue(res);
	}


	@Test
	public void testgetBaseSalaryPerMonth1() throws ExcepcionUsuario, FechaPosterior{
		Double sueldo = monitor.getBaseSalaryPerMonth();
		
		assertEquals(sueldo, -1.0, 0);
	}

	@Test
	public void testgetBaseSalaryPerMonth2() throws ExcepcionUsuario, FechaPosterior{
		
		ActividadGrupal actividadGrupal = new ActividadGrupal("Zumba",monitor, requisito, tipoActividad);
		salaSimple = new SalaSimple("Pesas", "Para realizar levantamiento de pesas", 15);
		SesionAG sesionAG = new SesionAG(LocalDate.now().plusDays(2), LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0, 0)), LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0, 0).plusHours(1)), actividad2, salaSimple, 40.0);
		
		actividadGrupal.addSesionMonitorizada(sesionAG);
		monitor.addActividadGrupal(actividadGrupal);
		
		Double sueldo = monitor.getBaseSalaryPerMonth();
		
		assertEquals(sueldo, 1040.0, 0.0);
	}
	
	
	@Test
	public void testSetUsuario1() {
		Boolean res = monitor.setUsuario(null);
		assertFalse(res);
	}
	
	@Test
	public void testSetUsuario2() {
		Boolean res = monitor.setUsuario("mark927");
		assertTrue(res);
		
		assertEquals(monitor.getUsuario(), "mark927");
	}

	@Test
	public void testSetContrasenya1() {
		Boolean res = monitor.setContrasenya("caracola");
		assertTrue(res);
		
		assertEquals(monitor.getContrasenya(), "caracola");
	}
	
	@Test
	public void testSetContrasenya2() {
		Boolean res = monitor.setContrasenya(null);
		assertFalse(res);
	}
	
	
	@Test
	public void testaddNotificaciones1()
	{
		Boolean res = monitor.addNotificaciones(null);
		assertFalse(res);
		
	}
	
	@Test
	public void testaddNotificaciones2()
	{
		Notificacion n = new Notificacion("Llevas 21 dias en el gym");
		Boolean res1 = monitor.listaContieneNotificaion(n);
		assertFalse(res1);
		
		Boolean res = monitor.addNotificaciones(n);
		assertTrue(res);
		
		Boolean res2 = monitor.listaContieneNotificaion(n);
		assertTrue(res2);
	}
	
	@Test
	public void testaddNotificaciones3()
	{
		Notificacion n = new Notificacion("Llevas 21 dias en el gym");
		
		Boolean res2 = monitor.addNotificaciones(n);
		assertTrue(res2);
		
		Boolean res1 = monitor.listaContieneNotificaion(n);
		assertTrue(res1);
		
		Boolean res = monitor.addNotificaciones(n);
		assertFalse(res);
		
	}
	
	

	@Test
	public void testCalcularHorasExtra1()
	{
		ActividadGrupal actividadGrupal = new ActividadGrupal("Zumba",monitor, requisito,tipoActividad);
		salaSimple = new SalaSimple("Pesas", "Para realizar levantamiento de pesas", 15);
		SesionAG sesionAG = new SesionAG(LocalDate.now().plusDays(2), LocalDateTime.now(), LocalDateTime.now().plusHours(1), actividadGrupal, salaSimple, 40.0);
		
		actividadGrupal.addSesionMonitorizada(sesionAG);
		monitor.addActividadGrupal(actividadGrupal);
		
		Boolean res = monitor.calcularHorasExtra();
		assertTrue(res);
		
		
		assertEquals(monitor.getHorasExtra(),1, 0);
		
	}
	
	@Test
	public void testCalcularHorasExtra2()
	{
		Boolean res = monitor.calcularHorasExtra();
		assertFalse(res);
	}

	@Test
	public void testDescargarNomina1()throws NonExistentFileException,UnsupportedImageTypeException,InvalidPeriod, ExcepcionUsuario, FechaPosterior, SueldoNoDefinido{
		Boolean res = monitor.descargarNomina(Month.FEBRUARY);
		assertTrue(res);
	}
	
	@Test
	public void testDescargarNomina2()throws NonExistentFileException,UnsupportedImageTypeException,InvalidPeriod, ExcepcionUsuario, FechaPosterior, SueldoNoDefinido{
		Boolean res = monitor.descargarNomina(null);
		assertFalse(res);
	}
	
	@Test(expected=InvalidPeriod.class)
	public void testDescargarNomina4()throws NonExistentFileException,UnsupportedImageTypeException,InvalidPeriod, ExcepcionUsuario, FechaPosterior, SueldoNoDefinido{
		Boolean res = monitor.descargarNomina(Month.JULY);
		assertTrue(res);
	}
	
	@Test
	public void testCancelarPlanPersonalizado1 () {
	//Actividad null
		Boolean res = monitor.cancelarPlanPersonalizado(null);
		assertFalse(res);
	}
	
	@Test
	public void testCancelarPlanPersonalizado2 () {
		//cancelarsesionpp que de null
		PlanPersonalizado plan = new PlanPersonalizado("padel", monitor, requisito, TipoObjetivo.PERDIDAPESO, "para amigos");
		monitor.addPlanPersonalizado(plan);
		Boolean res = monitor.cancelarPlanPersonalizado(plan);
		assertTrue(res);
		
	}
	
	@Test
	public void testCancelarSesionPlanPersonalizado1(){
		Boolean res = monitor.cancelarPlanPersonalizado(null);
		assertFalse(res);
	}
	
	@Test
	public void testCancelarSesionPlanPersonalizado2(){
		PlanPersonalizado plan = new PlanPersonalizado("padel", monitor, requisito, TipoObjetivo.PERDIDAPESO, "para amigos");
		monitor.addPlanPersonalizado(plan);
		//LocalDate fecha, LocalDateTime horaIni, LocalDateTime horaFin, Actividad actividad, SalaSimple sala, Double precio
		SesionPP sesionPP = new SesionPP(LocalDate.now().plusDays(2), LocalDateTime.now(), LocalDateTime.now().plusHours(2), plan, salaSimple, 25.99);
		plan.addSesionMonitorizada(sesionPP);
		Boolean res = monitor.cancelarSesionPlanPersonalizado(sesionPP);
	}
	
	@Test
	public void testIndicarAveria1() throws AveriaPreviamenteReportada{
		Boolean res = monitor.indicarAveria(null);
		assertFalse(res);
	}

	@Test
	public void testIndicarAveria2() throws AveriaPreviamenteReportada, ExcepcionUsuario, FechaPosterior{
		Maquina maquina = Gimnasio.getGimnasio().darAltaMaquinaPropiedad("Correr", "Quemar calorias", "Samsung", 20.5, LocalDate.now().minusDays(5));
		Boolean res = monitor.indicarAveria(maquina);
		assertTrue(res);
	}
	
	@Test(expected=AveriaPreviamenteReportada.class)
	public void testIndicarAveria3() throws AveriaPreviamenteReportada, ExcepcionUsuario, FechaPosterior{
		Maquina maquina = Gimnasio.getGimnasio().darAltaMaquinaPropiedad("Correr", "Quemar calorias", "Samsung", 20.5, LocalDate.now().minusDays(5));
		monitor.indicarAveria(maquina);
		Boolean res = monitor.indicarAveria(maquina);
		assertFalse(res);
	}
}
