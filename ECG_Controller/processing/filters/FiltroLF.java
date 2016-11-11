package processing.filters;

/**
 * Filtro de frecuencias bajas
 * @author Borja Bordel
 * Proyecto Semola
 */
public class FiltroLF {
    
    //Diseñado para frecuencia normalizada 0.5Hz/fs = 0.1/100 = 10^-3
    private double [] coeficientes = {0.0051,-0.0000,-0.0419, 0.0000,0.2885,0.4968,0.2885,0.0000,-0.0419,-0.0000, 0.0051}; 
    
    /**
     * Parámetros de diseño IFIR
     */
    private int factorExpansion = 10;    
    private double [] coeficientesExpandidos = new double [coeficientes.length*factorExpansion];    
    private double [] coeficientesInterpolador = {0.0051,-0.0000,0.0419,0.0000,0.2885,0.4968,0.2885,0.0000,-0.0419,-0.0000,0.0051};
    
    /**
     * Objetos de diseño IFIR
     */
    private GenericFIRFilter filtroExpandido;    
    private GenericFIRFilter filtroInterpolador; 
    
    /**
     * Constructor
     */
    public FiltroLF () {
        for(int i = 0; i < coeficientesExpandidos.length; i++) {
            if (i%factorExpansion == 0) {
                coeficientesExpandidos [i] = coeficientes [i/factorExpansion];
            } else {
                coeficientesExpandidos [i] = 0;
            }            
        }
        filtroExpandido = new GenericFIRFilter(coeficientesExpandidos);
        filtroInterpolador = new GenericFIRFilter(coeficientesInterpolador);
    }
    
    /**
     * Filtra una secuencia
     * @param data
     * @return Secuencia filtrada
     */
    public double [] filtra (double [] data) {
        double [] resultado;
        
        //Aplicamos el filtro expandido
        resultado = filtroExpandido.filtraSequence(data);
        //Eliminamos la réplicas con el interpolado
        resultado = filtroInterpolador.filtraSequence(resultado);
        return resultado;
    } 
}
