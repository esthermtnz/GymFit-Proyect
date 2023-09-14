package gui.controladores;

import aplicacion.*;
import aplicacion.actividad.TipoActividad;
import aplicacion.excepciones.ExcepcionUsuario;
import aplicacion.excepciones.FechaPosterior;
import aplicacion.usuario.Cliente;
import aplicacion.usuario.Usuario;
import aplicacion.usuario.tarifa.Tarifa;
import aplicacion.usuario.tarifa.TarifaPlana;
import aplicacion.usuario.tarifa.TarifaUso;
import aplicacion.usuario.tarifa.TipoTarifaPlana;
import gui.*;
import gui.Window;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.*;

import javax.swing.*;
import java.util.*;

/**
 * Este fichero muestra todo lo que tiene que ver con la clase ControladorRegistroUsuarios
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class ControladorRegistroUsuarios implements ActionListener {
	private RegistroUsuariosWindow registroUsuarios;
	private Window window;
	private Gimnasio gimnasio;
	private LocalDate fechaFin;
	private Cliente cliente;

	/**
	 * Constructor ControladorRegistroUsuarios
	 * @param window la ventana general
	 * @param gimnasio el gimnasio
	 */
	public ControladorRegistroUsuarios(Window window, Gimnasio gimnasio) {
		this.window = window;
		this.registroUsuarios = window.getRegistroUsuariosWindow();
		this.gimnasio = gimnasio;

	}

	/**
	 * Funcion para detectar todos los movimientos que realice el usuario en la aplicacion
	 * @param e la accion
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(this.window.getRegistroUsuariosWindow().getTipoTarifa().equals("Tarifa Plana (ANUAL)") 
				|| this.registroUsuarios.getTipoTarifa().equals("Tarifa Plana (MENSUAL)")
				|| this.registroUsuarios.getTipoTarifa().equals("Tarifa Plana (TRIMESTRAL)")) {
			this.registroUsuarios.updateContenido2();
		}else {
			this.registroUsuarios.updateContenidoElim1();
		}
		
		if (e.getActionCommand().equals("Registrarse")) {
			
			if (this.registroUsuarios.getTipoTarifa().equals("Tarifa Plana (ANUAL)")
    			|| this.registroUsuarios.getTipoTarifa().equals("Tarifa Plana (MENSUAL)")
    			|| this.registroUsuarios.getTipoTarifa().equals("Tarifa Plana (TRIMESTRAL)")) {
    			    
				
				    if (this.registroUsuarios.getTipoTarifa().equals("Tarifa Plana (ANUAL)")) {
				        fechaFin = LocalDate.now().plusYears(1);
				    }
				    else if (this.registroUsuarios.getTipoTarifa().equals("Tarifa Plana (MENSUAL)")) {
				        fechaFin = LocalDate.now().plusMonths(1);
				    }
				    else if (this.registroUsuarios.getTipoTarifa().equals("Tarifa Plana (TRIMESTRAL)")) {
				        fechaFin = LocalDate.now().plusMonths(3);
				    }
				   
				   
			
				//DEFINIR PRECIO POR EL ADMIN
				TarifaPlana tarifa = new TarifaPlana(this.registroUsuarios.getFecha(), fechaFin, 55.0,
						this.registroUsuarios.tipoTarifaPlana(this.registroUsuarios.getTipoTarifa()),
						this.registroUsuarios.tipoActividad(this.registroUsuarios.getActividadGrupal()));
				
				
				
				try {
					cliente = gimnasio.registroCliente(this.registroUsuarios.getCampoUsuario(),
							this.registroUsuarios.getCampoContrasenya(), this.registroUsuarios.getCampoNombre(),
							this.registroUsuarios.getCampoTelefono(), this.registroUsuarios.getFecha(),
							this.registroUsuarios.getCampoTarjeta(), this.registroUsuarios.getCampoPin(),
							this.registroUsuarios.getCampoTitular(), tarifa);
						
					if (cliente != null ) {
						this.window.mostrarPanel("Login Window");

					} else {
						JOptionPane.showMessageDialog(null, "Asegurese que ha rellenado todos los campos", null, JOptionPane.WARNING_MESSAGE);
					}

				} catch (ExcepcionUsuario e1) {
					System.err.println(e1);
				} catch (FechaPosterior e2) {
					System.err.println(e2);
				}

			} else {
				//aqui meter el precio que ha puesto el administrador
				TarifaUso tarifa = new TarifaUso(33.0);
				try {
					cliente = gimnasio.registroCliente(this.registroUsuarios.getCampoUsuario(),
							this.registroUsuarios.getCampoContrasenya(), this.registroUsuarios.getCampoNombre(),
							this.registroUsuarios.getCampoTelefono(), this.registroUsuarios.getFecha(),
							this.registroUsuarios.getCampoTarjeta(), this.registroUsuarios.getCampoPin(),
							this.registroUsuarios.getCampoTitular(), tarifa);
					if (cliente != null) {
						this.window.mostrarPanel("Login Window");

					} else {
						JOptionPane.showMessageDialog(null, "Asegurese que ha rellenado todos los campos", null,
								JOptionPane.WARNING_MESSAGE);
					}

				} catch (ExcepcionUsuario e1) {
					System.err.println(e1);
				} catch (FechaPosterior e2) {
					System.err.println(e2);
				}
			}
		
			try {
			    this.gimnasio.salvarAplicacion(this.gimnasio);
			} catch (IOException e1) {
			    e1.printStackTrace();
			}
			

		} else if (e.getActionCommand().equals("Cancelar")) {
			this.window.mostrarPanel("Login Window");
		}

		
		
		this.registroUsuarios.setCampoNombre("");
		this.registroUsuarios.setCampoUsuario("");
		this.registroUsuarios.setCampoContrasenya("");
		this.registroUsuarios.setCampoTelefono("");
		this.registroUsuarios.setCampoTarjeta("");
		this.registroUsuarios.setCampoTitular("");
		this.registroUsuarios.setCampoPin("");
	}

}
