/**
 * Este fichero muestra todo lo que tiene que ver con la clase SalaCompuesta
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
package aplicacion.sala;

import java.io.Serializable;
import java.time.*;
import java.util.*;


/**
 * esta es una clase que representa sala compuesta
 * 
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class SalaCompuesta extends Sala implements Serializable{
	
	private List <Sala> subsalas = new ArrayList<Sala>();
	
	/**
	 * Constructor Sala Compuesta
	 * @param nombre el nombre de la sala
	 * @param descripcion la descripcion
	 * @param aforo el aforo de la sala
	 */
    public SalaCompuesta(String nombre, String descripcion, Integer aforo){
		super(nombre,descripcion,aforo);
	}
	
	/**
	 * Funcion para crear la sala simple
	 * @param nombre de la sala
	 * @param descripcion la descripcion de la sala
	 * @param aforo el aforo de la sala
	 * @return ss la sala creada
	 */
	public SalaSimple crearSalaSimple(String nombre, String descripcion, Integer aforo)
	{
		if(nombre == null || descripcion == null || aforo < 0)
			return null;

		SalaSimple salaSimple = new SalaSimple(nombre, descripcion, aforo);
		salaSimple.definirHorarioClimatizacion(this.getHorarioClimatizacion().getHoraIni(), this.getHorarioClimatizacion().getHoraFin());
		this.setAforo(aforo);
		subsalas.add(salaSimple);
		
		return salaSimple;
	}
	
	/**
	 * Funcion para crear la sala compuesta
	 * @param nombre de la sala
	 * @param descripcion la descripcion de la sala
	 * @param aforo el aforo de la sala
	 * @return salaCompuesta la sala creada
	 */
	public  SalaCompuesta crearSalaCompuesta(String nombre, String descripcion, Integer aforo)
	{
		if(nombre == null || descripcion == null || aforo < 0 )
			return null;
			
		SalaCompuesta salaCompuesta = new SalaCompuesta(nombre, descripcion, aforo);
		salaCompuesta.definirHorarioClimatizacion(this.getHorarioClimatizacion().getHoraIni(), this.getHorarioClimatizacion().getHoraFin());
		
		this.setAforo(aforo);
		subsalas.add(salaCompuesta);
		
		return salaCompuesta;
	}
	
	/**
	 * Comprobar si es Sala simple
	 * @return false
	 */
	@Override
	public boolean isSimple()
	{
		return false;
	}
	
	/**
	 * Comprobar si es sala compuesta
	 * @return true
	 */
	@Override
	public boolean isCompuesta()
	{
		return true;
	}
	
	/**
	 * GETTER funcion para obtener las subsalas
	 * @return la lista de subsalas que la componen
	 */
	public List <Sala> getSubsalas(){
		return this.subsalas;
	}
	
	/**
     * Funcion para mostrar la sala compuesta
     * @return cadena cadena
     */
	@Override
	public String toString() {
		String cadena = super.toString();
		cadena += "Compuesta" + "\nNombre: " + this.getNombre() + " Descripcion: \n" + this.getDescripcion() + "\n Aforo: " + this.getAforo();
		cadena += "\nClimatizacion: " + this.getHorarioClimatizacion()+ "\n";
		return cadena;
	}
}