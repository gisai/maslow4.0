package server;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.MaslowController;

/**
 * Servidor
 * @author Borja Bordel 
 * Proyecto Semola
 */
public class BioServer {
    
    /**
     * Puerto donde se localiza el servidor
     */
    private int puerto;
    
    /**
     * Socket
     */
    private ServerSocket serverSocket = null;
    
    /**
     * Flag de activación
     */
    private boolean listening = true;
    
    /**
     * Lista de listeners
     */
    private ArrayList <ServerEventListener> listeners;
    
    /**
     * Constructor
     * @param puerto 
     */
    public BioServer (int puerto) {
        this.puerto = puerto;
        listeners = new ArrayList ();
    }
    
    /**
     * Inicia el servidor
     * @throws IOException 
     */
    public void startServer () throws IOException {

        try {
            serverSocket = new ServerSocket(puerto);
        } catch (IOException e) {
            Logger.getLogger(BioServer.class.getName()).log(Level.SEVERE, null, e);
            System.exit(-1);
        }
        Thread T = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (listening) {                    
                        new BioServerThread(serverSocket.accept(), listeners).start();
                        MaslowController.getController().addDevice();
                    }                 
                } catch (IOException ex) {
                    Logger.getLogger(BioServer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } );
        T.start();
    }
    
    /**
     * Cierra el servidor
     * @throws IOException 
     */
    public void closeServer () throws IOException {
        listening = false;
        serverSocket.close();
    }
    
    /**
     * Añade un listener
     * @param listener 
     */
    public void addListener (ServerEventListener listener) {
        listeners.add(listener);
    }
}
