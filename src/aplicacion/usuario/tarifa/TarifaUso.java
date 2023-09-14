/**
 * Este fichero muestra todo lo que tiene que ver con la clase TarifaUso
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
package aplicacion.usuario.tarifa;

import java.io.Serializable;

import aplicacion.actividad.Actividad;
import aplicacion.actividad.ActividadGrupal;
import aplicacion.actividad.sesion.Sesion;
import aplicacion.actividad.sesion.SesionAG;
import aplicacion.actividad.sesion.SesionPP;
import aplicacion.usuario.Cliente;


/**
 * esta es una clase que representa TarifaUso
 * 
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class TarifaUso extends Tarifa implements Serializable{
	
	/**
	 * Constructor Tarifa Uso
	 * @param precio el precio de la tarifa
	 */
	public TarifaUso(double precio){
		super(precio);
	}
	
    /**
     * Funcion para calcular el precio
     * @param sesion sesion que vas a pagar
     * @return el precio
     */
    public double calculaPrecio(Sesion sesion)
    {
    	if(sesion == null)
    		return -1.0;
    	super.setPrecio(sesion.getPrecio());
    	return super.getPrecio();
    }
    
    /**
	 * Función para mostrar la tarifaUso
	 * @return cadena
	 */
	@Override
	public String toString(){
		String cadena = super.toString() + "\n Tarifa de Pago por Uso";
		return cadena;
	}

}
