package processing.filters;

/**
 * Objeto filtro FIR genérico
 * @author Borja Bordel
 * Proyecto Semola
 */
public class GenericFIRFilter {
    
    /**
     * Longitud del filtro
     */
    private int length;
    /**
     * Retardos
     */
    private double[] delayLine;
    /**
     * Respuesta impulsiva
     */
    private double[] impulseResponse;
    /**
     * Cuenta interna
     */
    private int count = 0;

    /**
     * Constructor
     * @param coefs 
     */
    public GenericFIRFilter (double[] coefs) {
        length = coefs.length;
        impulseResponse = coefs;
        delayLine = new double[length];
    }

    /**
     * Algoritmo de filtrado
     * @param inputSample
     * @return Muestra filtrada
     */
    public double filtraSample(double inputSample) {
        delayLine[count] = inputSample;
        double result = 0.0;
        int index = count;
        for (int i=0; i<length; i++) {
            result += impulseResponse[i] * delayLine[index--];
            if (index < 0) {
                index = length-1;
            }
        }
        if (++count >= length) {
            count = 0;
        }
        return result;
    }
    
    /**
     * Filtra una secuencia
     * @param sequence
     * @return Secuencia filtrada
     */
    public double [] filtraSequence (double [] sequence) {
        int longitud = sequence.length;
        double [] filtrado = new double [longitud]; 
        for (int i = 0; i < longitud; i++) {
            filtrado[i] = filtraSample(sequence[i]);
        }
        return filtrado;
    }
}
