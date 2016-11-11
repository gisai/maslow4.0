package server;

/**
 * Objeto evento del servidor
 * @author Borja Bordel 
 * Proyecto Semola
 */
public class ServerEventObject {
    
    /**
     * Mensaje generado
     */
    private String mensaje;
    
    /**
     * Constructor
     * @param mensaje 
     */
    public ServerEventObject (String mensaje) {
        this.mensaje = mensaje;
    }
    
    /**
     * Getter
     * @return Mensaje del servidor 
     */
    public String getMensaje () {
        return mensaje;
    }
}
