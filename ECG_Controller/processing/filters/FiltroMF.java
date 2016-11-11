
package processing.filters;

/**
 * Filtro de frecuencias medias 
 * @author Borja Bordel
 * Proyecto Semola
 */
public class FiltroMF {
    
     //Diseñado para frecuencia normalizada
    private double [] coeficientes = {-0.0052, -0.0080,0.0134, 0.1057,0.2405, 0.3072,0.2405,0.1057, 0.0134,-0.0080,-0.0052};
    
    /**
     * Objeto filtro FIR
     */
    private GenericFIRFilter filtro;
    
    /**
     * Constructor
     */
    public FiltroMF () {
        filtro = new GenericFIRFilter(coeficientes);
    }
    
    /**
     * Filtra una secuencia
     * @param data
     * @return Secuencia filtrada
     */
    public double [] filtra (double [] data) {
        double [] resultado;
        
        //Aplicamos el filtro
        resultado = filtro.filtraSequence(data);
        return resultado;
    }
}
