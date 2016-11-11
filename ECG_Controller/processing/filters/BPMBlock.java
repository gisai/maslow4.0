package processing.filters;

/**
 * Bloque de cálculo de BPM
 * @author Borja Bordel
 * Proyecto Semola
 */
public class BPMBlock {
    
    /**
     * Parámetros del algoritmo
     */
    private int thresholdBeats = 3;    
    private double energyMargin = 1.3;    
    private int counterBeats;    
    private int beats;    
    private int samples;
    private int samplesLength = 100;    
    private double [] energyHistory;    
    private int historyLength = 40;    
    private int historyIndex;    
    private int oldBPM;
    
    /**
     * Constructor
     */    
    public BPMBlock () {        
        counterBeats = 0;
        historyIndex = 0;
        oldBPM = 0;
        energyHistory = new double [historyLength];
    }
    
    /**
     * Obtiene el valor instantáneo de los BPM
     * @param data
     * @return bpm
     */
    public int getInstantBPM (double [] data) {
        double energy = 0;
        int bpm;
        for (int i = 0; i<data.length; i++) {
            energy += Math.pow(data[i],2);
        }        
        energyHistory[historyIndex++] = energy;
        if (historyIndex >= historyLength) {
            historyIndex = 0;
        }
        if (energy >= energyMargin*getMediumEnergy ()) {
            counterBeats++;
            if (counterBeats > thresholdBeats) {
                counterBeats = 0;
                beats++;
            }
        } else {
            counterBeats = 0;
        }
        samples+=  data.length;
        if (samples >= samplesLength) {
            samples = 0;
            bpm = beats*60;
            oldBPM = bpm;
        } else {
            bpm = oldBPM;
        }
        return bpm;
    } 
    
    /**
     * Calcula el valor medio de la energía
     * @return Valor medio
     */
    public double getMediumEnergy () {
        double media = 0;
        for (int i = 0; i < historyLength; i++) {
            media+= energyHistory[i];
        }
        media/=historyLength;
        return media;
    }
}
