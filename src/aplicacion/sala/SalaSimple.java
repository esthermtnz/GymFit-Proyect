/**
 * Este fichero muestra todo lo que tiene que ver con la clase SalaSimple
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
package aplicacion.sala;
import java.io.Serializable;
import java.util.*;


import aplicacion.actividad.sesion.Sesion;

/**
 * esta es una clase que representa sala simple
 * 
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class SalaSimple extends Sala implements Serializable{
	
	private List <Sesion> sesiones = new ArrayList<Sesion>();
	/**
	 * Constructor Sala Simple
	 * @param nombre el nombre de la sala
	 * @param descripcion la descripcion
	 * @param aforo el aforo de la sala
	 */
    public SalaSimple(String nombre, String descripcion, Integer aforo){
		super(nombre,descripcion,aforo);
	}
	
    /**
     * GETTER
     * @return lista con las sesiones de la sala
     */
    public List<Sesion> getSesiones(){
    	return sesiones;
    }
    /**
     * Comprobar si es sala simple
     * @return true
     */
    @Override
	public boolean isSimple()
	{
		return true;
	}
	
    /**
     * Comprobar si es sala compuesta
     * @return false
     */
    @Override
	public boolean isCompuesta()
	{
		return false;
	}
	
	/**
	 * Añadir sesion
	 * @param sesion la sesion a añadir
	 * @return true si todo funciona bien
	 */
	public Boolean addSesion (Sesion sesion) {
		if(sesion==null || salaContieneSesion(sesion)==true) 
			return false;
		
		
		return sesiones.add(sesion);
	}
	
	/**
	 * Eliminar sesion
	 * @param sesion la sesion a eliminar
	 * @return true si todo funciona bien
	 */
	public Boolean removeSesion(Sesion sesion) {
		if(sesion==null || salaContieneSesion(sesion)==false)
			return false;
		
		
		return sesiones.remove(sesion);
	}
	
	/**
	 * Comprobar si la sala cooontiene la sesion
	 * @param sesion la sesion a comprobar
	 * @return true si todo funciona bien
	 */
	public Boolean salaContieneSesion(Sesion sesion) {
		
		if(sesion==null) 
			return false;
		

		return sesiones.contains(sesion);
	}
	
	/**
     * Funcion para mostrar el sala simple
     * @return cadena cadena
     */
	@Override
	public String toString() {
		String cadena = super.toString();
		cadena += "\nNombre: " + this.getNombre() + "\nDescripcion: " + this.getDescripcion() + "\n Aforo: " + this.getAforo();
		cadena += "\nClimatizacion: " + this.getHorarioClimatizacion() + "\n";
		return cadena;
	}
	
}