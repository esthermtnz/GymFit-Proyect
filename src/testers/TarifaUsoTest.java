package testers;

import aplicacion.actividad.ActividadGrupal;
import aplicacion.actividad.PlanPersonalizado;
import aplicacion.actividad.TipoActividad;
import aplicacion.actividad.TipoObjetivo;
import aplicacion.actividad.sesion.SesionAG;
import aplicacion.actividad.sesion.SesionLibre;
import aplicacion.actividad.sesion.SesionPP;
import aplicacion.actividad.sesion.requisito.Requisito;
import aplicacion.sala.SalaSimple;
import aplicacion.usuario.Cliente;
import aplicacion.usuario.Monitor;
import aplicacion.usuario.tarifa.TarifaUso;
import aplicacion.usuario.tarifa.TarjetaCredito;
import aplicacion.actividad.sesion.requisito.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TarifaUsoTest {

	private SesionAG sesionAG;
	private Requisito requisito, requisito1, requisito2;
	private Monitor monitor;
	private TarifaUso tarifaUso;
	private Cliente cliente;
	private TarjetaCredito tarjetaCredito;
	private TipoActividad tipoActividad;
	private SesionPP sesionPP;
	private SesionLibre sesionLibre;
	private SalaSimple salaSimple;
	private PlanPersonalizado planPersonalizado;
	private ActividadGrupal actividad;
	
	@Before
	public void setup() throws Exception{
		actividad = new ActividadGrupal("Cardio", monitor, requisito, tipoActividad);
		sesionAG = new SesionAG(LocalDate.now().plusDays(2), LocalDateTime.now(), LocalDateTime.now().plusHours(1), actividad, salaSimple, 40.0);
		monitor = new Monitor("Manu325", "00523821","gymManu", "Manuel", "manuel.guti@gmail.com");
		requisito = new RequisitoEdad(16, 33);
		requisito1 = new RequisitoCancelaciones(0,3);
		requisito2 = new RequisitoVeterania(3, 7);
		tipoActividad = new TipoActividad("Yoga1");
		sesionPP = new SesionPP(LocalDate.now().plusDays(1), LocalDateTime.now().plusHours(4), LocalDateTime.now().plusHours(6), planPersonalizado, salaSimple, 50.0);
		sesionLibre = new SesionLibre(LocalDate.now(), LocalDateTime.now(), LocalDateTime.now().plusHours(1), salaSimple, 20.0);
		tarifaUso = new TarifaUso(10.0);
		tarjetaCredito = new TarjetaCredito("4507670001000009","346", "Carla");
		cliente = new Cliente ("Carla", "657843210", LocalDate.of(2000, 6, 5), "luissito", "kslkncls", tarjetaCredito);
		salaSimple = new SalaSimple("Pesas", "Para realizar levantamiento de pesas", 15);
		planPersonalizado = new PlanPersonalizado ("Ganar musculo", monitor, requisito, TipoObjetivo.GANANCIAMUSCULAR, "Clases para ganar masa muscular");
		sesionLibre =  new SesionLibre(LocalDate.now(), LocalDateTime.now(), LocalDateTime.now().plusHours(1), salaSimple, 20.0);	
	}
	

	
	@Test
	public void testCalculaPrecio1() 
	{
		Double res = tarifaUso.calculaPrecio(sesionAG);
		assertEquals(res, 40.0, 0);
	}
	
	@Test
	public void testCalculaPrecio2() 
	{
		Double res = tarifaUso.calculaPrecio(sesionPP);
		assertEquals(res, 50.0, 0);
		
	}
	
	@Test
	public void testCalculaPrecio3() 
	{
		Double res = tarifaUso.calculaPrecio(sesionLibre);
		assertEquals(res, 20.0, 0);
		
	}

}
