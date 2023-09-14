package aplicacion.excepciones;
/**
 * esta es una clase que representa FueraHorarioClimatizacion
 * 
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 */
public class FueraHorarioClimatizacion extends Exception{
	/**
	 * Constructor.
	 * Este metodo es el constructor, el cual se encarga de inicializar los datos de la clase con los pasados como argumentos.
	 *
	 */
	public FueraHorarioClimatizacion() {
		super("Esta fuera del horario de climatizacion");
	}

}
