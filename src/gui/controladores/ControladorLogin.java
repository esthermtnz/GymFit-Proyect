package gui.controladores;

import aplicacion.*;
import aplicacion.actividad.TipoActividad;
import aplicacion.excepciones.ExcepcionUsuario;
import aplicacion.excepciones.FechaPosterior;
import aplicacion.excepciones.SinTarifa;
import aplicacion.usuario.Administrador;
import aplicacion.usuario.Monitor;
import aplicacion.usuario.Usuario;

import java.awt.*;
import java.awt.dnd.DropTargetContext;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashSet;
import java.io.File;
import javax.swing.*;

import aplicacion.*;
import gui.LoginWindow;
import gui.RegistroUsuariosWindow;
import gui.Window;
import gui.cliente.ClienteWindow;

/**
 * Este fichero muestra todo lo que tiene que ver con la clase ControladorLogin
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class ControladorLogin implements ActionListener {
	private LoginWindow login;
	private Window window;
	private Gimnasio gimnasio;

	/**
	 * Constructor ControladorLogin
	 * 
	 * @param window   la ventana general
	 * @param gimnasio el gimnasio
	 */
	public ControladorLogin(Window window, Gimnasio gimnasio) {
		this.window = window;
		this.login = window.getLoginWindow();
		this.gimnasio = gimnasio;
	}

	/**
	 * Funcion para detectar todos los movimientos que realice el usuario en la
	 * aplicacion
	 * 
	 * @param e la accion
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Iniciar Sesion")) {
			try {
				if (this.gimnasio.iniciarSesion(this.login.getCampoUsuario(),
						this.login.getCampoContrasenya()) == false) {
					JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos", null,
							JOptionPane.WARNING_MESSAGE);
				} else {
					if (this.gimnasio.isCliente(this.login.getCampoUsuario())) {
						this.window.mostrarPanel("Cliente Window");
					} else if (this.gimnasio.isMonitor(this.login.getCampoUsuario())) {
						this.window.mostrarPanel("Monitor Window");

					} else if (this.gimnasio.isAdministrador(this.login.getCampoUsuario())) {
						this.window.getAdministradorWindow()
								.update(((Administrador) this.gimnasio.getUsuarioRegistrado()).getUsuario());
						this.window.mostrarPanel("Administrador Window");
					}
				}
			} catch (HeadlessException | IOException e1) {
				e1.printStackTrace();
			} catch (ExcepcionUsuario e1) {
				e1.printStackTrace();
			} catch (FechaPosterior e1) {
				e1.printStackTrace();
			} catch (SinTarifa e1) {
				JOptionPane.showMessageDialog(null, "No tienes la tarifa renovada", null,
						JOptionPane.WARNING_MESSAGE);
						
				this.window.mostrarPanel("Renovar Tarifa Window");
			}
		} else if (e.getActionCommand().equals("Registrate")) {
			if (this.window.getRegistroUsuariosWindow().getTipoTarifa().equals("Tarifa Plana (ANUAL)")
					|| this.window.getRegistroUsuariosWindow().getTipoTarifa().equals("Tarifa Plana (MENSUAL)")
					|| this.window.getRegistroUsuariosWindow().getTipoTarifa().equals("Tarifa Plana (TRIMESTRAL)")) {
				this.window.getRegistroUsuariosWindow().updateContenido2();
			} else if (this.window.getRegistroUsuariosWindow().getTipoTarifa().equals("Tarifa Uso")) {
				this.window.getRegistroUsuariosWindow().updateContenidoElim1();
			}
			HashSet<String> tipoActividadSet = new HashSet<>();
			for (TipoActividad tipo : this.gimnasio.getTipoActividadGrupal()) {
					tipoActividadSet.add(tipo.getNombre());
			}
			this.window.getRegistroUsuariosWindow().updateTipoActividad(tipoActividadSet);
			this.window.mostrarPanel("Registro Usuario Window");
		} else if(e.getActionCommand().equals("Borrar backup"))
		{
			File direccion = new File("./resources");
	        File archivo = new File(direccion, "gimnasio.txt");

	        if (archivo.exists()) {
	            if (archivo.delete()) {
	            	JOptionPane.showMessageDialog(null, "Has eliminado el backup", null,
							JOptionPane.INFORMATION_MESSAGE);
	            	
	            } else {
	            	JOptionPane.showMessageDialog(null, "No has podido eliminar el backup", null,
							JOptionPane.WARNING_MESSAGE);
	            	
	            }
	        } else {
	        	JOptionPane.showMessageDialog(null, "El backup no existe", null,
						JOptionPane.WARNING_MESSAGE);
	        	
	        }
	       
		}
		else if(e.getActionCommand().equals("Renovar tarifa"))
		{
			this.window.getRenovarTarifaWindow().updateContenidoElim1();
			HashSet<String> tipoActividadSet = new HashSet<>();
			for (TipoActividad tipo : this.gimnasio.getTipoActividadGrupal()) {
					tipoActividadSet.add(tipo.getNombre());
			}
			this.window.getRenovarTarifaWindow().updateTipoActividad(tipoActividadSet);
			this.window.mostrarPanel("Renovar Tarifa Window");
		}

		this.login.setCampoUsuario("");
		this.login.setCampoContrasenya("");

	}

};
