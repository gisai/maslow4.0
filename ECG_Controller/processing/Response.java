package processing;

/**
 * Objeto respuesta
 * @author Borja Bordel
 * Proyecto Semola
 */
public class Response {
    
    /**
     * Nivel de alarma
     */
    private int alarmLevel;
    
    /**
     * Estado del usuario
     */
    private int userState;
    
    /**
     * ID del dispositivo
     */
    private int ID;
    
    /**
     * Constructor
     * @param ID
     * @param userState
     * @param alarmLevel 
     */
    public Response (int ID, int userState, int alarmLevel) {
        this.alarmLevel = alarmLevel;
        this.ID = ID;
        this.userState = userState;
    }
    
    /**
     * Getter
     * @return Estado del usuario 
     */
    public int getUserState () {
        return userState;
    }
    
    /**
     * Getter
     * @return ID 
     */
    public int getID () {
        return ID;
    }
    
    /**
     * Getter
     * @return Nivel de alarma
     */
    public int getAlarmLevel () {
        return alarmLevel;
    }
}
