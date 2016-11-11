package processing;

import processing.jsonSimple.JSONObject;
import processing.jsonSimple.parser.JSONParser;
import processing.jsonSimple.parser.ParseException;

/**
 * Traductor de protocolos
 * @author Borja Bordel
 * Proyecto Semola
 */
public class ProtocolTranslator {
    
    /**
     * Traduce un mensaje del dipositivo a un objeto Menaje
     * @param mensaje
     * @return Objeto mensaje
     */
    public static Message deviceProtocol2Object (String mensaje) {
       int id;
       int amount;
       int min_value;
       int max_value;
       double [] data;
       
       String [] lineas = mensaje.split("\n");   
       for (int i= 0; i<lineas.length; i++) {
           lineas[i] = lineas[i].substring(0,lineas[i].length()-1);
       }
       id = Integer.parseInt(lineas[1].split(" ")[1]);
       amount = Integer.parseInt(lineas[2].split(" ")[1]);
       max_value = Integer.parseInt(lineas[3].split(" ")[1]);
       min_value = Integer.parseInt(lineas[4].split(" ")[1]);
       data = new double [lineas.length-6];
       for (int i = 6; i < lineas.length; i++) {
           data[i-6] = Double.parseDouble(lineas[i]);
       }
       Message mess = new Message (id, amount, max_value, min_value, data);
       return mess;
    }
    
    /**
     * Traduce un mensaje del servidor a un objeto respuesta
     * @param mensaje
     * @return Objeto respuesta
     * @throws ParseException 
     */
    public static Response serverResponse2Object (String mensaje) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject)parser.parse(mensaje);
        int alarmLevel = ((Long)jsonObject.get("ALARMLEVEL")).intValue();
        int userState = ((Long)jsonObject.get("STATE")).intValue();
        int ID = ((Long)jsonObject.get("ID")).intValue();
        
        Response resp = new Response(ID, userState, alarmLevel);
        return resp;
    }
}
