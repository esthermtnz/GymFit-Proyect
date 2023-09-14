package testers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import aplicacion.Gimnasio;
import aplicacion.Reserva;
import aplicacion.actividad.sesion.Sesion;
import aplicacion.actividad.sesion.SesionLibre;
import aplicacion.sala.SalaSimple;
import aplicacion.usuario.Cliente;
import aplicacion.usuario.Monitor;
import aplicacion.usuario.tarifa.TarjetaCredito;
import aplicacion.actividad.TipoActividad;
import aplicacion.actividad.sesion.requisito.*;

public class ReservaTest {
	private Reserva reserva;
	private Cliente cliente;
	private Sesion sesion;
	private SalaSimple salaSimple;
	private RequisitoCancelaciones requisito;
	private Monitor monitor;
	private TarjetaCredito tarjetaCredito1;
	private TipoActividad tipoActividad;
	
	
	@Before
	public void setUp() throws Exception{
		cliente = new Cliente("Paula", "678965440", LocalDate.of(1991, 07, 02), "paula154a", "gympau", tarjetaCredito1);
		reserva = new Reserva(cliente, sesion, true);
		tipoActividad = new TipoActividad("Yoga1");
		requisito = new RequisitoCancelaciones (0, 15);
		monitor = new Monitor("Manu325", "00523821", "gymManu", "Manuel", "manuel.guti@gmail.com");
		salaSimple = new SalaSimple("Pesas", "Para realizar levantamiento de pesas", 15);
		sesion = new SesionLibre(LocalDate.now(), LocalDateTime.now(), LocalDateTime.now().plusHours(1), salaSimple, 20.0);
	}
	
	@Test
	public void setReservado1()
	{
		reserva.setReservado(false);
		
		assertEquals(reserva.getReservado(), false);
	}
	
	@Test
	public void setReservado2()
	{
		reserva.setReservado(true);
		
		assertEquals(reserva.getReservado(), true);
	}
	
	@Test
	public void setCliente1()
	{
		Boolean res = reserva.setCliente(null);
		assertFalse(res);
	}
	
	@Test
	public void setCliente2()
	{
		Boolean res = reserva.setCliente(cliente);
		assertTrue(res);
	}
	
	@Test
	public void setSesion1()
	{
		Boolean res = reserva.setSesion(null);
		assertFalse(res);
	}
	
	@Test
	public void setSesion2()
	{
		Boolean res = reserva.setSesion(sesion);
		assertTrue(res);
	}

}
