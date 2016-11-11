package processing.filters;

/**
 * Filtro de altas frecuencias
 * @author Borja Bordel
 * Proyecto Semola
 */
public class FiltroRF {
    
     //Diseñado para frecuencia normalizada [0.1 - 0.5]/fs
    private double [] coeficientes = {0,0.0151,-0.0908,0.0759, 0.2385,0.4756,0.2385,-0.0759,-0.0908,-0.0151,0};
    
    /**
     * Datos para diseño IFIR
     */
    private int factorExpansion = 10;    
    private double [] coeficientesExpandidos = new double [coeficientes.length*factorExpansion];    
    private double [] coeficientesInterpolador = {0.0051,-0.0000,0.0419,0.0000,0.2885,0.4968,0.2885,0.0000,-0.0419,-0.0000,0.0051};
    
    
    /**
     * Objetos del filtro IFIR
     */
    private GenericFIRFilter filtroExpandido;    
    private GenericFIRFilter filtroInterpolador; 
    
    /**
     * Constructor
     */
    public FiltroRF () {
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
