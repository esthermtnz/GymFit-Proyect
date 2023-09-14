package gui.controladores.monitor;

import aplicacion.*;
import aplicacion.actividad.*;
import aplicacion.equipacion.Maquina;
import aplicacion.excepciones.ExcepcionUsuario;
import aplicacion.excepciones.FechaPosterior;
import aplicacion.usuario.*;
import gui.Window;
import gui.cliente.ListaEsperaWindow;
import gui.monitor.MonitorWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;
/**
 * Este fichero muestra todo lo que tiene que ver con la clase ControladorMonitor
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class ControladorMonitor implements ActionListener{
	
	private MonitorWindow monitor;
	private Window window;
	private Gimnasio gimnasio;
	/**
	 * Constructor ControladorMonitor
	 * @param window la ventana general
	 * @param gimnasio el gimnasio
	 */
	public ControladorMonitor(Window window, Gimnasio gimnasio)
	{
		this.window = window;
		this.monitor = window.getMonitorWindow();
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
			this.window.getNotificacionMonitorWindow().update(notificaciones);
			
			
			this.window.mostrarPanel("Notificacion Monitor Window");
		}
		else if(e.getActionCommand().equals("Cerrar Sesion"))
		{
			try {
				if(this.gimnasio.cerrarSesion(this.gimnasio.getUsuarioRegistrado())==true)
				{
					this.window.mostrarPanel("Login Window");
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		else if(e.getActionCommand().equals("Datos Monitor"))
		{
			this.window.getDatosMonitorWindow().update(((Monitor)this.gimnasio.getUsuarioRegistrado()).getName() , ((Monitor)this.gimnasio.getUsuarioRegistrado()).getUsuario(), ((Monitor)this.gimnasio.getUsuarioRegistrado()).getEmail());
			this.window.mostrarPanel("Datos Monitor Window");
		}
		else if(e.getActionCommand().equals("Mis Actividades Grupales"))
		{
			
			ArrayList<ActividadGrupal> actividades = new ArrayList<>();
			
			for (ActividadGrupal act : ((Monitor)this.gimnasio.getUsuarioRegistrado()).getActividadesGrupales()) {
				actividades.add(act);
			}
			try {
				this.window.getMisActividadesGrupalesWindow().updateActividadesGrupales(actividades);
			} catch (ExcepcionUsuario | FechaPosterior e1) {
				e1.printStackTrace();
			}
			
			this.window.mostrarPanel("Mis Actividades Grupales Window");
		}
		else if(e.getActionCommand().equals("Mis Planes Personalizados"))
		{
			ArrayList<PlanPersonalizado> planes = new ArrayList<>();
			
			for (PlanPersonalizado act : ((Monitor)this.gimnasio.getUsuarioRegistrado()).getPlanesPersonalizados()) {
				planes.add(act);
			}

			try {
				this.window.getMisPlanesPersonalizadosWindow().updatePlanes(planes);
			} catch (ExcepcionUsuario | FechaPosterior e1) {
				e1.printStackTrace();
			}
			
			
			this.window.mostrarPanel("Mis Planes Personalizados Window");
		}
		else if(e.getActionCommand().equals("Consultar Maquinas")){
		ArrayList<Maquina> maquinasPropiedad = new ArrayList<>();
		ArrayList<Maquina> maquinasAlquiladas = new ArrayList<>();
		
		
		if(this.window.getConsultarMaquinasWindow().getBoxMaquina().equals("Propiedad"))
		{
			for(Maquina maq: this.gimnasio.getMaquinas()){
				if(maq.esPropiedad() == true){
					maquinasPropiedad.add(maq);
				}
			}
			this.window.getConsultarMaquinasWindow().updateMaquinas(maquinasPropiedad);
		}else if(this.window.getConsultarMaquinasWindow().getBoxMaquina().equals("Alquilada")){
			
				for(Maquina maq: this.gimnasio.getMaquinas()){
					if(maq.esAlquilada() == true){
						maquinasAlquiladas.add(maq);
					}
				}
			this.window.getConsultarMaquinasWindow().updateMaquinas(maquinasAlquiladas);
		}
		
			this.window.mostrarPanel("Consultar Maquinas Window");
		}
	}

}
