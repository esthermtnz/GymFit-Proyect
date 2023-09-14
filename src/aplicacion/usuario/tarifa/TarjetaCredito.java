/**
 * Este fichero muestra todo lo que tiene que ver con la clase TarjetaCredito
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */

package aplicacion.usuario.tarifa;

import java.io.Serializable;

/**
 * esta es una clase que representa TarjetaCredito
 * 
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class TarjetaCredito implements Serializable {
    private String numeroCredito;
    private String  pin; 
    private String titular;
    
    /**
     * 
     * @param numCredito el numero de la tarjeta
     * @param pin el pin 
     * @param titular el titular de la tarjeta
     */
    public TarjetaCredito(String numCredito, String  pin, String titular)
    {
    	this.numeroCredito = numCredito;
    	this.pin = pin;
    	this.titular = titular;
    }

    /**
     * Obtiene el numero de la tarjeta
     * @return el numero de la tarjeta
     */
    public String getCardNumberString(){
        return this.numeroCredito;
    }
    
    /**
     * GETTER
     * @return el mensaje
     */
     public String getMessage(){
        return "tarifa";
    }

    /**
     * Obtener el pin
     * @return el pin
     */
    
    public String  getPin(){
        return this.pin;
    }

    /**
     * Establece el pin
     * @param pin el pin
     * @return true si todo funciona bien
     */
    public Boolean setPin(String  pin){
    	if(pin == null)
    		return false;
    	
        this.pin = pin;
        return true;
    }
    
    /**
     * Establece el numero de la tarjeta de credito
     * @param numero de la tarjeta de credito
     * @return true si todo funciona bien
     */
    public Boolean setNumeroCredito(String numero){
    	if(numero == null)
    		return false;
    	
        this.numeroCredito = numero;
        return true;
    }

    /**
     * Obtiene el titular de la tarjeta
     * @return el tirular de la tarjeta
     */
    public String getTitular(){
        return this.titular;
    }

    /**
     * Establece el titular de la tarjeta
     * @param titular el titular de la tarjeta
     * @return true si todo funciona bien
     */
    public Boolean setTitular(String titular){
    	if(titular == null)
    		return false;
    	
        this.titular = titular;
        return true;
    }
    
    /**
     * Funcion para mostrar los datos de la tarjeta
     * @return cadena
     */
    @Override 
	public String toString(){
    	String cadena ="";
    	
    	cadena += "\nNumero: " + getCardNumberString() +  "\nPin: " + getPin() + "\nTitular: " + getTitular();
        return cadena;
    }
	
	
}
