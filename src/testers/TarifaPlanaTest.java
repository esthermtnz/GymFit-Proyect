package testers;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import aplicacion.actividad.ActividadGrupal;
import aplicacion.actividad.TipoActividad;
import aplicacion.actividad.sesion.SesionAG;
import aplicacion.actividad.sesion.requisito.Requisito;
import aplicacion.sala.SalaSimple;
import aplicacion.usuario.Monitor;
import aplicacion.usuario.Cliente;
import aplicacion.usuario.tarifa.TarifaPlana;
import aplicacion.usuario.tarifa.TarjetaCredito;
import aplicacion.usuario.tarifa.TipoTarifaPlana;
import aplicacion.actividad.sesion.requisito.*;

public class TarifaPlanaTest {
	
	private TarifaPlana tarifaPlana, tarifaCaducada;
	private SesionAG sesion;
	private TipoActividad tipoActividad;
	private TipoTarifaPlana tipoTarifaPlana;
	private ActividadGrupal actividad;
	private SalaSimple salaSimple;
	private Cliente cliente;
	private TarjetaCredito tarjetaCredito;
	private Monitor monitor;
	private Requisito requisito;
	
	
	@Before
	public void setUp() throws Exception{
		//no tiene sentido que haya una fecha de fin y otra de expedicion
		tipoActividad = new TipoActividad("Yoga1");
		tipoTarifaPlana = TipoTarifaPlana.TRIMESTRAL;
		requisito = new RequisitoVeterania (2, 15);
		monitor = new Monitor("Manu325","00523821", "gymManu", "Manuel", "manuel.guti@gmail.com");
		salaSimple = new SalaSimple("Pesas", "Para realizar levantamiento de pesas", 3);		
		actividad = new ActividadGrupal("Zumba",monitor, requisito,tipoActividad);
		sesion = new SesionAG (LocalDate.of(2023,3,28), LocalDateTime.now(), LocalDateTime.now().plusHours(1), actividad, salaSimple, 40.0);
		tarifaPlana = new TarifaPlana(LocalDate.now(), LocalDate.now().plusMonths(3), 100.0, tipoTarifaPlana, tipoActividad);
		tarifaCaducada = new TarifaPlana(LocalDate.now().minusMonths(4), LocalDate.now().minusMonths(1), 100.0, tipoTarifaPlana, tipoActividad);
		tarjetaCredito = new TarjetaCredito("4507670001000009","346", "Carla");
		cliente = new Cliente ("Carla", "657843210", LocalDate.of(2000, 6, 5), "luissito", "kslkncls", tarjetaCredito);
		
	}

	@Test
	public void testSetCaducada1() {
		
		Boolean res = tarifaPlana.setCaducada(true);
		assertTrue(res);
				
		assertEquals(tarifaPlana.getCaducada(), true);
	}
	
	@Test
	public void testSetCaducada2() {
		
		Boolean res = tarifaCaducada.setCaducada(false);
		assertTrue(res);
		
		assertEquals(tarifaCaducada.getCaducada(), false);
	}

	@Test
	public void testSetFechaInicio1() {
		Boolean res = tarifaPlana.setFechaInicio(LocalDate.now().minusDays(5));
		assertFalse(res);
	}
	
	@Test
	public void testSetFechaInicio2() {
		Boolean res = tarifaPlana.setFechaInicio(LocalDate.now());
		assertTrue(res);
		
		assertEquals(tarifaPlana.getFechaInicio(), LocalDate.now());
	}

	@Test
	public void testSetFechaFin1() {
		Boolean res = tarifaPlana.setFechaFin(LocalDate.now());
		assertTrue(res);
		
		assertEquals(tarifaPlana.getFechaFin(), LocalDate.now());
	}
	
	@Test
	public void testSetFechaFin2() {
		Boolean res = tarifaPlana.setFechaFin(LocalDate.now().minusDays(5));
		assertFalse(res);
		
	}


	@Test
	public void testSetPrecio1() {
		Boolean res = tarifaPlana.setPrecio(-44.78);
		assertFalse(res);
		
	}

	@Test
	public void testSetTipoTarifaPlana1() {
		Boolean res = tarifaPlana.setTipoTarifaPlana(TipoTarifaPlana.ANUAL);
		assertTrue(res);
		
	}
	
	
	@Test
	public void testSetTipoTarifaPlana2() {
		Boolean res = tarifaPlana.setTipoTarifaPlana(null);
		assertFalse(res);
		
	}

	@Test
	public void testSetPrecio2() {
		Boolean res = tarifaPlana.setPrecio(44.78);
		assertTrue(res);
		
	}
	
	@Test
	public void testDescuento1()
	{
		Double res = tarifaPlana.descuento(-4.0);
		assertEquals(res, -1.0, 0);
	}
	
	@Test
	public void testDescuento2()
	{
		Double res = tarifaPlana.descuento(20.0);
		
		assertEquals(res, 80.0, 0);
		
		assertEquals(tarifaPlana.getPrecio(), 80.0, 0);
		
	}

	@Test
	public void testprecioDefi1()
	{
		Double res = tarifaPlana.descuento(20.0);
		assertEquals(res, 80.0, 0);
		assertEquals(tarifaPlana.getPrecio(), 80.0, 0);
		
		Double res1 = tarifaPlana.precioDefinitivo(sesion);
		assertEquals(res1, 40.0, 0);
		assertEquals(tarifaPlana.getPrecio(), 40.0, 0);
	}
	
	@Test
	public void testprecioDefi2()
	{
		
		Double res1 = tarifaPlana.precioDefinitivo(null);
		assertEquals(res1, -1.0, 0);
	}

	@Test
	public void testRenovar1() {
		//intento renovar con la tarifa activa
		
		Boolean res = tarifaPlana.renovar(5, tipoActividad);
		assertFalse(res);
		
	}
	
	@Test
	public void testRenovar2() {
		//intento renovar una tarifa caducada con un tiempo negativo
		
		Boolean res = tarifaCaducada.renovar(-5, tipoActividad);
		assertFalse(res);
		
	}
	
	
	@Test
	public void testRenovar3() {
		//renuevo una tarifa caducada sin fallos
		tarifaCaducada.setCaducada(true);
		Boolean res = tarifaCaducada.renovar(5, tipoActividad);
		assertTrue(res);
		
				
	}


}
