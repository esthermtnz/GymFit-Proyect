/**
 * Este fichero muestra todo lo que tiene que ver con la clase Estado
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
package aplicacion.equipacion;

import java.io.Serializable;

/**
 * esta es una enumeracion que representa la Estado
 * 
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public enum Estado implements Serializable{
	OPERATIVA /*OPERATIVA*/, AVERIADA /*AVERIADA*/, EN_REPARACION /*EN_REPARACION*/, RETIRADA /*RETIRADA*/;
}
