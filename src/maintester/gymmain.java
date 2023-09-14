package maintester;

import aplicacion.*;

import java.io.IOException;
import java.time.*;

import javax.swing.JOptionPane;

import aplicacion.actividad.TipoActividad;
import aplicacion.sala.*;
import aplicacion.usuario.Cliente;
import aplicacion.usuario.Monitor;
import aplicacion.usuario.tarifa.TarjetaCredito;
import aplicacion.usuario.tarifa.TipoTarifaPlana;
import aplicacion.usuario.tarifa.TarifaPlana;
import aplicacion.actividad.*;
import aplicacion.usuario.tarifa.*;
import aplicacion.actividad.sesion.SesionAG;
import aplicacion.actividad.sesion.requisito.*;
import aplicacion.equipacion.Maquina;
import aplicacion.equipacion.Material;
import aplicacion.excepciones.ExcepcionCancelaciones;
import aplicacion.excepciones.ExcepcionEdad;
import aplicacion.excepciones.ExcepcionPago;
import aplicacion.excepciones.ExcepcionUsuario;
import aplicacion.excepciones.ExcepcionVeterania;
import aplicacion.excepciones.FechaPosterior;
import aplicacion.excepciones.FueraHorarioClimatizacion;
import aplicacion.excepciones.SalaLlena;
/**
 * Este fichero muestra todo lo que tiene que ver con la clase gymmain
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class gymmain {
	/**
	 * Funcion que ejecuta un ejemplo del programa
	 * @param args los argumentos introducidos
	 * @throws ExcepcionUsuario si el usuario noe xiste
	 * @throws FechaPosterior si la fecha e sposterior
	 * @throws IOException si salta una excepcion de la IO
	 * @throws ExcepcionEdad si no cumple los requisitos de edad
	 * @throws ExcepcionVeterania si no cumple los requisitos de veterania
	 * @throws ExcepcionCancelaciones si no cumple los requisitos de cancelaciones
	 * @throws SalaLlena si la sala esta completa
	 * @throws FueraHorarioClimatizacion si esta fuera del horario de climatizacion
	 */
	
	//Funcion main del programa
	public static void main(String args[]) throws ExcepcionUsuario, FechaPosterior, IOException, ExcepcionEdad, ExcepcionVeterania, ExcepcionCancelaciones, SalaLlena, FueraHorarioClimatizacion{
		
		/*
		 * un demostrador básico de la aplicación
		 * (es decir, una clase de prueba con un método main) que ilustre la funcionalidad de la aplicación 
		 * (registro de clientes, login, creación de actividades, reserva de sesiones, pagos, ...) 
		 * y la persistencia (es decir, guardar y cargar los datos propios de la aplicación).
		 */
		 
		 //Creacion del gimnasio
		    Gimnasio gimnasio = Gimnasio.getGimnasio();
		 
		 //Registro del monitor
			Monitor carlos = gimnasio.registroMonitor("carlosgym", "566776787E", "gymcarlos", "Carlos Fernandez Lopez", "carlosferlop@gmail.com");
			Monitor celia = gimnasio.registroMonitor("celiafit", "456712", "fitcelia", "Celia Sanchez Perez", "celiafitness@hotmail.com");
			
		//Creacion del tipo actividad
			TipoActividad tipoActividad = gimnasio.crearTipoActividad("Padel");
			TipoActividad tipoAct1 = gimnasio.crearTipoActividad("Deportes de cesped");
			TipoActividad tipoAct2 = gimnasio.crearTipoActividad("Actividades cardio");
			TipoActividad tipoAct3 = gimnasio.crearTipoActividad("Actividades relax");
			
		//Registro del cliente
			Cliente marta = gimnasio.registroCliente("martagym", "gymmarta", "Marta", "6554566678", LocalDate.of(2003, 06, 13), "1234567890987654", "4455", "Marta Garcia Martinez", new TarifaUso(0.0));
			gimnasio.registroCliente("carlagym", "gymcarla", "Carla", "654321987", LocalDate.of(1998, 02, 21), "7894561234567891", "1234", "Carla Gomez Sanchez", new TarifaPlana(LocalDate.now(), LocalDate.now().plusMonths(3), 40.99, TipoTarifaPlana.TRIMESTRAL, tipoActividad));
			
		//Creacion de las salas
			SalaSimple sala1 = gimnasio.crearSalaSimple("Sala 1", "para zumba", 3, LocalDateTime.now().plusDays(1).plusHours(1), LocalDateTime.now().plusDays(1).plusHours(6));
			SalaSimple sala2 = gimnasio.crearSalaSimple("Sala 2", "para otros", 1, LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(2));
			SalaCompuesta sala3 = gimnasio.crearSalaCompuesta("Sala 3", "para aerobic", 22,LocalDateTime.now().plusHours(2), LocalDateTime.now().plusHours(3));
			
		//Creacion actividades y sesiones
			ActividadGrupal actGrup1 = gimnasio.crearActividadGrupal("Padel", "WPT", new RequisitoEdad(18, 50), carlos, tipoActividad);
						  	SesionAG padel = actGrup1.crearSesion(LocalDate.now().plusDays(1),  LocalDateTime.now().plusDays(1).plusHours(2), LocalDateTime.now().plusDays(1).plusHours(3), sala1, carlos);
			
			ActividadGrupal actGrup2 = gimnasio.crearActividadGrupal("Cinta", "de correr", new RequisitoEdad(18, 50), carlos, tipoAct2);
							SesionAG cinta = actGrup2.crearSesion(LocalDate.now().plusDays(2),  LocalDateTime.now().plusDays(2).plusHours(1), LocalDateTime.now().plusDays(2).plusHours(2), sala1, carlos);
							
			ActividadGrupal actGrup3 = gimnasio.crearActividadGrupal("TAIK", "LOS", new RequisitoEdad(18, 50), celia, tipoAct3);
							SesionAG taik = actGrup3.crearSesion(LocalDate.now().plusDays(2), LocalDateTime.now().plusDays(2),  LocalDateTime.now().plusDays(2).plusHours(1), sala2, celia);
								
		//Creacion planes y sesiones
			PlanPersonalizado plan1 = gimnasio.crearPlanPersonalizado("Atletismo", carlos,  new RequisitoEdad(18, 50), TipoObjetivo.PERDIDAPESO, "WPT");
			plan1.crearSesion(LocalDate.now().plusDays(1),LocalDateTime.now().plusDays(1).plusHours(1), LocalDateTime.now().plusDays(1).plusHours(2), sala2, carlos);
			
		//Creacion de maquinas	
			Maquina maquina = gimnasio.darAltaMaquinaPropiedad("Pesas", "Levantamiento de pesas", "Sony", 250.0, LocalDate.now().minusMonths(3));
			Maquina maquina2 = gimnasio.darAltaMaquinaAlquilada("Running", "Quemar Calorias", "Samsung", 10.0, LocalDate.now().minusMonths(3));
		
		//Creacion de material
			Material material = gimnasio.darAltaMaterial("Para rehabilitacion", 15, LocalDate.now().minusMonths(1), 30.99);
		
		//Creacion de la copia de seguridad
			Gimnasio.getGimnasio().salvarAplicacion(gimnasio);
		
	}
}
