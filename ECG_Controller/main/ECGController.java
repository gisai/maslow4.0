package main;

import client.BioClient;
import client.ClientEventListener;
import communicationsBT.COMPortsController;
import communicationsBT.COMPortsEventListener;
import java.io.IOException;
import processing.Handler;
import processing.HandlerJSONReadyEventListener;
import processing.HandlerMessageReadyEventListener;

/**
 * Objeto controlador de sensor de ECG
 * @author Borja Bordel
 * Proyecto Semola
 */
public class ECGController {
    
    /**
     * Cliente de comunicación con el servidor
     */
    private BioClient cliente;
    
    /**
     * Controlador de puertos COM
     */
    private COMPortsController COMController;
    
    /**
     * Manejador de mensajes de puertos
     */
    private Handler manejador;
    
    /**
     * Objeto controlador de sensores de ECG
     */
    private static ECGController controlador;
    
    /**
     * Puerto donde escucha el servidor
     */
    private static int puertoSocket = 8000;
    /**
     * Dirección donde se localiza
     */
    private static String url = "localhost";
    
    /**
     * Constructor
     * @param puertoSocket
     * @param url
     * @param puertoCOM
     * @param baudios 
     */
    private ECGController (int puertoSocket, String url, int puertoCOM, int baudios) {
        cliente = new BioClient(url, puertoSocket);
        COMController = new COMPortsController(puertoCOM, baudios);
        manejador = new Handler();
    }
    
    /**
     * Getter Singleton
     * @param puertoCOM
     * @param baudios 
     */
    public static void createControlller (int puertoCOM, int baudios) {
        if (controlador == null) {
            controlador = new ECGController (puertoSocket, url, puertoCOM, baudios);
        }
    }
    
    /**
     * Inicia la ejecuación del dispositivo
     * @throws IOException 
     */
    public void startDevice () throws IOException {
        COMController.transmiteStart();
        
    }
    
    /**
     * Detiene la ejecución del dispositivo
     * @throws IOException 
     */
    public void stopDevice () throws IOException {
        COMController.transmiteCierre();        
    }
    
    /**
     * Abre las comunicaciones con los dipositivos
     * @throws IOException 
     */
    public void openCommunicationDevice () throws IOException {
        COMController.iniciaComunicacion();
    }
    
    /**
     * Ejecuta el cliente
     * @throws IOException 
     */
    public void runClient () throws IOException {     
        subscribeJSONReadyEvents(cliente);
        subscribeMessageReadyEvents (COMController);
        cliente.startClient();      
        subscribeDeviceEvents(manejador); 
        subscribeClientEvents(manejador);
    }
    
    /**
     * Abre todos los puertos
     * @throws IOException 
     */
    public void openAll () throws IOException {
        runClient ();
        openCommunicationDevice ();
    }
    
    /**
     * Devuelve el controlador
     * @return 
     */
    public static ECGController getController () {
        return controlador;
    }
    
    /**
     * Detiene el cliente
     * @throws IOException 
     */
    public void stopClient () throws IOException {
        cliente.close();
    }
    
    /**
     * Detiene las comunicaciones con el dispositivo
     * @throws IOException 
     */
    public void closeCommunicationDevice () throws IOException {
        COMController.stop();
    }
    
    /**
     * Cierra todos los puertos
     * @throws IOException 
     */
    public void closeAll () throws IOException {
        closeCommunicationDevice ();
        stopClient ();
    }
    
    /**
     * Trasnmite los resultados hacia el dispositivo
     * @param level
     * @param alarm
     * @throws IOException 
     */
    public void transmiteResultados (int level, int alarm) throws IOException {
        COMController.transmiteResponse(level, alarm);
    }
    
    /**
     * Subscripción a los eventos del dispositivo
     * @param listener 
     */
    public void subscribeDeviceEvents (COMPortsEventListener listener) {
        COMController.addListener(listener);
    }
    
    /**
     * Subscripción a los eventos de tipo JSON
     * @param listener 
     */
    public void subscribeJSONReadyEvents (HandlerJSONReadyEventListener listener) {
        manejador.addListener(listener);
    }
    
    /**
     * Subscripción a los eventos del servidor 
     * @param listener 
     */
    public void subscribeMessageReadyEvents (HandlerMessageReadyEventListener listener) {
        manejador.addListenerD(listener);
    }
    
    /**
     * Subscripción a los eventos del cliente
     * @param listener 
     */
    public void subscribeClientEvents (ClientEventListener listener) {
        cliente.addListener(listener);
    }
    
    /**
     * Getter
     * @return Manejador 
     */
    public Handler getHandler () {
        return manejador;
    }
    
    /**
     * Getter
     * @return cliente 
     */
    public BioClient getCliente () {
        return cliente;
    }
    
}
