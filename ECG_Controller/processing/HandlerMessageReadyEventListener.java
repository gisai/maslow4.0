
package processing;

/**
 * Interfaz para los listeners de eventos del manejador
 * @author Borja Bordel
 * Proyecto Semola
 */
public interface HandlerMessageReadyEventListener {
    /**
     * Procesa el evento
     * @param evento 
     */
    void processEventMessageReady(HandlerMessageReadyEventObject evento);
}
