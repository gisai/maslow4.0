
package processing;

/**
 * Interfaz de los listeners que escuchan eventos JSON del manejador
 * @author Borja Bordel
 * Proyecto Semola
 */
public interface HandlerJSONReadyEventListener {
    /**
     * Método que procesa los eventos
     * @param args 
     */
    void processEventJSONReady (HandlerJSONReadyEventObject args);  
}
