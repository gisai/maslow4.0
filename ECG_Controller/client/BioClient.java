package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import processing.HandlerJSONReadyEventListener;
import processing.HandlerJSONReadyEventObject;

/**
 * Implementación del cliente web para conectar con el dispositivo personal
 * @author Borja Bordel
 * Proyecto Semola
 */
public class BioClient implements HandlerJSONReadyEventListener {
    
    /**
     * Puerto del socket
     */
    private int puerto;
    /**
     * URL destino
     */
    private String url;
    
    /**
     * Objeto socktet
     */
    private Socket clientSocket = null;
    
    /**
     * Entrada y salida estándar del socket
     */
    private PrintWriter out = null;
    private BufferedReader in = null;
    
    /**
     * Lista de listeners de los eventos del cliente
     */
    private ArrayList <ClientEventListener> listeners;

    /**
     * Constructor
     * @param url
     * @param puerto 
     */
    public BioClient (String url, int puerto) {
        listeners = new ArrayList();
        this.url = url;
        this.puerto = puerto;
    }
    
    /**
     * Método para iniciar la ejecución
     * @throws IOException 
     */
    public void startClient () throws IOException {    
        clientSocket = new Socket(url, puerto);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        Thread T = new Thread(new Runnable() {
            @Override
            public void run() {                
                    try {                        
                        String inputLine;
                        while ((inputLine = in.readLine()) != null) {
                            //Se envía para su procesado
                            ClientEventObject evento = new ClientEventObject(inputLine);
                            for (ClientEventListener listener : listeners) {
                                listener.processEventClient(evento);
                            }
                        }                        
                    } catch (IOException ex) {
                        Logger.getLogger(BioClient.class.getName()).log(Level.SEVERE, null, ex);
                    } 
                }             
        });
        T.start();
    }
    
    /**
     * Envía un JSON al servidor
     * @param json 
     */
    public void sendData (String json) {
        out.println(json);
        out.flush();
    }
       
    /**
     * Cierra el cliente
     * @throws IOException 
     */
    public void close () throws IOException {
        out.close();
        in.close();
        clientSocket.close();
    }   
    
    /**
     * Procesa un evento tipo JSON
     * @param args 
     */
    public void processEventJSONReady (HandlerJSONReadyEventObject args) {
        sendData(args.getJSON().toJSONString());
    }
    
    /**
     * Añade un listener a la lista
     * @param listener 
     */
    public void addListener (ClientEventListener listener) {
        listeners.add(listener);
    }   
    
}
