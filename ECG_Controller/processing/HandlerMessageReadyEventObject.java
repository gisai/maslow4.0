
package processing;

/**
 * Objeto de traducción lista en el manejador
 * @author Borja Bordel
 * Proyecto Semola
 */
public class HandlerMessageReadyEventObject {
    
    /**
     * Respuesta traducida
     */
    private Response respuesta;
    
    /**
     * Constructor
     * @param respuesta 
     */
    public HandlerMessageReadyEventObject (Response respuesta) {
        this.respuesta = respuesta;
    }
    
/**
 * Getter
 * @return Respuesta 
 */    
    public Response getResponse () {
        return respuesta;
    }
}
