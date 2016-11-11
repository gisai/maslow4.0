package processing;

import client.ClientEventListener;
import client.ClientEventObject;
import communicationsBT.COMPortsEventListener;
import communicationsBT.COMPortsEventObject;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import processing.filters.BancoFiltros;
import processing.jsonSimple.JSONObject;
import processing.jsonSimple.parser.ParseException;

/**
 * Manjeador de mensajes
 * @author Borja Bordel
 * Proyecto Semola
 */
public class Handler implements COMPortsEventListener,ClientEventListener {
    
    /**
     * Listeners de JSON
     */
    private ArrayList <HandlerJSONReadyEventListener> listeners;
    
    /**
     * Listeners de respuestas
     */
    private ArrayList <HandlerMessageReadyEventListener> listenersD;
    
    /**
     * Banco de filtros para procesado
     */
    private BancoFiltros banco;
    
    /**
     * Constructor
     */
    public Handler () {
        listeners = new ArrayList();
        listenersD = new ArrayList();
        banco = new BancoFiltros();
    }
     
    /**
     * Procesa un evento del dispositivo
     * @param args 
     */
    public void processEventDevice (COMPortsEventObject args) {
        Message mess = ProtocolTranslator.deviceProtocol2Object(args.getMessage());
        JSONObject json = mess.toJSON();
        json.put("TIMESTAMP", args.getTime().getTime());
        json.put("PROCESSEDDATA", banco.filtraECG_JSON(mess.getData()));
        //Lanzamos evento de JSON preparado
        HandlerJSONReadyEventObject evento = new HandlerJSONReadyEventObject(json);
        
        for (HandlerJSONReadyEventListener listener : listeners) {
            listener.processEventJSONReady(evento);
        }
    }
    
    /**
     * Añade un listener a la lista de JSON
     * @param listener 
     */
    public void addListener (HandlerJSONReadyEventListener listener) {
        listeners.add(listener);
    }
    
    /**
     * Añade un listener a la lista de respuesta
     * @param listener 
     */
    public void addListenerD (HandlerMessageReadyEventListener listener) {
        listenersD.add(listener);
    }

    /**
     * Procesa los eventos del cliente
     * @param args 
     */
    @Override
    public void processEventClient(ClientEventObject args) {
        try {
            Response resp = ProtocolTranslator.serverResponse2Object(args.getMensaje());
            HandlerMessageReadyEventObject evento = new HandlerMessageReadyEventObject(resp);
            for (HandlerMessageReadyEventListener listener : listenersD) {
                listener.processEventMessageReady(evento);
            }
        } catch (ParseException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
