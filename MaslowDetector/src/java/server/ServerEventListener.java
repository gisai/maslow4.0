
package server;

/**
 * Interfaz para los listeners de los eventos servidor
 * @author Borja Bordel 
 * Proyecto Semola
 */
public interface ServerEventListener {
    /**
     * Porcesa un evento del servidor
     * @param event 
     */
    void processServerEvent (ServerEventObject event);
}
