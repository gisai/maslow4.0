
package processing.filters;

/**
 * Bloque PPM de procesado
 * @author Borja Bordel
 * Proyecto Semola
 */
public class PPMBlock {
    
    /**
     * Histórico
     */
    private double [] QRHistory;
    
    /**
     * Parámetros del algoritmo
     */
    private int QRLength = 400;    
    private int QRIndex;    
    private int samples;    
    private int samplesLength = 100;    
    private double tolerance = 10;    
    private int thesholdPulsations = 3;    
    private int counterPulsation;    
    private int pulsations;    
    private int oldPPM;
    
    
    /**
     * Constructor
     */
    public PPMBlock () {
        samples = 0;
        QRHistory = new double [QRLength];
        QRIndex = 0;  
        counterPulsation = 0;
        oldPPM = 0;
    }
    
    /**
     * Obtiene el valor de las PPM
     * @param data
     * @return 
     */
    public int getInstantPPM (double [] data) {
        double maximo = 0;
        int ppm = 0;
        
        for (int i = 0; i < data.length; i++) {
            if(data[i]>maximo){
                maximo = data [i];
            }
        }
        for (int i = 0; i < data.length; i++) {
            if(data[i] >= maximo-tolerance && data[i] <= maximo+tolerance){
                QRHistory[QRIndex++] = data [i];
                if (QRIndex >= QRLength) {
                    QRIndex = 0;
                }
            }
        }
        double threshold = updateThreshold ();
        for (int i = 0; i < data.length; i++) {
            if(data[i]>threshold){
                counterPulsation++;
                if (counterPulsation >= thesholdPulsations){
                    counterPulsation = 0;
                    pulsations++;
                }
            } else {
                counterPulsation = 0;
            }
        }
        samples+=  data.length;
        if (samples >= samplesLength) {
            samples = 0;
            ppm = pulsations*60;
            oldPPM = ppm;
        } else {
            ppm = oldPPM;
        }        
        return ppm;
    }
    
    /**
     * Actualiza el nivel considerado
     * @return 
     */
    public double updateThreshold () {
        double threshold = 0;
        for (int i = 0; i < QRLength; i++) {
            threshold+= QRHistory[i];
        }
        threshold /= QRLength;
        return threshold;
    }
}
