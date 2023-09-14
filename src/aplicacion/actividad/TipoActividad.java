/**
 * Este fichero muestra todo lo que tiene que ver con la clase TipoActividad
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
package aplicacion.actividad;

import java.io.Serializable;

/**
 * esta es una clase que representa el tipo de actividad
 * 
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class TipoActividad implements Serializable{
    private String nombre;

	/**
	 * Establecer el tipo de actividad
	 * @param nombre del tipo
	 */
    public TipoActividad(String nombre){
        this.nombre = nombre;
    }

	/**
	 * Obtener le nombre
	 * @return el nombre
	 */
    public String getNombre()
	{
		return this.nombre;
	}

    /**
     * SETTER 
     * @param nombre el nombre
     * @return true si se ha añadido y de lo contrario false
     */
	public Boolean setNombre(String nombre)
	{
		if(nombre == null)
			return false;

		this.nombre = nombre;
		return true;
	}
	
	/**
     * Funcion para mostrar el TipoActividad
     * @return cadena cadena
     */
	public String toString(){
        return "\nTipo de Actividad: " + this.getNombre();
    }
}