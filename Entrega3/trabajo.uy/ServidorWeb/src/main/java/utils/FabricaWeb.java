package utils;

import interfaces.IKeywordsLoader;
import interfaces.ILogica;

public class FabricaWeb {

    private FabricaWeb() {
    }

    public static FabricaWeb getInstance() {
        return new FabricaWeb();    
    }

    public ILogica getLogica() {
        return new Logica();
    }

    public IKeywordsLoader getKeywordsLoader() {
        return new KeywordsLoader(); 
    }
}
