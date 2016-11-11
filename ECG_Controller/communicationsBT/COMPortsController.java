package communicationsBT;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import processing.HandlerMessageReadyEventListener;
import processing.HandlerMessageReadyEventObject;

/**
 * Clase que gestiona la comunicación con los puertos COM
 * @author Borja Bordel 
 * Proyecto Semola
 */
public class COMPortsController implements HandlerMessageReadyEventListener{
   
    /**
     * Puerto donde está el dispositivo
     */
   private String puerto; 
   
   /**
    * Comando de ejecuación
    */
   private String comando;
   
   /**
    * Velocidad del puerto
    */
   private String baudios;
   
   /**
    * Flujos de información desde el puerto
    */
   private InputStream std;
   private OutputStream out;
   private InputStream err; 
   
   /**
    * Proceso demonio
    */
   private Process proceso;
   
   /**
    * Lista de entidades que quieres recibir noticias de la comuncación
    * 
    */
   private ArrayList <COMPortsEventListener> listeners;
   
   public COMPortsController (int numPuerto, int numBaudios) {
       listeners = new ArrayList();
       puerto ="COM"+numPuerto;
       baudios = ""+numBaudios;
       comando = ".\\src\\daemon\\plink -serial "+ puerto + " -sercfg "+baudios+",8,n,1,n";
   }
        
    /**
     * Método que abre una comunicación a través de Bluetooth
     * 
     */
    public void iniciaComunicacion () throws IOException {
                        
        Runtime r = Runtime.getRuntime ();
        proceso = r.exec (comando);            
        std = proceso.getInputStream ();
        out = proceso.getOutputStream ();
        err = proceso.getErrorStream ();     
        
        Thread T = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    try {                            
                        if (std.available () > 0) {
                            String linea = "";
                            int value = std.read ();                                
                            linea = linea+ (char) value;                                
                            while ((char)value != '\u0017') {
                                if (std.available () > 0) {
                                    value = std.read ();
                                    linea = linea+ (char) value;
                                }
                            } 
                            String lineaFinal = linea.substring(0, linea.length()-2);  
                            Thread Th = new Thread(new Runnable() {
                                public void run() {
                                    //Envío de los eventos 
                                    COMPortsEventObject evento = new COMPortsEventObject(lineaFinal);
                                    for (COMPortsEventListener listener : listeners) {
                                        listener.processEventDevice(evento);
                                    }
                                }
                            } );
                            Th.start();
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(COMPortsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } );
        T.start();
    }
    
    /**
     * Trasnmite una repuesta hacia el dispositivo
     * @param level
     * @param alarm
     * @throws IOException 
     */
    public void transmiteResponse (int level, int alarm) throws IOException {
        out.write("RESPONSE\n".getBytes());
        out.write(("LEVEL "+level).getBytes());
        out.write('\n');
        out.write(("ALARM "+alarm).getBytes());
        out.write('\n');
        out.write('\u0017');
        out.flush ();
    }
    
    /**
     * Finaliza la ejecuación del dispositivo
     * @throws IOException 
     */
    public void transmiteCierre () throws IOException {
        out.write("START N\n".getBytes());
        out.write('\u0017');
        out.flush ();
    }
    
    /**
     * Inicia la ejecuación del dispositivo
     * @throws IOException 
     */
    public void transmiteStart () throws IOException {
        out.write("START Y\n".getBytes());
        out.write('\u0017');
        out.flush ();
    }
    
    /**
     * Cierra el puerto de comunicaciones BT
     * @throws IOException 
     */
    public void stop () throws IOException {        
        transmiteCierre();
        std.close();
        out.close();
        err.close();
        proceso.destroy();
    }
   
    /**
     * Añade un listener a la lista
     * @param listener 
     */
    public void addListener (COMPortsEventListener listener) {
        listeners.add(listener);
    }

    /**
     * Procesa el evento que se produce al estar listo un mensaje para transmitir
     * @param evento 
     */
    @Override
    public void processEventMessageReady(HandlerMessageReadyEventObject evento) {
       try {
           transmiteResponse(evento.getResponse().getUserState(), evento.getResponse().getAlarmLevel());
       } catch (IOException ex) {
           Logger.getLogger(COMPortsController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
}
