package logica.servidor;

import logica.servidor.bean.WrapperLista;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Set;

public class WSUtils {

    public static WrapperLista envolverLista(ArrayList<String> strings) {
        WrapperLista listaBean = new WrapperLista();
        listaBean.setListaString(strings);
        return listaBean;
    }
    
    public static int obtenerPuerto() {
    	return 9128;
    }

    public static WrapperLista envolverLista(Set<String> strings) {
        return envolverLista(new ArrayList<>(strings));
    }

    public static String imagenAString(byte[] bytes) {
        if (bytes != null) {
            return "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(bytes);
        } else {
            return null;
        }
    }

    
}
