package processing.filters;

import java.util.HashMap;
import processing.jsonSimple.JSONArray;
import processing.jsonSimple.JSONObject;

/**
 * Objeto banco de filtros
 * @author Borja Bordel
 * Proyecto Semola
 */
public class BancoFiltros {
    
    /**
     * Filtros del banco
     */
    private FiltroCF filtroCF;
    private FiltroLF filtroLF;
    private FiltroMF filtroMF;    
    private FiltroRF filtroRF;
    
    /**
     * Otros módulos
     */
    private PPMBlock ppmBlock;
    private BPMBlock bpmBlock;
    
    /**
     * Constructor
     */
    public BancoFiltros () {
        filtroCF = new FiltroCF();
        filtroLF = new FiltroLF();
        filtroMF = new FiltroMF();
        filtroRF = new FiltroRF();
        ppmBlock = new PPMBlock();
        bpmBlock = new BPMBlock();
    }    
    
    /**
     * Procesa una secuencia y devuelve un JSONArray
     * @param data
     * @return JSONArray
     */
    public JSONArray array2JSONArray (double [] data) {
        JSONArray jsonarray = new JSONArray ();
        for (int i = 0; i < data.length; i++) {
            jsonarray.add(data[i]);
        }
        return jsonarray;
    }
        
    /**
     * Filtra una secuencia y devuelve un objeto JSON
     * @param data
     * @return JSON
     */
    public JSONObject filtraECG_JSON (double [] data) {
        JSONObject json = new JSONObject ();
                
        double [] LFSignal;
        double [] MFSignal;
        double [] CFSignal;
        double [] RFSignal;
        int PPMSignal;
        int BPMSignal;
        
        LFSignal = filtroLF.filtra(data);
        MFSignal = filtroMF.filtra(data);
        CFSignal = filtroCF.filtra(LFSignal);
        RFSignal = filtroRF.filtra(LFSignal);
        
        PPMSignal = ppmBlock.getInstantPPM(MFSignal);
        BPMSignal = bpmBlock.getInstantBPM(RFSignal);
        
        json.put("LFSignal", array2JSONArray(LFSignal));
        json.put("MFSignal", array2JSONArray(MFSignal));
        json.put("RFSignal", array2JSONArray(RFSignal));
        json.put("CFSignal", array2JSONArray(CFSignal));
        json.put("PPMSignal", PPMSignal);
        json.put("BPMSignal", BPMSignal);
        
        return json;
    }
    
    /**
     * Filtra una secuencia y devuelve una tabla hash
     * @param data
     * @return tabla HASH
     */
    public HashMap <String, Object> filtraECG_MAP (double [] data) {
        
        HashMap <String, Object> map = new HashMap <String, Object>();
        
        double [] LFSignal;
        double [] MFSignal;
        double [] CFSignal;
        double [] RFSignal;
        int PPMSignal;
        int BPMSignal;
        
        LFSignal = filtroLF.filtra(data);
        MFSignal = filtroMF.filtra(data);
        CFSignal = filtroCF.filtra(LFSignal);
        RFSignal = filtroRF.filtra(LFSignal);
        
        PPMSignal = ppmBlock.getInstantPPM(MFSignal);
        BPMSignal = bpmBlock.getInstantBPM(RFSignal);
        
        map.put("LFSignal", LFSignal);
        map.put("MFSignal", MFSignal);
        map.put("RFSignal", RFSignal);
        map.put("CFSignal", CFSignal);
        map.put("PPMSignal", PPMSignal);
        map.put("BPMSignal", BPMSignal);
        
        return map;
    }
    
}
