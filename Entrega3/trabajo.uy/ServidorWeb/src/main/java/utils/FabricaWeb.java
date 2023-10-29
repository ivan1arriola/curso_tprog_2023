package utils;

import interfaces.IKeywordsLoader;
import interfaces.ILogica;

public class FabricaWeb {

    private FabricaWeb() {
    }

    public static FabricaWeb getInstance() {
        return new FabricaWeb();    
    }

    public static ILogica getLogica() {
        return new Logica();
    }

    public static IKeywordsLoader getKeywordsLoader() {
        return new KeywordsLoader(); 
    }
}
