package utils;

import interfaces.IKeywordsLoader;
import interfaces.ILogica;
import webservice.ServidorCentral;
import webservice.ServidorCentralService;

public class FabricaWeb {

    private FabricaWeb() {
    }

    public static FabricaWeb getInstance() {
        return new FabricaWeb();    
    }

    public ILogica getLogica() {
        return (ILogica) new ConexionServidor();
    }
    
    public ServidorCentral getServidor() {
    	ServidorCentralService service = new ServidorCentralService();
    	return service.getServidorCentralPort();
    }

    public IKeywordsLoader getKeywordsLoader() {
        return new KeywordsLoader(); 
    }
}
