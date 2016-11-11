package processing;

import processing.jsonSimple.JSONArray;
import processing.jsonSimple.JSONObject;

/**
 * Objeto mensaje
 * @author Borja Bordel
 * Proyecto Semola
 */
public class Message {
    /**
     * ID del dipositivo
     */
    private int ID;
    
    /**
     * Cantidad del datos
     */
    private int amount;
    
    /**
     * Datos
     */
    private double [] data;
    
    /**
     * Máximo valor
     */
    private int max_value;
    
    /**
     * Mínimo valor
     */
    private int min_value;
    
    /**
     * Constructor
     * @param ID
     * @param amount
     * @param max_value
     * @param min_value
     * @param data 
     */
    public Message (int ID, int amount, int max_value, int min_value, double [] data) {
        this.ID = ID;
        this.amount = amount;
        this.data = data;
        this.max_value = max_value;
        this.min_value = min_value;
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
     * @return Cantidad de datos 
     */
    public int getAmount () {
        return amount;
    }
    
    /**
     * Getter
     * @return Máximo valor 
     */
    public int getMaxValue () {
        return max_value;
    }
    
    /**
     * Getter 
     * @return Mínimo valor 
     */
    public int getMinValue () {
        return min_value;
    }
    
    /**
     * Getter
     * @return Datos 
     */
    public double [] getData () {
        return data;
    }
    
    /**
     * Crea un Objeto JSON con los datos del mensaje
     * @return Objeto JSON
     */
    public JSONObject toJSON () {
        JSONObject json = new JSONObject ();
        json.put("ID", getID());
        json.put("AMOUNT", getAmount());
        json.put("MIN_VALUE", getMinValue());
        json.put("MAX_VALUE", getMaxValue());
        JSONArray list = new JSONArray();
        
        for (int i = 0; i < data.length ; i++) {
            list.add(data[i]);
        }
        json.put("RAWDATA", list);
        return json;
    }
    
    /**
     * Crea un JSONArray con los datos
     * @return JSONArray
     */
    public JSONArray dataToJSONArray () {
        JSONArray list = new JSONArray();
        
        for (int i = 0; i < data.length ; i++) {
            list.add(data[i]);
        }
        return list;
    }
}
