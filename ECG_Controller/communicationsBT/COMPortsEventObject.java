package communicationsBT;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Objeto evento de la red Bluetooth
 * @author Borja Bordel
 * Proyecto Semola
 */
public class COMPortsEventObject {
    
    /**
     * Mensaje recibido
     */
    private String mensaje;
    
    /**
     * Timestamp
     */
    private Timestamp marcaTiempo;
    
    /**
     * Cosntructor
     * @param mensaje 
     */
    public COMPortsEventObject (String mensaje) {
        this.mensaje = mensaje;
        Date date = new Date();
        marcaTiempo = new Timestamp(date.getTime());
    }
    
    /**
     * Getter
     * @return Mensaje de la red BT
     */
    public String getMessage () {
        return mensaje;
    }
    
    /**
     * Getter
     * @return Tiempo 
     */
    public Timestamp getTime () {
        return marcaTiempo;
    }
    
}
