package gui.controladores.monitor;

import aplicacion.*;
import aplicacion.actividad.PlanPersonalizado;
import aplicacion.actividad.TipoObjetivo;
import aplicacion.actividad.sesion.requisito.Requisito;
import aplicacion.actividad.sesion.requisito.RequisitoCancelaciones;
import aplicacion.actividad.sesion.requisito.RequisitoEdad;
import aplicacion.actividad.sesion.requisito.RequisitoVeterania;
import aplicacion.excepciones.ExcepcionUsuario;
import aplicacion.excepciones.FechaPosterior;
import aplicacion.sala.Sala;
import aplicacion.usuario.Monitor;
import gui.Window;
import gui.administrador.CrearActividadGrupalWindow;
import gui.monitor.CrearPlanPersonalizadoWindow;

import java.util.*;

import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Este fichero muestra todo lo que tiene que ver con la clase ControladorCrearPlanPersonalizado
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class ControladorCrearPlanPersonalizado implements ActionListener{
	
	private CrearPlanPersonalizadoWindow planPersonalizado;
	private Window window;
	private Gimnasio gimnasio;
	/**
	 * Constructor ControladorCrearPlanPersonalizado
	 * @param window la ventana general
	 * @param gimnasio el gimnasio
	 */
	public ControladorCrearPlanPersonalizado(Window window, Gimnasio gimnasio)
	{
		this.window = window;
		this.planPersonalizado = window.getCrearPlanPersonalizadoWindow();
		this.gimnasio = gimnasio;
		
	}
	/**
	 * Funcion para detectar todos los movimientos que realice el usuario en la aplicacion
	 * @param e la accion
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Cancelar"))
		{
			this.window.mostrarPanel("Mis Planes Personalizados Window");
		}
		else if(e.getActionCommand().equals("Crear Plan"))
		{
			if(this.gimnasio.getUsuarioRegistrado().esMonitor()==true) {
				
				if(this.planPersonalizado.getBoxTipoRequisito() == "EDAD")
				{
					if(gimnasio.crearPlanPersonalizado(this.planPersonalizado.getCampoNombre(), (Monitor)gimnasio.getUsuarioRegistrado(), new RequisitoEdad(this.planPersonalizado.getCampoRequisitoMin(), this.planPersonalizado.getCampoRequisitoMax()), this.planPersonalizado.getCampoTipoObjetivo(), this.planPersonalizado.getCampoDescripcion())!=null)
					{
					
						ArrayList<PlanPersonalizado> planes = new ArrayList<>();
						
						for (PlanPersonalizado act : ((Monitor)this.gimnasio.getUsuarioRegistrado()).getPlanesPersonalizados()) {
							planes.add(act);
						}
						
						JOptionPane.showMessageDialog(null, "Ha creado correctamente el plan", null, JOptionPane.INFORMATION_MESSAGE);
						
						try {
							this.window.getMisPlanesPersonalizadosWindow().updatePlanes(planes);
						} catch (ExcepcionUsuario | FechaPosterior e1) {
							e1.printStackTrace();
						}
						this.window.mostrarPanel("Mis Planes Personalizados Window");
						
					}
					else
						JOptionPane.showMessageDialog(null, "No has podido crear el plan", null, JOptionPane.WARNING_MESSAGE);
					
				}
				else if (this.planPersonalizado.getBoxTipoRequisito() == "VETERANIA")
				{
					if(gimnasio.crearPlanPersonalizado(this.planPersonalizado.getCampoNombre(), (Monitor)gimnasio.getUsuarioRegistrado(), new RequisitoVeterania(this.planPersonalizado.getCampoRequisitoMin(), this.planPersonalizado.getCampoRequisitoMax()), this.planPersonalizado.getCampoTipoObjetivo(), this.planPersonalizado.getCampoDescripcion())!=null)
					{
						JOptionPane.showMessageDialog(null, "Ha creado correctamente el plan", null, JOptionPane.INFORMATION_MESSAGE);
						
						this.window.mostrarPanel("Mis Planes Personalizados Window");
					}
					else
						JOptionPane.showMessageDialog(null, "No has podido crear el plan", null, JOptionPane.WARNING_MESSAGE);
					
				}
				else
				{
					if(gimnasio.crearPlanPersonalizado(this.planPersonalizado.getCampoNombre(), (Monitor)gimnasio.getUsuarioRegistrado(), new RequisitoCancelaciones(this.planPersonalizado.getCampoRequisitoMin(), this.planPersonalizado.getCampoRequisitoMax()), this.planPersonalizado.getCampoTipoObjetivo(), this.planPersonalizado.getCampoDescripcion())!=null)
					{
						JOptionPane.showMessageDialog(null, "Ha creado correctamente el plan", null, JOptionPane.INFORMATION_MESSAGE);
						this.window.mostrarPanel("Mis Planes Personalizados Window");
					}
					else
						JOptionPane.showMessageDialog(null, "No has podido crear el plan", null, JOptionPane.WARNING_MESSAGE);
					
				}
				
				
			}
			else{
				Requisito req;
				String nombre = this.window.getCrearPlanPersonalizadoWindow().getCampoNombre();
				TipoObjetivo objetivo = this.window.getCrearPlanPersonalizadoWindow().getCampoTipoObjetivo();
				String descripcion = this.window.getCrearPlanPersonalizadoWindow().getCampoDescripcion();
				if(this.window.getCrearPlanPersonalizadoWindow().getBoxTipoRequisito() == "EDAD"){
					req = new RequisitoEdad(this.window.getCrearPlanPersonalizadoWindow().getCampoRequisitoMin(), this.window.getCrearPlanPersonalizadoWindow().getCampoRequisitoMax());
				}else if(this.window.getCrearPlanPersonalizadoWindow().getBoxTipoRequisito() == "VETERANIA"){
					req = new RequisitoVeterania(this.window.getCrearPlanPersonalizadoWindow().getCampoRequisitoMin(), this.window.getCrearPlanPersonalizadoWindow().getCampoRequisitoMax());
				}else{
					req = new RequisitoCancelaciones(this.window.getCrearPlanPersonalizadoWindow().getCampoRequisitoMin(), this.window.getCrearPlanPersonalizadoWindow().getCampoRequisitoMax());
				}
				
				if(this.gimnasio.crearPlanPersonalizado(nombre, (Monitor)this.gimnasio.getUsuarioRegistrado(), req, objetivo, descripcion)!=null)
					JOptionPane.showMessageDialog(null, "Has creado el plan", null, JOptionPane.INFORMATION_MESSAGE);		
				else
					JOptionPane.showMessageDialog(null, "No has podido crear el plan", null, JOptionPane.WARNING_MESSAGE);
				this.window.mostrarPanel("Mis Planes Personalizados Window");
			}
		}
		
		
		this.planPersonalizado.setCampoNombre("");
		this.planPersonalizado.setCampoDescripcion("");
		this.planPersonalizado.setCampoRequisitoMin("");
		this.planPersonalizado.setCampoRequisitoMax("");
		
	}

}
