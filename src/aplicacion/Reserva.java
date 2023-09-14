/**
 * Este fichero muestra todo lo que tiene que ver con la clase Reserva
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */

package aplicacion;

import aplicacion.actividad.sesion.*;
import aplicacion.usuario.Cliente;
import java.io.Serializable;

/**
 * esta es una clase que representa la reserva 
 * 
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
 
 /**
 * Clase Reserva
 * Genera las propiedades de reserva
 */
public class Reserva implements Serializable{

	private Sesion sesion;
	private Boolean reservado;
	private Cliente cliente;

	/**
	 * La clase reserva se encargará de realizar las reservas de los clientes
	 * @param cliente cliente que hará la reserva
	 * @param sesion la sesión que reservará
	 * @param reservado si ha sido reserva la sesión o no
	 */
	public Reserva (Cliente cliente, Sesion sesion, Boolean reservado)
	{
		this.sesion = sesion;
		this.reservado = reservado;
		this.cliente = cliente;
	}
	
	/**
	 * Obtener si está reservado o no
	 * @return true si lo está o false si no lo está
	 */
	public Boolean getReservado()
	{
		return this.reservado;
	}
	
	/**
	 * Obtiene la sesion que se realizará la reserva
	 * @return la sesion
	 */
	public Sesion getSesion()
	{
		return this.sesion;
	}
	
	/**
	 * 
	 * @param sesion establece la sesion
	 * @return true si va bien, false si no.
	 */
	public Boolean setSesion(Sesion sesion)
	{
		
		if(sesion==null) {
			return false;
		}
		this.sesion = sesion;
		return true;
	}
	
	/**
	 * Obtiene el cliente que realizará la reserva
	 * @return el cliente
	 */
	public Cliente getCliente()
	{
		return this.cliente;
	}
	
	/**
	 * Establece el cliente que realizará la reserva
	 * @param cliente el cliente que realiza la reserva
	 * @return true si va bien, false si no.
	 */
	public Boolean setCliente(Cliente cliente)
	{
		
		if(cliente==null) {
			return false;
		}
		this.cliente = cliente;
		return true;
	}

	/**
	 * SETTER establece si esta reservado
	 * @param reservado el boolean si va a estar reservado
	 */
	public void setReservado(boolean reservado) {
		this.reservado = reservado;
		
	}

}
