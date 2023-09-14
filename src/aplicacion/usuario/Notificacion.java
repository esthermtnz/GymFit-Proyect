/**
 * Este fichero muestra todo lo que tiene que ver con la clase Notificacion
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
package aplicacion.usuario;

import java.io.Serializable;

/**
 * esta es una clase que representa la notificacion
 * 
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class Notificacion implements Serializable{
    private String texto;

    /**
     * Constructor de notificacion
     * @param texto texto
     */
	public Notificacion(String texto)
	{
		this.texto = texto;
	}

	/**
	 * GETTER
	 * @return devuelve el texto 
	 */
    public String getTexto()
	{
		return this.texto;
	}
	
    /**
     * SETTER
     * @param texto texto a introducir
     * @return devuelve true si se ha puesto correctamente, de lo contrario false
     */
	public Boolean setTexto(String texto)
	{
		if(texto == null)
			return false;

		this.texto = texto;
		return true;
	}
}
