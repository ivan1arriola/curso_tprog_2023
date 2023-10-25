package logica.servidor;

import logica.servidor.bean.DateBean;
import logica.servidor.bean.DTLista;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Set;

public class WSUtils {

    public static DTLista envolverLista(ArrayList<String> strings) {
        DTLista listaBean = new DTLista();
        listaBean.setListaString(strings);
        return listaBean;
    }

    public static DTLista envolverLista(Set<String> strings) {
        return envolverLista(new ArrayList<>(strings));
    }

    public static String imagenAString(byte[] bytes) {
        if (bytes != null) {
            return "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(bytes);
        } else {
            return null;
        }
    }

    public static DateBean toDateBean(LocalDate fechaNac) {
        return new DateBean(fechaNac);
    }
}
