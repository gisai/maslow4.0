package main;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import processing.Handler;
import processing.HandlerInformationEventListener;
import server.BioServer;
import server.ServerEventListener;

/**
 * Controlador del servidor de aplicaciones remoto
 * @author Borja Bordel 
 * Proyecto Semola
 */
public class MaslowController {
    
    /**
     * Servidor hacia el dispositivo
     */
    private BioServer server;
    
    /**
     * Puerto donde se encuentra
     */
    private static int puertoServer = 8000;
    
    /**
     * Objeto controlador
     */
    private static MaslowController controller;
    
    /**
     * Lista de dispositivos conectados
     */
    private ArrayList <String> connectedDevices;
    
    /**
     * Manejador de mensajes
     */
    private Handler manejador;
    
    /**
     * Constructor
     * @param puerto 
     */
    private MaslowController (int puerto) {
        server = new BioServer(puerto);
        connectedDevices = new ArrayList();
        manejador = new Handler();
    }
    
    /**
     * Getter tipo singleton
     */
    public static void createController () {
        if (controller == null ){
            controller = new MaslowController (puertoServer);
        }
    }
    
    /**
     * Getter singleton
     * @return 
     */
    public static MaslowController getController () {
        return controller;
    }
    
    /**
     * Ejecuta el servidor
     * @throws IOException 
     */
    public void runServer () throws IOException {
        subscribeServerEvents (manejador);
        server.startServer();
    }
    
    /**
     * Detiene el servidor
     * @throws IOException 
     */
    public void stopServer () throws IOException {
        server.closeServer();
    }   
    
    /**
     * Añade un nuevo dispositivo conectado
     */
    public void addDevice () {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        int ID = connectedDevices.size()+1;
        connectedDevices.add("Connected device NUMBER = "+ ID +" at "+dateFormat.format(date));
    }
    
    /**
     * Devulve el manejador
     * @return manejador
     */
    public Handler getManejador () {
        return manejador;
    }
    
    /**
     * Devuelve la lista de dispositivos conectados
     * @return Lista
     */
    public ArrayList getConnectedDevices () {
        return connectedDevices;
    }
    
    /**
     * Subscripción a los eventos del servidor
     * @param listener 
     */
    public void subscribeServerEvents (ServerEventListener listener) {
        server.addListener(listener);
    }
    
    /**
     * Subscripción a los eventos del manejador
     * @param listener 
     */
     public void subscribeHandlerEvents (HandlerInformationEventListener  listener) {
        manejador.addListener(listener);
    }
}
