
package server;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import main.MaslowController;
import processing.HandlerInformationEventListener;
import processing.HandlerInformationEventObject;

/**
 * Hebra independiente del servidor
 * @author Borja Bordel 
 * Proyecto Semola
 */
public class BioServerThread  extends Thread implements HandlerInformationEventListener{
    
    /**
     * Socket
     */
    private Socket socket = null;
    
    /**
     * Lista de listeners
     */
    private ArrayList <ServerEventListener> listeners;
    
    /**
     * Flujos de datos
     */
    private  PrintWriter out;    
    private BufferedReader in;
    
    /**
     * Constructor
     * @param socket
     * @param listeners 
     */    
    public BioServerThread(Socket socket, ArrayList <ServerEventListener> listeners) {
	super("BioServerThread");
	this.socket = socket;
        this.listeners = listeners;
        MaslowController.getController().subscribeHandlerEvents(this);
    }

    /**
     * Inicia la ejecución
     */
    public void run() {
	try {
	    out = new PrintWriter(socket.getOutputStream(), true);
	    in = new BufferedReader(
				    new InputStreamReader(
				    socket.getInputStream()));
	    
            String inputLine;
            while ((inputLine = in.readLine()) != null) {             
                ServerEventObject event = new ServerEventObject (inputLine);
                for(ServerEventListener listener : listeners) {
                    Thread T = new Thread(new Runnable() {
                        @Override
                        public void run() {
                                listener.processServerEvent(event);
                        }
                    });
                    T.start();
                }
            }
            out.close();
            in.close();
            socket.close();              
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    /**
     * Envía un nuevo JSON
     * @param json 
     */
    public void senData (String json) {
        out.println(json);
        out.flush();
    }      

    /**
     * Procesa un evento del manejador
     * @param event 
     */
    @Override
    public void processHandlerEvent(HandlerInformationEventObject event) {
        senData(event.getRespuesta().toJSONString());
    }
}
