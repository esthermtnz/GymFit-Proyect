/**
 * Este fichero muestra todo lo que tiene que ver con la clase Administrador
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
package aplicacion.usuario;

import java.io.Serializable;

/**
 * esta es una clase que representa el Administrador
 * 
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class Administrador extends Usuario implements Serializable{

	/**
	 * Constructor de Administrador
	 * @param usuario usuario del administrador
	 * @param contrasenya contraseña del administrador
	 */
    public Administrador(String usuario, String contrasenya)
    {
        super(usuario, contrasenya);
    }

    /**
     * Comprobar si es el administrador
     * @return true
     */
    @Override
    public boolean esAdministrador()
    {
    	return true;
    }
    
    /**
     * Comprobar si es el cliente
     * @return false
     */
    @Override
    public boolean esCliente()
    {
    	return false;
    }
    
    /**
     * Comprobar si es el monitor
     * @return false;
     */
    @Override
    public boolean esMonitor()
    {
    	return false;
    }

}
