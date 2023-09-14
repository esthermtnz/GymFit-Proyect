package gui.controladores.cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;

import aplicacion.Gimnasio;
import aplicacion.Reserva;
import aplicacion.actividad.Actividad;
import aplicacion.actividad.ActividadGrupal;
import aplicacion.actividad.PlanPersonalizado;
import aplicacion.actividad.sesion.SesionLibre;
import aplicacion.excepciones.ExcepcionUsuario;
import aplicacion.excepciones.FechaPosterior;
import aplicacion.sala.Sala;
import aplicacion.usuario.Cliente;
import aplicacion.usuario.Monitor;
import aplicacion.usuario.Notificacion;
import aplicacion.usuario.tarifa.TarifaPlana;
import gui.RegistroUsuariosWindow;
import gui.Window;
import gui.cliente.ClienteWindow;

/**
 * Este fichero muestra todo lo que tiene que ver con la clase ControladorCliente
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class ControladorCliente implements ActionListener{
	
	private ClienteWindow cliente;
	private Window window;
	private Gimnasio gimnasio;

	/**
	 * Constructor ControladorCliente
	 * @param window la ventana general
	 * @param gimnasio el gimnasio
	 */
	public ControladorCliente(Window window, Gimnasio gimnasio)
	{
		this.window = window;
		this.cliente = window.getClienteWindow();
		this.gimnasio = gimnasio;
		
	}

	/**
	 * Funcion para detectar todos los movimientos que realice el usuario en la aplicacion
	 * @param e la accion
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Notificaciones"))
		{			
			HashSet<String> notificaciones = new HashSet<>();
			for (Notificacion notificacion: this.gimnasio.getUsuarioRegistrado().getNotificaciones()) {
				notificaciones.add(notificacion.getTexto());
			}
			this.window.getNotificacionClienteWindow().update(notificaciones);
			
			this.window.mostrarPanel("Notificacion Cliente Window");
		}
		else if(e.getActionCommand().equals("Cerrar Sesion"))
		{
			try {
				
				if(this.gimnasio.cerrarSesion(this.gimnasio.getUsuarioRegistrado())==true)
				{				
					this.window.mostrarPanel("Login Window");
				}
			}catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		else if(e.getActionCommand().equals("Datos Usuario"))
		{
			if(((Cliente)this.gimnasio.getUsuarioRegistrado()).getTarifa() instanceof TarifaPlana)
				this.window.getDatosUsuarioWindow().update(((Cliente)this.gimnasio.getUsuarioRegistrado()).getNombre(), ((Cliente)this.gimnasio.getUsuarioRegistrado()).getUsuario(), ((Cliente)this.gimnasio.getUsuarioRegistrado()).getTelefono(), ((Cliente)this.gimnasio.getUsuarioRegistrado()).getTarifa().getClass().getSimpleName(), ((TarifaPlana)((Cliente)this.gimnasio.getUsuarioRegistrado()).getTarifa()).getTipoActividad().getNombre(), ((Cliente)this.gimnasio.getUsuarioRegistrado()).getCancelaciones());
			else
				this.window.getDatosUsuarioWindow().update(((Cliente)this.gimnasio.getUsuarioRegistrado()).getNombre(), ((Cliente)this.gimnasio.getUsuarioRegistrado()).getUsuario(), ((Cliente)this.gimnasio.getUsuarioRegistrado()).getTelefono(), ((Cliente)this.gimnasio.getUsuarioRegistrado()).getTarifa().getClass().getSimpleName(), "", ((Cliente)this.gimnasio.getUsuarioRegistrado()).getCancelaciones());
			
			this.window.mostrarPanel("Datos Usuario Window");
		}
		else if(e.getActionCommand().equals("Mis Reservas"))
		{
			ArrayList<Reserva> reservas = new ArrayList<>();
			
			for(Reserva res : ((Cliente)this.gimnasio.getUsuarioRegistrado()).getReservas())
			{
				reservas.add(res);
			}
			try {
			this.window.getMisReservasWindow().updateMisReservas(reservas);
			}catch(ExcepcionUsuario | FechaPosterior e2) {
				e2.printStackTrace();
			}
			
			this.window.mostrarPanel("Mis Reservas Window");
		}
		else if(e.getActionCommand().equals("Planes Personalizados"))
		{
			ArrayList<PlanPersonalizado> actividades = new ArrayList<>();
			
			for(PlanPersonalizado act : this.gimnasio.getPlanesPersonalizados())
			{
				actividades.add(act);
			}
			try {
				this.window.getPlanesPersonalizadosWindow().updatePlanes(actividades);
			} catch (ExcepcionUsuario | FechaPosterior e1) {
				e1.printStackTrace();
			}
			
			this.window.mostrarPanel("Planes Personalizados Window");
		}
		else if(e.getActionCommand().equals("Actividades Grupales"))
		{
			
			ArrayList<ActividadGrupal> actividades = new ArrayList<>();
			
			for(ActividadGrupal act : this.gimnasio.getActividadesGrupales())
			{
				actividades.add(act);
			}
			try {
				this.window.getActividadesGrupalesWindow().updateActividadesGrupales(actividades);
			} catch (ExcepcionUsuario | FechaPosterior e1) {
				e1.printStackTrace();
			}
			
			this.window.mostrarPanel("Actividades Grupales Window");
		}else if(e.getActionCommand().equals("Sesiones Libres"))
		{
			ArrayList<SesionLibre> actividades = new ArrayList<>();
			
			for(SesionLibre act : this.gimnasio.getSesionesLibres())
			{
				actividades.add(act);
			}
			try {
				this.window.getSesionLibreWindow().updateSesiones(actividades);
			} catch (ExcepcionUsuario | FechaPosterior e1) {
				e1.printStackTrace();
			}
			
			this.window.mostrarPanel("Sesion Libre Window");
		}
	}

}
