
package processing;

import processing.jsonSimple.JSONObject;

/**
 * Objeto de JSON listo en el manejador
 * @author Borja Bordel
 * Proyecto Semola
 */
public class HandlerJSONReadyEventObject {
    
    /**
     * Objeto JSON
     */
    private JSONObject json;
    
    /**
     * Constructor
     * @param json 
     */
    public HandlerJSONReadyEventObject (JSONObject json) {
        this.json = json;
    }
    
    /**
     * Getter
     * @return JSON creado 
     */
    public JSONObject getJSON () {
        return json;
    }
}
