package aplicacion.excepciones;
/**
	 * esta es una clase que representa FechaUnDia
	 * 
	 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
	 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
	 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
	 **/
public class FechaUnDia extends Exception{

	 /**
		 * Clase por si cancelas el mismo dia de la sesion
		 */
	public FechaUnDia() {
		super("No puedes cancelar el mismo dia de la sesion");
	}

}
