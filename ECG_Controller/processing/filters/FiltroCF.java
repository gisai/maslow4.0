package processing.filters;

/**
 * Filtro para nivel de continua
 * @author Borja Bordel
 * Proyecto Semola
 */
public class FiltroCF {
    
    //Diseñado para frecuencia normalizada 0.1Hz/fs = 0.1/100 = 10^-3
    private double [] coeficientes = {0.0100,0.0249,0.0668,0.1249,0.1756,0.1957,0.1756,0.1249,0.0668,0.0249,0.0100}; 
    
    /**
     * Datos para diseño IFIR
     */
    private int factorExpansion = 10;    
    private double [] coeficientesExpandidos = new double [coeficientes.length*factorExpansion];    
    private double [] coeficientesInterpolador = {0.0051,-0.0000,0.0419,0.0000,0.2885,0.4968,0.2885,0.0000,-0.0419,-0.0000,0.0051};
    
    /**
     * Objetos para diseño IFIR
     */
    private GenericFIRFilter filtroExpandido;    
    private GenericFIRFilter filtroInterpolador; 
    
    /**
     * Constructor
     */
    public FiltroCF () {
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
     * Algoritmo de filtrado
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
