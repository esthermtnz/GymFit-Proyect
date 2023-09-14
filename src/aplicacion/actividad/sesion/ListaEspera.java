/**
 * Este fichero muestra todo lo que tiene que ver con la clase ListaEspera
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
package aplicacion.actividad.sesion;


import java.io.Serializable;
import java.util.*;


import aplicacion.usuario.Notificacion;
import aplicacion.usuario.Cliente;

/**
 * esta es una clase que representa la ListaEspera
 * 
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class ListaEspera implements Serializable{

    private List <Cliente> clienteslista = new ArrayList<Cliente>();
    
    /**
     * Constructor Lista espera
     */
    public ListaEspera() {}
    
    /**
     * Función para puntar al cliente
     * @param cliente cliente que se va a apuntar
     * @return true si funciona bine
     */
    public Boolean apuntar(Cliente cliente)
    {
        if(listaContieneCliente(cliente)==true || cliente == null)
            return false;
		
        return clienteslista.add(cliente);
    }
    
    /**
     * Crear notificacion si está en lista de espera
     * @param notificacion cadena que se mandará al cliente
     * @return true si funciona bine
     */
    public Boolean notificar(String notificacion)
    {   	
    	Boolean status = false;
    	
        if(notificacion == null)
            return false;
        
        for(Cliente c: this.clienteslista)
        {
        	status = c.getNotificaciones().add(new Notificacion(notificacion));
        }
		
        return status;
    }
    
    /**
     * Funcion para obtener la lista de clientes de la lista de espera
     * @return lista de clientes
     */
    public List <Cliente> getListaClientes(){
        return this.clienteslista;
    }
    
    /**
     * Comprobar que el cliente está en la lista
     * @param cliente Cliente a comprobar
     * @return true si funciona bine
     */
    public Boolean listaContieneCliente(Cliente cliente)
    {
    	if(cliente == null)
    		return false;
    	
    	return clienteslista.contains(cliente);
    }
}
