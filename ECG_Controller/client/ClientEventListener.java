package client;

/**
 * Interfaz para los listener de eventos del cliente
 * @author Borja Bordel
 * Proyecto Semola
 */
public interface ClientEventListener {
    
    /**
     * Método para procesar el evento
     * @param evento 
     */
    void processEventClient(ClientEventObject evento);
}
