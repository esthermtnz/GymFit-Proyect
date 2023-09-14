package gui.controladores.cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JOptionPane;

import aplicacion.Gimnasio;
import gui.Window;
import gui.cliente.RenovarTarifaWindow;
import aplicacion.usuario.Cliente;
import aplicacion.usuario.tarifa.*;


/**
 * Este fichero muestra todo lo que tiene que ver con la clase ControladorRenovarTarifa
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class ControladorRenovarTarifa implements ActionListener {
	
	private RenovarTarifaWindow renovar;
	private Window window;
	private Gimnasio gimnasio;
	private LocalDate fechaFin;
	
	/**
	 * Constructor ControladorRenovarTarifa
	 * @param window la ventana general
	 * @param gimnasio el gimnasio
	 */
	public ControladorRenovarTarifa(Window window, Gimnasio gimnasio) {
		this.window = window;
		this.renovar = window.getRenovarTarifaWindow();
		this.gimnasio = gimnasio;
	}

	/**
	 * Funcion para detectar todos los movimientos que realice el usuario en la aplicacion
	 * @param e la accion
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(this.renovar.getTipoTarifa().equals("Tarifa Plana (ANUAL)") 
				|| this.renovar.getTipoTarifa().equals("Tarifa Plana (MENSUAL)")
				|| this.renovar.getTipoTarifa().equals("Tarifa Plana (TRIMESTRAL)")) {
			this.renovar.updateContenido2();
		}else {
			this.renovar.updateContenidoElim1();
		}
		
		if (e.getActionCommand().equals("Renovar")) {
			
			if (this.renovar.getTipoTarifa().equals("Tarifa Plana (ANUAL)")
    			|| this.renovar.getTipoTarifa().equals("Tarifa Plana (MENSUAL)")
    			|| this.renovar.getTipoTarifa().equals("Tarifa Plana (TRIMESTRAL)")) {
    			    
				
				    if (this.renovar.getTipoTarifa().equals("Tarifa Plana (ANUAL)")) {
				        fechaFin = LocalDate.now().plusYears(1);
				    }
				    else if (this.renovar.getTipoTarifa().equals("Tarifa Plana (MENSUAL)")) {
				        fechaFin = LocalDate.now().plusMonths(1);
				    }
				    else if (this.renovar.getTipoTarifa().equals("Tarifa Plana (TRIMESTRAL)")) {
				        fechaFin = LocalDate.now().plusMonths(3);
				    }
				   
				   
				TarifaPlana tarifa = new TarifaPlana (LocalDate.now(), fechaFin, 55.0,
						this.renovar.tipoTarifaPlana(this.renovar.getTipoTarifa()),
						this.renovar.tipoActividad(this.renovar.getActividadGrupal()));
				
				if(tarifa != null)
				{
					Cliente cliente = (Cliente) this.gimnasio.getUsuarioByData(this.window.getRenovarTarifaWindow().getCampoUsuario(), this.window.getRenovarTarifaWindow().getCampoContrasenya());
					
					if(cliente.setTarifa(tarifa)){
						JOptionPane.showMessageDialog(null, "Has renovado la tarifa plana", null,
									JOptionPane.INFORMATION_MESSAGE);
							this.window.mostrarPanel("Login Window");
					}
					else
					{
						JOptionPane.showMessageDialog(null, "No has podido renovar la tarifa", null,
								JOptionPane.WARNING_MESSAGE);
						this.window.mostrarPanel("Login Window");
					}
				}
		
			} else if(this.renovar.getTipoTarifa().equals("Tarifa Uso")){
				TarifaUso tarifa = new TarifaUso(0.0);
				Cliente cliente = (Cliente) this.gimnasio.getUsuarioByData(this.window.getRenovarTarifaWindow().getCampoUsuario(), this.window.getRenovarTarifaWindow().getCampoContrasenya());
				if(cliente.setTarifa(tarifa)){
					JOptionPane.showMessageDialog(null, "Has renovado la tarifa de uso", null,
									JOptionPane.INFORMATION_MESSAGE);
					this.window.mostrarPanel("Login Window");
				}else {
					JOptionPane.showMessageDialog(null, "No has podido renovar la tarifa", null,
							JOptionPane.WARNING_MESSAGE);
					this.window.mostrarPanel("Login Window");
				}
					
			}
	}
	else if(e.getActionCommand().equals("Cancelar"))
	{
		this.window.mostrarPanel("Login Window");
	}
	}
}
