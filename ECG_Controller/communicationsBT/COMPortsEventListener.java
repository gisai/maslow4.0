package communicationsBT;

import java.util.EventListener;

/**
 * Interfaz para los listener de eventos de la red BT
 * @author Borja Bordel
 * Proyecto Semola
 */
public interface COMPortsEventListener extends EventListener {
    
    /**
     * Método para el procesado del evento
     * 
     * @param args Evento que desencadena el aviso
     */
    void processEventDevice (COMPortsEventObject args);  
}
