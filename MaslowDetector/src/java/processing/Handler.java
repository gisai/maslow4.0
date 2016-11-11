
package processing;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import processing.jsonSimple.JSONArray;
import processing.jsonSimple.JSONObject;
import processing.jsonSimple.parser.JSONParser;
import processing.jsonSimple.parser.ParseException;
import server.ServerEventListener;
import server.ServerEventObject;
import servlets.CameraDataEventListener;
import servlets.CameraDataEventObject;

/**
 * Clase para gestionar los objetos JSON de la web
 * @author Borja Bordel
 * PROYECTO SEMOLA
 */
public class Handler implements ServerEventListener, CameraDataEventListener {
    
    /**
     * Calculadora de motivación
     */
    MaslowCalculator calculator;
    
    /**
     * Lista de listeners
     */
    private ArrayList <HandlerInformationEventListener> listeners;
    
    /**
     * Constructor
     */
    public Handler () {
        calculator = new MaslowCalculator();
        listeners = new ArrayList();
    }

    /**
     * Porcesa un evento del servidor
     * @param event 
     */
    @Override
    public void processServerEvent(ServerEventObject event) {
        JSONObject json = new JSONObject();
        JSONObject jsonCompleto = new JSONObject();
        JSONParser parser = new JSONParser ();        
        JSONObject mensaje;
                      
        try {
            mensaje = (JSONObject)parser.parse(event.getMensaje());
            json.put("ID", (String)mensaje.get("ID"));
            jsonCompleto.put("ID", (String)mensaje.get("ID"));
            JSONArray datosCrudos = (JSONArray)mensaje.get("RAWDATA");
            JSONArray datosLF = (JSONArray)((JSONObject)mensaje.get("PROCESSEDDATA")).get("LFSignal");
            JSONArray datosMF = (JSONArray)((JSONObject)mensaje.get("PROCESSEDDATA")).get("MFSignal");
            JSONArray datosCF = (JSONArray)((JSONObject)mensaje.get("PROCESSEDDATA")).get("CFSignal");
            JSONArray datosRF = (JSONArray)((JSONObject)mensaje.get("PROCESSEDDATA")).get("RFSignal");
            int datosBPM =  ((Long)((JSONObject)mensaje.get("PROCESSEDDATA")).get("BPMSignal")).intValue();
            int datosPPM = ((Long)((JSONObject)mensaje.get("PROCESSEDDATA")).get("PPMSignal")).intValue();

            for (int i = 0; i < datosCrudos.size(); i++) {
                double [] ecg = {(double)datosCrudos.get(i), (double)datosLF.get(i), (double)datosMF.get(i),
                                 (double)datosCF.get(i), (double)datosRF.get(i), datosBPM, datosPPM};
                calculator.updateECG(ecg);
                calculator.update();
                //Si se quisiera aquí se podría enviar nueva información
            }
            
            for(int i = 0; i<calculator.getLongMaslow (); i++) {
                jsonCompleto.put("NIVEL"+i, calculator.getMaslow()[i]);
            }
            
            int estado = calculator.getDominante();
            System.out.println(estado);
            int alarma;
            json.put("STATE", estado);
            switch (estado) {
                case 1:
                    alarma = 3;
                    break;
                case 2:
                    alarma = 2;
                    break;
                case 3:
                    alarma = 1;
                    break;
                default:
                    alarma = 0;
            }
            json.put("ALARMLEVEL", alarma);
            
            HandlerInformationEventObject evento = new HandlerInformationEventObject (json, jsonCompleto);
            for(HandlerInformationEventListener listener : listeners) {
                listener.processHandlerEvent(evento);
            }
        } catch (ParseException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }    
    

    /**
     * Procesa un evento de la cámara
     * @param event 
     */
    @Override
    public void processCameraDataEvent(CameraDataEventObject event) {
        JSONObject json = new JSONObject();
        JSONObject jsonCompleto = new JSONObject();
                    
        JSONObject mensaje = event.getData();
        json.put("ID", (String)mensaje.get("ID"));
        
        JSONObject mensajeEmo = (JSONObject)mensaje.get("resultados");
                
        double joy =  ((Long)((JSONObject)mensajeEmo.get("emotions")).get("joy")).doubleValue();
        double sadness =  ((Long)((JSONObject)mensajeEmo.get("emotions")).get("sadness")).doubleValue();
        double disgust =  ((Long)((JSONObject)mensajeEmo.get("emotions")).get("disgust")).doubleValue();
        double contempt =  ((Long)((JSONObject)mensajeEmo.get("emotions")).get("contempt")).doubleValue();
        double anger =  ((Long)((JSONObject)mensajeEmo.get("emotions")).get("anger")).doubleValue();
        double fear =  ((Long)((JSONObject)mensajeEmo.get("emotions")).get("fear")).doubleValue();
        double surprise =  ((Long)((JSONObject)mensajeEmo.get("emotions")).get("surprise")).doubleValue();
        double valence =  ((Long)((JSONObject)mensajeEmo.get("emotions")).get("valence")).doubleValue();
        double engagement =  ((Long)((JSONObject)mensajeEmo.get("emotions")).get("engagement")).doubleValue();

        double [] camera = {joy, sadness, disgust, contempt, anger, fear, surprise, valence, engagement};
        
        calculator.updateCamera(camera);
        calculator.update();
             
        for(int i = 0; i<calculator.getLongMaslow (); i++) {
             jsonCompleto.put("NIVEL"+i, calculator.getMaslow()[i]);
        }

         int estado = calculator.getDominante();
         System.out.println(estado);
         int alarma;
         json.put("STATE", estado);
         switch (estado) {
             case 1:
                 alarma = 3;
                 break;
             case 2:
                 alarma = 2;
                 break;
             case 3:
                 alarma = 1;
                 break;
             default:
                 alarma = 0;
         }
         json.put("ALARMLEVEL", alarma);

         HandlerInformationEventObject evento = new HandlerInformationEventObject (json, jsonCompleto);
         for(HandlerInformationEventListener listener : listeners) {
             listener.processHandlerEvent(evento);
         }        
    }
    
    /**
     * Añade un listener a la lista
     * @param listener 
     */
    public void addListener (HandlerInformationEventListener listener) {
        listeners.add(listener);
    }
    
}
