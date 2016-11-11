package processing;

import processing.jsonSimple.JSONObject;

/**
 * Objeto evento del manejador
 * @author Borja Bordel 
 * Proyecto Semola
 */
public class HandlerInformationEventObject {
    
    /**
     * JSON respuesta
     */
    JSONObject respuesta;
    
    /**
     * JSON repuesta en formato largo
     */
    JSONObject respuestaCompleta;
    
    /**
     * Constructor
     * @param respuesta
     * @param respuestaCompleta 
     */
    public  HandlerInformationEventObject (JSONObject respuesta, JSONObject respuestaCompleta) {
        this.respuesta = respuesta;
        this.respuestaCompleta = respuestaCompleta;
    }
    
    /**
     * Getter
     * @return Respuesta 
     */
    public JSONObject getRespuesta () {
        return respuesta;
    }
    
    /**
     * Getter
     * @return Respuesta extendida 
     */
    public JSONObject getRespuestaCompleta () {
        return respuestaCompleta;
    }    
}
