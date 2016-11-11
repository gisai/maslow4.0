package processing;

/**
 * Calculador de motivación humana
 * @author Borja Bordel 
 * Proyecto Semola
 */
public class MaslowCalculator {
    
    /**
     * Motivación
     */
    private double [] maslow;
    
    /**
     * Motivación dominante
     */
    private int dominante;
    
    /**
     * Número se señales
     */
    private int longitudECG = 7;    
    private int longitudMaslow = 8;    
    private int longitudCamera = 9;
    
    /**
     * Coeficientes del modelo
     */
    private double [][] coeficientesECG = new double [longitudMaslow][longitudECG];    
    private double [][] coeficientesCamera = new double [longitudMaslow][longitudCamera];
    
    /**
     * Últimas muestras
     */
    private double [] lastECG;    
    private double [] lastCamera;
    
    /**
     * Constructor
     */
    public MaslowCalculator() {
        maslow = new double [8];        
        dominante = 1;        
        lastECG = new double [longitudECG];        
        lastCamera = new double [longitudCamera];
        
        for (int j=0; j < longitudMaslow ; j++) {
            for (int i = 0; i < longitudECG ; i++) {
                coeficientesECG[j][i] = Math.random();
            }
            for (int i = 0; i < longitudCamera ; i++) {
                coeficientesCamera[j][i] = Math.random();
            }
        }        
    }
    
    /**
     * Actualiza motivación con el ECG
     * @param ecg 
     */
    public void updateECG (double [] ecg) {
        lastECG = ecg;
    }
    
    /**
     * Actualiza motivación con la cámara
     * @param camera 
     */
    public void updateCamera (double [] camera) {
        lastCamera = camera;
    }
    
    /**
     * Actualiza
     */
    public void update () {        
        double max_valor = Integer.MIN_VALUE;
        for (int j=0; j < longitudMaslow ; j++) {
            maslow[j] = 0;
            for (int i = 0; i < longitudECG ; i++) {
                maslow[j]+=coeficientesECG[j][i]*lastECG[i];
            }
            for (int i = 0; i < longitudCamera ; i++) {
                maslow[j]+=coeficientesCamera[j][i]*lastCamera[i];
            }
            if (maslow[j] > max_valor) {
                dominante = j;
            }
        }         
    }
    
    /**
     * Getter
     * @return Motivación dominante 
     */
    public int getDominante () {
        return dominante;
    }
    
    /**
     * Getter
     * @return Motivación 
     */
    public double [] getMaslow () {
        return maslow;
    }
    
    /**
     * Getter
     * @return Número de estados de la pirámide 
     */
    public int getLongMaslow () {
        return longitudMaslow;
    }
}
