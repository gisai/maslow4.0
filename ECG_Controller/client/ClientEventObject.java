package client;

/**
 * Clase para codificr un evento del cliente
 * @author Borja Bordel
 * Proyecto Semola
 */
public class ClientEventObject {
    
    /**
     * Mnesaje recivido
     */
    private String mensajeServidor;
    
    /**
     * Constructor
     * @param mensaje 
     */
    public ClientEventObject (String mensaje) {
        mensajeServidor = mensaje;
    }
    
    /**
     * Getter
     * @return Mensaje del servidor 
     */
    public String getMensaje () {
        return mensajeServidor;
    }
}
