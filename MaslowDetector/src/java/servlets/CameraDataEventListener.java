
package servlets;

/**
 * Interfaz para todos los listeners de eventos de camara
 * @author Borja Bordel 
 * Proyecto Semola
 */
public interface CameraDataEventListener {
    /**
     * Proces aun evento de cámara
     * @param event 
     */
    void processCameraDataEvent (CameraDataEventObject event);
}
