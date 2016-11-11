package processing;

/**
 * Interfaz para los listeners de eventos del manejador
 * @author Borja Bordel 
 * Proyecto Semola
 */
public interface HandlerInformationEventListener {
    /**
     * Procesa el evento del manejador
     * @param event 
     */
    void processHandlerEvent ( HandlerInformationEventObject event);
}
