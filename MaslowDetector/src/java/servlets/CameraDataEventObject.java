
package servlets;

import processing.jsonSimple.JSONObject;

/**
 * Evento de cámara
 * @author Borja Bordel 
 * Proyecto Semola
 */
public class CameraDataEventObject {
 
    /**
     * JSON con los datos
     */
    JSONObject datos;
    
    /**
     * Constructor
     * @param datos 
     */
    public CameraDataEventObject (JSONObject datos) {
        this.datos = datos;
    }
    
    /**
     * Getter
     * @return datos 
     */
    public JSONObject getData () {
        return datos;
    }
}
